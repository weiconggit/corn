package org.weicong.uas.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色信息
 * </p>
 *
 * @author weicong
 * @since 2019-06-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "SysRole对象", description = "角色信息")
public class SysRole implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@ApiModelProperty(value = "角色名称")
	private String name;

	@ApiModelProperty(value = "删除标识，0：正常，1：删除")
	private Boolean delFlag;

	@ApiModelProperty(value = "创建者")
	private Integer creator;

	@ApiModelProperty(value = "创建时间 2038年后失效")
	private LocalDateTime createTime;

	@ApiModelProperty(value = "最后更新时间 2038年后失效")
	private LocalDateTime updateTime;

}
