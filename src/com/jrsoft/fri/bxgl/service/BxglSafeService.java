package com.jrsoft.fri.bxgl.service;

import java.util.List;

import com.jrsoft.fri.bxgl.entity.BxglSafe;
import com.jrsoft.fri.bxgl.from.Safe;
import com.jrsoft.fri.bxgl.from.SafeUnit;
import com.jrsoft.fri.common.core.service.BaseService;

public interface BxglSafeService   extends BaseService<BxglSafe>{
	void exportSafe(String filePath,List<Safe> safe);
	void exportSafeUnit(String filePath,List<SafeUnit> unit );
}
