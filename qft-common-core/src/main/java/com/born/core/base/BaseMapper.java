
package com.born.core.base;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 
* @ClassName: BaseMapper  
* @Description: 数据库通用mapper  
* @author lijie
* @date 2018年4月25日  
*  
* @param <T>
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
  
