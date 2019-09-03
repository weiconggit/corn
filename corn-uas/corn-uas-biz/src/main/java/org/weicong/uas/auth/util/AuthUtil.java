package org.weicong.uas.auth.util;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;

import lombok.AllArgsConstructor;

/**
 * @description
 * @author weicong
 * @date 2019年9月3日
 * @version 1.0
 */
@AllArgsConstructor
public class AuthUtil {

	private final TokenStore tokenStore;

	public void removeTokenCache() {
		Authentication authentication = getAuthentication();
		OAuth2AccessToken accessToken = tokenStore.getAccessToken((OAuth2Authentication) authentication);
		tokenStore.removeAccessToken(accessToken);
	}

	public static Collection<? extends GrantedAuthority> getGrantedAuthority() {
		return getAuthentication().getAuthorities();
	}

	public static Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
}
