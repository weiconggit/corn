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
		if (authentication == null) {
			return result; 
		}
		FilterInvocation fi = (FilterInvocation) object;
		HttpServletRequest request = fi.getRequest();
		System.err.println("====投票器====");
//		HandlerMapping handlerMapping = new RequestMappingHandlerMapping();
//		HandlerExecutionChain chain = handlerMapping.getHandler(fi.getRequest());
//		String url = fi.getRequest().getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE).toString();
//		for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
//			if (url.equals(grantedAuthority.getAuthority())) {
//				result = ACCESS_GRANTED;// 访问通过
//				return result;
//			}
//		}
		
		
		
		// （一）=========== 使用 authenticationf 方式
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
		
		// ===========弃用 authentication 使用 redis 方式
		// 1、从authentication中获取到用户id
		// 2、从redis中获取用户的权限资源url集（根据用户id去找）
		// 3、比较0步骤中获取的url和redis获取的url信息
		
		return result;
	}


}
