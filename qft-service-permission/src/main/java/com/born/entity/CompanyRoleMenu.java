package com.born.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.born.core.entity.EntityClone;

import lombok.Data;
/**
 * 
* @ClassName: CompanyRoleMenu 
* @Description: 角色菜单 
* @author lijie 
* @date 2018年5月9日 下午4:48:16 
*
 */
@Data
@Table(name="qft_company_role_menu")
public class CompanyRoleMenu extends EntityClone<CompanyRoleMenu> {


	/**
	 * 
	 */
	private static final long serialVersionUID = 4358229381631467860L;
	
	@Id
	private Long id;
	/**
	 * 菜单ID
	 */
	@Column(name="menu_id")
	private Long menuId;
	/**
	 * 角色ID
	 */
	@Column(name="role_id")
	private Long roleId;
	/**
	 * 创建时间
	 */
	@Column(name="create_time")
	private Date createTime;
	/**
	 * 创建人ID
	 */
	@Column(name="creater_id")
	private Long createrId;
}
