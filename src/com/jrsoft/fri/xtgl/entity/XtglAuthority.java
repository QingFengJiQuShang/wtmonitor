package com.jrsoft.fri.xtgl.entity;


/**
 * ϵͳȨ��
 */

public class XtglAuthority implements java.io.Serializable {

	// Fields

	private Long id;			//id
	private Long usersId;			//�û�id
	private String key;			//Ȩ��Ԫ��
	private String flag;       //Ȩ������ 0����Ȩ�ޣ�1����Ȩ��

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