package com.jrsoft.fri.bxgl.dao;

import java.util.List;

import com.jrsoft.fri.bxgl.entity.BxglSafe;
import com.jrsoft.fri.bxgl.from.Safe;
import com.jrsoft.fri.bxgl.from.SafeUnit;
import com.jrsoft.fri.common.core.dao.BaseDao;

public interface BxglSafeDao  extends BaseDao<BxglSafe, String>{
	void exportSafe(String filePath,List<Safe> safe);
	void exportSafeUnit(String filePath,List<SafeUnit> unit );
}
