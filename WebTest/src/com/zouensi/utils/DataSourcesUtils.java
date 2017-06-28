package com.zouensi.utils;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;
/**
 * ���ӳع�����
 * @author DELL
 *
 */
public class DataSourcesUtils {
	private static ComboPooledDataSource ds = new ComboPooledDataSource();
	//�����߳�˽�а�װ��
	private static final ThreadLocal< Connection> tl = new ThreadLocal<>();
	public static ComboPooledDataSource getDs() {
		return ds;
	}
	/**
	 * ��ȡ����
	 * @return
	 */
	public static Connection getConnection() {
		Connection connection = tl.get();
		if(connection==null) {
			try {
				connection = ds.getConnection();
				tl.set(connection);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return connection;
	}
	
	/**
	 * �ر����ӳ�
	 */
	public static void closeConnection() {
		Connection connection = tl.get();
		if(connection!=null) {
			try {
				connection.close();
				tl.remove();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
