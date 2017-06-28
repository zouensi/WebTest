package com.zouensi.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;









import org.apache.commons.dbutils.DbUtils;

import com.zouensi.dao.ProductDao;
import com.zouensi.domain.Product;
import com.zouensi.utils.DataSourcesUtils;

public class ProductService {
	private ProductDao dao = new ProductDao();
	/**
	 * 查询所有信息
	 * @return
	 * @throws SQLException
	 */
	public List<Product> findAll() throws SQLException {
		List<Product> listProduct = dao.findAll();
		return listProduct;
	}
	
	/**
	 * 查询一条信息
	 * @param pid
	 * @return
	 * @throws SQLException
	 */
	public Product find(String pid) throws SQLException {
		Product product = dao.find(pid);
		return product;
	}
	
	/**
	 * 删除一条信息
	 * @param pid
	 * @return
	 * @throws SQLException
	 */
	public boolean delete(String pid) throws SQLException {
		int state = dao.delete(pid);
		return state>0?true:false;
	}
	
	/**
	 * 更新数据
	 * @param product
	 * @return
	 * @throws SQLException
	 */
	public boolean update (Product product) throws SQLException {
		int state = dao.update(product);
		return state>0?true:false;
	}
	
	/**
	 * 插入一条信息
	 * @param product
	 * @return
	 * @throws SQLException
	 */
	public boolean insert(Product product) throws SQLException {
		int state = dao.insert(product);
		return state>0?true:false;
		
	}
	/**
	 * 删除所有信息需要开启事务
	 * @param pids
	 * @throws Exception 
	 * @throws SQLException 
	 */
	public boolean deleteAll(String[] pids) throws Exception  {
		Connection conn = DataSourcesUtils.getConnection();
		//开启事务
		try {
			conn.setAutoCommit(false);
			for (String pid : pids) {
				dao.delete(pid,conn);
			}
			/*conn.commit();*/
			DbUtils.commitAndClose(conn);
			return true;
		} catch (SQLException e) {
			try {
				DbUtils.rollbackAndClose(conn);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			//将异常抛给servlet
			throw new Exception(e);
		}
		
	}

	/**
	 *按照条件查询
	 * @param findByShop_price
	 * @param findByPdesc
	 * @throws SQLException 
	 */
	public List<Product> findByCondition(String findByShop_price, String findByPdesc) throws SQLException {
		List<Product> listProduct = dao.findByCondition( findByShop_price,  findByPdesc);
		return listProduct;
	}
}
