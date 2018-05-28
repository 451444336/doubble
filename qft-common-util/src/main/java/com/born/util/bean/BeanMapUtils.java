package com.born.util.bean;

import java.util.List;
import java.util.Map;

import org.springframework.cglib.beans.BeanMap;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
/**
 * 
 * @ClassName: BeanMapUtils
 * @Description: bean and map
 * @author: lijie
 * @date: 2018年5月27日 下午5:59:03
 */
public class BeanMapUtils {
	/**
	 * 
	* @Title: beanToMap  
	* @Description: bean 转map 
	* @param: @param bean
	* @param: @return
	* @return Map<String,Object>
	* @author lijie
	* @throws
	 */
	public static Map<String, Object> beanToMap(Object bean) {
		Map<String, Object> map = Maps.newHashMap();
		if (null != bean) {
			BeanMap beanMap = BeanMap.create(bean);
			for (Object key : beanMap.keySet()) {
				map.put(key.toString(), beanMap.get(key));
			}
		}
		return map;
	}
	/**
	 * 
	* @Title: mapToBean  
	* @Description: map 转 bean 
	* @param: @param map
	* @param: @param bean
	* @param: @return
	* @return T
	* @author lijie
	* @throws
	 */
	public static <T> T mapToBean(Map<String, Object> map, T bean) {
		BeanMap beanMap = BeanMap.create(bean);
		beanMap.putAll(map);
		return bean;
	}
	
	public static <T> List<Map<String, Object>> objectsToMaps(List<T> objList) {
		List<Map<String, Object>> list = Lists.newArrayList();
		if (objList != null && objList.size() > 0) {
			Map<String, Object> map = null;
			T bean = null;
			for (int i = 0, size = objList.size(); i < size; i++) {
				bean = objList.get(i);
				map = beanToMap(bean);
				list.add(map);
			}
		}
		return list;
	}

	public static <T> List<T> mapsToObjects(List<Map<String, Object>> maps, Class<T> clazz)
			throws InstantiationException, IllegalAccessException {
		List<T> list = Lists.newArrayList();
		if (maps != null && maps.size() > 0) {
			Map<String, Object> map = null;
			T bean = null;
			for (int i = 0, size = maps.size(); i < size; i++) {
				map = maps.get(i);
				bean = clazz.newInstance();
				mapToBean(map, bean);
				list.add(bean);
			}
		}
		return list;
	}
}
