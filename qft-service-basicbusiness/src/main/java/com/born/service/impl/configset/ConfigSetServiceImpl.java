package com.born.service.impl.configset;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.born.core.constant.ConfigSetConstants;
import com.born.entity.configset.ConfigSet;
import com.born.facade.dto.configset.DefaultSetDTO;
import com.born.facade.dto.configset.RentFreePeriodDTO;
import com.born.facade.dto.configset.UserData;
import com.born.facade.exception.ConfigSetException;
import com.born.facade.service.configset.IConfigSetService;
import com.born.mapper.ConfigSetMapper;
import com.born.util.String.StringUtil;
import com.born.util.result.RespCode;
import com.born.util.result.Result;
import com.born.util.result.ResultUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @ClassName: ConfigSetServiceImpl
 * @Description: 通用设置
 * @author 张永胜
 * @date 2018年5月29日 下午5:59:11
 * @version 1.0
 */
@Slf4j
@Service(version = "1.0.0")
public class ConfigSetServiceImpl implements IConfigSetService {

	@Autowired
	private ConfigSetMapper configSetMapper;

	/**
	 * 
	* @Title: configSet 
	* @Description: 通过设置公共部分数据
	* @param data
	* @param model
	* @return 
	* @author 张永胜
	* @return ConfigSet
	* @date 2018年5月30日 下午3:09:44
	 */
	private ConfigSet configSet(UserData data, ConfigSet model) {
		model.setCreaterId(data.getUserId());//当前登录用户ID
		model.setCompanyId(data.getCompanyId());// 公司ID
		model.setCreateTime(new Date());// 创建时间
		model.setSetType(data.getType());// 设置类型
		return model;
	}
	
	@Override
	@Transactional
	public Result saveDefaultSet(UserData data, DefaultSetDTO params) {
		log.info("默认设置参数 {}", params);

		if (StringUtils.isNoneBlank(data.getType(), String.valueOf(data.getCompanyId())) && params == null) {
			return ResultUtil.getResult(RespCode.Code.ILLEGALARGUMENT);
		}

		List<ConfigSet> saveRecordList = new ArrayList<ConfigSet>();
		List<ConfigSet> updateRecordList = new ArrayList<ConfigSet>();
		ConfigSet model = null;// 默认设置实体类

		/**
		 * 房东-缴费提前时间设置
		 */
		if(params.getLandlordAdvanceDay() != null) {
			model = new ConfigSet();
			model.setSetValue(String.valueOf(params.getLandlordAdvanceDay()));
			if (params.getLandlordAdvanceId() != null) {
				model.setId(params.getLandlordAdvanceId());
				model.setUpdaterId(data.getUserId());
				model.setUpdateTime(new Date());
				updateRecordList.add(model);
			} else {
				model = configSet(data, model);
				model.setSetName(ConfigSetConstants.DefaultSet.LANDLORD_ADVANCE_DAY.getKey());
				model.setSetRemark(ConfigSetConstants.DefaultSet.LANDLORD_ADVANCE_DAY.getDescribe());
				saveRecordList.add(model);
			}
		}

		/**
		 * 房东-固定缴费时间设置[勾选]
		 */
		if(params.getIsFixedSetValue() != null) {
			model = new ConfigSet();
			model.setSetValue(String.valueOf(params.getIsFixedSetValue()));
			if (params.getIsFixedSetId() != null) {
				model.setId(params.getIsFixedSetId());
				model.setUpdaterId(data.getUserId());
				model.setUpdateTime(new Date());
				updateRecordList.add(model);
			} else {
				model = configSet(data, model);
				model.setSetName(ConfigSetConstants.DefaultSet.LANDLORD_IS_FIXED_SET.getKey());
				model.setSetRemark(ConfigSetConstants.DefaultSet.LANDLORD_IS_FIXED_SET.getDescribe());
				saveRecordList.add(model);
			}
		}

		/**
		 * 固定缴费时间-合同设置数据
		 * 如果设置勾选，就处理
		 * 这里要严格处理提交的参数是否合法
		 */
		if(params.getFixedDayDataValue() != null) {
			model = new ConfigSet();
			String fixedDayData = String.valueOf(params.getFixedDayDataValue());
			if (params.getIsFixedSetValue() == true) {
	
				// 验证设定的时间号数
				if (fixedDayData.isEmpty()) {
					return ResultUtil.getResult(RespCode.Code.ILLEGALARGUMENT);
				}
	
				// 拆分设定的时间组
				String[] v = fixedDayData.split(";");
				if (v.length <= 0) {
					return ResultUtil.getResult(RespCode.Code.FAIL, "必须有一条设定的时间");
				}
	
				// 每组拆分取值
				for (int i = 0; i < v.length - 1; i++) {
	
					String[] rV = v[i].split(":");// 当前行数据
					if (rV.length < 3) {
						return ResultUtil.getResult(RespCode.Code.ILLEGALARGUMENT);
					}
	
					if (rV[0].isEmpty() || rV[1].isEmpty() || rV[2].isEmpty()) {
						return ResultUtil.getResult(RespCode.Code.FAIL, "设定时间有空,不能保存");
					}
	
					if (!StringUtil.isInteger(rV[0]) && !StringUtil.isInteger(rV[1]) && !StringUtil.isInteger(rV[2])) {
						return ResultUtil.getResult(RespCode.Code.FAIL, "设定时间不是数字，不能保存");
					}
	
					int sDay = Integer.parseInt(v[0].split(":")[0]);// 第一行第一个
					int eDay = Integer.parseInt(v[v.length - 1].split(":")[1]);// 最后一行最后一个
					if (sDay != 1) {
						return ResultUtil.getResult(RespCode.Code.FAIL, "设定的时间，第一个必须为1号");
					}
					if (eDay != 31) {
						return ResultUtil.getResult(RespCode.Code.FAIL, "设定的时间，最后一个必须为31号");
					}
	
					if (Integer.parseInt(rV[0]) > Integer.parseInt(rV[1])) {
						return ResultUtil.getResult(RespCode.Code.FAIL, "结束日期必须大于开始日期！");
					}
	
					if (i < v.length) {
						String[] rNv = v[i + 1].split(":");// 下行数据
	
						// 当前行结束时间与下行数据比较连续性
						if (rNv.length < 3) {
							return ResultUtil.getResult(RespCode.Code.ILLEGALARGUMENT);
						}
						if (rNv[0].isEmpty() || rNv[1].isEmpty() || rNv[2].isEmpty()) {
							return ResultUtil.getResult(RespCode.Code.FAIL, "设定时间有空,不能保存！");
						}
						if (!StringUtil.isInteger(rNv[0])) {
							return ResultUtil.getResult(RespCode.Code.FAIL, "设定时间不是数字，不能保存！");
						}
						if (Integer.parseInt(rNv[0]) <= 0 || Integer.parseInt(rV[1]) <= 0) {
							return ResultUtil.getResult(RespCode.Code.FAIL, "设定时间不能小于0，不能保存！");
						}
						if ((Integer.parseInt(rV[1]) + 1) > Integer.parseInt(rNv[0])) {
							return ResultUtil.getResult(RespCode.Code.FAIL, "设定的时间有重叠，不能保存！");
						}
						if (Integer.parseInt(rNv[0]) != (Integer.parseInt(rV[1]) + 1)) {
							return ResultUtil.getResult(RespCode.Code.FAIL, "你设定的时间有间断，不能保存！");
						}
					}
				}
			}
			model.setSetValue(fixedDayData);
			if (params.getFixedDayDataId() != null) {
				model.setId(params.getFixedDayDataId());
				model.setUpdaterId(data.getUserId());
				model.setUpdateTime(new Date());
				updateRecordList.add(model);
			} else {
				model = configSet(data, model);
				model.setSetName(ConfigSetConstants.DefaultSet.LANDLORD_FIXED_DAY_DATA.getKey());
				model.setSetRemark(ConfigSetConstants.DefaultSet.LANDLORD_FIXED_DAY_DATA.getDescribe());
				saveRecordList.add(model);
			}
		}

		/**
		 * 租客-固定缴费时间设置
		 */
		if(params.getTenantsAdvanceDay() != null) {
			model = new ConfigSet();
			model.setSetValue(String.valueOf(params.getTenantsAdvanceDay()));
			if (params.getTenantsAdvanceId() != null) {
				model.setId(params.getTenantsAdvanceId());
				model.setUpdaterId(data.getUserId());
				model.setUpdateTime(new Date());
				updateRecordList.add(model);
			} else {
				model = configSet(data, model);
				model.setSetName(ConfigSetConstants.DefaultSet.TENANTS_ADVANCE_DAY.getKey());
				model.setSetRemark(ConfigSetConstants.DefaultSet.TENANTS_ADVANCE_DAY.getDescribe());
				saveRecordList.add(model);
			}
		}

		/**
		 * 合同下载类型设置[单选]
		 */
		if(params.getDownLoadTypeValue() != null) {
			model = new ConfigSet();
			model.setSetValue(String.valueOf(params.getDownLoadTypeValue()));
			if (params.getDownLoadTypeId() != null) {
				model.setId(params.getDownLoadTypeId());
				model.setUpdaterId(data.getUserId());
				model.setUpdateTime(new Date());
				updateRecordList.add(model);
			} else {
				model = configSet(data, model);
				model.setSetName(ConfigSetConstants.DefaultSet.DOWN_LOAD_TYPE.getKey());
				model.setSetRemark(ConfigSetConstants.DefaultSet.DOWN_LOAD_TYPE.getDescribe());
				saveRecordList.add(model);
			}
		}

		/**
		 * 租客-录入保存后弹出设置[勾选]
		 */
		if(params.getTenantsEjectSetValue() != null) {
			model = new ConfigSet();
			model.setSetValue(String.valueOf(params.getTenantsEjectSetValue()));
			if (params.getTenantsEjectSetId() != null) {
				model.setId(params.getTenantsEjectSetId());
				model.setUpdaterId(data.getUserId());
				model.setUpdateTime(new Date());
				updateRecordList.add(model);
			} else {
				model = configSet(data, model);
				model.setSetName(ConfigSetConstants.DefaultSet.TENANTS_EJECT_SET.getKey());
				model.setSetRemark(ConfigSetConstants.DefaultSet.TENANTS_EJECT_SET.getDescribe());
				saveRecordList.add(model);
			}
		}

		/**
		 * 这里如果是集中的话，是没有房东勾选项的
		 */
		if (!ConfigSetConstants.focus.equals(data.getType())) {
			/**
			 * 房东-录入保存后弹出设置[勾选]
			 */
			if(params.getLandlordEjectSetValue() != null) {
				model = new ConfigSet();
				model.setSetValue(String.valueOf(params.getLandlordEjectSetValue()));
				if (params.getLandlordEjectSetId() != null) {
					model.setId(params.getLandlordEjectSetId());
					model.setUpdaterId(data.getUserId());
					model.setUpdateTime(new Date());
					updateRecordList.add(model);
				} else {
					model = configSet(data, model);
					model.setSetName(ConfigSetConstants.DefaultSet.LANDLORD_EJECT_SET.getKey());
					model.setSetRemark(ConfigSetConstants.DefaultSet.LANDLORD_EJECT_SET.getDescribe());
					saveRecordList.add(model);
				}
			}
		}

		/**
		 * 押金默认值设置[勾选]
		 */
		if(params.getDepositSetValue() != null) {
			model = new ConfigSet();
			model.setSetValue(String.valueOf(params.getDepositSetValue()));
			if (params.getDepositSetId() != null) {
				model.setId(params.getDepositSetId());
				model.setUpdaterId(data.getUserId());
				model.setUpdateTime(new Date());
				updateRecordList.add(model);
			} else {
				model = configSet(data, model);
				model.setSetName(ConfigSetConstants.DefaultSet.DEPOSIT_SET.getKey());
				model.setSetRemark(ConfigSetConstants.DefaultSet.DEPOSIT_SET.getDescribe());
				saveRecordList.add(model);
			}
		}

		/**
		 * 物业、预存费默认值设置[勾选]
		 */
		if(params.getPsFeeSetValue() != null) {
			model = new ConfigSet();
			model.setSetValue(String.valueOf(params.getPsFeeSetValue()));
			if (params.getPsFeeSetId() != null) {
				model.setId(params.getPsFeeSetId());
				model.setUpdaterId(data.getUserId());
				model.setUpdateTime(new Date());
				updateRecordList.add(model);
			} else {
				model = configSet(data, model);
				model.setSetName(ConfigSetConstants.DefaultSet.PROPERTY_STORAGE_FEE_SET.getKey());
				model.setSetRemark(ConfigSetConstants.DefaultSet.PROPERTY_STORAGE_FEE_SET.getDescribe());
				saveRecordList.add(model);
			}
		}

		/**
		 * 物管费
		 */
		if(params.getPmFeeValue() != null) {
			model = new ConfigSet();
			model.setSetValue(String.valueOf(params.getPmFeeValue()));
			if (params.getPmFeeId() != null) {
				model.setId(params.getPmFeeId());
				model.setUpdaterId(data.getUserId());
				model.setUpdateTime(new Date());
				updateRecordList.add(model);
			} else {
				model = configSet(data, model);
				model.setSetName(ConfigSetConstants.DefaultSet.PROPERTY_MANAGEMENT_FEE.getKey());
				model.setSetRemark(ConfigSetConstants.DefaultSet.PROPERTY_MANAGEMENT_FEE.getDescribe());
				saveRecordList.add(model);
			}
		}

		/**
		 * 物管费-缴费方式[下拉框]
		 */
		if(params.getPmFeeModeValue() != null) {
			model = new ConfigSet();
			model.setSetValue(String.valueOf(params.getPmFeeModeValue()));
			if (params.getPmFeeModeId() != null) {
				model.setId(params.getPmFeeModeId());
				model.setUpdaterId(data.getUserId());
				model.setUpdateTime(new Date());
				updateRecordList.add(model);
			} else {
				model = configSet(data, model);
				model.setSetName(ConfigSetConstants.DefaultSet.PROPERTY_MANAGEMENT_FEE_MODE.getKey());
				model.setSetRemark(ConfigSetConstants.DefaultSet.PROPERTY_MANAGEMENT_FEE_MODE.getDescribe());
				saveRecordList.add(model);
			}
		}
		
		/**
		 * 预存费
		 */
		if(params.getPreDepositFeeValue() != null) {
			model = new ConfigSet();
			model.setSetValue(String.valueOf(params.getPreDepositFeeValue()));
			if (params.getPreDepositFeeId() != null) {
				model.setId(params.getPreDepositFeeId());
				model.setUpdaterId(data.getUserId());
				model.setUpdateTime(new Date());
				updateRecordList.add(model);
			} else {
				model = configSet(data, model);
				model.setSetName(ConfigSetConstants.DefaultSet.PRE_DEPOSIT_FEE.getKey());
				model.setSetRemark(ConfigSetConstants.DefaultSet.PRE_DEPOSIT_FEE.getDescribe());
				saveRecordList.add(model);
			}
		}
		
		/**
		 * 预存费-缴费方式[下拉框]
		 */
		if(params.getPreDepositFeeModeValue() != null) {
			model = new ConfigSet();
			model.setSetValue(String.valueOf(params.getPreDepositFeeModeValue()));
			if (params.getPreDepositFeeModeId() != null) {
				model.setId(params.getPreDepositFeeModeId());
				model.setUpdaterId(data.getUserId());
				model.setUpdateTime(new Date());
				updateRecordList.add(model);
			} else {
				model = configSet(data, model);
				model.setSetName(ConfigSetConstants.DefaultSet.PRE_DEPOSIT_FEE_MODE.getKey());
				model.setSetRemark(ConfigSetConstants.DefaultSet.PRE_DEPOSIT_FEE_MODE.getDescribe());
				saveRecordList.add(model);
			}
		}
		
		/**
		 * 按金额/按百分比[单选]
		 */
		if(params.getServiceTypeSetValue() != null) {
			model = new ConfigSet();
			model.setSetValue(String.valueOf(params.getServiceTypeSetValue()));
			if (params.getServiceTypeSetId() != null) {
				model.setId(params.getServiceTypeSetId());
				model.setUpdaterId(data.getUserId());
				model.setUpdateTime(new Date());
				updateRecordList.add(model);
			} else {
				model = configSet(data, model);
				model.setSetName(ConfigSetConstants.DefaultSet.SERVICE_TYPE_SET.getKey());
				model.setSetRemark(ConfigSetConstants.DefaultSet.SERVICE_TYPE_SET.getDescribe());
				saveRecordList.add(model);
			}
		}
		
		/**
		 * 服务费:金额或百分比值
		 */
		if(params.getServiceChargeValue() != null) {
			model = new ConfigSet();
			model.setSetValue(String.valueOf(params.getServiceChargeValue()));
			if (params.getServiceChargeId()!= null) {
				model.setId(params.getServiceChargeId());
				model.setUpdaterId(data.getUserId());
				model.setUpdateTime(new Date());
				updateRecordList.add(model);
			} else {
				model = configSet(data, model);
				model.setSetName(ConfigSetConstants.DefaultSet.SERVICE_CHARGE.getKey());
				model.setSetRemark(ConfigSetConstants.DefaultSet.SERVICE_CHARGE.getDescribe());
				saveRecordList.add(model);
			}
		}
		
		/**
		 * 服务费-缴费方式
		 */
		if(params.getServiceModeValue() != null) {
			model = new ConfigSet();
			model.setSetValue(String.valueOf(params.getServiceModeValue()));
			if (params.getServiceModeId() != null) {
				model.setId(params.getServiceModeId());
				model.setUpdaterId(data.getUserId());
				model.setUpdateTime(new Date());
				updateRecordList.add(model);
			} else {
				model = configSet(data, model);
				model.setSetName(ConfigSetConstants.DefaultSet.SERVICE_CHARGE_MODE.getKey());
				model.setSetRemark(ConfigSetConstants.DefaultSet.SERVICE_CHARGE_MODE.getDescribe());
				saveRecordList.add(model);
			}
		}

		try {

			if (saveRecordList.size() != 0) {
				configSetMapper.insertList(saveRecordList);
			}

			if (updateRecordList.size() != 0) {
				configSetMapper.batchUpdateByIds(updateRecordList);
			}

		} catch (Exception e) {
			log.error("保存默认设置异常", e);
			throw new ConfigSetException("保存默认设置异常");
		}
		return ResultUtil.getResult(RespCode.Code.SUCCESS);
	}

	@Override
	public Result saveRentFreePeriod(UserData data, RentFreePeriodDTO params)  {
		log.info("免租期模式参数 {}", params);
		
		if (StringUtils.isNoneBlank(data.getType(), String.valueOf(data.getCompanyId())) && params == null) {
			return ResultUtil.getResult(RespCode.Code.ILLEGALARGUMENT);
		}
		
		List<ConfigSet> saveRecordList = new ArrayList<ConfigSet>();
		List<ConfigSet> updateRecordList = new ArrayList<ConfigSet>();
		ConfigSet model = null;// 设置实体类
		
		/**
		 * 房东缴费明细生成方式
		 */
		if(params.getCopValue() != null) {
			model = new ConfigSet();
			model.setSetValue(params.getCopValue());
			if (params.getCopId() != null) {
				model.setId(params.getCopId());
				model.setUpdaterId(data.getUserId());
				model.setUpdateTime(new Date());
				updateRecordList.add(model);
			} else {
				model = configSet(data, model);
				model.setSetName(ConfigSetConstants.RentFreePeriodMode.RENT_FREE_PERIOD_MODE.getKey());
				model.setSetRemark(ConfigSetConstants.RentFreePeriodMode.RENT_FREE_PERIOD_MODE.getDescribe());
				saveRecordList.add(model);
			}
		}
		
		/**
		 * 租客租金生成模式
		 */
		if(params.getTrgValue() != null) {
			model = new ConfigSet();
			model.setSetValue(params.getCopValue());
			if (params.getTrgId() != null) {
				model.setId(params.getTrgId());
				model.setUpdaterId(data.getUserId());
				model.setUpdateTime(new Date());
				updateRecordList.add(model);
			} else {
				model = configSet(data, model);
				model.setSetName(ConfigSetConstants.RentFreePeriodMode.TENANT_RENT_GENERATION.getKey());
				/**
				 * 这里就不要设置每个对应的描述了
				 */
				model.setSetRemark(ConfigSetConstants.RentFreePeriodMode.TENANT_RENT_GENERATION.getDescribe());
				saveRecordList.add(model);
			}
		}
		
		try {

			if (saveRecordList.size() != 0) {
				configSetMapper.insertList(saveRecordList);
			}

			if (updateRecordList.size() != 0) {
				configSetMapper.batchUpdateByIds(updateRecordList);
			}

		} catch (Exception e) {
			log.error("保存免租期模式设置异常", e);
			throw new ConfigSetException("保存免租期模式设置异常");
		}
		return ResultUtil.getResult(RespCode.Code.SUCCESS);
	}

}
