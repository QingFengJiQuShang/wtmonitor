package com.jrsoft.fri.dtjk.service;

import com.jrsoft.fri.common.core.service.BaseServiceImpl;
import com.jrsoft.fri.dtjk.dao.DtjkElevatorDao;
import com.jrsoft.fri.dtjk.entity.DtjkElevator;

public class DtjkElevatorServiceImpl   extends BaseServiceImpl<DtjkElevator> implements DtjkElevatorService{
	private DtjkElevatorDao elevatorDao;

	public DtjkElevatorDao getElevatorDao() {
		return elevatorDao;
	}

	public void setElevatorDao(DtjkElevatorDao elevatorDao) {
		this.setBaseDao(elevatorDao);
		this.elevatorDao = elevatorDao;
	}

	@Override
	public void export(String filePath, DtjkElevator elevator) {
		elevatorDao.export(filePath, elevator);
		
	}

	
}