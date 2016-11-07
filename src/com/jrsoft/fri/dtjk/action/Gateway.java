package com.jrsoft.fri.dtjk.action;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import smart.sys.platform.springUtils.SpringBeanUtil;

import com.jrsoft.fri.dtjk.entity.DtjkElevator;
import com.jrsoft.fri.dtjk.entity.DtjkGateway;
import com.jrsoft.fri.dtjk.entity.DtjkRecord;
import com.jrsoft.fri.dtjk.service.DtjkElevatorService;
import com.jrsoft.fri.dtjk.service.DtjkGatewayService;
import com.jrsoft.fri.dtjk.service.DtjkRecordService;

public class Gateway {
	private static DtjkGatewayService gatewayService = (DtjkGatewayService)SpringBeanUtil.getBean("gatewayService");
	private static DtjkRecordService recordService = (DtjkRecordService)SpringBeanUtil.getBean("recordService");
	private static DtjkElevatorService elevatorService = (DtjkElevatorService)SpringBeanUtil.getBean("elevatorService");

	public static DtjkGatewayService getGatewayService() {
		return gatewayService;
	}

	public static void setGatewayService(DtjkGatewayService gatewayService) {
		Gateway.gatewayService = gatewayService;
	}

	public static DtjkRecordService getRecordService() {
		return recordService;
	}

	public static void setRecordService(DtjkRecordService recordService) {
		Gateway.recordService = recordService;
	}

	public static DtjkElevatorService getElevatorService() {
		return elevatorService;
	}

	public static void setElevatorService(DtjkElevatorService elevatorService) {
		Gateway.elevatorService = elevatorService;
	}

	public void query(String str,OutputStream os ) throws Exception{
		
		DtjkGateway gateway=new DtjkGateway();   //�ն˾�̬��¼
		DtjkRecord record=new DtjkRecord();     //�ն��ϱ���¼
		
		String type=str.substring(6,8); 
		System.out.println("�������ͣ�"+judgeType(type));
		
		String elevatorId=str.substring(8,48); 
		 elevatorId=convertHexToString(elevatorId);
		System.out.println("����id��"+elevatorId);
		
		String serialNumber=str.substring(48,70); 
		serialNumber=convertHexToString(serialNumber);
		System.out.println("����id��"+serialNumber);
		
	
		if(type.equalsIgnoreCase("20")){     //�ϱ���������
			record.setElevatorId(elevatorId);
			record.setSerialNumber(serialNumber);
			record.setFoundTime(new Date());
			//ѭ��  ��������
			for( int i=70; i<str.length()-1; i=i ){
				 String command =str.substring(i,i+2); //�������� 
				 String output = str.substring(i+2, (i + 4));//�����
			     int decimal = Integer.parseInt(output, 16)*2;
				String content =str.substring(i+4,i+4+decimal);
				juageContent(command, content,gateway,record);
				
				i=i+4+decimal;   //����i
				String end =str.substring(i,i+2); //�ж��Ƿ����
				if(end.equalsIgnoreCase("f0")){
					break;
				}
			}
			//���� �ϱ�����������
			recordService.save(record);
			
		}  else if(type.equalsIgnoreCase("23")){    //�ϱ���̬����
			gateway.setElevatorId(elevatorId);
			gateway.setSerialNumber(serialNumber);
			//ѭ��  ��������
			for( int i=70; i<str.length()-1; i=i ){
				 String command =str.substring(i,i+2); //�������� 
				 String output = str.substring(i+2, (i + 4));//�����
			     int decimal = Integer.parseInt(output, 16)*2;
				String content =str.substring(i+4,i+4+decimal);
				juageContent(command, content,gateway,record);
				
				i=i+4+decimal;   //����i
				String end =str.substring(i,i+2); //�ж��Ƿ����
				if(end.equalsIgnoreCase("f0")){
					break;
				}
			}
			
			//�����ն˺Ų�ѯ���ն��Ƿ��Ѽ�¼
			String hql=" where  1=1 and serialNumber='"+gateway.getSerialNumber()+"' " ;
			List<DtjkGateway> list=gatewayService.queryAll(hql);
			
			//num����0 ˵�����ն�δ��¼�����±���
			if(list.size()==0){
				gatewayService.save(gateway);
				String hql1="where registerid='"+elevatorId+"'";
				List<DtjkElevator> elevators=elevatorService.query(hql1);
				if(elevators.size()>0){
					DtjkElevator elevator=elevators.get(0);
					elevator.setGatewayId(gateway);
					elevatorService.update(elevator);
				}
				
			}else{//num������0 ˵�����ն��Ѽ�¼�������޸�
				
				String hql1=" where  1=1 and serialNumber='"+gateway.getSerialNumber()+"' " ;
				DtjkGateway dtjkGateways=gatewayService.queryAll(hql1).get(0);
				gateway.setId(dtjkGateways.getId());
				gatewayService.update(gateway);
				
			}
			
		}  else if(type.equalsIgnoreCase("21")){    //����
			//ѭ��  ��������
				 String command =str.substring(70,72); //���ϴ���
				 System.out.println("�������ͣ�"+ warning(command));
				 
				 String state =str.substring(72,74); //״̬
				 if(state.equalsIgnoreCase("00")){
					 System.out.println("״̬������");
				 }else if(state.equalsIgnoreCase("01")){
					 System.out.println("״̬������");
				 }else{
					 System.out.println("״̬����ֹ");
				 }
				 
				 String people  =str.substring(74,76); //����
				 if(people.equalsIgnoreCase("00")){
					 System.out.println("�ˣ�����");
				 }else{
					 System.out.println("�ˣ�����");
				 }
				 
				 String floor  =str.substring(76,78); //¥��
				 System.out.println("¥�㣺"+ Integer.parseInt(floor,16) );				 
				 
				 String door  =str.substring(78,80); // ��
				 if(door.equalsIgnoreCase("00")){
					 System.out.println("�ţ�����");
				 }else{
					 System.out.println("�ţ�����");
				 }
				 
				 try {
					 os.write("e00101e0".getBytes());
				} catch (IOException e) {
					e.printStackTrace();
				}
				
		}else{   //��������
			try {
				 os.write("e0XX113331313034333133303032303037313030303032313031363038303130303140630b3132333435363738393132630b3132333435363738313233e0".getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	
	}
	
	//ʮ������ascii ����
	 public static String convertStringToHex(String str){
		  char[] chars = str.toCharArray();
		  StringBuffer hex = new StringBuffer();
		  for(int i = 0; i < chars.length; i++){
		    hex.append(Integer.toHexString((int)chars[i]));
		  }

		  return hex.toString();
		  }
	 
	 //ʮ������ascii ����
	 public  String convertHexToString(String hex){

		  StringBuilder sb = new StringBuilder();
		  StringBuilder temp = new StringBuilder();
		  for( int i=0; i<hex.length()-1; i+=2 ){
		      String output = hex.substring(i, (i + 2));
		      int decimal = Integer.parseInt(output, 16);
		      sb.append((char)decimal);

		      temp.append(decimal);
		  }

		  return sb.toString();
		  }
	 
	 /**
	  * �ж����ͣ��ϱ����������������ݣ�
	  * @param type
	  * @return
	  */
	 public String judgeType(String type){
		 String name="";
		 if(type.equals("20")){
			 name="�ϱ���������";
		 }else if(type.equals("21")){
			 name="����";
		 }else if(type.equals("23")){
			 name="�ϱ���̬����";
		 }else{
			 name="��������";
		 }
		 return name;
	 }
	 
	 /**
	  * 0��20��0��23 �ϱ�  ���� �����Լ���Ӧ������
	  * @param command
	  * @param content
	  */
	 public void juageContent(String command,String content,DtjkGateway gateway,DtjkRecord record) {
		
		 //��¥��
		if(command.equalsIgnoreCase("01")){
			int floor=Integer.parseInt(content, 16);
			gateway.setFloor(Integer.toString(floor));
			System.out.println("��¥�㣺"+floor);
		}
		 //�趨�ٶ�
		if(command.equalsIgnoreCase("02")){
			String velocity =convertHexToString(content);
			gateway.setSpeed(velocity);
			System.out.println("�趨�ٶȣ�"+velocity+"mm/s");
		}
		 //����
		if(command.equalsIgnoreCase("20")){
			int floor=Integer.parseInt(content, 16);
			gateway.setSpacing(floor+"mm/s");
			System.out.println("���ࣺ"+floor+"mm");
		}
		//�ն�SIM
		if(command.equalsIgnoreCase("21")){
			String velocity =convertHexToString(content);
			gateway.setSim(velocity);
			System.out.println("�ն�SIM��"+velocity);
		}
		 //Ӳ���汾
		if(command.equalsIgnoreCase("22")){
			String velocity =convertHexToString(content);
			gateway.setHardware(velocity);
			System.out.println("Ӳ���汾��"+velocity);
		}
		 //����汾
		if(command.equalsIgnoreCase("23")){
			String velocity =convertHexToString(content);
			gateway.setSoftware(velocity);
			System.out.println("����汾��"+velocity);
		}
		 //����/��Ƶ
		if(command.equalsIgnoreCase("24")){
			int floor=Integer.parseInt(content, 16);
			if(floor==0){
				gateway.setType("������");
				System.out.println("����/��Ƶ��������");
			}else{
				gateway.setType("��Ƶ��");
				System.out.println("����/��Ƶ����Ƶ��");
			}
			
		}
		 //������ʽ
		if(command.equalsIgnoreCase("25")){
			int floor=Integer.parseInt(content, 16);
			if(floor==0){
				gateway.setNetworking("GPRS");
				System.out.println("������ʽ��GPRS");
			}else{
				gateway.setNetworking("WIFI");
				System.out.println("������ʽ��WIFI");
			}
			
		}
		 //���з���
		if(command.equalsIgnoreCase("30")){
			int floor=Integer.parseInt(content, 16);
			if(floor==0){
				record.setDirection("��");
				System.out.println("���з�����");
			}else{
				record.setDirection("��");
				System.out.println("���з�����");
			}
		}
		 //�����ٶ�
		if(command.equalsIgnoreCase("32")){
			String velocity =convertHexToString(content);
			record.setSpeed(velocity+"mm/s");
			System.out.println("�����ٶȣ�"+velocity+"mm/s");
		}
		 //��ǰ¥��
		if(command.equalsIgnoreCase("33")){
			int floor=Integer.parseInt(content, 16);
			record.setFloor(Integer.toString(floor));
			System.out.println("��ǰ¥�㣺"+floor);
		}
		//�ն�����
		if(command.equalsIgnoreCase("37")){
			int floor=Integer.parseInt(content, 16);
			record.setGatewayDate(Integer.toString(floor));
			System.out.println("�ն����ڣ�"+floor);
		}
		//�ն�ʱ��
		if(command.equalsIgnoreCase("38")){
			int floor=Integer.parseInt(content, 16);
			record.setGatewayTime(Integer.toString(floor));
			System.out.println("�ն�ʱ�䣺"+floor);
		}
		
		//��
		if(command.equalsIgnoreCase("39")){
			int floor=Integer.parseInt(content, 16);
			if(floor==0){
				record.setPeople("û��");
				System.out.println("�ˣ�û��");
			}else{
				record.setPeople("����");
				System.out.println("�ˣ�����");
			}
		}
		//��
		if(command.equalsIgnoreCase("3a")){
			int floor=Integer.parseInt(content, 16);
			if(floor==0){
				record.setDoor("����");
				System.out.println("�� ������");
			}else{
				record.setDoor("����");
				System.out.println("�ţ�����");
			}
		}
		//һ������
		if(command.equalsIgnoreCase("3c")){
			int floor=Integer.parseInt(content, 16);
			record.setHeartbeat(Integer.toString(floor));
			System.out.println("һ������ ��"+floor);
			
		}
		 //�ֳ�ά����Ա
		if(command.equalsIgnoreCase("50")){
			String velocity =convertHexToString(content);
			record.setMaintenanceUserId(velocity);
			System.out.println("�ֳ�ά����Ա��"+velocity);
		}
		//����״̬
		if(command.equalsIgnoreCase("51")){
			int floor=Integer.parseInt(content, 16);
			if(floor==0){
				record.setMaintenanceState("������");
				System.out.println("����״̬ ��������");
			}else{
				record.setMaintenanceState("����");
				System.out.println("����״̬������");
			}
		}
		
		//�ն�����
		if(command.equalsIgnoreCase("60")){
			int floor=Integer.parseInt(content, 16);
			System.out.println("���ڣ�"+floor);
		}
		//�ն�ʱ��
		if(command.equalsIgnoreCase("61")){
			int floor=Integer.parseInt(content, 16);
			System.out.println("ʱ�䣺"+floor);
		}
		//�ϱ�����
		if(command.equalsIgnoreCase("62")){
			int floor=Integer.parseInt(content, 16);
			gateway.setReport(Integer.toString(floor));
			System.out.println("�ϱ����� ��"+floor+"��");
			
		}
		//������1
		if(command.equalsIgnoreCase("63")){
			String velocity =convertHexToString(content);
			System.out.println("������1��"+velocity);
		}
		//������2
		if(command.equalsIgnoreCase("64")){
			String velocity =convertHexToString(content);
			System.out.println("������2��"+velocity);
		}
		//������3
		if(command.equalsIgnoreCase("65")){
			String velocity =convertHexToString(content);
			System.out.println("������3��"+velocity);
		}
		//������4
		if(command.equalsIgnoreCase("66")){
			String velocity =convertHexToString(content);
			System.out.println("������4��"+velocity);
		}
		
	}
	 
	/**
	 * 0��21  �жϹ�������
	 * @param type
	 * @return
	 */
	 public String warning(String type){
		 String name="";
		 
		 if(type.equals("01")){
			 name="����";
		 }else if(type.equals("02")){
			 name="�����п���";
		 }else if(type.equals("03")){
			 name="��������";
		 }else if(type.equals("04")){
			 name="�Źز���";
		 }else if(type.equals("05")){
			 name="����";
		 }else if(type.equals("06")){
			 name="����̫����������";
		 }else if(type.equals("07")){
			 name="��ƽ��ͣ��";
		 }else if(type.equals("08")){
			 name="��ƽ������";
		 }else if(type.equals("09")){
			 name="�嶥";
		 }else if(type.equals("0a")){
			 name="�嶥����";
		 }else if(type.equals("0b")){
			 name="���";
		 }else if(type.equals("0c")){
			 name="�������";
		 }else if(type.equals("20")){
			 name="����";
		 }else if(type.equals("21")){
			 name="��ֹ";
		 }else if(type.equals("22")){
			 name="����";
		 }else if(type.equals("23")){
			 name="����";
		 }else if(type.equals("24")){
			 name="����";
		 }else if(type.equals("25")){
			 name="����";
		 }else{
			 return name;
		 }
		 return name;
	 }

}
