package com.jrsoft.fri.dtjk.entity;


/**
 * 电梯网关表
 */

public class DtjkGateway implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;					//
	private String serialNumber;				//网关序列号
	private String elevatorId;				//电梯Id
	private String type;				//电梯网关类型
	private String hardware;				//硬件版本
	private String software;				//软件版本
	private String sim;				//SIM卡号
	private String report;				//上报周期
	private String floor;				//总楼层
	private String upper; 			//地上楼层
	private String lower; 			//地下楼层
	private String speed;				//设定速度
	private String spacing;				//平层间距
	private String networking;				//联网方式

	// Constructors

	/** default constructor */
	public DtjkGateway() {
	}

	/** full constructor */
	public DtjkGateway(String type, String hardware, String software,
			String sim, String report, String serialNumber, String ip,
			String port, String floor, String speed, String spacing,
			String networking,String elevatorId,String lower, String upper) {
		this.type = type;
		this.hardware = hardware;
		this.software = software;
		this.sim = sim;
		this.report = report;
		this.serialNumber = serialNumber;
		this.floor = floor;
		this.speed = speed;
		this.spacing = spacing;
		this.networking = networking;
		this.elevatorId=elevatorId;
		this.lower=lower;
		this.upper=upper;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHardware() {
		return this.hardware;
	}

	public void setHardware(String hardware) {
		this.hardware = hardware;
	}

	public String getSoftware() {
		return this.software;
	}

	public void setSoftware(String software) {
		this.software = software;
	}

	public String getSim() {
		return this.sim;
	}

	public void setSim(String sim) {
		this.sim = sim;
	}

	public String getReport() {
		return this.report;
	}

	public void setReport(String report) {
		this.report = report;
	}

	public String getSerialNumber() {
		return this.serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getFloor() {
		return this.floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getSpeed() {
		return this.speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public String getSpacing() {
		return this.spacing;
	}

	public void setSpacing(String spacing) {
		this.spacing = spacing;
	}

	public String getNetworking() {
		return this.networking;
	}

	public void setNetworking(String networking) {
		this.networking = networking;
	}

	public String getElevatorId() {
		return elevatorId;
	}

	public void setElevatorId(String elevatorId) {
		this.elevatorId = elevatorId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getLower() {
		return lower;
	}

	public void setLower(String lower) {
		this.lower = lower;
	}

	public String getUpper() {
		return upper;
	}

	public void setUpper(String upper) {
		this.upper = upper;
	}

}