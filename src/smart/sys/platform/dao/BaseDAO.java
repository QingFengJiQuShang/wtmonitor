package smart.sys.platform.dao;

import java.io.Serializable;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import smart.sys.platform.log.Log4jHelper;

public abstract class BaseDAO extends HibernateDaoSupport{
	private Log4jHelper logger = new Log4jHelper(BaseDAO.class);
	
	public abstract Class<? extends Object> getEntityClass();
	
	public Serializable load(String id){
		Serializable po = null;
		try {
			po = (Serializable)this.getHibernateTemplate().load(getEntityClass(), id);
			logger.debug("BaseDAO\u52a0\u8f7d"+getEntityClass().getName()+" ID="+id+"\u6210\u529f\uff01");	//XX加载XX成功
		} catch (DataAccessException e) {
			logger.debug("\u52a0\u8f7d"+getEntityClass().getName()+" ID="+id+"\u5931\u8d25\uff01");	//加载XX失败
			e.printStackTrace();
			throw new RuntimeException("\u52a0\u8f7d"+getEntityClass().getName()+"\u5931\u8d25\uff01",e);	//加载XX出错
		}
		return po;
	}
	public Serializable get(String id){
		Serializable po = null;
		try {
			po = (Serializable)this.getHibernateTemplate().get(getEntityClass(), id);
			logger.debug("BaseDAO\u52a0\u8f7d"+getEntityClass().getName()+" ID="+id+"\u6210\u529f\uff01");	//XX加载XX成功
		} catch (DataAccessException e) {
			logger.debug("\u52a0\u8f7d"+getEntityClass().getName()+" ID="+id+"\u5931\u8d25\uff01");	//加载XX失败
			e.printStackTrace();
			throw new RuntimeException("\u52a0\u8f7d"+getEntityClass().getName()+"\u5931\u8d25\uff01",e);	//加载XX出错
		}
		return po;
	}
	
	public void save(Serializable entity){
		try {
			this.getHibernateTemplate().save(entity);
			logger.debug("\u6301\u4e45\u5316(save)"+getEntityClass().getName()+"\u6210\u529f\uff01");	//持久化（save）XX成功
		} catch (DataAccessException e) {
			logger.debug("\u6301\u4e45\u5316(save)"+getEntityClass().getName()+"\u5931\u8d25\uff01");	//持久化（save）XX失败
			e.printStackTrace();
			throw new RuntimeException("\u4fdd\u5b58"+getEntityClass().getName()+"\u5931\u8d25\uff01",e);	//保存XX出错
		}
		
	}
	
	public void update(Serializable entity){
		try {
			this.getHibernateTemplate().update(entity);
			logger.debug("\u6301\u4e45\u5316(update)"+getEntityClass().getName()+"\u6210\u529f\uff01");	//持久化（update）XX成功
		} catch (DataAccessException e) {
			logger.debug("\u6301\u4e45\u5316(update)"+getEntityClass().getName()+"\u5931\u8d25\uff01");	//持久化（update）XX失败
			e.printStackTrace();
			throw new RuntimeException("\u4fee\u6539"+getEntityClass().getName()+"\u5931\u8d25\uff01",e.getRootCause());	//修改XX出错
		}
	}
	
	public void delete(String id){
		try {
			this.getHibernateTemplate().delete(this.getHibernateTemplate().load(getEntityClass(), id));
			logger.debug("\u6301\u4e45\u5316(delete)"+getEntityClass().getName()+" ID="+id+"\u6210\u529f\uff01");	//持久化（delete）XX成功
		} catch (DataAccessException e) {
			logger.debug("\u6301\u4e45\u5316(delete)"+getEntityClass().getName()+" ID="+id+"\u5931\u8d25\uff01");	//持久化（delete）XX失败
			e.printStackTrace();
			throw new RuntimeException("\u5220\u9664"+getEntityClass().getName()+"\u5931\u8d25\uff01",e);		//删除XX出错
		}
	}
	
}