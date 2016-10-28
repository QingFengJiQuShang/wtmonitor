package com.jrsoft.fri.dtjk.action;

import org.apache.struts.actions.DispatchAction;

import com.jrsoft.fri.dtjk.service.DtjkRecordService;

public class DtjkRecordAction extends DispatchAction {
	
	private DtjkRecordService recordService;

	public DtjkRecordService getRecordService() {
		return recordService;
	}

	public void setRecordService(DtjkRecordService recordService) {
		this.recordService = recordService;
	}
	
	

}
