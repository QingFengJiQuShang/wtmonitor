package com.jrsoft.fri.xtsz.from;

import com.jrsoft.fri.common.core.form.BaseForm;
import com.jrsoft.fri.xtsz.entity.XtszLog;

public class XtszFrom   extends BaseForm {
	private XtszLog log=new XtszLog();

	public XtszLog getLog() {
		return log;
	}

	public void setLog(XtszLog log) {
		this.log = log;
	}
	
	

}
