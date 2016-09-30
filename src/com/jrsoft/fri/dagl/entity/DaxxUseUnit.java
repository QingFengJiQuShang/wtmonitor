package com.jrsoft.fri.dagl.entity;

import java.math.BigDecimal;

/**
 *使用单位表
 */

public class DaxxUseUnit implements java.io.Serializable {

	// Fields

	private int id;
	private String name;			//单位名称
	private String type;				//单位类型
	private String reception;				//前台电话
	private String liaisons;				//联络人
	private String phone;				//联络电话
	private String address;				//地址
	private int regionId;				//区域id

	// Constructors

	/** default constructor */
	public DaxxUseUnit() {
	}

	/** full constructor */
	public DaxxUseUnit(String name, String type, String reception,
			String liaisons, String phone, String address, int regionId) {
		this.name = name;
		this.type = type;
		this.reception = reception;
		this.liaisons = liaisons;
		this.phone = phone;
		this.address = address;
		this.regionId = regionId;
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

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getReception() {
		return this.reception;
	}

	public void setReception(String reception) {
		this.reception = reception;
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

	public int getRegionId() {
		return this.regionId;
	}

	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}

}