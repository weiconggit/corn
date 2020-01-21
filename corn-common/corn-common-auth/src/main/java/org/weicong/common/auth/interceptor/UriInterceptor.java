package org.weicong.common.auth.interceptor;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.weicong.common.auth.config.CornAccessDeniedHandler;
import org.weicong.common.auth.config.CornUser;

import lombok.extern.slf4j.Slf4j;

/**
 * @description Resful Uri 动态鉴权拦截器
 * @author weicong
 * @date 2019年8月30日
 * @version 1.0
 */
@Slf4j
public class UriInterceptor implements HandlerInterceptor {
	
	// ~ Static final object
	// ==================================================================
	
	private static final AccessDeniedException NO_LOGIN = new AccessDeniedException("access denied, no login info");
	private static final AccessDeniedException NO_URI = new AccessDeniedException("access denied, no uri");
	private static final AccessDeniedException NO_AUTH = new AccessDeniedException("access denied");
	

	// ~ Main method
	// ==================================================================
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (null == object || !(object instanceof CornUser)) {
			throwException(request, response, NO_LOGIN);
			return false;
		}
			
		CornUser cornUser = (CornUser) object;
		log.debug("current cornUser is [{}]", cornUser);
		
		String uri = new StringBuilder()
				.append(request.getMethod())
				.append(request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE).toString())
				.toString();
		log.debug("current uri is [{}]", uri);
		
		List<String> uriList = cornUser.getUrlList();
		
		if (CollectionUtils.isEmpty(uriList)) {
			throwException(request, response, NO_URI);
			return false;
		}
		
		for (int i = 0, size = uriList.size(); i < size; i++) {
			if (uriList.get(i).equals(uri)) {
				log.debug("uri [{}] is true", uriList.get(i));
				return true;
			}
		}
		
		throwException(request, response, NO_AUTH);
		return false;
	}
	
	private void throwException(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) {
		try {
			new CornAccessDeniedHandler().handle(request, response, accessDeniedException);
		} catch (IOException | ServletException e) {
			log.error("throwException error");
			e.printStackTrace();
		}
	}

}
