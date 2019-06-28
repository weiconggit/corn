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
 * 菜单信息
 * </p>
 *
 * @author weicong
 * @since 2019-06-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "SysMenu对象", description = "")
public class SysMenu implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@ApiModelProperty(value = "菜单名称")
	private String name;

	@ApiModelProperty(value = "菜单类型")
	private Boolean type;

	@ApiModelProperty(value = "父类菜单id")
	private Integer parentId;

	@ApiModelProperty(value = "后端接口地址")
	private String backUrl;

	@ApiModelProperty(value = "前端路由地址")
	private String frontUrl;

	@ApiModelProperty(value = "图标")
	private String icon;

	@ApiModelProperty(value = "排序")
	private Integer sort;

	@ApiModelProperty(value = "删除标识，0：正常，1：删除")
	private Boolean delFlag;

	@ApiModelProperty(value = "创建者")
	private Integer creator;

	@ApiModelProperty(value = "创建时间 2038年后失效")
	private LocalDateTime createTime;

	@ApiModelProperty(value = "最后更新时间 2038年后失效")
	private LocalDateTime updateTime;

}
