package com.jrsoft.fri.xtsz.entity;

/**
 * ϵͳ����
 */

public class XtszHelp implements java.io.Serializable {

	// Fields

	private Long id;			//id	
	private String title;				//����
	private String content;		//����

	// Constructors

	/** default constructor */
	public XtszHelp() {
	}

	/** full constructor */
	public XtszHelp(String title, String content) {
		this.title = title;
		this.content = content;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}