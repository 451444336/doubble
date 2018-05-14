package com.born.util.session;

import javax.servlet.http.HttpServletRequest;

import com.born.util.constants.SysConstants;

/**
 * 
* @ClassName: SessionUtil 
* @Description: 会话工具类 
* @author lijie 
* @date 2018年5月9日 下午3:07:08 
*
 */
public class SessionUtil {

	/**
	 * 
	* @Title: getCorUrl 
	* @Description: 获取公司前缀
	* @param @param request
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author lijie
	* @throws
	 */
	public static String getCorUrl(HttpServletRequest request){
		Object url = request.getSession().getAttribute(SysConstants.COR_URL);
		if(null != url){
			return url.toString();
		}
		return "admin";
	}
}
