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
 * 用户信息
 * </p>
 *
 * @author weicong
 * @since 2019-06-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "SysUser对象", description = "用户信息")
public class SysUser implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;

	@ApiModelProperty(value = "用户名")
	private String username;

	@ApiModelProperty(value = "密码")
	private String password;

	@ApiModelProperty(value = "随机盐")
	private String salt;

	@ApiModelProperty(value = "电话号码")
	private String phone;

	@ApiModelProperty(value = "头像")
	private String head;

	@ApiModelProperty(value = "微信openid")
	private String wxOpenid;

	@ApiModelProperty(value = "qq openid")
	private String qqOpenid;

	@ApiModelProperty(value = "删除标识，0：正常，1：删除")
	private Boolean delFlag;

	@ApiModelProperty(value = "创建者")
	private Integer creator;

	@ApiModelProperty(value = "创建时间 2038年后失效")
	private LocalDateTime createTime;

	@ApiModelProperty(value = "最后更新时间 2038年后失效")
	private LocalDateTime updateTime;

}
