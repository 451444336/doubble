package com.born.util.result;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	 * 是否成功(true:成功 false:失败)
	 */
	@JsonIgnore
	private boolean success;
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

	public Result(boolean success) {
		this.success = success;
	}

	public Result(boolean success, String code, String message) {
		this.success = success;
		this.code = code;
		this.message = message;

	}
	public Result(boolean success, String code, String message,Object data) {
		this.success = success;
		this.code = code;
		this.message = message;
		this.data=data;
	}

	public Result(boolean success, String message, Object data) {
		this.success = success;
		this.message = message;
		this.data = data;
	}

	public Result(boolean success, Object data, long count) {
		super();
		this.success = success;
		this.data = data;
		this.count = count;
	}

	public Result(boolean success,String code, String message, Object data, long count) {
		super();
		this.success = success;
		this.code = code;
		this.message = message;
		this.data = data;
		this.count = count;
	}

	public Result() {
		super();
	}

	public <T> T getData(Class<T> tClass) {
		return tClass.cast(this.getData());
	}

}
