package org.weicong.common.auth.config;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.weicong.common.auth.constant.SecurityRpEnum;
import org.weicong.common.auth.constant.SecurityRpInfo;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * @description 访问拒绝处理器（执行条件：登录、访问其无权访问的接口）
 * @author weicong
 * @date 2019年8月23日
 * @version 1.0
 */
@Slf4j
public class CornAccessDeniedHandler extends OAuth2AccessDeniedHandler{

	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		log.info("访问拒绝处理器：授权失败 [{}]", request.getRequestURL());
		
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);// 401 未授权
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
		response.getWriter().write(objectMapper.writeValueAsString(
				new SecurityRpInfo<String>(SecurityRpEnum.NO_AUTHOR, accessDeniedException.getMessage())));
	}

}
