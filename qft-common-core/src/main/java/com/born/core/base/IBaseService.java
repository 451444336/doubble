package com.born.core.base;

import java.util.List;

import com.born.core.page.PageBean;
import com.born.util.result.Result;

/**
 * 
 * @ClassName: IBaseService
 * @Description: 基础服务针对常用的curd
 * @author: lijie
 * @date: 2018年5月26日 下午4:23:29
 */
public interface IBaseService<T extends BaseModel> {
	/**
	 * 
	* @Title: updateByModel  
	* @Description: 根据实体修改
	* @param: @param record
	* @return void
	* @author lijie
	* @throws
	 */
	Result updateByModel(T model);
	/**
	 * 
	* @Title: delByIds  
	* @Description: 根据多个id删除数据（逻辑删除） 
	* @param: @param ids
	* @param: @param userId
	* @param: @param isActual
	* @param: @return
	* @return Result
	* @author lijie
	* @throws
	 */
	Result delByIds(List<Long> ids, Long userId);
	/**
	 * 
	* @Title: delByIds  
	* @Description: 物理删除 
	* @param: @param ids
	* @param: @return
	* @return Result
	* @author lijie
	* @throws
	 */
	Result delByIds(List<Long> ids);
	/**
	 * 
	* @Title: delById  
	* @Description: 根据ID删除数据 （逻辑） 
	* @param: @param id
	* @param: @param userId
	* @return void
	* @author lijie
	* @throws
	 */
	Result delById(Long id, Long userId);
	/**
	 * 
	* @Title: delById  
	* @Description: 物理删除 
	* @param: @param id
	* @param: @return
	* @return Result
	* @author lijie
	* @throws
	 */
	Result delById(Long id);
	/**
	 * 
	* @Title: add  
	* @Description: 新增记录 
	* @param: @param record
	* @param: @return
	* @return Result
	* @author lijie
	* @throws
	 */
	Result addByModel(T record);
	/**
	 * 
	* @Title: addByList  
	* @Description: 批量新增 
	* @param: @param record
	* @param: @return
	* @return Result
	* @author lijie
	* @throws
	 */
	Result addByList(List<T> record);
	/**
	 * 
	* @Title: getById  
	* @Description: 根据ID查询 
	* @param: @param id
	* @param: @return
	* @return Result
	* @author lijie
	* @throws
	 */
	Result getById(Long id);
	/**
	 * 
	* @Title: queryList  
	* @Description: 根据多个ID 查询数据 
	* @param: @param ids
	* @param: @return
	* @return Result
	* @author lijie
	* @throws
	 */
	Result getByIdsList(List<Long> ids);
	/**
	 * 
	* @Title: getByEntityList  
	* @Description: 根据实体查询列表 
	* @param: @param model
	* @param: @return
	* @return Result
	* @author lijie
	* @throws
	 */
	Result getByModelList(T model);
	/**
	 * 
	* @Title: getByEntityOne  
	* @Description: 根据实体查询单个记录 
	* @param: @param model
	* @param: @return
	* @return Result
	* @author lijie
	* @throws
	 */
	Result getByModelOne(T model);
	/**
	 * 
	* @Title: getAll 
	* @Description: 查询所有 
	* @param @return    设定文件 
	* @return Result    返回类型 
	* @author lijie
	* @throws
	 */
	Result getAll();
	/**
	 * 
	* @Title: getListByPage 
	* @Description: 分页查询数据 
	* @param @param model
	* @param @return    设定文件 
	* @return Result    返回类型 
	* @author lijie
	* @throws
	 */
	Result getListByPage(T model, PageBean pageBean);
}
