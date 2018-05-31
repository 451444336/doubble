package com.born.util.result;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.born.util.bean.BeanMapUtils;

import lombok.Data;

/**
 * 
* @ClassName: Result  
* @Description: 通用消息返回
* @author lijie
* @date 2018年4月25日  
*
 */
@Data
public class Result implements Serializable	{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2540464186250760929L;
	/**
	 * 异常码
	 */
	private String code;
	/**
	 * 返回消息
	 */
	private String message;
	/**
	 * 返回数据
	 */
	private Object data;
	/**
	 * 总条数
	 */
	private long count;

	public Result(String code, String message) {
		this.code = code;
		this.message = message;

	}

	public Result(String code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public Result(String message, Object data) {
		this.message = message;
		this.data = data;
	}

	public Result(String code, String message, Object data, long count) {
		this.code = code;
		this.message = message;
		this.data = data;
		this.count = count;
	}

	public Result() {
		super();
	}

	@SuppressWarnings("unchecked")
	public <T> T getData(Class<T> tClass) {
		if (this.getData() instanceof Map) {
			try {
				if(tClass == Map.class){
					return (T) this.getData();
				}
				return BeanMapUtils.mapToBean(((Map<String, Object>) this.getData()), tClass);
			} catch (Exception e) {
				return null;
			}
		} else if (this.getData() instanceof List) {
			List<?> list = (List<?>) this.getData();
			if (!list.isEmpty() && list.get(0) instanceof Map) {
				try {
					return (T) BeanMapUtils.mapsToObjects((List<Map<String, Object>>) this.getData(), tClass);
				} catch (Exception e) {
					return null;
				}
			}
		}
		return tClass.cast(this.getData());
	}

}
