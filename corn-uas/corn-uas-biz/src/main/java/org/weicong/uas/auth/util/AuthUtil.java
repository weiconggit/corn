package org.weicong.uas.auth.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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

	public void removeTokenCache() {
		Authentication authentication = getAuthentication();
		OAuth2AccessToken accessToken = tokenStore.getAccessToken((OAuth2Authentication) authentication);
		tokenStore.removeAccessToken(accessToken);
	}
	
	public void overloadTokenCache() {
		OAuth2Authentication authentication = (OAuth2Authentication)getAuthentication();
		OAuth2AccessToken accessToken = tokenStore.getAccessToken((OAuth2Authentication) authentication);
		URLUser userDetails = (URLUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<String> list = new ArrayList<String>();
		list.add("GET/order/{id}");
		URLUser urlUser = new URLUser(list ,userDetails.getUsername(), authentication.getCredentials().toString(), userDetails.getAuthorities());
		Authentication up = new UsernamePasswordAuthenticationToken(urlUser, authentication.getCredentials().toString(), userDetails.getAuthorities());
		tokenStore.storeAccessToken(accessToken, new OAuth2Authentication(authentication.getOAuth2Request(), up));
		SecurityContextHolder.getContext().setAuthentication(up);
	}

	public static Collection<? extends GrantedAuthority> getGrantedAuthority() {
		return getAuthentication().getAuthorities();
	}

	public static Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
}
