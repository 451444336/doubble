package com.born.facade.entity.dic;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name="qft_dicitem")
public class DicItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ID主键
	 */
	private String id;

	/**
	 * 创建时间
	 */
	private String createtime;

	/**
	 * 公司唯一ID
	 */
	private String companyid;

	/**
	 * 字典类型code
	 */
	@Column(name="dictype_code")
	private String dictypeCode;

	/**
	 * 字典code
	 */
	private String dtcode;

	/**
	 * 字典名称
	 */
	private String diname;

	/**
	 * 字典vlaue
	 */
	private String divalue;

	/**
	 * 是否默认
	 */
	private String isdefault;

	/**
	 * 排序值
	 */
	private String ordernum;

	/**
	 * 创建人
	 */
	private String creater;

	/**
	 * 公司字典（1表示是，2表示不是）
	 */
	@Column(name="ispub_dic")
	private String ispubDic;

	/**
	 * 父级ID
	 */
	private String parentid;

	/**
	 * 字典等级
	 */
	@Column(name="dicRank")
	private String dicRank;
}
