package com.born.controller.configset;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.born.core.constant.ConfigSetConstants;
import com.born.facade.dto.configset.DefaultSetDTO;
import com.born.facade.dto.configset.UserData;
import com.born.facade.service.configset.IConfigSetService;
import com.born.util.result.RespCode;
import com.born.util.result.Result;
import com.born.util.result.ResultUtil;

@Controller
@RequestMapping(value = "web/config")
public class ConfigSetController {

	/**
	 * 通用设置服务
	 */
	@Reference(version = "1.0.0")
	private IConfigSetService iConfigSetService;

	/**
	 * 默认设置
	 * 
	 * @Title: saveDefaultSet
	 * @Description: 通用设置保存默认设置
	 * @param defaultSet
	 * @return
	 * @throws Exception
	 * @author 张永胜
	 * @return Result
	 * @date 2018年5月30日 上午9:54:55
	 */
	@ResponseBody
	@PostMapping(value = "/save/default/set")
	public Result saveDefaultSet(@RequestBody DefaultSetDTO defaultSet) throws Exception {
		switch (defaultSet.getFlag()) {
		case 1:
			return iConfigSetService.saveDefaultSet(new UserData(ConfigSetConstants.focus, 2L, 3L), defaultSet);
		case 2:
			return iConfigSetService.saveDefaultSet(new UserData(ConfigSetConstants.housing, 2L, 3L), defaultSet);
		case 3:
			return iConfigSetService.saveDefaultSet(new UserData(ConfigSetConstants.cotenant, 2L, 3L), defaultSet);
		default:
			break;
		}
		return ResultUtil.getResult(RespCode.Code.ILLEGALARGUMENT);
	}
}
