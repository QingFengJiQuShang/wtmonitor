package com.jrsoft.fri.xtgl.entity;

import java.math.BigDecimal;

/**
 * 系统用户
 */

public class XtglUsers implements java.io.Serializable {

	// Fields

	private Long id;								//id
	private String name;				//姓名
	private String loginname;		//登录名
	private String password;		//密码
	private String phone;				//电话
	private String unit;					//单位
	private String province;					//区域 省
	private String city;					// 区域 市
	private String area;					//区域  区
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