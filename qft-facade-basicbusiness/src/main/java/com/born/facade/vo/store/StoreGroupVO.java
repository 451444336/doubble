package com.born.facade.vo.store;

import java.util.Date;

import lombok.Data;

/**
 * @Description: 店面分组vo
 * @author 黄伟
 * @date 2018年5月15日 上午10:31:20
 */
@Data
public class StoreGroupVO {
	
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
	private String companyId;
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
