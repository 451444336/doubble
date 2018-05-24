package com.born.service.impl.notice;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.born.facade.dto.notice.NoticeDTO;
import com.born.facade.entity.notice.Notice;
import com.born.facade.exception.BasicBusinessException;
import com.born.facade.exception.BasicBusinessExceptionEnum;
import com.born.facade.service.notice.INoticeService;
import com.born.mapper.NoticeMapper;
import com.born.util.result.RespCode;
import com.born.util.result.Result;
import com.born.util.result.ResultUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @ClassName: DicServiceImpl
 * @Description: 公告实现
 * @author 明成
 * @date 2018年5月24日 下午1:58:33
 * @version 1.0
 */
@Slf4j
@Service(version = "1.0.0")
public class NoticeServiceImpl implements INoticeService {

	@Autowired
	private NoticeMapper noticeMapper;

	@Override
	public Result getPageList(NoticeDTO dto) {
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		String errorStr = dto.validateForm();
		if (StringUtils.isNotBlank(errorStr)) {
			result.setMessage(errorStr);
			return result;
		}
		try {
			PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
	        List<Notice> list = noticeMapper.selectPageList(dto);
	        PageInfo<Notice> pageInfo = new PageInfo<>(list);
	        result.setData(pageInfo.getList());
			result.setCount(pageInfo.getTotal());
			ResultUtil.setResult(result, RespCode.Code.SUCCESS);
		} catch (Exception e) {
			log.error("查询公告列表失败（NoticeServiceImpl.getPageList）", e);
		}
		return result;
	}

	@Override
	public Result getNoticeById(Long id) {
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		if (null==id) {
			result.setMessage("ID不能为空");
			return result;
		}
		try {
			Notice notice = new Notice();
			notice.setId(id);
			ResultUtil.setResult(result,RespCode.Code.SUCCESS, noticeMapper.selectOne(notice));
		} catch (Exception e) {
			log.error("获取公告失败（NoticeServiceImpl.getNoticeById）", e);
		}
		return result;
	}

	@Override
	@Transactional
	public Result addNotice(NoticeDTO dto) {
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		String errorStr = dto.validateForm();
		if (StringUtils.isNotBlank(errorStr)) {
			result.setMessage(errorStr);
			return result;
		}
		try {
			Notice notice = new Notice();
			BeanUtils.copyProperties(dto, notice);
			notice.setCreateTime(new Date());
			return ResultUtil.setResult(result, RespCode.Code.SUCCESS, noticeMapper.insertSelective(notice));
		} catch (Exception e) {
			log.error("添加公告失败（NoticeServiceImpl.addNotice）", e);
			throw new BasicBusinessException(BasicBusinessExceptionEnum.ADD_NOTICE_ERROR);
		}
	}

	@Override
	public Result delNotice(Long id) {
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		if (null==id) {
			result.setMessage("ID不能为空");
			return result;
		}
		try {
			ResultUtil.setResult(result,RespCode.Code.SUCCESS, noticeMapper.deleteNotice(id));
		} catch (Exception e) {
			log.error("删除公告失败（NoticeServiceImpl.delNotice）", e);
		}
		return result;
	}

}
