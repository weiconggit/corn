package org.weicong.common.auth.config;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.weicong.common.auth.constant.SecurityRpEnum;
import org.weicong.common.auth.constant.SecurityRpInfo;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * @description 认证入口（执行条件：未登录，访问被保护资源） or implements AuthenticationEntryPoint 
 * @author weicong
 * @date 2019年8月23日
 * @version 1.0  
 */
@Slf4j
public class CornAuthenticationEntryPoint extends OAuth2AuthenticationEntryPoint {

	private static final ObjectMapper objectMapper = new ObjectMapper();
	
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
		log.info("未登录禁止访问 [{}]", request.getRequestURL());
    	
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);// 403 禁止访问
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
		response.getWriter().write(objectMapper.writeValueAsString(
        		new SecurityRpInfo<String>(SecurityRpEnum.NO_LOGIN, e.getMessage())));
    }

}
