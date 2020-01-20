package org.weicong.uas.auth.integration;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * @description 附加图片验证
 * @author weicong
 * @time   2019年9月13日 下午1:37:01
 * @version 1.0
 */
public abstract class AbstractIntegrationAuthenticator implements IntegrationAuthenticator {

	@Override
	public UserDetails authenticate(IntegrationContext context) {
		
		// 1、imgValidate 图片校验码
		
		// 2、类型检验
		return check(context);
	}

	protected abstract UserDetails check(IntegrationContext context);

	@Override
	public boolean support(IntegrationContext integrationContext) {
		if (AuthenticationEnum.NONE == AuthenticationEnum.getAuthType(integrationContext.getAuthType())) {
			return false;
		}
		return true;
	}
	
	
}
