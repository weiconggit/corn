package org.weicong.common.auth.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @description 自定义过滤器
 * @author weicong
 * @date 2019年8月27日
 * @version 1.0
 */
@Deprecated
public class CornAuthenticationProcessingFilter implements Filter {

	private HandlerMapping handlerMapping;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		ServletContext servletContext = filterConfig.getServletContext();
		WebApplicationContext webAC = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		HandlerMapping handlerMapping = webAC.getBean(RequestMappingHandlerMapping.class);
		System.out.println("==" + handlerMapping);
		this.handlerMapping = handlerMapping;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (null != handlerMapping) {
			try {
				HandlerExecutionChain chain1 = handlerMapping.getHandler((HttpServletRequest) request);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Object url = request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
		System.out.println("=====================" + url);
		chain.doFilter(request, response);
	}


	

	
	
}
