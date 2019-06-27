package org.weicong.common.base.page;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @description 
 * @author weicong
 * @date 2019年6月11日
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("分页返回对象")
public class PageDTO implements Serializable{
	
	private static final long serialVersionUID = -9188142929064107223L;

	@ApiModelProperty(value = "每页显示条数，默认是10", example = "2")
    private long size;
    
	@ApiModelProperty(value = "当前页", example = "1")
    private long current;
	  
	@ApiModelProperty(value = "总数", example = "5")
    private long total;
		
	@ApiModelProperty(value = "总页数", example = "3")
	private long pages;
	
}
