package org.weicong.uas.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色菜单关联表
 * </p>
 *
 * @author weicong
 * @since 2019-06-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "SysRoleMenu对象", description = "角色菜单关联表")
public class SysRoleMenu implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "角色id")
	private Integer roleId;

	@ApiModelProperty(value = "菜单id")
	private Integer menuId;

}
