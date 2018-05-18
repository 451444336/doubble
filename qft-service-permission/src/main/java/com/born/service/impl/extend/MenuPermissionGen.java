package com.born.service.impl.extend;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.born.facade.vo.MenuAuthorityVO;
import com.born.facade.vo.MenuVO;
import com.born.facade.vo.permission.MenuPermissionVO;
import com.born.facade.vo.permission.PermissionVO;
import com.born.mapper.CompanyAuthorityMapper;
import com.born.mapper.CompanyMenuMapper;
import com.born.mapper.MenuAuthorityMapper;

/**
 * 
* @ClassName: MenuPermissionGen 
* @Description: 菜单权限生成 
* @author lijie 
* @date 2018年5月18日 下午2:12:08 
*
 */
public abstract class MenuPermissionGen implements MenuPermissionContent {

	@Autowired
	protected CompanyAuthorityMapper companyAuthorityMapper;
	
	@Autowired
	protected MenuAuthorityMapper menuAuthorityMapper;
	
	@Autowired
	protected CompanyMenuMapper companyMenuMapper;
	/**
	 * 
	* @Title: getPermissionsByMenuIds 
	* @Description: 根据菜单ID 查询 菜单权限数据
	* @param @param menuIds
	* @param @return
	* @param @throws CloneNotSupportedException    设定文件 
	* @return List<PermissionVO>    返回类型 
	* @author lijie
	* @throws
	 */
	protected List<PermissionVO> getPermissionsByMenuIds(final List<Long> menuIds) throws CloneNotSupportedException {
		final List<PermissionVO> result = new LinkedList<>();
		List<MenuAuthorityVO> mAuths = menuAuthorityMapper.selectAuthorityListByMenuIds(menuIds);
		if (CollectionUtils.isNotEmpty(mAuths)) {
			final PermissionVO rt = new PermissionVO();
			for (MenuAuthorityVO m : mAuths) {
				rt.setMenuId(m.getMenuId());
				rt.setMenuName(m.getMenuName());
				rt.setAuthorityId(m.getAuthorityId());
				rt.setAuthorityName(m.getAuthorityName());
				rt.setAuthorityUrl(m.getAuthorityUrl());
				rt.setOperType(m.getOperType());
				result.add(rt.clone());
			}
		}
		return result;
	}
	/**
	 * 
	* @Title: getPositionPermissions 
	* @Description: 根据职位ID查询权限数据 
	* @param @param positionId
	* @param @return    设定文件 
	* @return List<PermissionVO>    返回类型 
	* @author lijie
	* @throws
	 */
	protected List<PermissionVO> getPositionPermissions(final Long positionId) {

		return companyAuthorityMapper.selectPositionPermissions(positionId);
	}
	/**
	 * 
	* @Title: getPositionPermissionIds 
	* @Description: 得到职位权限ID集合 用于校验 
	* @param @param positions
	* @param @return    设定文件 
	* @return Set<Long>    返回类型 
	* @author lijie
	* @throws
	 */
	protected Set<Long> getPositionPermissionIds(List<PermissionVO> positions) {
		final Set<Long> result = new HashSet<>();
		if (CollectionUtils.isNotEmpty(positions)) {
			for (PermissionVO pv : positions) {
				result.add(pv.getAuthorityId());
			}
		}
		return result;
	}
	/**
	 * 
	* @Title: genMenuPermission 
	* @Description: 得到返回数据
	* @param @param menuAuths
	* @param @return    设定文件 
	* @return List<MenuPermissionVO>    返回类型 
	* @author lijie
	* @throws
	 */
	protected List<MenuPermissionVO> genMenuPermission(List<PermissionVO> menuAuths) {
		final List<MenuPermissionVO> result = new LinkedList<MenuPermissionVO>();
		if (CollectionUtils.isNotEmpty(menuAuths)) {
			// 封装返回分组对象
			final Map<Long, List<PermissionVO>> resultMap = genPermissionMap(menuAuths);
			if (resultMap.size() > 0) {
				MenuPermissionVO mpv;
				for (Map.Entry<Long, List<PermissionVO>> me : resultMap.entrySet()) {
					mpv = new MenuPermissionVO();
					mpv.setMenuId(me.getKey());
					mpv.setMenuName(me.getValue().get(0).getMenuName());
					mpv.setPermissions(me.getValue());
					result.add(mpv);
				}
			}
		}
		return result;
	}
	/**
	 * 
	* @Title: genMenuPermission 
	* @Description: 校验是否有菜单勾选 
	* @param @param menuAuths
	* @param @param isCheckMenu
	* @param @return    设定文件 
	* @return List<MenuPermissionVO>    返回类型 
	* @author lijie
	* @throws
	 */
	protected List<MenuPermissionVO> genMenuPermission(List<PermissionVO> menuAuths, final Long userId,
			final String companyId) {
		final List<MenuPermissionVO> result = new LinkedList<MenuPermissionVO>();
		if (CollectionUtils.isNotEmpty(menuAuths)) {
			// 封装返回分组对象
			final Map<Long, List<PermissionVO>> resultMap = genPermissionMap(menuAuths);
			if (resultMap.size() > 0) {
				// 查询个人菜单数据
				List<MenuVO> pMenus = companyMenuMapper.selectMenuByUserId(userId, companyId);
				final Set<Long> menuIds = new HashSet<>();
				if (CollectionUtils.isNotEmpty(pMenus)) {
					for (MenuVO m : pMenus) {
						menuIds.add(m.getId());
					}
				}
				MenuPermissionVO mpv;
				for (Map.Entry<Long, List<PermissionVO>> me : resultMap.entrySet()) {
					mpv = new MenuPermissionVO();
					mpv.setMenuId(me.getKey());
					mpv.setMenuName(me.getValue().get(0).getMenuName());
					mpv.setPermissions(me.getValue());
					mpv.setIsCheck(menuIds.contains(me.getKey()) ? (byte) 1 : (byte) 0);
					result.add(mpv);
				}
			}
		}
		return result;
	}
	
	
	private Map<Long, List<PermissionVO>> genPermissionMap(List<PermissionVO> menuAuths) {
		final Map<Long, List<PermissionVO>> resultMap = new HashMap<>();
		if (CollectionUtils.isNotEmpty(menuAuths)) {
			List<PermissionVO> list;
			for (PermissionVO pv : menuAuths) {
				list = resultMap.get(pv.getMenuId());
				if (null == list) {
					list = new LinkedList<>();
					resultMap.put(pv.getMenuId(), list);
				}
				list.add(pv);
			}
		}
		return resultMap;
	}
}
