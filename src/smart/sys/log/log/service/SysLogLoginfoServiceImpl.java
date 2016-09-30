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
	 * �����־
	 * @param type  ���(����)��1����¼/ע����־,2��Ӧ�ò�����־��3����̨������־��4����ȫ������־
	 * @param username  �û�����
	 * @param systemname  ��ϵͳ����
	 * @param operateDetail  ������������
	 * @param userIp  IP��ַ(����)
	 * @param descrip  ����
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
	 * ��Ӱ�ȫ������־
	 * @param type  ���(����)��1����¼/ע����־,2��Ӧ�ò�����־��3����̨������־��4����ȫ������־
	 * @param username  �û�����
	 * @param systemname  ��������
	 * @param flag  ���н��   0���ɹ�  1��ʧ��  
	 * @param userIp  IP��ַ(����)
	 * @param descrip  �ļ���ַ
	 */
	public void saveLog(int type,String username,String systemname,int flag,String userIp,String descrip){
		SysLogLoginfo log = new SysLogLoginfo();
		Date date = new Date();
		log.setOperateTime(date);
		log.setIp(userIp);
		log.setDescrip(descrip);
		String operateDetail="";
		if(flag==0){
			operateDetail="�ɹ�";
		}else{
			operateDetail="ʧ��";
		}
		log.setOperateDetail(operateDetail);
		log.setSystemname(systemname);
		log.setType(type);
		log.setUsername(username);
		log.setArchiveFlag(0);
		sysLogLoginfoDao.save(log);
	}
	/**
	 * δ�鵵����־
	 * @param type
	 * @return
	 */
	public List findLogInfo(int type){
		return sysLogLoginfoDao.findLogInfo(type);
	}
	/**
	 * ���ʱ��
	 * @param ids
	 * @return
	 */
	public Date findMaxTime(String type){
		return sysLogLoginfoDao.findMaxTime(type);
	}
	/**
	 * ��Сʱ��
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
	 * �鵵�޸ı�ʶ
	 * @param ids
	 * @param autoId
	 */
	public void updateFile(String type,String autoId){
		sysLogLoginfoDao.updateFile(type, autoId);
	}
	/**
	 * ��־�鵵(��ҳ)
	 * @param start
	 * @param limit
	 * @return
	 */
	public List findLogArchive(int start,int limit){
		return sysLogLoginfoDao.findLogArchive(start,limit);
	}
	/**
	 * ͨ��archiveId��ѯ��־��¼
	 * @param archiveId
	 * @return
	 */
	public List findLogArchId(String[] archiveId){
		return sysLogLoginfoDao.findLogArchId(archiveId);
	}
	/**
	 * δ�鵵����־(��ҳ)
	 * @param type
	 * @return
	 */
	public List findLogList(int type,int start,int limit){
		return sysLogLoginfoDao.findLogList(type, start, limit);
	}
	/**
	 * ��������
	 * @param type
	 * @return
	 */
	public int getCount(int type){
		return sysLogLoginfoDao.getCount(type);
	}
	/**
	 * �鵵
	 * @param arch
	 * @param type
	 */
	public void saveArchive(SysLogArchive arch,String type){
		sysLogLoginfoDao.saveLog(arch);
		sysLogLoginfoDao.updateFile(type, arch.getAutoId());
	}
	/**
	 * ����־�鵵������
	 * @return
	 */
	public int getArchCount(){
		return sysLogLoginfoDao.getArchCount();
	}
}
