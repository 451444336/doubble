package com.born.facade.dto.notice;

import java.util.Date;

import com.born.core.page.PageBean;

import lombok.Data;

/**
 * 
* @ClassName: NoticeDTO 
* @Description: 公告条件参数 
* @author 明成 
* @date 2018年5月24日 上午11:31:42 
* @version 1.0
 */
@Data
public class NoticeDTO extends PageBean {


	private static final long serialVersionUID = 5225319627305280537L;
	/**
	 * 主键id
	 */
	private Long id;

	/**
	 * 修改时间
	 */
	private Date updateTime;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 创建人ID
	 */
	private Long createrId;

	/**
	 * 修改人ID
	 */
	private Long updaterId;
	
	/**
	 * 公司ID
	 */
	private Long companyId;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 发布时间
	 */
	private String time;
	/**
	 * 性质
	 */
	private String nature;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 发布人
	 */
	private String login_name;
	/**
	 * 是否关闭
	 */
	private String isClose;
	/**
	 *
	 */
	private String imgurl;
	/**
	 * 
	 */
	private String isphoto;
	/**
	 * 
	 */
	private String tag;
	/**
	 * 
	 */
	private String isupdate;
	/**
	 * 
	 */
	private String fileUrl;

	private String fileKey;
}
