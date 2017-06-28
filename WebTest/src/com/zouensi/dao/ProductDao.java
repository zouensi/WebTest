package com.zouensi.dao;

import static com.zouensi.utils.DataSourcesUtils.getDs;
import static com.zouensi.utils.Utils.isEmpty;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.zouensi.domain.Product;

public class ProductDao {
	private QueryRunner qr = new QueryRunner(getDs());
	/**
	 * ��ѯ������Ϣ
	 * @return
	 * @throws SQLException
	 */
	public List<Product> findAll() throws SQLException {
		String sql = "select * from product";
		List<Product> listProduct = qr.query(sql, new BeanListHandler<>(Product.class));
		return listProduct;
	}
	
	/**
	 * ��ѯһ����Ϣ
	 * @param pid
	 * @return
	 * @throws SQLException
	 */
	public Product find(String pid) throws SQLException {
		String sql = "select * from product where pid = ?";
		Object[] objs = new Object[]{pid};
		Product product = qr.query(sql, new BeanHandler<>(Product.class),objs);
		return product;
	}
	
	/**
	 * ɾ��һ����Ϣ
	 * @param pid
	 * @return
	 * @throws SQLException
	 */
	public int delete(String pid) throws SQLException {
		String sql = "delete from product where pid = ?";
		int state = qr.update(sql,pid);
		return state;
	}
	
	/**
	 * ��������
	 * @param product
	 * @return
	 * @throws SQLException
	 */
	public int update (Product product) throws SQLException {
		String sql = "update product set pname=?,market_price=?,shop_price=?,pimage=?,pdate=?,pdesc=? where pid= ?";
		Object[] objs = new Object[]{
				product.getPname(),
				product.getMarket_price(),
				product.getShop_price(),
				product.getPimage(),
				product.getPdate(),
				product.getPdesc(),
				product.getPid()
		};
		int state = qr.update(sql, objs);
		return state;
	}
	
	/**
	 * ����һ����Ϣ
	 * @param product
	 * @return
	 * @throws SQLException
	 */
	public int insert(Product product) throws SQLException {
		String sql = "insert into product values(?,?,?,?,?,?,?)";
		Object[] objs = new Object[]{
				product.getPid(),
				product.getPname(),
				product.getMarket_price(),
				product.getShop_price(),
				product.getPimage(),
				product.getPdate(),
				product.getPdesc()
		};
		int state = qr.update(sql, objs);
		return state;
		
	}

	/**
	 * ��������ɾ����Ϣ
	 * @param pid
	 * @param conn
	 * @throws SQLException 
	 */
	public int delete(String pid, Connection conn) throws SQLException {
		QueryRunner qr = new QueryRunner();
		String sql = "delete from product where pid=?";
		int state = qr.update(conn, sql, pid);
		return state;
	}

	/**
	 * ����������ѯ
	 * @param findByShop_price
	 * @param findByPdesc
	 * @throws SQLException 
	 */
	public List<Product> findByCondition(String findByShop_price, String findByPdesc) throws SQLException {
		//�ж��Ƿ��Ѿ���where������,Ĭ��false
		boolean hasWhere = false;
		StringBuilder sql = new StringBuilder( "select * from product ");
		if(!isEmpty(findByShop_price)) {//�пղ���
			hasWhere = appendWhereIfNeed(sql,hasWhere);
			sql.append(" shop_price like '%"+findByShop_price+"%'");
		}
		if(!isEmpty(findByPdesc)) {
			hasWhere = appendWhereIfNeed(sql,hasWhere);
			sql.append(" pdesc like '%"+findByPdesc+"%'");
		}
		List<Product> listProduct = qr.query(sql.toString(), new BeanListHandler<Product>(Product.class));
		return listProduct;
		
	}
	/**
	 * �ж�sql������ý���ʲô����
	 * @param sql
	 * @param hasWhere
	 * @return
	 */
	private boolean appendWhereIfNeed(StringBuilder sql, boolean hasWhere) {
		if (hasWhere == false) {
			sql.append("WHERE");
		} else {
			sql.append("AND");
		}
		return true;
	}
}
