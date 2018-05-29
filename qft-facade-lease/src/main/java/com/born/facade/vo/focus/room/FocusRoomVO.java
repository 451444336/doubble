package com.born.facade.vo.focus.room;


import lombok.Data;

/**
 * @Description: 集中整租房源vo
 * @author 黄伟
 * @date 2018年5月25日 下午6:15:21
 */
@Data
public class FocusRoomVO{

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
	
}
