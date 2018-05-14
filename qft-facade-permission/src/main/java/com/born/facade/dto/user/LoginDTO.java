package com.born.facade.dto.user;

import org.hibernate.validator.constraints.NotBlank;

import com.born.core.base.BaseValidate;

import lombok.Data;
/**
 * 
* @ClassName: LoginDTO 
* @Description: 用户登录传输DTO 
* @author lijie 
* @date 2018年5月7日 上午11:40:39 
*
 */
@Data
public class LoginDTO extends BaseValidate<LoginDTO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6046345271568662632L;

	/**
     * 用户账号
     */
	@NotBlank(message="用户账号不能为空")
    private String account;
    /**
     * 密码
     */
	@NotBlank(message="用户密码不能为空")
    private String password;
	/**
	 * 当前IP
	 */
	@NotBlank(message="IP不能为空")
	private String ip;
}
