package com.kitri.cafe.util;

public class NumberCheck {

	// tmp가 숫자가 아니면 0을 리턴하는 메소드
	public static int NotNumberToZero(String tmp) {
		if(isNumber(tmp)) {
			return Integer.parseInt(tmp);
		} else {
			return 0;
		}
	}
	
	// tmp가 숫자가 아니면 0을 리턴하는 메소드
	public static int NotNumberToOne(String tmp) {
		if(isNumber(tmp)) {
			return Integer.parseInt(tmp);
		} else {
			return 1;
		}
	}

	private static boolean isNumber(String tmp) {
		
		boolean flag = true;
		
		if(tmp != null) {
			int len = tmp.length();
			for(int i = 0; i < len; i++) {
				int num = tmp.charAt(i) - 48;
				if(num < 0 || num > 9) {
					flag = false;
					break;
				}
			}
		} else {
			flag = false;
		}
		
		return flag;
	}
	
}
