package com.born.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.UUID;

import sun.misc.BASE64Encoder;

public class TokenUitl {

	private static final TokenUitl instance = new TokenUitl();

	/**
	 * 返回类的对象
	 * 
	 * @return
	 */
	public static TokenUitl getInstance() {
		return instance;
	}

	/**
	 * 生成Token Token：Nv6RRuGEVvmGjB+jimI/gw==
	 * 
	 * @return
	 */
	public String makeToken() {
	
		String aToken = UUID.randomUUID().toString().replace("-", "");
		String token = (System.currentTimeMillis() + aToken + new Random().nextInt(999999999)) + "";
		// 数据指纹 128位长 16个字节 md5
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			byte md5[] = md.digest(token.getBytes());

			// base64编码--任意二进制编码明文字符 adfsdfsdfsf
			BASE64Encoder encoder = new BASE64Encoder();
			return encoder.encode(md5);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}
