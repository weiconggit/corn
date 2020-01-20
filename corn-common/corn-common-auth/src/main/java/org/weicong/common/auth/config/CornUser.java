package org.weicong.common.auth.config;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * @description 自定义 oauth2 用户信息
 * @author weicong
 * @date 2019年9月4日
 * @version 1.0
 */
public class CornUser extends User {

	private static final long serialVersionUID = -7041106311015979624L;
	
	private List<String> urlList;

	public CornUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	public CornUser(List<String> urlList, String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.urlList = urlList;
	}

	public List<String> getUrlList() {
		return urlList;
	}

	public void setUrlList(List<String> urlList) {
		this.urlList = urlList;
	}
	
}
