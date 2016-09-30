package com.jrsoft.fri.common.core.service;

import java.io.Serializable;
import java.util.List;
import com.jrsoft.fri.common.utils.ReflectUtil;
import smart.sys.platform.springUtils.SpringBeanUtil;

import com.jrsoft.fri.common.core.dao.BaseDao;
import com.jrsoft.fri.common.utils.Page;

public class BaseServiceImpl<T,PK extends Serializable> implements BaseService<T, PK> {

	private BaseDao baseDao = null;
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public T save(T entity) {
		
		return (T)baseDao.save(entity);
	}
	
	@Override
	public T saveWithAtta(T entity, String tempOwnerId) {
		T t = (T)baseDao.save(entity);
		String autoId = ReflectUtil.getFieldValue(t, "getAutoId");
	//	IAttachmentInfoDAO attachmentInfoDao = (AttachmentInfoDAOImpl)SpringBeanUtil.getBean("attachmentInfoDao");
	//	attachmentInfoDao.updateOwnerId(tempOwnerId, autoId);
		
		return t;
	}

	@Override
	public T saveorupdate(T entity) {
		
		return (T)baseDao.saveorupdate(entity);
	}

	@Override
	public T get(PK id) {
		
		return (T)baseDao.get(id);
	}

	@Override
	public T load(PK id) {
		
		return (T)baseDao.load(id);
	}

	@Override
	public T update(T entity) {
		
		return (T)baseDao.update(entity);
	}

	@Override
	public void delete(PK ids) {
		String[] idsArr = ((String)ids).split(",");
		for(String id : idsArr){
			baseDao.delete(id);
		}
	}

	@Override
	public List<T> query(Page page) {
		return (List<T>)baseDao.query(page);
	}

	@Override
	public List<T> queryAll(String checkhql) {
		// TODO Auto-generated method stub
		System.out.println(baseDao);
		return (List<T>)baseDao.queryAll(checkhql);
	}

	@Override
	public List<Object[]> executeSQLQuery(String sql) {
		// TODO Auto-generated method stub
		return baseDao.executeSQLQuery(sql);
	}

	@Override
	public List<T> queryHQL(String checkHql, int currentPage,int perPage) {
		// TODO Auto-generated method stub
		return baseDao.queryHQL(checkHql, currentPage, perPage);
	}

	@Override
	public List<Object[]> executeSQLQuery(String sql, int currentPage,
			int perPage) {
		// TODO Auto-generated method stub
		return baseDao.executeSQLQuery(sql, currentPage, perPage);
	}

}
