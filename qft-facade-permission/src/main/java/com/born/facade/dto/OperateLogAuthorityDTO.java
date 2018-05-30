package com.born.facade.dto;

import java.util.Date;

import com.born.core.page.PageBean;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description: 操作日志记录dto
 * @author 黄伟
 * @date 2018年5月14日 下午4:21:19
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OperateLogAuthorityDTO extends PageBean {


	/**
	 * 
	 */
	private static final long serialVersionUID = -2464067513481898899L;
	/**
	 * 主键ID
	 */
	private Long id;
	/**
     * 创建人ID
     */
    private Long createrId;
	 /**
     * 创建时间
     */
    private Date createTime;
    /**
	 * 公司ID
	 */
	private String companyId;
	/**
     * 权限类型：0 用户，1 角色，2 职位，3 菜单
     */
    private Byte type;
    /**
     * 权限类型ID：用户ID/角色ID/职位ID/菜单ID
     */
    private Long typeId;
	/**
	 * 操作
	 */
	private String operate;
	
}
