package org.weicong.common.auth.config;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

/**
 * @description
 * @author weicong
 * @date 2019年8月26日
 * @version 1.0
 */
public class CornTokenEnhancer implements TokenEnhancer {

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		return new CornOAuth2AccessToken(accessToken);

	}

}
