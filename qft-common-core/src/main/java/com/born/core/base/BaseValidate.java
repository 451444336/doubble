/**
 * 
 */
package com.born.core.base;


import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.born.core.entity.EntityClone;
/**
 * 
* @ClassName: BaseValidate  
* @Description: 必填校验  
* @author lijie
* @date 2018年4月25日  
*
 */
public class BaseValidate<T> extends EntityClone<T> {

	private static final long serialVersionUID = -5300113985007593228L;

	/**
	 * 
	* @Title: validateForm  
	* @Description: 验证FormBean  
	* @param @return    参数  
	* @return String    返回类型  
	* @author lijie
	* @throws
	 */
	public String validateForm() {

		return this.validateForm((Class<?>[]) null);
	}

	/**
	 * 
	* @Title: validateForm  
	* @Description: 验证FormBean 
	* @param @param clazz
	* @param @return    参数  
	* @return String    返回类型  
	* @author lijie
	* @throws
	 */
	public String validateForm(Class<?>... clazz) {
		final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		@SuppressWarnings("rawtypes")
		Set<ConstraintViolation<BaseValidate>> violation;
		if (clazz == null) {
			violation = validator.validate(this);
		} else {
			violation = validator.validate(this, clazz);
		}
		return violation.stream().findFirst().map(ConstraintViolation::getMessage).orElse(null);
	}

}
