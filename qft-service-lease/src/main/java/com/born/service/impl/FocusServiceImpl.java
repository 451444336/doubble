package com.born.service.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.born.core.base.BaseModel;
import com.born.core.base.BaseService;
import com.born.core.base.DataBaseParameters;
import com.born.entity.focus.tenants.Tenants;
import com.born.entity.focus.tenants.TenantsInfo;
import com.born.facade.dto.focus.tenants.FocusRegDTO;
import com.born.facade.service.IFocusService;
import com.born.mapper.FocusRoomMapper;
import com.born.mapper.FocusTenantsInfoMapper;
import com.born.mapper.FocusTenantsMapper;
import com.born.util.result.RespCode;
import com.born.util.result.Result;
import com.born.util.result.ResultUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @ClassName: FocusServiceImpl
 * @Description: 集中租客相关服务类
 * @author 张永胜
 * @date 2018年5月29日 上午11:18:21
 * @version 1.0
 */
@Slf4j
@Service(version = "1.0.0")
public class FocusServiceImpl extends BaseService<BaseModel, Tenants> implements IFocusService {

	/**
	 * 租客基本信息
	 */
	@Autowired
	private FocusTenantsMapper focusTenantsMapper;

	/**
	 * 租客必要信息
	 */
	@Autowired
	private FocusTenantsInfoMapper focusTenantsInfoMapper;

	/**
	 * 房间服务
	 */
	@Autowired
	private FocusRoomMapper focusRoomMapper;

	@Override
	protected DataBaseParameters<Tenants> getDataBaseParameters() {
		DataBaseParameters<Tenants> map = new DataBaseParameters<Tenants>();
		map.setMapper(focusTenantsMapper);
		map.setEntityClass(Tenants.class);
		return map;
	}

	@Override
	@Transactional
	public Result insertTenants(FocusRegDTO focusReg) throws Exception {

		log.info("新增租客请求参数 {} ", focusReg);

		if (focusReg == null) {
			return ResultUtil.getResult(RespCode.Code.ILLEGALARGUMENT, "请求参数非法");
		}

		try {

			/**
			 * 添加租客基本信息
			 */
			Tenants tenants = new Tenants();
			BeanUtils.copyProperties(focusReg.getTenants(), tenants);
			tenants.setRoomId(focusReg.getRoomId());
			focusTenantsMapper.insertUseGeneratedKeys(tenants);

			/**
			 * 添加租客必须要的租赁信息等
			 */
			Date roomCtime = focusRoomMapper.getRoomCreateTimeById(focusReg.getRoomId(), focusReg.getCompanyId());
			TenantsInfo tenantsInfo = new TenantsInfo();
			BeanUtils.copyProperties(focusReg.getTenantsInfo(), tenantsInfo);
			tenantsInfo.setCompanyId(focusReg.getCompanyId());
			tenantsInfo.setCreaterId(1L);
			tenantsInfo.setCreateTime(new Date());
			tenantsInfo.setTenantId(tenants.getId());
			tenantsInfo.setTenantsContractNature("new_z");
			tenantsInfo.setLeaseStarttime(roomCtime);
			tenantsInfo.setLeasePriceShow(tenantsInfo.getLeasePrice());
			focusTenantsInfoMapper.insert(tenantsInfo);

			/**
			 * 更新当前房间状态、定金状态等
			 */
			focusRoomMapper.updateRoomStatusById(focusReg.getRoomId(), focusReg.getCompanyId());
			
			/**
			 * 处理生成收入数据
			 */

		} catch (Exception e) {
			log.error("添加租客异常", e);
			throw new Exception("添加租客异常");
		}
		return ResultUtil.getResult(RespCode.Code.SUCCESS);
	}

}
