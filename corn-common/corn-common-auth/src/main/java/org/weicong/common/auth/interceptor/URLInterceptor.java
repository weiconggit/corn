package org.weicong.common.auth.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.weicong.common.auth.config.CornAccessDeniedHandler;
import org.weicong.common.auth.config.URLUser;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * @description URL鉴权拦截器
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
		if (null != object && object instanceof URLUser) {
			URLUser urlUser = (URLUser) object;
			String url = new StringBuilder(request.getMethod())
					.append(request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE).toString()).toString();
			for (String urlString : urlUser.getUrlList()) {
				// TODO DEL
				System.err.println("urlString=" + urlString + ", url=" + url);
				if (urlString.equals(url)) {
					return true;
				}
			}
			log.info("URL grant denied ! urlUser:[{}], url:[{}]", urlUser, url);
			new CornAccessDeniedHandler(new ObjectMapper()).handle(request, response, new AccessDeniedException("access denied !"));
			return false;
		}
		log.info("object not instanceof URLUser !");
		return false;
	}

}
