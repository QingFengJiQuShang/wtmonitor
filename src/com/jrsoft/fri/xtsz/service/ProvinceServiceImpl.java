package com.jrsoft.fri.xtsz.service;

import com.jrsoft.fri.common.core.service.BaseServiceImpl;
import com.jrsoft.fri.xtsz.dao.ProvinceDao;
import com.jrsoft.fri.xtsz.entity.Province;

public class ProvinceServiceImpl  extends BaseServiceImpl<Province> implements ProvinceService{
	private ProvinceDao provinceDao;

	public ProvinceDao getProvinceDao() {
		return provinceDao;
	}

	public void setProvinceDao(ProvinceDao provinceDao) {
		this.setBaseDao(provinceDao);
		this.provinceDao = provinceDao;
	}
	
}
