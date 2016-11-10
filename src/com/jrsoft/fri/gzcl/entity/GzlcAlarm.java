package com.jrsoft.fri.gzcl.entity;

import java.util.Date;

import com.jrsoft.fri.dtjk.entity.DtjkElevator;

/**
 * GzlcAlarm entity. @author MyEclipse Persistence Tools
 */

public class GzlcAlarm implements java.io.Serializable {

	// 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;					//
	private DtjkElevator elevatorId=new DtjkElevator();//维保电梯Id
	private String fault;					//故障问题
	private Date time;					//日期
	private String alarmPerson;					//报警人名称
	private String phone;					//电话
	private String describe;					//故障描述
	private String flag;					//是否转入当前故障标示
	private Date happenTime;					//故障发生时间
	
	private String registerid;			//电梯注册号
	private String distinguishid;			//识别码
	private String place;			//安装地点
	// Constructors

	/** default constructor */
	public GzlcAlarm() {
	}

	/** full constructor */
	public GzlcAlarm(DtjkElevator elevatorId, String fault, Date time,
			String alarmPerson, String phone, String describe, String flag,Date happenTime) {
		this.elevatorId = elevatorId;
		this.fault = fault;
		this.time = time;
		this.alarmPerson = alarmPerson;
		this.phone = phone;
		this.describe = describe;
		this.flag = flag;
		this.happenTime=happenTime;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DtjkElevator getElevatorId() {
		return this.elevatorId;
	}

	public void setElevatorId(DtjkElevator elevatorId) {
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

	public String getRegisterid() {
		return registerid;
	}

	public void setRegisterid(String registerid) {
		this.registerid = registerid;
	}

	public String getDistinguishid() {
		return distinguishid;
	}

	public void setDistinguishid(String distinguishid) {
		this.distinguishid = distinguishid;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Date getHappenTime() {
		return happenTime;
	}

	public void setHappenTime(Date happenTime) {
		this.happenTime = happenTime;
	}

}