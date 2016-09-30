package smart.sys.log.log.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public interface ISysLogLoginfoDAO {
	public void save(Serializable entity);
	/**
	 * δ�鵵����־
	 * @param type
	 * @return
	 */
	public List findLogInfo(int type);
	/**
	 * ���ʱ��
	 * @param ids
	 * @return
	 */
	public Date findMaxTime(String type);
	/**
	 * ��Сʱ��
	 * @param ids
	 * @return
	 */
	public Date findMinTime(String type);
	/**
	 * �鵵�޸ı�ʶ
	 * @param ids
	 * @param autoId
	 */
	public void updateFile(String type,String autoId);
	/**
	 * ��־�鵵(��ҳ)
	 * @param start
	 * @param limit
	 * @return
	 */
	public List findLogArchive(int start,int limit);
	/**
	 * ͨ��archiveId��ѯ��־��¼
	 * @param archiveId
	 * @return
	 */
	public List findLogArchId(String[] archiveId);
	/**
	 * δ�鵵����־(��ҳ)
	 * @param type
	 * @return
	 */
	public List findLogList(int type,int start,int limit);
	/**
	 * ��������
	 * @param type
	 * @return
	 */
	public int getCount(int type);
	public Serializable saveLog(Serializable entity);
	/**
	 * ����־�鵵������
	 * @return
	 */
	public int getArchCount();
}
