package com.born.facade.entity.store;

import javax.persistence.Column;
import javax.persistence.Table;

import com.born.core.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description: 店面实体类
 * @author 黄伟
 * @date 2018年5月15日 下午1:37:21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "qft_store")
public class CompanyStore extends BaseEntity<CompanyStore> {


	/**
	 * 
	 */
	private static final long serialVersionUID = 6188668011889710153L;
	/**
	 * 公司ID
	 */
	@Column(name = "company_id")
	private String companyId;
	/**
	 * 店面名称
	 */
	@Column(name = "name")
	private String name;
	/**
	 * 店面值
	 */
	@Column(name = "value")
	private String value;
	/**
	 * 所在地
	 */
	@Column(name = "adress")
	private String adress;
	/**
	 * 手机号
	 */
	@Column(name = "mobile")
	private String mobile;
	/**
	 * 座机号
	 */
	@Column(name = "phone")
	private String phone;
	/**
	 * 经度
	 */
	@Column(name = "lng")
	private String lng;
	/**
	 * 纬度
	 */
	@Column(name = "lat")
	private String lat;
	/**
	 * 打卡定位的地址
	 */
	@Column(name = "atten_Address")
	private String attenAddress;
	/**
	 * wifi名称
	 */
	@Column(name = "ssid")
	private String ssid;
	/**
	 * wifi唯一标识
	 */
	@Column(name = "bssid")
	private String bssid;
	/**
	 * 打卡方式1.二维码打卡2.wifi打卡3.范围打卡
	 */
	@Column(name = "state")
	private String state;
	/**
	 * 城市
	 */
	@Column(name = "city")
	private String city;
	/**
	 * 用于电子合同生成的公司名称。
	 * 有的公司是合伙人模式或加盟模式，每个店面生成出来的公司名称不一样，所以需要在这里填写公司名称。
	 * 如果你的所有店面生成的公司名称都一样，则可不用填写。
	 */
	@Column(name = "other_company_name")
	private String otherCompanyName;
	/**
	 * 合租房间数
	 */
	@Column(name = "cotenant")
	private Long cotenant;
	/**
	 * 整租房间数
	 */
	@Column(name = "housing")
	private Long housing;
	/**
	 * 集中房间数
	 */
	@Column(name = "focus")
	private Long focus;
	/**
	 * 分组
	 */
	@Column(name = "grouping")
	private String grouping;
	/**
	 * 0店面分组，1店面未分组
	 */
	@Column(name = "grouping_status")
	private byte groupingStatus;
	/**
	 * 地图中定位的地址
	 */
	@Column(name = "localize_add")
	private String localizeAdd;
	/**
	 * 定位数据
	 */
	@Column(name = "localize")
	private String localize;
	/**
     * 删除状态
     */
	@Column(name = "is_delete")
    private Integer IsDelete;
}
