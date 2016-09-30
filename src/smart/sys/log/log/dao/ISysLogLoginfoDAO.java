package smart.sys.log.log.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public interface ISysLogLoginfoDAO {
	public void save(Serializable entity);
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
	public Serializable saveLog(Serializable entity);
	/**
	 * 求日志归档总条数
	 * @return
	 */
	public int getArchCount();
}
