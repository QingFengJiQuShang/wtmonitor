package com.jrsoft.fri.dagl.dao;

import org.hibernate.Query;

import com.jrsoft.fri.common.core.dao.BaseDaoImpl;
import com.jrsoft.fri.dagl.entity.DaxxElevator;

public class DaxxElevatorDaoImpl extends BaseDaoImpl< DaxxElevator, String> implements  DaxxElevatorDao{

	@Override
	public Class<? extends Object> getEntityClass() {
		// TODO Auto-generated method stub
		return  DaxxElevator.class;
	}


}
