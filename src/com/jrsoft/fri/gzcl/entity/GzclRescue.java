package com.jrsoft.fri.gzcl.entity;

import com.jrsoft.fri.xtgl.entity.XtglRescueUnit;


/**
 * GzclRescue entity. @author MyEclipse Persistence Tools
 */

public class GzclRescue implements java.io.Serializable {

	// Fields

	private Long id;
	private GzclFault faultId=new GzclFault();			//故障信息id
	private XtglRescueUnit rescueUnitId=new XtglRescueUnit();			//救援单位id
	
	// Constructors

	/** default constructor */
	public GzclRescue() {
	}

	/** full constructor */
	public GzclRescue(GzclFault faultId, XtglRescueUnit rescueUnitId) {
		this.faultId = faultId;
		this.rescueUnitId = rescueUnitId;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GzclFault getFaultId() {
		return faultId;
	}

	public void setFaultId(GzclFault faultId) {
		this.faultId = faultId;
	}

	public XtglRescueUnit getRescueUnitId() {
		return rescueUnitId;
	}

	public void setRescueUnitId(XtglRescueUnit rescueUnitId) {
		this.rescueUnitId = rescueUnitId;
	}


}