package com.born.config.auth;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.born.config.auth.token.Token;
import com.born.config.auth.token.TokenManager;
import com.born.core.constant.CommonConstants;
import com.born.core.constant.PropertiesConstants;
import com.born.util.constants.AppConstants;
import com.born.util.encrypt.security.SecurityUtil;
import com.born.util.json.JsonResult;
import com.born.util.json.ResultCode;
import com.born.util.json.ResultEntity;

import lombok.extern.slf4j.Slf4j;

/**
 * Token 验证
 * 
 * @ClassName: AccessTokenFilter
 * @Description: 相当于网关，请求都需要过滤Token验证
 * @author 张永胜
 * @date 2018年5月7日 上午10:15:20
 * @version 1.0
 */
@Slf4j
public class AccessTokenFilter extends AccessControlFilter {

	/**
	 * Token管理
	 */
	@Autowired
	private TokenManager tokenManager;

	/**
	 * 每个接口都会过此方法来验证Token 检验接口是否被篡改
	 */
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		try {
			return isValidAccessToken(request, response);
		} catch (RuntimeException e) {
			outWrite(response, JsonResult.info(ResultCode.INTERNAL_SERVER_ERROR, e.getMessage()));
		}
		return true;
	}

	/**
	 * 
	 * @Title: isValidAccessToken
	 * @Description: 与缓存中Token进行验证
	 * @param request
	 * @return
	 * @author 张永胜
	 * @return boolean
	 * @date 2018年5月10日 上午11:24:47
	 */
	private boolean isValidAccessToken(ServletRequest request, ServletResponse response) {
		HttpServletRequest req = (HttpServletRequest) request;
		/**
		 * 用户和token拼接字符串 这里采用拼接是混淆加密方式
		 */
		String token = req.getHeader(AppConstants.AUTHORIZATION);
		String timestamp = req.getHeader("timestamp");// 时间戳
		String nonc = req.getHeader("nonc");// 随机数
		String sign = req.getHeader("sign");// 签名
		log.info("token value  {} " + token);
		
		/**
		 * 判断请求头是否包含以下参数
		 */
		if (!StringUtils.isNoneEmpty(token, timestamp, sign, nonc)) {
			log.info("非法请求");
			outWrite(response, JsonResult.fail(ResultCode.ILLEGAL_REQUEST));
			return false;
		}

		/**
		 * 请求过期验证
		 */
		Date dt = new Date();
		long lt = new Long(timestamp) * 1000L;
		Date times = new Date(lt);
		if ((dt.getTime() - times.getTime()) / 60000 > 3) {
			log.info("请求已过期");
			outWrite(response, JsonResult.fail(ResultCode.OVERDUE_REQUEST));
			return false;
		}

		/**
		 * 验证Token是否有效
		 */
		Token model = tokenManager.getToken(token);
		if (!tokenManager.checkToken(model)) {
			log.info("访问被拒绝, 未授权");
			outWrite(response, JsonResult.fail(ResultCode.UNAUTHORIZED));
			return false;
		}

		/**
		 * 签名验证
		 */
		String vStr = append(token, nonc, timestamp, model.getUserId());
		if (!SecurityUtil.verifyRSA(SecurityUtil.encryptMd5Hex(vStr),
				PropertiesConstants.PROPERTIES_MAP.get(CommonConstants.SIGN_PUBLIC_KEY), sign)) {
			log.info("签名错误");
			outWrite(response, JsonResult.fail(ResultCode.INCORRECT_SIGNATURE));
			return false;
		}

		return true;
	}

	/**
	 * 
	 * @Title: append
	 * @Description: 拼接字符串
	 * @param param
	 * @return
	 * @author 张永胜
	 * @return String
	 * @date 2018年5月10日 下午3:11:19
	 */
	private String append(String... param) {
		if (param != null) {
			StringBuffer str = new StringBuffer();
			for (int i = 0; i < param.length; i++) {
				str.append(param[i]);
			}
			return str.toString();
		}
		return null;
	}

	/**
	 * 
	 * @Title: outWrite
	 * @Description: 返回给客户端
	 * @param response
	 * @param result
	 * @author 张永胜
	 * @return void
	 * @date 2018年5月10日 下午3:31:52
	 */
	public void outWrite(ServletResponse response, ResultEntity<String> result) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			os.write(JSON.toJSONString(result, SerializerFeature.WriteMapNullValue).getBytes());
		} catch (IOException e) {
			log.info("IO异常", e);
		} finally {
			if (os != null) {
				try {
					os.flush();
					os.close();
				} catch (IOException e) {
					log.info("IO异常", e);
				}
			}
		}
	}

	/**
	 * 如果Token验证无效 会执行此方法 return false 表示不再往下面执行，直接返回给客服端
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		return false;
	}

}
