package com.born.core.base;

import java.io.Serializable;

import lombok.Data;
/**
 * 
 * @ClassName: DataBaseParameters
 * @Description: 数据库操作参数
 * @author: lijie
 * @date: 2018年5月27日 下午4:10:35
 * @param <E>
 */
@Data
public class DataBaseParameters<E> implements Serializable {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 数据库操作mapper
	 */
	private BaseMapper<E> mapper;
	/**
	 * 数据操作实体
	 */
	private Class<E> entityClass;
}
