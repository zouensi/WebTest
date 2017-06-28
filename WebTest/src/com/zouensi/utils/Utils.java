package com.zouensi.utils;

public class Utils {
	private Utils() {
		
	}
	
	/**
	 * ÅÐ¶Ï×Ö·û´®ÊÇ·ñÎª¿Õ
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if(str==null||"".equals(str.trim())) {
			return true;
		}
		return false;
	}
}
