package com.jrsoft.fri.dtjk.from;

import com.jrsoft.fri.dtjk.entity.DtjkElevator;
import com.jrsoft.fri.dtjk.entity.DtjkGateway;
import com.jrsoft.fri.dtjk.entity.DtjkRecord;

public class Control {
	private DtjkElevator elevator=new DtjkElevator();
	private DtjkRecord record=new DtjkRecord();
	private DtjkGateway gateway=new DtjkGateway();
	
	public DtjkElevator getElevator() {
		return elevator;
	}
	public void setElevator(DtjkElevator elevator) {
		this.elevator = elevator;
	}
	public DtjkRecord getRecord() {
		return record;
	}
	public void setRecord(DtjkRecord record) {
		this.record = record;
	}
	public DtjkGateway getGateway() {
		return gateway;
	}
	public void setGateway(DtjkGateway gateway) {
		this.gateway = gateway;
	}
	
	
	

}
