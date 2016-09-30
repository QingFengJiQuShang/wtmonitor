package smart.sys.log.log.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import smart.sys.log.log.po.SysLogArchive;

public interface ISysLogLoginfoService {
	/**
	 * 添加日志
	 * @param type  类别(常量)：1：登录/注销日志,2：应用操作日志，3：后台操作日志，4：安全备份日志
	 * @param username  用户名称
	 * @param systemname  子系统名称
	 * @param operateDetail  操作内容描述
	 * @param userIp  IP地址(来自)
	 * @param descrip  描述
	 */
	public void saveLog(int type,String username,String systemname,String operateDetail,String userIp,String descrip);
	/**
	 * 添加安全备份日志
	 * @param type  类别(常量)：1：登录/注销日志,2：应用操作日志，3：后台操作日志，4：安全备份日志
	 * @param username  用户名称
	 * @param systemname  策略名称
	 * @param flag  运行结果   0：成功  1：失败  
	 * @param userIp  IP地址(来自)
	 * @param descrip  文件地址
	 */
	public void saveLog(int type,String username,String systemname,int flag,String userIp,String descrip);
	/**
	 * 未归档的日志
	 * @param type
	 * @return
	 */
	public List findLogInfo(int type);
	/**
	 * 最大时间
	 * @param ids
	 * @return
	 */
	public Date findMaxTime(String type);
	/**
	 * 最小时间
	 * @param ids
	 * @return
	 */
	public Date findMinTime(String type);
	public void save(Serializable entity);
	/**
	 * 归档修改标识
	 * @param ids
	 * @param autoId
	 */
	public void updateFile(String type,String autoId);
	/**
	 * 日志归档(分页)
	 * @param start
	 * @param limit
	 * @return
	 */
	public List findLogArchive(int start,int limit);
	/**
	 * 通过archiveId查询日志记录
	 * @param archiveId
	 * @return
	 */
	public List findLogArchId(String[] archiveId);
	/**
	 * 未归档的日志(分页)
	 * @param type
	 * @return
	 */
	public List findLogList(int type,int start,int limit);
	/**
	 * 求总条数
	 * @param type
	 * @return
	 */
	public int getCount(int type);
	/**
	 * 归档
	 * @param arch
	 * @param type
	 */
	public void saveArchive(SysLogArchive arch,String type);
	/**
	 * 求日志归档总条数
	 * @return
	 */
	public int getArchCount();
}
