package com.jrsoft.fri.xtsz.entity;

import java.util.Date;

/**
 * XtszLog entity. @author MyEclipse Persistence Tools
 */

public class XtszLog implements java.io.Serializable {

	// Fields

	private Long id;
	private Date foundTime;			//操作时间
	private String userName;		//操作人
	private String content;			//操作日志
	private String flag;					//日志类型 0,通信日志 1，操作日志，2
	
	private String begintime;
	private String endtime;
	// Constructors

	/** default constructor */
	public XtszLog() {
	}

	/** full constructor */
	public XtszLog(Date foundTime, String userName, String content,String flag) {
		this.foundTime = foundTime;
		this.userName = userName;
		this.content = content;
		this.flag=flag;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFoundTime() {
		return this.foundTime;
	}

	public void setFoundTime(Date foundTime) {
		this.foundTime = foundTime;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getBegintime() {
		return begintime;
	}

	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

}