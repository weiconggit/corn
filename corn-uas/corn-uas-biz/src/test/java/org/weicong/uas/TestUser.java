package org.weicong.uas;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.weicong.uas.service.UserService;

/**
 * @description 
 * @author weicong
 * @date 2019年6月10日 下午2:13:00
 * @version 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestUser {

	@Autowired
	private UserService userSerivce;
	
	@Test
	public void testService() {
//		String string = userSerivce.save();
//		System.out.println("string is : " + string);
	}
}
