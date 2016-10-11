package com.jrsoft.fri.xtgl.entity;

import java.util.Date;

/**
 * 电梯年检表
 */

public class XtglYearlyInspection implements java.io.Serializable {

	// Fields

	private Long id;			//id
	private Date time;			//检验日期
	private String inspectionUnit;			//检验单位
	private String result;			//检验结果
	private String remarks;			//备注
	private Long elevatorId;			//年检电梯Id

	// Constructors

	/** default constructor */
	public XtglYearlyInspection() {
	}

	/** full constructor */
	public XtglYearlyInspection(Date time, String inspectionUnit,
			String result, String remarks, Long elevatorId) {
		this.time = time;
		this.inspectionUnit = inspectionUnit;
		this.result = result;
		this.remarks = remarks;
		this.elevatorId = elevatorId;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getInspectionUnit() {
		return this.inspectionUnit;
	}

	public void setInspectionUnit(String inspectionUnit) {
		this.inspectionUnit = inspectionUnit;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Long getElevatorId() {
		return this.elevatorId;
	}

	public void setElevatorId(Long elevatorId) {
		this.elevatorId = elevatorId;
	}

}