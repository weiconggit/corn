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
import lombok.extern.slf4j.Slf4j;

/**
 * @description 默认密码登录认证
 * @author weicong
 * @time   2019年9月15日
 * @version 1.0
 */
@Slf4j
@Component
@AllArgsConstructor
public class DefaultAuthenticator extends AbstractIntegrationAuthenticator{

	private final ISysUserService sysUserService;
	
	@Override
	protected UserDetails check(IntegrationContext context) {
		// obtain user info from db
		SysUser sysUser = sysUserService.getByUsername(context.getUsername());
		
		if (null == sysUser) throw new OAuth2Exception("用户名或密码错误");
		
		// TODO get authority uri info from db
		List<String> uriList = new ArrayList<>();
		uriList.add("GET/product/{id}");
		List<SimpleGrantedAuthority> roles = new ArrayList<>();
		
		log.debug("login user has authortity [{}]", uriList);
		return new CornUser(sysUser.getUsername(), sysUser.getPassword(), uriList, roles);
	}

}
