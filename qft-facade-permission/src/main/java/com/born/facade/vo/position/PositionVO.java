package com.born.facade.vo.position;

import java.util.Date;

import lombok.Data;

/**
 * 
* @ClassName: PositionVO 
* @Description: 编辑职位返回VO
* @author 明成 
* @date 2018年5月4日 下午4:36:02 
* @version 1.0
 */
@Data
public class PositionVO {
	
	/**
	 * 主键id
	 */
	private Long id;

	/**
	 * 修改时间
	 */
	private Date updateTime;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 创建人ID
	 */
	private Long createrId;

	/**
	 * 修改人ID
	 */
	private Long updaterId;
	private String name;//职位名称
	
	private Long deptId;//部门ID
}
