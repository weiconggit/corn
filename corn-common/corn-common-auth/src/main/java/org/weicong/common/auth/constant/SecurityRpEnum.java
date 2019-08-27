package org.weicong.common.auth.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description 认证权限状态信息
 * @author weicong
 * @date 2019年8月23日
 * @version 1.0
 */
@AllArgsConstructor
@Getter
public enum SecurityRpEnum {

	LOGIN_SUCCESS(0, "登录成功"),
	GET_TOKEN_SUCCESS(0, "token获取成功"),
	NO_LOGIN(-2, "请先登录"),
	ERROR_AUTHEN(-3, "认证错误"),
	NO_PERMIT(-4, "登录已过期，请重新登录"),
	NO_AUTHOR(-5, "没有权限");

	public Integer code;

	public String msg;
}
