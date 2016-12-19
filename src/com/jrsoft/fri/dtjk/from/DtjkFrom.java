package com.jrsoft.fri.dtjk.from;

import com.jrsoft.fri.common.core.form.BaseForm;
import com.jrsoft.fri.dtjk.entity.DtjkElevator;
import com.jrsoft.fri.dtjk.entity.DtjkGateway;
import com.jrsoft.fri.dtjk.entity.DtjkMaintenanceRecords;
import com.jrsoft.fri.dtjk.entity.DtjkPhone;
import com.jrsoft.fri.dtjk.entity.DtjkPush;
import com.jrsoft.fri.dtjk.entity.DtjkRecord;
import com.jrsoft.fri.dtjk.entity.DtjkService;
import com.jrsoft.fri.dtjk.entity.DtjkYearlyInspection;

public class DtjkFrom   extends BaseForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  DtjkElevator elevator=new DtjkElevator();
	private  DtjkGateway gateway =new DtjkGateway();
	private DtjkMaintenanceRecords maintenanceRecords=new DtjkMaintenanceRecords();
	private DtjkRecord record=new DtjkRecord();
	private DtjkYearlyInspection inspection=new DtjkYearlyInspection();
	private DtjkPhone ph=new DtjkPhone();
	private DtjkPush push=new DtjkPush();
	private DtjkService service=new DtjkService();
	
	public DtjkElevator getElevator() {
		return elevator;
	}
	public void setElevator(DtjkElevator elevator) {
		this.elevator = elevator;
	}
	public DtjkGateway getGateway() {
		return gateway;
	}
	public void setGateway(DtjkGateway gateway) {
		this.gateway = gateway;
	}
	public DtjkMaintenanceRecords getMaintenanceRecords() {
		return maintenanceRecords;
	}
	public void setMaintenanceRecords(DtjkMaintenanceRecords maintenanceRecords) {
		this.maintenanceRecords = maintenanceRecords;
	}
	public DtjkRecord getRecord() {
		return record;
	}
	public void setRecord(DtjkRecord record) {
		this.record = record;
	}
	public DtjkYearlyInspection getInspection() {
		return inspection;
	}
	public void setInspection(DtjkYearlyInspection inspection) {
		this.inspection = inspection;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public DtjkPhone getPh() {
		return ph;
	}
	public void setPh(DtjkPhone ph) {
		this.ph = ph;
	}
	public DtjkPush getPush() {
		return push;
	}
	public void setPush(DtjkPush push) {
		this.push = push;
	}
	public DtjkService getService() {
		return service;
	}
	public void setService(DtjkService service) {
		this.service = service;
	}
	
}
