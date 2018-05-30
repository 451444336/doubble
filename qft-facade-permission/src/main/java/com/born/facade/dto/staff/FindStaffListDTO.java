package com.born.facade.dto.staff;

import java.util.List;

import com.born.core.page.PageBean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 员工DTO
 * @author 明成
 *
 */
@Data
@ApiModel(value="员工管理请求实体")
@EqualsAndHashCode(callSuper = true)
public class FindStaffListDTO extends PageBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="真实姓名",name="realname")
	private String realname;
	
	@ApiModelProperty(value="工作店面",name="workstore")
	private String workstore;
	
	@ApiModelProperty(value="工作的组ID",name="grouping")
	private String grouping;
	
	@ApiModelProperty(value="城市ID",name="cityId")
	private String cityId;
	
	@ApiModelProperty(value="职务ID",name="positionId")
	private Long positionId;
	//公司ID
	private Long companyId;
	//权限IDs
	private List<Long> permissionIds;
	
	private Long userId;
	
	//状态 正常 关闭
	private String status;
	
	//工作分组
	private String attendanceId;
	
	//系统锁定
	private String sysStatus;
	//菜单权限ID
	private Long[] meunIds;
	
	//身份证号
	private String idcard;
	//账号
	private String account;
	//联系方式
	private String phone;
}


