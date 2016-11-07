package com.jrsoft.fri.common.core.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.jrsoft.fri.common.utils.Page;

import org.hibernate.Session;

@SuppressWarnings("unchecked")
public abstract class BaseDaoImpl <T, PK extends Serializable> extends HibernateDaoSupport implements BaseDao<T, PK>{

	public abstract Class<? extends Object> getEntityClass();
	
	@Override
	public T save(T entity) {
		try {
			this.getHibernateTemplate().save(entity);
			logger.debug("\u6301\u4e45\u5316(save)"+getEntityClass().getName()+"\u6210\u529f\uff01");	//持久化（save）XX成功
		} catch (DataAccessException e) {
			logger.debug("\u6301\u4e45\u5316(save)"+getEntityClass().getName()+"\u5931\u8d25\uff01");	//持久化（save）XX失败
			e.printStackTrace();
			throw new RuntimeException("\u4fdd\u5b58"+getEntityClass().getName()+"\u5931\u8d25\uff01",e);	//保存XX出错
		}
		return entity;
	}
	@Override
	public T saveorupdate(T entity) {
		try {
			this.getHibernateTemplate().saveOrUpdate(entity);
			logger.debug("\u6301\u4e45\u5316(saveOrUpdate)"+getEntityClass().getName()+"\u6210\u529f\uff01");	//持久化（save）XX成功
		} catch (DataAccessException e) {
			logger.debug("\u6301\u4e45\u5316(saveOrUpdate)"+getEntityClass().getName()+"\u5931\u8d25\uff01");	//持久化（save）XX失败
			e.printStackTrace();
			throw new RuntimeException("\u4fdd\u5b58"+getEntityClass().getName()+"\u5931\u8d25\uff01",e);	//保存XX出错
		}
		return entity;
	}

	@Override
	public T load(PK id) {
		T entity = null;
		try {
			entity = (T)this.getHibernateTemplate().load(getEntityClass(), id);
			logger.debug("BaseDAO\u52a0\u8f7d"+getEntityClass().getName()+" ID="+id+"\u6210\u529f\uff01");	//XX加载XX成功
		} catch (DataAccessException e) {
			logger.debug("\u52a0\u8f7d"+getEntityClass().getName()+" ID="+id+"\u5931\u8d25\uff01");	//加载XX失败
			e.printStackTrace();
			throw new RuntimeException("\u52a0\u8f7d"+getEntityClass().getName()+"\u5931\u8d25\uff01",e);	//加载XX出错
		}
		return entity;
	}

	@Override
	public T get(PK id) {
		T entity = null;
		try {
			entity = (T)this.getHibernateTemplate().get(getEntityClass(), id);
			logger.debug("BaseDAO\u52a0\u8f7d"+getEntityClass().getName()+" ID="+id+"\u6210\u529f\uff01");	//XX加载XX成功
		} catch (DataAccessException e) {
			logger.debug("\u52a0\u8f7d"+getEntityClass().getName()+" ID="+id+"\u5931\u8d25\uff01");	//加载XX失败
			e.printStackTrace();
			throw new RuntimeException("\u52a0\u8f7d"+getEntityClass().getName()+"\u5931\u8d25\uff01",e);	//加载XX出错
		}
		return entity;
	}
	
	@Override
	public T update(T entity) {
		try {
			this.getHibernateTemplate().update(entity);
			logger.debug("\u6301\u4e45\u5316(save)"+getEntityClass().getName()+"\u6210\u529f\uff01");	//持久化（save）XX成功
		} catch (DataAccessException e) {
			logger.debug("\u6301\u4e45\u5316(save)"+getEntityClass().getName()+"\u5931\u8d25\uff01");	//持久化（save）XX失败
			e.printStackTrace();
			throw new RuntimeException("\u4fdd\u5b58"+getEntityClass().getName()+"\u5931\u8d25\uff01",e);	//保存XX出错
		}
		return entity;
	}

	@Override
	public void delete(PK id) {
		try {
			this.getHibernateTemplate().delete(this.getHibernateTemplate().load(getEntityClass(), id));
			logger.debug("\u6301\u4e45\u5316(delete)"+getEntityClass().getName()+" ID="+id+"\u6210\u529f\uff01");	//持久化（delete）XX成功
		} catch (DataAccessException e) {
			logger.debug("\u6301\u4e45\u5316(delete)"+getEntityClass().getName()+" ID="+id+"\u5931\u8d25\uff01");	//持久化（delete）XX失败
			e.printStackTrace();
			throw new RuntimeException("\u5220\u9664"+getEntityClass().getName()+"\u5931\u8d25\uff01",e);		//删除XX出错
		}
	}

	@Override
	public List<T> query(String checkhql) {
		
		String hql=" from "+getEntityClass().getSimpleName()+checkhql;
		
		Query query = this.getSession(true).createQuery(hql);
		System.out.print("-----------"+getEntityClass().getSimpleName());
		List<T> list = query.list();
		return list;
	}
	
	/**
	 * * @param checkhql  例如：where plan_id='20129309883948s01a0009'
	 * */
	
	@Override
	public List<T> queryAll(String checkhql) {
		
		String hql=" from "+getEntityClass().getSimpleName()+checkhql;
		Query query = this.getSession(true).createQuery(hql);
		System.out.print("-----------"+getEntityClass().getSimpleName());
		List<T> list = query.list();
		return list;
	}
	
	/**
	 * @Description：返回数组的sql查询语句
	 * @parameter：String sql语句,Object param[] 参数数组
	 * @Return：int 数据
	 * @Warn：void
	 * */
	@Override
	public List<Object []> executeSQLQuery(final String sql){
		return (List<Object []>)this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {
				
				final SQLQuery query= session.createSQLQuery(sql);
				final List list=query.list();
				final Iterator it=list.iterator();
				final List<Object []> re=new ArrayList<Object []>();
				Object []obj=null;
				while(it.hasNext()){
					obj=(Object[])it.next();
					re.add(obj);
				}
				return re;
			}
		});
	}
	/**
	 * @Description：返回数组的sql查询语句
	 * @parameter：String sql语句,Object param[] 参数数组
	 * @Return：int 数据
	 * @Warn：void
	 * */
	public List<Object []> executeSQLQuery(final String sql,final int currentPage,final int perPage){
		return (List<Object []>)this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {
				
				final SQLQuery query= (SQLQuery) session.createSQLQuery(sql).setFirstResult((currentPage - 1) * perPage).setMaxResults(currentPage*perPage);
				final List list=query.list();
				final Iterator it=list.iterator();
				final List<Object []> re=new ArrayList<Object []>();
				Object []obj=null;
				while(it.hasNext()){
					obj=(Object[])it.next();
					re.add(obj);
				}
				return re;
			}
		});
	}
	
	public List<T> queryHQL(String hql,int currentPage,int perPage){
		
		List list=(List) super.getSession().createQuery(hql).setFirstResult((currentPage - 1) * perPage).setMaxResults(currentPage*perPage).list();
		System.out.println(list.size());
		return list;
	}
}
