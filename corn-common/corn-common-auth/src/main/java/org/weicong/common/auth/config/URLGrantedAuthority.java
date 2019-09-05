package org.weicong.common.auth.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

/**
 * @description 
 * @author weicong
 * @date 2019年8月30日
 * @version 1.0
 */
@Deprecated
public class URLGrantedAuthority implements GrantedAuthority {

	private static final long serialVersionUID = -7187790544464696954L;
	
	private final String url;

	public URLGrantedAuthority(String url) {
		Assert.hasText(url, "A granted authority textual representation is required");
		this.url = url;
	}

	@Override
	public String getAuthority() {
		return url;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj instanceof URLGrantedAuthority) {
			return url.equals(((URLGrantedAuthority) obj).url);
		}

		return false;
	}

	@Override
	public int hashCode() {
		return this.url.hashCode();
	}

	@Override
	public String toString() {
		return this.url;
	}

}
