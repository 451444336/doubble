package com.born.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.born.core.base.BaseMapper;
import com.born.entity.User;
import com.born.facade.dto.user.DeteleUserDTO;
import com.born.facade.dto.user.UserDTO;
import com.born.facade.dto.user.UserRoleDTO;
import com.born.facade.vo.UserApiVO;
import com.born.facade.vo.UserVO;

@Repository
public interface SysUserMapper extends BaseMapper<User> {

	/**
	 * 逻辑删除用户
	 * 
	 * @param DeteleUserDTO
	 *            请求参数
	 */
	void updateUser(DeteleUserDTO dto);

	/**
	 * 
	 * @Title: selectUserByCondition
	 * @Description: 根据条件查询用户信息
	 * @param @param
	 *            userDto
	 * @param @return
	 *            设定文件
	 * @return List<UserVO> 返回类型
	 * @author lijie
	 */
	List<UserVO> selectUserByCondition(UserDTO userDto);

	/**
	 * 
	 * @Title: selectUserByAccount
	 * @Description: 获取账户
	 * @param account
	 *            账号
	 * @return
	 * @author 张永胜
	 * @return User
	 * @date 2018年5月4日 下午6:10:13
	 */
	UserApiVO selectUserByAccount(String account);

	/**
	 * 
	 * @Title: selectUserIsExist
	 * @Description: 查询用户是否存在
	 * @param account
	 *            账号
	 * @return
	 * @author 张永胜
	 * @return int
	 * @date 2018年5月4日 下午6:10:56
	 */
	int selectUserIsExist(String account);
	
	/**
	 * 
	* @Title: updateUserPassById 
	* @Description: 更新用户的ID
	* @param id 用户ID
	* @return 
	* @author 张永胜
	* @return int
	* @date 2018年5月9日 上午10:27:39
	 */
	int updateUserPassById(Map<String, Object> param);

	/**
	 * 
	* @Title: insertRoleUser 
	* @Description: 添加用户角色中间表
	* @param @param dto
	* @param @return
	* @author 明成
	* @return int
	* @date 2018年5月18日 上午10:56:06 
	* @throws
	 */
	int insertRoleUser(UserRoleDTO dto);
}
