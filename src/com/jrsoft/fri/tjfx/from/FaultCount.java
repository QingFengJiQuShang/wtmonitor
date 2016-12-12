package com.jrsoft.fri.tjfx.from;

//故障统计

public class FaultCount {
	private String faultType;		//故障类型
	private int faultNum;			//故障次数
	private int elevatorNum;		//电梯次数
	private String incidence;				//故障发生率
	
	
	
	public String getFaultType() {
		return faultType;
	}
	public void setFaultType(String faultType) {
		this.faultType = faultType;
	}
	public int getFaultNum() {
		return faultNum;
	}
	public void setFaultNum(int faultNum) {
		this.faultNum = faultNum;
	}
	public int getElevatorNum() {
		return elevatorNum;
	}
	public void setElevatorNum(int elevatorNum) {
		this.elevatorNum = elevatorNum;
	}
	public String getIncidence() {
		return incidence;
	}
	public void setIncidence(String incidence) {
		this.incidence = incidence;
	}
	
	
	
	

}
