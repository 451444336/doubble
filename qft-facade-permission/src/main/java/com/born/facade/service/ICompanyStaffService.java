package com.born.facade.service;

import com.born.facade.dto.CompanyStaffDTO;
import com.born.facade.dto.staff.FindStaffListDTO;
import com.born.util.result.Result;

/**
 * 
 * @ClassName: ICompanyStaffService
 * @Description: 员工信息
 * @author 张永胜
 * @date 2018年5月4日 下午5:58:59
 * @version 1.0
 */
public interface ICompanyStaffService {

	/**
	 * 
	 * @Title: getCompanyStaff
	 * @Description: 根据用户ID查询用户的基本信息
	 * @param userId
	 *            用户ID
	 * @author 张永胜
	 * @return Result
	 * @date 2018年5月4日
	 */
	Result getCompanyStaff(String userId);

	/**
	 * 根据ID获取员工
	 * 
	 * @param CompanyStaffDTO
	 *            员工ID必传
	 * @return Result 通用消息返回
	 */
	Result findStaff(Long id);

	/**
	 * 根据条件获取员工集合
	 * 
	 * @param Staff
	 *            员工
	 * @return Result 通用消息返回
	 */
	Result findStaffList(FindStaffListDTO staffDTO);

	/**
	 * 修改员工
	 * 
	 * @param Staff
	 *            员工
	 * @return Result 通用消息返回
	 */
	Result updateStaff(CompanyStaffDTO staffDTO);

	/**
	 * 添加员工
	 * 
	 * @param Staff
	 *            员工
	 * @return Result 通用消息返回
	 */
	Result addStaff(CompanyStaffDTO staffDTO);

	/**
	 * 逻辑删除员工
	 * 
	 * @param Long 用户ID
	 * @return Result 通用消息返回
	 */
	Result deleteStaff(Long userId);
	
	
	/**
	 * 
	* @Title: getPageList 
	* @Description: 获取员工分页
	* @param @param dto
	* @param @return
	* @author 明成
	* @return Result
	* @date 2018年5月8日 下午7:28:50 
	* @throws
	 */
	Result getPageList(FindStaffListDTO dto);
	
	/**
	 * 修改员工状态
	 * 
	 * @param Staff
	 *            员工
	 * @return Result 通用消息返回
	 */
	Result updateUser(CompanyStaffDTO staffDTO);
}
