package com.born.entity.dic;

import javax.persistence.Column;
import javax.persistence.Table;

import com.born.core.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(name="qft_dicitem")
public class DicItem extends BaseEntity<DicItem> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 字典类型code
	 */
	@Column(name="dictype_code")
	private String dictypeCode;
	/**
	 * 字典code
	 */
	@Column(name="dtcode")
	private String dtcode;
	/**
	 * 字典名称
	 */
	@Column(name="diname")
	private String diname;
	/**
	 * 字典vlaue
	 */
	@Column(name="divalue")
	private String divalue;
	/**
	 * 是否默认
	 */
	@Column(name = "is_default")
	private Byte isDefault;
	/**
	 * 排序值
	 */
	@Column(name="order_num")
	private Integer orderNum;
	/**
	 * 公司字典（1表示是，2表示不是）
	 */
	@Column(name="ispub_dic")
	private Integer ispubDic;
	/**
	 * 父级ID
	 */
	@Column(name="parent_id")
	private Long parentId;
	/**
	 * 字典等级
	 */
	@Column(name="dic_rank")
	private Integer dicRank;
	/**
	 * 
	 */
	@Column(name = "base_id")
	private Long baseId;
	/**
	 * 公司唯一ID
	 */
	@Column(name = "company_id")
	private Long companyId;
	/**
	 * 是否删除：0、否  1、是
	 */
	@Column(name = "is_delete")
	private Byte isDelete;
}
