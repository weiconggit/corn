package org.weicong.uas.auth.integration;
/**
 * @description 集成认证管理器
 * @author weicong
 * @date 2019年9月13日
 * @version 1.0
 */
@Deprecated
public class IntegrationContextHolder {
	
    private static ThreadLocal<IntegrationContext> holder = new ThreadLocal<>();

    public static void set(IntegrationContext integrationContext){
        holder.set(integrationContext);
    }

    public static IntegrationContext get(){
        return holder.get();
    }

    public static void clear(){
        holder.remove();
    }

}
