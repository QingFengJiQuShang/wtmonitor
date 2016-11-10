package com.jrsoft.fri.dtjk.entity;


import java.util.Date;

import com.jrsoft.fri.xtgl.entity.XtglUseUnit;

/**
 * 电梯年检表
 */

public class DtjkYearlyInspection implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;				//
	private Date time;				//检验日期
	private String inspectionUnit;				//检验单位
	private String result;				//检验结果
	private String remarks;				//备注
	private DtjkElevator elevatorId=new DtjkElevator();//维保电梯Id
	private XtglUseUnit useUnitId=new XtglUseUnit();			//使用单位id

	private String useUnitName;  //使用单位
	private String userName;		//维保人员
	private String unitName;    	//维保单位名称
	private String registerid;			//电梯注册号
	private String distinguishid;			//识别码
	private String place;			//安装地点

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