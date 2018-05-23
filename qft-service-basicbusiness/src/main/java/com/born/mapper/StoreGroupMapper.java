package com.born.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.born.core.base.BaseMapper;
import com.born.facade.dto.store.StoreGroupDTO;
import com.born.facade.entity.store.StoreGroup;
import com.born.facade.vo.store.StoreGroupVO;

/**
 * @Description: 店面分组Mapper
 * @author 黄伟
 * @date 2018年5月15日 下午1:50:19
 */
@Repository
public interface StoreGroupMapper extends BaseMapper<StoreGroup> {

	/**
	 * 根据分组ID删除店面分组
	 * @param roleId
	 */
	void deleteGroupById(@Param("id") Long id);
	
	/**
	 * 根据店面ID查询店面分组
	 * 
	 * @param dto
	 * @return
	 */
	List<StoreGroupVO> selectGroupByStoreId(StoreGroupDTO dto);
	
	
}
