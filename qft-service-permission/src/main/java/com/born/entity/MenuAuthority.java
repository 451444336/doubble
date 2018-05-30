package com.born.entity;

import javax.persistence.Column;
import javax.persistence.Table;

import com.born.core.base.BaseCoreEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 
* @ClassName: MenuAuthority 
* @Description: 菜单权限 
* @author lijie 
* @date 2018年4月28日 下午3:48:50 
*
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name="qft_menu_authority")
public class MenuAuthority extends BaseCoreEntity<MenuAuthority> {
	
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = -1522985762545608062L;
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
}
