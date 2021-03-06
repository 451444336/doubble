/**
 *
 */
package com.born.core.base;

import java.util.Date;

import javax.persistence.Column;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @ClassName: BaseEntity
 * @Description:实体base类
 * @author chen
 * @date 2018年4月27日 上午11:08:10
 *  ces 
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseEntity<T> extends BaseCoreEntity<T> {
	/**
	 *
	 */
	private static final long serialVersionUID = -5300113985007593228L;
	/**
	 * 修改时间
	 */
	@Column(name = "update_time")
	private Date updateTime;
	/**
	 * 修改人ID
	 */
	@Column(name = "updater_id")
	private Long updaterId;

}