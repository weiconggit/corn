package org.weicong.uas.auth.config;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;
import org.weicong.common.auth.config.CornUser;
import org.weicong.common.base.util.RedisUtil;

import lombok.AllArgsConstructor;

/**
 * @description 单设备登录认证 single device sign-on，这里可以监听认证成功，包含client认证和password
 * @author weicong
 * @time   2019年9月13日 下午8:52:04
 * @version 1.0
 */
@Deprecated
@Component
@AllArgsConstructor
public class SDSOAuthentication implements ApplicationListener<AuthenticationSuccessEvent> {

	private final RedisUtil redisUtil;
	
	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent event) {
		Object object = event.getAuthentication().getPrincipal();
		if (object instanceof CornUser) {
			CornUser cornUser = (CornUser)object;
		}
	}

}
