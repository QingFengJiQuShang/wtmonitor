package com.jrsoft.fri.xtsz.entity;

/**
 * Area entity. @author MyEclipse Persistence Tools
 */

public class Area implements java.io.Serializable {

	// Fields

	private Long id;
	private String areaid;		//区 编号
	private String area;			//区 名称
	private String father;		//市 编号

	// Constructors

	/** default constructor */
	public Area() {
	}

	/** full constructor */
	public Area(String areaid, String area, String father) {
		this.areaid = areaid;
		this.area = area;
		this.father = father;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAreaid() {
		return this.areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getFather() {
		return this.father;
	}

	public void setFather(String father) {
		this.father = father;
	}

}