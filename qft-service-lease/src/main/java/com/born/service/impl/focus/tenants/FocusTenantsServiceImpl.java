package com.born.service.impl.focus.tenants;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.born.core.base.BaseModel;
import com.born.core.base.BaseService;
import com.born.core.base.DataBaseParameters;
import com.born.entity.focus.tenants.Tenants;
import com.born.entity.focus.tenants.TenantsCheckOut;
import com.born.entity.focus.tenants.TenantsInfo;
import com.born.facade.dto.UserData;
import com.born.facade.dto.focus.tenants.add.TenantsRegDTO;
import com.born.facade.dto.focus.tenants.outroom.TenantsOutRoomDTO;
import com.born.facade.exception.tenants.FocusTenantsException;
import com.born.facade.exception.tenants.FocusTenantsExceptionEnum;
import com.born.facade.service.focus.tenants.IFocusTenantsService;
import com.born.mapper.FocusRoomMapper;
import com.born.mapper.FocusTenantsCheckOutMapper;
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
public class FocusTenantsServiceImpl extends BaseService<BaseModel, Tenants> implements IFocusTenantsService {

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
	 * 租客退房
	 */
	@Autowired
	private FocusTenantsCheckOutMapper focusTenantsCheckOutMapper;

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
	public Result saveTenants(UserData user, TenantsRegDTO params) {

		log.info("新增租客请求参数 {} ", params);

		if (params == null) {
			return ResultUtil.getResult(RespCode.Code.ILLEGALARGUMENT, "请求参数非法");
		}

		try {

			/**
			 * 添加租客基本信息
			 */
			Tenants tenants = new Tenants();
			BeanUtils.copyProperties(params.getTenants(), tenants);
			tenants.setRoomId(params.getRoomId());
			focusTenantsMapper.insertUseGeneratedKeys(tenants);

			/**
			 * 添加租客必须要的租赁信息等
			 */
			Date roomCtime = focusRoomMapper.getRoomCreateTimeById(params.getRoomId(), user.getCompanyId());
			TenantsInfo tenantsInfo = new TenantsInfo();
			BeanUtils.copyProperties(params.getTenantsInfo(), tenantsInfo);
			tenantsInfo.setCompanyId(user.getCompanyId());
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
			focusRoomMapper.updateRoomStatusById(params.getRoomId(), user.getCompanyId());

			/**
			 * 处理生成收入数据
			 */

		} catch (Exception e) {
			log.error("添加租客异常", e);
			throw new FocusTenantsException(FocusTenantsExceptionEnum.SAVE_TENANTS_ERROR);
		}
		return ResultUtil.getResult(RespCode.Code.SUCCESS);
	}

	@Override
	@Transactional
	public Result saveOutRoom(UserData user, TenantsOutRoomDTO params) {
		log.info("保存租客退房参数 {}", params);

		if (params == null) {
			return ResultUtil.getResult(RespCode.Code.ILLEGALARGUMENT);
		}

		try {

			TenantsCheckOut outRoom = new TenantsCheckOut();
			BeanUtils.copyProperties(params.getOutRoom(), outRoom);
			outRoom.setCompanyId(user.getCompanyId());
			outRoom.setCreaterId(user.getUserId());
			outRoom.setCreateTime(new Date());
			focusTenantsCheckOutMapper.insertUseGeneratedKeys(outRoom);
			
			/**
			 * 如果是没有签成的 需要生成一条代办
			 */
			/**
			 * 查询退房后是否取消定价  0不启用 1 启用
			 */
			/**
			 * 修改房间状态
			 */
			focusRoomMapper.updateRoomStatusById(outRoom.getRoomId(), user.getCompanyId());
			if(outRoom.getCheckOutType() == "no") {
				/**
				 * 如果没有签成，需要修改房间的最后一次租出时间 为租客上次租出时间
				 */
				Date lastTime = focusTenantsInfoMapper.getTenantsLastTimeById(outRoom.getRoomId(), user.getCompanyId());
				focusRoomMapper.updateRoomLastTime(outRoom.getRoomId(), user.getCompanyId(), lastTime);
			}
			
			/**
			 * 查询通用设置中定价设置项
			 */
			
			
			
			
		} catch (Exception e) {
			log.error("保存租客退房异常", e);
			throw new FocusTenantsException(FocusTenantsExceptionEnum.SAVE_TENANTS_OUT_ROOM_ERROR);
		}

		return null;
	}

}
