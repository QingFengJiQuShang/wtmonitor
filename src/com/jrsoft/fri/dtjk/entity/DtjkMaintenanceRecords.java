package com.jrsoft.fri.dtjk.entity;


import java.util.Date;

import com.jrsoft.fri.xtgl.entity.XtglMaintenanceUnit;
import com.jrsoft.fri.xtgl.entity.XtglMaintenanceUsers;
import com.jrsoft.fri.xtgl.entity.XtglUseUnit;

/**
 * ����ά����¼��
 */

public class DtjkMaintenanceRecords implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;				//
	private Date time;				//ά������
	private XtglMaintenanceUnit unitId=new XtglMaintenanceUnit();			//ά����λid
	private XtglMaintenanceUsers userId=new XtglMaintenanceUsers();			//ά����id
	private XtglUseUnit useUnitId=new XtglUseUnit();			//ʹ�õ�λid
	private DtjkElevator elevatorId=new DtjkElevator();//ά������Id
	private String content;				//ά������
	private String cardNumber;				//����
	private String remarks;				//��ע
	
	private String useUnitName;  //ʹ�õ�λ
	private String userName;		//ά����Ա
	private String unitName;    	//ά����λ����
	private String registerid;			//����ע���
	private String distinguishid;			//ʶ����
	private String place;			//��װ�ص�
	   private String  propertyUnitName;				//��ҵ��λ
	   private String phone;				//�绰
	// Constructors

	/** default constructor */
	public DtjkMaintenanceRecords() {
	}

	/** full constructor */
	public DtjkMaintenanceRecords(Date time, XtglMaintenanceUnit unitId,
			XtglMaintenanceUsers userId, String content, DtjkElevator elevatorId,
			String cardNumber, String remarks, XtglUseUnit useUnitId) {
		this.time = time;
		this.unitId = unitId;
		this.userId = userId;
		this.content = content;
		this.elevatorId = elevatorId;
		this.cardNumber = cardNumber;
		this.remarks = remarks;
		this.useUnitId = useUnitId;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCardNumber() {
		return this.cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public XtglMaintenanceUnit getUnitId() {
		return unitId;
	}

	public void setUnitId(XtglMaintenanceUnit unitId) {
		this.unitId = unitId;
	}

	public XtglMaintenanceUsers getUserId() {
		return userId;
	}

	public void setUserId(XtglMaintenanceUsers userId) {
		this.userId = userId;
	}

	public XtglUseUnit getUseUnitId() {
		return useUnitId;
	}

	public void setUseUnitId(XtglUseUnit useUnitId) {
		this.useUnitId = useUnitId;
	}

	public DtjkElevator getElevatorId() {
		return elevatorId;
	}

	public void setElevatorId(DtjkElevator elevatorId) {
		this.elevatorId = elevatorId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public String getUseUnitName() {
		return useUnitName;
	}

	public void setUseUnitName(String useUnitName) {
		this.useUnitName = useUnitName;
	}

	public String getPropertyUnitName() {
		return propertyUnitName;
	}

	public void setPropertyUnitName(String propertyUnitName) {
		this.propertyUnitName = propertyUnitName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


}