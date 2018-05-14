package com.born.util.web;

import javax.servlet.http.HttpServletRequest;

/**
 * Web 辅助类
 * 
 * @author zys
 *
 */
public class WebUtil {

	/**
	 * 获取ip
	 * 
	 * @param request
	 * @return
	 */
	public static String getRemortIP(HttpServletRequest request) {
		String ip = null;
		if (request.getHeader("x-forwarded-for") == null) {
			ip = request.getRemoteAddr();
		} else {
			ip = request.getHeader("x-forwarded-for");
		}
		/** 如果存在，号，需要分隔 */
		if (ip.indexOf(",") != -1) {
			String[] split = ip.split(",");
			ip = split[0];
		}
		return ip;
	}
}
