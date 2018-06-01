package com.born.controller.configset;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.born.core.constant.ConfigSetConstants;
import com.born.core.entity.UserData;
import com.born.facade.dto.configset.DefaultSetDTO;
import com.born.facade.dto.configset.DepositSetDTO;
import com.born.facade.dto.configset.FixPriceSetDTO;
import com.born.facade.dto.configset.RentFreePeriodDTO;
import com.born.facade.service.configset.IConfigSetService;
import com.born.util.result.RespCode;
import com.born.util.result.Result;
import com.born.util.result.ResultUtil;

/**
 * 通用设置
 * 
 * @ClassName: ConfigSetController
 * @Description: 通用设置访问层
 * @author 张永胜
 * @date 2018年5月31日 上午10:01:27
 * @version 1.0
 */
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
	 * @Description: 通用设置-保存默认设置
	 * @param defaultSet
	 * @return
	 * @throws Exception
	 * @author 张永胜
	 * @return Result
	 * @date 2018年5月30日 上午9:54:55
	 */
	@ResponseBody
	@PostMapping(value = "/save/default/set")
	public Result saveDefaultSet(@RequestBody DefaultSetDTO model) throws Exception {
		switch (model.getFlag()) {
		case 1:
			return iConfigSetService.saveDefaultSet(new UserData(ConfigSetConstants.focus, 2L, 3L), model);
		case 2:
			return iConfigSetService.saveDefaultSet(new UserData(ConfigSetConstants.housing, 2L, 3L), model);
		case 3:
			return iConfigSetService.saveDefaultSet(new UserData(ConfigSetConstants.cotenant, 2L, 3L), model);
		default:
			break;
		}
		return ResultUtil.getResult(RespCode.Code.ILLEGALARGUMENT);
	}

	/**
	 * 免租期模式
	 * 
	 * @Title: saveRentFreePeriod
	 * @Description: 通用设置-保存免租期设置
	 * @param model
	 * @return
	 * @throws Exception
	 * @author 张永胜
	 * @return Result
	 * @date 2018年5月31日 上午10:00:54
	 */
	@ResponseBody
	@PostMapping(value = "/save/rent/free/period")
	public Result saveRentFreePeriod(@RequestBody RentFreePeriodDTO model) throws Exception {
		switch (model.getFlag()) {
		case 1:
			return iConfigSetService.saveRentFreePeriod(new UserData(ConfigSetConstants.focus, 2L, 3L), model);
		case 2:
			return iConfigSetService.saveRentFreePeriod(new UserData(ConfigSetConstants.housing, 2L, 3L), model);
		case 3:
			return iConfigSetService.saveRentFreePeriod(new UserData(ConfigSetConstants.cotenant, 2L, 3L), model);
		default:
			break;
		}
		return ResultUtil.getResult(RespCode.Code.ILLEGALARGUMENT);
	}

	/**
	 * 
	 * @Title: saveFixPriceSet
	 * @Description: 定价设置
	 * @param model
	 * @return
	 * @throws Exception
	 * @author 张永胜
	 * @return Result
	 * @date 2018年6月1日 下午4:20:32
	 */
	@ResponseBody
	@PostMapping(value = "/save/fix/price/set")
	public Result saveFixPriceSet(@RequestBody FixPriceSetDTO model) throws Exception {
		switch (model.getFlag()) {
		case 1:
			return iConfigSetService.saveFixPriceSet(new UserData(ConfigSetConstants.focus, 2L, 3L), model);
		case 2:
			return iConfigSetService.saveFixPriceSet(new UserData(ConfigSetConstants.housing, 2L, 3L), model);
		case 3:
			return iConfigSetService.saveFixPriceSet(new UserData(ConfigSetConstants.cotenant, 2L, 3L), model);
		default:
			break;
		}
		return ResultUtil.getResult(RespCode.Code.ILLEGALARGUMENT);
	}

	/**
	 * 
	 * @Title: saveDepositSet
	 * @Description: 定金设置
	 * @param model
	 * @return
	 * @throws Exception
	 * @author 张永胜
	 * @return Result
	 * @date 2018年6月1日 下午4:21:30
	 */
	@ResponseBody
	@PostMapping(value = "/save/deposit/set")
	public Result saveDepositSet(@RequestBody DepositSetDTO model) throws Exception {
		switch (model.getFlag()) {
		case 1:
			return iConfigSetService.saveDepositSet(new UserData(ConfigSetConstants.focus, 2L, 3L), model);
		case 2:
			return iConfigSetService.saveDepositSet(new UserData(ConfigSetConstants.housing, 2L, 3L), model);
		case 3:
			return iConfigSetService.saveDepositSet(new UserData(ConfigSetConstants.cotenant, 2L, 3L), model);
		default:
			break;
		}
		return ResultUtil.getResult(RespCode.Code.ILLEGALARGUMENT);
	}
}
