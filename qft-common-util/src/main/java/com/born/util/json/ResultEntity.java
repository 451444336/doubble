package com.born.util.json;

import java.io.Serializable;

/**
 * 
 * @ClassName: ResultEntity
 * @Description: 统一API数据封装实体类
 * @author 张永胜
 * @date 2018年5月8日 下午3:10:54
 * @version 1.0
 * @param <T>
 */
public class ResultEntity<T> implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 返回代码
	 */
	private int code;

	/**
	 * 状态消息
	 */
	private String message;

	/**
	 * 返回结果
	 */
	private T data;

	public static <T> ResultEntity<T> getInstance() {
		return new ResultEntity<T>();
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = Cast.cast(data, false);
	}

	@Override
	public String toString() {
		return "ResultEntity [code=" + code + ", message=" + message + ", data=" + data + "]";
	}

}
