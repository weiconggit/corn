package org.weicong.common.auth.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * @description 
 * @author weicong
 * @date 2019年8月30日
 * @version 1.0
 */
@Slf4j
public class URLGrantedAuthorityInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String url = new StringBuilder(request.getMethod())
				.append(request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE).toString()).toString();
		
		for (GrantedAuthority grantedAuthority : userDetails.getAuthorities()) {
			System.err.println("grantedAuthority.getAuthority()=" + grantedAuthority.getAuthority() + ", urlMethod+url=" + url);
			if (grantedAuthority.getAuthority().equals(url)) {
				return true;
			}
		}
		
		log.info("URL grant denied ! userDetails:[{}], url:[{}]", userDetails, url);
		throw new AccessDeniedException("grant denied");
	}

}
