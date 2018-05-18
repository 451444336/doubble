package com.born.service.impl.extend;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;
import com.born.facade.constant.MenuAuthEnum;
import com.born.facade.dto.permission.PermissionQueryDTO;
import com.born.facade.vo.permission.MenuPermissionVO;
import com.born.facade.vo.permission.PermissionVO;
import com.born.util.ContainerUtil;

import lombok.extern.slf4j.Slf4j;
/**
 * 
* @ClassName: PersionMenuAuth 
* @Description: 个人菜单权限数据 
* @author lijie 
* @date 2018年5月18日 下午2:37:29 
*
 */
@Slf4j
@Component("persionMenuAuth")
public class PersionMenuAuth extends MenuPermissionGen {

	@Override
	public List<MenuPermissionVO> genMenuAuth(PermissionQueryDTO dto) throws CloneNotSupportedException {
		// 得到职位权限
		final List<PermissionVO> positionAuths = getPositionPermissions(dto.getPositionId());
		if (CollectionUtils.isEmpty(positionAuths)) {
			log.info("当前职位" + dto.getPositionId() + "没有权限数据");
			return ContainerUtil.aList();
		}
		// 得到菜单权限
		final List<PermissionVO> menuAuths = getPermissionsByMenuIds(dto.getMenuIds());
		if (CollectionUtils.isEmpty(menuAuths)) {
			log.info("根据菜单ID:" + JSON.toJSONString(dto.getMenuIds()) + "未查询到相关数据");
			return ContainerUtil.aList();
		}
		// 职位权限数据ID
		final Set<Long> positionAuthIds = getPositionPermissionIds(positionAuths);
		Iterator<PermissionVO> it = menuAuths.iterator();
		while (it.hasNext()) {
			if (!positionAuthIds.contains(it.next().getAuthorityId())) {
				it.remove();
			}
		}
		// 查询个人权限数据
		final List<PermissionVO> users = getPersionPermissions(dto.getUserId());
		if (CollectionUtils.isNotEmpty(users)) {
			final Set<Long> userSets = new HashSet<>();
			for (PermissionVO pv : users) {
				userSets.add(pv.getAuthorityId());
			}
			for (PermissionVO mpv : menuAuths) {
				if (userSets.contains(mpv.getAuthorityId())) {
					mpv.setIsCheck(MenuAuthEnum.CHECK.getStatus());
				}
			}
		}
		return genMenuPermission(menuAuths, dto.getUserId(), dto.getCompanyId());
	}
	/**
	 * 
	* @Title: getPersionPermissions 
	* @Description: 得到个人权限数据 
	* @param @param userId
	* @param @return    设定文件 
	* @return List<PermissionVO>    返回类型 
	* @author lijie
	* @throws
	 */
	private List<PermissionVO> getPersionPermissions(Long userId) {

		return companyAuthorityMapper.selectPersonalPermissions(userId);
	}
}
