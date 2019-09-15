package org.weicong.uas.auth.integration;

import java.io.IOException;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.GenericFilterBean;

/**
 * @description
 * @author weicong
 * @date 2019年9月13日
 * @version 1.0
 */
//@Component
@Deprecated
public class IntegrationAuthenticationFilter extends GenericFilterBean {

	private static final String AUTH_TYPE = "auth_type";
	private static final String OAUTH_TOKEN_URL = "/oauth/token";

	private final Map<String, IntegrationAuthenticator> map;
	
	private final RequestMatcher requestMatcher = new OrRequestMatcher(
			new AntPathRequestMatcher(OAUTH_TOKEN_URL, "POST"),
			new AntPathRequestMatcher(OAUTH_TOKEN_URL, "GET"));

	public IntegrationAuthenticationFilter(Map<String, IntegrationAuthenticator> map) {
		this.map = map;
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		System.err.println("1===================");
		if (requestMatcher.matches(request)) {
//			IntegrationContext context = new IntegrationContext();
//			context.setAuthType(request.getParameter(AUTH_TYPE));
//			context.setUsername(request.getParameter("username"));
//			context.setUsername(request.getParameter("password"));
//			// 1、判断是否支持
//			IntegrationAuthenticator authenticator = getIntegrationAuthenticator(context);
//			if (!authenticator.support(context)) {
//				throw new OAuth2Exception("认证类型不支持");
//			}
//			// 2、集成认证特定类型认证
//			authenticator.authenticate(context);
			System.err.println("前");
			chain.doFilter(request, response);
			Object object  = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			System.err.println("后 " + object);
		}
	}

	private IntegrationAuthenticator getIntegrationAuthenticator(IntegrationContext integrationContext) {
		return map.get(integrationContext.getAuthType() + IntegrationAuthenticator.class.getSimpleName());
	}

}
