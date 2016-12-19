package com.jrsoft.fri.dtjk.entity;

import java.util.Date;


/**
 * DtjkService entity. @author MyEclipse Persistence Tools
 */

public class DtjkService implements java.io.Serializable {

	// Fields

	private Long id;
	private DtjkElevator elevatorId=new DtjkElevator();//服务电梯Id
	private Date startTime;		//开始时间
	private Date endTime;			//结束时间
	private String type;				//服务类型
	private Long money;			//服务金额

	
	private String useUnitName;  //使用单位
	private String userName;		//维保人员
	private String unitName;    	//维保单位名称
	private String registerid;			//电梯注册号
	private String distinguishid;			//识别码
	private String place;			//安装地点
	// Constructors

	/** default constructor */
	public DtjkService() {
	}

	/** full constructor */
	public DtjkService(DtjkElevator elevatorId, Date startTime,
			Date endTime, String type, Long money) {
		this.elevatorId = elevatorId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.type = type;
		this.money = money;
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

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getMoney() {
		return this.money;
	}

	public void setMoney(Long money) {
		this.money = money;
	}

	public String getUseUnitName() {
		return useUnitName;
	}

	public void setUseUnitName(String useUnitName) {
		this.useUnitName = useUnitName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
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

}