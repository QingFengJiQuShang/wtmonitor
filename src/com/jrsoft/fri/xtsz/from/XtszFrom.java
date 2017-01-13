package com.jrsoft.fri.xtsz.from;

import com.jrsoft.fri.common.core.form.BaseForm;
import com.jrsoft.fri.xtsz.entity.Area;
import com.jrsoft.fri.xtsz.entity.City;
import com.jrsoft.fri.xtsz.entity.Province;
import com.jrsoft.fri.xtsz.entity.XtszDictionary;
import com.jrsoft.fri.xtsz.entity.XtszHelp;
import com.jrsoft.fri.xtsz.entity.XtszLog;
import com.jrsoft.fri.xtsz.entity.XtszMessage;

public class XtszFrom   extends BaseForm {
	private XtszLog log=new XtszLog();
	private XtszHelp help=new XtszHelp();
	private XtszMessage message=new  XtszMessage();
	private Area area=new  Area();
	private City city=new  City();
	private Province province=new  Province();
	private XtszDictionary dictionary=new XtszDictionary();

	public XtszLog getLog() {
		return log;
	}

	public void setLog(XtszLog log) {
		this.log = log;
	}

	public XtszHelp getHelp() {
		return help;
	}

	public void setHelp(XtszHelp help) {
		this.help = help;
	}

	public XtszMessage getMessage() {
		return message;
	}

	public void setMessage(XtszMessage message) {
		this.message = message;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public XtszDictionary getDictionary() {
		return dictionary;
	}

	public void setDictionary(XtszDictionary dictionary) {
		this.dictionary = dictionary;
	}
	
	

}
