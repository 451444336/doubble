package com.born.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.born.core.base.BaseMapper;
import com.born.facade.entity.dic.DicItem;

/**
 * 
 * @ClassName: OrderMapper
 * @Description: 订单数据
 * @author lijie
 * @date 2018年5月15日 下午1:58:26
 *
 */
@Repository
public interface DicItemMapper extends BaseMapper<DicItem> {

	/**
	 * 
	 * @Title: updateDicItem
	 * @Description: 更新字典数据
	 * @param id
	 * @param name
	 * @return
	 * @author 张永胜
	 * @return int
	 * @date 2018年5月18日 下午3:24:22
	 */
	int updateDicItemById(@Param(value = "id") String id, @Param(value = "name") String name,
			@Param(value = "companyId") String companyId);

	/**
	 * 
	* @Title: deleteItemById 
	* @Description: 根据ID删除二级字典
	* @param companyId
	* @param id
	* @return 
	* @author 张永胜
	* @return int
	* @date 2018年5月21日 下午4:57:49
	 */
	int deleteItemById(@Param(value = "companyId") String companyId, @Param(value = "id") String id);
	
	/**
	 * 
	 * @Title: batchDeleteItemByIds
	 * @Description: 批量删除字典信息
	 * @param companyId
	 *            模板公司ID
	 * @param ids
	 *            条件ID
	 * @return
	 * @author 张永胜
	 * @return int
	 * @date 2018年5月21日 上午11:32:24
	 */
	int batchDeleteItemByIds(@Param(value = "companyId") String companyId, @Param(value = "ids") String[] ids);

	/**
	 * 
	 * @Title: batchDeleteSubItemByIds
	 * @Description: 批量删除二级字典
	 * @param companyId
	 *            模板公司ID
	 * @param ids
	 *            条件ID
	 * @return
	 * @author 张永胜
	 * @return int
	 * @date 2018年5月21日 上午11:32:04
	 */
	int batchDeleteSubItemByIds(@Param(value = "companyId") String companyId, @Param(value = "ids") String[] ids);

}
