package com.born.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.born.core.base.BaseMapper;
import com.born.entity.dic.MenuDic;
import com.born.facade.vo.dic.DicItemVO;
import com.born.facade.vo.dic.DicMenuVO;
/**
 * 
* @ClassName: MenuDicMapper 
* @Description: 菜单字典 
* @author lijie 
* @date 2018年5月30日 下午6:28:29 
*
 */
@Repository
public interface MenuDicMapper extends BaseMapper<MenuDic> {
	/**
	 * 
	 * @Title: findDicZtree
	 * @Description: 获取字典数据
	 * @return
	 * @author 张永胜
	 * @return Set<DicType>
	 * @date 2018年5月17日 下午3:44:12
	 */
	List<DicMenuVO> selectDicZtree();

	/**
	 * 这里可以无限查询字典级数数据
	 * 
	 * @Title: findDicItem
	 * @Description: 获取字典级数数据
	 * @param pId
	 *            父ID（字典类型表ID）
	 * @param companyId
	 *            模板公司ID
	 * @return
	 * @author 张永胜
	 * @return List<DicItem>
	 * @date 2018年5月18日 上午10:36:40
	 */
	List<DicItemVO> selectDicItemList(@Param(value = "pId") String pId, @Param(value = "companyId") Long companyId);

}
