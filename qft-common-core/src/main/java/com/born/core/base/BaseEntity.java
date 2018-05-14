/**
 *
 */
package com.born.core.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import com.born.core.entity.EntityClone;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @ClassName: BaseEntity
 * @Description:实体base类
 * @author lijie
 * @date 2018年4月27日 上午11:08:10
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseEntity<T> extends EntityClone<T> {
	/**
	 *
	 */
	private static final long serialVersionUID = -5300113985007593228L;

	/**
	 * 主键id
	 */
	@Id
	private Long id;

	/**
	 * 修改时间
	 */
	@Column(name = "update_time")
	private Date updateTime;

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

	/**
	 * 修改人ID
	 */
	@Column(name = "updater_id")
	private Long updaterId;

}