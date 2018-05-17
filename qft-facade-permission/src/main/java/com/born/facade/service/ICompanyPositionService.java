package com.born.facade.service;

import com.born.facade.dto.CompanyPositionDTO;
import com.born.util.result.Result;

/**
 * 
 * @ClassName: ICompanyPositionService
 * @Description: 获取职位接口服务
 * @author 明成
 * @date 2018年4月27日
 *
 */
public interface ICompanyPositionService {
	/**
	 * 根据ID获取职位
	 * 
	 * @param CompanyDeptDTO
	 *            职位ID必传
	 * @return Result 通用消息返回
	 */
	Result findPosition(Long id);

	/**
	 * 根据条件获取职位集合
	 * 
	 * @param Dept
	 *            职位
	 * @return Result 通用消息返回
	 */
	Result findPositionList(CompanyPositionDTO dto);

	/**
	 * 修改职位
	 * 
	 * @param Dept
	 *            职位
	 * @return Result 通用消息返回
	 */
	Result updatePosition(CompanyPositionDTO dto);

	/**
	 * 添加职位
	 * 
	 * @param Dept
	 *            职位
	 * @return Result 通用消息返回
	 */
	Result addPosition(CompanyPositionDTO dto);

	/**
	 * 删除职位(可批量)
	 * 
	 * @param String
	 *            职位ID必传 多ID列如："1,2,3"
	 * @return Result 通用消息返回
	 */
	Result deletePosition(String ids);

	/**
	 * 
	 * @Title: getPageList @Description: 获取职位分页 @param @param
	 * dto @param @return @author 明成 @return Result @date 2018年5月8日
	 * 下午7:28:50 @throws
	 */
	Result getPageList(CompanyPositionDTO dto);

	/**
	 * 编辑员工
	 * 
	 * @param Position
	 *            员工
	 * @return Result 通用消息返回
	 */
	Result editPosition(CompanyPositionDTO dto);

	/**
	 * 
	* @Title: addPositionAuth 
	* @Description: 添加职位权限表
	* @param pId 职位ID
	* @param authIds 权限ID
	* @param createrId 创建人
	* @return 
	* @author 张永胜
	* @return Result
	* @date 2018年5月9日 下午7:35:17
	 */
	Result addPositionAuth(String pId, String[] authIds,String createrId);

	/**
	 * 
	* @Title: getPositionByComId 
	* @Description: 根据条件获取职位
	* @param @param CompanyPositionDTO
	* @param @return
	* @author 明成
	* @return Result
	* @date 2018年5月17日 下午3:53:55 
	* @throws
	 */
	Result selectPosition(CompanyPositionDTO dto);
}
