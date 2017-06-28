package com.zouensi.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
/**
 * ˼�벻������ò�Ʋ�����
 * @author DELL
 *
 */
public class ArrayParameterUtils {
	private ArrayParameterUtils() {
		
	}
	/**
	 * ��ȡҪ���뵽���ݿ�Ĳ���
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
