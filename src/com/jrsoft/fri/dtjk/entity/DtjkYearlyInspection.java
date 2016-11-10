package com.jrsoft.fri.dtjk.entity;


import java.util.Date;

import com.jrsoft.fri.xtgl.entity.XtglUseUnit;

/**
 * ��������
 */

public class DtjkYearlyInspection implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;				//
	private Date time;				//��������
	private String inspectionUnit;				//���鵥λ
	private String result;				//������
	private String remarks;				//��ע
	private DtjkElevator elevatorId=new DtjkElevator();//ά������Id
	private XtglUseUnit useUnitId=new XtglUseUnit();			//ʹ�õ�λid

	private String useUnitName;  //ʹ�õ�λ
	private String userName;		//ά����Ա
	private String unitName;    	//ά����λ����
	private String registerid;			//����ע���
	private String distinguishid;			//ʶ����
	private String place;			//��װ�ص�

	/** default constructor */
	public DtjkYearlyInspection() {
	}

	/** full constructor */
	public DtjkYearlyInspection(Date time, String inspectionUnit,
			String result, String remarks, DtjkElevator elevatorId,
			XtglUseUnit useUnitId) {
		this.time = time;
		this.inspectionUnit = inspectionUnit;
		this.result = result;
		this.remarks = remarks;
		this.elevatorId = elevatorId;
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

	public String getInspectionUnit() {
		return this.inspectionUnit;
	}

	public void setInspectionUnit(String inspectionUnit) {
		this.inspectionUnit = inspectionUnit;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public DtjkElevator getElevatorId() {
		return elevatorId;
	}

	public void setElevatorId(DtjkElevator elevatorId) {
		this.elevatorId = elevatorId;
	}

	public XtglUseUnit getUseUnitId() {
		return useUnitId;
	}

	public void setUseUnitId(XtglUseUnit useUnitId) {
		this.useUnitId = useUnitId;
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