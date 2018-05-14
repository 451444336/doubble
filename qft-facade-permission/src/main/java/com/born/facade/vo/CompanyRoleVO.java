package com.born.facade.vo;

import lombok.Data;

/**
 * @Description: 角色vo
 * @author wangxy
 * @date 2018年4月27日 下午2:15:21
 */
@Data
public class CompanyRoleVO {
	
	/**
	 * 主键ID
	 */
	private Long id;

	/**
	 * 角色名称
	 */
	private String roleName;
	/**
	 * 添加时间
	 */
	private String createTime;
	/**
	 * 是否能权限编辑
	 */
	private Integer isAuthEdit;
	/**
	 * 是否有效
	 */
	private Integer isValid;
	/**
	 * 是否删除
	 */
	private Integer isDelete;
}
