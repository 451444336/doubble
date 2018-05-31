package com.born.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.born.core.base.BaseModel;
import com.born.core.base.BaseService;
import com.born.core.base.DataBaseParameters;
import com.born.core.constant.DataBaseEnum;
import com.born.entity.dic.DicItem;
import com.born.facade.dto.dic.AddDicItemDTO;
import com.born.facade.dto.dic.DicItemDTO;
import com.born.facade.dto.dic.UpdateDicItemDTO;
import com.born.facade.dto.dic.UpdateDicItemSortDTO;
import com.born.facade.exception.DicExcepionEnum;
import com.born.facade.exception.DicException;
import com.born.facade.service.dic.IDicService;
import com.born.facade.vo.dic.DicItemSortVO;
import com.born.facade.vo.dic.DicItemVO;
import com.born.facade.vo.dic.DicMenuVO;
import com.born.mapper.DicItemMapper;
import com.born.mapper.MenuDicMapper;
import com.born.util.result.RespCode;
import com.born.util.result.Result;
import com.born.util.result.ResultUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * 字典服务
 * 
 * @ClassName: DicServiceImpl
 * @Description: 字典服务
 * @author 张永胜
 * @date 2018年5月17日 下午5:04:37
 * @version 1.0
 */
@Slf4j
@Service(version = "1.0.0")
public class DicServiceImpl extends BaseService<BaseModel, DicItem> implements IDicService {

	@Autowired
	private MenuDicMapper dicMapper;

	@Autowired
	private DicItemMapper dicItemMapper;
	
	@Override
	public Result findDicZtree() {
		try {
			List<DicMenuVO> list = dicMapper.selectDicZtree();
			if (list.size() == 0) {
				return ResultUtil.getResult(RespCode.Code.SUCCESS, "获取数据为空");
			}
			return ResultUtil.getResult(RespCode.Code.SUCCESS, list);
		} catch (Exception e) {
			log.error("获取字典数据异常", e);
			return ResultUtil.getResult(RespCode.Code.FAIL);
		}
	}

	@Override
	public Result findDicItemList(DicItemDTO params) {
		try {
			String errorStr = params.validateForm();
			if (StringUtils.isNotBlank(errorStr)) {
				return ResultUtil.requestDataError(errorStr);
			} else {
				Result result = ResultUtil.getResult(RespCode.Code.FAIL);
				PageHelper.startPage(params.getPageNum(), params.getPageSize());
				List<DicItemVO> list = dicMapper.selectDicItemList(params.getPId(), params.getCompanyId());
				PageInfo<DicItemVO> pageInfo = new PageInfo<>(list);
				result.setData(pageInfo.getList());
				result.setCount(pageInfo.getTotal());
				if (list.size() == 0) {
					return ResultUtil.getResult(RespCode.Code.SUCCESS, "获取数据为空");
				}
				return ResultUtil.setResult(result, RespCode.Code.SUCCESS);
			}
		} catch (Exception e) {
			log.error("获取字典数据异常", e);
			return ResultUtil.getResult(RespCode.Code.FAIL);
		}
	}

	@Override
	@Transactional
	public Result updateDicItemById(UpdateDicItemDTO updateDicItemDTO) {
		log.info("更新字典参数 = {}", JSON.toJSONString(updateDicItemDTO));
		try {
			String errorStr = updateDicItemDTO.validateForm();
			if (StringUtils.isNotBlank(errorStr)) {
				return ResultUtil.requestDataError(errorStr);
			} else {
				int i = dicItemMapper.updateDicItemById(updateDicItemDTO.getId(), StringEscapeUtils.escapeHtml4(updateDicItemDTO.getName()), 
						updateDicItemDTO.getCompanyId());
				if (i > 0) {
					return ResultUtil.getResult(RespCode.Code.SUCCESS);
				}
			}
		} catch (Exception e) {
			log.error("更新字典数据异常", e);
		}
		return ResultUtil.getResult(RespCode.Code.FAIL);
	}

	@Override
	@Transactional
	public Result addDicItem(AddDicItemDTO dto) {
		log.info("添加子级菜单数据入参={}", JSON.toJSONString(dto));
		try {
			String errorStr = dto.validateForm();
			if (StringUtils.isNotBlank(errorStr)) {
				return ResultUtil.requestDataError(errorStr);
			}
			String[] data = dto.getDicItem().split(",");
			List<DicItem> datalist = new ArrayList<DicItem>(data.length);
			DicItem item;
			for (int i = 0; i < data.length; i++) {
				item = new DicItem();
				item.setCompanyId(dto.getCompanyId());
				item.setCreateTime(new Date());
				item.setCreaterId(dto.getCtraterId());
				item.setDicRank(dto.getDicRank());
				item.setDiname(StringEscapeUtils.escapeHtml4(data[i]));
				item.setIsPossibleDel((byte) 1);
				item.setIspubDic(2);
				item.setOrderNum(0);
				item.setParentId(Long.valueOf(dto.getPId()));
				item.setIsDelete(DataBaseEnum.NOT_DELETE.getStatus());
				datalist.add(item);
			}
			int i = dicItemMapper.insertList(datalist);
			if (i > 0) {
				return ResultUtil.getResult(RespCode.Code.SUCCESS);
			}
		} catch (Exception e) {
			log.error("添加字典数据异常", e);
			throw new DicException("添加字典数据异常");
		}
		return ResultUtil.getResult(RespCode.Code.FAIL);
	}

	@Override
	@Transactional
	public Result deleteDicItemByIds(String ids, Long companyId, Long updaterId) {
		try {
			log.error("批量删除参数ID {}", ids);
			if (StringUtils.isBlank(ids)) {
				return ResultUtil.getResult(RespCode.Code.FAIL);
			}
			String[] data = ids.split(",");
			List<Long> delIds = new ArrayList<>(data.length);
			for (String str : data) {
				delIds.add(Long.valueOf(str));
			}
			int a = dicItemMapper.batchDeleteItemByIds(companyId, delIds, updaterId);
			int b = dicItemMapper.batchDeleteSubItemByIds(companyId, delIds, updaterId);
			if (a > 0) {
				log.error("批量删除二级字典返回结果 {} ", b > 0 ? true : false);
				return ResultUtil.getResult(RespCode.Code.SUCCESS);
			}
		} catch (Exception e) {
			log.error("批量删除字典数据异常", e);
			throw new DicException("批量删除字典数据异常");
		}
		return ResultUtil.getResult(RespCode.Code.FAIL);
	}

	@Override
	public Result findDicItemAllById(Long pId, Integer rank, Long companyId) {
		try {
			List<DicItemSortVO> list = dicItemMapper.selectItemAllById(companyId, pId, rank);
			if (list.size() == 0) {
				return ResultUtil.getResult(RespCode.Code.SUCCESS, "获取数据为空");
			}
			for (DicItemSortVO dicItemSortVO : list) {
				dicItemSortVO.setCount(list.size());
			}
			return ResultUtil.getResult(RespCode.Code.SUCCESS, list);
		} catch (Exception e) {
			log.error("获取字典数据异常", e);
			return ResultUtil.getResult(RespCode.Code.FAIL);
		}
	}

	@Override
	@Transactional
	public Result updateDicItemSort(List<UpdateDicItemSortDTO> list) {
		try {
			int res = dicItemMapper.batchUpdateDicItemSortById(list);
			if (res > 0) {
				return ResultUtil.getResult(RespCode.Code.SUCCESS);
			}
			return ResultUtil.getResult(RespCode.Code.FAIL);
		} catch (Exception e) {
			log.error("更新字典排序数据异常", e);
			throw new DicException("更新字典排序数据异常");
		}
	}

	@Override
	protected DataBaseParameters<DicItem> getDataBaseParameters() {
		DataBaseParameters<DicItem> result = new DataBaseParameters<>();
		result.setEntityClass(DicItem.class);
		result.setMapper(dicItemMapper);
		return result;
	}

	@Override
	@Transactional
	public Result delByIdAndIsPossible(Long id, Long userId) {
		log.info("根据ID删除字典入参 = {},{}", id, userId);
		try {
			if (null == id) {
				return ResultUtil.requestDataError("字典ID不能为空");
			}
			if (null == userId) {
				return ResultUtil.requestDataError("操作人ID不能为空");
			}
			DicItem record = new DicItem();
			record.setId(id);
			record.setIsPossibleDel((byte) 2);
			record = dicItemMapper.selectOne(record);
			if (null == record) {
				return ResultUtil.fail(DicExcepionEnum.UNABLE_DEL);
			}
			DicItem del = new DicItem();
			del.setId(id);
			del.setUpdaterId(userId);
			del.setUpdateTime(new Date());
			del.setIsDelete(DataBaseEnum.DELETE.getStatus());
			return ResultUtil.success(dicItemMapper.updateByPrimaryKeySelective(del));
		} catch (Exception e) {
			log.error("根据ID删除字典数据异常", e);
			throw new DicException(DicExcepionEnum.DEL_DIC_ITEM_ERROR);
		}
	}

	@Override
	public Result getDicMenus(Long companyId) {
		log.info("根据公司ID获取字典菜单数据入参={}", companyId);
		try {

			return null;
		} catch (Exception e) {
			log.error("根据公司ID获取字典菜单数据异常", e);
			return ResultUtil.fail(RespCode.Code.INTERNAL_SERVER_ERROR);
		}
	}

}
