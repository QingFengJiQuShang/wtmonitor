package com.jrsoft.fri.bxgl.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javassist.expr.NewArray;

import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * BxglClaim entity. @author MyEclipse Persistence Tools
 */

public class BxglClaim implements java.io.Serializable {

	// ���������¼��

	private Long id;					//
	private BxglSafe safeId=new BxglSafe();					//���ռ�¼Id
	private String cause;					//�¹�ԭ��
	private Date happenTime;					//�¹ʷ���ʱ��
	private Date claimTime;					//����ʱ��
	private String money;					//������
	private String picturePath;					//Ȩ��֤����Ƭ

	// Constructors

	/** default constructor */
	public BxglClaim() {
	}

	/** full constructor */
	public BxglClaim(BxglSafe safeId, String cause, Date happenTime,
			Date claimTime, String money, String picturePath) {
		this.safeId = safeId;
		this.cause = cause;
		this.happenTime = happenTime;
		this.claimTime = claimTime;
		this.money = money;
		this.picturePath = picturePath;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BxglSafe getSafeId() {
		return this.safeId;
	}

	public void setSafeId(BxglSafe safeId) {
		this.safeId = safeId;
	}

	public String getCause() {
		return this.cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public Date getHappenTime() {
		return this.happenTime;
	}

	public void setHappenTime(Date happenTime) {
		this.happenTime = happenTime;
	}

	public Date getClaimTime() {
		return this.claimTime;
	}

	public void setClaimTime(Date claimTime) {
		this.claimTime = claimTime;
	}

	public String getMoney() {
		return this.money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getPicturePath() {
		return this.picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

}