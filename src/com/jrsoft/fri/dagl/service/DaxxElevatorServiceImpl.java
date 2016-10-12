package com.jrsoft.fri.dagl.service;

import com.jrsoft.fri.common.core.service.BaseServiceImpl;
import com.jrsoft.fri.dagl.dao.DaxxElevatorDao;
import com.jrsoft.fri.dagl.entity.DaxxElevator;

public class DaxxElevatorServiceImpl   extends BaseServiceImpl<DaxxElevator> implements DaxxElevatorService{
	private DaxxElevatorDao elevatorDao;

	public DaxxElevatorDao getElevatorDao() {
		return elevatorDao;
	}

	public void setElevatorDao(DaxxElevatorDao elevatorDao) {
		this.setBaseDao(elevatorDao);
		this.elevatorDao = elevatorDao;
	}

	@Override
	public void export(String filePath, DaxxElevator elevator) {
		elevatorDao.export(filePath, elevator);
		
	}

	
}