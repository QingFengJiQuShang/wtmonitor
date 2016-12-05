package com.jrsoft.fri.xtsz.entity;

/**
 * 系统帮助
 */

public class XtszHelp implements java.io.Serializable {

	// Fields

	private Long id;			//id	
	private String title;				//标题
	private String content;		//内容

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