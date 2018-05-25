package com.born.entity.notice;

import javax.persistence.Column;
import javax.persistence.Table;

import com.born.core.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
* @ClassName: Notice 
* @Description: 公告管理
* @author 明成 
* @date 2018年5月24日 上午11:17:41 
* @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "qft_notice")
public class Notice extends BaseEntity<Notice> {


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
	 * 标题
	 */
	@Column(name = "title")
	private String title;
	/**
	 * 发布时间
	 */
	@Column(name = "time")
	private String time;
	/**
	 * 性质
	 */
	@Column(name = "nature")
	private String nature;
	/**
	 * 内容
	 */
	@Column(name = "content")
	private String content;
	/**
	 * 类型
	 */
	@Column(name = "type")
	private String type;
	/**
	 * 发布人
	 */
	@Column(name = "login_name")
	private String logiName;
	/**
	 * 是否关闭
	 */
	@Column(name = "isclose")
	private String isClose;
	/**
	 *
	 */
	@Column(name = "img_url")
	private String imgUrl;
	/**
	 * 
	 */
	@Column(name = "isphoto")
	private String isPhoto;
	/**
	 * 
	 */
	@Column(name = "tag")
	private String tag;
	/**
	 * 
	 */
	@Column(name = "isupdate")
	private String isUpdate;
	/**
	 * 
	 */
	@Column(name = "file_url")
	private String fileUrl;

	@Column(name = "file_key")
	private String fileKey;
}
