package com.jrsoft.fri.xtsz.from;

import com.jrsoft.fri.common.core.form.BaseForm;
import com.jrsoft.fri.xtsz.entity.XtszHelp;
import com.jrsoft.fri.xtsz.entity.XtszLog;
import com.jrsoft.fri.xtsz.entity.XtszMessage;

public class XtszFrom   extends BaseForm {
	private XtszLog log=new XtszLog();
	private XtszHelp help=new XtszHelp();
	private XtszMessage message=new  XtszMessage();

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
	
	

}
