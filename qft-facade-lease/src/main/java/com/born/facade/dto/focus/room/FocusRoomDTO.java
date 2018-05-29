package com.born.facade.dto.focus.room;


import com.born.core.page.PageBean;

import lombok.Data;

/**
 * @Description: 集中整租房源dto
 * @author 黄伟
 * @date 2018年5月25日 下午6:15:21
 */
@Data
public class FocusRoomDTO extends PageBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4715165883274928504L;
	/**
	 * 主键ID
	 */
	private Long id;
	/**
	 * 创建人ID
	 */
	private String createrId;
	/**
     * 创建时间
     */
    private String createTime;
	/**
     * 修改人ID
     */
    private Long updaterId;
    /**
	 * 修改时间
	 */
	private String updateTime;
    /**
	 * 公司ID
	 */
	private Long companyId;
	
	
	/**
	 * 是否删除：0 正常，1删除
	 */
	private byte isDelete;
}
