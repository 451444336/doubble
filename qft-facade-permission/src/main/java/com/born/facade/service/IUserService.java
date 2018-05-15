package com.born.facade.service;

import com.born.facade.dto.StaffOperDTO;
import com.born.facade.dto.user.LoginDTO;
import com.born.facade.dto.user.UserDTO;
import com.born.util.result.Result;

/**
 * 
* @ClassName: IWebUserService 
* @Description: IUserService 
* @author lijie 
* @date 2018年5月2日 下午2:17:32 
*
 */
public interface IUserService {
	
	/**
	 * 
	* @Title: updateUserPass 
	* @Description: 后端更新用户密码
	* @param id 用户表ID
	* @param password 用户密码
	* @param operatingId 当前操作人ID
	* @return 
	* @author 张永胜
	* @return Result
	* @date 2018年5月9日 上午10:23:57
	 */
	Result updateUserPass(String id, String password, String account, String operatingId);

	 /**
	  * 
	 * @Title: getUserByCondition 
	 * @Description: 根据条件查询用户
	 * @param @param userDto
	 * @param @return    设定文件 
	 * @return Result    返回类型 
	 * @author lijie
	 * @throws
	  */
    Result getUserByCondition(UserDTO userDto);
    /**
     * 
    * @Title: login 
    * @Description: 登录服务
    * @param @param loginDto
    * @param @return    设定文件 
    * @return Result    返回类型 
    * @author lijie
    * @throws
     */
    Result login(LoginDTO loginDto);
    /**
     * 
    * @Title: changeStaffOper 
    * @Description: 修改用户操作信息 
    * @param @param staffOperDTO
    * @param @return    设定文件 
    * @return Result    返回类型 
    * @author lijie
    * @throws
     */
    Result changeStaffOper(StaffOperDTO staffOperDTO);
}
