package com.jrsoft.fri.xtsz.entity;

/**
 * Province entity. @author MyEclipse Persistence Tools
 */

public class Province implements java.io.Serializable {

	// Fields

	private Long id;
	private String provinceid;			//±àºÅ
	private String province;				//Ê¡Ãû³Æ

	// Constructors

	/** default constructor */
	public Province() {
	}

	/** full constructor */
	public Province(String provinceid, String province) {
		this.provinceid = provinceid;
		this.province = province;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProvinceid() {
		return this.provinceid;
	}

	public void setProvinceid(String provinceid) {
		this.provinceid = provinceid;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

}