package com.born.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.born.core.base.BaseMapper;
import com.born.facade.dto.CompanyPositionDTO;
import com.born.facade.dto.position.PositionAuthDTO;
import com.born.facade.entity.CompanyPosition;
import com.born.facade.vo.companyPosition.CompanyPositionVO;
/**
 * 
* @ClassName: CompanyPositionMapper  
* @Description: 职位管理
* @author 明成
* @date 2018年4月27日  
*
 */
@Repository
public interface CompanyPositionMapper extends BaseMapper<CompanyPosition> {

	/**
	 *	批量逻辑删除职位
	 * @param ids
	 */
	void batchUpdatePosition(@Param("pos") CompanyPositionDTO dto,@Param("ids") String[] ids);
	
	/**
	 * 
	* @Title: selectPositionList 
	* @Description: 查询职位集合
	* @param @param Position
	* @param @return
	* @author 明成
	* @return List<CompanyPositionVO>
	* @date 2018年5月7日 下午4:01:50 
	* @throws
	 */
	List<CompanyPositionVO> selectPositionList(CompanyPositionDTO dept);
	
	/**
	 * 
	* @Title: batchInsertPosition 
	* @Description: 批量添加职位权限
	* @author 张永胜
	* @return void
	* @date 2018年5月9日 下午7:16:43
	 */
	void batchInsertPosition(@Param("param")List<PositionAuthDTO> param);
	
	/**
	 * 
	* @Title: deletePositionAuthByPid 
	* @Description: 根据职位ID 删除职位权限记录
	* @param positionId 职位ID
	* @return 
	* @author 张永胜
	* @return int
	* @date 2018年5月9日 下午7:37:45
	 */
	int deletePositionAuthByPid(String positionId);
	
}
