package com.jrsoft.fri.xtsz.entity;


/**
 * XtszDictionary entity. @author MyEclipse Persistence Tools
 */

public class XtszDictionary implements java.io.Serializable {

	//字典表

	private Long id;
	private String dictionary;			//字典名称
	private String flag;			//字典类型
	private String remarks;			//备注

	// Constructors

	/** default constructor */
	public XtszDictionary() {
	}

	/** full constructor */
	public XtszDictionary(String dictionary, String flag, String remarks) {
		this.dictionary = dictionary;
		this.flag = flag;
		this.remarks = remarks;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDictionary() {
		return this.dictionary;
	}

	public void setDictionary(String dictionary) {
		this.dictionary = dictionary;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}