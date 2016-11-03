package com.jrsoft.fri.xtgl.entity;

import java.math.BigDecimal;

/**
 * 维保单位
 */

public class XtglMaintenanceUnit implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;				//单位名称
	private String liaisons;				//负责人
	private String phone;				//负责人电话
	private String address;				//办公地点
	private String code;				//公司代码
	private String corporation;				//法人
	private int num;   //维保人数
	// Constructors

	/** default constructor */
	public XtglMaintenanceUnit() {
	}

	/** full constructor */
	public XtglMaintenanceUnit(String name, String liaisons, String phone,
			String address, String code, String corporation, int num) {
		this.name = name;
		this.liaisons = liaisons;
		this.phone = phone;
		this.address = address;
		this.code = code;
		this.corporation = corporation;
		this.num=num;
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

}