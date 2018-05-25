package com.born.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.born.core.base.BaseMapper;
import com.born.entity.CompanyStaff;
import com.born.facade.dto.staff.DeteleStaffDTO;
import com.born.facade.dto.staff.FindStaffListDTO;
import com.born.facade.dto.staff.PositionStaffDTO;
import com.born.facade.vo.CompanyStaffVO;

@Repository
public interface CompanyStaffMapper extends BaseMapper<CompanyStaff> {

	/**
	 * 
	 * @Title: findStaffByUserId
	 * @Description: 获取用户基本信息表
	 * @param userId
	 *            用户ID
	 * @return
	 * @author 张永胜
	 * @return CompanyStaff
	 * @date 2018年5月4日 下午6:12:51
	 */
	CompanyStaff selectStaffByUserId(String userId);

	/**
	 * 逻辑删除员工
	 * 
	 * @param ids
	 */
	void updateStaff(DeteleStaffDTO dto);

	/**
	 * 根据员工ID 获取基本信息
	 * 
	 * @author 明成
	 * @param id
	 * @return CompanyStaffVo
	 */
	CompanyStaffVO selectStaffById(@Param("id") Long id);

	/**
	 * 根据条件获取员工集合
	 * 
	 * @author 明成
	 * @param dto
	 * @return CompanyStaffVO
	 */
	List<CompanyStaffVO> selectStaffList(FindStaffListDTO dto);

	/**
	 * 添加职位员工关系表
	 * 
	 * @author 明成
	 * @param PositionStaffDTO
	 */
	void insertPositionStaff(PositionStaffDTO dto);

	/**
	 * 修改职位员工关系表
	 * 
	 * @author 明成
	 * @param PositionStaffDTO
	 */
	void updatePositionStaff(PositionStaffDTO dto);
	
	/**
	 * 
	* @Title: validation 
	* @Description: 用户验证
	* @param @param dto
	* @param @return
	* @author 明成
	* @return Integer
	* @date 2018年5月23日 上午10:12:24 
	* @throws
	 */
	Integer validation(FindStaffListDTO dto);
}
