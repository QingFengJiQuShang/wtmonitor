package com.jrsoft.fri.dtjk.from;

import com.jrsoft.fri.common.core.form.BaseForm;
import com.jrsoft.fri.dtjk.entity.DtjkElevator;
import com.jrsoft.fri.dtjk.entity.DtjkGateway;
import com.jrsoft.fri.dtjk.entity.DtjkMaintenanceRecords;
import com.jrsoft.fri.dtjk.entity.DtjkRecord;
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
	
	
	

}
