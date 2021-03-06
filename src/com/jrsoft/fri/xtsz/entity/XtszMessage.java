package com.jrsoft.fri.xtsz.entity;

import java.util.Date;

/**
 * XtszMessage entity. @author MyEclipse Persistence Tools
 */

public class XtszMessage implements java.io.Serializable {

	// Fields

	private Long id;
	private String phone;				//短信接收人
	private String name;				//短信接收人姓名
	private String state;					//发送状态
	private Date time;						//发送时间
	private String content;			//内容
	private Date foundTime;                //创建时间

	// Constructors

	/** default constructor */
	public XtszMessage() {
	}

	/** full constructor */
	public XtszMessage(String phone, String state, Date time,
			String content,String name) {
		this.phone = phone;
		this.name=name;
		this.state = state;
		this.time = time;
		this.content = content;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getFoundTime() {
		return foundTime;
	}

	public void setFoundTime(Date foundTime) {
		this.foundTime = foundTime;
	}

}