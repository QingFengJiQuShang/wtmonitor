package com.jrsoft.fri.bxgl.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import com.jrsoft.fri.dtjk.entity.DtjkElevator;

/**
 * BxglSafe entity. @author MyEclipse Persistence Tools
 */

public class BxglSafe implements java.io.Serializable {

	// Fields

	private Long id;
	private DtjkElevator elevatorId=new DtjkElevator();//���յ���Id
	private Date startTime;					//��ʼʱ��
	private Date endTime;						//����ʱ��
	private String state;					//�Ƿ�����
	private String beneficiary;					//������
	private Long money;					//���ս��
	private String flag;					//��ʶ
	private String picturePath; 		//ͼƬ·��
	private String number; 				//������
	private String company;				//���չ�˾
	
	private int num;

	// Constructors

	/** default constructor */
	public BxglSafe() {
	}

	/** full constructor */
	public BxglSafe(DtjkElevator elevatorId, Date startTime,
			Date endTime, String state, String beneficiary,
			Long money, String flag,String picturePath,String number,String company) {
		this.elevatorId = elevatorId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.state = state;
		this.beneficiary = beneficiary;
		this.money = money;
		this.flag = flag;
		this.picturePath=picturePath;
		this.number=number;
		this.company=company;
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

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getBeneficiary() {
		return this.beneficiary;
	}

	public void setBeneficiary(String beneficiary) {
		this.beneficiary = beneficiary;
	}

	public Long getMoney() {
		return this.money;
	}

	public void setMoney(Long money) {
		this.money = money;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

}