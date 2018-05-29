package com.born.core.page;

import com.born.core.base.BaseValidate;
import com.born.core.constant.PageConstants;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 
* @ClassName: PageBean 
* @Description: 分页信息 
* @author lijie 
* @date 2018年5月3日 下午3:40:23 
*
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PageBean extends BaseValidate<PageBean> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2985524102576468583L;
	/**
	 * 当前页数
	 */
	@ApiModelProperty(value = "当前页数(默认为1)")
	private Integer pageNum;
	/**
	 * 每页条数
	 */
	@ApiModelProperty(value = "每页条数(默认为20)")
	private Integer pageSize;

	public PageBean(int pageNum, int pageSize) {
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}

	public PageBean() {
		this.pageNum = PageConstants.PAGE_NUM;
		this.pageSize = PageConstants.PAGE_SIZE;
	}
}
