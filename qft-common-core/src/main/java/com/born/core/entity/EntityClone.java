package com.born.core.entity;

import java.io.Serializable;

/**
 * 
* @ClassName: EntityClone 
* @Description: 实体克隆 
* @author lijie 
* @date 2018年5月3日 下午6:33:35 
* 
* @param <T>
 */
public abstract class EntityClone<T> implements Cloneable, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8195069321710129849L;

	@Override
	@SuppressWarnings("unchecked")
	public T clone() throws CloneNotSupportedException {

		return (T) super.clone();
	}
}
