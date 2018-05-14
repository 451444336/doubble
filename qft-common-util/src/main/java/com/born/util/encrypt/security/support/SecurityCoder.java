package com.born.util.encrypt.security.support;

import java.security.Security;

/**
 * 
 * @ClassName: SecurityCoder
 * @Description: 加密基类
 * @author 张永胜
 * @date 2018年5月9日 下午2:58:43
 * @version 1.0
 */
public abstract class SecurityCoder {
	private static Byte ADDFLAG = 0;
	static {
		if (ADDFLAG == 0) {
			// 加入BouncyCastleProvider支持
			Security.addProvider(new BouncyCastleProvider());
			ADDFLAG = 1;
		}
	}
}
