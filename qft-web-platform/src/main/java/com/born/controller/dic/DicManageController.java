package com.born.controller.dic;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.born.config.shiro.token.TokenManager;
import com.born.core.constant.DataBaseEnum;
import com.born.facade.dto.dic.AddDicTypeDTO;
import com.born.facade.dto.dic.UpdateDicTypeDTO;
import com.born.facade.service.dic.IDicTypeService;
import com.born.util.result.Result;

import lombok.extern.slf4j.Slf4j;
/**
 * 
* @ClassName: DicManageController 
* @Description: 字典管理控制
* @author lijie 
* @date 2018年5月31日 上午11:41:07 
*
 */
@Slf4j
@Controller
@RequestMapping(value = "web/dic/manage")
public class DicManageController {

	/**
	 * 字典类型接口
	 */
	@Reference(version = "1.0.0")
	private IDicTypeService dicTypeService;
	/**
	 * 
	* @Title: addDicType 
	* @Description: 添加字典类型 
	* @param @param dto
	* @param @return    设定文件 
	* @return Result    返回类型 
	* @author lijie
	* @throws
	 */
	@PostMapping("type/add")
	public @ResponseBody Result addDicType(AddDicTypeDTO dto) {
		dto.setCreaterId(TokenManager.getLoginUser().getId());
		dto.setCreateTime(new Date());
		dto.setIsDelete(DataBaseEnum.NOT_DELETE.getStatus());
		Result result = dicTypeService.addByModel(dto);
		log.info("添加字典类型返回数据={}", JSON.toJSONString(result));
		return result;
	}
	/**
	 * 
	* @Title: updateDicType 
	* @Description: 修改字典类型
	* @param @param dto
	* @param @return    设定文件 
	* @return Result    返回类型 
	* @author lijie
	* @throws
	 */
	@PostMapping("type/update")
	public @ResponseBody Result updateDicType(UpdateDicTypeDTO dto) {
		dto.setUpdaterId(TokenManager.getLoginUser().getId());
		dto.setUpdateTime(new Date());
		Result result = dicTypeService.updateByModel(dto);
		log.info("修改字典类型返回数据={}", JSON.toJSONString(result));
		return result;
	}
	/**
	 * 
	* @Title: delDicType 
	* @Description: 删除字典类型
	* @param @param dto
	* @param @return    设定文件 
	* @return Result    返回类型 
	* @author lijie
	* @throws
	 */
	@PostMapping("type/del")
	public @ResponseBody Result delDicType(Long id) {
		Result result = dicTypeService.delById(id, TokenManager.getLoginUser().getId());
		log.info("删除字典类型返回数据={}", JSON.toJSONString(result));
		return result;
	}
	/**
	 * 
	* @Title: getDicMenus 
	* @Description:查询字典菜单数据
	* @param @return    设定文件 
	* @return Result    返回类型 
	* @author lijie
	* @throws
	 */
	@GetMapping("getDicMenus")
	public @ResponseBody Result getDicMenus() {

		return null;
	}
}
