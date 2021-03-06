package com.born.facade.vo.property;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 
 * @description 区域vo
 * @author 黄伟
 * @date 2018年5月31日 下午3:02:44
 */
@Data
public class ProvCityAreaVO {

	/** 区域主键ID */
	private Long id;
	/** 区域名称 */
    private String areaName;
    /** 楼盘地址集合 */
	List<AreaPropertyVO> list = new ArrayList<>();
}
