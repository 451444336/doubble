package com.born.core.exception;
/**
 * 
 * @ClassName: DataBaseException
 * @Description: 数据库异常 
 * @author: lijie
 * @date: 2018年5月27日 下午3:27:53
 */
public class DataBaseException extends BizException {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;
	
	public DataBaseException(String msg, String code, Object... args) {
		super(msg, code, args);
	}

	public DataBaseException(String msg, String code) {
		super(msg, code);
	}

	public DataBaseException() {
		super();
	}

	public DataBaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataBaseException(String message) {
		super(message);
	}

	public DataBaseException(Throwable cause) {
		super(cause);
	}

}
