package com.born.facade.service.store;

import java.util.List;

import com.born.facade.dto.store.StoreGroupDTO;
import com.born.util.result.Result;

/**
 * @Description:店面管理
 * @author 黄伟
 * @date 2018年5月14日 下午6:25:15
 */
public interface IStoreGroupService {
	
	

	/**
	 * 保存店面分组
	 * 
	 * @param store
	 * @return
	 */
	Result insertBatch(List<StoreGroupDTO> list);

	/**
	 * 删除店面分组
	 * 
	 * @param id
	 */
	Result deleteById(Long id);

	/**
	 * 修改店面分组
	 * 
	 * @param store
	 * @return
	 */
	Result update(StoreGroupDTO dto);

	/**
	 * 根据店面ID查询店面分组
	 * @param pageModel
	 * @return
	 */
	Result getGroupListByStoreId(Long storeId);
}
