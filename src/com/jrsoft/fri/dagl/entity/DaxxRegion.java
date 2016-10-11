package com.jrsoft.fri.dagl.entity;

import java.math.BigDecimal;

/**
 * 区域表
 */

public class DaxxRegion implements java.io.Serializable {

	// Fields

	private Long id;
	private String region;			//行政区域
	private Long clientId;   //客户id
 
	// Constructors

	/** default constructor */
	public DaxxRegion() {
	}

	/** full constructor */
	public DaxxRegion(String region, Long clientId) {
		this.region = region;
		this.clientId = clientId;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Long getClientId() {
		return this.clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

}