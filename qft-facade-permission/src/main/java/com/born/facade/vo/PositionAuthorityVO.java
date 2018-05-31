package com.born.facade.vo;

import java.util.List;

import com.born.core.base.BaseVO;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
* @ClassName: PositionAuthorityVO 
* @Description: 编辑职位返回VO
* @author 明成 
* @date 2018年5月4日 下午4:36:02 
* @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PositionAuthorityVO extends BaseVO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 职位名称
	 */
	private String name;
	
	/**
	 * 职位权限
	 */
	private List<Long> authoriyIds;
}
