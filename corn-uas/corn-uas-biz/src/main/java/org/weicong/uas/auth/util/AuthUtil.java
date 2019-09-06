package org.weicong.uas.auth.util;

import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;
import org.weicong.common.auth.config.URLUser;

import lombok.AllArgsConstructor;

/**
 * @description
 * @author weicong
 * @date 2019年9月3日
 * @version 1.0
 */
@Component
@AllArgsConstructor
public class AuthUtil {

	private final TokenStore tokenStore;

	/**
	 * 删除用户相关认证（一般用于退出登录）
	 */
	public void removeTokenCache() {
		Authentication authentication = getAuthentication();
		OAuth2AccessToken accessToken = tokenStore.getAccessToken((OAuth2Authentication) authentication);
		tokenStore.removeAccessToken(accessToken);
		// TODO weicong 2019-09-06 remove refresh token
	}

	/**
	 * 重载用户权限信息
	 * 
	 * @param urList 新权限（url格式：GET/user/{id}）信息
	 */
	public void overloadTokenCache(List<String> urList) {
		// authentication 在oauth2本例中就是 OAuth2Authentication
		OAuth2Authentication authentication = (OAuth2Authentication) getAuthentication();
		// principal 存放的是 UserDetailsService.loadUserByUsername() 的自定义 User
		URLUser userDetails = (URLUser) authentication.getPrincipal();
		// 构造用户新权限信息
		URLUser urlUser = new URLUser(urList, userDetails.getUsername(), authentication.getCredentials().toString(),
				userDetails.getAuthorities());
		// 构造用户新认证信息，credentials 一般指的是 password
		Authentication up = new UsernamePasswordAuthenticationToken(urlUser, authentication.getCredentials().toString(),
				userDetails.getAuthorities());

		// 从 redis 中获取 access_token
		OAuth2AccessToken accessToken = tokenStore.getAccessToken(authentication);
		tokenStore.storeAccessToken(accessToken, new OAuth2Authentication(authentication.getOAuth2Request(), up));
		SecurityContextHolder.getContext().setAuthentication(up);
	}

	private Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
}
