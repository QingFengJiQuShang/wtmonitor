package com.jrsoft.fri.dagl.entity;

import java.math.BigDecimal;

/**
 * �����
 */

public class DaxxRegion implements java.io.Serializable {

	// Fields

	private Long id;
	private String region;			//��������
	private Long clientId;   //�ͻ�id
 
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