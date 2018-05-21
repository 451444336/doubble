package com.born.facade.service.store;

import com.born.facade.dto.store.CompanyStoreDTO;
import com.born.util.result.Result;

/**
 * @Description:店面管理
 * @author 黄伟
 * @date 2018年5月14日 下午6:25:15
 */
public interface ICompanyStoreService {
	
	

	/**
	 * 保存店面
	 * 
	 * @param store
	 * @return
	 */
	Result insert(CompanyStoreDTO dto);

	/**
	 * 删除店面
	 * 
	 * @param id
	 */
	Result deleteById(Long id);

	/**
	 * 修改店面
	 * 
	 * @param store
	 * @return
	 */
	Result update(CompanyStoreDTO dto);

	/**
	 * 根据ID查询店面
	 * 
	 * @param id
	 * @return
	 */
	Result getStoreById(Long id);
	
	
	
	/**
	 * 店面列表分页查询
	 * @param pageModel
	 * @return
	 */
	Result getPageList(CompanyStoreDTO dto);
}
