package smart.sys.log.log.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import smart.sys.log.log.po.SysLogArchive;

public interface ISysLogLoginfoService {
	/**
	 * �����־
	 * @param type  ���(����)��1����¼/ע����־,2��Ӧ�ò�����־��3����̨������־��4����ȫ������־
	 * @param username  �û�����
	 * @param systemname  ��ϵͳ����
	 * @param operateDetail  ������������
	 * @param userIp  IP��ַ(����)
	 * @param descrip  ����
	 */
	public void saveLog(int type,String username,String systemname,String operateDetail,String userIp,String descrip);
	/**
	 * ��Ӱ�ȫ������־
	 * @param type  ���(����)��1����¼/ע����־,2��Ӧ�ò�����־��3����̨������־��4����ȫ������־
	 * @param username  �û�����
	 * @param systemname  ��������
	 * @param flag  ���н��   0���ɹ�  1��ʧ��  
	 * @param userIp  IP��ַ(����)
	 * @param descrip  �ļ���ַ
	 */
	public void saveLog(int type,String username,String systemname,int flag,String userIp,String descrip);
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
	public void save(Serializable entity);
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
	/**
	 * �鵵
	 * @param arch
	 * @param type
	 */
	public void saveArchive(SysLogArchive arch,String type);
	/**
	 * ����־�鵵������
	 * @return
	 */
	public int getArchCount();
}
