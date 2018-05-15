package com.born.facade.service;

import com.born.facade.dto.dept.CompanyDeptDTO;
import com.born.util.result.Result;

/**
 * 
* @ClassName: IDeptService  
* @Description: 获取部门接口服务  
* @author 明成
* @date 2018年4月27日  
*
 */
public interface ICompanyDeptService {
	
	/**
	 * 
	* @Title: findOrgList 
	* @Description: 根据条件获取组织机构集合
	* @param @param companyId
	* @param @return
	* @author 明成
	* @return Result
	* @date 2018年5月8日 下午5:18:42 
	* @throws
	 */
	Result findOrgList(String companyId);
	
	/**
	 * 根据ID获取部门
	 * @param CompanyDeptDTO 部门ID必传
	 * @return Result 通用消息返回
	 */
	Result findDept(Long id);
	
	/**
	 * 根据条件获取部门集合
	 * @param Dept 部门
	 * @return Result 通用消息返回
	 */
	Result findDeptList(CompanyDeptDTO deptDTO);
	
	/**
	 * 修改部门
	 * @param Dept 部门
	 * @return Result 通用消息返回
	 */
	Result updateDept(CompanyDeptDTO deptDTO);
	
	/**
	 * 添加部门
	 * @param Dept 部门
	 * @return Result 通用消息返回
	 */
	Result addDept(CompanyDeptDTO deptDTO);
	
	/**
	 * 删除部门(可批量)
	 * @param CompanyDeptDTO 部门ids必传
	 * 多ids列如："1,2,3"
	 * @return Result 通用消息返回
	 */
	Result deleteDept(CompanyDeptDTO dto);
	
	/**
	 * 
	* @Title: getPageList 
	* @Description: 获取部门分页
	* @param @param dto
	* @param @return
	* @author 明成
	* @return Result
	* @date 2018年5月8日 下午7:28:50 
	* @throws
	 */
	Result getPageList(CompanyDeptDTO dto);
	
	/**
	 * 编辑部门
	 * @param Dept 部门
	 * @return Result 通用消息返回
	 */
	Result editDept(CompanyDeptDTO deptDTO);
}
