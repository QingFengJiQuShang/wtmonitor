package com.jrsoft.fri.common.utils;

import java.util.List;

import javax.servlet.ServletRequest;


import com.jrsoft.fri.common.core.bean.JqGridSearchTo;

public class Page {
	private int pageNum = 1;
	private int pageSize = 10;
	
	private String partHql; // �Զ��� ����
	
	private List<String> deptIds; // �������ŵĺ��¼�����ids
	private String rank; // 1:�ܲ� 2��������λ
	private int sec = 0; // 0���Լ��ɼ�   1:ȫ���ɼ�   2�� ����Ȩ
	private int pageCount;

	private List<? extends Object> dataList;
	private int dataCount;

	private int start = 0; // sql����е���ʼλ�� ������ֵ
	private int end = 10; // sql����еĽ�βλ�� С�ڵ��ڴ�ֵ

	// jqgrid��ѯ
	private Boolean search; //�Ƿ�����
	private String sord; //����ʽ asc desc
	private String sidx; //�����ֶ�
	private String filters; //���ֶβ�ѯ
	private String subId; //��ID
	private JqGridSearchTo to;
	//����������left join
	private String jointable;
	private String joinHQL;

	public Page(int pageNum, int pageSize) {
		if (pageNum > 0) {
			this.pageNum = pageNum;
		}
		this.pageSize = pageSize;
		init();
	}

	public Page(ServletRequest request, String paramName_pageNum,
			String paramName_pageSize) {
		String pageNumStr = request.getParameter(paramName_pageNum);
		String pageSizeStr = request.getParameter(paramName_pageSize);
		if (pageNumStr != null && !"".equals(pageNumStr)) {
			this.pageNum = Integer.parseInt(pageNumStr);
		}
		if (pageSizeStr != null && !"".equals(pageSizeStr)) {
			this.pageSize = Integer.parseInt(pageSizeStr);
		}
		init();
	}

	private void init() {
		this.start = (pageNum - 1) * pageSize;
		this.end = pageNum * pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
		init();
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		init();
	}

	public int getPageCount() {
		return pageCount;
	}

	public List<? extends Object> getDataList() {
		return dataList;
	}

	public void setDataList(List<? extends Object> dataList) {
		this.dataList = dataList;
	}

	public int getDataCount() {
		return dataCount;
	}

	public void setDataCount(int dataCount) {
		this.dataCount = dataCount;
		this.pageCount = (dataCount + pageSize - 1) / pageSize;

		if (this.pageCount == 0) {
			this.pageCount = 1;
		}
	}

	/**
	 * ��¼��ʼλ��(��һҳΪ0) sql��rownumӦ���ڴ�ֵ
	 * 
	 * @return
	 */
	public int getStart() {
		return start;
	}

	/**
	 * ��¼����λ�� sql��rownumӦС�ڵ��ڴ�ֵ
	 * 
	 * @return
	 */
	public int getEnd() {
		return end;
	}

	public Boolean getSearch() {
		return search;
	}

	public void setSearch(Boolean search) {
		this.search = search;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getFilters() {
		return filters;
	}

	public void setFilters(String filters) {
		this.filters = filters;
	}

	public String getSubId() {
		return subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}

	public JqGridSearchTo getTo() {
		return to;
	}

	public void setTo(JqGridSearchTo to) {
		this.to = to;
	}

	public String getPartHql() {
		return partHql;
	}

	public void setPartHql(String partHql) {
		this.partHql = partHql;
	}


	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public int getSec() {
		return sec;
	}

	public void setSec(int sec) {
		this.sec = sec;
	}

	public List<String> getDeptIds() {
		return deptIds;
	}

	public void setDeptIds(List<String> deptIds) {
		this.deptIds = deptIds;
	}

	public String getJoinHQL() {
		return joinHQL;
	}

	public void setJoinHQL(String joinHQL) {
		this.joinHQL = joinHQL;
	}

	public String getJointable() {
		return jointable;
	}

	public void setJointable(String jointable) {
		this.jointable = jointable;
	}

}
