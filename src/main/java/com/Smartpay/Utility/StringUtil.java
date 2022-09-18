package com.Smartpay.Utility;

public class StringUtil {

	public static String getLastSixDigitOfMobileNo(String input) {
		String result = null;
		if (input.length() > 6) {
			result = input.substring(input.length() - 6);
		} else {
			result = input;
		}
		return result;
	}

	public static String generateDefaultPassword(String input) {
		String result = null;
		if (null != input && !input.isEmpty()) {
			String[] arr = input.split(" ");
			String firstname = arr[0];
			result = firstname + "12345";
		}
		return result;
	}

	public static String getLast4digit(String input) {
		String result = null;
		int i = input.length();
		if (i > 4) {
			char charArr[] = new char[4];
			for (int j = charArr.length - 1; j >= 0; j--) {
				charArr[j] = input.charAt(i - 1);
				i--;
			}
			result = String.valueOf(charArr);
		} else {
			result = input;
		}
		return result;
	}
}
