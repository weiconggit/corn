package org.weicong.uas.auth.integration;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * @description 
 * @author weicong
 * @date 2019年9月13日
 * @version 1.0
 */
public interface IntegrationAuthenticator {
	
	/**
     * 处理相应类型认证
     * @param 
     * @return
     */
	UserDetails authenticate(IntegrationContext integrationContext);

     /**
     * 判断是否支持集成认证类型
     * @param 
     * @return
     */
    boolean support(IntegrationContext integrationContext);

}
