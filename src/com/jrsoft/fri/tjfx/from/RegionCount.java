package com.jrsoft.fri.tjfx.from;

/**
 * ����ͳ��
 * @author Administrator
 *
 */
public class RegionCount {
	private String province;			//ʡ
	private String city;						//��
	private String area;					//��
	private int faultNum;					//��������
	private int alarmNum;					//�˹��Ӿ�����
	private int automaticNum;		//�Զ��Ӿ�����
	private int peopleTrappedNum;//���˹�������
	private String peopleTrappedRate; //������
	private int otherNum;						//������������
	
	
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
