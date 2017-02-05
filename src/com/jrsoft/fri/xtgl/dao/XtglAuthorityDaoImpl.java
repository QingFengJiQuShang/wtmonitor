package com.jrsoft.fri.xtgl.dao;

import org.hibernate.Query;

import com.jrsoft.fri.common.core.dao.BaseDaoImpl;
import com.jrsoft.fri.xtgl.entity.XtglAuthority;
import com.jrsoft.fri.xtgl.entity.XtglUsers;

public class XtglAuthorityDaoImpl  extends BaseDaoImpl< XtglAuthority, String> implements  XtglAuthorityDao{

	@Override
	public Class<? extends Object> getEntityClass() {
		// TODO Auto-generated method stub
		return XtglAuthority.class;
	}
	
	/**
	 * 查看用户是否拥有key资源元素
	 * @param userId 用户ID
	 * @param key 资源元素key
	 * @return
	 */
	@Override
	public boolean getByUser(Long userId, String key) {
		String hql = " from XtglAuthority e where ( e.flag='0' or e.flag is null ) and e.usersId=:usersId and e.key=:key ";
		Query q = this.getSession(true).createQuery(hql);
		q.setLong("usersId", userId);
		q.setString("key", key);
		Object rs = q.uniqueResult();
		XtglAuthority user = null;
		if(rs!=null){
			user = (XtglAuthority)rs;
			return true;
		}else{
			return false;
		}
		
	}

	@Override
	public boolean getByMessage(String key) {
		String hql = " from XtglAuthority e where ( e.flag='1'  )  and e.key=:key ";
		Query q = this.getSession(true).createQuery(hql);
		q.setString("key", key);
		Object rs = q.uniqueResult();
		XtglAuthority user = null;
		if(rs!=null){
			user = (XtglAuthority)rs;
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean getByPush(String key) {
		String hql = " from XtglAuthority e where ( e.flag='2'  )  and e.key=:key ";
		Query q = this.getSession(true).createQuery(hql);
		q.setString("key", key);
		Object rs = q.uniqueResult();
		XtglAuthority user = null;
		if(rs!=null){
			user = (XtglAuthority)rs;
			return true;
		}else{
			return false;
		}
	}
}
