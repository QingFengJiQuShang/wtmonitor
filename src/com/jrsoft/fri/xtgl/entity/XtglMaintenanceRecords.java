package com.jrsoft.fri.xtgl.entity;

import java.util.Date;

/**
 * 电梯维保记录表
 */

public class XtglMaintenanceRecords implements java.io.Serializable {

	// Fields

	private int id;		//id
	private Date time;		//维保日期
	private int unitId;		//维保单位_id
	private int userId;		//维保人Id
	private String content;		//维保内容
	private int elevatorId;		//维保电梯Id

	// Constructors

	/** default constructor */
	public XtglMaintenanceRecords() {
	}

	/** full constructor */
	public XtglMaintenanceRecords(Date time, int unitId,
			int userId, String content, int elevatorId) {
		this.time = time;
		this.unitId = unitId;
		this.userId = userId;
		this.content = content;
		this.elevatorId = elevatorId;
	}

	// Property accessors

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getUnitId() {
		return this.unitId;
	}

	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getElevatorId() {
		return this.elevatorId;
	}

	public void setElevatorId(int elevatorId) {
		this.elevatorId = elevatorId;
	}

}