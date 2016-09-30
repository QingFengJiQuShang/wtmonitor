package com.jrsoft.fri.dagl.entity;

import java.math.BigDecimal;

/**
 * 区域表
 */

public class DaxxRegion implements java.io.Serializable {

	// Fields

	private int id;
	private String region;			//行政区域
	private int clientId;   //客户id
 
	// Constructors

	/** default constructor */
	public DaxxRegion() {
	}

	/** full constructor */
	public DaxxRegion(String region, int clientId) {
		this.region = region;
		this.clientId = clientId;
	}

	// Property accessors

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public int getClientId() {
		return this.clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

}