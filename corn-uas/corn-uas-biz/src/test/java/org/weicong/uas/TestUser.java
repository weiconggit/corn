package org.weicong.uas;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.weicong.uas.auth.integration.AuthenticationEnum;

/**
 * @description
 * @author weicong
 * @date 2019年6月10日 下午2:13:00
 * @version 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestUser {

	public static void main(String args[]) {
		 
//		String fdString = "sms";
//		System.out.println(AuthenticationEnum.SMS.getAuthType());
//		try {
//			Enumeration<NetworkInterface> ie = NetworkInterface.getNetworkInterfaces();
//			while (ie.hasMoreElements()) {
//				NetworkInterface i = ie.nextElement();
//				System.out.println(i.getDisplayName() + " [" + i.getName() + "]: "
//						+ formatAddress(i.getHardwareAddress()) + "; isVirtual=" + i.isVirtual());
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	private static String formatAddress(byte[] address) {
		if (address == null) {
			return null;
		}

		StringBuilder ret = new StringBuilder(address.length * 2);
		for (byte b : address) {
			if (ret.length() > 0) {
				ret.append('-');
			}

			String bs = Integer.toHexString(b & 0x000000FF).toUpperCase();
			if (bs.length() < 2) {
				ret.append('0');
			}
			ret.append(bs);
		}

		return ret.toString();
	}

//	@Autowired
//	private UserService userSerivce;

//	@Test
//	public void testService() {
//		String string = userSerivce.save();
//		System.out.println("string is : " + string);
//	}
}
