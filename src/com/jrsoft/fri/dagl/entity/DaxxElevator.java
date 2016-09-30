package com.jrsoft.fri.dagl.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 电梯表
 */

public class DaxxElevator implements java.io.Serializable {

	// Fields

	private int id;
	private String registerid;			//电梯注册号
	private String distinguishid;		//识别码
	private String brand;		//电梯品牌
	private String model;		//电梯型号
	private String state;		//注册状态
	private String type;		//电梯类型
	private String numbers;		//总层数
	private String lengths;		//长度
	private String label;		//地图标注
	private String place;		//安装地点
	private Date manufactureTime;		//生产日期
	private int gatewayId;		//电梯网关id
	private int useUnitId;		//使用单位id
	private int maintenanceUnitId;		//维保单位id
	private String yearlyState;		//年检状态
	private String maintenanceState;		//维保状态

	// Constructors

	/** default constructor */
	public DaxxElevator() {
	}

	/** full constructor */
	public DaxxElevator(String registerid, String distinguishid, String brand,
			String model, String state, String type, String numbers,
			String lengths, String label, String place,
			Date manufactureTime, int gatewayId,
			int useUnitId, int maintenanceUnitId,
			String yearlyState, String maintenanceState) {
		this.registerid = registerid;
		this.distinguishid = distinguishid;
		this.brand = brand;
		this.model = model;
		this.state = state;
		this.type = type;
		this.numbers = numbers;
		this.lengths = lengths;
		this.label = label;
		this.place = place;
		this.manufactureTime = manufactureTime;
		this.gatewayId = gatewayId;
		this.useUnitId = useUnitId;
		this.maintenanceUnitId = maintenanceUnitId;
		this.yearlyState = yearlyState;
		this.maintenanceState = maintenanceState;
	}

	// Property accessors

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNumbers() {
		return this.numbers;
	}

	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}

	public String getLengths() {
		return this.lengths;
	}

	public void setLengths(String lengths) {
		this.lengths = lengths;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getPlace() {
		return this.place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Date getManufactureTime() {
		return this.manufactureTime;
	}

	public void setManufactureTime(Date manufactureTime) {
		this.manufactureTime = manufactureTime;
	}

	public int getGatewayId() {
		return this.gatewayId;
	}

	public void setGatewayId(int gatewayId) {
		this.gatewayId = gatewayId;
	}

	public int getUseUnitId() {
		return this.useUnitId;
	}

	public void setUseUnitId(int useUnitId) {
		this.useUnitId = useUnitId;
	}

	public int getMaintenanceUnitId() {
		return this.maintenanceUnitId;
	}

	public void setMaintenanceUnitId(int maintenanceUnitId) {
		this.maintenanceUnitId = maintenanceUnitId;
	}

	public String getYearlyState() {
		return this.yearlyState;
	}

	public void setYearlyState(String yearlyState) {
		this.yearlyState = yearlyState;
	}

	public String getMaintenanceState() {
		return this.maintenanceState;
	}

	public void setMaintenanceState(String maintenanceState) {
		this.maintenanceState = maintenanceState;
	}

}