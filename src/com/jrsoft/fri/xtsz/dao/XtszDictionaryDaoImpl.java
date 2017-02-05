package com.jrsoft.fri.xtsz.dao;

import org.hibernate.Query;

import com.jrsoft.fri.common.core.dao.BaseDaoImpl;
import com.jrsoft.fri.xtgl.entity.XtglAuthority;
import com.jrsoft.fri.xtsz.entity.XtszDictionary;

public class XtszDictionaryDaoImpl  extends BaseDaoImpl< XtszDictionary, String> implements  XtszDictionaryDao{

	@Override
	public Class<? extends Object> getEntityClass() {
		// TODO Auto-generated method stub
		return XtszDictionary.class;
	}

	@Override
	public String getDictionary(String flag) {
		String hql = " from XtszDictionary e where ( e.flag=:flag )   ";
		Query q = this.getSession(true).createQuery(hql);
		q.setString("flag", flag);
		Object rs = q.uniqueResult();
		XtszDictionary user = null;
		if(rs!=null){
			user = (XtszDictionary)rs;
			return user.getDictionary();
		}else{
			return null;
		}
	}

}
