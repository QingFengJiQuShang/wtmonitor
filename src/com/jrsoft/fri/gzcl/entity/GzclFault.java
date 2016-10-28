package com.jrsoft.fri.gzcl.entity;

import java.util.Date;


/**
 * GzclFault entity. @author MyEclipse Persistence Tools
 */

public class GzclFault implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long id;					//
	private Long elevatorId;					//���ϵ���Id
	private String fault;					//������Ϣ
	private String handle;					//������Ϣ
	private Date happenTime;					//���Ϸ���ʱ��
	private String type;					//�Ӿ�����   0�Զ��Ӿ� 1�˹��Ӿ�
	private Date alarmTime;					//�Ӿ�ʱ��
	private Date arriveTime;					//��Ԯ����ʱ��
	private Date successTime;					//��Ԯ�ɹ�ʱ��
	private String unitId;					//ʩ�ȵ�λ�б�
	private String numbers;					//��������
	private Long dutyId;					//ֵ����
	private String flag;					//�Ƿ��ǵ�ǰ���ϱ�ʾ

	// Constructors

	/** default constructor */
	public GzclFault() {
	}

	/** full constructor */
	public GzclFault(Long elevatorId, String fault, String handle,
			Date happenTime, String type, Date alarmTime,
			Date arriveTime, Date successTime, String unitId,
			String numbers, Long dutyId, String flag) {
		this.elevatorId = elevatorId;
		this.fault = fault;
		this.handle = handle;
		this.happenTime = happenTime;
		this.type = type;
		this.alarmTime = alarmTime;
		this.arriveTime = arriveTime;
		this.successTime = successTime;
		this.unitId = unitId;
		this.numbers = numbers;
		this.dutyId = dutyId;
		this.flag = flag;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getElevatorId() {
		return this.elevatorId;
	}

	public void setElevatorId(Long elevatorId) {
		this.elevatorId = elevatorId;
	}

	public String getFault() {
		return this.fault;
	}

	public void setFault(String fault) {
		this.fault = fault;
	}

	public String getHandle() {
		return this.handle;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}

	public Date getHappenTime() {
		return this.happenTime;
	}

	public void setHappenTime(Date happenTime) {
		this.happenTime = happenTime;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getAlarmTime() {
		return this.alarmTime;
	}

	public void setAlarmTime(Date alarmTime) {
		this.alarmTime = alarmTime;
	}

	public Date getArriveTime() {
		return this.arriveTime;
	}

	public void setArriveTime(Date arriveTime) {
		this.arriveTime = arriveTime;
	}

	public Date getSuccessTime() {
		return this.successTime;
	}

	public void setSuccessTime(Date successTime) {
		this.successTime = successTime;
	}

	public String getUnitId() {
		return this.unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getNumbers() {
		return this.numbers;
	}

	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}

	public Long getDutyId() {
		return this.dutyId;
	}

	public void setDutyId(Long dutyId) {
		this.dutyId = dutyId;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}