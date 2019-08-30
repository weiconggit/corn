package org.weicong.uas.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.weicong.common.auth.config.URLGrantedAuthority;
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
		List<URLGrantedAuthority> list = new ArrayList<URLGrantedAuthority>();
		// TODO weicong get data from database
		list.add(new URLGrantedAuthority("GET/order/{id}"));
		return new User(sysUser.getUsername(), sysUser.getPassword(), list);
	}

}
