package com.jrsoft.fri.common.core.bean;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class JqGridSearchTo implements Serializable {

	private String groupOp; // ���ֶβ�ѯʱ�������ͣ���Ҫ��AND����OR
	private List<JqGridSearchDetailTo> rules; // ���ֶβ�ѯʱ�򣬲�ѯ�����ļ���
	private String sidx; // �����ֶ�
	private String sord; // �������� ASC����DESC
	private Boolean search = false; // �Ƿ��ǲ�ѯ true ���� false

	public String getGroupOp() {
		return groupOp;
	}

	public void setGroupOp(String groupOp) {
		this.groupOp = groupOp;
	}

	public List<JqGridSearchDetailTo> getRules() {
		return rules;
	}

	public void setRules(List<JqGridSearchDetailTo> rules) {
		this.rules = rules;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

	public Boolean getSearch() {
		return search;
	}

	public void setSearch(Boolean search) {
		this.search = search;
	}
 }
