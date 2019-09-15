package org.weicong.uas.auth.integration;

/**
 * @description 集成认证类型
 * @author weicong
 * @time   2019年9月13日 
 * @version 1.0
 */
public enum AuthenticationEnum {

	PW, SMS, QQ, WX, EMAIL, NONE;

	public static AuthenticationEnum getAuthType(String typeName) {
		switch (typeName.toLowerCase()) {
			case "pw": return PW;
			case "sms": return SMS;
			case "qq": return QQ;
			case "wx": return WX;
			case "email": return EMAIL;
			default: return NONE;
		}
	}
	
	
}
