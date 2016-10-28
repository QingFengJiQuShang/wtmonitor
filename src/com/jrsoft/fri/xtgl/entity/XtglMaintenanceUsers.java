package com.jrsoft.fri.xtgl.entity;

/**
 * XtglMaintenanceUsers entity. @author MyEclipse Persistence Tools
 */

public class XtglMaintenanceUsers implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;				//ά����
	private String numbers;				//ά��֤���
	private String validity;				//ά��֤��Ч��
	private String phone;				//�绰
	private XtglMaintenanceUnit unitId=new XtglMaintenanceUnit();				//ά����λid
	private String cardNumber;				//����

	// Constructors

	/** default constructor */
	public XtglMaintenanceUsers() {
	}

	/** full constructor */
	public XtglMaintenanceUsers(String name, String numbers, String validity,
			String phone, XtglMaintenanceUnit unitId, String cardNumber) {
		this.name = name;
		this.numbers = numbers;
		this.validity = validity;
		this.phone = phone;
		this.unitId = unitId;
		this.cardNumber = cardNumber;
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

	public String getNumbers() {
		return this.numbers;
	}

	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}

	public String getValidity() {
		return this.validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public XtglMaintenanceUnit getUnitId() {
		return this.unitId;
	}

	public void setUnitId(XtglMaintenanceUnit unitId) {
		this.unitId = unitId;
	}

	public String getCardNumber() {
		return this.cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

}