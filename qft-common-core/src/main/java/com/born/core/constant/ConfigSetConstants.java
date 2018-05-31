package com.born.core.constant;

/**
 * 
 * 注意:枚举类中每个key 都是Redis中取出来转换Map后的Key值
 * 这里key用于转换Map后各个服务中获取缓存中的通用设置
 * 
 * @ClassName: ConfigSetConstants
 * @Description: 通用设置枚举
 * @author 张永胜
 * @date 2018年5月29日 下午4:50:32
 * @version 1.0
 */
public interface ConfigSetConstants {

	/** 集中 */
	String focus = "focus";
	/** 合租 */
	String cotenant = "cotenant";
	/** 整租 */
	String housing = "housing";

	String getKey();

	String getDescribe();

	/**
	 * 
	 * @ClassName: DefaultSet
	 * @Description: 默认设置板块
	 * @author 张永胜
	 * @date 2018年5月29日 下午5:07:20
	 * @version 1.0
	 */
	enum DefaultSet implements ConfigSetConstants {

		LANDLORD_ADVANCE_DAY("landlord_advance_day", "房东缴费提前时间设置的天数"),
		LANDLORD_IS_FIXED_SET("landlord_is_fixed_set", "房东固定缴费时间设置勾选"),
		LANDLORD_FIXED_DAY_DATA("landlord_fixed_day_data", "固定缴费时间的合同数据"),
		TENANTS_ADVANCE_DAY("tenants_advance_day", "租客缴费提前时间设置的天数"),
		DOWN_LOAD_TYPE("down_load_type", "合同下载类型设置单选"),
		LANDLORD_EJECT_SET("landlord_eject_set", "房东-录入保存后弹出设置勾选"),
		TENANTS_EJECT_SET("tenants_eject_set", "租客-录入保存后弹出设置勾选"),
		DEPOSIT_SET("deposit_set", "押金默认值设置勾选"),
		PROPERTY_STORAGE_FEE_SET("property_storage_fee_set", "物业、预存费默认值设置勾选"),
		PROPERTY_MANAGEMENT_FEE("property_management_fee", "物管费"),
		PROPERTY_MANAGEMENT_FEE_MODE("property_management_fee_mode", "物管费-缴费方式"),
		PRE_DEPOSIT_FEE("pre_deposit_fee", "预存费"),
		PRE_DEPOSIT_FEE_MODE("pre_deposit_fee_mode", "预存费-缴费方式"),
		SERVICE_TYPE_SET("service_type_set", "按金额/按百分比单选"),
		SERVICE_CHARGE("service_charge", "服务费:金额或百分比值"),
		SERVICE_CHARGE_MODE("service_charge_mode", "服务费-缴费方式");

		/** 标识 */
		private final String key;
		/** 描述 */
		private final String describe;

		DefaultSet(String key, String describe) {
			this.key = key;
			this.describe = describe;
		}

		@Override
		public String getKey() {
			return this.key;
		}

		@Override
		public String getDescribe() {
			return this.describe;
		}
	}

	/**
	 * 
	 * @ClassName: RentFreePeriodMode
	 * @Description: 免租期模式
	 * @author 张永胜
	 * @date 2018年5月30日 下午2:47:55
	 * @version 1.0
	 */
	enum RentFreePeriodMode implements ConfigSetConstants {

		RENT_FREE_PERIOD_MODE("rent_free_period_mode", "免租期模式"),
		TENANT_RENT_GENERATION("tenant_rent_generation", "租客租金生成模式");

		/** 标识 */
		private final String key;
		/** 描述 */
		private final String describe;

		RentFreePeriodMode(String key, String describe) {
			this.key = key;
			this.describe = describe;
		}

		@Override
		public String getKey() {
			return this.key;
		}

		@Override
		public String getDescribe() {
			return this.describe;
		}
	}

	/**
	 * 
	* @ClassName: FixPriceMode 
	* @Description: 定价设置
	* @author 张永胜 
	* @date 2018年5月31日 下午3:23:06 
	* @version 1.0
	 */
	enum FixPriceMode implements ConfigSetConstants {
		LOW_FIX_PRICE_NO_IN("low_fix_price_no_in", "低于定价出租，不能录入信息"),
		LOW_DEPOSIT_NO_IN("low_deposit_no_in", "低于规定押金出租，不能录入信息"),
		OUT_ROOM_IS_SAVE_FIX_PRICE("out_room_is_save_fix_price", "启用退房后仍保留之前定价");

		/** 标识 */
		private final String key;
		/** 描述 */
		private final String describe;

		FixPriceMode(String key, String describe) {
			this.key = key;
			this.describe = describe;
		}

		@Override
		public String getKey() {
			return this.key;
		}

		@Override
		public String getDescribe() {
			return this.describe;
		}

	}
}
