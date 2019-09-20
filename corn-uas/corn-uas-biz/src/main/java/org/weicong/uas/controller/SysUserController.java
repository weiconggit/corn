package org.weicong.uas.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.weicong.common.auth.constant.SecurityRpInfo;
import org.weicong.common.base.constant.RpInfo;
import org.weicong.uas.entity.SysUser;
import org.weicong.uas.service.ISysUserService;

import lombok.AllArgsConstructor;

/**
 * <p>
 * 用户信息 前端控制器
 * </p>
 *
 * @author weicong
 * @since 2019-06-28
 */
@AllArgsConstructor
@RestController
@RequestMapping("/sys-user")
public class SysUserController {
	
	private final ISysUserService iSysUserService;

	// TODO this is for test
	@PostMapping("register")
	public RpInfo<String> register(@RequestBody SysUser sysUser) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		sysUser.setPassword(encoder.encode(sysUser.getPassword()));
		iSysUserService.save(sysUser);
		return RpInfo.buildRpInfo(0, "注册成功");
	}

}
