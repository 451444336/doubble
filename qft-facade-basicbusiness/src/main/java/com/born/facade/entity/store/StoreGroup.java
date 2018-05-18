package com.born.facade.entity.store;

import javax.persistence.Column;
import javax.persistence.Table;

import com.born.core.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description: 店面分组实体类
 * @author 黄伟
 * @date 2018年5月15日 下午1:37:21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "qft_store_grouping")
public class StoreGroup extends BaseEntity<StoreGroup> {


	/**
	 * 
	 */
	private static final long serialVersionUID = 4256272745385051231L;
	/**
	 * 分组名称
	 */
	@Column(name = "name")
	private String name;
	/**
	 * 店面ID
	 */
	@Column(name = "store_id")
	private Long storeId;
	/**
	 * 公司ID
	 */
	@Column(name = "company_id")
	private String companyId;
	/**
     * 删除状态
     */
	@Column(name = "is_delete")
    private Integer IsDelete;
	/**
	 * 状态
	 */
	@Column(name = "statistics")
	private String statistics;
	
}
