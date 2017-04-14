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
	private String unit;					//����λ����
	private String province;					//���� ʡ
	private String city;					// ���� ��
	private String area;					//����  ��
	private String type;					//�û�����   1��������Ա  2�����û�  3�ն��û�
	private String regionUnitId;	//����λ
	private String unitType;			//��λ����
	private String unitId;				//��λid
	private String unitName;		//��λ����
	// Constructors
	private int num;
	/** default constructor */
	public XtglUsers() {
	}

	/** full constructor */
	public XtglUsers(String name, String loginname, String password,
			String phone, String unit,String province,String city,String area,
			String type,String regionUnitId,String unitType,String unitId,String unitName) {
		this.name = name;
		this.loginname = loginname;
		this.password = password;
		this.phone = phone;
		this.province=province;
		this.unit = unit;
		this.city=city;
		this.area=area;
		this.type=type;
		this.regionUnitId=regionUnitId;
		this.unitType=unitType;
		this.unitId=unitId;
		this.unitName=unitName;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRegionUnitId() {
		return regionUnitId;
	}

	public void setRegionUnitId(String regionUnitId) {
		this.regionUnitId = regionUnitId;
	}

	public String getUnitType() {
		return unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

}