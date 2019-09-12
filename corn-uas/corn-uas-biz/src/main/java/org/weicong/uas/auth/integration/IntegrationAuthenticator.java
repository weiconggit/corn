package org.weicong.uas.auth.integration;
/**
 * @description 
 * @author weicong
 * @date 2019年9月11日
 * @version 1.0
 */
public interface IntegrationAuthenticator {
	
	/**
     * 处理集成认证
     * @param integrationAuthentication
     * @return
     */
    void authenticate(IntegrationAuthentication integrationAuthentication);


    /**
     * 进行预处理
     * @param integrationAuthentication
     */
    void prepare(IntegrationAuthentication integrationAuthentication);

     /**
     * 判断是否支持集成认证类型
     * @param integrationAuthentication
     * @return
     */
    boolean support(IntegrationAuthentication integrationAuthentication);

    /** 认证结束后执行
     * @param integrationAuthentication
     */
    void complete(IntegrationAuthentication integrationAuthentication);


}
