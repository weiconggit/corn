package org.weicong.uas.auth.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.weicong.common.auth.config.URLUser;
import org.weicong.uas.auth.integration.IntegrationAuthentication;
import org.weicong.uas.auth.integration.IntegrationAuthenticationContext;
import org.weicong.uas.auth.integration.IntegrationAuthenticator;
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
	private final Map<String, IntegrationAuthenticator> map;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		HttpServletRequest request = ((ServletRequestAttributes)(RequestContextHolder.currentRequestAttributes())).getRequest();
		String requestHeader = request.getHeader("user-agent");
		System.out.println("requestHeader = " + requestHeader);
		if(requestHeader.indexOf("Windows") > 0){// PC Windows
			System.out.println("pc windows");
		}else if (requestHeader.indexOf("Macintosh") > 0) { // PC Mac OS X
			System.out.println("pc Macintosh");
		}else if (requestHeader.indexOf("X11") > 0) {// PC Linux 
			System.out.println("pc X11");
        }else if (requestHeader.indexOf("Android") > 0) {// MOBILE Android
			System.out.println("Android");
		}else if (requestHeader.indexOf("iPhone") > 0) {// MOBILE iPhone OS
			System.out.println("iPhone");
		}else if (requestHeader.indexOf("windows phone") > 0){// 待验证
			System.out.println("windows phone");
		}
	
		IntegrationAuthentication integ = IntegrationAuthenticationContext.get();
		System.err.println("===auth_type " + integ.getAuthType() + " " + IntegrationAuthenticator.class.getSimpleName());
		IntegrationAuthenticator tor = map.get(integ.getAuthType() + "IntegrationAtor");
		tor.authenticate(integ);
		
		SysUser sysUser = sysUserService.getByUsername(username);
		// TODO weicong get data from database
		System.err.println("=== load user ...");
		
		List<String> urList = new ArrayList<>();
		urList.add("GET/order/{id}");
		urList.add("GET/product/{id}");
		List<SimpleGrantedAuthority> roles = new ArrayList<>();
		return new URLUser(urList, sysUser.getUsername(), sysUser.getPassword(), roles);
	}

}
