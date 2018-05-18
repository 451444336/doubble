package com.born.service.impl.extend;

import java.util.List;

import org.springframework.stereotype.Component;

import com.born.core.exception.BizException;
import com.born.facade.constant.MenuAuthEnum;
import com.born.facade.dto.permission.PermissionQueryDTO;
import com.born.facade.vo.permission.MenuPermissionVO;
import com.born.util.bean.SpringUtil;
/**
 * 
* @ClassName: MenuPermissionFactory 
* @Description: 菜单权限 
* @author lijie 
* @date 2018年5月18日 下午3:37:58 
*
 */
@Component
public class MenuPermissionFactory implements MenuPermissionContent {

	private MenuPermissionContent menuPermission;

	private MenuPermissionContent getMenuPermissionContent(final Byte type) {
		if (MenuAuthEnum.POSITION_AUTH.getStatus().equals(type)) {
			menuPermission = SpringUtil.getBeanByName("positionmMenuAuth", PositionmMenuAuth.class);
		} else if (MenuAuthEnum.PERSION_AUTH.getStatus().equals(type)) {
			menuPermission = SpringUtil.getBeanByName("persionMenuAuth", PersionMenuAuth.class);
		}
		if (null == menuPermission) {
			throw new BizException("MenuPermissionContent class project is null ");
		}
		return menuPermission;
	}

	@Override
	public List<MenuPermissionVO> genMenuAuth(PermissionQueryDTO dto) throws CloneNotSupportedException {
		return getMenuPermissionContent(dto.getOperType()).genMenuAuth(dto);
	}
	
}
