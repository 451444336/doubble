package com.born.core.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import com.born.core.entity.EntityClone;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 
* @ClassName: BaseCreateEntity 
* @Description: 创建公用实体 
* @author lijie 
* @date 2018年5月28日 下午3:47:11 
* 
* @param <T>
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseCoreEntity<T> extends EntityClone<T> {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	/**
	 * 主键id
	 */
	@Id
	private Long id;
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;

	/**
	 * 创建人ID
	 */
	@Column(name = "creater_id")
	private Long createrId;
}
