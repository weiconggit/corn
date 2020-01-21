package org.weicong.uas.auth.integration.sms;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Component;
import org.weicong.common.auth.config.CornUser;
import org.weicong.uas.auth.integration.AbstractIntegrationAuthenticator;
import org.weicong.uas.entity.SysUser;
import org.weicong.uas.service.ISysUserService;

import lombok.AllArgsConstructor;

/**
 * @description 短信登录，继承AbstractIntegrationAuthenticator启用图片验证，实现IntegrationAuthenticator不用图片验证
 * @author weicong
 * @date 2019年9月11日
 * @version 1.0
 */
@Component
@AllArgsConstructor
public class SMSAuthenticator extends AbstractIntegrationAuthenticator {

	private final ISysUserService sysUserService;

	@Override
	protected UserDetails check(String username, String credentials) {
		// TODO 从缓存获取
		String validataCode = "1234567";
		// 从缓存获取短信验证码信息和password字段作比较 注：此时password存放的是短信验证码
		if (!credentials.equals(validataCode)) {
			throw new OAuth2Exception("手机验证码错误");
		}
		
		// 从数据库根据 手机号 获取用户数据
		// 验证码检验通过后将获取用户数据中的password字段设置为短信验证码值，以便通过oauth2后续检验
		SysUser sysUser = sysUserService.getByPhone(username);
		if (null == sysUser) {
			throw new OAuth2Exception("手机号对应用户不存在");
		}
		sysUser.setPassword(validataCode);
		
		// TODO 从数据库获取权限数据
		List<String> urList = new ArrayList<>();
		urList.add("GET/order/{id}");
		urList.add("GET/product/{id}");
		List<SimpleGrantedAuthority> roles = new ArrayList<>();
		return new CornUser(sysUser.getUsername(), sysUser.getPassword(), urList, roles);
	}


}
