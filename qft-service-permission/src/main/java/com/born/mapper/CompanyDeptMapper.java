package com.born.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.born.core.base.BaseMapper;
import com.born.entity.CompanyDept;
import com.born.facade.dto.dept.CompanyDeptDTO;
import com.born.facade.vo.companyDept.CompanyDeptVO;
/**
 * 
* @ClassName: CompanyDeptMapper  
* @Description: 部门管理
* @author 明成
* @date 2018年4月27日  
*
 */
@Repository
public interface CompanyDeptMapper extends BaseMapper<CompanyDept> {

	/**
	 *	批量逻辑删除部门
	 * @param ids
	 */
	void batchUpdateDept(@Param("dept") CompanyDeptDTO dept,@Param("ids") String[] ids);
	
	/**
	 * 
	* @Title: selectDeptList 
	* @Description: 查询部门集合
	* @param @param dept
	* @param @return
	* @author 明成
	* @return List<CompanyDeptVO>
	* @date 2018年5月7日 下午4:01:50 
	* @throws
	 */
	List<CompanyDeptVO> selectDeptList(CompanyDeptDTO dept);
	
	/**
	 * 
	* @Title: selectOrgList 
	* @Description: 获取组织架构
	* @param @param companyId
	* @param @return
	* @author 明成
	* @return List<CompanyDeptVO>
	* @date 2018年5月8日 下午5:41:20 
	* @throws
	 */
	List<CompanyDeptVO> selectOrgList(@Param("companyId")Long companyId);
	
	/**
	 * 
	* @Title: selectPageList 
	* @Description: 部门获取分页集合
	* @param @param dto
	* @param @return
	* @author 明成
	* @return List<CompanyDeptVO>
	* @date 2018年5月8日 下午7:32:49 
	* @throws
	 */
	List<CompanyDeptVO> selectPageList(CompanyDeptDTO dto);
}
