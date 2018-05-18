/**
 * 
 */
package com.born.controller.store;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.born.config.shiro.token.TokenManager;
import com.born.facade.dto.store.StoreGroupDTO;
import com.born.facade.service.store.IStoreGroupService;
import com.born.facade.vo.UserInfoVO;
import com.born.util.result.RespCode;
import com.born.util.result.Result;
import com.born.util.result.ResultUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 店面分组管理控制类
 * @author 黄伟
 * @date 2018年5月14日 下午6:25:15
 */
@Slf4j
@Controller
@RequestMapping(value = "/web/group")
public class StoreGroupController {

	@Reference(version = "1.0.0")
	IStoreGroupService storeGroupService;

	/**
	 * 
	* @Title: saveIndex 
	* @Description: 跳转到添加店面分组页面
	* @param @return
	* @author 黄伟
	* @return String
	* @date 2018年5月17日 下午1:36:28 
	* @throws
	 */
	@GetMapping(value = "/saveIndex")
    public String saveIndex() {
        return "store/qft_addGroup";
    }
	/**
	 * @Description 保存店面分组
	 * @author 黄伟
	 * @date 2018年5月17日 上午10:35:03
	 * @param role
	 * @return
	 */
	@ApiOperation(value = "添加店面分组",notes = "确保填写参数是否正确")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@PostMapping(value = "/save")
	@ResponseBody
	public Result saveStore(Long storeId, String groupNames) {
		if (groupNames == null || "".equals(groupNames) || storeId == null) {
			
			return ResultUtil.getResult(RespCode.Code.REQUEST_DATA_ERROR);
		}
		List<StoreGroupDTO> list = new ArrayList<StoreGroupDTO>();
		// 获取当前登录用户
		UserInfoVO su = TokenManager.getLoginUser();
		// 设置默认值
		String[] names = groupNames.split(",");
		for(String name : names){
			StoreGroupDTO dto = new StoreGroupDTO();
			dto.setCreateTime(new Date());
			dto.setUpdateTime(new Date());
			dto.setCreaterId(su.getId());
			dto.setUpdaterId(su.getId());
			dto.setCompanyId(su.getCompanyId());
			dto.setStoreId(storeId);
			dto.setName(name);
			list.add(dto);
		}
		return storeGroupService.insertBatch(list);
	}

	/**
	 * @Description 修改店面分组
	 * @author 黄伟
	 * @date 2018年5月17日 上午10:57:35
	 * @param role
	 * @return
	 */
	@ApiOperation(value = "修改店面分组",notes = "确保填写参数是否正确")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@PostMapping(value = "/update")
	@ResponseBody
	public Result updateStore(Long id, String name) {
		if(id == null || StringUtils.isBlank(name)){
			return ResultUtil.getResult(RespCode.Code.REQUEST_DATA_ERROR);
		}
		// 获取当前用户信息
		UserInfoVO su = TokenManager.getLoginUser();
		// 设置默认值
		StoreGroupDTO dto = new StoreGroupDTO();
		dto.setId(id);
		dto.setName(name);
		dto.setUpdateTime(new Date());
		dto.setUpdaterId(1L);
		return storeGroupService.update(dto);
	}

	/**
	 * @Description 删除店面分组
	 * @author 黄伟
	 * @date 2018年5月17日 上午11:06:45
	 * @param id
	 *            店面分组ID
	 * @return
	 */
	@ApiOperation(value = "删除店面分组",notes = "确保填写参数是否正确")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@PostMapping(value = "/delete/{id}")
	@ResponseBody
	public Result deleteStore(@PathVariable Long id) {
		return storeGroupService.deleteById(id);
	}
	
	/**
	 * @Description 根据店面ID查询店面分组
	 * @author 黄伟
	 * @date 2018年5月17日 上午11:36:21
	 */
	@ApiOperation(value = "根据店面ID查询店面分组",notes = "确保填写参数是否正确")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@GetMapping(value = "/getGroupList/{storeId}")
	public String getGroupListByStoreId(@PathVariable Long storeId, Model model) {
		Result result = storeGroupService.getGroupListByStoreId(storeId);
		model.addAttribute("groupList", result.getData());
		model.addAttribute("storeId",storeId);
		return "store/qft_addGroup";
	}
}
