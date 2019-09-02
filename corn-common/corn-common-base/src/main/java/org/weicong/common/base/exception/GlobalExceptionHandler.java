package org.weicong.common.base.exception;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.weicong.common.base.constant.RpEnum;
import org.weicong.common.base.constant.RpInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * @description 全局异常处理
 * @author weicong
 * @date 2019年6月9日
 * @version 1.0
 */
@Slf4j
@CrossOrigin
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = { MissingServletRequestParameterException.class
			, MethodArgumentNotValidException.class
			, BindException.class
			, Exception.class
			, BizException.class })
	public RpInfo<?> processException(Exception ex, HttpServletRequest request, HttpServletResponse response) {
		// 参数错误
		if (ex instanceof MissingServletRequestParameterException) {
			log.info("MissingServletRequestParameterException=", ex);
			return RpInfo.buildRpInfo(RpEnum.ERROR_PARAMETER);
		}

		// 统一参数校验错误转换
		if (ex instanceof MethodArgumentNotValidException) {
			log.info("MethodArgumentNotValidException=", ex);
			List<ObjectError> errors = ((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors();
			return RpInfo.buildRpInfo(RpEnum.ERROR_PARAMETER)
					.setMsg(errors.isEmpty() ? "未知参数错误！" : errors.get(0).getDefaultMessage());
		}
		if (ex instanceof BindException) {
			log.info("BindException=", ex);
			List<ObjectError> errors = ((BindException) ex).getBindingResult().getAllErrors();
			return RpInfo.buildRpInfo(RpEnum.ERROR_PARAMETER)
					.setMsg(errors.isEmpty() ? "未知参数错误！" : errors.get(0).getDefaultMessage());
		}

		// 业务异常
		if (ex instanceof BizException) {
			BizException exception = (BizException) ex;
			if (RpEnum.NO_AUTHEN.getCode().equals(exception.getFrontCode())) {
				log.info(String.format("BizException: code=%d, message=%s", exception.getCode(),
						exception.getMessage()));
				return RpInfo.buildRpInfo(RpEnum.NO_AUTHEN).setMsg(exception.getMessage());
			} else if (RpEnum.NO_AUTHOR.getCode().equals(exception.getFrontCode())) {
				log.info(String.format("BizException: code=%d, message=%s", exception.getCode(),
						exception.getMessage()));
				return RpInfo.buildRpInfo(RpEnum.NO_AUTHOR).setMsg(exception.getMessage());
			} else if (RpEnum.NO_PERMIT.getCode().equals(exception.getFrontCode())) {
				log.info(String.format("BizException: code=%d, message=%s", exception.getCode(),
						exception.getMessage()));
				return RpInfo.buildRpInfo(RpEnum.NO_PERMIT).setMsg(exception.getMessage());
			} else {
				log.info(String.format("BizException: code=%d, message=%s", exception.getCode(),
						exception.getMessage()));
				return RpInfo.buildRpInfo(RpEnum.ERROR_SYSTEM).setMsg(exception.getMessage());
			}
		}
		log.info("全局异常ex= ", ex);
		return RpInfo.buildRpInfo(RpEnum.ERROR_SYSTEM);
	}

}
