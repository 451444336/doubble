package com.born.config.auth.token;

/**
 * 
 * @ClassName: TokenManager
 * @Description: 对token进行操作的接口
 * @author 张永胜
 * @date 2018年5月4日 下午6:41:48
 * @version 1.0
 */
public interface TokenManager {

	/**
	 * 创建一个token关联上指定用户
	 * 
	 * @param userId
	 *            指定用户的id
	 * @return 生成的token
	 */
	public Token createToken(String userId);

	/**
	 * 检查token是否有效
	 * 
	 * @param token
	 *            token 值
	 * @return 是否有效
	 */
	public boolean checkToken(Token token);

	/**
	 * 从字符串中解析token
	 * 
	 * @param authentication
	 *            加密后的字符串
	 * @return
	 */
	public Token getToken(String authentication);

	/**
	 * 清除token
	 * 
	 * @param key
	 *            登录用户的id
	 */
	public void deleteToken(String key);
}
