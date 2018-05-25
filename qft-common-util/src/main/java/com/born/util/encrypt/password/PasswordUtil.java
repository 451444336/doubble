package com.born.util.encrypt.password;

import com.born.util.encrypt.Md5Util;

/**
 * 
* @ClassName: PasswordUtil 
* @Description: 密码加密 
* @author lijie 
* @date 2018年5月9日 下午2:36:06 
*
 */
public final class PasswordUtil {

	public final static String PAWWWORD_KEY = "70b4f7fd-cf80-4751-b6b0-11d9e41f09cf";

	/**
	 * 
	* @Title: encrypt 
	* @Description:登录密码加密
	* @param @param accout
	* @param @param pwd
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author lijie
	* @throws
	 */
	public static String encrypt(String account, String pwd) {
		StringBuilder sb = new StringBuilder(pwd);
		sb.append(PAWWWORD_KEY);
		sb.append("_" + account);
		return Md5Util.encode(sb.toString());
	}
	
	public static void main(String[] args) {
		System.out.println(encrypt("15823690857", "123456"));
	}
}
