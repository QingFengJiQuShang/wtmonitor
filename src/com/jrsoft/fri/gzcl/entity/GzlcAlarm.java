package com.jrsoft.fri.gzcl.entity;

import java.util.Date;

/**
 * GzlcAlarm entity. @author MyEclipse Persistence Tools
 */

public class GzlcAlarm implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;					//
	private Long elevatorId;					//报警电梯Id
	private String fault;					//故障问题
	private Date time;					//日期
	private String alarmPerson;					//报警人名称
	private String phone;					//电话
	private String describe;					//故障描述
	private String flag;					//是否转入当前故障标示

	// Constructors

	/** default constructor */
	public GzlcAlarm() {
	}

	/** full constructor */
	public GzlcAlarm(Long elevatorId, String fault, Date time,
			String alarmPerson, String phone, String describe, String flag) {
		this.elevatorId = elevatorId;
		this.fault = fault;
		this.time = time;
		this.alarmPerson = alarmPerson;
		this.phone = phone;
		this.describe = describe;
		this.flag = flag;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getElevatorId() {
		return this.elevatorId;
	}

	public void setElevatorId(Long elevatorId) {
		this.elevatorId = elevatorId;
	}

	public String getFault() {
		return this.fault;
	}

	public void setFault(String fault) {
		this.fault = fault;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getAlarmPerson() {
		return this.alarmPerson;
	}

	public void setAlarmPerson(String alarmPerson) {
		this.alarmPerson = alarmPerson;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDescribe() {
		return this.describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}