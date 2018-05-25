package com.born.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.born.core.base.BaseMapper;
import com.born.entity.notice.Notice;
import com.born.facade.dto.notice.NoticeDTO;

/**
 * 
* @ClassName: NoticeMapper 
* @Description: 公告数据层
* @author 明成 
* @date 2018年5月24日 下午2:16:21 
* @version 1.0
 */
@Repository
public interface NoticeMapper extends BaseMapper<Notice> {

	/**
	* @Title: getPageList 
	* @Description: 获取公告列表 
	* @param @return
	* @author 明成
	* @return List
	* @date 2018年5月24日 上午11:29:32 
	* @throws
	 */
	List<Notice> selectPageList(NoticeDTO dto);

	/**
	 * 
	* @Title: getNoticeById 
	* @Description: 获取公告对象 
	* @param @param id
	* @param @return
	* @author 明成
	* @return Notice
	* @date 2018年5月24日 上午11:30:45 
	* @throws
	 */
	Notice selectNoticeById(Long id);
	
	/**
	 * 
	* @Title: addNotice 
	* @Description: 添加公告
	* @param @param dto
	* @param @return
	* @author 明成
	* @return Integer
	* @date 2018年5月24日 下午1:56:53 
	* @throws
	 */
	Integer insertNotice(NoticeDTO dto);
	
	/**
	 * 
	* @Title: deleteNotice 
	* @Description: 逻辑删除公告
	* @param @param id
	* @param @return
	* @author 明成
	* @return Integer
	* @date 2018年5月24日 下午3:01:02 
	* @throws
	 */
	Integer deleteNotice(@Param("id")Long id);
}
