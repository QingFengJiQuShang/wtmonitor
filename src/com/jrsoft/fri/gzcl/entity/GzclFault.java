package com.jrsoft.fri.gzcl.entity;

import java.util.Date;

import com.jrsoft.fri.dtjk.entity.DtjkElevator;
import com.jrsoft.fri.xtgl.entity.XtglUsers;


/**
 * GzclFault entity. @author MyEclipse Persistence Tools
 */

public class GzclFault implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long id;					//
	private DtjkElevator elevatorId=new DtjkElevator();//维保电梯Id
	private String fault;					//故障信息
	private String handle;					//处理信息
	private Date happenTime;					//故障发生时间
	private String type;					//接警类型   0自动接警 1人工接警
	private Date alarmTime;					//接警时间
	private Date arriveTime;					//救援到达时间
	private Date successTime;					//救援成功时间
	private String unitId;					//施救单位列表
	private String numbers;					//困人数量
	 private XtglUsers dutyId=new XtglUsers();			//值班人
	private String flag;					//是否是当前故障标示
	private String state;			//状态
	private String faultType;  		//故障类型 
	// Constructors
	private String registerid;			//电梯注册号
	private String distinguishid;			//识别码
	private String place;			//安装地点
	private String dutyName; //值班人姓名
	private String begintime;
	private String endtime;
	private String useUnitName;
	
	/** default constructor */
	public GzclFault() {
	}

	/** full constructor */
	public GzclFault(DtjkElevator elevatorId, String fault, String handle,
			Date happenTime, String type, Date alarmTime,
			Date arriveTime, Date successTime, String unitId,
			String numbers, XtglUsers dutyId, String flag,String state,String faultType) {
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
		this.faultType=faultType;
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

	public XtglUsers getDutyId() {
		return this.dutyId;
	}

	public void setDutyId(XtglUsers dutyId) {
		this.dutyId = dutyId;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDutyName() {
		return dutyName;
	}

	public void setDutyName(String dutyName) {
		this.dutyName = dutyName;
	}

	public String getBegintime() {
		return begintime;
	}

	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getFaultType() {
		return faultType;
	}

	public void setFaultType(String faultType) {
		this.faultType = faultType;
	}

	public String getUseUnitName() {
		return useUnitName;
	}

	public void setUseUnitName(String useUnitName) {
		this.useUnitName = useUnitName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}