package com.born.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;

import com.born.core.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description: 操作日志记录实体类
 * @author 黄伟
 * @date 2018年5月14日 下午4:21:19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "qft_oper_log_authority")
public class OperateLogAuthority extends BaseEntity<OperateLogAuthority> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1899122038992628178L;
	/**
     * 创建人ID
     */
	@Column(name = "creater_id")
    private Long createrId;
	/**
     * 创建时间
     */
	@Column(name = "create_time")
    private Date createTime;
	/**
	 * 公司ID
	 */
	@Column(name = "company_id")
	private Long companyId;
	/**
     * 权限类型：0 用户，1 角色，2 职位，3 菜单
     */
	@Column(name = "type")
    private Byte type;
	/**
     * 权限类型ID：用户ID/角色ID/职位ID/菜单ID
     */
	@Column(name = "type_id")
    private Long typeId;
	/**
	 * 操作
	 */
	@Column(name = "operate")
	private String operate;
	
	
    
}
