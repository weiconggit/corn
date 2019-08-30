package org.weicong.uas.auth.endpoint;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.weicong.common.auth.constant.SecurityRpEnum;
import org.weicong.common.auth.constant.SecurityRpInfo;

import lombok.AllArgsConstructor;

/**
 * @description 
 * @author weicong
 * @date 2019年8月30日
 * @version 1.0
 */
@AllArgsConstructor
@RestController("oauth")
public class CornTokenEndpoint {
	
	@RequestMapping("logout")
	public SecurityRpInfo<String> logout(){
		SecurityContextHolder.getContext().setAuthentication(null);
		SecurityContextHolder.clearContext();
		return new SecurityRpInfo<String>(SecurityRpEnum.LOGOUT_SUCCESS, null);
	}
}
