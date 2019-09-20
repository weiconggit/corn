package org.weicong.common.auth.exception;

import java.io.IOException;

import org.weicong.common.auth.constant.SecurityRpEnum;
import org.weicong.common.auth.constant.SecurityRpInfo;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
 * @description 自定义oauth2异常序列化
 * @author weicong
 * @time   2019年8月25日
 * @version 1.0
 */
public class CornOauthExceptionSerializer extends StdSerializer<CornOAuth2Exception> {

	private static final long serialVersionUID = 461912971586427765L;
	
	public CornOauthExceptionSerializer() {
        super(CornOAuth2Exception.class);
    }
	@Override
    public void serialize(CornOAuth2Exception value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
		SecurityRpInfo<String> securityRpInfo = new SecurityRpInfo<String>(SecurityRpEnum.ERROR_AUTHEN, value.getMessage(), value.getOAuth2ErrorCode());
		ObjectMapper objectMapper = new ObjectMapper();
		jsonGenerator.writeRawValue(objectMapper.writeValueAsString(securityRpInfo));
    }
}
