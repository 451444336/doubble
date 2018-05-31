package com.born.entity.property;

import javax.persistence.Column;
import javax.persistence.Table;

import com.born.core.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @description 楼盘地址（物业地址）实体类
 * @author 黄伟
 * @date 2018年5月31日 下午2:39:18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name="qft_area_property")
public class AreaProperty extends BaseEntity<AreaProperty> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8159662604906450284L;

    /** 公司ID */
    @Column(name="company_id")
    private Long companyId;
    
	/** 区县ID */
    @Column(name="area_id")
    private Long areaId;

    /** 物业地址 */
    @Column(name="property_adrr")
    private String propertyAdrr;

    /** 是否删除：0正常，1删除 */
    @Column(name = "is_delete")
    private byte isDelete;
    
}