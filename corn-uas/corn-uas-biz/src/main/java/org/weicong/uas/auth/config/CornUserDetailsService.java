package org.weicong.uas.auth.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.weicong.common.auth.config.URLUser;
import org.weicong.uas.entity.SysUser;
import org.weicong.uas.service.ISysUserService;

import lombok.AllArgsConstructor;

/**
 * @description 
 * @author weicong
 * @date 2019年8月28日
 * @version 1.0
 */
@AllArgsConstructor
@Service
public class CornUserDetailsService implements UserDetailsService {

	private final ISysUserService sysUserService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUser sysUser = sysUserService.getByUsername(username);
		// TODO weicong get data from database
		List<String> urList = new ArrayList<>();
		urList.add("GET/order/{id}");
		urList.add("GET/product/{id}");
		List<SimpleGrantedAuthority> roles = new ArrayList<>();
		return new URLUser(urList, sysUser.getUsername(), sysUser.getPassword(), roles);
	}

}
