package com.jrsoft.fri.common.core.service;

import java.io.Serializable;
import java.util.List;

import com.jrsoft.fri.common.utils.Page;

public interface BaseService<T,PK extends Serializable> {

	public T save(T entity);
	public T saveWithAtta(T entity , String tempOwnerId);
	public T saveorupdate(T entity);
	public T load(PK id);
	public T get(PK id);
	public T update(T entity);
	public void delete(PK ids);
	public List<T> query(Page page);
	public List<T> queryAll(String checkhql);
	public List<Object []> executeSQLQuery(final String sql);
	public List<T> queryHQL(String checkHql,int currentPage,int perPage);
	public List<Object []> executeSQLQuery(final String sql,final int currentPage,final int perPage);
}
