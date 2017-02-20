package com.jrsoft.fri.tjfx.from;


//救援单位统计
public class RescueCount {
	private String province;			//省
	private String city;						//市
	private String area;					//区
	private String name;						//救援单位名称
	private String arriveTime;					//平均救援到达时间
	private String successTime;					//平均救援成功时间
	private int num;									//救援次数
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getArriveTime() {
		return arriveTime;
	}
	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}
	public String getSuccessTime() {
		return successTime;
	}
	public void setSuccessTime(String successTime) {
		this.successTime = successTime;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
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
	
	

}
