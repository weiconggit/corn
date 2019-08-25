package org.weicong.uas.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

/**
 * @description 
 * @author weicong
 * @time   2019年8月25日 下午2:02:26
 * @version 1.0
 */
@SuppressWarnings("rawtypes")
public class CornWebResponseExceptionTranslator implements WebResponseExceptionTranslator {

	/* (non-Javadoc)
	 * @see org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator#translate(java.lang.Exception)
	 */
	@Override
	public ResponseEntity translate(Exception exception) throws Exception {
		if (exception instanceof OAuth2Exception) {
            OAuth2Exception oAuth2Exception = (OAuth2Exception) exception;
            return ResponseEntity
                    .status(oAuth2Exception.getHttpErrorCode())
                    .body(new CornOAuth2Exception(oAuth2Exception.getMessage()));
        }else if(exception instanceof AuthenticationException){
            AuthenticationException authenticationException = (AuthenticationException) exception;
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new CornOAuth2Exception(authenticationException.getMessage()));
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CornOAuth2Exception(exception.getMessage()));
	}

}
