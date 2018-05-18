package com.born.service.impl.extend;

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
* @ClassName: PositionmMenuAuth 
* @Description: 职位权限数据 
* @author lijie 
* @date 2018年5月18日 下午2:18:27 
*
 */
@Slf4j
@Component("positionmMenuAuth")
public class PositionmMenuAuth extends MenuPermissionGen {

	@Override
	public List<MenuPermissionVO> genMenuAuth(PermissionQueryDTO dto) throws CloneNotSupportedException {
		// 得到菜单权限
		final List<PermissionVO> menuAuths = getPermissionsByMenuIds(dto.getMenuIds());
		if (CollectionUtils.isEmpty(menuAuths)) {
			log.info("根据菜单ID:" + JSON.toJSONString(dto.getMenuIds()) + "未查询到相关数据");
			return ContainerUtil.aList();
		}
		// 职位权限数据ID
		final Set<Long> positionAuthIds = getPositionPermissionIds(getPositionPermissions(dto.getPositionId()));
		for (PermissionVO mpv : menuAuths) {
			if (positionAuthIds.contains(mpv.getAuthorityId())) {
				mpv.setIsCheck(MenuAuthEnum.CHECK.getStatus());
			}
		}
		return genMenuPermission(menuAuths);
	}

}
