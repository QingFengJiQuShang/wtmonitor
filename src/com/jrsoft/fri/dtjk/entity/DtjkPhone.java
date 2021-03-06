package com.jrsoft.fri.dtjk.entity;



/**
 * 电梯白名单
 */

public class DtjkPhone implements java.io.Serializable {

	// Fields

	private Long id;
	private DtjkElevator elevatorId=new DtjkElevator();//维保电梯Id
	private String phone;			//白名单
	private String belong;			//所属人 

	// Constructors

	/** default constructor */
	public DtjkPhone() {
	}

	/** full constructor */
	public DtjkPhone(DtjkElevator elevatorId, String phone,String belong) {
		this.elevatorId = elevatorId;
		this.phone = phone;
		this.belong=belong;
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

	public String getBelong() {
		return belong;
	}

	public void setBelong(String belong) {
		this.belong = belong;
	}

}