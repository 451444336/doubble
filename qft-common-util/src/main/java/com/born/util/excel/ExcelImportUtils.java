package com.born.util.excel;


import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.born.util.ClassUtils;
import com.born.util.date.SyncDateUtils;

/**
 * 
* @ClassName: ExcelImportUtil  
* @Description: excel 导入工具类  
* @author lijie  
* @date 2017年3月22日  
*
 */
public class ExcelImportUtils {

	/**
	 * 格式化数字
	 */
	private static final DecimalFormat NF = new DecimalFormat("#0");

	private ExcelImportUtils(){}
	/**
	 * 
	* @Title: getExcelImportInfos 
	* @Description: 跟据导入信息返回实体集合（底层集合是链表）
	* @param @param clas
	* @param @param names
	* @param @param request
	* @param @return
	* @param @throws Exception    设定文件 
	* @return List<T>    返回类型 
	* @author lijie
	* @throws
	 */
	public static <T> List<T> getExcelImportInfos(Class<T> clas, String[] names, HttpServletRequest request) throws Exception {
		List<T> ts = null;
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> multipartFiles = multiRequest.getFileMap();
		if (multipartFiles != null) {
			for (Map.Entry<String, MultipartFile> entry : multipartFiles.entrySet()) {
				MultipartFile mfile = entry.getValue();
				InputStream in = mfile.getInputStream();
				String filePath = mfile.getOriginalFilename();
				ts = ExcelImportUtils.readExcel(clas, names, in, filePath);
			}
		}
		return ts;
	}
	/**
	 * @describe: cls 为需要转换的对象、names 为该对象与excel表格对应的字段名
	 * @param cls
	 * @param names 备注：此处的filePath 用于校验文件格式用途
	 * @return 返回对应的实体类
	 * @throws Exception
	 */
	public static <T> List<T> readExcel(Class<T> cls, String[] names, InputStream in, String filePath)
			throws Exception {
		if (names == null || names.length == 0) {
			throw new RuntimeException("参数 names 不能为空 必须为excel 表格对应字段必须为映射的实体类的字段名");
		}
		if (isExcelXls(filePath)) {
			return result(cls, readXls(in), names, false);
		} else if (isExcelxlsx(filePath)) {
			return result(cls, readXlsx(in), names, false);
		} else {
			throw new IOException("暂不支持该文件类型");
		}
	}
	/**
	 * 
	* @Title: readExcel  
	* @Description: TODO 
	* @param: @param cls
	* @param: @param names
	* @param: @param in
	* @param: @param filePath
	* @param: @param strFilter
	* @param: @return
	* @param: @throws Exception
	* @return List<T>
	* @author lijie
	* @throws
	 */
	public static <T> List<T> readExcel(Class<T> cls, String[] names, InputStream in, String filePath,
			boolean strFilter) throws Exception {
		if (names == null || names.length == 0) {
			throw new RuntimeException("参数 names 不能为空 必须为excel 表格对应字段必须为映射的实体类的字段名");
		}
		if (isExcelXls(filePath)) {
			return result(cls, readXls(in), names, strFilter);
		} else if (isExcelxlsx(filePath)) {
			return result(cls, readXlsx(in), names, strFilter);
		} else {
			throw new IOException("暂不支持该文件类型");
		}
	}

	/**
	 * 
	 * @Title: isExcelXls @Description: @param @param filePath @param @return
	 * 参数 @return boolean 返回类型 @throws
	 */
	public static boolean isExcelXls(String filePath) {
		return filePath.matches("^.+\\.(?i)(xls)$");
	}

	/**
	 * 判断是否是xlsx版excel
	 * 
	 * @param filePath
	 *            文件路径
	 * @return 结果
	 */
	public static boolean isExcelxlsx(String filePath) {
		return filePath.matches("^.+\\.(?i)(xlsx)$");
	}

	/**
	 * 读取xls 格式的excel 文件
	 * 
	 * @param in
	 * @return
	 * @throws IOException
	 */
	private static List<List<Object>> readXls(InputStream in) throws IOException {
		final List<List<Object>> result = new LinkedList<List<Object>>();
		HSSFWorkbook hwb = new HSSFWorkbook(in);
		int sheetNum = hwb.getNumberOfSheets();
		List<List<Object>> retList;
		for (int i = 0; i < sheetNum; i++) {
			retList = getValueBySheets(hwb.getSheetAt(i));
			if (retList == null || retList.isEmpty()) {
				continue;
			}
			result.addAll(retList);
		}
		return result;
	}

	/**
	 * 
	* @Title: getValueBySheets  
	* @Description:读取xls 每个sheet 内容
	* @param @param sheet
	* @param @return    参数  
	* @return List<List<Object>>    返回类型  
	* @throws
	 */
	private static List<List<Object>> getValueBySheets(HSSFSheet sheet) {
		final List<List<Object>> result = new LinkedList<List<Object>>();
		HSSFRow row;
		int counter = 0;
		List<Object> linked;
		for (int i = sheet.getFirstRowNum(); counter < sheet.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			if (row == null) {
				continue;
			} else {
				counter++;
			}
			if (counter == 1) {
				continue;
			}
			linked = getCellValues(row);
			if (linked == null || linked.isEmpty())
			{
				continue;
			}
			result.add(linked);
		}
		return result;
	}
	/**
	 * 
	* @Title: getCellValues  
	* @Description: 读取xls 每个Cell 内容
	* @param @param row
	* @param @return    参数  
	* @return List<Object>    返回类型  
	* @throws
	 */
	private static List<Object> getCellValues(HSSFRow row) {
		final List<Object> result = new LinkedList<Object>();
		HSSFCell cell;
		for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
			cell = row.getCell(j);
			if (cell == null) {
				if (j != row.getLastCellNum())
				{
					result.add(null);
				}
				continue;
			}
			result.add(getValue(cell.getCellType(), cell));
		}
		return result;
	}
	/**
	 * 
	* @Title: readXlsx  
	* @Description:读取Xlsx 所有sheet 内容
	* @param @param in
	* @param @return
	* @param @throws IOException    参数  
	* @return List<List<Object>>    返回类型  
	* @throws
	 */
	private static List<List<Object>> readXlsx(InputStream in) throws IOException {
		final List<List<Object>> result = new LinkedList<List<Object>>();
		XSSFWorkbook xwb = new XSSFWorkbook(in);
		int sheetNum = xwb.getNumberOfSheets();
		List<List<Object>> retList;
		for (int i = 0; i < sheetNum; i++) {
			retList = getValueByXSheets(xwb.getSheetAt(i));
			if (retList == null || retList.isEmpty())
			{
				continue;
			}
			result.addAll(retList);
		}
		return result;
	}
	/**
	 * 
	* @Title: getValueByXSheets  
	* @Description: 读取每个sheet 内容  
	* @param @param sheet
	* @param @return    参数  
	* @return List<List<Object>>    返回类型  
	* @throws
	 */
	private static List<List<Object>> getValueByXSheets(XSSFSheet sheet) {
		final List<List<Object>> result = new LinkedList<List<Object>>();
		XSSFRow row = null;
		int counter = 0;
		List<Object> list;
		for (int i = sheet.getFirstRowNum(); counter < sheet.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			if (row == null) {
				continue;
			} else {
				counter++;
			}
			if (counter == 1) {
				continue;
			}
			list = getCellValuesByXHSSFRow(row);
			if (list == null || list.isEmpty()) {
				continue;
			}
			result.add(list);
		}
		return result;
	}
	/**
	 * 
	* @Title: getCellValuesByXHSSFRow  
	* @Description: 读取 XLSX 没cell 的数据
	* @param @param row
	* @param @return    参数  
	* @return List<Object>    返回类型  
	* @throws
	 */
	private static List<Object> getCellValuesByXHSSFRow(XSSFRow row) {
		final List<Object> result = new LinkedList<Object>();
		XSSFCell cell;
		for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
			cell = row.getCell(j);
			if (cell == null) {
				if (j != row.getLastCellNum()) {
					result.add(null);
				}
				continue;
			}
			result.add(getValue(cell.getCellType(), cell));
		}
		return result;
	}
	/**
	 * 获取xlsx格式的文本内容
	 * 
	 * @param cellType
	 * @param cell
	 * @return
	 */
	private static Object getValue(int cellType, XSSFCell cell) {
		Object value;
		switch (cell.getCellType()) {
		case XSSFCell.CELL_TYPE_STRING:
			value = cell.getStringCellValue();
			break;
		case XSSFCell.CELL_TYPE_NUMERIC:
			value = cell.getNumericCellValue();
			break;
		case XSSFCell.CELL_TYPE_BOOLEAN:
			value = cell.getBooleanCellValue();
			break;
		case XSSFCell.CELL_TYPE_BLANK:
			value = "";
			break;
		default:
			value = cell.toString();
			break;
		}
		return value;
	}

	/**
	 * 获取xls格式的文本内容
	 * 
	 * @param cellType
	 * @param cell
	 * @return
	 */
	private static Object getValue(int cellType, HSSFCell cell) {
		Object value;
		switch (cell.getCellType()) {
		case XSSFCell.CELL_TYPE_STRING:
			value = cell.getStringCellValue();
			break;
		case XSSFCell.CELL_TYPE_NUMERIC:
			value = cell.getNumericCellValue();
			break;
		case XSSFCell.CELL_TYPE_BOOLEAN:
			value = cell.getBooleanCellValue();
			break;
		case XSSFCell.CELL_TYPE_BLANK:
			value = "";
			break;
		default:
			value = cell.toString();
		}
		return value;
	}

	/**
	 * @Describe 根据Class对象返回实体类 参数list 必须是 读取的 数据与实体字段 以map 的形式 映射
	 *           注意：excel表格的数据必须与实体类的字段一一对应
	 * @param cls
	 * @param list
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	private static <T> List<T> queryEntity(Class<T> cls, List<Map> list, boolean strFilter) throws Exception {
		final List<T> result = new LinkedList<T>();
		if (list == null || list.isEmpty()) {
			return result;
		}
		try {
			Map retMap = types(cls);
			if (retMap == null || retMap.isEmpty()) {
				return result;
			}
			// 方法名及对应的参数类型
			Object[] objs;
			T invokeTester;
			ValueConver valueConver;
			for (Map<?, ?> m : list) {
				if (m == null || m.size() == 0) {
					continue;
				}
				invokeTester = cls.newInstance();
				for (Map.Entry<?, ?> mm : m.entrySet()) {
					if (mm == null) {
						continue;
					}
					objs = (Object[]) retMap.get(mm.getKey());
					if (objs == null) {
						continue;
					}
					valueConver = VALUECONVERMAP.get((Class) objs[1]);
					if (null != valueConver) {
						cls.getDeclaredMethod(objs[0].toString(), new Class[] { (Class) objs[1] }).invoke(invokeTester,
								new Object[] { valueConver.convertType(mm.getValue(), strFilter) });
					}
				}
				result.add(invokeTester);
			}
		} finally {
			SyncDateUtils.remove();
		}
		return result;
	}
	
	private static Map<Type, ValueConver> VALUECONVERMAP = new HashMap<>();

	static {
		VALUECONVERMAP.put(Integer.class, new ValueConver(ValueType.INTEGER));
		VALUECONVERMAP.put(Byte.class, new ValueConver(ValueType.BYTE));
		VALUECONVERMAP.put(Short.class, new ValueConver(ValueType.SHORT));
		VALUECONVERMAP.put(Date.class, new ValueConver(ValueType.DATE));
		VALUECONVERMAP.put(Long.class, new ValueConver(ValueType.LONG));
		VALUECONVERMAP.put(Double.class, new ValueConver(ValueType.DOUBLE));
		VALUECONVERMAP.put(Float.class, new ValueConver(ValueType.FLOAT));
		VALUECONVERMAP.put(BigDecimal.class, new ValueConver(ValueType.BIGDECIMAL));
		VALUECONVERMAP.put(String.class, new ValueConver(ValueType.STRING));
		VALUECONVERMAP.put(Boolean.class, new ValueConver(ValueType.BOOLEAN));
	}
	
	protected static enum ValueType {
		INTEGER, BYTE, SHORT, DATE, LONG, DOUBLE, FLOAT, BIGDECIMAL, STRING, BOOLEAN;
	}
	
	/**
	 * 
	 * @ClassName: ValueConver
	 * @Description: TODO
	 * @author: lijie
	 * @date: 2018年4月21日 下午1:27:12
	 */
	protected static class ValueConver {

		private final ValueType type;

		public ValueConver(ValueType type) {
			this.type = type;
		}

		/**
		 * 
		* @Title: convertType  
		* @Description: 值转换 
		* @param: @param value
		* @param: @param strFilter
		* @param: @return
		* @return Object
		* @author lijie
		* @throws
		 */
		public Object convertType(Object value, boolean strFilter) {
			if (null == value) {
				return null;
			}
			switch (type) {
			case INTEGER:
				return getBigDecimal(value).intValue();
			case BYTE:
				return getBigDecimal(value).byteValue();
			case SHORT:
				return getBigDecimal(value).shortValue();
			case DATE:
				return SyncDateUtils.strToDate(value.toString());
			case LONG:
				return getBigDecimal(value).longValue();
			case DOUBLE:
				return Double.valueOf(value.toString());
			case FLOAT:
				return Float.valueOf(value.toString());
			case BIGDECIMAL:
				return new BigDecimal(value.toString());
			case STRING:
				String str = value.toString();
				if (strFilter) {
					if (str.indexOf(".") != -1) {
						str = str.substring(0, str.indexOf("."));
					}
				}
				return str;
			case BOOLEAN:
				return Boolean.valueOf(value.toString());
			default:
				break;
			}
			return value;
		}
		
		/**
		 * 
		* @Title: getBigDecimal 
		* @Description: 格式化 
		* @param @param value
		* @param @return    设定文件 
		* @return BigDecimal    返回类型 
		* @author lijie
		* @throws
		 */
		private BigDecimal getBigDecimal(Object value) {

			return null == value ? null : new BigDecimal(NF.format(new BigDecimal(value.toString())));
		}
	}
	/**
	 * 
	* @Title: type  
	* @Description: 得到字段类型 
	* @param @param cls
	* @param @return
	* @param @throws ClassNotFoundException    参数  
	* @return Map    返回类型  
	* @throws
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static Map types(Class<?> cls) throws ClassNotFoundException {
		if (cls == null) {
			throw new ClassNotFoundException();
		}
		Map map = new HashMap<>(16);
		Method[] mh = cls.getDeclaredMethods();
		if (mh != null && mh.length > 0) {
			String name;
			for (Method m : mh) {
				name = m.getName();
				if (name.startsWith("set")) {
					map.put(ClassUtils.toLowerCase(name.substring("set".length())),
							new Object[] { name, m.getParameterTypes()[0] });
				}
			}
		}
		return map;
	}
    /**
     * 
     * @Title: result   
     * @Description: 返回导入的实体集合   
     * @param: @param cls
     * @param: @param list
     * @param: @param names
     * @param: @return
     * @param: @throws Exception      
     * @return: List<T>      
     * @throws
     */
	@SuppressWarnings("rawtypes")
	private static <T> List<T> result(Class<T> cls, List<List<Object>> list, String[] names, boolean strFilter)
			throws Exception {
		List<T> result = new LinkedList<T>();
		if (null != list && !list.isEmpty()) {
			ArrayList<Map> maps = new ArrayList<>(list.size());
			Iterator<?> it = list.iterator();
			List<?> obj;
			Map<String, Object> map;
			Iterator<?> objit;
			while (it.hasNext()) {
				obj = (List<?>) it.next();
				if (null != obj && !obj.isEmpty()) {
					map = new HashMap<String, Object>(16);
					objit = obj.iterator();
					for (String name : names) {
						map.put(name, objit.next());
					}
					maps.add(map);
				}
			}
			result = queryEntity(cls, maps, strFilter);
		}
		return result;
	}

}
