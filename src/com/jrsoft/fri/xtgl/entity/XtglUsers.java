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

	// Constructors

	/** default constructor */
	public XtglUsers() {
	}

	/** full constructor */
	public XtglUsers(String name, String loginname, String password,
			String phone, String unit) {
		this.name = name;
		this.loginname = loginname;
		this.password = password;
		this.phone = phone;
		this.unit = unit;
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

}