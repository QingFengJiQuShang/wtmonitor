package com.jrsoft.fri.dtjk.entity;



/**
 * ���ݰ�����
 */

public class DtjkPhone implements java.io.Serializable {

	// Fields

	private Long id;
	private DtjkElevator elevatorId=new DtjkElevator();//ά������Id
	private String phone;			//������

	// Constructors

	/** default constructor */
	public DtjkPhone() {
	}

	/** full constructor */
	public DtjkPhone(DtjkElevator elevatorId, String phone) {
		this.elevatorId = elevatorId;
		this.phone = phone;
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

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}