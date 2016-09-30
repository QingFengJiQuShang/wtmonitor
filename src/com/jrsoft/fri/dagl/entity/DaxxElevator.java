package com.jrsoft.fri.dagl.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * ���ݱ�
 */

public class DaxxElevator implements java.io.Serializable {

	// Fields

	private int id;
	private String registerid;			//����ע���
	private String distinguishid;		//ʶ����
	private String brand;		//����Ʒ��
	private String model;		//�����ͺ�
	private String state;		//ע��״̬
	private String type;		//��������
	private String numbers;		//�ܲ���
	private String lengths;		//����
	private String label;		//��ͼ��ע
	private String place;		//��װ�ص�
	private Date manufactureTime;		//��������
	private int gatewayId;		//��������id
	private int useUnitId;		//ʹ�õ�λid
	private int maintenanceUnitId;		//ά����λid
	private String yearlyState;		//���״̬
	private String maintenanceState;		//ά��״̬

	// Constructors

	/** default constructor */
	public DaxxElevator() {
	}

	/** full constructor */
	public DaxxElevator(String registerid, String distinguishid, String brand,
			String model, String state, String type, String numbers,
			String lengths, String label, String place,
			Date manufactureTime, int gatewayId,
			int useUnitId, int maintenanceUnitId,
			String yearlyState, String maintenanceState) {
		this.registerid = registerid;
		this.distinguishid = distinguishid;
		this.brand = brand;
		this.model = model;
		this.state = state;
		this.type = type;
		this.numbers = numbers;
		this.lengths = lengths;
		this.label = label;
		this.place = place;
		this.manufactureTime = manufactureTime;
		this.gatewayId = gatewayId;
		this.useUnitId = useUnitId;
		this.maintenanceUnitId = maintenanceUnitId;
		this.yearlyState = yearlyState;
		this.maintenanceState = maintenanceState;
	}

	// Property accessors

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNumbers() {
		return this.numbers;
	}

	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}

	public String getLengths() {
		return this.lengths;
	}

	public void setLengths(String lengths) {
		this.lengths = lengths;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getPlace() {
		return this.place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Date getManufactureTime() {
		return this.manufactureTime;
	}

	public void setManufactureTime(Date manufactureTime) {
		this.manufactureTime = manufactureTime;
	}

	public int getGatewayId() {
		return this.gatewayId;
	}

	public void setGatewayId(int gatewayId) {
		this.gatewayId = gatewayId;
	}

	public int getUseUnitId() {
		return this.useUnitId;
	}

	public void setUseUnitId(int useUnitId) {
		this.useUnitId = useUnitId;
	}

	public int getMaintenanceUnitId() {
		return this.maintenanceUnitId;
	}

	public void setMaintenanceUnitId(int maintenanceUnitId) {
		this.maintenanceUnitId = maintenanceUnitId;
	}

	public String getYearlyState() {
		return this.yearlyState;
	}

	public void setYearlyState(String yearlyState) {
		this.yearlyState = yearlyState;
	}

	public String getMaintenanceState() {
		return this.maintenanceState;
	}

	public void setMaintenanceState(String maintenanceState) {
		this.maintenanceState = maintenanceState;
	}

}