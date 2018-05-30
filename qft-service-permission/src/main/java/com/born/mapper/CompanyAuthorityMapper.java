package com.born.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.born.core.base.BaseMapper;
import com.born.entity.CompanyAuthority;
import com.born.facade.vo.PermissionInfoVO;
import com.born.facade.vo.company.CompanyInfoVO;
import com.born.facade.vo.permission.PermissionVO;
/**
 * 
* @ClassName: CompanyAuthorityMapper 
* @Description: 公司权限数据操作
* @author lijie 
* @date 2018年5月3日 下午2:47:51 
*
 */
@Repository
public interface CompanyAuthorityMapper extends BaseMapper<CompanyAuthority> {
	/**
	 * 
	* @Title: selectCompanyAuthorityList 
	* @Description: 查询菜单/权限详情数据 
	* @param @param menuIds
	* @param @return    设定文件 
	* @return List<PermissionInfoVO>    返回类型 
	* @author lijie
	* @throws
	 */
	List<PermissionInfoVO> selectCompanyAuthorityList(@Param("menuIds") List<String> menuIds);
	/**
	 * 
	* @Title: selectCompanyInfo 
	* @Description: 获取公司详情
	* @param @param corUrl
	* @param @return    设定文件 
	* @return CompanyInfoVO    返回类型 
	* @author lijie
	* @throws
	 */
	CompanyInfoVO selectCompanyInfo(@Param("corUrl") String corUrl);
	/**
	 * 
	* @Title: selectPersonalPermissions 
	* @Description: 查询个人权限数据
	* @param @param userId
	* @param @return    设定文件 
	* @return List<PermissionVO>    返回类型 
	* @author lijie
	* @throws
	 */
	List<PermissionVO> selectPersonalPermissions(@Param("userId") Long userId);
	/**
	 * 
	* @Title: selectPositionPermissions 
	* @Description: 查询职位权限数据 
	* @param @param positionId
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author lijie
	* @throws
	 */
	List<PermissionVO> selectPositionPermissions(@Param("positionId") Long positionId);
	/**
	 * 
	* @Title: selectPermissions 
	* @Description: 根据公司ID查询权限数据
	* @param @param companyId
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author lijie
	* @throws
	 */
	List<PermissionVO> selectPermissions(@Param("companyId") Long companyId);
	/**
	 * 
	* @Title: updateMenuAuthorityByAuthIds 
	* @Description: 修改权限数据是否删除状态
	* @param @param bases
	* @param @return    设定文件 
	* @return int    返回类型 
	* @author lijie
	* @throws
	 */
	int updateMenuAuthorityByAuthIds(@Param("list") List<Long> list, @Param("isDelete") Byte isDelete,
			@Param("updaterId") Long updaterId);

}
