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

	NO_LOGIN(-2, "请先登录"),
	NO_PERMIT(-3, "登录已过期，请重新登录"),
	NO_AUTHOR(-4, "没有权限");

	public Integer code;

	public String msg;
}
