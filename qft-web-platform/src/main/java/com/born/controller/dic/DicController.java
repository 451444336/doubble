package com.born.controller.dic;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.born.config.shiro.token.TokenManager;
import com.born.facade.dto.dic.AddDicItemDTO;
import com.born.facade.dto.dic.DicItemDTO;
import com.born.facade.dto.dic.UpdateDicItemDTO;
import com.born.facade.dto.dic.UpdateDicItemSortDTO;
import com.born.facade.dto.property.AreaPropertyDTO;
import com.born.facade.dto.property.ChangeAreaPropertyDTO;
import com.born.facade.service.dic.IDicService;
import com.born.facade.service.property.IAreaPropertyService;
import com.born.facade.vo.UserInfoVO;
import com.born.util.json.JsonResult;
import com.born.util.json.ResultCode;
import com.born.util.json.ResultEntity;
import com.born.util.result.RespCode;
import com.born.util.result.Result;

/**
 * 
 * @ClassName: DicController
 * @Description: 字典
 * @author 张永胜
 * @date 2018年5月17日 下午2:38:15
 * @version 1.0
 */
@Controller
@RequestMapping(value = "web/dic")
public class DicController {

	/**
	 * 字典数据服务接口
	 */
	@Reference(version = "1.0.0")
	private IDicService iDicService;

	/**
	 * 区域+楼盘接口
	 */
	@Reference(version = "1.0.0")
	IAreaPropertyService service;
	
	/**
	 * 字典列表页面
	 * 
	 * @Title: list
	 * @Description: 字典列表
	 * @return
	 * @author 张永胜
	 * @return String
	 * @date 2018年5月17日 下午2:40:09
	 */
	@GetMapping(value = "/list")
	public String list() {
		return "dic/qft_dic_list";
	}

	/**
	 * 字典说明页面
	 * 
	 * @Title: explain
	 * @Description: 字典说明页面
	 * @return
	 * @author 张永胜
	 * @return String
	 * @date 2018年5月17日 下午2:45:30
	 */
	@GetMapping(value = "/explain")
	public String explain() {
		return "dic/qft_dic_explain";
	}

	/**
	 * 
	 * @Title: sort
	 * @Description: 一级二级字典排序
	 * @param pId
	 *            父级ID
	 * @param rank
	 *            几级字典
	 * @return
	 * @author 张永胜
	 * @return String
	 * @date 2018年5月21日 下午5:46:44
	 */
	@GetMapping(value = "/sort/{pId}/{rank}")
	public String sort(@PathVariable(value = "pId") String pId, @PathVariable(value = "rank") String rank,
			Model model) {
		model.addAttribute("pId", pId);
		model.addAttribute("rank", rank);
		return "dic/sort/qft_dic_sort";
	}

	/**
	 * 
	 * @Title: sort
	 * @Description: 获取字典排序数据
	 * @param pId
	 *            父级ID
	 * @param rank
	 *            几级字典
	 * @return
	 * @author 张永胜
	 * @return Result
	 * @date 2018年5月21日 下午6:16:54
	 */
	@ResponseBody
	@GetMapping(value = "/find/sort/data")
	public Result sort(@Param(value = "pId") String pId, @Param(value = "rank") String rank) {
		return iDicService.findDicItemAllById(Long.valueOf(pId), Integer.valueOf(rank),
				TokenManager.getLoginUser().getCompanyId());
	}

	/**
	 * 数据列表页面
	 * 
	 * @Title: page
	 * @Description: 一级字典和二级字典的页面
	 * @param id
	 * @param rank
	 * @return
	 * @author 张永胜
	 * @return String
	 * @date 2018年5月18日 上午9:38:32
	 */
	@GetMapping(value = "/page/{id}/{rank}/{name}")
	public String page(@PathVariable(name = "id") String id, @PathVariable(name = "rank") int rank,
			@PathVariable(name = "name") String name, Model model) {
		model.addAttribute("id", id);
		model.addAttribute("name", name);
		if (rank == 1) {
			/**
			 * 标识一级字典
			 */
			return "dic/nature/qft_dic_add_nature";
		}
		return "dic/area/qft_dic_add_book";
	}

	/**
	 * 
	 * @Title: dicItemData
	 * @Description: 获取数据字典值
	 * @return
	 * @author 张永胜
	 * @return ResultEntity<Object>
	 * @date 2018年5月18日 上午11:13:40
	 */
	@ResponseBody
	@GetMapping(value = "/find/item/data")
	public Result findDicItemData(DicItemDTO params) {
		params.setCompanyId(TokenManager.getLoginUser().getCompanyId());
		return iDicService.findDicItemList(params);
	}

	/**
	 * 菜单字典树形数据
	 * 
	 * @Title: dicZtreeData
	 * @Description: 获取左边树形菜单字典数据
	 * @return
	 * @author 张永胜
	 * @return ResultEntity<Object>
	 * @date 2018年5月17日 下午5:58:26
	 */
	@ResponseBody
	@GetMapping(value = "/find/ztree/data")
	public ResultEntity<Object> findDicZtreeData() {
		Result result = iDicService.findDicZtree();
		return JsonResult.info(ResultCode.SUCCESS, result.getData());
	}

	/**
	 * 
	 * @Title: updateDicItemData
	 * @Description: 更新字典
	 * @param updateDicItemDTO
	 * @return
	 * @author 张永胜
	 * @return ResultEntity<Object>
	 * @date 2018年5月18日 下午3:39:53
	 */
	@ResponseBody
	@PutMapping(value = "/update/item/data")
	public ResultEntity<Object> updateDicItemData(@RequestBody UpdateDicItemDTO updateDicItemDTO) {
		updateDicItemDTO.setCompanyId(TokenManager.getLoginUser().getCompanyId());
		Result result = iDicService.updateDicItemById(updateDicItemDTO);
		if (RespCode.Code.SUCCESS.getCode().equals(result.getCode())) {
			return JsonResult.info(ResultCode.SUCCESS);
		}
		return JsonResult.info(ResultCode.FAIL);
	}

	/**
	 * 
	 * @Title: updateDicItemSortData
	 * @Description: 批量更新字典排序
	 * @param list
	 * @return
	 * @author 张永胜
	 * @return ResultEntity<Object>
	 * @date 2018年5月22日 下午2:26:46
	 */
	@ResponseBody
	@PutMapping(value = "/update/item/sort/data")
	public ResultEntity<Object> updateDicItemSortData(@RequestBody List<UpdateDicItemSortDTO> list) {
		Result result = iDicService.updateDicItemSort(list);
		if (RespCode.Code.SUCCESS.getCode().equals(result.getCode())) {
			return JsonResult.info(ResultCode.SUCCESS);
		}
		return JsonResult.info(ResultCode.FAIL);
	}

	/**
	 * 
	 * @Title: addDicItemData
	 * @Description: 添加字典
	 * @param params
	 * @return
	 * @author 张永胜
	 * @return ResultEntity<Object>
	 * @throws Exception 
	 * @date 2018年5月18日 下午5:08:49
	 */
	@ResponseBody
	@PostMapping(value = "/add/item/data")
	public ResultEntity<Object> addDicItemData(@Param(value = "pId") String pId, @Param(value = "names") String names)
			throws Exception {
		UserInfoVO info = TokenManager.getLoginUser();
		AddDicItemDTO dto = new AddDicItemDTO();
		dto.setCompanyId(info.getCompanyId());
		dto.setCtraterId(info.getId());
		dto.setDicItem(names);
		dto.setPId(pId);
		dto.setDicRank(1);
		Result result = iDicService.addDicItem(dto);
		if (RespCode.Code.SUCCESS.getCode().equals(result.getCode())) {
			return JsonResult.info(ResultCode.SUCCESS);
		}
		return JsonResult.info(ResultCode.FAIL);
	}

	/**
	 * 
	 * @Title: addDicSubItemData
	 * @Description: 添加二级字典
	 * @param pId
	 *            父级ID
	 * @param names
	 *            名称
	 * @return
	 * @author 张永胜
	 * @return ResultEntity<Object>
	 * @throws Exception 
	 * @date 2018年5月21日 上午11:54:28
	 */
	@ResponseBody
	@PostMapping(value = "/add/sub/item/data")
	public ResultEntity<Object> addDicSubItemData(@Param(value = "pId") String pId,
			@Param(value = "names") String names) throws Exception {
		UserInfoVO info = TokenManager.getLoginUser();
		AddDicItemDTO dto = new AddDicItemDTO();
		dto.setCompanyId(info.getCompanyId());
		dto.setCtraterId(info.getId());
		dto.setDicItem(names);
		dto.setPId(pId);
		dto.setDicRank(2);
		Result result = iDicService.addDicItem(dto);
		if (RespCode.Code.SUCCESS.getCode().equals(result.getCode())) {
			return JsonResult.info(ResultCode.SUCCESS);
		}
		return JsonResult.info(ResultCode.FAIL);
	}

	/**
	 * 
	 * @Title: deleteDicItemData
	 * @Description: 批量删除字典数据
	 * @param ids
	 * @return
	 * @author 张永胜
	 * @return ResultEntity<Object>
	 * @date 2018年5月18日 下午6:19:16
	 */
	@ResponseBody
	@DeleteMapping(value = "/delete/item/data/{itemIds}")
	public ResultEntity<Object> deleteDicItemData(@PathVariable(name = "itemIds") String itemIds) {
		UserInfoVO info = TokenManager.getLoginUser();
		Result result = iDicService.deleteDicItemByIds(itemIds, info.getCompanyId(), info.getId());
		if (RespCode.Code.SUCCESS.getCode().equals(result.getCode())) {
			return JsonResult.info(ResultCode.SUCCESS);
		}
		return JsonResult.info(ResultCode.FAIL);
	}

	/**
	 * 
	 * @Title: deleteDicSubItemData
	 * @Description: 根据ID删除单个二级字典
	 * @param id
	 *            二级字典ID
	 * @return
	 * @author 张永胜
	 * @return ResultEntity<Object>
	 * @date 2018年5月21日 下午5:02:51
	 */
	@ResponseBody
	@DeleteMapping(value = "/delete/sub/item/data/{id}")
	public ResultEntity<Object> deleteDicSubItemData(@PathVariable(name = "id") String id) {
		Result result = iDicService.delById(Long.valueOf(id), TokenManager.getLoginUser().getId());
		if (RespCode.Code.SUCCESS.getCode().equals(result.getCode())) {
			return JsonResult.info(ResultCode.SUCCESS);
		}
		return JsonResult.info(ResultCode.FAIL);
	}
	
	/**
	 * 
	 * @Title
	 * @param
	 * @Description 添加楼盘地址
	 * @author 黄伟
	 * @return
	 * @date 2018年5月31日 下午5:58:06
	 */
	@ResponseBody
	@PostMapping(value = "/add")
	public Result add(ChangeAreaPropertyDTO dto){
		//获取登录人信息
		UserInfoVO su = TokenManager.getLoginUser();
		//设置默认值
		dto.setCreaterId(su.getId());
		dto.setCreateTime(new Date());
		dto.setUpdaterId(su.getId());
		dto.setUpdateTime(new Date());
		dto.setCompanyId(su.getCompanyId());
		return service.add(dto);
	}
	
	/**
	 * 
	 * @Title
	 * @param
	 * @Description 更新楼盘地址
	 * @author 黄伟
	 * @return
	 * @date 2018年5月31日 下午5:58:29
	 */
	@ResponseBody
	@PostMapping(value = "/updateProperty")
	public Result updateProperty(ChangeAreaPropertyDTO dto){
		//获取登录人信息
		UserInfoVO su = TokenManager.getLoginUser();
		//设置默认值
		dto.setUpdaterId(su.getId());
		dto.setUpdateTime(new Date());
		return service.update(dto);
	}
	
	/**
	 * 
	 * @Title
	 * @param
	 * @Description 删除楼盘地址
	 * @author 黄伟
	 * @return
	 * @date 2018年5月31日 下午5:58:42
	 */
	@ResponseBody
	@PostMapping(value = "/deleteProperty")
	private Result deleteProperty(Long id) {

		return service.delete(id);
	}
	
	/**
	 * 
	 * @Title
	 * @param
	 * @Description 根据市获取其下的区县
	 * @author 黄伟
	 * @return
	 * @date 2018年5月31日 下午5:58:53
	 */
	@ResponseBody
	@PostMapping(value = "/getArea")
	public Result getProvCityAreaAll(String areaName){
		
		return service.getProCityAreaAll(areaName);
	}
	
	/**
	 * 
	 * @Title
	 * @param
	 * @Description 根据市获取其下的区县及楼盘地址
	 * @author 黄伟
	 * @return
	 * @date 2018年5月31日 下午5:59:15
	 */
	@ResponseBody
	@PostMapping(value = "/getProperty")
	public Result getAreaPropertyAll(AreaPropertyDTO dto){
		//获取登录人信息
		UserInfoVO su = TokenManager.getLoginUser();
		//设置默认值
		dto.setCompanyId(su.getCompanyId());
		return service.getAreaPropertyAll(dto);
	}
}
