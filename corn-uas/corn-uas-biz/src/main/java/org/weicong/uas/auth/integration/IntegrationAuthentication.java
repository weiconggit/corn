package org.weicong.uas.auth.integration;

import java.util.Map;

import lombok.Data;

/**
 * @description 
 * @author weicong
 * @date 2019年9月11日
 * @version 1.0
 */
@Data
public class IntegrationAuthentication {
	
	private String authType;
    private String username;
//    private Map<String,String[]> authParameters;

//    public String getAuthParameter(String paramter){
//        String[] values = this.authParameters.get(paramter);
//        if(values != null && values.length > 0){
//            return values[0];
//        }
//        return null;
//    }

}
