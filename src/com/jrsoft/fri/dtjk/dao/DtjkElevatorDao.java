package com.jrsoft.fri.dtjk.dao;

import com.jrsoft.fri.common.core.dao.BaseDao;
import com.jrsoft.fri.dtjk.entity.DtjkElevator;

public interface DtjkElevatorDao extends BaseDao<DtjkElevator, String>{
	void export(String filePath,DtjkElevator elevator,String sql);
}
