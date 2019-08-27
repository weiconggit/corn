package org.weicong.common.auth.config;

import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenEndpointFilter;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @description 
 * @author weicong
 * @date 2019年8月27日
 * @version 1.0
 */
public class CornAuthenticationProcessingFilter extends ClientCredentialsTokenEndpointFilter {

	@Override
	public void setAuthenticationEntryPoint(AuthenticationEntryPoint authenticationEntryPoint) {
		super.setAuthenticationEntryPoint(new CornAuthenticationEntryPoint(new ObjectMapper()));
	}

	
}
