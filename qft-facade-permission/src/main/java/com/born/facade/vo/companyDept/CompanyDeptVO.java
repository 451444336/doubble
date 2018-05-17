package com.born.facade.vo.companyDept;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * @Description: 部门vo
 * @author 明成
 * @date 2018年4月27日 下午2:15:21
 */
@Data
public class CompanyDeptVO{
	
	private String id;
	
	private String name;//部门名称
	
	@JsonProperty("pId")
	private String pId;//上级部门ID
	
	private String url;
	
	private String icon;
	
	//是否展开 true close
	private String open = "true";
	
	private String target = "roleRight";
	
	private String createTime;
	
	private String countId;
}
