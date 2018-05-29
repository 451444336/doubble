package com.born.entity.focus.room;

import javax.persistence.Column;
import javax.persistence.Table;

import com.born.core.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @description 房间配置实体类
 * @author 黄伟
 * @date 2018年5月29日 下午3:06:08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "qft_focus_room_config")
public class RoomConfig extends BaseEntity<RoomConfig>{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4329671512441776621L;
	
	/**
	 * 公司ID
	 */
	@Column(name = "company_id")
	private Long companyId;
	/**
	 * 配置名称
	 */
	@Column(name = "config_name")
	private String configName;
	/**
	 * 配置种类：0家电类；1家具类，2办公设置类
	 */
	@Column(name = "config_type")
	private byte configType; 
	/**
	 * 父级ID
	 */
	@Column(name = "parent_id")	
	private Long parentId;
	/**
	 * 是否删除：0 正常，1删除
	 */
	@Column(name = "is_delete")
	private byte isDelete;

}
