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
	 * ��ѯ������Ϣ
	 * @return
	 * @throws SQLException
	 */
	public List<Product> findAll() throws SQLException {
		List<Product> listProduct = dao.findAll();
		return listProduct;
	}
	
	/**
	 * ��ѯһ����Ϣ
	 * @param pid
	 * @return
	 * @throws SQLException
	 */
	public Product find(String pid) throws SQLException {
		Product product = dao.find(pid);
		return product;
	}
	
	/**
	 * ɾ��һ����Ϣ
	 * @param pid
	 * @return
	 * @throws SQLException
	 */
	public boolean delete(String pid) throws SQLException {
		int state = dao.delete(pid);
		return state>0?true:false;
	}
	
	/**
	 * ��������
	 * @param product
	 * @return
	 * @throws SQLException
	 */
	public boolean update (Product product) throws SQLException {
		int state = dao.update(product);
		return state>0?true:false;
	}
	
	/**
	 * ����һ����Ϣ
	 * @param product
	 * @return
	 * @throws SQLException
	 */
	public boolean insert(Product product) throws SQLException {
		int state = dao.insert(product);
		return state>0?true:false;
		
	}
	/**
	 * ɾ��������Ϣ��Ҫ��������
	 * @param pids
	 * @throws Exception 
	 * @throws SQLException 
	 */
	public boolean deleteAll(String[] pids) throws Exception  {
		Connection conn = DataSourcesUtils.getConnection();
		//��������
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
			//���쳣�׸�servlet
			throw new Exception(e);
		}
		
	}

	/**
	 *����������ѯ
	 * @param findByShop_price
	 * @param findByPdesc
	 * @throws SQLException 
	 */
	public List<Product> findByCondition(String findByShop_price, String findByPdesc) throws SQLException {
		List<Product> listProduct = dao.findByCondition( findByShop_price,  findByPdesc);
		return listProduct;
	}
}
