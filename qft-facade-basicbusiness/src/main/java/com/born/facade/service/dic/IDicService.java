package com.born.facade.service.dic;

import com.born.facade.dto.dic.DicItemDTO;
import com.born.facade.dto.dic.UpdateDicItemDTO;
import com.born.util.result.Result;

/**
 * 字典
 * 
 * @ClassName: IDicService
 * @Description: 字典服务
 * @author 张永胜
 * @date 2018年5月17日 下午4:58:51
 * @version 1.0
 */
public interface IDicService {

	/**
	 * 获取字典数据
	 * 
	 * @Title: findDicList
	 * @Description: 菜单和字典数据
	 * @return
	 * @author 张永胜
	 * @return List<DicMenu>
	 * @date 2018年5月17日 下午5:03:25
	 */
	Result findDicZtree();

	/**
	 * 可以无限级数字典查询
	 * 
	 * @Title: findDicItemList
	 * @Description: 获取字典数据列表
	 * @param params
	 *            参数,包括分页参数
	 * @return
	 * @author 张永胜
	 * @return Result
	 * @date 2018年5月18日 上午10:41:18
	 */
	Result findDicItemList(DicItemDTO params);

	/**
	 * 
	 * @Title: updateDicItemById
	 * @Description: 根据ID 更新某个字典
	 * @param updateDicItemDTO
	 * @return
	 * @author 张永胜
	 * @return Result
	 * @date 2018年5月18日 下午3:35:57
	 */
	Result updateDicItemById(UpdateDicItemDTO updateDicItemDTO);

	/**
	 * 
	 * @Title: addDicItem
	 * @Description: 添加字典
	 * @param pId
	 *            父级ID
	 * @param dicItem
	 *            二级名称数据,多个参数以逗号隔开
	 * @return
	 * @author 张永胜
	 * @return Result
	 * @date 2018年5月21日 上午11:51:24
	 */
	Result addDicItem(String pId, String dicItem);

	/**
	 * 
	 * @Title: addDicSubItem
	 * @Description: 添加二级字典
	 * @param pId
	 *            父级ID
	 * @param dicSubItem
	 *            二级名称数据,多个参数以逗号隔开
	 * @return
	 * @author 张永胜
	 * @return Result
	 * @date 2018年5月21日 上午11:50:48
	 */
	Result addDicSubItem(String pId, String dicSubItem);

	/**
	 * 根据ID删除
	 * 
	 * @Title: deleteDicItemByIds
	 * @Description: 批量删除字典数据
	 * @param ids
	 *            参数ID
	 * @return
	 * @author 张永胜
	 * @return Result
	 * @date 2018年5月18日 下午6:14:34
	 */
	Result deleteDicItemByIds(String ids);

	/**
	 * 
	 * @Title: deleteDicItemById
	 * @Description: 根据单个ID删除二级字典数据
	 * @param id 二级字典ID
	 * @return
	 * @author 张永胜
	 * @return Result
	 * @date 2018年5月21日 下午4:59:44
	 */
	Result deleteDicItemById(String id);
}