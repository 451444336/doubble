package com.born.facade.vo.store;

import lombok.Data;

/**
 * @Description: 店面vo
 * @author 黄伟
 * @date 2018年5月15日 上午10:31:20
 */
@Data
public class CompanyStoreVO {
	
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
	 * 公司名称
	 */
	private String companyName;
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
