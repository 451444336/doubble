package com.born.controller.focus.tenants;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.born.facade.dto.focus.tenants.FocusRegDTO;
import com.born.facade.service.IFocusService;
import com.born.util.result.Result;

@Controller
@RequestMapping(value = "web/focus")
public class TenantsController {

	/**
	 * 字典数据服务接口
	 */
	@Reference(version = "1.0.0")
	private IFocusService iFocusService;

	/**
	 * 添加租客
	 * @throws Exception 
	 */
	@ResponseBody
	@PostMapping(value = "/tenants/add")
	public Result addTenants(@RequestBody FocusRegDTO focusRegDTO) throws Exception {
		//focusRegDTO.setCompanyId(TokenManager.getLoginUser().getCompanyId());
		focusRegDTO.setCompanyId(22L);
		return iFocusService.insertTenants(focusRegDTO);
	}

}
