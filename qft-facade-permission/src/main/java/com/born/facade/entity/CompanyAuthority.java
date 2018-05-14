package com.born.facade.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.born.core.entity.EntityClone;

import lombok.Data;
/**
 * 
* @ClassName: CompanyAuthority 
* @Description: 公司权限 
* @author lijie 
* @date 2018年5月3日 下午2:32:02 
*
 */
@Data
@Table(name="qft_company_authority")
public class CompanyAuthority extends EntityClone<CompanyAuthority> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7051556679204423855L;
	/**
	 * 主键id
	 */
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
	 * 公司ID
	 */
	@Column(name="company_id")
	private String companyId;
	/**
	 * 权限基础数据ID
	 */
	@Column(name="authority_base_id")
	private Long authorityBaseId;
}
