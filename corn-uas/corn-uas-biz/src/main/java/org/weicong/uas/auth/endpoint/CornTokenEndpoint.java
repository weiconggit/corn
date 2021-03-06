package org.weicong.uas.auth.endpoint;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.weicong.common.auth.constant.SecurityRpEnum;
import org.weicong.common.auth.constant.SecurityRpInfo;
import org.weicong.common.base.util.StringUtil;
import org.weicong.uas.auth.util.AuthUtil;

import lombok.AllArgsConstructor;

/**
 * @description oauth2相关自定义接口
 * @author weicong
 * @date 2019年8月30日
 * @version 1.0
 */
@AllArgsConstructor
@RestController
@RequestMapping("/oauth")
public class CornTokenEndpoint {
	
	private final TokenStore tokenStore;
	private final AuthUtil authUtil;
	
	@RequestMapping("/logout")
	public SecurityRpInfo<String> logout(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authHeader){
		if (StringUtil.isBlank(authHeader)) 
			return new SecurityRpInfo<String>(-1, "退出登录失败，token为空", "");
		
		String tokenValue = authHeader.replace(OAuth2AccessToken.BEARER_TYPE, "").trim();
		OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
		
		if (accessToken == null || StringUtil.isBlank(accessToken.getValue()))
			return new SecurityRpInfo<String>(-1, "退出失败，token无效", "");

		tokenStore.removeAccessToken(accessToken);
		tokenStore.removeRefreshToken(accessToken.getRefreshToken());
		return new SecurityRpInfo<String>(SecurityRpEnum.LOGOUT_SUCCESS, "");
	}
	
	@RequestMapping("/overload")
	public SecurityRpInfo<String> overload(){
		List<String> list = new ArrayList<String>();
		// TODO from database
		list.add("GET/order/{id}");
		authUtil.overloadTokenCache(list);
		return new SecurityRpInfo<String>(SecurityRpEnum.OVERLOAD_SUCCESS, "");
	}
}
