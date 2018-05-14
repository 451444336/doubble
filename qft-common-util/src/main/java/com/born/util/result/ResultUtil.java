package com.born.util.result;

import org.apache.commons.lang3.StringUtils;

/**
 * 
* @ClassName: ResultUtil  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author lijie
* @date 2018年4月25日  
*
 */
public final class ResultUtil {

    /**
     * 
    * @Title: getResult  
    * @Description: TODO(这里用一句话描述这个方法的作用)  
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
        result.setSuccess(RespCode.Code.SUCCESS.getCode().equals(respCode.getCode()));
        return result;
    }

   /**
    * 
   * @Title: getResult  
   * @Description: TODO(这里用一句话描述这个方法的作用)  
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
        result.setSuccess(RespCode.Code.SUCCESS.getCode().equals(respCode.getCode()));
        return result;
    }
    
    /**
     * 
    * @Title: getResult 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param respCode
    * @param data
    * @return 
    * @author 张永胜
    * @return Result
    * @date 2018年5月9日 下午3:55:37
     */
    public static Result getResult(RespCode respCode, String message) {
        Result result = new Result();
        result.setData(null);
        result.setMessage(message);
        result.setCode(respCode.getCode());
        result.setSuccess(RespCode.Code.SUCCESS.getCode().equals(respCode.getCode()));
        return result;
    }

   /**
    * 
   * @Title: getResult  
   * @Description: TODO(这里用一句话描述这个方法的作用)  
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
        result.setSuccess(RespCode.Code.SUCCESS.getCode().equals(respCode.getCode()));
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
        result.setSuccess(RespCode.Code.SUCCESS.getCode().equals(respCode.getCode()));
        return result;
    }
    /**
     * 
    * @Title: setResult 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
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
		result.setSuccess(RespCode.Code.SUCCESS.getCode().equals(respCode.getCode()));
		return result;
	}
    /**
     * 
    * @Title: setResult  
    * @Description: TODO 
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
		result.setSuccess(RespCode.Code.SUCCESS.getCode().equals(respCode.getCode()));
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
        result.setSuccess(flag);
        result.setMessage(message);
    }
}