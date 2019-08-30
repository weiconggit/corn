package org.weicong.uas.auth;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.weicong.common.auth.constant.SecurityRpEnum;
import org.weicong.common.auth.constant.SecurityRpInfo;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @description 认证失败处理器，仅用于表单登录
 * @author weicong
 * @date 2019年8月23日
 * @version 1.0
 */
@Deprecated
@Slf4j
@AllArgsConstructor
public class CornAuthenticationFailureHandler implements AuthenticationFailureHandler{

	private final ObjectMapper objectMapper;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		log.info("认证失败处理器：认证失败 [{}]", exception);
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);// 401 登录失败
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
		response.getWriter().write(objectMapper.writeValueAsString(
        		new SecurityRpInfo<String>(SecurityRpEnum.ERROR_AUTHEN, exception.getMessage())));
	}

}
