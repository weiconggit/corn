package org.weicong.common.base.constant;

import java.io.Serializable;

import org.weicong.common.base.page.PageDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @description 请求返回体
 * @author weicong
 * @date 2019年6月9日 
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RpInfo<E> implements Serializable{
	
	private static final long serialVersionUID = -939399577904238648L;

    /**
     * @code : 响应状态码
     */
    private Integer code;

    /**
     * @msg : 该状态码对应的提示信息
     */
    private String msg;

    /**
     * @data : 响应数据
     */
    private E data;
    
    
    /**
     * @page ： 分页信息
     */
    private PageDTO page;

    public RpInfo(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    
    /**
     * 返回成功信息
     * @param <E>
     * @return
     */
    public static <E> RpInfo<E> buildSuccessInfo() {
        return RpInfo.buildRpInfo(RpEnum.SUCCESS, null);
    }
    
    /**
     * 返回查询结果信息
     * @param <E>  返回数据类型
     * @param data 返回数据
     * @return
     */
    public static <E> RpInfo<E> buildSuccessInfo(E data) {
        return RpInfo.buildRpInfo(RpEnum.SUCCESS, data);
    }
    
    /**
     * 返回分页查询信息
     * @param <E> 返回数据类型
     * @param data 返回数据
     * @param pageDTO 分页返回对象
     * @return
     */
    public static <E> RpInfo<E> buildSuccessInfo(E data, PageDTO pageDTO) {
        return new RpInfo<E>(RpEnum.SUCCESS.code, RpEnum.SUCCESS.msg, data, pageDTO);
    }
    
    public static <E> RpInfo<E> buildRpInfo(RpEnum rpEnum) {
        return new RpInfo<E>(rpEnum.code, rpEnum.msg);
    }

    public static <E> RpInfo<E> buildRpInfo(RpEnum rpEnum, E data) {
        return new RpInfo<E>(rpEnum.code, rpEnum.msg).setData(data);
    }

    public static <E> RpInfo<E> buildRpInfo(Integer code, String msg) {
        return new RpInfo<E>(code, msg);
    }

}
