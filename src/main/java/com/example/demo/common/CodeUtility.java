package com.example.demo.common;

public class CodeUtility {

	public static boolean isNotEmpty(Object obj) {
		return obj != null;
	}

	public static boolean isNotEmpty(String str) {
		return str != null && str.length() > 0;
	}

	public static boolean isEmpty(Object obj) {
		return obj == null;
	}

}