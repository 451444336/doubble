package com.born.util.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.ValueFilter;

/**
 * 
 * @ClassName: Cast
 * @Description: 转换工具
 * @author 张永胜
 * @date 2018年5月8日 下午2:59:38
 * @version 1.0
 */
public class Cast {

	/**
	 * 
	 * @Title: cast
	 * @Description: 泛型null处理再转换泛型
	 * @param obj
	 *            数据对象
	 * @param isNullValue
	 *            是否NULL值转换
	 * @return
	 * @author 张永胜
	 * @return T
	 * @date 2018年5月11日 下午2:21:48
	 */
	@SuppressWarnings("unchecked")
	public static <T> T cast(T obj, boolean isNullValue) {
		if (obj != null && isNullValue != false) {
			if (String.class.isAssignableFrom(obj.getClass()) || Boolean.class.isAssignableFrom(obj.getClass())
					|| Integer.class.isAssignableFrom(obj.getClass()) || Long.class.isAssignableFrom(obj.getClass())
					|| float.class.isAssignableFrom(obj.getClass()) || int.class.isAssignableFrom(obj.getClass())
					|| char.class.isAssignableFrom(obj.getClass()) || byte.class.isAssignableFrom(obj.getClass())
					|| short.class.isAssignableFrom(obj.getClass())) {
				return obj;
			}

			/**
			 * 对象或list数值 去null转换
			 */
			String json = JSONObject.toJSONString(obj, filter);

			/**
			 * 转换反斜杠
			 */
			return (T) JSON.parse(json);
		}

		return obj;
	}

	private static ValueFilter filter = new ValueFilter() {
		@Override
		public Object process(Object obj, String s, Object v) {
			if (v == null)
				return "";
			return v;
		}
	};
}
