package com.born.facade.vo;

import java.io.Serializable;

import lombok.Data;
/**
 * 
 * @ClassName: UserAuthVO
 * @Description: 用户权限数据
 * @author: lijie
 * @date: 2018年5月6日 下午12:27:56
 */
@Data
public class UserAuthVO implements Serializable {
	
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = -4635026346742156904L;

	/**
	 * 权限ID
	 */
	private Long authorityId;
	/**
	 * 公司ID
	 */
	private String companyId;
	/**
	 * 权限名称
	 */
	private String authorityName;
	/**
	 * 权限地址
	 */
	private String authorityUrl;
	/**
	 * 权限归属类型：1、pc  2、手机
	 */
	private Byte ascription;
	/**
	 * 权限图标
	 */
	private String icon;
	/**
	 * 权限操作类型：1、查看 2、修改删除 3、操作 4、特殊
	 */
	private Byte operType;
	/**
	 * 是否可用：0、不可用 1、可用
	 */
	private Byte isUsable;
}
