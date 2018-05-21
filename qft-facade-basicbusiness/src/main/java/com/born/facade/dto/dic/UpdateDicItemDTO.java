package com.born.facade.dto.dic;

import java.io.Serializable;

import lombok.Data;

/**
 * 之后可能会扩展
 * 
 * @ClassName: UpdateDicItemDTO
 * @Description: 更新字典是DTO
 * @author 张永胜
 * @date 2018年5月18日 下午3:34:41
 * @version 1.0
 */
@Data
public class UpdateDicItemDTO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	/**
	 * 字典ID
	 */
	private String id;

	/**
	 * 字典名称
	 */
	private String name;
}
