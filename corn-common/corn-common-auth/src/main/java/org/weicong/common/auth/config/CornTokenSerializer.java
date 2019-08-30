package org.weicong.common.auth.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
