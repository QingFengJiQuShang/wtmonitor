package com.jrsoft.fri.xtgl.from;
import com.jrsoft.fri.common.core.form.BaseForm;
import com.jrsoft.fri.xtgl.entity.XtglMaintenanceUnit;
import com.jrsoft.fri.xtgl.entity.XtglMaintenanceUsers;
import com.jrsoft.fri.xtgl.entity.XtglRescueUnit;
import com.jrsoft.fri.xtgl.entity.XtglUseUnit;
import com.jrsoft.fri.xtgl.entity.XtglUsers;

public class XtglForm  extends BaseForm{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  XtglUsers users=new XtglUsers();
	private  XtglMaintenanceUnit maintenanceUnit=new XtglMaintenanceUnit();
	private  XtglMaintenanceUsers maintenanceUsers=new XtglMaintenanceUsers(); 
	private  XtglRescueUnit rescueUnit=new XtglRescueUnit();
	private  XtglUseUnit unit=new XtglUseUnit();
	
	public XtglUsers getUsers() {
		return users;
	}

	public void setUsers(XtglUsers users) {
		this.users = users;
	}

	public XtglMaintenanceUnit getMaintenanceUnit() {
		return maintenanceUnit;
	}

	public void setMaintenanceUnit(XtglMaintenanceUnit maintenanceUnit) {
		this.maintenanceUnit = maintenanceUnit;
	}

	public XtglMaintenanceUsers getMaintenanceUsers() {
		return maintenanceUsers;
	}

	public void setMaintenanceUsers(XtglMaintenanceUsers maintenanceUsers) {
		this.maintenanceUsers = maintenanceUsers;
	}

	public XtglRescueUnit getRescueUnit() {
		return rescueUnit;
	}

	public void setRescueUnit(XtglRescueUnit rescueUnit) {
		this.rescueUnit = rescueUnit;
	}

	public XtglUseUnit getUnit() {
		return unit;
	}

	public void setUnit(XtglUseUnit unit) {
		this.unit = unit;
	}
	
}
