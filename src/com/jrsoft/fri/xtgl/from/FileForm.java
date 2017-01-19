package com.jrsoft.fri.xtgl.from;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class FileForm extends ActionForm {
	private FormFile theFile; //文件
	private FormFile companyFile;	//企业信息

	public FormFile getCompanyFile() {
		return companyFile;
	}

	public void setCompanyFile(FormFile companyFile) {
		this.companyFile = companyFile;
	}

	public FormFile getTheFile() {
		return theFile; 
	}

	public void setTheFile(FormFile theFile) {
		this.theFile = theFile;
	}
}
