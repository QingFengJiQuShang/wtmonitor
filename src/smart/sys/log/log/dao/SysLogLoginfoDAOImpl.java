package smart.sys.log.log.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.dao.DataAccessException;

import smart.sys.log.log.po.SysLogLoginfo;
import smart.sys.platform.dao.BaseDAO;

public class SysLogLoginfoDAOImpl extends BaseDAO implements ISysLogLoginfoDAO{

	@Override
	public Class<? extends Object> getEntityClass() {
		// TODO Auto-generated method stub
		return SysLogLoginfo.class;
	}
	public void save(Serializable entity){
		try {
			 this.getHibernateTemplate().save(entity);
		} catch (DataAccessException e) { 
			e.printStackTrace();
		}
		
	}
	public Serializable saveLog(Serializable entity){
		try {
			 return this.getHibernateTemplate().save(entity);
		} catch (DataAccessException e) { 
			e.printStackTrace();
		}
		return entity;
		
	}
	/**
	 * δ�鵵����־
	 * @param type
	 * @return
	 */
	public List findLogInfo(int type){
		String hql = "from SysLogLoginfo where type="+type+" and archiveFlag=0 order by operateTime desc";
		List list = this.getHibernateTemplate().find(hql);
		return list;
	}
	/**
	 * δ�鵵����־(��ҳ)
	 * @param type
	 * @return
	 */
	public List findLogList(int type,int start,int limit){
		String hql = "from SysLogLoginfo where type="+type+" and archiveFlag=0 order by operateTime desc";
		Query  query = this.getSession(true).createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(limit);
		List list = query.list();
		return list;
	}
	/**
	 * ��������
	 * @param type
	 * @return
	 */
	public int getCount(int type){
		String hql = "select count(autoId) from SysLogLoginfo where type="+type+" and archiveFlag=0 order by operateTime desc";
		Query  query = this.getSession(true).createQuery(hql);
		int recCount = Integer.valueOf(query.list().get(0).toString());
		return recCount;
	}
	/**
	 * ���ʱ��
	 * @param ids
	 * @return
	 */
	public Date findMaxTime(String type){
		String hql = "select max(operate_Time) operateTime from Sys_Log_Loginfo e where type="+type+" and archive_Flag=0";
		Query q = this.getSession(true).createSQLQuery(hql);
		
		List list = q.list();
		Date operateTime = null;
		if(list.size()!=0){
			operateTime = (Date)list.get(0);
		}
		return operateTime;
	}
	/**
	 * ��Сʱ��
	 * @param ids
	 * @return
	 */
	public Date findMinTime(String type){
		String hql = "select min(operate_Time) operateTime from Sys_Log_Loginfo e where type="+type+" and archive_Flag=0";
		Query q = this.getSession(true).createSQLQuery(hql);
		List list = q.list();
		Date operateTime = null;
		if(list.size()!=0){
			operateTime = (Date)list.get(0);
		}
		return operateTime;
	}
	/**
	 * �鵵�޸ı�ʶ
	 * @param ids
	 * @param autoId
	 */
	public void updateFile(String type,String autoId){
		String hql = "update SysLogLoginfo e set e.archiveFlag='1',archiveId='"+autoId+"' where e.type="+type+" and e.archiveFlag=0";
		Query q = this.getSession(true).createQuery(hql);
		q.executeUpdate();
	}
	/**
	 * ��־�鵵(��ҳ)
	 * @param start
	 * @param limit
	 * @return
	 */
	public List findLogArchive(int start,int limit){
		String hql = "from SysLogArchive order by archiveTime desc";
		Query  query = this.getSession(true).createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(limit);
		List list = query.list();
		return list;
	}
	/**
	 * ����־�鵵������
	 * @param type
	 * @return
	 */
	public int getArchCount(){
		String hql = "select count(autoId) from SysLogArchive";
		Query  query = this.getSession(true).createQuery(hql);
		int recCount = Integer.valueOf(query.list().get(0).toString());
		return recCount;
	}
	/**
	 * ͨ��archiveId��ѯ��־��¼
	 * @param archiveId
	 * @return
	 */
	public List findLogArchId(String[] archiveId){
		String hql = " from SysLogLoginfo e where e.archiveId in(:archiveId)";
		Query q = this.getSession(true).createQuery(hql);
		q.setParameterList("archiveId", archiveId);
		List list = q.list();
		return list;
	}
}
