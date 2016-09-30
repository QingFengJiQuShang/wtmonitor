package com.jrsoft.fri.xtgl.dao;

import org.hibernate.Query;
import com.jrsoft.fri.common.core.dao.BaseDaoImpl;
import com.jrsoft.fri.xtgl.entity.XtglUsers;

public class XtglUsersDaoImpl  extends BaseDaoImpl< XtglUsers, String> implements  XtglUsersDao{

	@Override
	public Class<? extends Object> getEntityClass() {
		// TODO Auto-generated method stub
		return  XtglUsers.class;
	}

	@Override
	public XtglUsers getUserByPasswd(String loginName, String passwd) {
		String hql = " from XtglUsers e where e.loginname=:loginName and e.password=:passwd ";
		Query q = this.getSession(true).createQuery(hql);
		q.setString("loginName", loginName);
		q.setString("passwd", passwd);
		Object rs = q.uniqueResult();
		XtglUsers user = null;
		if(rs!=null){
			user = (XtglUsers)rs;
		}
		
		return user;
	}
	

}
