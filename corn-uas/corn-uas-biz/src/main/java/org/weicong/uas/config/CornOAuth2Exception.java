package org.weicong.uas.config;

import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @description 
 * @author weicong
 * @time   2019年8月25日 下午2:05:00
 * @version 1.0
 */
@JsonSerialize(using = CornOauthExceptionSerializer.class)
public class CornOAuth2Exception extends OAuth2Exception {
	public CornOAuth2Exception(String msg) {
        super(msg);
    }
}
