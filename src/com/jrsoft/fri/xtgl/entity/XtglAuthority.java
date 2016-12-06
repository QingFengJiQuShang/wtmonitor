package com.jrsoft.fri.xtgl.entity;


/**
 * 系统权限
 */

public class XtglAuthority implements java.io.Serializable {

	// Fields

	private Long id;			//id
	private Long usersId;			//用户id
	private String key;			//权限元素
	private String flag;       //权限类型 0操作权限，1短信权限

	// Constructors

	/** default constructor */
	public XtglAuthority() {
	}

	/** full constructor */
	public XtglAuthority(Long usersId, String key,String flag) {
		this.usersId = usersId;
		this.key = key;
		this.flag=flag;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUsersId() {
		return this.usersId;
	}

	public void setUsersId(Long usersId) {
		this.usersId = usersId;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}