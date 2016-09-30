package smart.sys.log.main;

import smart.sys.log.log.po.SysLogLoginfo;
import smart.sys.log.log.service.ISysLogLoginfoService;
import smart.sys.platform.springUtils.SpringBeanUtil;

public class SmartLog {
	private static ISysLogLoginfoService sysLogLoginfoService =(ISysLogLoginfoService)SpringBeanUtil.getBean("sysLogLoginfoService");
	/**
	 * 登录/注销日志
	 */
	public final static Integer LOGIN = 1;
	/**
	 * 应用操作日志
	 */
	public final static Integer APP_OP = 2;
	/**
	 * 后台操作日志
	 */
	public final static Integer PLATFORM_OP = 3;
	/**
	 * 安全备份日志
	 */
	public final static Integer SECURITY = 4;
	public synchronized static boolean writeLog(SysLogLoginfo log){
		sysLogLoginfoService.save(log);
		return false;
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
	public synchronized static boolean writeLog(int type,String username,String systemname,String operateDetail,String userIp,String descrip){
		sysLogLoginfoService.saveLog(type, username, systemname, operateDetail, userIp, descrip);
		return false;
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
	public synchronized static boolean writeLog(int type,String username,String systemname,int flag,String userIp,String descrip){
		sysLogLoginfoService.saveLog(type, username, systemname, flag, userIp, descrip);
		return false;
	}
}
