package smart.sys.log.main;

import smart.sys.log.log.po.SysLogLoginfo;
import smart.sys.log.log.service.ISysLogLoginfoService;
import smart.sys.platform.springUtils.SpringBeanUtil;

public class SmartLog {
	private static ISysLogLoginfoService sysLogLoginfoService =(ISysLogLoginfoService)SpringBeanUtil.getBean("sysLogLoginfoService");
	/**
	 * ��¼/ע����־
	 */
	public final static Integer LOGIN = 1;
	/**
	 * Ӧ�ò�����־
	 */
	public final static Integer APP_OP = 2;
	/**
	 * ��̨������־
	 */
	public final static Integer PLATFORM_OP = 3;
	/**
	 * ��ȫ������־
	 */
	public final static Integer SECURITY = 4;
	public synchronized static boolean writeLog(SysLogLoginfo log){
		sysLogLoginfoService.save(log);
		return false;
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
	public synchronized static boolean writeLog(int type,String username,String systemname,String operateDetail,String userIp,String descrip){
		sysLogLoginfoService.saveLog(type, username, systemname, operateDetail, userIp, descrip);
		return false;
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
	public synchronized static boolean writeLog(int type,String username,String systemname,int flag,String userIp,String descrip){
		sysLogLoginfoService.saveLog(type, username, systemname, flag, userIp, descrip);
		return false;
	}
}
