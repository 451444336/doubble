package com.born.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.born.facade.dto.dept.CompanyDeptDTO;
import com.born.facade.entity.CompanyDept;
import com.born.facade.exception.PermissionException;
import com.born.facade.exception.PermissionExceptionEnum;
import com.born.facade.service.ICompanyDeptService;
import com.born.facade.vo.companyDept.CompanyDeptVO;
import com.born.mapper.CompanyDeptMapper;
import com.born.util.result.RespCode;
import com.born.util.result.Result;
import com.born.util.result.ResultUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;
/**
 * 
* @ClassName: CompanyDeptServiceImpl  
* @Description: 部门实现
* @author AlanMing
* @date 2018年4月27日  
*
 */
@Slf4j
@Service(version = "1.0.0")
public class CompanyDeptServiceImpl implements ICompanyDeptService {

	@Autowired
	private CompanyDeptMapper deptMapper;

	
	/**
	 * 
	* @Title: findOrgList 
	* @Description: 获取组织架构
	* @param @param companyId
	* @param @return
	* @author 明成
	* @return Result
	* @date 2018年5月8日 下午5:39:59 
	* @throws
	 */
	@Override
	public Result findOrgList(String companyId) {
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		//校验参数
		if(!StringUtils.isNotBlank(companyId)) {
				result.setMessage("公司ID不能为空");
				return result;
		}
		try {
			//自己看sql 根据公司id 获取它下面的组织架构树
			 List<CompanyDeptVO> voList  = deptMapper.selectOrgList(companyId);
			 //点击组织架构 跳转地址
			 for(CompanyDeptVO vo :voList) {
				 String id = vo.getId();
				 if(StringUtils.isNotBlank(id)) {
					 String[] urlArray = id.split("-");
					 //如果这个树层是公司
					 if(urlArray[0].equals("company")) {
						 //链接为根据公司id获取所有部门
						 vo.setUrl("/web/dept/index/"+urlArray[1]);
						 vo.setIcon("/static/js/ztree/img/user_group.png");
					 }
					 //如果这个树层是部门
					 if(urlArray[0].equals("dept")) {
						 //链接为根据部门id获取所有职位
						 vo.setUrl("/web/pos/index/"+urlArray[1]);
						 vo.setIcon("/static/js/ztree/img/user_group.png");
					 }
					 //如果这个树层是职位
					 if(urlArray[0].equals("position")) {
						 //链接为根据职位id获取所有员工
						 vo.setUrl("/web/staff/index/"+urlArray[1]);
						 //先写死
						 vo.setIcon("/static/js/ztree/img/user_worker.png");
					 }
				 }
			 }
			ResultUtil.setResult(result, RespCode.Code.SUCCESS,voList);
		} catch (Exception e) {
			log.error("查询组织架构失败（DeptServiceImpl.selectOrgList）.......................",e);
		}
		return result;
	}
	
	/**
	 * 获取部门
	 */
	@Override
	public Result findDept(Long id) {
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		//校验参数
		if(null==id) {
			result.setMessage("主键不能为空");
			return result;
		}
		try {
			//声明对象
			CompanyDept dept = new CompanyDept();
			dept.setId(id);
			ResultUtil.setResult(result, RespCode.Code.SUCCESS,deptMapper.selectOne(dept));
		} catch (Exception e) {
			log.error("查询部门失败(DeptServiceImpl.findDept).......................",e);
		}
		return result;
	}

	/**
	 * 获取部门集合
	 */
	@Override
	public Result findDeptList(CompanyDeptDTO dto) {
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		//校验参数
		if(dto!=null) {
			String errorStr = dto.validateForm();
			if(StringUtils.isNotBlank(errorStr)) {
				result.setMessage(errorStr);
				return result;
			}
		}
		try {
			ResultUtil.setResult(result, RespCode.Code.SUCCESS,deptMapper.selectDeptList(dto));
		} catch (Exception e) {
			log.error("查询部门失败（DeptServiceImpl.findDeptList）.......................",e);
		}
		return result;
	}
	
	/**
	 * 修改部门
	 */
	@Override
	@Transactional
	public Result updateDept(CompanyDeptDTO dto) {
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		//校验参数
		if(dto==null) {
			result.setMessage("传入对象不能为空");
			return result;
		}
		CompanyDept dept = new CompanyDept();
		String errorStr = dto.validateForm();
		if(StringUtils.isNotBlank(errorStr)) {
			result.setMessage(errorStr);
			return result;
		}
		//转换实体
		BeanUtils.copyProperties(dto, dept);
		//设置时间
		dept.setUpdateTime(new Date());
		dept.setUpdaterId(dto.getUserId());
		try {
			return ResultUtil.setResult(result, RespCode.Code.SUCCESS,deptMapper.updateByPrimaryKeySelective(dept));
		} catch (Exception e) {
			log.error("修改部门失败（DeptServiceImpl.updateDept）",e);
			throw new PermissionException(PermissionExceptionEnum.UPDATE_DEPT_ERROR);
		}
	}

	/**
	 * 添加部门
	 */
	@Override
	@Transactional
	public Result addDept(CompanyDeptDTO dto) {
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		//校验参数
		if(dto==null) {
			result.setMessage("传入对象不能为空");
			return result;
		}
		if(dto.getId()!=null) {
			result.setMessage("主键参数非法");
			return result;
		}
		CompanyDept dept = new CompanyDept();
		String errorStr = dto.validateForm();
		if(StringUtils.isNotBlank(errorStr)) {
			result.setMessage(errorStr);
			return result;
		}
		//转换实体
		BeanUtils.copyProperties(dto, dept);
		//设置时间
		dept.setCreateTime(new Date());
		dept.setCreaterId(dto.getUserId());
		dept.setId(null);
		try {
			return ResultUtil.setResult(result,RespCode.Code.SUCCESS, deptMapper.insertSelective(dept));
		} catch (Exception e) {
			log.error("添加部门失败（DeptServiceImpl.addDept）", e);
			throw new PermissionException(PermissionExceptionEnum.ADD_DEPT_ERROR);
		}
	}

	/**
	 * 逻辑删除部门
	 */
	@Override
	@Transactional
	public Result deleteDept(CompanyDeptDTO dto) {
		//校验参数
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		if(dto==null||!StringUtils.isNotBlank(dto.getIds())) {
			result.setMessage("传入参数不能为空");
			return result;
		}
		//修改属性值添加
		String[] idsStr = dto.getIds().split(",");
		dto.setUpdateTime(new Date());
		dto.setUpdaterId(dto.getUserId());
		try {
			//执行修改
			deptMapper.batchUpdateDept(dto,idsStr);
			//返回消息
			return ResultUtil.setResult(result, RespCode.Code.SUCCESS);
		} catch (Exception e) {
			log.error("删除部门失败（DeptServiceImpl.deleteDept）",e);
			throw new PermissionException(PermissionExceptionEnum.DETELE_DEPT_ERROR);
		}
	}
	@Override
	public Result getPageList(CompanyDeptDTO dto) {
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		try {
		 	PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
	        List<CompanyDeptVO> list = deptMapper.selectDeptList(dto);
	        PageInfo<CompanyDeptVO> pageInfo = new PageInfo<>(list);
	        result.setData(pageInfo.getList());
			result.setCount(pageInfo.getTotal());
			return ResultUtil.setResult(result, RespCode.Code.SUCCESS);
		} catch (Exception e) {
			log.error("查询分页数据异常", e);
		}
        return result;
	}
	
	/**
	 * 编辑部门
	 */
	@Override
	public Result editDept(CompanyDeptDTO dto) {
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		//校验参数
		if(dto==null) {
			result.setMessage("传入对象不能为空");
			return result;
		}
		//如果无ID表示为添加
		if(dto.getId()!=null) {
			return updateDept(dto);
		}
		//如果有ID表示为修改
		if(dto.getId()==null) {
			return addDept(dto);
		}
		return result;
	}
}
