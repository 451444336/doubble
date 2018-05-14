/*package com.born.service;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.born.facade.dto.CompanyPositionDTO;
import com.born.service.impl.CompanyPositionServiceImpl;

import lombok.extern.slf4j.Slf4j;

*//**
 * @Description: 职位单元测试类
 * @author 明成
 * @date 2018年4月27日 下午4:33:13
 *//*
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyPositionTest {

	@Autowired
	private CompanyPositionServiceImpl serviceImpl;
	
	*//**
	 * @Description 根据ID查询职位
	 * @author 明成
	 * @date 2018年5月2日 上午8:59:08
	 * @throws Exception
	 *//*
	@Test
	public void selectPositionById() throws Exception{
		serviceImpl.findPosition(1L);//执行方法 ID
		log.info("PositionTest...selectPositionById()...执行成功!");
	}
	
	*//**
	 * @Description 查询职位
	 * @author 明成
	 * @date 2018年5月2日 上午8:59:08
	 * @throws Exception
	 *//*
	@Test
	public void selectPosition() throws Exception{
		//查询条件
		CompanyPositionDTO dto = new CompanyPositionDTO();//职位实体
		dto.setName("职位1");//职位名称
		dto.setId(1l);//条件ID
		dto.setDeptId(1L);
		serviceImpl.findPositionList(dto);//执行方法
		log.info("PositionTest...selectPosition()...执行成功!");
	}
	
	*//**
	 * @Description 新增职位
	 * @author 明成
	 * @date 2018年5月2日 上午8:59:08
	 * @throws Exception
	 *//*
	@Test
	public void insert() throws Exception{
		CompanyPositionDTO dto = new CompanyPositionDTO();//职位实体
		dto.setName("职位1");//职位名称
		dto.setDeptId(1L);//部门ID
		serviceImpl.addPosition(dto);//执行方法
		log.info("PositionTest...insert()...执行成功!");
	}
	*//**
	 * @Description 删除职位(可批量删除)
	 * @author 明成
	 * @date 2018年5月2日 上午8:59:28
	 * @throws Exception
	 *//*
	@Test
	public void delete() throws Exception{
		serviceImpl.deletePosition("1,2");//string ids "1,2"
		log.info("PositionTest...delete()...执行成功!");
	}
	
	*//**
	 * @Description 修改职位
	 * @author 明成
	 * @date 2018年5月2日 上午9:07:11
	 * @throws Exception
	 *//*
	@Test
	public void update() throws Exception{
		CompanyPositionDTO dto = new CompanyPositionDTO();
		dto.setId(1L);
		dto.setName("职位2");//职位名称
		dto.setUpdaterId(1L);
		dto.setUpdateTime(new Date());
		dto.setDeptId(2L);
		serviceImpl.updatePosition(dto);//执行修改
		log.info("PositionTest...update()...执行成功!");
	}
	
}
*/