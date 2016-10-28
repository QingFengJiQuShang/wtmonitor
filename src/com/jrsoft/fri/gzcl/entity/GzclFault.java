package com.jrsoft.fri.gzcl.entity;

import java.util.Date;


/**
 * GzclFault entity. @author MyEclipse Persistence Tools
 */

public class GzclFault implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long id;					//
	private Long elevatorId;					//故障电梯Id
	private String fault;					//故障信息
	private String handle;					//处理信息
	private Date happenTime;					//故障发生时间
	private String type;					//接警类型   0自动接警 1人工接警
	private Date alarmTime;					//接警时间
	private Date arriveTime;					//救援到达时间
	private Date successTime;					//救援成功时间
	private String unitId;					//施救单位列表
	private String numbers;					//困人数量
	private Long dutyId;					//值班人
	private String flag;					//是否是当前故障标示

	// Constructors

	/** default constructor */
	public GzclFault() {
	}

	/** full constructor */
	public GzclFault(Long elevatorId, String fault, String handle,
			Date happenTime, String type, Date alarmTime,
			Date arriveTime, Date successTime, String unitId,
			String numbers, Long dutyId, String flag) {
		this.elevatorId = elevatorId;
		this.fault = fault;
		this.handle = handle;
		this.happenTime = happenTime;
		this.type = type;
		this.alarmTime = alarmTime;
		this.arriveTime = arriveTime;
		this.successTime = successTime;
		this.unitId = unitId;
		this.numbers = numbers;
		this.dutyId = dutyId;
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

	public String getHandle() {
		return this.handle;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}

	public Date getHappenTime() {
		return this.happenTime;
	}

	public void setHappenTime(Date happenTime) {
		this.happenTime = happenTime;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getAlarmTime() {
		return this.alarmTime;
	}

	public void setAlarmTime(Date alarmTime) {
		this.alarmTime = alarmTime;
	}

	public Date getArriveTime() {
		return this.arriveTime;
	}

	public void setArriveTime(Date arriveTime) {
		this.arriveTime = arriveTime;
	}

	public Date getSuccessTime() {
		return this.successTime;
	}

	public void setSuccessTime(Date successTime) {
		this.successTime = successTime;
	}

	public String getUnitId() {
		return this.unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getNumbers() {
		return this.numbers;
	}

	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}

	public Long getDutyId() {
		return this.dutyId;
	}

	public void setDutyId(Long dutyId) {
		this.dutyId = dutyId;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}