package com.born.facade.vo;

import java.io.Serializable;
import java.util.List;

import com.born.facade.vo.permission.MenuPermissionVO;

import lombok.Data;
/**
 * 
* @ClassName: MenuVO 
* @Description: 菜单返回实体 
* @author lijie 
* @date 2018年4月28日 上午11:52:34 
*
 */
@Data
public class MenuVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -732081124504478292L;
	/**
     * id
     */
    private Long id;
    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * 菜单地址
     */
    private String menuUrl;
    /**
     * 菜单父id
     */
    private Long parentId;
    /**
     * 顺序
     */
    private Integer sequence;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 菜单类型：1、正常菜单 2、后台菜单 3、首页菜单 4、公有菜单
     */
    private Byte type;
    /**
     * 菜单归属类型：1、pc  2、手机
     */
    private Byte ascription;
    /**
     * 菜单级别
     */
    private Integer menuLevel;
    /**
     * 公司ID
     */
    private String companyId;
    /**
     * 子菜单
     */
    private List<MenuVO> childs;
    /**
     * 权限数据
     */
   private MenuPermissionVO permission;
}
