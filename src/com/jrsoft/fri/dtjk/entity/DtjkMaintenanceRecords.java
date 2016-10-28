package com.jrsoft.fri.dtjk.entity;


import java.util.Date;

/**
 * 电梯维保记录表
 */

public class DtjkMaintenanceRecords implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;				//
	private Date time;				//维保日期
	private Long unitId;				//维保单位_id
	private Long userId;				//维保人Id
	private String content;				//维保内容
	private Long elevatorId;				//维保电梯Id
	private String cardNumber;				//卡号
	private String remarks;				//备注
	private Long useUnitId;				//使用单位id

	// Constructors

	/** default constructor */
	public DtjkMaintenanceRecords() {
	}

	/** full constructor */
	public DtjkMaintenanceRecords(Date time, Long unitId,
			Long userId, String content, Long elevatorId,
			String cardNumber, String remarks, Long useUnitId) {
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

	public Long getUnitId() {
		return this.unitId;
	}

	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getElevatorId() {
		return this.elevatorId;
	}

	public void setElevatorId(Long elevatorId) {
		this.elevatorId = elevatorId;
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

	public Long getUseUnitId() {
		return this.useUnitId;
	}

	public void setUseUnitId(Long useUnitId) {
		this.useUnitId = useUnitId;
	}

}