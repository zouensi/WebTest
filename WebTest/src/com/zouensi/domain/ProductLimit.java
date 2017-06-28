package com.zouensi.domain;

import java.io.Serializable;
import java.util.List;

/**
 * ��ҳ��Ϣ��װ����
 * @author DELL
 *
 */
public class ProductLimit implements Serializable{
	private static final long serialVersionUID = 1L;
	private long totalCount;//�ܼ�¼��
	private int pageNumber;//��ǰҳ��
	private int totalPage;//��ҳ��
	private int pageSize;//ÿһҳ��ʾ�ļ�¼��
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
