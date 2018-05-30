package com.born.util.String;

import java.util.regex.Pattern;

/**
 * 
 * @ClassName: StringRedisKeyUtil
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 张永胜
 * @date 2018年5月28日 上午10:11:49
 * @version 1.0
 */
public class StringUtil {

	/**
	 * 拼接顺序不要错
	 * 
	 * @Title: appendRedisKey
	 * @Description: 拼接RedisKey值
	 * @param args
	 * @return
	 * @author 张永胜
	 * @return String
	 * @date 2018年5月28日 上午10:11:29
	 */
	public static String appendRedisKey(Object... args) {
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < args.length; i++) {
			str.append(args[i]).append(":");
		}
		str.deleteCharAt(str.length() - 1);
		return str.toString();
	}

	/**
	 * 判断是否是数字
	 * 
	 * @Title: isInteger
	 * @Description: 判断是否是正整数字
	 * @param str
	 * @return
	 * @author 张永胜
	 * @return boolean
	 * @date 2018年5月30日 上午11:02:08
	 */
	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}
}
