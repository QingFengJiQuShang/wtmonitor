package com.jrsoft.fri.tjfx.from;

//品牌统计
public class BrandCount {
	private String brand;			//电梯品牌（选择）
	private String model;			//电梯型号
	private int num; 					//品牌电梯数量
	private int faultNum;			//故障次数
	private String time;						//无故障运行时间
	private String incidence;				//故障发生率
	
	
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
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
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getIncidence() {
		return incidence;
	}
	public void setIncidence(String incidence) {
		this.incidence = incidence;
	}
	
	
	

}
