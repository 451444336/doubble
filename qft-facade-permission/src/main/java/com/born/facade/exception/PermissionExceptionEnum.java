package com.born.facade.exception;

import com.born.util.result.RespCode;

import lombok.AllArgsConstructor;
import lombok.Getter;
/**
 * 
* @ClassName: PermissionExceptionEnum 
* @Description: 权限异常枚举 
* @author lijie 
* @date 2018年4月27日 上午9:30:49 
*
 */
@Getter
@AllArgsConstructor
public enum PermissionExceptionEnum implements RespCode {
	
	UNKNOWN_ACCOUNT("10011", "未知账户"),
    INCORRECT_CREDENTIALS("10012", "密码不正确"),
    LOCKED_ACCOUNT("10013", "账户已锁定"),
    EXCESSIVE_ATTEMPTS("10014", "用户名或密码错误次数过多"),
    AUTHENTICATION("10015", "用户名或密码不正确"),
    NO_ROLE("10016", "该用户还没有角色，请先分配角色"),
    NO_PASS("10017", "未通过审核，不能重置密码"),
	ADD_PERMISSION_ERROR("10018","添加权限异常"),
	ADD_MENU_ERROR("10019","添加菜单异常"),
	ADD_DEPT_ERROR("10020","添加部门失败"),
	UPDATE_DEPT_ERROR("10021","修改部门失败"),
	DETELE_DEPT_ERROR("10022","删除部门失败"),
	ADD_POSITION_ERROR("10023","添加职位失败"),
	UPDATE_POSITION_ERROR("10024","修改职位失败"),
	DETELE_POSITION_ERROR("10025","删除职位失败"),
	ADD_STAFF_ERROR("10026","添加员工失败"),
	UPDATE_STAFF_ERROR("10027","修改员工失败"),
	DETELE_STAFF_ERROR("10028","删除员工失败"),
	USER_LOGOUT("10029","用户已被注销"),
	CHANGE_STAFF_OPER("10030","改变用户操作信息异常"),
	USER_DATA_ERROR("10031","员工信息异常"),
	UPDATE_PERMISSION_ERROR("10032","编辑权限异常"),
	PERMISSION_NOT_EXISTS("10033","权限数据不存在拟"),
	ADD_PERSONAL_PERMISSION("10034","新增个人权限数据异常"),
	USER_NON_EXISTENT("10035","账号不存在"),
	ADD_ROLE_ERROE("10036","添加角色异常"),
	UPDATE_ROLE_ERROE("10036","修改角色异常"),
	DELETE_ROLE_ERROE("10036","删除角色异常"),
	BIND_ROLE_MENU_ERROE("10036","给角色绑定菜单异常");
	private String code;
    private String msg;

}
