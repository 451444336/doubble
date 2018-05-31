package com.born.facade.dto.focus.housing;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.born.core.page.PageBean;

import lombok.Data;

/**
 * @Description: 集中整租房源vo
 * @author 黄伟
 * @date 2018年5月25日 下午6:15:21
 */
@Data
public class UpdRoomCountHousingDTO extends PageBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8205775007308149933L;
	/**
	 * 主键ID
	 */
	@NotNull(message = "房源ID不能为空！")
	private Long id;
	/**
     * 修改人ID
     */
    private Long updaterId;
    /**
	 * 修改时间
	 */
	private Date updateTime;
	/**
	 * 房间数 为0表示未分配房间
	 */
	private int roomCount;
	/**
	 * 房间余量
	 */
	private int restRoomCount;
	/**
	 * 房间数差量，默认为＋，加负号为-
	 */
	private int variableCount;
	/**
	 * 房间余量差量，默认为＋，加负号为-
	 */
	private int variableRestCount;
}
