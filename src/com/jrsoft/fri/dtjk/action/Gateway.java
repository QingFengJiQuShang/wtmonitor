package com.jrsoft.fri.dtjk.action;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import smart.sys.platform.springUtils.SpringBeanUtil;
import tcpip.byteUtil;
import com.jrsoft.fri.dtjk.entity.DtjkElevator;
import com.jrsoft.fri.dtjk.entity.DtjkGateway;
import com.jrsoft.fri.dtjk.entity.DtjkMaintenanceRecords;
import com.jrsoft.fri.dtjk.entity.DtjkPhone;
import com.jrsoft.fri.dtjk.entity.DtjkPush;
import com.jrsoft.fri.dtjk.entity.DtjkRecord;
import com.jrsoft.fri.dtjk.from.Ask;
import com.jrsoft.fri.dtjk.service.DtjkElevatorService;
import com.jrsoft.fri.dtjk.service.DtjkGatewayService;
import com.jrsoft.fri.dtjk.service.DtjkMaintenanceRecordsService;
import com.jrsoft.fri.dtjk.service.DtjkPhoneService;
import com.jrsoft.fri.dtjk.service.DtjkPushService;
import com.jrsoft.fri.dtjk.service.DtjkRecordService;
import com.jrsoft.fri.gzcl.entity.GzclFault;
import com.jrsoft.fri.gzcl.service.GzclFaultService;
import com.jrsoft.fri.xtgl.action.Authority;
import com.jrsoft.fri.xtgl.entity.XtglMaintenanceUsers;
import com.jrsoft.fri.xtgl.service.XtglMaintenanceUsersService;
import com.jrsoft.fri.xtsz.action.Log;
import com.jrsoft.fri.xtsz.action.Message;
import com.jrsoft.fri.xtsz.entity.XtszLog;

public class Gateway {
	private static DtjkGatewayService gatewayService = (DtjkGatewayService)SpringBeanUtil.getBean("gatewayService");
	private static DtjkRecordService recordService = (DtjkRecordService)SpringBeanUtil.getBean("recordService");
	private static DtjkElevatorService elevatorService = (DtjkElevatorService)SpringBeanUtil.getBean("elevatorService");
	private static DtjkMaintenanceRecordsService recordsService = (DtjkMaintenanceRecordsService)SpringBeanUtil.getBean("recordsService");
	private static XtglMaintenanceUsersService maintenanceUsersService = (XtglMaintenanceUsersService)SpringBeanUtil.getBean("maintenanceUsersService");
	private static GzclFaultService faultService = (GzclFaultService)SpringBeanUtil.getBean("gzclFaultService");
	private static DtjkPhoneService phoneService = (DtjkPhoneService)SpringBeanUtil.getBean("phoneService");
	private static DtjkPushService pushService = (DtjkPushService)SpringBeanUtil.getBean("pushService");

	
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

	public static DtjkMaintenanceRecordsService getRecordsService() {
		return recordsService;
	}

	public static void setRecordsService(
			DtjkMaintenanceRecordsService recordsService) {
		Gateway.recordsService = recordsService;
	}

	public static XtglMaintenanceUsersService getMaintenanceUsersService() {
		return maintenanceUsersService;
	}

	public static void setMaintenanceUsersService(
			XtglMaintenanceUsersService maintenanceUsersService) {
		Gateway.maintenanceUsersService = maintenanceUsersService;
	}

	public static GzclFaultService getFaultService() {
		return faultService;
	}

	public static void setFaultService(GzclFaultService faultService) {
		Gateway.faultService = faultService;
	}

	public static DtjkPhoneService getPhoneService() {
		return phoneService;
	}

	public static void setPhoneService(DtjkPhoneService phoneService) {
		Gateway.phoneService = phoneService;
	}

	public static DtjkPushService getPushService() {
		return pushService;
	}

	public static void setPushService(DtjkPushService pushService) {
		Gateway.pushService = pushService;
	}
	
	private static String str;
	private static String type;
	private static String elevatorId;

	public void query(byte[] buffer,OutputStream os ) throws Exception{
        byteUtil util=new byteUtil();
        String str = util.BytesHexString(buffer);
        System.out.println(str);
        this.str=str;
		SimpleDateFormat d=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //�ж������
        if(length(str)){
        	Log logAction=new Log();
            XtszLog log=new XtszLog();
            log.setFlag("0");
            log.setFoundTime(new Date());
            log.setUserName("�����ն�");
        	DtjkGateway gateway=new DtjkGateway();   //�ն˾�̬��¼
    		DtjkRecord record=new DtjkRecord();     //�ն��ϱ���¼
    		Ask ask=new Ask();     				//��������
    		String type=str.substring(6,8); 
    		System.out.println("�������ͣ�"+judgeType(type));
    		 this.type=type;
    		String elevatorId=str.substring(8,48); 
    		 elevatorId=convertHexToString(elevatorId);
    		System.out.println("����id��"+elevatorId);
    		this.elevatorId=elevatorId;
    		
    		String hql2=" where 1=1 and  registerid = '"+elevatorId+"' and delflag!='1' ";
			List<DtjkElevator> elevators=elevatorService.queryAll(hql2);
			//���ݵ���ע��ţ��жϸĵ����Ƿ���ڣ�����ֹ����
			if(elevators.size()==0){
				 System.out.println("�������E0021102F0");
				 os.write(byteUtil.hexStringToByte("E0021102F0"));
				CreateWorkbook("E0021102F0");
				 return;
			}
    		
    		
    		String serialNumber=str.substring(48,70); 
    		serialNumber=convertHexToString(serialNumber);
    		System.out.println("����id��"+serialNumber);
    		
    		log.setContent(judgeType(type)+"   "+str);
            logAction.addEntity(log);
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
    				juageContent(command, content,gateway,record,ask);
    				
    				i=i+4+decimal;   //����i
    				String end =str.substring(i,i+2); //�ж��Ƿ����
    				if(end.equalsIgnoreCase("F0")){
    					break;
    				}
    			}
    			if(record.getGatewayDate()==null){
    				 SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
    				 record.setGatewayDate(df.format(new Date()));
    			}
    			if(record.getGatewayTime()==null){
   				 	SimpleDateFormat df=new SimpleDateFormat("HH:mm:ss");
   				 	record.setGatewayTime(df.format(new Date()));
    			}
    			//���� �ϱ�����������
    			recordService.save(record);
    			
    	//		String hql1=" where 1=1 and  registerid = '"+record.getElevatorId()+"'";
		//		List<DtjkElevator> elevators=elevatorService.queryAll(hql1);
				//�޸� �����ϱ�ʱ��
				if(elevators.size()>0){
    				DtjkElevator entity =elevatorService.get(elevators.get(0).getId());
    				entity.setReportTime(new Date());
    				//�޸ĵ�������״̬Ϊ����״̬
    				if(entity.getState().equals("����")){
    					entity.setState("����");
    				}
    				elevatorService.update(entity);
				}
    			//ά����Ա��Ϊ��ʱ������ά����¼
    			if(record.getMaintenanceUserId()!=null&&!record.getMaintenanceUserId().equals("")&&record.getMaintenanceState().equals("������")){
    				DtjkMaintenanceRecords records=new DtjkMaintenanceRecords();
    				records.setTime(new Date());
    				records.setCardNumber(record.getMaintenanceUserId());		//����
    				
    				String hql=" where 1=1 and  cardNumber = '"+record.getMaintenanceUserId()+"'";
    				List<XtglMaintenanceUsers> useUnitId=maintenanceUsersService.queryAll(hql);
    				if(useUnitId.size()>0){
    					records.setUserId(useUnitId.get(0));//ά����id
    					records.setUnitId(useUnitId.get(0).getUnitId());//ά����λid
    				}else{
    					records.setUserId(null);//ά����id
    					records.setUnitId(null);//ά����λid
    				}
    				
    				if(elevators.size()>0){
    					records.setUseUnitId(elevators.get(0).getUseUnitId());//ʹ�õ�λid
    					records.setElevatorId(elevators.get(0));//ά������Id
    					
    					//�޸ĵ��ݵ�ά��״̬��ά��ʱ��
        				DtjkElevator entity =elevatorService.get(elevators.get(0).getId());
        				entity.setMaintenanceTime(new Date());
        				entity.setState("����");
        				entity.setMaintenanceState("����");
        				elevatorService.update(entity);
    				}else{
    					records.setUseUnitId(null);//ʹ�õ�λid
    					records.setElevatorId(null);//ά������Id
    				}
    				recordsService.save(records);
    			}
    			//ά����Ա��Ϊ��ʱ��
    			if(record.getMaintenanceUserId()!=null&&!record.getMaintenanceUserId().equals("")&&record.getMaintenanceState().equals("����")){
    				
    				if(elevators.size()>0){
	    				//�޸ĵ��ݵ�ά��״̬��ά��ʱ��
	    				DtjkElevator entity =elevatorService.get(elevators.get(0).getId());
	    				entity.setMaintenanceTime(new Date());
	    				entity.setMaintenanceState("����");
	    				entity.setState("����");
	    				elevatorService.update(entity);
    				}
    			}
    			try {
					 //os.write("E0021101F0".getBytes());
					 os.write(byteUtil.hexStringToByte("E0021101F0")); 
					CreateWorkbook("E0021101F0");
				} catch (IOException e) {
					e.printStackTrace();
				}
    			
    		}  else if(type.equalsIgnoreCase("23")){    //�ϱ���̬����
    			gateway.setElevatorId(elevatorId);
    			gateway.setSerialNumber(serialNumber);
    			//ѭ��  ��������
    			for( int i=70; i<str.length()-1; i=i ){
    				 String command =str.substring(i,i+2); //�������� 
    				 String output = str.substring(i+2, (i + 4));//�����
    			     int decimal = Integer.parseInt(output, 16)*2;
    				String content =str.substring(i+4,i+4+decimal);
    				juageContent(command, content,gateway,record,ask);
    				
    				i=i+4+decimal;   //����i
    				String end =str.substring(i,i+2); //�ж��Ƿ����
    				if(end.equalsIgnoreCase("F0")){
    					break;
    				}
    			}
    			
    			//�����ն˺Ų�ѯ���ն��Ƿ��Ѽ�¼
    			String hql=" where  1=1 and serialNumber='"+gateway.getSerialNumber()+"' " ;
    			List<DtjkGateway> list=gatewayService.queryAll(hql);
    			
    			//num����0 ˵�����ն�δ��¼�����±���
    			if(list.size()==0){
    				gatewayService.save(gateway);
    	//			String hql1="where registerid='"+elevatorId+"'";
    	//			List<DtjkElevator> elevators=elevatorService.query(hql1);
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
    			 try {
					 //os.write("E0021101F0".getBytes());
					 os.write(byteUtil.hexStringToByte("E0021101F0")); 
					CreateWorkbook("E0021101F0");
				} catch (IOException e) {
					e.printStackTrace();
				}
    		}  else if(type.equalsIgnoreCase("21")){    //����
    			
    			 
    			//ѭ��  ��������
    				 String command =str.substring(70,72); //���ϴ���
    				 String types=command;     //
    				 String order=warning(command);
    				 record.setType(order);
    				 command="�������ͣ�"+ warning(command);
    				 System.out.println(command);
    				 
    				 String state =str.substring(72,74); //״̬
    				 if(state.equalsIgnoreCase("00")){
    					 record.setDirection("����");
    					 state="����״̬������";
    				 }else if(state.equalsIgnoreCase("01")){
    					 record.setDirection("����");
    					 state="����״̬������";
    				 }else{
    					 record.setDirection("��ֹ");
    					 state="����״̬����ֹ";
    				 }
					 System.out.println(state);

    				 String people  =str.substring(74,76); //����
    				 if(people.equalsIgnoreCase("00")){
    					 record.setPeople("����");
    					 people="	�Ƿ����ˣ�����";
    				 }else{
    					 record.setPeople("����");
    					 people="	�Ƿ����ˣ�����";
    				 }
    				 System.out.println(people);
    				 String floor  =str.substring(76,78); //¥��
    				 record.setFloor(""+(byte)Integer.parseInt(floor,16));
    				 floor="	��ǰ¥�㣺"+  (byte)Integer.parseInt(floor,16);
    				 System.out.println(floor);				 
    				 
    				 String door  =str.substring(78,80); // ��
    				 if(door.equalsIgnoreCase("00")){
    					 record.setDoor("����");
    					 door="	��״̬������";
    				 }else{
    					 record.setDoor("����");
    					 door=" 	��״̬������";
    				 }
					 System.out.println(door);
					 String date  =str.substring(80,86); // ����
					 date=dateString(hexToString(date)) ;
					 String time=str.substring(86,92);
					 time=timeString(hexToString(time));  //ʱ��
					// System.out.println(d.format(new Date()));
					// System.out.println(d.format(d.parse("20"+date+" "+time)));
					 record.setSerialNumber(serialNumber);
					 record.setElevatorId(elevatorId);
					
					 
					 record.setGatewayDate(date);
					 record.setGatewayTime(time);
					 record.setFoundTime(new Date());
					//���� �ϱ��ı�����¼
		    			recordService.save(record);
    			  //�Զ����� ��ǰ����
    			  GzclFault fault=new GzclFault();
       			  fault.setHappenTime(d.parse("20"+date+" "+time));
       			  fault.setAlarmTime(new Date());
       			  fault.setType("�Զ��Ӿ�");
       			  fault.setState("������");
       			  fault.setFaultType(order);
       			  fault.setFault(state+people+floor+door);
       			  fault.setDutyId(null);
      // 			String hql1=" where 1=1 and  registerid = '"+elevatorId+"'";
		//		List<DtjkElevator> elevators=elevatorService.queryAll(hql1);
				DtjkElevator list=null;
				DtjkPush push=new DtjkPush();
				if(elevators.size()>0){
					fault.setElevatorId(elevators.get(0));//ά������Id
					push.setElevatorId(elevators.get(0));
					list=elevators.get(0);
					list.setState("����");
					elevatorService.update(list);	//�޸ĵ�������״̬
					push.setRegisterid(elevatorId);
					push.setDistinguishid(serialNumber);
					push.setInstallPlace(list.getInstallPlace());
					push.setFaultType(command);
					push.setFlag("0");
					pushService.save(push);		//�������Ѽ�¼
				}else{
					fault.setElevatorId(null);//ά������Id
					push.setElevatorId(null);
				}
				faultService.save(fault);		//���ɵ�ǰ����
				
    				 try {
    					 //os.write("E0021101F0".getBytes());
    		       		 os.write(byteUtil.hexStringToByte("E0021101F0"));
    					CreateWorkbook("E0021101F0");
    				} catch (IOException e) {
    					e.printStackTrace();
    				}
    			//���ɱ������� 
    				if(elevators.size()>0){
    					Message.addMessage(types,order,elevators.get(0));
    				}
    				
    		}  else if(type.equalsIgnoreCase("22")){    //��������	
    			gateway.setElevatorId(elevatorId);
    			gateway.setSerialNumber(serialNumber);
    			//ѭ��  ��������
    			for( int i=70; i<str.length()-1; i=i ){
    				 String command =str.substring(i,i+2); //�������� 
    				 String output = str.substring(i+2, (i + 4));//�����
    			     int decimal = Integer.parseInt(output, 16)*2;
    				String content =str.substring(i+4,i+4+decimal);
    				juageContent(command, content,gateway,record,ask);
    				
    				i=i+4+decimal;   //����i
    				String end =str.substring(i,i+2); //�ж��Ƿ����
    				if(end.equalsIgnoreCase("F0")){
    					break;
    				}
    			}
    			//���ɷ�������
    			String elevatorIds=str.substring(8,48); 
    			String serialNumbers=str.substring(48,70); 
    			 SimpleDateFormat df=new SimpleDateFormat("yyMMdd");
    			 SimpleDateFormat df1=new SimpleDateFormat("HHmmss");
    			 String date="";
    			 String m="1140"+elevatorIds+serialNumbers;
    			 //0X60
    			 if(ask.getDates()!=null){
    				 date=df.format(new Date());
    				 date=stringToHex(date);
    				 m+="6003"+date;
    			 }
    			//0X61
    			 if(ask.getTime()!=null){
    				 date=df1.format(new Date());
    				 date=stringToHex(date);
    				 m+="6103"+date;
    			 }
    			 //0X62
    			 if(ask.getPeriod()!=null){
    				 	//�����ն˺Ų�ѯ���ն��Ƿ��Ѽ�¼
    	     			String hql=" where  1=1 and serialNumber='"+gateway.getSerialNumber()+"' " ;
    	     			List<DtjkGateway>  gateways=gatewayService.queryAll(hql);
    	     			//num����0 ˵�����ն�δ��¼�����±���
    	     			if(gateways.size()>0){
    	     				DtjkGateway list=gateways.get(0);
    	     				if(list.getReport()!=null&&!list.getReport().equals("")){
    	     					date=stringToHex(list.getReport());
        	     				m+="6201"+date;
    	     				}else{
        	     				m+="620100";
        	     			}
    	     			}
    			 }
   // 			 String hql1=" where 1=1 and  registerid = '"+elevatorId+"'";
 	//			List<DtjkElevator> elevators=elevatorService.queryAll(hql1);
    				 if(elevators.size()>0){
    					 DtjkElevator elevator=elevators.get(0);
    					 String hql=" where  1=1  and elevatorId='"+elevator.getId()+"' " ;
    		     		 List<DtjkPhone>  phones=phoneService.queryAll(hql);
    		     		 
    		     	//  ������
    	    			 if(ask.getWhite1()!=null){
    		     		 	//0X63  ������1
	    		     		if(phones.size()>0){
	    		     			DtjkPhone phone=phones.get(0);
	    		     			date=convertStringToHex(phone.getPhone());
	    		     			m+="630b"+date;
	    		     		}else{
	    		     			m+="630b0000000000000000000000";
	    		     		}
    	    			 }
    	    			//  ������
    	    			 if(ask.getWhite2()!=null){
	    		     		//0X64  ������2
	    		     		if(phones.size()>1){
	    		     			DtjkPhone phone=phones.get(1);
	    		     			date=convertStringToHex(phone.getPhone());
	    		     			m+="640b"+date;
	    		     		}else{
	    		     			m+="640b0000000000000000000000";
	    		     		}
    	    			 }
	    		     	//  ������
	    	    			 if(ask.getWhite3()!=null){
		    		     		//0X65  ������3
		    		     		if(phones.size()>2){
		    		     			DtjkPhone phone=phones.get(2);
		    		     			date=convertStringToHex(phone.getPhone());
		    		     			m+="650b"+date;
		    		     		}else{
		    		     			m+="650b0000000000000000000000";
		    		     		}
	    	    			 }
	    		     	//  ������
	    	    			 if(ask.getWhite4()!=null){
		    		     		//0X66  ������4
		    		     		if(phones.size()>3){
		    		     			DtjkPhone phone=phones.get(3);
		    		     			date=convertStringToHex(phone.getPhone());
		    		     			ask.setWhite4("660b"+date);
		    		     			m+="660b"+date;
		    		     		}else{
		    		     			m+="660b0000000000000000000000";
		    		     		}
	    	    			 }
    			 }
    			String len=Integer.toHexString(m.length()/2);
    			m="E0"+len+m+"F0";
    			System.out.println("�����������"+m.toUpperCase());
       			//os.write(m.getBytes());
       			os.write(byteUtil.hexStringToByte(m.toUpperCase()));
				CreateWorkbook(m.toUpperCase());
    		}else{   
    			try {
    				 System.out.println("�������E0021102F0");
    				 os.write(byteUtil.hexStringToByte("E0021102F0"));
    				CreateWorkbook("E0021102F0");
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		}
        }else{
        	 System.out.println("���ȴ���E0021103F0");
			 os.write(byteUtil.hexStringToByte("E0021103F0"));
				CreateWorkbook("E0021103F0");
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

	 //ʮ������ascii ����
	 public  String hexToString(String hex){

		  StringBuilder temp = new StringBuilder();
		  for( int i=0; i<hex.length()-1; i+=2 ){
		      String output = hex.substring(i, (i + 2));
		      int decimal = Integer.parseInt(output, 16);
		      if(decimal>9){
		    	  temp.append(decimal);
		      }else{
		    	  temp.append("0"+decimal);
		      }
		     
		  }

		  return temp.toString();
		  }
	//ʮ������ascii ����
	 public static  String stringToHex(String hex){

		  StringBuilder temp = new StringBuilder();
		  for( int i=0; i<hex.length()-1; i+=2 ){
		      String output = hex.substring(i, (i + 2));
		      int decimal = Integer.parseInt(output);
		      if(decimal>15){
		    	  temp.append(Integer.toHexString(decimal)); 
		      }else{
		    	  temp.append("0"+Integer.toHexString(decimal));
		      }
		  }

		  return temp.toString();
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
	 * @throws ParseException 
	  */
	 public void juageContent(String command,String content,DtjkGateway gateway,DtjkRecord record,Ask ask) throws ParseException {
		 //����¥��
		if(command.equalsIgnoreCase("01")){
			int floor=Integer.parseInt(content, 16);
			gateway.setFloor(Integer.toString(floor));
			System.out.println("��¥�㣺"+floor);
		}
		 //����¥��
		if(command.equalsIgnoreCase("03")){
			int floor=Integer.parseInt(content, 16);
			gateway.setUpper(Integer.toString(floor));
			System.out.println("����¥�㣺"+floor);
		}
		 //����¥��
		if(command.equalsIgnoreCase("04")){
			int floor=Integer.parseInt(content, 16);
			gateway.setLower(Integer.toString(floor));
			System.out.println("����¥�㣺"+floor);
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
				record.setDirection("����");
				System.out.println("���з�������");
			}else if(floor==1){
				record.setDirection("����");
				System.out.println("���з�������");
			}else{
				record.setDirection("��ֹ");
				System.out.println("���з��򣺾�ֹ");
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
			int floor = (byte) Integer.parseInt(content, 16); 
//			if(floor<=0)
//				floor=floor-1;
			record.setFloor(Integer.toString(floor));
			System.out.println("��ǰ¥�㣺"+floor);
		}
		//�ն�����
		if(command.equalsIgnoreCase("37")){
			String date=hexToString(content);
			 date=dateString(date) ;
			record.setGatewayDate("20"+date);
			System.out.println("�ն����ڣ�"+"20"+date);
		}
		//�ն�ʱ��
		if(command.equalsIgnoreCase("38")){
			String time=hexToString(content);
			time=timeString(time) ;
			record.setGatewayTime(time);
			System.out.println("�ն�ʱ�䣺"+time);
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
			 String date=hexToString(content);
			 date=dateString(date) ;
			 ask.setDates(date);
			 System.out.println("���ڣ�"+date);
		}
		//�ն�ʱ��
		if(command.equalsIgnoreCase("61")){
			String time=hexToString(content);
			time=timeString(time) ;
			ask.setTime(time);
			System.out.println("ʱ�䣺"+time);
		}
		//�ϱ�����
		if(command.equalsIgnoreCase("62")){
			String velocity =convertHexToString(content);
			gateway.setReport(velocity);
			ask.setPeriod(velocity);
			System.out.println("�ϱ����� ��"+velocity+"��");
			
		}
		//������1
		if(command.equalsIgnoreCase("63")){
			String velocity =convertHexToString(content);
			ask.setWhite1(velocity);
			System.out.println("������1��"+velocity);
		}
		//������2
		if(command.equalsIgnoreCase("64")){
			String velocity =convertHexToString(content);
			ask.setWhite2(velocity);
			System.out.println("������2��"+velocity);
		}
		//������3
		if(command.equalsIgnoreCase("65")){
			String velocity =convertHexToString(content);
			ask.setWhite3(velocity);
			System.out.println("������3��"+velocity);
		}
		//������4
		if(command.equalsIgnoreCase("66")){
			String velocity =convertHexToString(content);
			ask.setWhite4(velocity);
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
		 if(type.equals("0")){
			 name="����";
		 }else if(type.equals("01")){
			 name="����";
		 }else if(type.equals("02")){
			 name="����";
		 }else if(type.equals("03")){
			 name="�Źز���";
		 }else if(type.equals("04")){
			 name="�嶥����";
		 }else if(type.equals("05")){
			 name="�嶥";
		 }else if(type.equals("06")){
			 name="�׵�����";
		 }else if(type.equals("07")){
			 name="�׵�";
		 }else if(type.equals("08")){
			 name="��������";
		 }else if(type.equals("09")){
			 name="�˶��п���";
		 }else if(type.equals("0a")){
			 name="��ƽ������";
		 }else if(type.equals("0b")){
			 name="��ƽ��ͣ��";
		 }else if(type.equals("0c")){
			 name="ͣ��";
		 }else if(type.equals("0d")){
			 name="���Ų���λ";
		 }else if(type.equals("10")){
			 name="��ƽ�㿪��";
		 }else{
			 return name;
		 }
		 return name;
	 }
	//����
	 public static  String dateString(String hex){

		  StringBuilder temp = new StringBuilder();
		  for( int i=0; i<hex.length()-1; i+=2 ){
		      String output = hex.substring(i, (i + 2));
		      if((i+2)!=hex.length()){
		    	  temp.append(output+ "-");
		      }else{
		    	  temp.append(output);
		      }
		  }

		  return temp.toString();
	 }
	//ʱ��
	 public static  String timeString(String hex){

		  StringBuilder temp = new StringBuilder();
		  for( int i=0; i<hex.length()-1; i+=2 ){
		      String output = hex.substring(i, (i + 2));
		      if((i+2)!=hex.length()){
		    	  temp.append(output+ ":");
		      }else{
		    	  temp.append(output);
		      }
		  }

		  return temp.toString();
	 }
	
	 
	//�ж������
	 public static  Boolean length(String hex){
		 String str=null;
		 str=StringUtils.substringBetween(hex,"e0","f0" );
		 String length=str.substring(0, 2);
		 str=str.substring(2);
		 int len=Integer.parseInt(length, 16)*2;
		 if(len==str.length()){
			 return true;
		 }else{
			 return false;
		 }
		 
	 }
	 /**
		 * java д��txt�ı�
		 * @param content		д������
		 */
		public static void write(String content) {  

			String filePath="D:/test.txt";
			BufferedWriter out = null;
			try {
				out = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(filePath, true)));
				out.write(content+"\r\n");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		/**
		 * ��excel��׷������
		 * @param a
		 */
		public static void CreateWorkbook(String cmd) {
			SimpleDateFormat d=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 String[] a={d.format(new Date()),type,elevatorId,str,cmd};
			String url="D:/����ͨ������.xls";
			File file=new File(url);
	        try {
	            if (!file.exists()) { //�ж��ļ��Ƿ��Ѵ��ڣ����û�д����򴴽����ļ�
	            	export(url);
	            } 
	            	// �����  
	            	   FileInputStream is = new FileInputStream(file);  
	            	   HSSFWorkbook wb =new HSSFWorkbook(is); 
	            	   HSSFCellStyle style = wb.createCellStyle(); // ��ʽ����
		   //    			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// ��ֱ
		   //    			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// ˮƽ
	            	   HSSFSheet sheet1 = wb.getSheetAt(0);  
	            	   HSSFRow row = sheet1.createRow(sheet1.getLastRowNum() + 1);  
	            	   row.setRowStyle(style);
	            	   row.setHeightInPoints((short) 25);  
	            	   // ����һ�и�ֵ  
	            	   row.createCell(0).setCellValue(a[0]);  
	            	   row.createCell(1).setCellValue(a[1]);  
	            	   row.createCell(2).setCellValue(a[2]);  
	            	   row.createCell(3).setCellValue(a[3]);  
	            	   row.createCell(4).setCellValue(a[4]);  
	            	   FileOutputStream os = new FileOutputStream(file);  
	            	   type=null;
	            	   str=null;
	            	   elevatorId=null;
	            	   wb.write(os);  
	            	   is.close();  
	            	   os.close();  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	       
	    }
		
		/**
		 * ����һ��excel
		 * @param filePath ����·��
		 */
		public static void export(String filePath) {
			try {
				HSSFWorkbook workbook = new HSSFWorkbook();
				// ��Excel�������н�һ����������Ϊȱʡֵ
				HSSFSheet sheet = null;
				HSSFRow row = null;
				HSSFCell cell = null;
				sheet = workbook.createSheet("����ͨ����Ϣ");
				HSSFCellStyle style = workbook.createCellStyle(); // ��ʽ����
				style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// ��ֱ
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// ˮƽ
				/**
				 * ��������
				 */
				HSSFFont f = workbook.createFont();
				f.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// ����Ӵ�
				style.setFont(f);		
				String[] top_arraydis = {"ʱ��","��������","����ע���","ԭʼ����","�ظ�����"};
					
					row = sheet.createRow(0);// ����һ��
					for (int c = 0; c < top_arraydis.length; c++) {
						cell = row.createCell((short) c);// ������ �ֶ� 
						cell.setCellValue("����");
						cell.setCellStyle(style);
						cell.setCellValue(top_arraydis[c]);
					}
					
				// �½�һ����ļ���
					sheet.setColumnWidth((short)0,(short)5000);
					sheet.setColumnWidth((short)1,(short)5000); 
					sheet.setColumnWidth((short)2,(short)7000); 
					sheet.setColumnWidth((short)3,(short)20000); 
					sheet.setColumnWidth((short)4,(short)5000); 
				FileOutputStream fOut = new FileOutputStream(filePath);
				workbook.write(fOut);
				fOut.flush();
				// �����������ر��ļ�
				fOut.close();
				return;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("������ xlCreate() : " + e);
			}
			
		}
}
