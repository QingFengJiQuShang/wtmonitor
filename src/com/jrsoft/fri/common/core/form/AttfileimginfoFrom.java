package com.jrsoft.fri.common.core.form;

import org.apache.struts.upload.FormFile;

public class AttfileimginfoFrom extends BaseForm{
	private String owner;
	private String ownerID;
	private Long ownerLevel;
	private String catalogID;
	private String fileName;
	private FormFile uploadFile;
	private String urlstr;
	private String tempOwnID;	// ¸½¼þ
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getOwnerID() {
		return ownerID;
	}
	public void setOwnerID(String ownerID) {
		this.ownerID = ownerID;
	}
	public Long getOwnerLevel() {
		return ownerLevel;
	}
	public void setOwnerLevel(Long ownerLevel) {
		this.ownerLevel = ownerLevel;
	}
	public String getCatalogID() {
		return catalogID;
	}
	public void setCatalogID(String catalogID) {
		this.catalogID = catalogID;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public FormFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(FormFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	public String getTempOwnID() {
		return tempOwnID;
	}
	public void setTempOwnID(String tempOwnID) {
		this.tempOwnID = tempOwnID;
	}
	public String getUrlstr() {
		return urlstr;
	}
	public void setUrlstr(String urlstr) {
		this.urlstr = urlstr;
	}

}
