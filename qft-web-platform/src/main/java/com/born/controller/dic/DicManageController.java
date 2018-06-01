package com.born.controller.dic;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.born.facade.dto.dic.QueryDicTypeDTO;
import com.born.facade.dto.dic.UpdateDicTypeDTO;
import com.born.facade.dto.dic.UpdateModelDicItemDTO;
import com.born.facade.service.dic.IDicService;
import com.born.facade.service.dic.IDicTypeService;
import com.born.facade.vo.UserInfoVO;
import com.born.util.result.Result;
import com.born.util.result.ResultUtil;

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
	* @Title: toDicListPage 
	* @Description: 字典列表数据 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author lijie
	* @throws
	 */
	@GetMapping("listPage")
	public String toDicListPage(){
		
		return "dic/manage/qft_dicList";
	}
	/**
	 * 
	* @Title: todicInfoPage 
	* @Description: 字典详情列表 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author lijie
	* @throws
	 */
	@GetMapping("dicInfoListPage")
	public String todicInfoPage(){
		
		return "dic/manage/qft_dicInfo";
	}
	/**
	 * 
	* @Title: addDicTypePage 
	* @Description: 添加菜单字典类型 
	* @param @param menuId
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author lijie
	* @throws
	 */
	@GetMapping("type/addPage/{menuId}")
	public String addDicTypePage(@PathVariable Long menuId, Model model) {
		model.addAttribute("menuId", menuId);
		return "dic/manage/qft_adddic";
	}
	/**
	 * 
	* @Title: typeInfo 
	* @Description: 字典类型详情信息页面 
	* @param @param typeId
	* @param @param model
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author lijie
	* @throws
	 */
	@GetMapping("type/info/{typeId}")
	public String typeInfo(@PathVariable Long typeId, Model model) {
		model.addAttribute("typeId", typeId);
		model.addAttribute("typeInfo", dicTypeService.getById(typeId).getData());
		return "dic/manage/qft_editdic_one";
	}
	/**
	 * 
	* @Title: itemInfo 
	* @Description: 字典详情信息 
	* @param @param itemId
	* @param @param model
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author lijie
	* @throws
	 */
	@GetMapping("item/info/{itemId}")
	public String itemInfo(@PathVariable Long itemId, Model model) {
		model.addAttribute("itemId", itemId);
		return "dic/manage/qft_editdic_two";
	}
	/**
	 * 
	* @Title: addItemPage 
	* @Description: 添加字典项值
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author lijie
	* @throws
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("item/addDicItemPage/{parentId}/{type}")
	public String addItemPage(@PathVariable Long parentId, @PathVariable Byte type, Model model) {
		Map<String, Object> map;
		Object name;
		if (type == 1) {
			map = (Map<String, Object>) dicTypeService.getById(parentId).getData();
			name = map.get("dtname");
		} else {
			map = (Map<String, Object>) dicService.getById(parentId).getData();
			name = map.get("diname");
		}
		model.addAttribute("id", map.get("id"));
		model.addAttribute("name", name);
		return "dic/manage/qft_add_dicitem";
	}
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
		if(null != getDicTypeIdByCode(dto.getDtcode())){
			return ResultUtil.fail("字典类型编码已存在");
		}
		dto.setCreaterId(TokenManager.getLoginUser().getId());
		dto.setCreateTime(new Date());
		dto.setIsDelete(DataBaseEnum.NOT_DELETE.getStatus());
		Result result = dicTypeService.addByModel(dto);
		log.info("添加字典类型返回数据={}", JSON.toJSONString(result));
		return result;
	}
	
	@SuppressWarnings("unchecked")
	private Long getDicTypeIdByCode(String code) {
		QueryDicTypeDTO type = new QueryDicTypeDTO();
		type.setDtcode(code);
		Map<String, Object> map = (Map<String, Object>) dicTypeService.getByModelOne(type).getData();
		if (null != map) {
			return (Long) map.get("id");
		}
		return null;
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
		Long id = getDicTypeIdByCode(dto.getDtcode());
		if (!dto.getId().equals(id)) {
			return ResultUtil.fail("字典类型编码已存在");
		}
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
		
		return dicService.getDicMenuTrees(TokenManager.getLoginUser().getCompanyId());
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
	@GetMapping("getDicList")
	public @ResponseBody Result getDicListByPage(PageBean pageBean) {
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
		Result result = dicService.delById(id, TokenManager.getLoginUser().getId());
		log.info("删除字典返回数据={}", JSON.toJSONString(result));
		return result;
	}
}
