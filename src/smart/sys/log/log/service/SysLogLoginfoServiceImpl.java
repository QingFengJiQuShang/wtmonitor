package smart.sys.log.log.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import smart.sys.log.log.dao.ISysLogLoginfoDAO;
import smart.sys.log.log.po.SysLogArchive;
import smart.sys.log.log.po.SysLogLoginfo;


public class SysLogLoginfoServiceImpl implements ISysLogLoginfoService{
	private ISysLogLoginfoDAO sysLogLoginfoDao;
	public ISysLogLoginfoDAO getSysLogLoginfoDao() {
		return sysLogLoginfoDao;
	}
	public void setSysLogLoginfoDao(ISysLogLoginfoDAO sysLogLoginfoDao) {
		this.sysLogLoginfoDao = sysLogLoginfoDao;
	}
	/**
	 * 添加日志
	 * @param type  类别(常量)：1：登录/注销日志,2：应用操作日志，3：后台操作日志，4：安全备份日志
	 * @param username  用户名称
	 * @param systemname  子系统名称
	 * @param operateDetail  操作内容描述
	 * @param userIp  IP地址(来自)
	 * @param descrip  描述
	 */
	public void saveLog(int type,String username,String systemname,String operateDetail,String userIp,String descrip){
		SysLogLoginfo log = new SysLogLoginfo();
		Date date = new Date();
		log.setOperateTime(date);
		log.setIp(userIp);
		log.setDescrip(descrip);
		log.setOperateDetail(operateDetail);
		log.setSystemname(systemname);
		log.setType(type);
		log.setUsername(username);
		log.setArchiveFlag(0);
		sysLogLoginfoDao.save(log);
	}
	/**
	 * 添加安全备份日志
	 * @param type  类别(常量)：1：登录/注销日志,2：应用操作日志，3：后台操作日志，4：安全备份日志
	 * @param username  用户名称
	 * @param systemname  策略名称
	 * @param flag  运行结果   0：成功  1：失败  
	 * @param userIp  IP地址(来自)
	 * @param descrip  文件地址
	 */
	public void saveLog(int type,String username,String systemname,int flag,String userIp,String descrip){
		SysLogLoginfo log = new SysLogLoginfo();
		Date date = new Date();
		log.setOperateTime(date);
		log.setIp(userIp);
		log.setDescrip(descrip);
		String operateDetail="";
		if(flag==0){
			operateDetail="成功";
		}else{
			operateDetail="失败";
		}
		log.setOperateDetail(operateDetail);
		log.setSystemname(systemname);
		log.setType(type);
		log.setUsername(username);
		log.setArchiveFlag(0);
		sysLogLoginfoDao.save(log);
	}
	/**
	 * 未归档的日志
	 * @param type
	 * @return
	 */
	public List findLogInfo(int type){
		return sysLogLoginfoDao.findLogInfo(type);
	}
	/**
	 * 最大时间
	 * @param ids
	 * @return
	 */
	public Date findMaxTime(String type){
		return sysLogLoginfoDao.findMaxTime(type);
	}
	/**
	 * 最小时间
	 * @param ids
	 * @return
	 */
	public Date findMinTime(String type){
		return sysLogLoginfoDao.findMinTime(type);
	}
	public void save(Serializable entity){
		sysLogLoginfoDao.save(entity);
	}
	/**
	 * 归档修改标识
	 * @param ids
	 * @param autoId
	 */
	public void updateFile(String type,String autoId){
		sysLogLoginfoDao.updateFile(type, autoId);
	}
	/**
	 * 日志归档(分页)
	 * @param start
	 * @param limit
	 * @return
	 */
	public List findLogArchive(int start,int limit){
		return sysLogLoginfoDao.findLogArchive(start,limit);
	}
	/**
	 * 通过archiveId查询日志记录
	 * @param archiveId
	 * @return
	 */
	public List findLogArchId(String[] archiveId){
		return sysLogLoginfoDao.findLogArchId(archiveId);
	}
	/**
	 * 未归档的日志(分页)
	 * @param type
	 * @return
	 */
	public List findLogList(int type,int start,int limit){
		return sysLogLoginfoDao.findLogList(type, start, limit);
	}
	/**
	 * 求总条数
	 * @param type
	 * @return
	 */
	public int getCount(int type){
		return sysLogLoginfoDao.getCount(type);
	}
	/**
	 * 归档
	 * @param arch
	 * @param type
	 */
	public void saveArchive(SysLogArchive arch,String type){
		sysLogLoginfoDao.saveLog(arch);
		sysLogLoginfoDao.updateFile(type, arch.getAutoId());
	}
	/**
	 * 求日志归档总条数
	 * @return
	 */
	public int getArchCount(){
		return sysLogLoginfoDao.getArchCount();
	}
}
