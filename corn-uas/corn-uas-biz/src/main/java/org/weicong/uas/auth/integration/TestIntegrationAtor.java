package org.weicong.uas.auth.integration;

import org.springframework.stereotype.Component;

/**
 * @description 
 * @author weicong
 * @date 2019年9月11日
 * @version 1.0
 */
@Component
public class TestIntegrationAtor implements IntegrationAuthenticator{

	@Override
	public void authenticate(IntegrationAuthentication integrationAuthentication) {
		System.out.println("======== authenticate");
	}

	@Override
	public void prepare(IntegrationAuthentication integrationAuthentication) {
		System.out.println("======== prepare");
	}

	@Override
	public boolean support(IntegrationAuthentication integrationAuthentication) {
		System.out.println("======== support");

		return false;
	}

	@Override
	public void complete(IntegrationAuthentication integrationAuthentication) {
		System.out.println("======== complete");
		
	}

}
