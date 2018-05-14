package com.born.facade.dto.permission;

import java.io.Serializable;

import lombok.Data;
/**
 * 
* @ClassName: QueryMenuAuthDTO 
* @Description: 查询菜单权限传输实体
* @author lijie 
* @date 2018年5月10日 下午2:35:39 
*
 */
@Data
public class QueryMenuAuthDTO implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 8230554199787153318L;
	/**
	 * 职位ID
	 */
	private Long positionId;
	/**
	 * 操作类型：1、职位 2、个人
	 */
	private Byte operType;
	/**
	 * 用户ID
	 */
	private Long userId;
}
