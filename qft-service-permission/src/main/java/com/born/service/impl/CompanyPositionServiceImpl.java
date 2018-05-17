package com.born.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.born.facade.dto.CompanyPositionDTO;
import com.born.facade.dto.position.PositionAuthDTO;
import com.born.facade.entity.CompanyPosition;
import com.born.facade.exception.PermissionException;
import com.born.facade.exception.PermissionExceptionEnum;
import com.born.facade.service.ICompanyPositionService;
import com.born.facade.vo.companyPosition.CompanyPositionVO;
import com.born.mapper.CompanyPositionMapper;
import com.born.util.result.RespCode;
import com.born.util.result.Result;
import com.born.util.result.ResultUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @ClassName: CompanyPositionServiceImpl
 * @Description: 职位实现
 * @author AlanMing
 * @date 2018年4月27日
 *
 */
@Slf4j
@Service(version = "1.0.0")
public class CompanyPositionServiceImpl implements ICompanyPositionService {

	@Autowired
	private CompanyPositionMapper mapper;

	/**
	 * 获取职位
	 */
	@Override
	public Result findPosition(Long id) {
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		// 校验参数
		if (null == id) {
			result.setMessage("主键不能为空");
			return result;
		}
		try {
			// 声明对象
			CompanyPosition position = new CompanyPosition();
			position.setId(id);
			ResultUtil.setResult(result, RespCode.Code.SUCCESS, mapper.selectOne(position));
		} catch (Exception e) {
			log.error("查询职位失败(PositionServiceImpl.findPosition).......................", e);
		}
		return result;
	}

	/**
	 * 获取职位集合
	 */
	@Override
	public Result findPositionList(CompanyPositionDTO dto) {
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		CompanyPosition position = new CompanyPosition();
		// 校验参数
		if (dto != null) {
			String errorStr = dto.validateForm();
			if (StringUtils.isNotBlank(errorStr)) {
				result.setMessage(errorStr);
				return result;
			}
			// 转换实体
			BeanUtils.copyProperties(dto, position);
		}
		try {
			ResultUtil.setResult(result, RespCode.Code.SUCCESS, mapper.select(position));
		} catch (Exception e) {
			log.error("查询职位失败（PositionServiceImpl.findPositionList）.......................", e);
		}
		return result;
	}

	/**
	 * 修改职位
	 */
	@Override
	@Transactional
	public Result updatePosition(CompanyPositionDTO dto) {
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		// 校验参数
		if (dto == null) {
			result.setMessage("传入对象不能为空");
			return result;
		}
		CompanyPosition position = new CompanyPosition();
		String errorStr = dto.validateForm();
		if (StringUtils.isNotBlank(errorStr)) {
			result.setMessage(errorStr);
			return result;
		}
		// 转换实体
		BeanUtils.copyProperties(dto, position);
		// 设置时间
		position.setUpdateTime(new Date());
		//先修改职位信息
		try {
			 ResultUtil.setResult(result, RespCode.Code.SUCCESS, mapper.updateByPrimaryKeySelective(position));
		} catch (Exception e) {
			log.error("修改职位失败（PositionServiceImpl.updatePosition）", e);
			throw new PermissionException(PermissionExceptionEnum.UPDATE_POSITION_ERROR);
		}
		//在修改职位权限表
		addPositionAuth(dto.getId().toString(), dto.getPermissionIds(), dto.getUpdaterId().toString());
		return result;
	}

	/**
	 * 添加职位
	 */
	@Override
	@Transactional
	public Result addPosition(CompanyPositionDTO dto) {
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		// 校验参数
		if (dto == null) {
			result.setMessage("传入对象不能为空");
			return result;
		}
		if (dto.getId() != null) {
			result.setMessage("主键参数非法");
			return result;
		}
		CompanyPosition position = new CompanyPosition();
		String errorStr = dto.validateForm();
		if (StringUtils.isNotBlank(errorStr)) {
			result.setMessage(errorStr);
			return result;
		}
		// 转换实体
		BeanUtils.copyProperties(dto, position);
		// 设置时间
		position.setCreateTime(new Date());
		// 设置创建人
		position.setId(null);
		try {
			return ResultUtil.setResult(result, RespCode.Code.SUCCESS, mapper.insertSelective(position));
		} catch (Exception e) {
			log.error("添加职位失败（PositionServiceImpl.addPosition）", e);
			throw new PermissionException(PermissionExceptionEnum.ADD_POSITION_ERROR);
		}
	}

	/**
	 * 逻辑删除职位
	 */
	@Override
	@Transactional
	public Result deletePosition(String ids) {
		// 校验参数
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		if (!StringUtils.isNotBlank(ids)) {
			result.setMessage("传入参数不能为空");
			return result;
		}
		// 修改属性值添加
		CompanyPositionDTO position = new CompanyPositionDTO();
		position.setUpdateTime(new Date());
		position.setUpdaterId(1L);
		String[] idsStr = ids.split(",");
		try {
			// 执行修改
			mapper.batchUpdatePosition(position, idsStr);
			// 返回消息
			return ResultUtil.setResult(result, RespCode.Code.SUCCESS);
		} catch (Exception e) {
			log.error("删除职位失败（PositionServiceImpl.deletePosition）", e);
			throw new PermissionException(PermissionExceptionEnum.DETELE_POSITION_ERROR);
		}
	}

	@Override
	public Result getPageList(CompanyPositionDTO dto) {
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		try {
			PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
			List<CompanyPositionVO> list = mapper.selectPositionList(dto);
			PageInfo<CompanyPositionVO> pageInfo = new PageInfo<>(list);
			result.setData(pageInfo.getList());
			result.setCount(pageInfo.getTotal());
			return ResultUtil.setResult(result, RespCode.Code.SUCCESS);
		} catch (Exception e) {
			log.error("查询分页数据异常", e);
		}
		return result;
	}

	/**
	 * 编辑员工
	 */
	@Override
	public Result editPosition(CompanyPositionDTO dto) {
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		// 校验参数
		if (dto == null) {
			result.setMessage("传入对象不能为空");
			return result;
		}
		// 如果无ID表示为添加
		if (dto.getId() != null) {
			return updatePosition(dto);
		}
		// 如果有ID表示为修改
		if (dto.getId() == null) {
			return addPosition(dto);
		}
		return result;
	}

	@Override
	@Transactional
	public Result addPositionAuth(String pId, String[] authIds, String createrId) {

		try {
			List<PositionAuthDTO> list = new ArrayList<PositionAuthDTO>();

			/**
			 * 转换成list
			 */
			for (String aid : authIds) {
				PositionAuthDTO model = new PositionAuthDTO();
				model.setPositionId(pId);
				model.setAuthorityId(aid);
				model.setCreateTime(new Date());
				model.setCreaterId(createrId);
				list.add(model);
			}

			mapper.deletePositionAuthByPid(pId);
			mapper.batchInsertPosition(list);

			return ResultUtil.getResult(RespCode.Code.SUCCESS);
		} catch (Exception e) {
			return ResultUtil.getResult(RespCode.Code.FAIL);
		}
	}

	@Override
	public Result selectPosition(CompanyPositionDTO dto) {
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		try {
			return ResultUtil.setResult(result, RespCode.Code.SUCCESS,mapper.selectPositionList(dto));
		} catch (Exception e) {
			log.error("查询职位数据异常", e);
		}
		return result;
	}
}
