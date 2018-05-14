package com.born.util.encrypt.security;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.born.util.constants.SysConstants;
import com.born.util.encrypt.security.coder.Base64Encoder;
import com.born.util.encrypt.security.coder.Base64Coder;
import com.born.util.encrypt.security.coder.DESCoder;
import com.born.util.encrypt.security.coder.HmacCoder;
import com.born.util.encrypt.security.coder.MDCoder;
import com.born.util.encrypt.security.coder.Md5Coder;
import com.born.util.encrypt.security.coder.RSACoder;
import com.born.util.encrypt.security.coder.SHACoder;

/**
 * 数据加密辅助类
 * 
 * @ClassName: SecurityUtil
 * @Description: 默认编码UTF-8
 * @author 张永胜
 * @date 2018年5月9日 下午3:03:11
 * @version 1.0
 */
public final class SecurityUtil {

	private SecurityUtil() {
	}

	/**
	 * 默认算法密钥
	 */
	private static final byte[] ENCRYPT_KEY = { -81, 0, 105, 7, -32, 26, -49, 88 };

	/**
	 * 设置字符集
	 */
	public static final String CHARSET = "UTF-8";

	static {
		Security.addProvider(new BouncyCastleProvider());
	}

	/**
	 * BASE64解码
	 * 
	 * @param key
	 * @return
	 */
	public static final byte[] decryptBASE64(String key) {
		try {
			return new Base64Encoder().decode(key);
		} catch (Exception e) {
			throw new RuntimeException("解密错误，错误信息：", e);
		}
	}

	/**
	 * BASE64编码
	 * 
	 * @param key
	 * @return
	 */
	public static final String encryptBASE64(byte[] key) {
		try {
			return new Base64Encoder().encode(key);
		} catch (Exception e) {
			throw new RuntimeException("加密错误，错误信息：", e);
		}
	}

	/**
	 * 数据解密，算法（DES）
	 * 
	 * @param cryptData
	 *            加密数据
	 * @return 解密后的数据
	 */
	public static final String decryptDes(String cryptData) {
		return decryptDes(cryptData, ENCRYPT_KEY);
	}

	/**
	 * 数据加密，算法（DES）
	 * 
	 * @param data
	 *            要进行加密的数据
	 * @return 加密后的数据
	 */
	public static final String encryptDes(String data) {
		return encryptDes(data, ENCRYPT_KEY);
	}

	/**
	 * 基于MD5算法的单向加密
	 * 
	 * @param strSrc
	 *            明文
	 * @return 返回密文
	 */
	public static final String encryptMd5(String strSrc) {
		String outString = null;
		try {
			outString = encryptBASE64(MDCoder.encodeMD5(strSrc.getBytes(CHARSET)));
		} catch (Exception e) {
			throw new RuntimeException("加密错误，错误信息：", e);
		}
		return outString;
	}

	/**
	 * 基于MD5算法的单向加密
	 * 
	 * @param strSrc
	 *            明文
	 * @return 返回密文
	 */
	public static final String encryptMd5Hex(String strSrc) {
		try {
			return Md5Coder.getMd5(strSrc);
		} catch (Exception e) {
			throw new RuntimeException("加密错误，错误信息：", e);
		}
	}

	/**
	 * SHA加密
	 * 
	 * @param data
	 * @return
	 */
	public static final String encryptSHA(String data) {
		try {
			return encryptBASE64(SHACoder.encodeSHA256(data.getBytes(CHARSET)));
		} catch (Exception e) {
			throw new RuntimeException("加密错误，错误信息：", e);
		}
	}

	/**
	 * HMAC加密
	 * 
	 * @param data
	 * @return
	 */
	public static final String encryptHMAC(String data) {
		return encryptHMAC(data, ENCRYPT_KEY);
	}

	/**
	 * 数据解密，算法（DES）
	 * 
	 * @param cryptData
	 *            加密数据
	 * @param key
	 * @return 解密后的数据
	 */
	public static final String decryptDes(String cryptData, byte[] key) {
		String decryptedData = null;
		try {
			// 把字符串解码为字节数组，并解密
			decryptedData = new String(DESCoder.decrypt(decryptBASE64(cryptData), key));
		} catch (Exception e) {
			throw new RuntimeException("解密错误，错误信息：", e);
		}
		return decryptedData;
	}

	/**
	 * 数据加密，算法（DES）
	 * 
	 * @param data
	 *            要进行加密的数据
	 * @param key
	 * @return 加密后的数据
	 */
	public static final String encryptDes(String data, byte[] key) {
		String encryptedData = null;
		try {
			// 加密，并把字节数组编码成字符串
			encryptedData = encryptBASE64(DESCoder.encrypt(data.getBytes(), key));
		} catch (Exception e) {
			throw new RuntimeException("加密错误，错误信息：", e);
		}
		return encryptedData;
	}

	/**
	 * HMAC加密
	 * 
	 * @param data
	 * @param key
	 * @return
	 */
	public static final String encryptHMAC(String data, byte[] key) {
		try {
			return encryptBASE64(HmacCoder.encodeHmacSHA512(data.getBytes(CHARSET), key));
		} catch (Exception e) {
			throw new RuntimeException("加密错误，错误信息：", e);
		}
	}

	/**
	 * RSA签名
	 * 
	 * @param data
	 *            原数据
	 * @param privateKey
	 * @return
	 */
	public static final String signRSA(String data, String privateKey) {
		try {
			return Base64Coder.encode(RSACoder.sign(data.getBytes(CHARSET), Base64Coder.decode(privateKey)));
		} catch (Exception e) {
			throw new RuntimeException("签名错误，错误信息：", e);
		}
	}

	/**
	 * RSA验签
	 * 
	 * @param data
	 *            原数据
	 * @param publicKey
	 * @param sign
	 * @return
	 */
	public static final boolean verifyRSA(String data, String publicKey, String sign) {
		try {
			return RSACoder.verify(data.getBytes(CHARSET), Base64Coder.decode(publicKey), Base64Coder.decode(sign));
		} catch (Exception e) {
			throw new RuntimeException("验签错误，错误信息：", e);
		}
	}

	/**
	 * 数据加密，算法（RSA）
	 * 
	 * @param data
	 *            数据
	 * @param privateKey
	 * @return 加密后的数据
	 */
	public static final String encryptRSAPrivate(String data, String privateKey) {
		try {
			return encryptBASE64(RSACoder.encryptByPrivateKey(data.getBytes(CHARSET), decryptBASE64(privateKey)));
		} catch (Exception e) {
			throw new RuntimeException("解密错误，错误信息：", e);
		}
	}

	/**
	 * 数据加密，算法（RSA）
	 * 
	 * @param data
	 *            数据
	 * @param privateKey
	 * @return 加密后的数据
	 */
	public static final String encryptRSAPublic(String data, String publicKey) {
		try {
			return Base64Coder
					.encode(RSACoder.encryptByPublicKey(data.getBytes(CHARSET), Base64Coder.decode(publicKey)));
		} catch (Exception e) {
			throw new RuntimeException("加密错误，错误信息：", e);
		}
	}

	/**
	 * 数据解密，算法（RSA）
	 * 
	 * @param cryptData
	 *            加密数据
	 * @param publicKey
	 * @return 解密后的数据
	 */
	public static final String decryptRSAPrivate(String cryptData, String privateKey) {
		try {
			// 把字符串解码为字节数组，并解密
			return new String(
					RSACoder.decryptByPrivateKey(Base64Coder.decode(cryptData), Base64Coder.decode(privateKey)));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("解密错误，错误信息：", e);
		}
	}

	/**
	 * 数据解密，算法（RSA）
	 * 
	 * @param cryptData
	 *            加密数据
	 * @param publicKey
	 * @return 解密后的数据
	 */
	public static final String decryptRSAPublic(String cryptData, String publicKey) {
		try {
			// 把字符串解码为字节数组，并解密
			return new String(RSACoder.decryptByPublicKey(decryptBASE64(cryptData), decryptBASE64(publicKey)));
		} catch (Exception e) {
			throw new RuntimeException("解密错误，错误信息：", e);
		}
	}

	/**
	 * 
	 * @Title: encryptPassword
	 * @Description: MD5加密
	 * @param password
	 * @return
	 * @author 张永胜
	 * @return String
	 * @date 2018年5月9日 下午2:38:22
	 */
	public static String encryptPassword(String password) {
		return encryptMd5(SecurityUtil.encryptSHA(password));
	}

	/**
	 * 
	 * @Title: encrypt
	 * @Description: 老系统加密方式
	 * @param account
	 *            账号
	 * @param password
	 *            密码
	 * @return
	 * @author 张永胜
	 * @return String
	 * @date 2018年5月9日 下午2:38:40
	 */
	public static String encryptPassword(String account, String password) {
		StringBuilder sb = new StringBuilder(password);
		sb.append(SysConstants.PASSWORD_KEY);
		sb.append("_" + account);
		return Md5Coder.encode(sb.toString());
	}

	/**
	 * 
	 * @Title: loadPublicKeyByFile
	 * @Description: 读取文件
	 * @param path
	 * @return
	 * @throws Exception
	 * @author 张永胜
	 * @return String
	 * @date 2018年5月7日 下午5:41:40
	 */
	public static String loadPublicKeyByFile(String path) throws Exception {
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String readLine = null;
			StringBuilder sb = new StringBuilder();
			while ((readLine = br.readLine()) != null) {
				sb.append(readLine);
			}
			br.close();
			return sb.toString();
		} catch (IOException e) {
			throw new Exception("公钥数据流读取错误");
		} catch (NullPointerException e) {
			throw new Exception("公钥输入流为空");
		}
	}

	public static void main(String[] args) throws Exception {
		System.out.println(encryptDes("SHJR"));
		System.out.println(decryptDes("INzvw/3Qc4q="));
		System.out.println(encryptMd5("SHJR"));
		System.out.println(encryptSHA("1"));

		/*
		 * Map<String, Object> key = RSACoder.initKey(); String privateKeys =
		 * encryptBASE64(RSACoder.getPrivateKey(key)); String publicKeys =
		 * encryptBASE64(RSACoder.getPublicKey(key)); System.out.println(privateKeys);
		 * System.out.println(publicKeys);
		 */

		// System.out.println("a>>>>> " +
		// loadPublicKeyByFile("d://rsa_public_key.pem"));

		// 私钥
		String signPrivateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBANdxF0HHMMXEzxal\r\n"
				+ "o8NHEi7C2crD6oAVjcZBYbgx1O2wR2iiz5PsI0ankMkADB6TNHFPLgHyW2k/8agn\r\n"
				+ "O/EoqHUZShNS8usAiqqcCNLlNbjz7DUMvnnYXU2WeZIIsA9gBkjVvc0csr3yPzLM\r\n"
				+ "iyQf+jRLG8faLz2cEu2JAvan5oR3AgMBAAECgYAsnMOHIHtOCMIlW2l874OpYTn+\r\n"
				+ "TfGKUmxISJO2s8L28VDoLwazB+wKHnrR1Iut7Sen6xCI4KnjMpWVD4LPHm87Zxy3\r\n"
				+ "6dhUXSIOMPqlTCUx5nezC2wAcB6TL9Xad+NP/EHLOhZK1BlWXrymFBu78QpUyFLC\r\n"
				+ "u9dL+Xr/j9CDT/qrMQJBAOvbxUQVZYTLbsLLi/npBVxAmxmqqKus+mFwOejMWfuo\r\n"
				+ "PsNVm1OLcekX/d/J4U5KUIHo/huUoOpRIQxJs/bVCrkCQQDp1vq56tEBUDbn182+\r\n"
				+ "CUND4pRdEmx/FdzizwMEJSFlKe2z++/eUMUB8mRHBYJWbRi5YVyRk1pfpMREIYl1\r\n"
				+ "jbCvAkEAvHYkf02Vi/G99FsO40dmKBvbkzvBgUQ74UX6j+FR+XurgwvRjIU9JSMt\r\n"
				+ "kfdPV+6bu+J/wWwW1IS1Iot2CMKcyQJBAJkuJbJyexWEKWN4f6NUSAcJPy0063PY\r\n"
				+ "q4i8GPq3JAg0Di2QBRK6EEP1N/E6T6Yz8zVE7THDuDjdI2CKI5SpC6sCQFegz8lw\r\n"
				+ "K7REfnPYciFaMydInLwE8ay4yKpA1ipCpW28nQlsGpMzPp/fiJBhbJC6kJiWvrAq\r\n" + "ouTzJOaRalRrVT0=";
		// 公钥
		String signPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDXcRdBxzDFxM8WpaPDRxIuwtnK\r\n"
				+ "w+qAFY3GQWG4MdTtsEdoos+T7CNGp5DJAAwekzRxTy4B8ltpP/GoJzvxKKh1GUoT\r\n"
				+ "UvLrAIqqnAjS5TW48+w1DL552F1NlnmSCLAPYAZI1b3NHLK98j8yzIskH/o0SxvH\r\n" + "2i89nBLtiQL2p+aEdwIDAQAB";
		// String privateKey =
		// "miibvaibadanb7AG8A9gZMQbaqefaascatUM77eWa7eaaAeaA+qv8XnEwD1GQAnZ1KH8TA+BrzEL13fKAOmoLuZJMlDIlFbCLctOJky1oChjJApAMv4w8ab3TT56MuqrFCVqtqidaqabaAagWeZnie3o5FVJV2muXfwsyzLN0uIlEhE+GNoek8krpIIm9OY/o7FOcsqwxYSNFuvGMjtGOQIk22DdN8vq9scna9ea/az2E1luGbRPTnFu7sJ6aiw6BEZ1QWcaWga3+uaMqSYciqcwotZEFDyQvD/bPDtqGmecasPhDBhGOpES416m68/1aMi8aikKPuXEpPIzyFTLLi6GngTNTaDxn6kIJiJkyRK/Ja3ta9aVe/v7Oo25O7lKSU7nbcgzFCk9u980U7IyccFc9Wwe9Mi7kq0vCR33Xz1pAYIwaqqxJu5iunysIm0r/NuJbRiBDvU=";
		// String publicKey =
		// "mfMMdqyjkEzi8L3naqebbqadsMaMsajbajpAfy5P1fFSGJjd6wHGU4VpFuw1lSDb2Fm0dHRp23cVXcWqzHMAYHsCg0F8O2zdVmfxvEqaxnZTDYfee1zKueQcaMeaaq==";
		String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKauuNXjePoU+Fji\r\n"
				+ "Ut3H1MSEvYpfj1JUDEqaSQxf0YjzxDqsmlDCLI9sBQvONX8JraVXtYVGov1thRqk\r\n"
				+ "iIJUIIwfQIIWm43yp7r9LK/EnhosqJmPvwVoC4bBJK87kys7hS/E+8lHwO8kUj4S\r\n"
				+ "xZyqYEMp/bVOu1Ad70CedXPz+mzDAgMBAAECgYEAjV28d/NAJGHqq3/wJhcYdj/a\r\n"
				+ "fWREqt+LuS4XbD5L+So9sEG5c2rr3gwQT43pwz/THVtBIbLiA6yCajFHcbmmC7MB\r\n"
				+ "ypHlGCtFfd4wdLpvT1NxmQnTFXLz+JVnxm8eazjEHGF/RrVvDPP7kmbysAKy+ImE\r\n"
				+ "MAIckEM2kkJQMKmexrECQQDQRjHPB+NHRdngRCpXXL+FVfR3ehMBpPwGSo+sep5g\r\n"
				+ "7nMJowWVQTZNzs8JKBHY+Ms42niouIUEFpGGqKrwNRQJAkEAzOCrvdSR/Fa25Mpn\r\n"
				+ "nUr2d2aOzkXvk5cJqEaHafhGvXTBab1OdIxlK5HMFte2dvPSgbU/8d6B+0Pidzus\r\n"
				+ "OaflawJAOvKzJEqyPDj7+qqwZTueBWoXoD1P5dZBcrJlycaGzx6ONrsKWH01ln4j\r\n"
				+ "QQ8zRfrc4gGLxw06sLaGh9tzTjtnqQJAJ0R0hkjExjmUiltk6pfgj1f/zPdIiOAt\r\n"
				+ "AKyr7ZqhbFSBcZPQArSZ+3TRShuEJF5tHWEw5VnBTfhf4nx0cQSGCwJAXWoriwNG\r\n"
				+ "a+HEV9r8DMBSbNTO5IUtR6AWtvuDlYrGdZJbPwC7HxQq/BY4/keZBJkbg1gJe+2T\r\n" + "op11AlsVgZpdPw==";
		String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCmrrjV43j6FPhY4lLdx9TEhL2K\r\n"
				+ "X49SVAxKmkkMX9GI88Q6rJpQwiyPbAULzjV/Ca2lV7WFRqL9bYUapIiCVCCMH0CC\r\n"
				+ "FpuN8qe6/SyvxJ4aLKiZj78FaAuGwSSvO5MrO4UvxPvJR8DvJFI+EsWcqmBDKf21\r\n" + "TrtQHe9AnnVz8/pswwIDAQAB";

		System.out.println("私钥 >> " + privateKey);
		System.out.println("公钥 >> " + publicKey);

		String sign = (signRSA(SecurityUtil.encryptMd5("admin123456"), signPrivateKey));
		System.out.println("sign >> " + sign);

		Security.addProvider(new BouncyCastleProvider());
		String encrypt = encryptRSAPublic("123456", publicKey);
		System.out.println("公钥加密 >> " + encrypt);
		String org = decryptRSAPrivate(encrypt, privateKey);

		System.out.println("私钥解密 >> " + org);
		System.out.println("RSA验证 >> " + verifyRSA(SecurityUtil.encryptMd5("admin123456"), signPublicKey, sign));

		// System.out.println("-------列出加密服务提供者-----");
		// Provider[] pro = Security.getProviders();
		// for (Provider p : pro) {
		// System.out.println("Provider:" + p.getName() + " - version:" +
		// p.getVersion());
		// System.out.println(p.getInfo());
		// }
		// System.out.println("");
		// System.out.println("-------列出系统支持的消息摘要算法：");
		// for (String s : Security.getAlgorithms("MessageDigest")) {
		// System.out.println(s);
		// }
		// System.out.println("-------列出系统支持的生成公钥和私钥对的算法：");
		// for (String s : Security.getAlgorithms("KeyPairGenerator")) {
		// System.out.println(s);
		// }
	}
}
