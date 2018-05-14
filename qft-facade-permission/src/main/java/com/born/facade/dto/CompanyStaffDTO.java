package com.born.facade.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.Min;

import com.born.core.page.PageBean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 员工DTO
 * @author 明成
 *
 */
@Data
@ApiModel(value="员工管理请求实体")
public class CompanyStaffDTO extends PageBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Min(value=0)
	@ApiModelProperty(value="主键",name="id",required=true)
	private Long id;
	
	@ApiModelProperty(value="联系方式",name="phone")
	private String phone;
	
	@ApiModelProperty(value="备注",name="note")
	private String note;
	
	@ApiModelProperty(value="店面",name="store")
	private String store;
	
	@ApiModelProperty(value="真实姓名",name="realname")
	private String realname;
	
	@ApiModelProperty(value="身份证号",name="idcard")
	private String idcard;
	
	@ApiModelProperty(value="公司ID",name="companyId")
	private String companyId;
	
	@ApiModelProperty(value="工作店面",name="workstore")
	private String workstore;
	
	@ApiModelProperty(value="打卡规则ID",name="attendanceId")
	private String attendanceId;
	
	@ApiModelProperty(value="工作的组ID",name="grouping")
	private String grouping;
	
	@ApiModelProperty(value="可看组id",name="storegrouping")
	private String storegrouping;
	
	@ApiModelProperty(value="城市ID",name="cityId")
	private String cityId;
	
	@ApiModelProperty(value="可看房源的城市",name="cityList")
	private String cityList;
	
	@ApiModelProperty(value="用户ID",name="userId")
	private Long userId;
	
	@ApiModelProperty(value="创建时间",name="createTime")
	private Date createTime;
	
	@ApiModelProperty(value="修改时间",name="updateTime")
	private Date updateTime;
	
	@ApiModelProperty(value="创建人",name="createrId")
	private Long createrId;
	
	@ApiModelProperty(value="修改人",name="updaterId")
	private Long updaterId;
	
	@ApiModelProperty(value="删除状态",name="IsDelete")
	private Integer IsDelete;
	
	@ApiModelProperty(value="职务ID",name="positionId")
	private Long positionId;
	
	@ApiModelProperty(value="App登录",name="isEnableApp")
	private Integer isEnableApp;
	
	/**
	 * 账号
	 */
	private String account;
	
	/**
	 * 是否APP通知
	 */
	private Integer isAppNotice;

	/**
	 * 是否Web端通知
	 */
	private Integer isWebNotice;
	
	/**
	 * 密码
	 */
	private String password;
}
