package com.born.controller.notice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.born.config.shiro.token.TokenManager;
import com.born.facade.dto.notice.NoticeDTO;
import com.born.facade.service.notice.INoticeService;
import com.born.facade.vo.UserInfoVO;
import com.born.util.result.Result;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @ClassName: NoticeController
 * @Description: 公告视图层
 * @author 明成
 * @date 2018年5月24日 下午1:49:12
 * @version 1.0
 */
@Controller
@RequestMapping(value = "web/not")
public class NoticeController {

	@Reference(version = "1.0.0")
	private INoticeService noticeService;

	/**
	 * 
	 * @Title: getPageList @Description: 分页查询公告列表" @param @param
	 * dto @param @return @author 明成 @return Result @date 2018年5月9日
	 * 上午11:30:51 @throws
	 */
	@ApiOperation(value = "分页查询公告列表", notes = "确保填写参数是否正确")
	@ApiResponses(value = { @ApiResponse(code = 10100, message = "请求参数有误"),
			@ApiResponse(code = 200, message = "操作成功") })
	@GetMapping("/getPageList")
	@ResponseBody
	public Result getPageList(NoticeDTO dto) {
		return noticeService.getPageList(dto);
	}

	/**
	 * 根据ID获取公告
	 * 
	 * @param dto
	 *            参数对象
	 * @return Result 返回对象
	 */
	@ApiOperation(value = "根据ID获取公告", notes = "参数填写")
	@ApiResponses(value = { @ApiResponse(code = 10100, message = "请求参数有误"),
			@ApiResponse(code = 200, message = "操作成功") })
	@GetMapping("/getNotice/{id}")
	@ResponseBody
	public Result getNotice(@PathVariable(name = "id") Long id) {
		return noticeService.getNoticeById(id);
	}

	/**
	 * 添加公告
	 * 
	 * @param dto
	 *            参数对象
	 * @return Result 返回对象
	 */
	@ApiOperation(value = "添加公告", notes = "确保填写参数是否正确")
	@ApiResponses(value = { @ApiResponse(code = 10100, message = "请求参数有误"),
			@ApiResponse(code = 200, message = "操作成功") })
	@ResponseBody
	@RequestMapping(value = "addNotice", method = RequestMethod.POST)
	public Result addNotice(NoticeDTO dto) {
		UserInfoVO user = TokenManager.getLoginUser();
		dto.setCreaterId(user.getId());
		dto.setCompanyId(user.getCompanyId());
		return noticeService.addNotice(dto);
	}

	/**
	 * 删除公告
	 * 
	 * @param dto
	 *            参数对象
	 * @return Result 返回对象
	 */
	@ApiOperation(value = "删除公告", notes = "确保填写参数是否正确")
	@ApiResponses(value = { @ApiResponse(code = 10100, message = "请求参数有误"),
			@ApiResponse(code = 200, message = "操作成功") })
	@ResponseBody
	@RequestMapping(value = "delNotice/{id}", method = RequestMethod.POST)
	public Result delNotice(@PathVariable(name = "id") Long id) {
		return noticeService.delNotice(id);
	}
}
