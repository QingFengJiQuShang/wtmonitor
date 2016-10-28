package com.jrsoft.fri.xtgl.entity;

import java.math.BigDecimal;

/**
 * XtglRescueUnit entity. @author MyEclipse Persistence Tools
 */

public class XtglRescueUnit implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;				//��λ����
	private String type;				//��λ����
	private String liaisons;				//������
	private String phone;				//����绰
	private String address;				//��ַ

	// Constructors

	/** default constructor */
	public XtglRescueUnit() {
	}

	/** full constructor */
	public XtglRescueUnit(String name, String type, String liaisons,
			String phone, String address) {
		this.name = name;
		this.type = type;
		this.liaisons = liaisons;
		this.phone = phone;
		this.address = address;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLiaisons() {
		return this.liaisons;
	}

	public void setLiaisons(String liaisons) {
		this.liaisons = liaisons;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}