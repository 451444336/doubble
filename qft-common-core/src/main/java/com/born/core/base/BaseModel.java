package com.born.core.base;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
/**
 * 
 * @ClassName: BaseModel
 * @Description: 通用service查询model
 * @author: lijie
 * @date: 2018年5月26日 下午4:27:25
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class BaseModel extends BaseValidate<BaseModel> {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
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
	 * id数组
	 */
	private List<Long> ids;

}
