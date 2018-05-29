package com.born.facade.dto.focus.room;


import java.util.Date;

import com.born.core.page.PageBean;

import lombok.Data;

/**
 * 
 * @description 房间配置dto
 * @author 黄伟
 * @date 2018年5月29日 下午3:05:49
 */
@Data
public class RoomConfigDTO extends PageBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7653521860650244711L;
	
	/**
	 * 主键ID
	 */
	private Long id;
	/**
	 * 创建人ID
	 */
	private Long createrId;
	/**
     * 创建时间
     */
    private Date createTime;
	/**
     * 修改人ID
     */
    private Long updaterId;
    /**
	 * 修改时间
	 */
	private Date updateTime;
    /**
	 * 公司ID
	 */
	private Long companyId;
	/**
	 * 配置名称
	 */
	private String configName;
	/**
	 * 配置种类：0家电类；1家具类，2办公设置类
	 */
	private byte configType; 
	/**
	 * 父级ID
	 */
	private Long parentId;
	/**
	 * 是否删除：0 正常，1删除
	 */
	private byte isDelete;
}
