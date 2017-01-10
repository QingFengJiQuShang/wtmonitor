package com.jrsoft.fri.xtgl.entity;

import java.math.BigDecimal;

/**
 * ά����λ
 */

public class XtglMaintenanceUnit implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;				//��λ����
	private String liaisons;				//������
	private String phone;				//�����˵绰
	private String address;				//�칫�ص�
	private String code;				//��˾����
	private String corporation;				//����
	private String province;			//ʡ
	private String city;						//��
	private String area;					//��
	private int num;   //ά������
	
	// Constructors

	/** default constructor */
	public XtglMaintenanceUnit() {
	}

	/** full constructor */
	public XtglMaintenanceUnit(String name, String liaisons, String phone,
			String address, String code, String corporation, int num,String province,String city,String area) {
		this.name = name;
		this.liaisons = liaisons;
		this.phone = phone;
		this.address = address;
		this.code = code;
		this.corporation = corporation;
		this.num=num;
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

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
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