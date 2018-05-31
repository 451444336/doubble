package com.born.util.result;

import org.apache.commons.lang3.StringUtils;

/**
 * 
* @ClassName: ResultUtil  
* @Description: 统一返回结果 
* @author lijie
* @date 2018年4月25日  
*
 */
public final class ResultUtil {

    /**
     * 
    * @Title: getResult  
    * @Description: 得到返回结果 
    * @param @param respCode
    * @param @param data
    * @param @param count
    * @param @return    参数  
    * @return Result    返回类型  
    * @author lijie
    * @throws
     */
    public static Result getResult(RespCode respCode, Object data, long count) {
        Result result = new Result();
        result.setData(data);
        result.setMessage(respCode.getMsg());
        result.setCode(respCode.getCode());
        result.setCount(count);
        return result;
    }

   /**
    * 
   * @Title: getResult  
   * @Description: 
   * @param @param respCode
   * @param @param data
   * @param @return    参数  
   * @return Result    返回类型  
   * @author lijie
   * @throws
    */
    public static Result getResult(RespCode respCode, Object data) {
        Result result = new Result();
        result.setData(data);
        result.setMessage(respCode.getMsg());
        result.setCode(respCode.getCode());
        return result;
    }
    
    /**
     * 
    * @Title: getResult 
    * @Description: 
    * @param respCode
    * @param data
    * @return 
    * @author 张永胜
    * @return Result
    * @date 2018年5月9日 下午3:55:37
     */
    public static Result getResult(RespCode respCode, String message) {
        Result result = new Result();
        result.setMessage(message);
        result.setCode(respCode.getCode());
        return result;
    }

   /**
    * 
   * @Title: getResult  
   * @Description:  
   * @param @param respCode
   * @param @return    参数  
   * @return Result    返回类型  
   * @author lijie
   * @throws
    */
    public static Result getResult(RespCode respCode) {
        Result result = new Result();
        result.setMessage(respCode.getMsg());
        result.setCode(respCode.getCode());
        return result;
    }

    /**
     * @throws
     * @Title: setResult
     * @Description: 设置返回结果
     * @param: @param result
     * @param: @param respCode
     * @param: @return
     * @return: Result
     * @author：李杰
     */
    public static Result setResult(Result result, RespCode respCode) {
        result.setMessage(respCode.getMsg());
        result.setCode(respCode.getCode());
        return result;
    }
    /**
     * 
    * @Title: setResult 
    * @Description: 
    * @param @param result
    * @param @param respCode
    * @param @return    设定文件 
    * @return Result    返回类型 
    * @author lijie
    * @throws
     */
	public static Result setResult(Result result, RespCode respCode, String message) {
		result.setMessage(StringUtils.isBlank(message) ? respCode.getMsg() : message);
		result.setCode(respCode.getCode());
		return result;
	}
    /**
     * 
    * @Title: setResult  
    * @Description:  
    * @param: @param result
    * @param: @param respCode
    * @param: @param data
    * @param: @return
    * @return Result
    * @author lijie
    * @throws
     */
	public static Result setResult(Result result, RespCode respCode, Object data) {
		result.setMessage(respCode.getMsg());
		result.setCode(respCode.getCode());
		result.setData(data);
		return result;
	}

    /**
     * @throws
     * @Title: setResult
     * @Description:设置返回结果
     * @param: @param result
     * @param: @param flag
     * @param: @param message
     * @param: @return
     * @return: Result
     * @author：李杰
     */
    public static Result setResult(Result result, boolean flag, String message) {
        if (result == null) {
            result = new Result();
        }
        setResultValue(result, flag, message);
        return result;
    }

    /**
     * @throws
     * @Title: setResult
     * @Description: 设置返回结果
     * @param: @param flag
     * @param: @param message
     * @param: @return
     * @return: Result
     * @author：李杰
     */
    public static Result setResult(boolean flag, String message) {
        Result result = new Result();
        setResultValue(result, flag, message);
        return result;
    }

    /**
     * @throws
     * @Title: setResultValue
     * @Description: 赋值操作
     * @param: @param result
     * @param: @param flag
     * @param: @param message
     * @return: void
     * @author：李杰
     */
    private static void setResultValue(Result result, boolean flag, String message) {
        result.setCode(flag ? RespCode.Code.SUCCESS.getCode() : RespCode.Code.FAIL.getCode());
        result.setMessage(message);
    }
    /**
     * 
    * @Title: success 
    * @Description: 成功返回数据 
    * @param @param data
    * @param @return    设定文件 
    * @return Result    返回类型 
    * @author lijie
    * @throws
     */
    public static Result success(Object data){
    	
    	return getResult(RespCode.Code.SUCCESS, data);
    }
    /**
     * 
    * @Title: success 
    * @Description: 成功返回 
    * @param @return    设定文件 
    * @return Result    返回类型 
    * @author lijie
    * @throws
     */
	public static Result success() {

		return getResult(RespCode.Code.SUCCESS);
	}
    /**
     * 
    * @Title: success 
    * @Description: 成功返回数据以及条数
    * @param @param data
    * @param @param count
    * @param @return    设定文件 
    * @return Result    返回类型 
    * @author lijie
    * @throws
     */
	public static Result success(Object data, long count) {

		return getResult(RespCode.Code.SUCCESS, data, count);
	}
    /**
     * 
    * @Title: success 
    * @Description: 成功返回数据 
    * @param @param result
    * @param @param data
    * @param @return    设定文件 
    * @return Result    返回类型 
    * @author lijie
    * @throws
     */
	public static Result success(Result result, Object data) {

		return setResult(result, RespCode.Code.SUCCESS, data);
	}
	/**
	 * 
	* @Title: fail 
	* @Description: 失败
	* @param @return    设定文件 
	* @return Result    返回类型 
	* @author lijie
	* @throws
	 */
	public static Result fail(){
		
		return ResultUtil.getResult(RespCode.Code.FAIL);
	}
	/**
	 * 
	* @Title: fail 
	* @Description: 失败返回 
	* @param @param code
	* @param @return    设定文件 
	* @return Result    返回类型 
	* @author lijie
	* @throws
	 */
	public static Result fail(RespCode code){
		
		return ResultUtil.getResult(code);
	}
	/**
	 * 
	* @Title: serverError 
	* @Description: 服务异常
	* @param @param code
	* @param @return    设定文件 
	* @return Result    返回类型 
	* @author lijie
	* @throws
	 */
	public static Result serverError(RespCode code){
		
		return ResultUtil.getResult(code);
	}
	/**
	 * 
	* @Title: serverError 
	* @Description: 服务异常 
	* @param @return    设定文件 
	* @return Result    返回类型 
	* @author lijie
	* @throws
	 */
	public static Result serverError(){
		
		return ResultUtil.getResult(RespCode.Code.INTERNAL_SERVER_ERROR);
	}
	/**
	 * 
	* @Title: fail 
	* @Description: 失败返回 
	* @param @param code
	* @param @param message
	* @param @return    设定文件 
	* @return Result    返回类型 
	* @author lijie
	* @throws
	 */
	public static Result fail(RespCode code, String message) {

		return ResultUtil.getResult(code, message);
	}
	/**
	 * 
	* @Title: requestDataError 
	* @Description: 请求参数异常 
	* @param @param message
	* @param @return    设定文件 
	* @return Result    返回类型 
	* @author lijie
	* @throws
	 */
	public static Result requestDataError(String message) {

		return ResultUtil.getResult(RespCode.Code.REQUEST_DATA_ERROR, message);
	}
}
