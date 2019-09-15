package org.weicong.uas.auth.config;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Component;
import org.weicong.common.auth.config.CornUser;
import org.weicong.common.base.util.RedisUtil;

import lombok.AllArgsConstructor;

/**
 * @description 单设备登录认证 single device sign-on
 * @author weicong
 * @time   2019年9月13日 下午8:52:04
 * @version 1.0
 */
@Component
@AllArgsConstructor
public class SDSOAuthentication implements ApplicationListener<AuthenticationSuccessEvent> {

	private final RedisUtil redisUtil;
	
	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent event) {
		Object object = event.getAuthentication().getPrincipal();
		if (object instanceof CornUser) {
			CornUser cornUser = (CornUser)object;
			
//			redisUtil.get(cornUser.getDeviceId(), "");
		}
	}

}
