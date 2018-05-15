package com.born.facade.dto;

import java.util.Date;

import com.born.core.page.PageBean;

import lombok.Data;

/**
 * @Description: 操作日志记录dto
 * @author 黄伟
 * @date 2018年5月14日 下午4:21:19
 */
@Data
public class OperateLogRecordDTO extends PageBean {


	/**
	 * 
	 */
	private static final long serialVersionUID = -2464067513481898899L;
	/**
	 * 主键ID
	 */
	private Long id;
	/**
     * 创建人ID
     */
    private Long createrId;
	 /**
     * 创建时间
     */
    private Date createTime;
    /**
	 * 公司ID
	 */
	private String companyId;
	/**
	 * 操作
	 */
	private String operate;
	
}
