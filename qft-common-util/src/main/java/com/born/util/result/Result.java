package com.born.util.result;

import java.io.Serializable;

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

	public <T> T getData(Class<T> tClass) {
		return tClass.cast(this.getData());
	}

}
