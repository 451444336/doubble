package com.born.entity.focus.room;

import javax.persistence.Column;
import javax.persistence.Table;

import com.born.core.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description: 集中整租房间实体类
 * @author 黄伟
 * @date 2018年5月25日 下午6:15:21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "qft_focus_room")
public class FocusRoom extends BaseEntity<FocusRoom>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7866833387307006884L;
	
	/**
	 * 公司ID
	 */
	@Column(name = "company_id")
	private Long companyId;
	/**
	 * 是否删除：0 正常，1删除
	 */
	@Column(name = "is_delete")
	private byte isDelete;

}
