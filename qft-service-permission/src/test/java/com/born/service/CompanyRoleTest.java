/*package com.born.service;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.born.facade.dto.CompanyRoleDTO;
import com.born.facade.vo.CompanyRoleVO;
import com.born.service.impl.CompanyRoleServiceImpl;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;

*//**
 * @Description: 角色单元测试类
 * @author wangxy
 * @date 2018年4月27日 下午4:33:13
 *//*
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyRoleTest {

	@Autowired
	private CompanyRoleServiceImpl companyRoleServiceImpl;
	
	
	*//**
	 * @Description 新增角色
	 * @author wangxy
	 * @date 2018年5月2日 上午8:59:08
	 * @throws Exception
	 *//*
	@Test
	public void insert() throws Exception {
		CompanyRoleDTO dto = new CompanyRoleDTO();
		dto.setRoleName("管理员");
		dto.setIsAuthEdit(1);
		// 设置默认值
		dto.setCreateTime(new Date());
		dto.setCreaterId(1L);
		dto.setIsValid(1);
		dto.setIsDelete(0);
		companyRoleServiceImpl.insert(dto);
		log.info("CompanyRoleTest...insert()...执行成功!");
	}
	*//**
	 * @Description 删除角色
	 * @author wangxy
	 * @date 2018年5月2日 上午8:59:28
	 * @throws Exception
	 *//*
	@Test
	public void delete() throws Exception {
		companyRoleServiceImpl.deleteById(8L);
		log.info("CompanyRoleTest...delete()...执行成功!");
	}
	
	*//**
	 * @Description 修改角色
	 * @author wangxy
	 * @date 2018年5月2日 上午9:07:11
	 * @throws Exception
	 *//*
	@Test
	public void update() throws Exception {
		// 查询角色
		CompanyRoleDTO dto = new CompanyRoleDTO();
		dto.setId(9L);
		dto.setRoleName("管理员1");
		dto.setUpdateTime(new Date());
		companyRoleServiceImpl.update(dto);
		log.info("CompanyRoleTest...update()...执行成功!");
	}
	
	*//**
	 * @Description 绑定角色菜单
	 * @author wangxy
	 * @date 2018年5月2日 上午9:10:17
	 * @throws Exception
	 *//*
	@Test
	public void bindRoleMenu() throws Exception{
		Long[] menuIds = {1L,2L,3L};
		Long roleId = 9L;
		companyRoleServiceImpl.bindRoleMenu(menuIds, roleId, 1L ,new Date());
		log.info("CompanyRoleTest...bindRoleMenu()...执行成功!");
	}
	
	*//**
	 * @Description 角色列表分页查询
	 * @author wangxy
	 * @date 2018年5月2日 上午9:10:17
	 * @throws Exception
	 *//*
	@Test
	public void getPageList() throws Exception{
		CompanyRoleDTO dto = new CompanyRoleDTO();
		dto.setRoleName("管理");
		dto.setPageNum(1);
		dto.setPageSize(2);
		PageInfo<CompanyRoleVO> pageList = companyRoleServiceImpl.getPageList(dto);
		System.out.println(pageList.getList().size());
		log.info("CompanyRoleTest...getPageList()...执行成功!");
	}
}
*/