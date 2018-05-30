package com.born.facade.dto.code;

import java.util.Date;

import com.born.core.base.BaseModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@ApiModel(value="编号设置请求参数")
public class CodeDTO extends BaseModel {
	private static final long serialVersionUID = 1L;
	/**
	 * 主键id
	 */
	private Long id;

	/**
	 * 修改时间
	 */
	private Date updateTime;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 创建人ID
	 */
	private Long createrId;

	/**
	 * 修改人ID
	 */
	private Long updaterId;
	/**
	 * 公司唯一ID
	 */
	private Long companyId;

	/**
	 * 店面ID
	 */
	private String storeId;
	
	/**
	 * 编号前缀
	 */
	private String prefix;
	
	/**
	 * 流水号位数
	 */
	private Long places;
	
	/**
	 * 排列方式
	 */
	private String arrangeType;
	
	/**
	 * 最后一次生成的
	 */
	private String newValue;
	
	/**
	 * 业务类型
	 */
	private String type;
}
