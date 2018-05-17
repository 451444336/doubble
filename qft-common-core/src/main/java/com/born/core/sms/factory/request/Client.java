package com.born.core.sms.factory.request;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.born.core.sms.factory.request.method.IClientMethod;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @ClassName: Client
 * @Description: 请求工具客户端
 * @author 张永胜
 * @date 2018年5月14日 下午6:04:27
 * @version 1.0
 */
@Slf4j
public class Client implements IClientMethod {

	/**
	 * 请求客户端
	 */
	private static Client instance;

	/**
	 * 编码格式。发送编码格式统一用UTF-8
	 */
	private static String ENCODING = "UTF-8";

	/**
	 * 
	 * @Title: getInstance
	 * @Description: 双重单列模式
	 * @return
	 * @author 张永胜
	 * @return Client
	 * @date 2018年5月14日 下午6:13:00
	 */
	public static Client getInstance() {
		if (instance == null) {
			synchronized (Client.class) {
				if (instance == null) {
					instance = new Client();
				}
			}
		}
		return instance;
	}

	@Override
	public String post(String url, Map<String, String> params) {
		CloseableHttpClient client = HttpClients.createDefault();
		String responseText = "";
		CloseableHttpResponse response = null;
		try {
			HttpPost method = new HttpPost(url);
			if (params != null) {
				List<NameValuePair> paramList = new ArrayList<NameValuePair>();
				for (Map.Entry<String, String> param : params.entrySet()) {
					NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
					paramList.add(pair);
				}
				method.setEntity(new UrlEncodedFormEntity(paramList, ENCODING));
			}
			response = client.execute(method);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				responseText = EntityUtils.toString(entity, ENCODING);
			}
		} catch (Exception e) {
			log.error("POST请求远程接口异常", e);
		} finally {
			try {
				response.close();
			} catch (Exception e) {
				log.error("POST关闭流时出现的异常", e);
			}
		}
		return responseText;
	}

	@Override
	public String get(String url, Map<String, String> params) {
		CloseableHttpClient client = HttpClients.createDefault();
		String responseText = "";
		CloseableHttpResponse response = null;
		try {
			HttpGet method = new HttpGet(url);
			StringBuffer request = new StringBuffer(url);
			if (params != null) {
				request.append("?");
				for (Map.Entry<String, String> param : params.entrySet()) {
					request.append(param.getKey()).append("=").append(param.getValue()).append("&");
				}
				request.deleteCharAt(request.length() - 1);
			}
			response = client.execute(method);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				responseText = EntityUtils.toString(entity, ENCODING);
			}
		} catch (Exception e) {
			log.error("GET请求远程接口异常", e);
		} finally {
			try {
				response.close();
			} catch (Exception e) {
				log.error("GET关闭流时出现的异常", e);
			}
		}
		return responseText;
	}

	@Override
	public String put(String url, Map<String, String> params) {
		CloseableHttpClient client = HttpClients.createDefault();
		String responseText = "";
		CloseableHttpResponse response = null;
		try {
			HttpPut method = new HttpPut(url);
			if (params != null) {
				List<NameValuePair> paramList = new ArrayList<NameValuePair>();
				for (Map.Entry<String, String> param : params.entrySet()) {
					NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
					paramList.add(pair);
				}
				method.setEntity(new UrlEncodedFormEntity(paramList, ENCODING));
			}
			response = client.execute(method);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				responseText = EntityUtils.toString(entity, ENCODING);
			}
		} catch (Exception e) {
			log.error("PUT请求远程接口异常", e);
		} finally {
			try {
				response.close();
			} catch (Exception e) {
				log.error("PUT关闭流时出现的异常", e);
			}
		}
		return responseText;
	}

}
