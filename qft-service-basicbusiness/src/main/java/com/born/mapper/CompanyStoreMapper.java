package com.born.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.born.core.base.BaseMapper;
import com.born.entity.store.CompanyStore;
import com.born.facade.dto.store.AddCompanyStoreDTO;
import com.born.facade.dto.store.CompanyStoreDTO;
import com.born.facade.vo.store.CompanyStoreVO;

/**
 * @Description: 店面Mapper
 * @author 黄伟
 * @date 2018年5月15日 下午1:50:19
 */
@Repository
public interface CompanyStoreMapper extends BaseMapper<CompanyStore> {

	/**
	 * 添加店面
	 * @param dto
	 * @return
	 */
	Long insertCompanyStore(AddCompanyStoreDTO dto);
	/**
	 * 根据店面ID删除店面
	 * @param roleId
	 */
	void deleteStoreById(@Param("id") Long id);
	
	/**
	 * 查询店面
	 * 
	 * @param dto
	 * @return
	 */
	List<CompanyStoreVO> selectCompanyStore(CompanyStoreDTO dto);
	
	
}
