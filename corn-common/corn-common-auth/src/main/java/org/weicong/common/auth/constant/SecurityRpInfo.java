package org.weicong.common.auth.constant;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @description 认证权限返回体
 * @author weicong
 * @date 2019年8月23日
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SecurityRpInfo<E> implements Serializable{
	
	private static final long serialVersionUID = 8442112128157981918L;

	/**
     * @code : 响应状态码
     */
    private Integer code;

    /**
     * @msg : 该状态码对应的提示信息
     */
    private String msg;

    
    private E data;
    
    public SecurityRpInfo(SecurityRpEnum rpEnum, E data){
    	this.code = rpEnum.code;
    	this.msg = rpEnum.msg;
    	this.data = data;
    }

}
