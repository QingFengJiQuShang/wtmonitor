package com.jrsoft.fri.xtgl.entity;

import java.math.BigDecimal;

/**
 * ϵͳ�û�
 */

public class XtglUsers implements java.io.Serializable {

	// Fields

	private Long id;								//id
	private String name;				//����
	private String loginname;		//��¼��
	private String password;		//����
	private String phone;				//�绰
	private String unit;					//��λ
	private String province;					//���� ʡ
	private String city;					// ���� ��
	private String area;					//����  ��
	// Constructors

	/** default constructor */
	public XtglUsers() {
	}

	/** full constructor */
	public XtglUsers(String name, String loginname, String password,
			String phone, String unit,String province,String city,String area) {
		this.name = name;
		this.loginname = loginname;
		this.password = password;
		this.phone = phone;
		this.province=province;
		this.unit = unit;
		this.city=city;
		this.area=area;
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

	public String getLoginname() {
		return this.loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

}