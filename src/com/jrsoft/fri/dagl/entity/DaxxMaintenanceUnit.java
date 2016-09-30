package com.jrsoft.fri.dagl.entity;


/**
 * ά����λ��
 */

public class DaxxMaintenanceUnit implements java.io.Serializable {

	// Fields

	private int id;
	private String name;			//��λ����
	private String liaisons;			//������
	private String phone;			//�����˵绰
	private String address;			//�칫�ص�
	private String code;			//��˾����
	private String corporation;			//����

	// Constructors

	/** default constructor */
	public DaxxMaintenanceUnit() {
	}

	/** full constructor */
	public DaxxMaintenanceUnit(String name, String liaisons, String phone,
			String address, String code, String corporation) {
		this.name = name;
		this.liaisons = liaisons;
		this.phone = phone;
		this.address = address;
		this.code = code;
		this.corporation = corporation;
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

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCorporation() {
		return this.corporation;
	}

	public void setCorporation(String corporation) {
		this.corporation = corporation;
	}

}