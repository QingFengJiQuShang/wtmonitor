package com.jrsoft.fri.xtsz.action;

import java.util.Date;
import java.util.List;

import smart.sys.platform.springUtils.SpringBeanUtil;
import com.jrsoft.fri.dtjk.entity.DtjkElevator;
import com.jrsoft.fri.dtjk.service.DtjkElevatorService;
import com.jrsoft.fri.xtgl.action.Authority;
import com.jrsoft.fri.xtgl.entity.XtglMaintenanceUsers;
import com.jrsoft.fri.xtgl.entity.XtglUseUnit;
import com.jrsoft.fri.xtgl.entity.XtglUsers;
import com.jrsoft.fri.xtgl.service.XtglMaintenanceUsersService;
import com.jrsoft.fri.xtgl.service.XtglUseUnitService;
import com.jrsoft.fri.xtgl.service.XtglUsersService;
import com.jrsoft.fri.xtsz.entity.XtszDictionary;
import com.jrsoft.fri.xtsz.entity.XtszMessage;
import com.jrsoft.fri.xtsz.service.XtszDictionaryService;
import com.jrsoft.fri.xtsz.service.XtszMessageService;

public class Message {
	private static XtszMessageService messageService = (XtszMessageService)SpringBeanUtil.getBean("messageService");
	private static DtjkElevatorService elevatorService = (DtjkElevatorService)SpringBeanUtil.getBean("elevatorService");
	private static XtglUseUnitService useUnitService = (XtglUseUnitService)SpringBeanUtil.getBean("useUnitService");
	private static XtglMaintenanceUsersService maintenanceUsersService = (XtglMaintenanceUsersService)SpringBeanUtil.getBean("maintenanceUsersService");
	private static XtglUsersService usersService = (XtglUsersService)SpringBeanUtil.getBean("usersService");
	private static XtszDictionaryService dictionaryService = (XtszDictionaryService)SpringBeanUtil.getBean("dictionaryService");


	
	/**
	 * 接到报警命令后，自动生成短信
	 * type 报警命令
	 * type 解析后的命令
	 * elevators 报警电梯信息
	 */
	public static void addMessage(String type,String order,DtjkElevator elevators){
		XtszMessage message=new XtszMessage();
		DtjkElevator list=elevatorService.get(elevators.getId());
		String hql=" where flag='4' and remarks='故障' ";
		XtszDictionary dictionaries=dictionaryService.query(hql).get(0);
		String str="";
		if(dictionaries!=null){
			str=dictionaries.getDictionary();
			
			str=str.replace("{place}",elevators.getInstallPlace());
			str=str.replace("{type}",order);
		}
		
		//判断短信权限   使用单位
		if(Authority.haveMessage("sy_"+type)){
			message.setState("未发送");
			XtglUseUnit useUnit=useUnitService.get(list.getUseUnitId().getId());
			if(useUnit!=null){
				str=str.replace("{name}",useUnit.getLiaisons());
				message.setName(useUnit.getLiaisons());
				message.setPhone(useUnit.getPhone());
				message.setContent(str);
				messageService.save(message);
			}
			//判断是否拥有自动发送短信权限
			if(Authority.haveMessage("sy_00")){
				sendMessage(message.getId());
			}
		}
		//判断短信权限   维保单位
		if(Authority.haveMessage("wb_"+type)){
			message.setState("未发送");
			XtglMaintenanceUsers useUnit=maintenanceUsersService.get(list.getMaintenanceUnitId().getId());
			if(useUnit!=null){
				str=str.replace("{name}",useUnit.getName());
				message.setName(useUnit.getName());
				message.setPhone(useUnit.getPhone());
				message.setContent(str);
				messageService.save(message);
			}
			
			//判断是否拥有自动发送短信权限
			if(Authority.haveMessage("wb_00")){
				sendMessage(message.getId());
			}
		}
		
		//判断短信权限   系统用户
		if(Authority.haveMessage("xt_"+type)){
			message.setState("未发送");
			XtglUsers useUnit=usersService.get(list.getUserid().getId());
			if(useUnit!=null){
				str=str.replace("{name}",useUnit.getName());
				message.setName(useUnit.getName());
				message.setPhone(useUnit.getPhone());
				message.setContent(str);
				messageService.save(message);
			}
			//判断是否拥有自动发送短信权限
			if(Authority.haveMessage("xt_00")){
				sendMessage(message.getId());
			}
		}
		
		
	}
	
	/**
	 * 根据短信模板类型生成短信
	 * 短信模板类型
	 * name 接收人姓名
	 * phone 接收人手机号
	 * type 电梯安装地址
	 * code 电梯识别码
	 * sim    sim卡号
	 * date 日期
	 * days 天数
	 */
	public static void addMessage(String type,String name,String phone,String place,String code,String sim,String date,String days){
		XtszMessage message=new XtszMessage();
		String hql=" where flag='4' and remarks='"+type+"' ";
		XtszDictionary dictionaries=dictionaryService.query(hql).get(0);
		String str="";
		if(dictionaries!=null){
			str=dictionaries.getDictionary();
			str=str.replace("{name}",name);
			str=str.replace("{place}",place);
			str=str.replace("{code}",code);
			str=str.replace("{date}",date);
			str=str.replace("{sim}",sim);
			str=str.replace("{days}",days);
		}
		str=str.replace("{name}",name);
		message.setName(name);
		message.setState("未发送");
		message.setPhone(phone);
		message.setContent(str);
		messageService.save(message);
		
	}
	
	/**
	 * 调用短信接口发送短信
	 * @param id		报警短信 id
	 */
	public static String sendMessage(Long id){
		XtszMessage message=messageService.get(id);		//查询报警短信
		String str=SmsSample.sendMessage(message.getPhone(), message.getContent());		//调用发送短信接口
		if(str.equals("0")){
			//修改短信发送类型
			message.setTime(new Date());
			message.setState("已发送");
			messageService.update(message);
		}
		return str;
		
	}
	


	public static XtszMessageService getMessageService() {
		return messageService;
	}


	public static void setMessageService(XtszMessageService messageService) {
		Message.messageService = messageService;
	}


	public static DtjkElevatorService getElevatorService() {
		return elevatorService;
	}


	public static void setElevatorService(DtjkElevatorService elevatorService) {
		Message.elevatorService = elevatorService;
	}


	public static XtglUseUnitService getUseUnitService() {
		return useUnitService;
	}


	public static void setUseUnitService(XtglUseUnitService useUnitService) {
		Message.useUnitService = useUnitService;
	}


	public static XtglMaintenanceUsersService getMaintenanceUsersService() {
		return maintenanceUsersService;
	}


	public static void setMaintenanceUsersService(
			XtglMaintenanceUsersService maintenanceUsersService) {
		Message.maintenanceUsersService = maintenanceUsersService;
	}


	public static XtglUsersService getUsersService() {
		return usersService;
	}


	public static void setUsersService(XtglUsersService usersService) {
		Message.usersService = usersService;
	}
	public static XtszDictionaryService getDictionaryService() {
		return dictionaryService;
	}
	public static void setDictionaryService(XtszDictionaryService dictionaryService) {
		Message.dictionaryService = dictionaryService;
	}

}
