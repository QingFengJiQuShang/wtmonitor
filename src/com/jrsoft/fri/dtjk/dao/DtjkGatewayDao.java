package com.jrsoft.fri.dtjk.dao;

import com.jrsoft.fri.common.core.dao.BaseDao;
import com.jrsoft.fri.dtjk.entity.DtjkGateway;

public interface DtjkGatewayDao  extends BaseDao<DtjkGateway, String>{
	 void export(String filePath, DtjkGateway gateway) ;
}
