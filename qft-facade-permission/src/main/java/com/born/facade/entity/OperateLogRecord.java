package com.born.facade.entity;


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
@Table(name = "tb_operate_log_record")
public class OperateLogRecord extends BaseEntity<OperateLogRecord> {

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
	private String companyId;
	/**
     * 角色ID
     */
	@Column(name = "role_id")
    private Long roleId;
	/**
	 * 操作
	 */
	@Column(name = "operate")
	private String operate;
	
	
    
}
