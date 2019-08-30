package org.weicong.auth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description 
 * @author weicong
 * @date 2019年8月20日
 * @version 1.0
 */
@RestController
public class TestEndpoints {
	
	@GetMapping("/alive")
    public String getProduct() {
        //for debug
        return "spring security test is alive";
    }

	@GetMapping("/product/{id}")
    public String getProduct(@PathVariable String id) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();        
		System.err.println("==prod==" + userDetails.getAuthorities());
        //for debug
        @SuppressWarnings("unused")
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "product id : " + id;
    }

    @GetMapping("/order/{id}")
    public String getOrder(@PathVariable String id) {
        //for debug
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "order id : " + id;
    }

}
