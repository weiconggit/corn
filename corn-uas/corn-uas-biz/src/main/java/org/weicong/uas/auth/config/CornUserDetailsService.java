package org.weicong.uas.auth.config;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Service;
import org.weicong.common.base.util.StringUtil;
import org.weicong.uas.auth.integration.DefaultAuthenticator;
import org.weicong.uas.auth.integration.IntegrationAuthenticator;

/**
 * @description 自定义用户加载源
 * @author weicong
 * @date 2019年8月28日
 * @version 1.0
 */
@Service
public class CornUserDetailsService implements UserDetailsService, ApplicationContextAware {

	private Map<String, IntegrationAuthenticator> authenticators;
	private IntegrationAuthenticator defaultAuthenticator;
	
	@Autowired
	private HttpServletRequest request;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		String authType = request.getParameter("auth_type");
		
		if (StringUtil.isBlank(authType)) {
			return defaultAuthenticator.authenticate(username, "");
		}
		
		IntegrationAuthenticator authenticator = getIntegrationAuthenticator(authType);
		
		if (!authenticator.support(authType)) {
			throw new OAuth2Exception("不支持该登录方式");
		}
		
		return authenticator.authenticate(username, request.getParameter("password"));	
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.defaultAuthenticator = applicationContext.getBean(DefaultAuthenticator.class);
		this.authenticators = applicationContext.getBeansOfType(IntegrationAuthenticator.class);
	}

	private IntegrationAuthenticator getIntegrationAuthenticator(String authType) {
		return authenticators.values().stream().filter(v -> v.getClass().getSimpleName().startsWith(authType.toUpperCase())).findFirst().get();
	}
}
