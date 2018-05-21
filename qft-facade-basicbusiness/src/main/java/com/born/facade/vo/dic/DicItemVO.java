package com.born.facade.vo.dic;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * 
 * @ClassName: DicItem
 * @Description: 字典类型值数据
 * @author 张永胜
 * @date 2018年5月17日 下午3:19:11
 * @version 1.0
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DicItemVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	private String id;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 子节点
	 */
	private Set<DicItemVO> itemlist;

}
