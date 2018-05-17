package com.born.facade.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.born.core.entity.EntityClone;

import lombok.Data;
/**
 * 
* @ClassName: RoleRelation 
* @Description: 角色关系表 
* @author lijie 
* @date 2018年5月17日 上午9:13:29 
*
 */
@Data
@Table(name="qft_role_relation")
public class RoleRelation extends EntityClone<RoleRelation> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 */
	@Id
	private Long id;
	/**
	 * 公司ID
	 */
	@Column(name="company_id")
	private String companyId;
	/**
	 * 角色ID
	 */
	@Column(name="role_id")
	private Long roleId;
	/**
	 * 创建时间
	 */
	@Column(name="create_time")
	private Date createTime;
	/**
	 * 创建人ID
	 */
	@Column(name="creater_id")
	private Long createrId;
}
