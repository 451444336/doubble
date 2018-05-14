package com.born.util.encrypt.security.coder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.StringUtils;

import sun.misc.BASE64Encoder;

/**
 * md5加密工具类
 * 
 * @author xionglei
 *
 */
public class Md5Coder {

	private static MessageDigest md5;

	static {
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Title: encode
	 * @Description: Base64 编码md5加密后的数据
	 * @param str
	 * @return
	 * @author 张永胜
	 * @return String
	 * @date 2018年5月9日 下午3:15:04
	 */
	public static String encode(String str) {
		String newstr = "";
		try {
			BASE64Encoder base64en = new BASE64Encoder();
			newstr = base64en.encode(md5.digest(str.getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return newstr;
	}

	/**
	 * 
	 * @Title: toMD5
	 * @Description: 标准MD5加密
	 * @param inStr
	 * @return
	 * @author 张永胜
	 * @return String
	 * @date 2018年5月9日 下午3:15:15
	 */
	public static String toMD5(String inStr) {
		StringBuffer sb = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(inStr.getBytes());
			byte b[] = md.digest();
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
			return null;
		}
		return sb.toString().toUpperCase();
	}

	/**
	 * 
	 * @Title: md5
	 * @Description: MD5加密
	 * @param str
	 * @return
	 * @author 张永胜
	 * @return String
	 * @date 2018年5月9日 下午3:15:23
	 */
	public static String getMd5(String str) {
		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			if (!StringUtils.isEmpty(str)) {
				messageDigest.update(str.getBytes("UTF-8"));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		byte[] byteArray = messageDigest.digest();

		StringBuffer md5StrBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
				md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
			} else {
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
			}
		}
		return md5StrBuff.toString().toUpperCase();
	}

}
