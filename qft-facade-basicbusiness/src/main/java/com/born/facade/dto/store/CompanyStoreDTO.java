package com.born.facade.dto.store;

import java.util.Date;

import com.born.core.page.PageBean;

import lombok.Data;

/**
 * @Description: 店面dto
 * @author 黄伟
 * @date 2018年5月14日 下午6:25:15
 */
@Data
public class CompanyStoreDTO extends PageBean {


	/**
	 * 
	 */
	private static final long serialVersionUID = 5225319627305280537L;
	/**
	 * 主键ID
	 */
	private Long id;
	 /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 创建人ID
     */
    private Long createrId;
    /**
     * 修改人ID
     */
    private Long updaterId;
    /**
     * 删除状态
     */
    private Integer IsDelete;
	/**
	 * 公司ID
	 */
	private String companyId;
	/**
	 * 城市
	 */
	private String city;
	/**
	 * 店面名称
	 */
	private String name;
	/**
	 * 所在地
	 */
	private String adress;
	/**
	 * 合租房间数
	 */
	private Long cotenant;
	/**
	 * 整租房间数
	 */
	private Long housing;
	/**
	 * 集中房间数
	 */
	private Long focus;
	/**
	 * 座机号
	 */
	private String phone;
	/**
	 * 分组
	 */
	private String grouping;
	/**
	 * 是否分组
	 */
	private byte groupingStatus;
	/**
	 * 地图中定位地址
	 */
	private String localizeAdd;
}
