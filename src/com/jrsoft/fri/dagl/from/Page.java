package com.jrsoft.fri.dagl.from;

public class Page {

	//总记录数
	private int count;
	//当前页数
	private int pageNum;
	//每页显示数
	private int pageSize;
	//总页数
	private int countSize;
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCountSize() {
		return countSize;
	}
	public void setCountSize(int countSize) {
		this.countSize = countSize;
	}
	
	

}
