package com.born.facade.dto.store;

import java.util.Date;


import com.born.core.page.PageBean;

import lombok.Data;

/**
 * @Description: 店面分组dto
 * @author 黄伟
 * @date 2018年5月14日 下午6:25:15
 */
@Data
public class StoreGroupDTO extends PageBean {


	/**
	 * 
	 */
	private static final long serialVersionUID = 5631265107505418364L;
	/**
	 * 主键ID
	 */
	private Long id;
	/**
     * 分组名称
     */
    private String name;
    /**
     * 店面ID
     */
    private Long storeId;
	/**
	 * 公司ID
	 */
	private Long companyId;
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
    private Integer IsDelete;
    /**
	 * 状态
	 */
	private String statistics;
}
