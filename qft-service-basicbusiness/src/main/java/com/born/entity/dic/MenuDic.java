package com.born.entity.dic;

import javax.persistence.Column;
import javax.persistence.Table;

import com.born.core.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 
* @ClassName: MenuDic 
* @Description: 菜单字典
* @author lijie 
* @date 2018年5月30日 下午6:10:09 
*
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "qft_menu_dic")
public class MenuDic extends BaseEntity<MenuDic> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 菜单ID
	 */
	@Column(name = "menu_id")
	private Long menuId;
	/**
	 * 是否删除
	 */
	@Column(name = "is_delete")
	private Byte isDelete;
	/**
	 * 公司ID
	 */
	@Column(name = "company_id")
	private Long companyId;
}
