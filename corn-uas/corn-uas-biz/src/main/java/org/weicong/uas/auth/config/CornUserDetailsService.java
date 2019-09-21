package org.weicong.uas.auth.config;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.weicong.common.base.util.StringUtil;
import org.weicong.uas.auth.integration.DefaultAuthenticator;
import org.weicong.uas.auth.integration.IntegrationAuthenticator;
import org.weicong.uas.auth.integration.IntegrationContext;

/**
 * @description 
 * @author weicong
 * @date 2019年8月28日
 * @version 1.0
 */
@Service
public class CornUserDetailsService implements UserDetailsService, ApplicationContextAware {

	private Map<String, IntegrationAuthenticator> authenticators;
	private IntegrationAuthenticator defaultAuthenticator;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
//		String requestHeader = request.getHeader("user-agent");
//		System.out.println("requestHeader = " + requestHeader);
//		if(requestHeader.indexOf("Windows") > 0){// PC Windows
//			System.out.println("pc windows");
//		}else if (requestHeader.indexOf("Macintosh") > 0) { // PC Mac OS X
//			System.out.println("pc Macintosh");
//		}else if (requestHeader.indexOf("X11") > 0) {// PC Linux 
//			System.out.println("pc X11");
//        }else if (requestHeader.indexOf("Android") > 0) {// MOBILE Android
//			System.out.println("Android");
//		}else if (requestHeader.indexOf("iPhone") > 0) {// MOBILE iPhone OS
//			System.out.println("iPhone");
//		}else if (requestHeader.indexOf("windows phone") > 0){// 待验证
//			System.out.println("windows phone");
//		}
		System.err.println("=============-----");
		IntegrationContext context = new IntegrationContext();
		context.setAuthType(request.getParameter("auth_type"));
		context.setUsername(request.getParameter("username"));
		context.setPassword(request.getParameter("password"));
		if (StringUtil.isBlank(context.getAuthType())) {
			return defaultAuthenticator.authenticate(context);
		}
		
		IntegrationAuthenticator authenticator = getIntegrationAuthenticator(context.getAuthType());
		if (!authenticator.support(context)) {
			throw new OAuth2Exception("不支持该登录方式");
		}
		return authenticator.authenticate(context);	
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
