package com.jrsoft.fri.xtsz.entity;

/**
 * City entity. @author MyEclipse Persistence Tools
 */

public class City implements java.io.Serializable {

	// Fields

	private Long id;
	private String cityid;				//市 编号
	private String city;				//市 名称
	private String father;			//省 编号

	// Constructors

	/** default constructor */
	public City() {
	}

	/** full constructor */
	public City(String cityid, String city, String father) {
		this.cityid = cityid;
		this.city = city;
		this.father = father;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCityid() {
		return this.cityid;
	}

	public void setCityid(String cityid) {
		this.cityid = cityid;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getFather() {
		return this.father;
	}

	public void setFather(String father) {
		this.father = father;
	}

}