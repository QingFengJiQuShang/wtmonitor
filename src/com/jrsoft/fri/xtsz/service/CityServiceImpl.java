package com.jrsoft.fri.xtsz.service;

import com.jrsoft.fri.common.core.service.BaseServiceImpl;
import com.jrsoft.fri.xtsz.dao.CityDao;
import com.jrsoft.fri.xtsz.entity.City;

public class CityServiceImpl   extends BaseServiceImpl<City> implements CityService{
	private CityDao cityDao;

	public CityDao getCityDao() {
		return cityDao;
	}

	public void setCityDao(CityDao cityDao) {
		this.setBaseDao(cityDao);
		this.cityDao = cityDao;
	}

}
