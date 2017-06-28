package com.zouensi.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 分页信息封装对象
 * @author DELL
 *
 */
public class ProductLimit implements Serializable{
	private static final long serialVersionUID = 1L;
	private long totalCount;//总记录数
	private int pageNumber;//当前页数
	private int totalPage;//总页数
	private int pageSize;//每一页显示的记录数
	private List<Product> list;
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<Product> getList() {
		return list;
	}
	public void setList(List<Product> list) {
		this.list = list;
	}
	public ProductLimit() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductLimit(long totalCount, int pageNumber, int totalPage,
			int pageSize, List<Product> list) {
		super();
		this.totalCount = totalCount;
		this.pageNumber = pageNumber;
		this.totalPage = totalPage;
		this.pageSize = pageSize;
		this.list = list;
	}
	
}
