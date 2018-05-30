package com.born.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.born.entity.dic.DicItem;
import com.born.facade.dto.dic.DicItemDTO;
import com.born.facade.dto.dic.UpdateDicItemDTO;
import com.born.facade.dto.dic.UpdateDicItemSortDTO;
import com.born.facade.exception.DicException;
import com.born.facade.service.dic.IDicService;
import com.born.facade.vo.dic.DicItemSortVO;
import com.born.facade.vo.dic.DicItemVO;
import com.born.facade.vo.dic.DicMenuVO;
import com.born.mapper.DicItemMapper;
import com.born.mapper.DicMapper;
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
public class DicServiceImpl implements IDicService {

	@Autowired
	private DicMapper dicMapper;

	@Autowired
	private DicItemMapper dicItemMapper;

	private String companyId = "Company_20170319112315J3Awn";

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
			if (params != null) {
				Result result = ResultUtil.getResult(RespCode.Code.FAIL);
				PageHelper.startPage(params.getPageNum(), params.getPageSize());
				List<DicItemVO> list = dicMapper.selectDicItemList(params.getPId(), companyId);
				PageInfo<DicItemVO> pageInfo = new PageInfo<>(list);
				result.setData(pageInfo.getList());
				result.setCount(pageInfo.getTotal());
				if (list.size() == 0) {
					return ResultUtil.getResult(RespCode.Code.SUCCESS, "获取数据为空");
				}
				return ResultUtil.setResult(result, RespCode.Code.SUCCESS);
			}
			return ResultUtil.getResult(RespCode.Code.FAIL);
		} catch (Exception e) {
			log.error("获取字典数据异常", e);
			return ResultUtil.getResult(RespCode.Code.FAIL);
		}
	}

	@Override
	@Transactional
	public Result updateDicItemById(UpdateDicItemDTO updateDicItemDTO) {
		log.info("更新字典参数 {}", updateDicItemDTO.getId(), updateDicItemDTO.getName());
		if (StringUtils.isBlank(updateDicItemDTO.getId()) || StringUtils.isBlank(updateDicItemDTO.getName())) {
			return ResultUtil.getResult(RespCode.Code.FAIL);
		}
		try {
			int i = dicItemMapper.updateDicItemById(updateDicItemDTO.getId(),
					StringEscapeUtils.escapeHtml4(updateDicItemDTO.getName()), companyId);
			if (i > 0) {
				return ResultUtil.getResult(RespCode.Code.SUCCESS);
			}
		} catch (Exception e) {
			log.error("更新字典数据异常", e);
		}
		return ResultUtil.getResult(RespCode.Code.FAIL);
	}

	@Override
	@Transactional
	public Result addDicItem(String pId, String dicItem)  {
		try {

			if (StringUtils.isBlank(pId) || StringUtils.isBlank(dicItem)) {
				return ResultUtil.getResult(RespCode.Code.FAIL);
			}

			String[] data = dicItem.split(",");

			List<DicItem> datalist = new ArrayList<DicItem>(data.length);

			for (int i = 0; i < data.length; i++) {
				DicItem item = new DicItem();
				item.setId(String.valueOf(UUID.randomUUID()));
				item.setCompanyid(companyId);
				item.setCreatetime(String.valueOf(new Date().getTime()));
				item.setDicRank("1");
				item.setDiname(StringEscapeUtils.escapeHtml4(data[i]));
				item.setIsdefault("1");
				item.setIspubDic("2");
				item.setOrdernum("0");
				item.setParentid(pId);
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
	public Result deleteDicItemByIds(String ids) {
		try {
			log.error("批量删除参数ID {}", ids);
			if (StringUtils.isBlank(ids)) {
				return ResultUtil.getResult(RespCode.Code.FAIL);
			}
			String[] data = ids.split(",");
			int a = dicItemMapper.batchDeleteItemByIds(companyId, data);
			int b = dicItemMapper.batchDeleteSubItemByIds(companyId, data);
			if (a > 0) {
				log.error("批量删除二级字典返回结果 {} ", b > 0 ? true : false);
				return ResultUtil.getResult(RespCode.Code.SUCCESS);
			}
		} catch (Exception e) {
			log.error("批量删除字典数据异常", e);
		}
		return ResultUtil.getResult(RespCode.Code.FAIL);
	}

	@Override
	@Transactional
	public Result addDicSubItem(String pId, String dicSubItem) {
		try {

			if (StringUtils.isBlank(pId) || StringUtils.isBlank(dicSubItem)) {
				return ResultUtil.getResult(RespCode.Code.FAIL);
			}

			String[] data = dicSubItem.split(",");

			List<DicItem> datalist = new ArrayList<DicItem>(data.length);

			for (int i = 0; i < data.length; i++) {
				DicItem item = new DicItem();
				item.setId(String.valueOf(UUID.randomUUID()));
				item.setCompanyid(companyId);
				item.setCreatetime(String.valueOf(new Date().getTime()));
				item.setDicRank("2");
				item.setDiname(StringEscapeUtils.escapeHtml4(data[i]));
				item.setIsdefault("1");
				item.setIspubDic("2");
				item.setOrdernum("0");
				item.setParentid(pId);
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
	public Result deleteDicItemById(String id) {
		try {
			log.error("删除参数ID {}", id);
			if (StringUtils.isBlank(id)) {
				return ResultUtil.getResult(RespCode.Code.FAIL);
			}
			int a = dicItemMapper.deleteItemById(companyId, id);
			if (a > 0) {
				log.error("删除二级字典返回结果 [成功]");
				return ResultUtil.getResult(RespCode.Code.SUCCESS);
			}
		} catch (Exception e) {
			log.error("删除字典数据异常", e);
		}
		return ResultUtil.getResult(RespCode.Code.FAIL);
	}

	@Override
	public Result findDicItemAllById(String pId, String rank) {
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
			return ResultUtil.getResult(RespCode.Code.FAIL);
		}
	}

}
