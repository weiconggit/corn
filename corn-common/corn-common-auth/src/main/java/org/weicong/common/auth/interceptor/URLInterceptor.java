package org.weicong.common.auth.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.weicong.common.auth.config.CornAccessDeniedHandler;
import org.weicong.common.auth.config.CornUser;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * @description URL动态鉴权拦截器
 * @author weicong
 * @date 2019年8月30日
 * @version 1.0
 */
@Slf4j
public class URLInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (null == object || !(object instanceof CornUser)) {
			log.info("object is not instanceof CornUser !");
			return false;
		}
			
		CornUser cornUser = (CornUser) object;
		log.debug("current cornUser is [{}]", cornUser);
		
		String uri = new StringBuilder(request.getMethod())
				.append(request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE).toString()).toString();
		log.debug("current uri is [{}]", uri);
		
		List<String> uriList = cornUser.getUrlList();
		
		for (int i = 0, size = uriList.size(); i < size; i++) {
			if (uriList.get(i).equals(uri)) {
				log.debug("uri [{}] is true", uriList.get(i));
				return true;
			}
		}
		
		new CornAccessDeniedHandler(new ObjectMapper()).handle(request, response, new AccessDeniedException("access denied !"));
		
		return false;
	}

}
