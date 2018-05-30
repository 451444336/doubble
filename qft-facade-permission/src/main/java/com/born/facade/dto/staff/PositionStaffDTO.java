package com.born.facade.dto.staff;

import java.util.Date;

import javax.persistence.Id;

import com.born.core.base.BaseValidate;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 员工DTO
 * @author 明成
 *
 */
@Data
@ApiModel(value="职位员工管理请求实体")
@EqualsAndHashCode(callSuper = true)
public class PositionStaffDTO extends BaseValidate<PositionStaffDTO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 主键id
	 */
	@Id
	private Long id;
	/**
     * 创建时间
     */
    private Date createTime;
    
    /**
     *  创建人ID
    */
    private Long createrId;

    /**
     * 职位ID
     */
	private Long positionId;
	
	 /**
     * 员工ID
     */
	private Long userId;
}
