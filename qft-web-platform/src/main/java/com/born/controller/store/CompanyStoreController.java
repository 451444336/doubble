/**
 * 
 */
package com.born.controller.store;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.born.config.shiro.token.TokenManager;
import com.born.facade.dto.store.CompanyStoreDTO;
import com.born.facade.service.store.ICompanyStoreService;
import com.born.facade.vo.UserInfoVO;
import com.born.facade.vo.store.CompanyStoreVO;
import com.born.util.result.Result;
import com.github.pagehelper.Page;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 店面管理控制类
 * @author 黄伟
 * @date 2018年5月14日 下午6:25:15
 */
@Slf4j
@Controller
@RequestMapping(value = "/web/store")
public class CompanyStoreController {

	@Reference(version = "1.0.0")
	ICompanyStoreService companyStoreService;

	/**
	 * 
	* @Title: saveIndex 
	* @Description: 跳转到添加店面页面
	* @param @return
	* @author 黄伟
	* @return String
	* @date 2018年5月15日 下午1:36:28 
	* @throws
	 */
	@GetMapping(value = "/saveIndex")
    public String saveIndex(Model model) {
		UserInfoVO su = TokenManager.getLoginUser();
		CompanyStoreVO vo = new CompanyStoreVO();
		vo.setCompanyName(su.getCompanyName());
		model.addAttribute("store", vo);
		System.out.println(su.getCompanyId());
        return "store/qft_add_store";
    }
	/**
	 * @Description 保存店面
	 * @author 黄伟
	 * @date 2018年5月15日 上午10:35:03
	 * @param role
	 * @return
	 */
	@ApiOperation(value = "添加店面",notes = "确保填写参数是否正确")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@PostMapping(value = "/save")
	@ResponseBody
	public Result saveStore(CompanyStoreDTO dto) {
		// 获取当前登录用户
		UserInfoVO su = TokenManager.getLoginUser();
		// 设置默认值
		dto.setCreateTime(new Date());
		dto.setUpdateTime(new Date());
		dto.setCreaterId(su.getId());
		dto.setUpdaterId(su.getId());
		dto.setCompanyId(su.getCompanyId());
		return companyStoreService.insert(dto);
	}

	/**
	 * @Description 根据店面ID查询店面
	 * @author 黄伟
	 * @date 2018年5月15日 上午11:06:45
	 * @param id
	 *            店面ID
	 * @return
	 */
	@ApiOperation(value = "根据店面ID查询店面",notes = "确保填写参数是否正确")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@GetMapping(value = "/getStoreById/{id}")
	public String getStoreById(@PathVariable Long id, Model model){
		
		Result result = companyStoreService.getStoreById(id);
		CompanyStoreVO vo = new CompanyStoreVO();
		BeanUtils.copyProperties(result.getData(), vo);
		// 获取当前登录用户
		UserInfoVO su = TokenManager.getLoginUser();
		vo.setCompanyName(su.getCompanyName());
		model.addAttribute("store", vo);
		return "store/qft_edit_store";
	}
	
	/**
	 * @Description 修改店面
	 * @author 黄伟
	 * @date 2018年5月15日 上午10:57:35
	 * @param role
	 * @return
	 */
	@ApiOperation(value = "修改店面",notes = "确保填写参数是否正确")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@PostMapping(value = "/update")
	@ResponseBody
	public Result updateStore(CompanyStoreDTO dto) {
		// 获取当前用户信息
		UserInfoVO su = TokenManager.getLoginUser();
		// 设置默认值
		dto.setUpdateTime(new Date());
		dto.setUpdaterId(su.getId());
		return companyStoreService.update(dto);
	}

	/**
	 * @Description 删除店面
	 * @author 黄伟
	 * @date 2018年5月15日 上午11:06:45
	 * @param id
	 *            店面ID
	 * @return
	 */
	@ApiOperation(value = "删除店面",notes = "确保填写参数是否正确")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@PostMapping(value = "/delete/{id}")
	@ResponseBody
	public Result deleteStore(@PathVariable Long id) {
		return companyStoreService.deleteById(id);
	}
	
	/**
	 * 
	* @Title: list 
	* @Description: 跳转到查询列表
	* @param @return
	* @author 黄伟
	* @return String
	* @date 2018年5月15日 下午1:36:28 
	* @throws
	 */
	@GetMapping(value = "/storeList")
    public String storeList() {
        return "store/qft_storeList";
    }
	/**
	 * @Description 分页查询店面列表
	 * @author 黄伟
	 * @date 2018年5月9日 上午11:36:21
	 */
	@ApiOperation(value = "分页查询店面列表",notes = "确保填写参数是否正确")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@GetMapping(value = "/getPageList")
	@ResponseBody
	public Result getPageList(CompanyStoreDTO dto) {
		
		return companyStoreService.getPageList(dto);
	}
	
}
