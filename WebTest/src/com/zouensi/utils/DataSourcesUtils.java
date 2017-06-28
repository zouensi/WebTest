package com.zouensi.utils;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;
/**
 * 连接池工具类
 * @author DELL
 *
 */
public class DataSourcesUtils {
	private static ComboPooledDataSource ds = new ComboPooledDataSource();
	//创建线程私有包装类
	private static final ThreadLocal< Connection> tl = new ThreadLocal<>();
	public static ComboPooledDataSource getDs() {
		return ds;
	}
	/**
	 * 获取连接
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
	 * 关闭连接池
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
