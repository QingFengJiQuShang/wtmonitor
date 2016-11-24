package com.jrsoft.fri.dtjk.entity;

import java.math.BigDecimal;

/**
 * DtjkPush entity. @author MyEclipse Persistence Tools
 */

public class DtjkPush implements java.io.Serializable {

	// Fields

	private Long id;
	private DtjkElevator elevatorId=new DtjkElevator();//����Id
	private String registerid;			//����ע���
	private String distinguishid;		//ʶ����
	private String installPlace;		//��װ�ص�
	private String faultType;	//��������
	private String flag;			//�Ƿ�����  0 δ����  1��������  2������

	// Constructors

	/** default constructor */
	public DtjkPush() {
	}

	/** full constructor */
	public DtjkPush(DtjkElevator elevatorId, String registerid,
			String distinguishid, String installPlace, String faultType,
			String flag) {
		this.elevatorId = elevatorId;
		this.registerid = registerid;
		this.distinguishid = distinguishid;
		this.installPlace = installPlace;
		this.faultType = faultType;
		this.flag = flag;
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

	public String getInstallPlace() {
		return this.installPlace;
	}

	public void setInstallPlace(String installPlace) {
		this.installPlace = installPlace;
	}

	public String getFaultType() {
		return this.faultType;
	}

	public void setFaultType(String faultType) {
		this.faultType = faultType;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}