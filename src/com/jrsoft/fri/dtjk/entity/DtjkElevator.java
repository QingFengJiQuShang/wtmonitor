package com.jrsoft.fri.dtjk.entity;

import java.util.Date;

import com.jrsoft.fri.xtgl.entity.XtglMaintenanceUnit;
import com.jrsoft.fri.xtgl.entity.XtglMaintenanceUsers;
import com.jrsoft.fri.xtgl.entity.XtglUseUnit;
import com.jrsoft.fri.xtgl.entity.XtglUsers;

/**
 * ���ݱ�
 */

/**
 * @author Ziliang
 *
 */
public class DtjkElevator implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private Long id;			//
	private String registerid;			//����ע���
	private String distinguishid;			//ʶ����
	private String brand;			//����Ʒ�ƣ�ѡ��
	private String model;			//�����ͺ�
	private String state;			//����״̬
	private String type;			//��������
	private String numbers;			//�ܲ���
	private String label;			//��ͼ��ע
	private String place;			//��װ�ص�
	private Date manufactureTime;			//��������
	private DtjkGateway gatewayId=new DtjkGateway();			//��������id
	private XtglUseUnit useUnitId=new XtglUseUnit();			//ʹ�õ�λid
	private XtglMaintenanceUnit maintenanceUnitId=new XtglMaintenanceUnit();			//ά����λid
	private String yearlyState;			//���״̬
	private String maintenanceState;			//ά��״̬
	private String registerState;			//ע��״̬
	private String speed;			//�����ٶ�
	private XtglMaintenanceUsers maintenanceUsersId=new XtglMaintenanceUsers();			//ά����id
	private String installPlace;			//��װ�ص�
	private String installUnit;			//��װ��λ
	private String installUser;			//��װ��
	private Date installTime;			//��װʱ��
	private String manufacturer;			//������
	private String manufacturerPhone;			//�����̵绰
	private String manufacturerAddress;			//�����̵�ַ
	private String manufacturerUrl;			//��������ַ
	private String filialeAddress;			//�����̱��طֹ�˾��ַ
	private String filialePhone;			//�����̱��طֹ�˾�绰
	private String filialeContact;			//�����̱��طֹ�˾��ϵ��
	private String serviceIfe;			//����ʹ������
	private Date yearlyTime;			//�ϴ����ʱ��
	private Date maintenanceTime;			//�ϴ�ά��ʱ��
	private String remarks;			//��ע
    private XtglUsers userid=new XtglUsers();				//�����û�
    private String period;				//�ϱ�����
    private Date flowStart;				//������ʼʱ��
    private Date flowEnd;						//��������ʱ��
    private String flowTotal;				//�����ܶ�
    private String flowNum;				//����ʹ�ö�
    private String flowSurplus;				//ʣ������
    private Date reportTime;				//�ϴ��ϱ�ʱ��
	// Constructors
    
    private String useUnitName;			//ʹ�õ�λ����
    private String maintenanceUnitName;		//ά����λ����
    private String maintenanceUsersName;	//ά����Ա����
    
    private int numRecords;  //ά����¼����
    private int numYearly;  //����¼����
    private int num;  //�ϱ���¼����
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
			Date flowEnd,String flowTotal,String flowNum,String flowSurplus, Date reportTime) {
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

	public String getFlowTotal() {
		return flowTotal;
	}

	public void setFlowTotal(String flowTotal) {
		this.flowTotal = flowTotal;
	}

	public String getFlowNum() {
		return flowNum;
	}

	public void setFlowNum(String flowNum) {
		this.flowNum = flowNum;
	}

	public String getFlowSurplus() {
		return flowSurplus;
	}

	public void setFlowSurplus(String flowSurplus) {
		this.flowSurplus = flowSurplus;
	}

	public Date getReportTime() {
		return reportTime;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}

}