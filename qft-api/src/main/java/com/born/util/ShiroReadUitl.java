package com.born.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;

/**
 * 
 * @ClassName: ShiroReadUitl
 * @Description: 读取Shiro配置文件工具
 * @author 张永胜
 * @date 2018年5月4日 下午4:34:40
 * @version 1.0
 */
public class ShiroReadUitl {

	/**
	 * 用linked hash map 来保持有序的读取
	 * 
	 */
	final LinkedHashMap<String, LinkedHashMap<String, String>> coreMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();

	/**
	 * 当前Section的引用
	 */
	String currentSection = null;

	/**
	 * 读取
	 * 
	 * @param file
	 *            文件
	 * @throws FileNotFoundException
	 */
	public ShiroReadUitl(File file) throws FileNotFoundException {
		this.init(new BufferedReader(new FileReader(file)));
	}

	/***
	 * 重载读取
	 * 
	 * @param path
	 *            给文件路径
	 * @throws FileNotFoundException
	 */
	public ShiroReadUitl(String path) throws FileNotFoundException {
		this.init(new BufferedReader(new FileReader(path)));
	}

	/***
	 * 重载读取
	 * 
	 * @param source
	 *            ClassPathResource 文件，如果文件在resource 里，那么直接 new
	 *            ClassPathResource("file name");
	 * @throws IOException
	 */
	public ShiroReadUitl(ClassPathResource source) throws IOException {
		this(source.getFile());
	}

	void init(BufferedReader bufferedReader) {
		try {
			read(bufferedReader);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("IO Exception:" + e);
		}
	}

	/**
	 * 读取文件
	 * 
	 * @param reader
	 * @throws IOException
	 */
	void read(BufferedReader reader) throws IOException {
		String line = null;
		while ((line = reader.readLine()) != null) {
			parseLine(line);
		}
	}

	/**
	 * 转换文件并解析
	 * 
	 * @param line
	 */
	void parseLine(String line) {
		line = line.trim();

		/**
		 * 此部分为注释
		 */
		if (line.matches("^\\#.*$")) {
			return;
		} else if (line.matches("^\\[\\S+\\]$")) {
			// section
			String section = line.replaceFirst("^\\[(\\S+)\\]$", "$1");
			addSection(section);
		} else if (line.matches("^\\S+=.*$")) {
			// key ,value
			int i = line.indexOf("=");
			String key = line.substring(0, i).trim();
			String value = line.substring(i + 1).trim();
			addKeyValue(currentSection, key, value);
		}
	}

	/**
	 * 增加新的Key和Value
	 * 
	 * @param currentSection
	 * @param key
	 * @param value
	 */
	void addKeyValue(String currentSection, String key, String value) {
		if (!coreMap.containsKey(currentSection)) {
			return;
		}
		Map<String, String> childMap = coreMap.get(currentSection);
		childMap.put(key, value);
	}

	/**
	 * 增加Section
	 * 
	 * @param section
	 */
	void addSection(String section) {
		if (!coreMap.containsKey(section)) {
			currentSection = section;
			LinkedHashMap<String, String> childMap = new LinkedHashMap<String, String>();
			coreMap.put(section, childMap);
		}
	}

	/**
	 * 获取配置文件指定Section和指定子键的值
	 * 
	 * @param section
	 * @param key
	 * @return
	 */
	public String get(String section, String key) {
		if (coreMap.containsKey(section)) {
			return get(section).containsKey(key) ? get(section).get(key) : null;
		}
		return null;
	}

	/**
	 * 获取配置文件指定Section的子键和值
	 * 
	 * @param section
	 * @return
	 */
	public Map<String, String> get(String section) {
		return coreMap.containsKey(section) ? coreMap.get(section) : null;
	}

	/**
	 * 获取这个配置文件的节点和值
	 * 
	 * @return
	 */
	public LinkedHashMap<String, LinkedHashMap<String, String>> get() {
		return coreMap;
	}

	/**
	 * 
	 * @Title: readShrio
	 * @Description: 读取文件
	 * @return
	 * @author 张永胜
	 * @return LinkedHashMap<String,LinkedHashMap<String,String>>
	 * @date 2018年5月4日 下午6:54:56
	 */
	public static LinkedHashMap<String, LinkedHashMap<String, String>> readShrio() {
		ShiroReadUitl ini = null;
		try {
			String fileName = "shiro.config";
			InputStream inCfg = ShiroReadUitl.class.getClassLoader().getResourceAsStream("shiro/" + fileName);
			File file = new File(fileName);
			inputStreamToFile(inCfg, file);
			ini = new ShiroReadUitl(file);
			if (ini != null) {
				return ini.get();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	* @Title: inputstreamtofile 
	* @Description: 转换输入流为file文件类型
	* @param ins
	* @param file 
	* @author 张永胜
	* @return void
	* @date 2018年5月16日 下午5:33:35
	 */
	public static void inputStreamToFile(InputStream ins, File file) {
		try {
			OutputStream os = new FileOutputStream(file);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			os.close();
			ins.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}