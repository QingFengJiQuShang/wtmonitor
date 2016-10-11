package com.jrsoft.fri.xtgl.entity;

import java.util.Date;

/**
 * 电梯维保记录表
 */

public class XtglMaintenanceRecords implements java.io.Serializable {

	// Fields

	private Long id;		//id
	private Date time;		//维保日期
	private Long unitId;		//维保单位_id
	private Long userId;		//维保人Id
	private String content;		//维保内容
	private Long elevatorId;		//维保电梯Id

	// Constructors

	/** default constructor */
	public XtglMaintenanceRecords() {
	}

	/** full constructor */
	public XtglMaintenanceRecords(Date time, Long unitId,
			Long userId, String content, Long elevatorId) {
		this.time = time;
		this.unitId = unitId;
		this.userId = userId;
		this.content = content;
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

	public Long getUnitId() {
		return this.unitId;
	}

	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getElevatorId() {
		return this.elevatorId;
	}

	public void setElevatorId(Long elevatorId) {
		this.elevatorId = elevatorId;
	}

}