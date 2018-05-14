package com.born.config.shiro.token;

import java.io.Serializable;

import org.apache.shiro.authc.UsernamePasswordToken;

import lombok.Data;
/**
 * 
* @ClassName: ShiroToken 
* @Description: 安全框架 token
* @author lijie 
* @date 2018年5月7日 下午3:09:43 
*
 */
@Data
public class ShiroToken extends UsernamePasswordToken implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 59798346400642308L;

	/**
	 * 登录密码[字符串类型] 因为父类是char[]
	 */
	private String pswd;
	/**
	 * 登录IP
	 */
	private String ip;
	
	public ShiroToken(String username, String password, String ip) {
		super(username, password);
		this.pswd = password;
		this.ip = ip;
	}

	public ShiroToken(String username, String password, boolean rememberMe, String ip) {
		super(username, password, rememberMe);
		this.pswd = password;
		this.ip = ip;
	}
}
