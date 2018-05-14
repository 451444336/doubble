package com.born.facade.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
/**
 * 
* @ClassName: RoleVO 
* @Description: 角色返回实体
* @author lijie 
* @date 2018年4月28日 下午1:42:41 
*
 */
@Data
public class RoleVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5969598674538550978L;
	/**
	 * 主键ID
	 */
	private Long id;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 是否有效：0、无效 1、有效
     */
    private Byte isValid;
    /**
     * 是否能权限编辑：0、不能编辑 1、可以编辑
     */
    private Byte isAuthEdit;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
