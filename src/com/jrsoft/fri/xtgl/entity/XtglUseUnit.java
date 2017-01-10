package com.jrsoft.fri.xtgl.entity;

import java.math.BigDecimal;

/**
 * ʹ�õ�λ
 */

public class XtglUseUnit implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;				//��λ����
	private String type;				//��λ����
	private String liaisons;				//������
	private String phone;				//����绰
	private String address;				//��ַ
	private String province;			//ʡ
	private String city;						//��
	private String area;					//��

	// Constructors

	/** default constructor */
	public XtglUseUnit() {
	}

	/** full constructor */
	public XtglUseUnit(String name, String type, String liaisons, String phone,
			String address,String province,String city,String area) {
		this.name = name;
		this.type = type;
		this.liaisons = liaisons;
		this.phone = phone;
		this.address = address;
		this.province=province;
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