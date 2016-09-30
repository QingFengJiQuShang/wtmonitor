package com.jrsoft.fri.dagl.entity;

import java.math.BigDecimal;

/**
 * ά����Ա��
 */

public class DaxxMaintenanceUsers implements java.io.Serializable {

	// Fields

	private int id;
	private String name;		//ά����
	private String numbers;		//ά��֤���
	private String validity;		//ά��֤��Ч��
	private String phone;		//�绰

	// Constructors

	/** default constructor */
	public DaxxMaintenanceUsers() {
	}

	/** full constructor */
	public DaxxMaintenanceUsers(String name, String numbers, String validity,
			String phone) {
		this.name = name;
		this.numbers = numbers;
		this.validity = validity;
		this.phone = phone;
	}

	// Property accessors

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
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

}