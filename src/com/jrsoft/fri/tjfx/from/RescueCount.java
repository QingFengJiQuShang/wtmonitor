package com.jrsoft.fri.tjfx.from;


//救援单位统计
public class RescueCount {
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
	
	

}
