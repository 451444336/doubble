/*package com.born.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.born.facade.dto.CompanyDeptDTO;
import com.born.service.impl.CompanyDeptServiceImpl;

import lombok.extern.slf4j.Slf4j;

*//**
 * @Description: 部门单元测试类
 * @author 明成
 * @date 2018年4月27日 下午4:33:13
 *//*
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyDeptTest {

	@Autowired
	private CompanyDeptServiceImpl deptServiceImpl;
	
	*//**
	 * @Description 根据ID查询部门
	 * @author 明成
	 * @date 2018年5月2日 上午8:59:08
	 * @throws Exception
	 *//*
	@Test
	public void selectDeptById() throws Exception{
		deptServiceImpl.findDept(1L);//执行方法 ID
		log.info("DeptTest...selectDeptById()...执行成功!");
	}
	
	*//**
	 * @Description 查询部门
	 * @author 明成
	 * @date 2018年5月2日 上午8:59:08
	 * @throws Exception
	 *//*
	@Test
	public void selectDept() throws Exception{
		//查询条件
		CompanyDeptDTO dept = new CompanyDeptDTO();//部门实体
		dept.setCompanyId("jdh1547chw44514");//公司ID
		dept.setDeptName("部门1");//部门名称
		dept.setParentId(1L);//部门父级ID
		dept.setId(1l);//条件ID
		deptServiceImpl.findDeptList(dept);//执行方法
		log.info("DeptTest...selectDept()...执行成功!");
	}
	
	*//**
	 * @Description 新增部门
	 * @author 明成
	 * @date 2018年5月2日 上午8:59:08
	 * @throws Exception
	 *//*
	@Test
	public void insert() throws Exception{
		CompanyDeptDTO dept = new CompanyDeptDTO();//部门实体
		dept.setCompanyId("1");//公司ID
		dept.setDeptName("部门1");//部门名称
		dept.setParentId(1L);//部门父级ID
		deptServiceImpl.addDept(dept);//执行方法
		log.info("DeptTest...insert()...执行成功!");
	}
	*//**
	 * @Description 删除部门(可批量删除)
	 * @author 明成
	 * @date 2018年5月2日 上午8:59:28
	 * @throws Exception
	 *//*
	@Test
	public void delete() throws Exception{
		deptServiceImpl.deleteDept("1,2");//string ids "1,2"
		log.info("DeptTest...delete()...执行成功!");
	}
	
	*//**
	 * @Description 修改角色
	 * @author 明成
	 * @date 2018年5月2日 上午9:07:11
	 * @throws Exception
	 *//*
	@Test
	public void update() throws Exception{
		CompanyDeptDTO dto = new CompanyDeptDTO();
		dto.setId(1L);
		dto.setCompanyId("sfsafs23421");//公司ID
		dto.setDeptName("部门2");//部门名称
		dto.setParentId(1L);//父级ID
		deptServiceImpl.updateDept(dto);//执行修改
		log.info("DeptTest...update()...执行成功!");
	}
	
}
*/