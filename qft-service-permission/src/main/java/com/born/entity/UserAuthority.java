package com.born.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.born.core.entity.EntityClone;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 
* @ClassName: StaffAuthority 
* @Description: 用户权限实体
* @author lijie 
* @date 2018年5月9日 下午5:42:39 
*
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name="qft_user_authority")
public class UserAuthority extends EntityClone<UserAuthority> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	/**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
    /**
     * 创建人ID
     */
    @Column(name = "creater_id")
    private Long createrId;
	/**
	 * 用户ID
	 */
	@Column(name="user_id")
	private Long userId;
	/**
	 * 权限ID
	 */
	@Column(name="authority_id")
	private Long authorityId;
}
