package com.jrsoft.fri.dtjk.entity;

import java.math.BigDecimal;

/**
 * DtjkPush entity. @author MyEclipse Persistence Tools
 */

public class DtjkPush implements java.io.Serializable {

	// Fields

	private Long id;
	private DtjkElevator elevatorId=new DtjkElevator();//电梯Id
	private String registerid;			//电梯注册号
	private String distinguishid;		//识别码
	private String installPlace;		//安装地点
	private String faultType;	//故障类型
	private String flag;			//是否提醒  0 未提醒  1正在提醒  2已提醒
	private String code; 				//故障代码
	private String alarmTime;	//报警时间
	private String useUnitName; //使用单位

	// Constructors

	/** default constructor */
	public DtjkPush() {
	}

	/** full constructor */
	public DtjkPush(DtjkElevator elevatorId, String registerid,
			String distinguishid, String installPlace, String faultType,
			String flag, String code,String alarmTime,String useUnitName) {
		this.elevatorId = elevatorId;
		this.registerid = registerid;
		this.distinguishid = distinguishid;
		this.installPlace = installPlace;
		this.faultType = faultType;
		this.flag = flag;
		this.code=code;
		this.alarmTime=alarmTime;
		this.useUnitName=useUnitName;
		
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

	public String getRegisterid() {
		return this.registerid;
	}

	public void setRegisterid(String registerid) {
		this.registerid = registerid;
	}

	public String getDistinguishid() {
		return this.distinguishid;
	}

	public void setDistinguishid(String distinguishid) {
		this.distinguishid = distinguishid;
	}

	public String getInstallPlace() {
		return this.installPlace;
	}

	public void setInstallPlace(String installPlace) {
		this.installPlace = installPlace;
	}

	public String getFaultType() {
		return this.faultType;
	}

	public void setFaultType(String faultType) {
		this.faultType = faultType;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAlarmTime() {
		return alarmTime;
	}

	public void setAlarmTime(String alarmTime) {
		this.alarmTime = alarmTime;
	}

	public String getUseUnitName() {
		return useUnitName;
	}

	public void setUseUnitName(String useUnitName) {
		this.useUnitName = useUnitName;
	}

}