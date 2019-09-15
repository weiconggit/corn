package org.weicong.uas.auth.integration;

import lombok.Data;

/**
 * @description 
 * @author weicong
 * @date 2019年9月13日
 * @version 1.0
 */
@Data
public class IntegrationContext {
	
	private String authType;
	private String username;
	private String password;

}
