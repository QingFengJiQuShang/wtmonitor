package com.jrsoft.fri.dtjk.entity;


/**
 * �������ر�
 */

public class DtjkGateway implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;					//
	private String serialNumber;				//�������к�
	private String elevatorId;				//����Id
	private String type;				//������������
	private String hardware;				//Ӳ���汾
	private String software;				//����汾
	private String sim;				//SIM����
	private String report;				//�ϱ�����
	private String floor;				//��¥��
	private String upper; 			//����¥��
	private String lower; 			//����¥��
	private String speed;				//�趨�ٶ�
	private String spacing;				//ƽ����
	private String networking;				//������ʽ

	// Constructors

	/** default constructor */
	public DtjkGateway() {
	}

	/** full constructor */
	public DtjkGateway(String type, String hardware, String software,
			String sim, String report, String serialNumber, String ip,
			String port, String floor, String speed, String spacing,
			String networking,String elevatorId,String lower, String upper) {
		this.type = type;
		this.hardware = hardware;
		this.software = software;
		this.sim = sim;
		this.report = report;
		this.serialNumber = serialNumber;
		this.floor = floor;
		this.speed = speed;
		this.spacing = spacing;
		this.networking = networking;
		this.elevatorId=elevatorId;
		this.lower=lower;
		this.upper=upper;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHardware() {
		return this.hardware;
	}

	public void setHardware(String hardware) {
		this.hardware = hardware;
	}

	public String getSoftware() {
		return this.software;
	}

	public void setSoftware(String software) {
		this.software = software;
	}

	public String getSim() {
		return this.sim;
	}

	public void setSim(String sim) {
		this.sim = sim;
	}

	public String getReport() {
		return this.report;
	}

	public void setReport(String report) {
		this.report = report;
	}

	public String getSerialNumber() {
		return this.serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getFloor() {
		return this.floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getSpeed() {
		return this.speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public String getSpacing() {
		return this.spacing;
	}

	public void setSpacing(String spacing) {
		this.spacing = spacing;
	}

	public String getNetworking() {
		return this.networking;
	}

	public void setNetworking(String networking) {
		this.networking = networking;
	}

	public String getElevatorId() {
		return elevatorId;
	}

	public void setElevatorId(String elevatorId) {
		this.elevatorId = elevatorId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getLower() {
		return lower;
	}

	public void setLower(String lower) {
		this.lower = lower;
	}

	public String getUpper() {
		return upper;
	}

	public void setUpper(String upper) {
		this.upper = upper;
	}

}