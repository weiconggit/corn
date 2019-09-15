package org.weicong.uas.auth.integration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Component;
import org.weicong.common.auth.config.CornUser;
import org.weicong.uas.entity.SysUser;
import org.weicong.uas.service.ISysUserService;

import lombok.AllArgsConstructor;

/**
 * @description 默认密码登录认证
 * @author weicong
 * @time   2019年9月15日
 * @version 1.0
 */
@Component
@AllArgsConstructor
public class DefaultAuthenticator extends AbstractIntegrationAuthenticator{

	private final ISysUserService sysUserService;
	
	@Override
	protected UserDetails check(IntegrationContext context) {
		SysUser sysUser = sysUserService.getByUsername(context.getUsername());
		if (null == sysUser) {
			throw new OAuth2Exception("账号或密码错误");
		}
		// TODO 从数据库获取权限数据
		List<String> urList = new ArrayList<>();
		urList.add("GET/order/{id}");
		urList.add("GET/product/{id}");
		List<SimpleGrantedAuthority> roles = new ArrayList<>();
		return new CornUser(urList, sysUser.getUsername(), sysUser.getPassword(), roles);
	}

}
