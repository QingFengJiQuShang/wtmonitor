package com.jrsoft.fri.dtjk.entity;

import java.util.Date;

import com.jrsoft.fri.xtgl.entity.XtglMaintenanceUnit;
import com.jrsoft.fri.xtgl.entity.XtglMaintenanceUsers;
import com.jrsoft.fri.xtgl.entity.XtglUseUnit;
import com.jrsoft.fri.xtgl.entity.XtglUsers;

/**
 * 电梯表
 */

/**
 * @author Ziliang
 *
 */
public class DtjkElevator implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private Long id;			//
	private String registerid;			//电梯注册号
	private String distinguishid;			//识别码
	private String brand;			//电梯品牌（选择）
	private String model;			//电梯型号
	private String state;			//运行状态
	private String type;			//电梯类型
	private String numbers;			//总层数
	private String label;			//地图标注
	private String place;			//安装地点
	private Date manufactureTime;			//生产日期
	private DtjkGateway gatewayId=new DtjkGateway();			//电梯网关id
	private XtglUseUnit useUnitId=new XtglUseUnit();			//使用单位id
	private XtglMaintenanceUnit maintenanceUnitId=new XtglMaintenanceUnit();			//维保单位id
	private String yearlyState;			//年检状态
	private String maintenanceState;			//维保状态
	private String registerState;			//注册状态
	private String speed;			//电梯速度
	private XtglMaintenanceUsers maintenanceUsersId=new XtglMaintenanceUsers();			//维保人id
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
    private XtglUsers userid=new XtglUsers();				//所属用户
    private String period;				//上报周期
    private Date flowStart;				//流量开始时间
    private Date flowEnd;						//流量结束时间
    private Long flowTotal;				//流量总额
    private Long flowNum;				//流量使用额
    private Long flowSurplus;				//剩余流量
    private Date reportTime;				//上次上报时间
    private String delflag;						//删除标识 0未删除，1已删除
    private String serviceState;			//服务状态 1 服务中   
    private String safeState;					//保险状态 null 未保 0 脱保  1在保  
	// Constructors
    
    private String useUnitName;			//使用单位名称
    private String maintenanceUnitName;		//维保单位名称
    private String maintenanceUsersName;	//维保人员姓名
    
    private int numRecords;  //维保记录数量
    private int numYearly;  //年检记录数量
    private int numService;  //服务费记录数量
    private int num;  //上报记录数量
	/** default constructor */
	public DtjkElevator() {
	}

	/** full constructor */
	public DtjkElevator(String registerid, String distinguishid, String brand,
			String model, String state, String type, String numbers,
			String label, String place, Date manufactureTime,
			DtjkGateway gatewayId, XtglUseUnit useUnitId,
			XtglMaintenanceUnit maintenanceUnitId, String yearlyState,
			String maintenanceState, String registerState, String speed,
			XtglMaintenanceUsers maintenanceUsersId, String installPlace,
			String installUnit, String installUser, Date installTime,
			String manufacturer, String manufacturerPhone,
			String manufacturerAddress, String manufacturerUrl,
			String filialeAddress, String filialePhone, String filialeContact,
			String serviceIfe, Date yearlyTime, Date maintenanceTime,
			String remarks,XtglUsers userid,String period,Date flowStart,
			Date flowEnd,Long flowTotal,Long flowNum,Long flowSurplus,
			Date reportTime,String delflag,String serviceState,String safeState) {
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
		this.period=period;
		this.flowStart=flowStart;
		this.flowEnd=flowEnd;
		this.flowTotal=flowTotal;
		this.flowNum=flowNum;
		this.flowSurplus=flowSurplus;
		this.reportTime=reportTime;
		this.delflag=delflag;
		this.serviceState=serviceState;
		this.safeState=safeState;
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

	public DtjkGateway getGatewayId() {
		return gatewayId;
	}

	public void setGatewayId(DtjkGateway gatewayId) {
		this.gatewayId = gatewayId;
	}

	public XtglUseUnit getUseUnitId() {
		return useUnitId;
	}

	public void setUseUnitId(XtglUseUnit useUnitId) {
		this.useUnitId = useUnitId;
	}

	public XtglMaintenanceUnit getMaintenanceUnitId() {
		return maintenanceUnitId;
	}

	public void setMaintenanceUnitId(XtglMaintenanceUnit maintenanceUnitId) {
		this.maintenanceUnitId = maintenanceUnitId;
	}

	public XtglMaintenanceUsers getMaintenanceUsersId() {
		return maintenanceUsersId;
	}

	public void setMaintenanceUsersId(XtglMaintenanceUsers maintenanceUsersId) {
		this.maintenanceUsersId = maintenanceUsersId;
	}

	public XtglUsers getUserid() {
		return userid;
	}

	public void setUserid(XtglUsers userid) {
		this.userid = userid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUseUnitName() {
		return useUnitName;
	}

	public void setUseUnitName(String useUnitName) {
		this.useUnitName = useUnitName;
	}

	public String getMaintenanceUnitName() {
		return maintenanceUnitName;
	}

	public void setMaintenanceUnitName(String maintenanceUnitName) {
		this.maintenanceUnitName = maintenanceUnitName;
	}

	public String getMaintenanceUsersName() {
		return maintenanceUsersName;
	}

	public void setMaintenanceUsersName(String maintenanceUsersName) {
		this.maintenanceUsersName = maintenanceUsersName;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getNumRecords() {
		return numRecords;
	}

	public void setNumRecords(int numRecords) {
		this.numRecords = numRecords;
	}

	public int getNumYearly() {
		return numYearly;
	}

	public void setNumYearly(int numYearly) {
		this.numYearly = numYearly;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public Date getFlowStart() {
		return flowStart;
	}

	public void setFlowStart(Date flowStart) {
		this.flowStart = flowStart;
	}

	public Date getFlowEnd() {
		return flowEnd;
	}

	public void setFlowEnd(Date flowEnd) {
		this.flowEnd = flowEnd;
	}

	public Long getFlowTotal() {
		return flowTotal;
	}

	public void setFlowTotal(Long flowTotal) {
		this.flowTotal = flowTotal;
	}

	public Long getFlowNum() {
		return flowNum;
	}

	public void setFlowNum(Long flowNum) {
		this.flowNum = flowNum;
	}

	public Long getFlowSurplus() {
		return flowSurplus;
	}

	public void setFlowSurplus(Long flowSurplus) {
		this.flowSurplus = flowSurplus;
	}

	public Date getReportTime() {
		return reportTime;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}

	public String getDelflag() {
		return delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

	public int getNumService() {
		return numService;
	}

	public void setNumService(int numService) {
		this.numService = numService;
	}

	public String getServiceState() {
		return serviceState;
	}

	public void setServiceState(String serviceState) {
		this.serviceState = serviceState;
	}

	public String getSafeState() {
		return safeState;
	}

	public void setSafeState(String safeState) {
		this.safeState = safeState;
	}

}