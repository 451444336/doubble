package com.born.facade.vo.dic;

import lombok.Data;

/**
 * 
 * @ClassName: DicType
 * @Description: 字典类型
 * @author 张永胜
 * @date 2018年5月17日 下午8:01:43
 * @version 1.0
 */
@Data
public class DicTypeVO {
	/**
	 * ID
	 */
	private String id;

	/**
	 * 父级ID
	 */
	private String pId;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 几级字典
	 */
	private String rank;
}
