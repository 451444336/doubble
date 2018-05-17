package com.born.facade.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.born.core.entity.EntityClone;

import lombok.Data;
/**
 * 
* @ClassName: MenuAuthority 
* @Description: 菜单权限 
* @author lijie 
* @date 2018年4月28日 下午3:48:50 
*
 */
@Data
@Table(name="qft_menu_authority")
public class MenuAuthority extends EntityClone<MenuAuthority> {
	
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = -1522985762545608062L;
	/**
	 * 主键ID
	 */
	@Id
	private Long id;
	/**
	 * 权限ID
	 */
	@Column(name = "authority_id")
	private Long authorityId;
	/**
	 * 菜单ID
	 */
	@Column(name = "menu_id")
	private Long menuId;
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;
	/**
	 * 创建人ID
	 */
	@Column(name = "creater_id")
	private Long createrId;
	/**
	 * 公司ID
	 */
	@Column(name = "company_id")
	private String companyId;
}
