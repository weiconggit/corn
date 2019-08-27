package org.weicong.uas.config;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @description
 * @author weicong
 * @date 2019年8月26日
 * @version 1.0
 */
@JsonSerialize(using = CornTokenSerializer.class)
public class CornOAuth2AccessToken extends DefaultOAuth2AccessToken {

	private static final long serialVersionUID = -2362625383390592897L;

	public CornOAuth2AccessToken(String value) {
	        super(value);
	}

	public CornOAuth2AccessToken(OAuth2AccessToken accessToken) {
	        super(accessToken);
	}

}
