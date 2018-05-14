/**
 *
 */
package com.born.core.base;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 
* @ClassName: BaseVO
* @Description:baseVO类
* @author 明成
* * @date 2018年4月27日 上午11:08:10 
*
 */
@Data
public class BaseVO implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * 创建时间
     */
    private Date createTime;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 创建人ID
     */
    private Long createrId;
    /**
     * 修改人ID
     */
    private Long updaterId;
    /**
     * 删除状态
     */
    private Integer IsDelete;
    
    
}