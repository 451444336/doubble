package com.born.config.auth.token.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.born.config.auth.token.Token;
import com.born.config.auth.token.TokenManager;
import com.born.core.rediscache.ICacheService;
import com.born.util.String.StringUtil;
import com.born.util.constants.AppConstants;
import com.born.util.encrypt.security.SecurityUtil;

import sun.misc.BASE64Encoder;

/**
 * 
 * @ClassName: RedisTokenManager
 * @Description: 通过Redis存储和验证token的实现类
 * @author 张永胜
 * @date 2018年5月4日 下午6:41:31
 * @version 1.0
 */
@Component
public class RedisTokenManager implements TokenManager {

	@Autowired
	private ICacheService<String, Object> iCacheService;

	@Override
	public Token createToken(String userId) {

		String aToken = UUID.randomUUID().toString().replace("-", "");
		String token = (System.currentTimeMillis() + aToken + new Random().nextInt(999999999)) + "";

		// 数据指纹 128位长 16个字节 md5
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			byte md5[] = md.digest(token.getBytes());

			// base64编码--任意二进制编码明文字符
			BASE64Encoder encoder = new BASE64Encoder();
			String resultToken = encoder.encode(md5);// 加密Token
			iCacheService.set(StringUtil.appendRedisKey(AppConstants.TOKEN_KEY, userId), resultToken,
					AppConstants.TOKEN_DAY, TimeUnit.DAYS);
			return new Token(SecurityUtil.encryptDes(userId), resultToken);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public boolean checkToken(Token tokenModel) {
		if (tokenModel == null) {
			return false;
		}

		String token = (String) iCacheService.get(
				StringUtil.appendRedisKey(AppConstants.TOKEN_KEY, SecurityUtil.decryptDes(tokenModel.getUserId())));
		if (token == null || !token.equals(tokenModel.getToken())) {
			return false;
		}

		// 如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
		iCacheService.expire(StringUtil.appendRedisKey(AppConstants.TOKEN_KEY, tokenModel.getUserId()),
				AppConstants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
		return true;
	}

	@Override
	public Token getToken(String authentication) {
		if (authentication == null || authentication.length() == 0) {
			return null;
		}

		String[] param = authentication.split("_");
		if (param.length != 2) {
			return null;
		}

		/**
		 * 使用userId和源token简单拼接成的token，可以增加加密措施
		 */
		String userId = param[0];
		String token = param[1];
		return new Token(userId, token);
	}

	@Override
	public void deleteToken(String key) {
		iCacheService.remove(key);
	}

}
