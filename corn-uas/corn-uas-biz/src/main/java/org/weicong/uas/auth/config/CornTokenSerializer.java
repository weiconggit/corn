package org.weicong.uas.auth.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.weicong.common.auth.constant.SecurityRpEnum;
import org.weicong.common.auth.constant.SecurityRpInfo;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
 * @description token json 返回值
 * @author weicong
 * @date 2019年8月26日
 * @version 1.0
 */
public final class CornTokenSerializer extends StdSerializer<CornOAuth2AccessToken> {

	private static final long serialVersionUID = -9131492598696616764L;

	public CornTokenSerializer() {
		super(CornOAuth2AccessToken.class);
	}

	@Override
	public void serialize(CornOAuth2AccessToken value, JsonGenerator gen, SerializerProvider provider)
			throws IOException {
//		HttpServletRequest request = ((ServletRequestAttributes)(RequestContextHolder.currentRequestAttributes())).getRequest();
//		String requestHeader = request.getHeader("user-agent");
//		System.out.println("requestHeader = " + requestHeader);
//		if(requestHeader.indexOf("Windows") > 0){// PC Windows
//			System.out.println("pc windows");
//		}else if (requestHeader.indexOf("Macintosh") > 0) { // PC Mac OS X
//			System.out.println("pc Macintosh");
//		}else if (requestHeader.indexOf("X11") > 0) {// PC Linux 
//			System.out.println("pc X11");
//        }else if (requestHeader.indexOf("Android") > 0) {// MOBILE Android
//			System.out.println("Android");
//		}else if (requestHeader.indexOf("iPhone") > 0) {// MOBILE iPhone OS
//			System.out.println("iPhone");
//		}else if (requestHeader.indexOf("windows phone") > 0){// 待验证
//			System.out.println("windows phone");
//		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("token_type", value.getTokenType());
		map.put("access_token", value.getValue());
		map.put("expiresin", value.getExpiresIn());
		map.put("scope", value.getScope());
		map.put("refresh_token", value.getRefreshToken());
		map.put("additional", value.getAdditionalInformation());
		SecurityRpInfo<Map<String, Object>> securityRpInfo = new SecurityRpInfo<>(SecurityRpEnum.GET_TOKEN_SUCCESS, map);
		map = null;
		ObjectMapper objectMapper = new ObjectMapper();
		gen.writeRawValue(objectMapper.writeValueAsString(securityRpInfo));
		objectMapper = null;
	}
}
