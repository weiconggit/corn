package org.weicong.uas.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户角色关联表
 * </p>
 *
 * @author weicong
 * @since 2019-06-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "SysUserRole对象", description = "用户角色关联表")
public class SysUserRole implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "用户id")
	private Integer userId;

	@ApiModelProperty(value = "角色id")
	private Integer roleId;

}
