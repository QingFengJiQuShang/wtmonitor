package com.jrsoft.fri.xtgl.from;
import com.jrsoft.fri.common.core.form.BaseForm;
import com.jrsoft.fri.xtgl.entity.XtglMaintenanceRecords;
import com.jrsoft.fri.xtgl.entity.XtglUsers;
import com.jrsoft.fri.xtgl.entity.XtglYearlyInspection;

public class XtglForm  extends BaseForm{
	
	private  XtglUsers users=new XtglUsers();
	private  XtglMaintenanceRecords records=new XtglMaintenanceRecords();
	private  XtglYearlyInspection inspection=new XtglYearlyInspection();

	public XtglUsers getUsers() {
		return users;
	}

	public void setUsers(XtglUsers users) {
		this.users = users;
	}

	public XtglMaintenanceRecords getRecords() {
		return records;
	}

	public void setRecords(XtglMaintenanceRecords records) {
		this.records = records;
	}

	public XtglYearlyInspection getInspection() {
		return inspection;
	}

	public void setInspection(XtglYearlyInspection inspection) {
		this.inspection = inspection;
	}
}
