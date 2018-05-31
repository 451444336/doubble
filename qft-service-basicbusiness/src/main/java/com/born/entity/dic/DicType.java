package com.born.entity.dic;

import javax.persistence.Column;
import javax.persistence.Table;

import com.born.core.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 
* @ClassName: DicType 
* @Description: 字典类型表
* @author lijie 
* @date 2018年5月30日 下午6:12:53 
*
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "qft_dictype")
public class DicType extends BaseEntity<DicType>{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 字典菜单主键ID
	 */
	@Column(name = "menu_dic_id")
	private Long menuDicId;
	/**
	 * 字典名称
	 */
	@Column(name = "dtname")
	private String dtname;
	/**
	 * 字典code
	 */
	@Column(name = "dtcode")
	private String dtcode;
	/**
	 * 是否公用
	 */
	@Column(name = "is_pubdic")
	private Byte isPubdic;
	/**
	 * 拥有几级字典
	 */
	@Column(name = "dic_rank")
	private Integer dicRank;
	/**
	 * 是否删除
	 */
	@Column(name = "is_delete")
	private Byte isDelete;
}
