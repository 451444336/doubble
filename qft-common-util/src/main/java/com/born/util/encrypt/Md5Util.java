package com.born.util.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Base64;

import lombok.extern.slf4j.Slf4j;
/**
 * 
* @ClassName: Md5Util 
* @Description: md5加密工具类
* @author lijie 
* @date 2018年5月2日 下午4:52:56 
*
 */
@Slf4j
public class Md5Util {
	
	private static MessageDigest MD5 ;
	
	static {
		try{
			MD5 = MessageDigest.getInstance("MD5");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* @Title: getMD5 
	* @Description: 签名数据
	* @param @param data
	* @param @return    设定文件 
	* @return byte[]    返回类型 
	* @author lijie
	* @throws
	 */
	public static byte[] getMD5(byte[] data) {
		byte[] md5 = null;
		try {
			md5 = MD5.digest(data);
		} catch (Exception e) {
		}
		return md5;
	}
	/**
	 * 
	* @Title: encode 
	* @Description: Base64 编码md5加密后的数据
	* @param @param str
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author lijie
	* @throws
	 */
	public static String encode(String str){
		try {
			return Base64.getEncoder().encodeToString(MD5.digest(str.getBytes("UTF-8")));  
		} catch (UnsupportedEncodingException e) {
			log.error("Base64 编码md5加密后的数据异常",e);
		}
		return "";
	}
	/**
	 * 
	* @Title: getMD5Str 
	* @Description: 标准MD5加密
	* @param @param str
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author lijie
	* @throws
	 */
	public static String toMd5(String str) {
		StringBuffer sb = new StringBuffer();
		try {
			MD5.update(str.getBytes());
			byte b[] = MD5.digest();
			int i;
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					sb.append("0");
				sb.append(Integer.toHexString(i));
			}
		} catch (Exception e) {
			log.error("标准MD5加密异常",e);
			return "";
		}
		return sb.toString().toUpperCase();
	}
}
