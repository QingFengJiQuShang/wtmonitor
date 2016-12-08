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
	 * �ӵ�����������Զ����ɶ���
	 * type ��������
	 * type �����������
	 * elevators ����������Ϣ
	 */
	public static void addMessage(String type,String order,DtjkElevator elevators){
		XtszMessage message=new XtszMessage();
		DtjkElevator list=elevatorService.get(elevators.getId());
		//�ж϶���Ȩ��   ʹ�õ�λ
		if(Authority.haveMessage("sy_"+type)){
			message.setState("δ����");
			XtglUseUnit useUnit=useUnitService.get(list.getUseUnitId().getId());
			message.setPhone(useUnit.getPhone());
			message.setContent("���ã�"+elevators.getInstallPlace()+"�ĵ��ݷ����˹��ϣ�����ԭ���ǣ�"+order+"���뾡������ά�ޣ����лۡ�");
			messageService.save(message);
			//�ж��Ƿ�ӵ���Զ����Ͷ���Ȩ��
			if(Authority.haveMessage("sy_00")){
				sendMessage(message.getId());
			}
		}
		//�ж϶���Ȩ��   ά����λ
		if(Authority.haveMessage("wb_"+type)){
			message.setState("δ����");
			XtglMaintenanceUsers useUnit=maintenanceUsersService.get(list.getMaintenanceUnitId().getId());
			message.setPhone(useUnit.getPhone());
			message.setContent("���ã�"+elevators.getInstallPlace()+"�ĵ��ݷ����˹��ϣ�����ԭ���ǣ�"+order+"���뾡������ά�ޣ����лۡ�");
			messageService.save(message);
			//�ж��Ƿ�ӵ���Զ����Ͷ���Ȩ��
			if(Authority.haveMessage("wb_00")){
				sendMessage(message.getId());
			}
		}
		
		//�ж϶���Ȩ��   ϵͳ�û�
		if(Authority.haveMessage("xt_"+type)){
			message.setState("δ����");
			XtglUsers useUnit=usersService.get(list.getUserid().getId());
			message.setPhone(useUnit.getPhone());
			message.setContent("���ã�"+elevators.getInstallPlace()+"�ĵ��ݷ����˹��ϣ�����ԭ���ǣ�"+order+"���뾡������ά�ޣ����лۡ�");
			messageService.save(message);
			//�ж��Ƿ�ӵ���Զ����Ͷ���Ȩ��
			if(Authority.haveMessage("xt_00")){
				sendMessage(message.getId());
			}
		}
		
		
	}
	
	/**
	 * ���ö��Žӿڷ��Ͷ���
	 * @param id		�������� id
	 */
	public static String sendMessage(Long id){
		XtszMessage message=messageService.get(id);		//��ѯ��������
		String str=SmsSample.sendMessage(message.getPhone(), message.getContent());		//���÷��Ͷ��Žӿ�
		if(str.equals("0")){
			//�޸Ķ��ŷ�������
			message.setTime(new Date());
			message.setState("�ѷ���");
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
