package com.born.util.bean;

import java.util.List;
import java.util.Map;

import org.springframework.cglib.beans.BeanMap;
import org.springframework.util.CollectionUtils;

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
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
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
	public static <T> T mapToBean(Map<String, Object> map, Class<T> clazz) throws InstantiationException, IllegalAccessException {
		T result = clazz.newInstance();
		BeanMap beanMap = BeanMap.create(result);
		beanMap.putAll(map);
		return result;
	}
	/**
	 * 
	* @Title: objectsToMaps 
	* @Description: list to map
	* @param @param objList
	* @param @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @author lijie
	* @throws
	 */
	public static <T> List<Map<String, Object>> objectsToMaps(List<T> objList) {
		List<Map<String, Object>> result = Lists.newArrayList();
		if (CollectionUtils.isEmpty(objList)) {
			return result;
		}
		for (T t : objList) {
			result.add(beanToMap(t));
		}
		return result;
	}
	/**
	 * 
	* @Title: mapsToObjects 
	* @Description: map to list
	* @param @param maps
	* @param @param clazz
	* @param @return
	* @param @throws InstantiationException
	* @param @throws IllegalAccessException    设定文件 
	* @return List<T>    返回类型 
	* @author lijie
	* @throws
	 */
	public static <T> List<T> mapsToObjects(List<Map<String, Object>> maps, Class<T> clazz)
			throws InstantiationException, IllegalAccessException {
		List<T> result = Lists.newArrayList();
		if (CollectionUtils.isEmpty(maps)) {
			return result;
		}
		for (Map<String, Object> map : maps) {
			result.add(mapToBean(map, clazz));
		}
		return result;
	}
}
