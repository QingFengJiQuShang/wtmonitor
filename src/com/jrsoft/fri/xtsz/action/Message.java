package com.jrsoft.fri.xtsz.action;

import java.util.Date;

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
import com.jrsoft.fri.xtsz.entity.XtszMessage;
import com.jrsoft.fri.xtsz.service.XtszMessageService;

public class Message {
	private static XtszMessageService messageService = (XtszMessageService)SpringBeanUtil.getBean("messageService");
	private static DtjkElevatorService elevatorService = (DtjkElevatorService)SpringBeanUtil.getBean("elevatorService");
	private static XtglUseUnitService useUnitService = (XtglUseUnitService)SpringBeanUtil.getBean("useUnitService");
	private static XtglMaintenanceUsersService maintenanceUsersService = (XtglMaintenanceUsersService)SpringBeanUtil.getBean("maintenanceUsersService");
	private static XtglUsersService usersService = (XtglUsersService)SpringBeanUtil.getBean("usersService");

	
	/**
	 * 接到报警命令后，自动生成短信
	 * type 报警命令
	 * type 解析后的命令
	 * elevators 报警电梯信息
	 */
	public static void addMessage(String type,String order,DtjkElevator elevators){
		XtszMessage message=new XtszMessage();
		DtjkElevator list=elevatorService.get(elevators.getId());
		//判断短信权限   使用单位
		if(Authority.haveMessage("sy_"+type)){
			message.setState("未发送");
			XtglUseUnit useUnit=useUnitService.get(list.getUseUnitId().getId());
			message.setPhone(useUnit.getPhone());
			message.setContent("您好，"+elevators.getInstallPlace()+"的电梯发生了故障，故障原因是："+order+"。请尽快派人维修！【中慧】");
			messageService.save(message);
			//判断是否拥有自动发送短信权限
			if(Authority.haveMessage("sy_00")){
				sendMessage(message.getId());
			}
		}
		//判断短信权限   维保单位
		if(Authority.haveMessage("wb_"+type)){
			message.setState("未发送");
			XtglMaintenanceUsers useUnit=maintenanceUsersService.get(list.getMaintenanceUnitId().getId());
			message.setPhone(useUnit.getPhone());
			message.setContent("您好，"+elevators.getInstallPlace()+"的电梯发生了故障，故障原因是："+order+"。请尽快派人维修！【中慧】");
			messageService.save(message);
			//判断是否拥有自动发送短信权限
			if(Authority.haveMessage("wb_00")){
				sendMessage(message.getId());
			}
		}
		
		//判断短信权限   系统用户
		if(Authority.haveMessage("xt_"+type)){
			message.setState("未发送");
			XtglUsers useUnit=usersService.get(list.getUserid().getId());
			message.setPhone(useUnit.getPhone());
			message.setContent("您好，"+elevators.getInstallPlace()+"的电梯发生了故障，故障原因是："+order+"。请尽快派人维修！【中慧】");
			messageService.save(message);
			//判断是否拥有自动发送短信权限
			if(Authority.haveMessage("xt_00")){
				sendMessage(message.getId());
			}
		}
		
		
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

}
