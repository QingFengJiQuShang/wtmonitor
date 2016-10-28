package com.jrsoft.fri.dtjk.entity;

import java.util.Date;

/**
 * 电梯表
 */

public class DtjkElevator implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private Long id;			//
	private String registerid;			//电梯注册号
	private String distinguishid;			//识别码
	private String brand;			//电梯品牌（选择）
	private String model;			//电梯型号
	private String state;			//注册状态
	private String type;			//电梯类型
	private String numbers;			//总层数
	private String label;			//地图标注
	private String place;			//安装地点
	private Date manufactureTime;			//生产日期
	private Long gatewayId;			//电梯网关id
	private Long useUnitId;			//使用单位id
	private Long maintenanceUnitId;			//维保单位id
	private String yearlyState;			//年检状态
	private String maintenanceState;			//维保状态
	private String registerState;			//注册状态
	private String speed;			//电梯速度
	private Long maintenanceUsersId;			//维保人id
	private String installPlace;			//安装地点
	private String installUnit;			//安装单位
	private String installUser;			//安装人
	private Date installTime;			//安装时间
	private String manufacturer;			//制造商
	private String manufacturerPhone;			//制作商电话
	private String manufacturerAddress;			//制造商地址
	private String manufacturerUrl;			//制造商网址
	private String filialeAddress;			//制作商本地分公司地址
	private String filialePhone;			//制造商本地分公司电话
	private String filialeContact;			//制造商本地分公司联系人
	private String serviceIfe;			//电梯使用年限
	private Date yearlyTime;			//上次年检时间
	private Date maintenanceTime;			//上次维保时间
	private String remarks;			//备注

	// Constructors

	/** default constructor */
	public DtjkElevator() {
	}

	/** full constructor */
	public DtjkElevator(String registerid, String distinguishid, String brand,
			String model, String state, String type, String numbers,
			String label, String place, Date manufactureTime,
			Long gatewayId, Long useUnitId,
			Long maintenanceUnitId, String yearlyState,
			String maintenanceState, String registerState, String speed,
			Long maintenanceUsersId, String installPlace,
			String installUnit, String installUser, Date installTime,
			String manufacturer, String manufacturerPhone,
			String manufacturerAddress, String manufacturerUrl,
			String filialeAddress, String filialePhone, String filialeContact,
			String serviceIfe, Date yearlyTime, Date maintenanceTime,
			String remarks) {
		this.registerid = registerid;
		this.distinguishid = distinguishid;
		this.brand = brand;
		this.model = model;
		this.state = state;
		this.type = type;
		this.numbers = numbers;
		this.label = label;
		this.place = place;
		this.manufactureTime = manufactureTime;
		this.gatewayId = gatewayId;
		this.useUnitId = useUnitId;
		this.maintenanceUnitId = maintenanceUnitId;
		this.yearlyState = yearlyState;
		this.maintenanceState = maintenanceState;
		this.registerState = registerState;
		this.speed = speed;
		this.maintenanceUsersId = maintenanceUsersId;
		this.installPlace = installPlace;
		this.installUnit = installUnit;
		this.installUser = installUser;
		this.installTime = installTime;
		this.manufacturer = manufacturer;
		this.manufacturerPhone = manufacturerPhone;
		this.manufacturerAddress = manufacturerAddress;
		this.manufacturerUrl = manufacturerUrl;
		this.filialeAddress = filialeAddress;
		this.filialePhone = filialePhone;
		this.filialeContact = filialeContact;
		this.serviceIfe = serviceIfe;
		this.yearlyTime = yearlyTime;
		this.maintenanceTime = maintenanceTime;
		this.remarks = remarks;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
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

	public Long getGatewayId() {
		return this.gatewayId;
	}

	public void setGatewayId(Long gatewayId) {
		this.gatewayId = gatewayId;
	}

	public Long getUseUnitId() {
		return this.useUnitId;
	}

	public void setUseUnitId(Long useUnitId) {
		this.useUnitId = useUnitId;
	}

	public Long getMaintenanceUnitId() {
		return this.maintenanceUnitId;
	}

	public void setMaintenanceUnitId(Long maintenanceUnitId) {
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

	public String getRegisterState() {
		return this.registerState;
	}

	public void setRegisterState(String registerState) {
		this.registerState = registerState;
	}

	public String getSpeed() {
		return this.speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public Long getMaintenanceUsersId() {
		return this.maintenanceUsersId;
	}

	public void setMaintenanceUsersId(Long maintenanceUsersId) {
		this.maintenanceUsersId = maintenanceUsersId;
	}

	public String getInstallPlace() {
		return this.installPlace;
	}

	public void setInstallPlace(String installPlace) {
		this.installPlace = installPlace;
	}

	public String getInstallUnit() {
		return this.installUnit;
	}

	public void setInstallUnit(String installUnit) {
		this.installUnit = installUnit;
	}

	public String getInstallUser() {
		return this.installUser;
	}

	public void setInstallUser(String installUser) {
		this.installUser = installUser;
	}

	public Date getInstallTime() {
		return this.installTime;
	}

	public void setInstallTime(Date installTime) {
		this.installTime = installTime;
	}

	public String getManufacturer() {
		return this.manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getManufacturerPhone() {
		return this.manufacturerPhone;
	}

	public void setManufacturerPhone(String manufacturerPhone) {
		this.manufacturerPhone = manufacturerPhone;
	}

	public String getManufacturerAddress() {
		return this.manufacturerAddress;
	}

	public void setManufacturerAddress(String manufacturerAddress) {
		this.manufacturerAddress = manufacturerAddress;
	}

	public String getManufacturerUrl() {
		return this.manufacturerUrl;
	}

	public void setManufacturerUrl(String manufacturerUrl) {
		this.manufacturerUrl = manufacturerUrl;
	}

	public String getFilialeAddress() {
		return this.filialeAddress;
	}

	public void setFilialeAddress(String filialeAddress) {
		this.filialeAddress = filialeAddress;
	}

	public String getFilialePhone() {
		return this.filialePhone;
	}

	public void setFilialePhone(String filialePhone) {
		this.filialePhone = filialePhone;
	}

	public String getFilialeContact() {
		return this.filialeContact;
	}

	public void setFilialeContact(String filialeContact) {
		this.filialeContact = filialeContact;
	}

	public String getServiceIfe() {
		return this.serviceIfe;
	}

	public void setServiceIfe(String serviceIfe) {
		this.serviceIfe = serviceIfe;
	}

	public Date getYearlyTime() {
		return this.yearlyTime;
	}

	public void setYearlyTime(Date yearlyTime) {
		this.yearlyTime = yearlyTime;
	}

	public Date getMaintenanceTime() {
		return this.maintenanceTime;
	}

	public void setMaintenanceTime(Date maintenanceTime) {
		this.maintenanceTime = maintenanceTime;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}