package com.born.facade.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * @Description: 员工vo
 * @author 明成
 * @date 2018年4月27日 下午2:15:21
 */
@Data
public class CompanyStaffVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Long id;
	//联系方式
	private String phone;
	
	//备注
	private String note;
	
	//店面
	private String store;
	
	//真实姓名
	private String realname;
	
	//身份证号
	private String idcard;
	
	//公司ID
	private String companyId;
	
	//工作店面
	private String workstore;
	
	//打卡规则ID
	private String attendanceId;
	
	//工作的组ID
	private String grouping;
	
	//可看组id
	private String storegrouping;
	
	//城市ID
	private String cityId;
	
	//可看房源的城市
	private String cityList;
	
	//用户ID
	private Long userId;
	
	//账号
	private String account;
	
	//密码
	private String password;
	
	//App登录
	private Integer isEnableApp;
	
	//系统锁定状态
	private Integer status;
	
	//职务ID
	private Long positionId;
	
	//职务名称
	private String positionName;
	
	//上次登录时间
	private String lastTime;
	
	//创建时间
	private String createTime;
	
	//部门名称
	private String deptName;
	
	//部门ID
	private String deptId;
	
	//系统锁定
	private String sysStatus;
}
