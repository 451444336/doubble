package com.born.facade.service.notice;

import com.born.facade.dto.notice.NoticeDTO;
import com.born.facade.entity.notice.Notice;
import com.born.util.result.Result;

/**
 * 
* @ClassName: INoticeService 
* @Description: 公告服务接口
* @author 明成 
* @date 2018年5月24日 上午11:27:23 
* @version 1.0
 */
public interface INoticeService {

	/**
	* @Title: getPageList 
	* @Description: 获取公告列表 
	* @param @return
	* @author 明成
	* @return Result
	* @date 2018年5月24日 上午11:29:32 
	* @throws
	 */
	Result getPageList(NoticeDTO dto);

	/**
	 * 
	* @Title: getNoticeById 
	* @Description: 获取公告对象 
	* @param @param id
	* @param @return
	* @author 明成
	* @return Result
	* @date 2018年5月24日 上午11:30:45 
	* @throws
	 */
	Result getNoticeById(Long id);
	
	/**
	 * 
	* @Title: addNotice 
	* @Description: 添加公告
	* @param @param dto
	* @param @return
	* @author 明成
	* @return Result
	* @date 2018年5月24日 下午1:56:53 
	* @throws
	 */
	Result addNotice(NoticeDTO dto);
	
	/**
	 * 
	* @Title: delNotice 
	* @Description: 删除公告
	* @param @param id
	* @param @return
	* @author 明成
	* @return Result
	* @date 2018年5月24日 下午2:56:51 
	* @throws
	 */
	Result delNotice(Long id);
}
