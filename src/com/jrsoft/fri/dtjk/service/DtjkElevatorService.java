package com.jrsoft.fri.dtjk.service;

import com.jrsoft.fri.common.core.service.BaseService;
import com.jrsoft.fri.dtjk.entity.DtjkElevator;

public interface DtjkElevatorService   extends BaseService< DtjkElevator>{
	void export(String filePath,DtjkElevator elevator,String sql);
}
