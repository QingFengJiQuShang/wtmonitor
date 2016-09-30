package com.jrsoft.fri.common.core.bean;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class JqGridSearchTo implements Serializable {

	private String groupOp; // 多字段查询时分组类型，主要是AND或者OR
	private List<JqGridSearchDetailTo> rules; // 多字段查询时候，查询条件的集合
	private String sidx; // 排序字段
	private String sord; // 排序类型 ASC或者DESC
	private Boolean search = false; // 是否是查询 true 或者 false

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
