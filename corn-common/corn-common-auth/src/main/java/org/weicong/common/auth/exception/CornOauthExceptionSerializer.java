package org.weicong.common.auth.exception;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.weicong.common.auth.constant.SecurityRpEnum;
import org.weicong.common.auth.constant.SecurityRpInfo;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
 * @description 
 * @author weicong
 * @time   2019年8月25日 下午2:07:05
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
		
		//可以不用下面的代码，只用 jsonGenerator.writeRawValue(myJsonString)  输出自定义字符串
//        jsonGenerator.writeStartObject();
//        jsonGenerator.writeNumberField("code4444", value.getHttpErrorCode());
//        jsonGenerator.writeBooleanField("status", false);
//        jsonGenerator.writeObjectField("data", null);
//        jsonGenerator.writeObjectField("errors", Arrays.asList(value.getOAuth2ErrorCode(),value.getMessage()));
//        if (value.getAdditionalInformation()!=null) {
//            for (Map.Entry<String, String> entry : value.getAdditionalInformation().entrySet()) {
//                String key = entry.getKey();
//                String add = entry.getValue();
//                jsonGenerator.writeStringField(key, add);
//            }
//        }
//        jsonGenerator.writeEndObject();

    }
}
