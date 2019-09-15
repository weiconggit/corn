package org.weicong.common.auth.constant;

/**
 * @description 用户设备枚举
 * @author weicong
 * @time   2019年9月13日 
 * @version 1.0
 */
public enum UserDeviceEnum {

	WINDOWS("Windows"), MAC("Macintosh"), LINUX("X11"), ANDROID("Android"), IPhONE("iPhone");
	
	private String deviceType;

	private UserDeviceEnum(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceType() {
		return deviceType;
	}
	
}
