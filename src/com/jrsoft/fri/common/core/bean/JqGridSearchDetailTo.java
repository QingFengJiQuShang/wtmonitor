package com.jrsoft.fri.common.core.bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class JqGridSearchDetailTo implements Serializable {

	private String field; //��ѯ�ֶ�   
	private String op; //��ѯ����   
	private String data; //ѡ��Ĳ�ѯֵ

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
