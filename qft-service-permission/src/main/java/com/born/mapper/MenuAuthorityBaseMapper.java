package com.born.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.born.core.base.BaseMapper;
import com.born.entity.MenuAuthorityBase;
/**
 * 
* @ClassName: MenuAuthorityBaseMapper 
* @Description: 菜单权限数据操作 
* @author lijie 
* @date 2018年5月4日 上午11:40:23 
*
 */
@Repository
public interface MenuAuthorityBaseMapper extends BaseMapper<MenuAuthorityBase> {
	/**
	 * 
	* @Title: updateMenuAuthorityByAuthIds 
	* @Description: 修改权限数据是否删除状态
	* @param @param bases
	* @param @return    设定文件 
	* @return int    返回类型 
	* @author lijie
	* @throws
	 */
	int updateMenuAuthorityByAuthIds(@Param("list") List<Long> list, @Param("isDelete") Byte isDelete,
			@Param("updaterId") Long updaterId);

}
