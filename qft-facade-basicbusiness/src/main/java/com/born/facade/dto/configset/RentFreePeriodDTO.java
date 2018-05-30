package com.born.facade.dto.configset;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 * @ClassName: RentFreePeriodDTO
 * @Description: 免租期模式
 * @author 张永胜
 * @date 2018年5月30日 下午1:50:59
 * @version 1.0
 */
@Data
public class RentFreePeriodDTO implements Serializable {

	/**
	 * 这里字段名简化了的 更新添加都是这个实体
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 标识处理那块的业务 如：1集中、2整租、3合租
	 */
	private int flag;

	/**
	 * 是否同步
	 */
	private Boolean isSynch = false;

	/**
	 * 房东缴费明细生成方式
	 */
	private Long copId;//Contribution of payment 缩写
	private String copValue;
	
	/**
	 * 租客租金生成模式
	 */
	private Long trgId;//Tenant rent generation缩写
	private String trgValue;
	

}
