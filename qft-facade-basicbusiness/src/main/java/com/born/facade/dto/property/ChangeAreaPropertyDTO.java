package com.born.facade.dto.property;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

import com.born.core.page.PageBean;

import lombok.Data;

/**
 * 
 * @description 楼盘地址dto
 * @author 黄伟
 * @date 2018年5月31日 下午2:58:19
 */
@Data
public class ChangeAreaPropertyDTO extends PageBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 90697457189598516L;

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
	 * 公司ID
	 */
	private Long companyId;
	/**
	 * 区域ID
	 */
	private Long areaId;
	/**
	 * 楼盘地址
	 */
	@NotBlank(message = "楼盘地址不能为空")
	private String propertyAdrr;
}
