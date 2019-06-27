package org.weicong.common.base.exception;

import java.text.MessageFormat;

import org.weicong.common.base.entity.RpEnum;

import lombok.Getter;

/**
 * @description 业务异常信息
 * @author weicong
 * @date 2019年6月9日 
 * @version 1.0
 */
@Getter
public class BizException extends RuntimeException{
	
	private static final long serialVersionUID = -1971580736283989021L;

	private Integer code;

    private Integer frontCode;

    private String message;

	public BizException(String exceptionMsg) {
    	this(RpEnum.ERROR_SYSTEM, exceptionMsg);
	}
    
    public BizException(RpEnum rpEnum, String exceptionMsg, Object ... args) {
        super(null, null, true, false);
        this.frontCode = rpEnum.code;
        this.message = MessageFormat.format(exceptionMsg, args).replaceAll("\\{\\d+\\}", "");
    }
    
    public BizException(RpEnum rpEnum) {
        super(null, null, true, false);
        this.code = rpEnum.code;
        this.frontCode = rpEnum.code;
        this.message = rpEnum.getMsg();
    }

}
