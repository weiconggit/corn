package org.weicong.common.auth.config;

import java.util.Collection;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * @description 
 * @author weicong
 * @date 2019年8月28日
 * @version 1.0
 */
@Slf4j
@Deprecated
public class CornAccessDecisionVoter implements AccessDecisionVoter<Object> {

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

	@Override
	public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
		int result = ACCESS_DENIED; // 访问拒绝
		if (authentication == null) return result; 
		
		FilterInvocation fi = (FilterInvocation) object;
		HttpServletRequest request = fi.getRequest();
		System.err.println("====投票器====");
		
		// 提前触发初始化无效
//		HandlerMapping handlerMapping = new RequestMappingHandlerMapping();
//		HandlerExecutionChain chain = handlerMapping.getHandler(fi.getRequest());
//		// 无法在 filter 中获取 restful 风格的 url，因为 spring handler 初始化在 filter 之后
//		String url = fi.getRequest().getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE).toString();

		// 0、这里可以获取到该用户将要访问的api资源的url信息
//		String url = fi.getRequestUrl();
//		
//		log.info("-----------------" + url);
//		log.info("-----------------" + fi.getHttpRequest().getMethod());
//		StringTokenizer urlToken = new StringTokenizer(url, "/");
//		for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
//			if (url.equals(grantedAuthority.getAuthority())) {
//				result = ACCESS_GRANTED;// 访问通过
//				return result;
//			}
//		}
		
		return result;
	}


}
