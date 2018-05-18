package com.born.facade.dto;

import java.util.Date;

import com.born.core.page.PageBean;

import lombok.Data;

/**
 * @Description: 角色dto
 * @author wangxy
 * @date 2018年4月27日 下午2:15:21
 */
@Data
public class CompanyRoleDTO extends PageBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6930080905121410594L;

	/**
	 * 主键ID
	 */
	private Long id;
	 /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 创建人ID
     */
    private Long createrId;
    /**
     * 修改人ID
     */
    private Long updaterId;
    /**
     * 删除状态
     */
    private Byte IsDelete;
	/**
	 * 角色名称
	 */
	private String roleName;
	/**
	 * 是否能权限编辑
	 */
	private Byte isAuthEdit;
	/**
	 * 是否有效
	 */
	private Byte isValid;
	/**
	 * 公司ID
	 */
	private String companyId;
}
