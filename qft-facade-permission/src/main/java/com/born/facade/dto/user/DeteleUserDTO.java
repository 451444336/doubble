package com.born.facade.dto.user;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
/**
 * 
* @ClassName: DeteleUserDTO 
* @Description: 用户请求传输DTO 
* @author 明成 
* @date 2018年5月2日 上午10:58:40 
*
 */
@Data
public class DeteleUserDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 用户id
     */
    private Long id;
    
    /**
     * 修改人
     */
    private Long updaterId;
    
    /**
     * 修改时间
     */
    private Date updateTime;
}
