package com.born.controller.dic;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.born.config.shiro.token.TokenManager;
import com.born.core.annotation.RepeatToken;
import com.born.core.constant.DataBaseEnum;
import com.born.core.page.PageBean;
import com.born.facade.dto.dic.AddDicTypeDTO;
import com.born.facade.dto.dic.AddModelDicItemDTO;
import com.born.facade.dto.dic.QueryDicDTO;
import com.born.facade.dto.dic.UpdateDicTypeDTO;
import com.born.facade.dto.dic.UpdateModelDicItemDTO;
import com.born.facade.service.dic.IDicService;
import com.born.facade.service.dic.IDicTypeService;
import com.born.facade.vo.UserInfoVO;
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
	 * 字典项数据
	 */
	@Reference(version = "1.0.0")
	private IDicService dicService;
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
	@RepeatToken(key = "menuDicId")
	@PostMapping("type/add")
	public @ResponseBody Result addDicType(HttpServletRequest request, AddDicTypeDTO dto) {
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
		
		return dicService.getDicMenus(TokenManager.getLoginUser().getCompanyId());
	}
	/**
	 * 
	* @Title: getDicListByPage 
	* @Description: 根据分页信息查询字典列表数据 
	* @param @param dto
	* @param @return    设定文件 
	* @return Result    返回类型 
	* @author lijie
	* @throws
	 */
	@GetMapping("getDicListByPage")
	public @ResponseBody Result getDicListByPage(PageBean pageBean, Byte type) {
		QueryDicDTO dto = new QueryDicDTO();
		dto.setCompanyId(TokenManager.getLoginUser().getCompanyId());
		dto.setIsDelete(DataBaseEnum.NOT_DELETE.getStatus());
		Result result = dicTypeService.getListByPage(dto, pageBean);
		log.info("分页查询字典列表返回数据 = {}", JSON.toJSONString(result));
		return result;
	}
	/**
	 * 
	* @Title: addDicItem 
	* @Description:添加模板公司字典项
	* @param @param dto
	* @param @return    设定文件 
	* @return Result    返回类型 
	* @author lijie
	* @throws
	 */
	@RepeatToken(key = "menuDicId")
	@PostMapping("add")
	public @ResponseBody Result addDicItem(AddModelDicItemDTO dto) {
		UserInfoVO info = TokenManager.getLoginUser();
		dto.setCompanyId(info.getCompanyId());
		dto.setCreaterId(info.getId());
		dto.setCreateTime(new Date());
		dto.setIsDelete(DataBaseEnum.NOT_DELETE.getStatus());
		Result result = dicService.addByModel(dto);
		log.info("添加模板公司字典项返回 = {}", JSON.toJSONString(result));
		return result;
	}
	
	/**
	 * 
	* @Title: updateDicItem 
	* @Description: 修改模板公司字典项
	* @param @param dto
	* @param @return    设定文件 
	* @return Result    返回类型 
	* @author lijie
	* @throws
	 */
	@PostMapping("update")
	public @ResponseBody Result updateDicItem(UpdateModelDicItemDTO dto) {
		dto.setUpdaterId(TokenManager.getLoginUser().getId());
		dto.setUpdateTime(new Date());
		Result result = dicService.addByModel(dto);
		log.info("修改模板公司字典项项返回 = {}", JSON.toJSONString(result));
		return result;
	}
	/**
	 * 
	* @Title: delDic 
	* @Description: 删除字典数据
	* @param @param id
	* @param @return    设定文件 
	* @return Result    返回类型 
	* @author lijie
	* @throws
	 */
	@PostMapping("del")
	public @ResponseBody Result delDic(Long id) {
		Result result = dicService.delByIdAndIsPossible(id, TokenManager.getLoginUser().getId());
		log.info("删除字典返回数据={}", JSON.toJSONString(result));
		return result;
	}
}
