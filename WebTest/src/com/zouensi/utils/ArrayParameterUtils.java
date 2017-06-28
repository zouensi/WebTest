package com.zouensi.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
/**
 * 思想不错，单是貌似不适用
 * @author DELL
 *
 */
public class ArrayParameterUtils {
	private ArrayParameterUtils() {
		
	}
	/**
	 * 获取要传入到数据库的参数
	 * @param clazz
	 * @return
	 */
	
	public static<T> Object[] getParameters(Class<T> clazz) {
		Field[] fields = clazz.getFields();
		List<Object> list = new ArrayList();
		for (Field field : fields) {
			list.add(field);
		}
		return list.toArray();
	}
}
