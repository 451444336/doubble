package com.born.facade.vo;

import java.io.Serializable;

import lombok.Data;
/**
 * 
* @ClassName: PermissionInfoVO 
* @Description: 权限详情数据
* @author lijie 
* @date 2018年5月3日 下午3:04:12 
*
 */
@Data
public class PermissionInfoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2368272787599612226L;
	/**
	 * 公司ID
	 */
	private String companyId;
	/**
	 * 权限ID
	 */
	private String authorityId;
	/**
	 * 是否可用：0、不可用 1、可用
	 */
	private Byte isUsable;
	/**
	 * 菜单/权限名称
	 */
	private String menuName;
	/**
	 * 菜单/权限访问地址
	 */
	private String menuUrl;
	/**
	 * 权限归属类型：1、pc  2、手机
	 */
	private Byte ascription;
	/**
	 * 是否共享：0、否 1、是
	 */
	private Byte isShare;
	/**
	 * 手机url
	 */
	private String appUrl;
	/**
	 * 权限图标
	 */
	private String icon;
	/**
	 * 手机排序
	 */
	private Integer appSeq;
	/**
	 * 排序
	 */
	private Integer menuSeq;
	/**
	 * 权限类型：1、正常菜单 2、后台菜单 3、首页菜单 4、公有菜单
	 */
	private String type;
	/**
	 * 权限操作类型：1、查看 2、修改删除 3、操作 4、特殊
	 */
	private String operType;
	/**
	 * 上级ID
	 */
	private String parentId;
	/**
	 * 菜单级别
	 */
	private String menuLevel;
}
