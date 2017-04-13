package com.jrsoft.fri.gzcl.entity;

import java.math.BigDecimal;

/**
 * GzclSuccor entity. @author MyEclipse Persistence Tools
 */

public class GzclSuccor implements java.io.Serializable {

	// Fields

	private Long id;
	private Long faultId;			//π ’œ–≈œ¢id
	private Long unitId;
	private String unitName;
	private String flag;

	// Constructors

	/** default constructor */
	public GzclSuccor() {
	}

	/** full constructor */
	public GzclSuccor(Long faultId, Long unitId, String unitName,
			String flag) {
		this.faultId = faultId;
		this.unitId = unitId;
		this.unitName = unitName;
		this.flag = flag;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFaultId() {
		return this.faultId;
	}

	public void setFaultId(Long faultId) {
		this.faultId = faultId;
	}

	public Long getUnitId() {
		return this.unitId;
	}

	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return this.unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}