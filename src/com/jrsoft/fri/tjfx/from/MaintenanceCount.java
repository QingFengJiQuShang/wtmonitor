package com.jrsoft.fri.tjfx.from;

public class MaintenanceCount {
	private String province;			//省
	private String city;						//市
	private String area;					//区
	private String name;				//单位名称
	private int num; 					//电梯数量
	private int faultNum;					//故障数量
	private int wbkNum;						//维保卡数量
	private String wbkRate; 				//维保刷卡率
	private int peopleTrappedNum;//困人故障数量
	private String peopleTrappedRate; //困人率 
	
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getFaultNum() {
		return faultNum;
	}
	public void setFaultNum(int faultNum) {
		this.faultNum = faultNum;
	}
	public int getWbkNum() {
		return wbkNum;
	}
	public void setWbkNum(int wbkNum) {
		this.wbkNum = wbkNum;
	}
	public String getWbkRate() {
		return wbkRate;
	}
	public void setWbkRate(String wbkRate) {
		this.wbkRate = wbkRate;
	}
	public int getPeopleTrappedNum() {
		return peopleTrappedNum;
	}
	public void setPeopleTrappedNum(int peopleTrappedNum) {
		this.peopleTrappedNum = peopleTrappedNum;
	}
	public String getPeopleTrappedRate() {
		return peopleTrappedRate;
	}
	public void setPeopleTrappedRate(String peopleTrappedRate) {
		this.peopleTrappedRate = peopleTrappedRate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
