package com.jrsoft.fri.dtjk.from;

public class Index {
	private int normalNum;			//正常电梯数量
	private int offLineNum;			//离线电梯数量
	private int faultNum;				//故障电梯数量
	private int maintenanceNum;//维保过期电梯数量
	private int yearlyNum;				//未年检电梯数量
	
	
	
	public int getNormalNum() {
		return normalNum;
	}
	public void setNormalNum(int normalNum) {
		this.normalNum = normalNum;
	}
	public int getOffLineNum() {
		return offLineNum;
	}
	public void setOffLineNum(int offLineNum) {
		this.offLineNum = offLineNum;
	}
	public int getFaultNum() {
		return faultNum;
	}
	public void setFaultNum(int faultNum) {
		this.faultNum = faultNum;
	}
	public int getMaintenanceNum() {
		return maintenanceNum;
	}
	public void setMaintenanceNum(int maintenanceNum) {
		this.maintenanceNum = maintenanceNum;
	}
	public int getYearlyNum() {
		return yearlyNum;
	}
	public void setYearlyNum(int yearlyNum) {
		this.yearlyNum = yearlyNum;
	}
	
	
	

}
