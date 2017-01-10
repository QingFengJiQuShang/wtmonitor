package com.jrsoft.fri.xtgl.entity;

import java.math.BigDecimal;

/**
 * 使用单位
 */

public class XtglUseUnit implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;				//单位名称
	private String type;				//单位类型
	private String liaisons;				//联络人
	private String phone;				//联络电话
	private String address;				//地址
	private String province;			//省
	private String city;						//市
	private String area;					//区

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