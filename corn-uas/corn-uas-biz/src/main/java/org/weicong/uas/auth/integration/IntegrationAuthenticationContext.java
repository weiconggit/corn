package org.weicong.uas.auth.integration;
/**
 * @description 
 * @author weicong
 * @date 2019年9月11日
 * @version 1.0
 */
public class IntegrationAuthenticationContext {
	
    private static ThreadLocal<IntegrationAuthentication> holder = new ThreadLocal<>();

    public static void set(IntegrationAuthentication integrationAuthentication){
        holder.set(integrationAuthentication);
    }

    public static IntegrationAuthentication get(){
        return holder.get();
    }

    public static void clear(){
        holder.remove();
    }

}
