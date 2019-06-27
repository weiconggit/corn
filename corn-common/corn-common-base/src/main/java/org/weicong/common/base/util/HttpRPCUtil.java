package org.weicong.common.base.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @description Http远程调用
 * @author weicong
 * @date 2019年6月12日
 * @version 1.0
 */
public abstract class HttpRPCUtil {
	
    public static String httpRequest(HttpMethod httpMethod, String requestUrl, String sysTicket, Object params) throws Exception {
        if (null ==  httpMethod || StringUtil.isBlank(requestUrl)) {
			throw new Exception("远程调用方法参数为空！");
		}
    	// 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("sys_identify", sysTicket);

        // 参数序列化
        ObjectMapper mapper = new ObjectMapper();
        String result = null;
        RestTemplate restTemplate = new RestTemplate();
        try {
            String value = mapper.writeValueAsString(params);
            HttpEntity<String> requestEntity = new HttpEntity<>(value, headers);
            //  执行HTTP请求
            switch (httpMethod) {
                case PUT:
                    restTemplate.put(requestUrl, requestEntity, String.class);
                    break;
                case POST:
                    result = restTemplate.postForEntity(requestUrl, requestEntity, String.class).getBody();
                    break;
                case GET:
                    result = restTemplate.exchange(requestUrl, HttpMethod.GET, requestEntity, String.class).getBody();
			default:
				break;
            }
        } catch (Exception e) {
        	throw new Exception("远程调用异常！e = " + e.getMessage());
        }
        return result;
    }

}
