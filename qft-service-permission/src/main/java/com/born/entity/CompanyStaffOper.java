package com.born.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;

import com.born.core.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 
* @ClassName: CompanyStaffOper 
* @Description:用户操作记录
* @author lijie 
* @date 2018年5月7日 下午2:06:56 
*
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name="qft_user_oper")
public class CompanyStaffOper extends BaseEntity<CompanyStaffOper> {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6278041386752199708L;
	/**
	 * 用户ID
	 */
	@Column(name="user_id")
	private Long userId;
	/**
	 * 上次登录IP
	 */
	@Column(name="last_ip")
	private String lastIp;
	/**
	 * 上次登录时间
	 */
	@Column(name="last_time")
	private Date lastTime;
	
}
