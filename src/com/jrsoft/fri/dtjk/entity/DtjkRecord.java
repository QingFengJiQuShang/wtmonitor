package com.jrsoft.fri.dtjk.entity;

import java.util.Date;


/**
 * �����ϱ���¼
 */

public class DtjkRecord implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;				//
	private String serialNumber;				//�������к�
	private String elevatorId;				//����Id
	private String direction;				//���з���
	private String speed;				//�����ٶ�
	private String floor;				//��ǰ¥��
	private String gatewayDate;				//�ն�����
	private String gatewayTime;				//�ն�ʱ��
	private String people;				//��
	private String door;				//��
	private String heartbeat;				//һ������
	private String maintenanceUserId;				//�ֳ�ά����Ա
	private String maintenanceState;				//����״̬
	private Date foundTime;                //����ʱ��
	private String type;							//��������
	
	private String foundTime1;
	private String state;//����״̬

	// Constructors

	/** default constructor */
	public DtjkRecord() {
	}

	/** full constructor */
	public DtjkRecord(String serialNumber, String elevatorId,
			String direction, String speed, String floor, String gatewayDate,
			String gatewayTime, String people, String door, String heartbeat,
			String maintenanceUserId, String maintenanceState,Date foundTime,
			String stype) {
		this.serialNumber = serialNumber;
		this.elevatorId = elevatorId;
		this.direction = direction;
		this.speed = speed;
		this.floor = floor;
		this.gatewayDate = gatewayDate;
		this.gatewayTime = gatewayTime;
		this.people = people;
		this.door = door;
		this.heartbeat = heartbeat;
		this.maintenanceUserId = maintenanceUserId;
		this.maintenanceState = maintenanceState;
		this.type=type;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSerialNumber() {
		return this.serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getElevatorId() {
		return this.elevatorId;
	}

	public void setElevatorId(String elevatorId) {
		this.elevatorId = elevatorId;
	}

	public String getDirection() {
		return this.direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getSpeed() {
		return this.speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public String getFloor() {
		return this.floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getGatewayDate() {
		return this.gatewayDate;
	}

	public void setGatewayDate(String gatewayDate) {
		this.gatewayDate = gatewayDate;
	}

	public String getGatewayTime() {
		return this.gatewayTime;
	}

	public void setGatewayTime(String gatewayTime) {
		this.gatewayTime = gatewayTime;
	}

	public String getPeople() {
		return this.people;
	}

	public void setPeople(String people) {
		this.people = people;
	}

	public String getDoor() {
		return this.door;
	}

	public void setDoor(String door) {
		this.door = door;
	}

	public String getHeartbeat() {
		return this.heartbeat;
	}

	public void setHeartbeat(String heartbeat) {
		this.heartbeat = heartbeat;
	}

	public String getMaintenanceUserId() {
		return this.maintenanceUserId;
	}

	public void setMaintenanceUserId(String maintenanceUserId) {
		this.maintenanceUserId = maintenanceUserId;
	}

	public String getMaintenanceState() {
		return this.maintenanceState;
	}

	public void setMaintenanceState(String maintenanceState) {
		this.maintenanceState = maintenanceState;
	}

	public Date getFoundTime() {
		return foundTime;
	}

	public void setFoundTime(Date foundTime) {
		this.foundTime = foundTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getFoundTime1() {
		return foundTime1;
	}

	public void setFoundTime1(String foundTime1) {
		this.foundTime1 = foundTime1;
	}

}