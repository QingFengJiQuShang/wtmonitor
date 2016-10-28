package com.jrsoft.fri.gzcl.dao;

import com.jrsoft.fri.common.core.dao.BaseDaoImpl;
import com.jrsoft.fri.gzcl.entity.GzlcAlarm;

public class GzlcAlarmDaoImpl  extends BaseDaoImpl< GzlcAlarm, String> implements  GzlcAlarmDao{

	@Override
	public Class<? extends Object> getEntityClass() {
		// TODO Auto-generated method stub
		return  GzlcAlarm.class;
	}
}
