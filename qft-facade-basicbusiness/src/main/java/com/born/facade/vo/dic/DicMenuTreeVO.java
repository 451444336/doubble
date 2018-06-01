package com.born.facade.vo.dic;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
/**
 * 
* @ClassName: DicMenuTreeVO 
* @Description: 字典菜菜单树返回实体 
* @author lijie 
* @date 2018年5月31日 下午5:49:19 
*
 */
@Data
public class DicMenuTreeVO implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 字典ID
	 */
	private String id;
	/**
	 * 上级字典ID
	 */
	@JsonProperty("pId")
	private String pId;
	/**
	 * 菜单名称
	 */
	private String name;
	/**
	 * 字典类型编码
	 */
	private String typeCode;
	/**
	 * 类型：0、菜单 1、字典类型 2、字典
	 */
	private Byte type;
	/**
	 * 跳转URL
	 */
	private String url;
	/**
	 * 前段页面需要
	 */
	private String target = "roleRight";
	/**
	 * 图标
	 */
	private String icon;
}
