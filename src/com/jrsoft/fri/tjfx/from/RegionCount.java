package com.jrsoft.fri.tjfx.from;

/**
 * 区域统计
 * @author Administrator
 *
 */
public class RegionCount {
	private String province;			//省
	private String city;						//市
	private String area;					//区
	private int faultNum;					//故障数量
	private int alarmNum;					//人工接警数量
	private int automaticNum;		//自动接警数量
	private int peopleTrappedNum;//困人故障数量
	private String peopleTrappedRate; //困人率
	private int otherNum;						//其它故障数量
	
	
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
	public int getFaultNum() {
		return faultNum;
	}
	public void setFaultNum(int faultNum) {
		this.faultNum = faultNum;
	}
	public int getAlarmNum() {
		return alarmNum;
	}
	public void setAlarmNum(int alarmNum) {
		this.alarmNum = alarmNum;
	}
	public int getAutomaticNum() {
		return automaticNum;
	}
	public void setAutomaticNum(int automaticNum) {
		this.automaticNum = automaticNum;
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
	public int getOtherNum() {
		return otherNum;
	}
	public void setOtherNum(int otherNum) {
		this.otherNum = otherNum;
	}
	
	
	

}
