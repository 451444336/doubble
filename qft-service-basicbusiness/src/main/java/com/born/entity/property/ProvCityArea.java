package com.born.entity.property;

import javax.persistence.Column;
import javax.persistence.Table;

import com.born.core.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @description 区域实体类
 * @author 黄伟
 * @date 2018年5月31日 下午2:38:52
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name="qft_prov_city_area")
public class ProvCityArea extends BaseEntity<ProvCityArea> {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6090064703134718615L;

    /** 区域名称 */
    @Column(name="area_name")
    private String areaName;

    /** 上级区域编号 */
    @Column(name="parent_id")
    private Integer parentId;

    /** 区域编码 */
    @Column(name="area_code")
    private String areaCode;

    /** 区域级别 */
    @Column(name="area_level")
    private Boolean areaLevel;

    /** 区域类型名称 */
    @Column(name="type_name")
    private String typeName;
}