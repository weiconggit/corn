package org.weicong.uas.auth.config;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

/**
 * @description 
 * @author weicong
 * @date 2019年9月17日
 * @version 1.0
 */
@Deprecated
public class CornClientDetails {

//	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		return new BaseClientDetails(clientId, "order", "select", "client_credentials,refresh_token", "client");
	}

}
