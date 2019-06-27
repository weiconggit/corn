package org.weicong.uas.entity;

import java.time.LocalDate;

import lombok.Data;

/**
 * @description 
 * @author weicong
 * @date 2019年6月27日
 * @version 1.0
 */
@Data
public class SysUser {

	private Integer id;
	private String username;
	private String password;
	private String salt;
	private String phone;
	private String head;
	private String wxOpenid;
	private String qqOpenid;
	private Integer delFlag;
	private Integer enableFlag;
	private Integer creator;
	private LocalDate createTime;
	private LocalDate updateTime;
	private Integer version;
}
