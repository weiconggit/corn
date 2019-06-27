package org.weicong.common.base.util;

import java.util.Calendar;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description
 * @author weicong
 * @date 2019年6月15日
 * @version 1.0
 */
public abstract class StringUtil {

	/**
	 * 港澳台通信证校验， Hong Kong, Macao, Taiwan
	 * 
	 * @param HMTcard
	 * @return
	 */
	public static boolean isHMTcard(String HMTcard) {
		String pattern = "^[a-zA-Z0-9]{5,21}$";
		return Pattern.matches(pattern, HMTcard);
	}
	
	/**
	 * 护照校验
	 * 
	 * @param passport
	 * @return
	 */
	public static boolean isPassport(String passport) {
		String pattern = "/^[a-zA-Z0-9]{3,21}//(P\\d7)|(G\\d8)//(P\\d7)|(G\\d8)/";
		return Pattern.matches(pattern, passport);
	}

	/**
	 * 校验身份证号是否正确
	 * 
	 * @param IDcard
	 * @return
	 */
	public static boolean isIDcard(String IDcard) {
		if (isBlank(IDcard)) {
			return false;
		}
		try {
			int length = IDcard.length();
			if (length == 15) {
				Pattern p = Pattern.compile("^[0-9]*$");
				Matcher m = p.matcher(IDcard);
				return m.matches();
			} else if (length == 18) {
				String front_17 = IDcard.substring(0, IDcard.length() - 1);// 号码前17位
				String verify = IDcard.substring(17, 18);// 校验位(最后一位)
				Pattern p = Pattern.compile("^[0-9]*$");
				Matcher m = p.matcher(front_17);
				if (!m.matches()) {
					return false;
				} else {
					return checkVerify(front_17, verify);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private static boolean checkVerify(String front_17, String verify) {
		try {
			int[] wi = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 };
			String[] vi = { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };
			int s = 0;
			for (int i = 0; i < front_17.length(); i++) {
				int ai = Integer.parseInt(front_17.charAt(i) + "");
				s += wi[i] * ai;
			}
			int y = s % 11;
			String v = vi[y];
			if (!(verify.toUpperCase().equals(v))) {
				return false;
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 根据身份证获取年龄
	 * 
	 * @param IDcard 身份证
	 * @return
	 */
	public static Integer getAgeByIDcard(String IDcard) {
		if (isBlank(IDcard)) {
			return 0;
		}
		if (IDcard.length() == 18) {
			try {
				int selectYear = Integer.parseInt(IDcard.substring(6, 10));
				int selectMonth = Integer.parseInt(IDcard.substring(10, 12));
				int selectDay = Integer.parseInt(IDcard.substring(12, 14));
				// 得到当前时间的年、月、日
				Calendar cal = Calendar.getInstance();
				int yearNow = cal.get(Calendar.YEAR);
				int monthNow = cal.get(Calendar.MONTH) + 1;
				int dayNow = cal.get(Calendar.DATE);
				// 用当前年月日减去生日年月日
				int yearMinus = yearNow - selectYear;
				int monthMinus = monthNow - selectMonth;
				int dayMinus = dayNow - selectDay;
				int age = yearMinus;
				if (yearMinus < 0) {// 选了未来的年份
					age = 0;
				} else if (yearMinus == 0) {// 同年的，要么为1，要么为0
					if (monthMinus < 0) {// 选了未来的月份
						age = 0;
					} else if (monthMinus == 0) {// 同月份的
						if (dayMinus < 0) {// 选了未来的日期
							age = 0;
						} else if (dayMinus >= 0) {
							age = 1;
						}
					} else if (monthMinus > 0) {
						age = 1;
					}
				} else if (yearMinus > 0) {
					if (monthMinus < 0) {// 当前月>生日月
					} else if (monthMinus == 0) {// 同月份的，再根据日期计算年龄
						if (dayMinus < 0) {
						} else if (dayMinus >= 0) {
							age = age + 1;
						}
					} else if (monthMinus > 0) {
						age = age + 1;
					}
				}
				return age;
			} catch (Exception e) {
				e.printStackTrace();
				return 0;
			}
		} else {
			return 0;
		}
	}

	/**
	 * 邮箱校验
	 * 
	 * @param email 邮箱字符串
	 * @return
	 */
	public static boolean isEamil(String email) {
		if (isBlank(email)) {
			return false;
		}
		String pattern = "^[0-9A-Za-z][\\.-_0-9A-Za-z]*@[0-9A-Za-z]+(?:\\.[0-9A-Za-z]+)+$";
		return Pattern.matches(pattern, email);
	}

	/**
	 * 手机号码正则校验
	 * 
	 * @param phone 号码字符串
	 * @return
	 */
	public static boolean isPhoneNumRight(String phone) {
		if (isBlank(phone)) {
			return false;
		}
		String pattern = "|^(13[0-9]|14[579]|15[0-3,5-9]|16[56]|17[0135678]|18[0-9]|19[89])\\d{8}$";
		return Pattern.matches(pattern, phone);
	}

	/**
	 * 生成UUID
	 * 
	 * @return UUID
	 */
	public static String generateUUID() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}

	/**
	 * 判断字符是否为空
	 * 
	 * @param cs 待判断字符串
	 * @return
	 */
	public static boolean isBlank(final CharSequence cs) {
		int strLen;
		if (cs == null || (strLen = cs.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(cs.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}
}
