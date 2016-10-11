package com.jrsoft.fri.dagl.from;

import com.jrsoft.fri.common.core.form.BaseForm;
import com.jrsoft.fri.dagl.entity.DaxxElevator;
import com.jrsoft.fri.dagl.entity.DaxxGateway;
import com.jrsoft.fri.dagl.entity.DaxxMaintenanceUnit;
import com.jrsoft.fri.dagl.entity.DaxxMaintenanceUsers;
import com.jrsoft.fri.dagl.entity.DaxxRegion;
import com.jrsoft.fri.dagl.entity.DaxxUseUnit;

public class DaglFrom   extends BaseForm {
	private  DaxxElevator elevator=new DaxxElevator();
	private  DaxxGateway gateway =new DaxxGateway();
	private  DaxxMaintenanceUnit maintenanceUnit =new DaxxMaintenanceUnit();
	private  DaxxMaintenanceUsers maintenanceUsers =new DaxxMaintenanceUsers();	
	private  DaxxRegion region=new DaxxRegion();
	private  DaxxUseUnit useUnit=new DaxxUseUnit();
	
	public DaxxElevator getElevator() {
		return elevator;
	}
	public void setElevator(DaxxElevator elevator) {
		this.elevator = elevator;
	}
	public DaxxGateway getGateway() {
		return gateway;
	}
	public void setGateway(DaxxGateway gateway) {
		this.gateway = gateway;
	}
	public DaxxMaintenanceUnit getMaintenanceUnit() {
		return maintenanceUnit;
	}
	public void setMaintenanceUnit(DaxxMaintenanceUnit maintenanceUnit) {
		this.maintenanceUnit = maintenanceUnit;
	}
	public DaxxRegion getRegion() {
		return region;
	}
	public void setRegion(DaxxRegion region) {
		this.region = region;
	}
	public DaxxUseUnit getUseUnit() {
		return useUnit;
	}
	public void setUseUnit(DaxxUseUnit useUnit) {
		this.useUnit = useUnit;
	}
	public DaxxMaintenanceUsers getMaintenanceUsers() {
		return maintenanceUsers;
	}
	public void setMaintenanceUsers(DaxxMaintenanceUsers maintenanceUsers) {
		this.maintenanceUsers = maintenanceUsers;
	}
	
	

}
