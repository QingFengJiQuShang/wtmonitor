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
        //判断命令长度
        if(length(str)){
        	Log logAction=new Log();
            XtszLog log=new XtszLog();
            log.setFlag("0");
            log.setFoundTime(new Date());
            log.setUserName("电梯终端");
        	DtjkGateway gateway=new DtjkGateway();   //终端静态记录
    		DtjkRecord record=new DtjkRecord();     //终端上报记录
    		Ask ask=new Ask();     				//请求命令
    		String type=str.substring(6,8); 
    		System.out.println("数据类型："+judgeType(type));
    		 this.type=type;
    		String elevatorId=str.substring(8,48); 
    		 elevatorId=convertHexToString(elevatorId);
    		System.out.println("电梯id："+elevatorId);
    		this.elevatorId=elevatorId;
    		
    		String hql2=" where 1=1 and  registerid = '"+elevatorId+"' and delflag!='1' ";
			List<DtjkElevator> elevators=elevatorService.queryAll(hql2);
			//根据电梯注册号，判断改电梯是否存在，否终止方法
			if(elevators.size()==0){
				 System.out.println("命令错误：E0021102F0");
				 os.write(byteUtil.hexStringToByte("E0021102F0"));
				CreateWorkbook("E0021102F0");
				 return;
			}
    		
    		
    		String serialNumber=str.substring(48,70); 
    		serialNumber=convertHexToString(serialNumber);
    		System.out.println("网关id："+serialNumber);
    		
    		log.setContent(judgeType(type)+"   "+str);
            logAction.addEntity(log);
    		if(type.equalsIgnoreCase("20")){     //上报运行数据
    			record.setElevatorId(elevatorId);
    			record.setSerialNumber(serialNumber);
    			record.setFoundTime(new Date());
    			//循环  解析命令
    			for( int i=70; i<str.length()-1; i=i ){
    				 String command =str.substring(i,i+2); //命令类型 
    				 String output = str.substring(i+2, (i + 4));//命令长度
    			     int decimal = Integer.parseInt(output, 16)*2;
    				String content =str.substring(i+4,i+4+decimal);
    				juageContent(command, content,gateway,record,ask);
    				
    				i=i+4+decimal;   //更改i
    				String end =str.substring(i,i+2); //判断是否结束
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
    			//保存 上报的运行数据
    			recordService.save(record);
    			
    	//		String hql1=" where 1=1 and  registerid = '"+record.getElevatorId()+"'";
		//		List<DtjkElevator> elevators=elevatorService.queryAll(hql1);
				//修改 电梯上报时间
				if(elevators.size()>0){
    				DtjkElevator entity =elevatorService.get(elevators.get(0).getId());
    				entity.setReportTime(new Date());
    				//修改电梯离线状态为正常状态
    				if(entity.getState().equals("离线")){
    					entity.setState("正常");
    				}
    				elevatorService.update(entity);
				}
    			//维保人员不为空时，生成维保记录
    			if(record.getMaintenanceUserId()!=null&&!record.getMaintenanceUserId().equals("")&&record.getMaintenanceState().equals("检修中")){
    				DtjkMaintenanceRecords records=new DtjkMaintenanceRecords();
    				records.setTime(new Date());
    				records.setCardNumber(record.getMaintenanceUserId());		//卡号
    				
    				String hql=" where 1=1 and  cardNumber = '"+record.getMaintenanceUserId()+"'";
    				List<XtglMaintenanceUsers> useUnitId=maintenanceUsersService.queryAll(hql);
    				if(useUnitId.size()>0){
    					records.setUserId(useUnitId.get(0));//维保人id
    					records.setUnitId(useUnitId.get(0).getUnitId());//维保单位id
    				}else{
    					records.setUserId(null);//维保人id
    					records.setUnitId(null);//维保单位id
    				}
    				
    				if(elevators.size()>0){
    					records.setUseUnitId(elevators.get(0).getUseUnitId());//使用单位id
    					records.setElevatorId(elevators.get(0));//维保电梯Id
    					
    					//修改电梯的维保状态和维保时间
        				DtjkElevator entity =elevatorService.get(elevators.get(0).getId());
        				entity.setMaintenanceTime(new Date());
        				entity.setState("故障");
        				entity.setMaintenanceState("正常");
        				elevatorService.update(entity);
    				}else{
    					records.setUseUnitId(null);//使用单位id
    					records.setElevatorId(null);//维保电梯Id
    				}
    				recordsService.save(records);
    			}
    			//维保人员不为空时，
    			if(record.getMaintenanceUserId()!=null&&!record.getMaintenanceUserId().equals("")&&record.getMaintenanceState().equals("正常")){
    				
    				if(elevators.size()>0){
	    				//修改电梯的维保状态和维保时间
	    				DtjkElevator entity =elevatorService.get(elevators.get(0).getId());
	    				entity.setMaintenanceTime(new Date());
	    				entity.setMaintenanceState("正常");
	    				entity.setState("正常");
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
    			
    		}  else if(type.equalsIgnoreCase("23")){    //上报静态数据
    			gateway.setElevatorId(elevatorId);
    			gateway.setSerialNumber(serialNumber);
    			//循环  解析命令
    			for( int i=70; i<str.length()-1; i=i ){
    				 String command =str.substring(i,i+2); //命令类型 
    				 String output = str.substring(i+2, (i + 4));//命令长度
    			     int decimal = Integer.parseInt(output, 16)*2;
    				String content =str.substring(i+4,i+4+decimal);
    				juageContent(command, content,gateway,record,ask);
    				
    				i=i+4+decimal;   //更改i
    				String end =str.substring(i,i+2); //判断是否结束
    				if(end.equalsIgnoreCase("F0")){
    					break;
    				}
    			}
    			
    			//根据终端号查询该终端是否已记录
    			String hql=" where  1=1 and serialNumber='"+gateway.getSerialNumber()+"' " ;
    			List<DtjkGateway> list=gatewayService.queryAll(hql);
    			
    			//num等于0 说明该终端未记录，重新保存
    			if(list.size()==0){
    				gatewayService.save(gateway);
    	//			String hql1="where registerid='"+elevatorId+"'";
    	//			List<DtjkElevator> elevators=elevatorService.query(hql1);
    				if(elevators.size()>0){
    					DtjkElevator elevator=elevators.get(0);
    					elevator.setGatewayId(gateway);
    					elevatorService.update(elevator);
    				}
    				
    			}else{//num不等于0 说明该终端已记录，重新修改
    				
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
    		}  else if(type.equalsIgnoreCase("21")){    //报警
    			
    			 
    			//循环  解析命令
    				 String command =str.substring(70,72); //故障代码
    				 String types=command;     //
    				 String order=warning(command);
    				 record.setType(order);
    				 command="故障类型："+ warning(command);
    				 System.out.println(command);
    				 
    				 String state =str.substring(72,74); //状态
    				 if(state.equalsIgnoreCase("00")){
    					 record.setDirection("上行");
    					 state="运行状态：上行";
    				 }else if(state.equalsIgnoreCase("01")){
    					 record.setDirection("下行");
    					 state="运行状态：下行";
    				 }else{
    					 record.setDirection("静止");
    					 state="运行状态：静止";
    				 }
					 System.out.println(state);

    				 String people  =str.substring(74,76); //有人
    				 if(people.equalsIgnoreCase("00")){
    					 record.setPeople("无人");
    					 people="	是否有人：无人";
    				 }else{
    					 record.setPeople("有人");
    					 people="	是否有人：有人";
    				 }
    				 System.out.println(people);
    				 String floor  =str.substring(76,78); //楼层
    				 record.setFloor(""+(byte)Integer.parseInt(floor,16));
    				 floor="	当前楼层："+  (byte)Integer.parseInt(floor,16);
    				 System.out.println(floor);				 
    				 
    				 String door  =str.substring(78,80); // 门
    				 if(door.equalsIgnoreCase("00")){
    					 record.setDoor("开门");
    					 door="	门状态：开门";
    				 }else{
    					 record.setDoor("关门");
    					 door=" 	门状态：关门";
    				 }
					 System.out.println(door);
					 String date  =str.substring(80,86); // 日期
					 date=dateString(hexToString(date)) ;
					 String time=str.substring(86,92);
					 time=timeString(hexToString(time));  //时间
					// System.out.println(d.format(new Date()));
					// System.out.println(d.format(d.parse("20"+date+" "+time)));
					 record.setSerialNumber(serialNumber);
					 record.setElevatorId(elevatorId);
					
					 
					 record.setGatewayDate(date);
					 record.setGatewayTime(time);
					 record.setFoundTime(new Date());
					//保存 上报的报警记录
		    			recordService.save(record);
    			  //自动生成 当前故障
    			  GzclFault fault=new GzclFault();
       			  fault.setHappenTime(d.parse("20"+date+" "+time));
       			  fault.setAlarmTime(new Date());
       			  fault.setType("自动接警");
       			  fault.setState("处理中");
       			  fault.setFaultType(order);
       			  fault.setFault(state+people+floor+door);
       			  fault.setDutyId(null);
      // 			String hql1=" where 1=1 and  registerid = '"+elevatorId+"'";
		//		List<DtjkElevator> elevators=elevatorService.queryAll(hql1);
				DtjkElevator list=null;
				DtjkPush push=new DtjkPush();
				if(elevators.size()>0){
					fault.setElevatorId(elevators.get(0));//维保电梯Id
					push.setElevatorId(elevators.get(0));
					list=elevators.get(0);
					list.setState("故障");
					elevatorService.update(list);	//修改电梯运行状态
					push.setRegisterid(elevatorId);
					push.setDistinguishid(serialNumber);
					push.setInstallPlace(list.getInstallPlace());
					push.setFaultType(command);
					push.setFlag("0");
					pushService.save(push);		//生成提醒记录
				}else{
					fault.setElevatorId(null);//维保电梯Id
					push.setElevatorId(null);
				}
				faultService.save(fault);		//生成当前故障
				
    				 try {
    					 //os.write("E0021101F0".getBytes());
    		       		 os.write(byteUtil.hexStringToByte("E0021101F0"));
    					CreateWorkbook("E0021101F0");
    				} catch (IOException e) {
    					e.printStackTrace();
    				}
    			//生成报警短信 
    				if(elevators.size()>0){
    					Message.addMessage(types,order,elevators.get(0));
    				}
    				
    		}  else if(type.equalsIgnoreCase("22")){    //请求数据	
    			gateway.setElevatorId(elevatorId);
    			gateway.setSerialNumber(serialNumber);
    			//循环  解析命令
    			for( int i=70; i<str.length()-1; i=i ){
    				 String command =str.substring(i,i+2); //命令类型 
    				 String output = str.substring(i+2, (i + 4));//命令长度
    			     int decimal = Integer.parseInt(output, 16)*2;
    				String content =str.substring(i+4,i+4+decimal);
    				juageContent(command, content,gateway,record,ask);
    				
    				i=i+4+decimal;   //更改i
    				String end =str.substring(i,i+2); //判断是否结束
    				if(end.equalsIgnoreCase("F0")){
    					break;
    				}
    			}
    			//生成发送命令
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
    				 	//根据终端号查询该终端是否已记录
    	     			String hql=" where  1=1 and serialNumber='"+gateway.getSerialNumber()+"' " ;
    	     			List<DtjkGateway>  gateways=gatewayService.queryAll(hql);
    	     			//num等于0 说明该终端未记录，重新保存
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
    		     		 
    		     	//  白名单
    	    			 if(ask.getWhite1()!=null){
    		     		 	//0X63  白名单1
	    		     		if(phones.size()>0){
	    		     			DtjkPhone phone=phones.get(0);
	    		     			date=convertStringToHex(phone.getPhone());
	    		     			m+="630b"+date;
	    		     		}else{
	    		     			m+="630b0000000000000000000000";
	    		     		}
    	    			 }
    	    			//  白名单
    	    			 if(ask.getWhite2()!=null){
	    		     		//0X64  白名单2
	    		     		if(phones.size()>1){
	    		     			DtjkPhone phone=phones.get(1);
	    		     			date=convertStringToHex(phone.getPhone());
	    		     			m+="640b"+date;
	    		     		}else{
	    		     			m+="640b0000000000000000000000";
	    		     		}
    	    			 }
	    		     	//  白名单
	    	    			 if(ask.getWhite3()!=null){
		    		     		//0X65  白名单3
		    		     		if(phones.size()>2){
		    		     			DtjkPhone phone=phones.get(2);
		    		     			date=convertStringToHex(phone.getPhone());
		    		     			m+="650b"+date;
		    		     		}else{
		    		     			m+="650b0000000000000000000000";
		    		     		}
	    	    			 }
	    		     	//  白名单
	    	    			 if(ask.getWhite4()!=null){
		    		     		//0X66  白名单4
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
    			System.out.println("发送请求命令："+m.toUpperCase());
       			//os.write(m.getBytes());
       			os.write(byteUtil.hexStringToByte(m.toUpperCase()));
				CreateWorkbook(m.toUpperCase());
    		}else{   
    			try {
    				 System.out.println("命令错误：E0021102F0");
    				 os.write(byteUtil.hexStringToByte("E0021102F0"));
    				CreateWorkbook("E0021102F0");
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		}
        }else{
        	 System.out.println("长度错误：E0021103F0");
			 os.write(byteUtil.hexStringToByte("E0021103F0"));
				CreateWorkbook("E0021103F0");
        }
        	
       
		
	
	}
	
	//十六进制ascii 加密
	 public static String convertStringToHex(String str){
		  char[] chars = str.toCharArray();
		  StringBuffer hex = new StringBuffer();
		  for(int i = 0; i < chars.length; i++){
		    hex.append(Integer.toHexString((int)chars[i]));
		  }

		  return hex.toString();
		  }
	 
	 //十六进制ascii 解析
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

	 //十六进制ascii 解析
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
	//十六进制ascii 解析
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
	  * 判断类型（上报，报警，请求数据）
	  * @param type
	  * @return
	  */
	 public String judgeType(String type){
		 String name="";
		 if(type.equals("20")){
			 name="上报运行数据";
		 }else if(type.equals("21")){
			 name="报警";
		 }else if(type.equals("23")){
			 name="上报静态数据";
		 }else{
			 name="请求数据";
		 }
		 return name;
	 }
	 
	 /**
	  * 0×20，0×23 上报  解析 命令以及对应的内容
	  * @param command
	  * @param content
	 * @throws ParseException 
	  */
	 public void juageContent(String command,String content,DtjkGateway gateway,DtjkRecord record,Ask ask) throws ParseException {
		 //地上楼层
		if(command.equalsIgnoreCase("01")){
			int floor=Integer.parseInt(content, 16);
			gateway.setFloor(Integer.toString(floor));
			System.out.println("总楼层："+floor);
		}
		 //地上楼层
		if(command.equalsIgnoreCase("03")){
			int floor=Integer.parseInt(content, 16);
			gateway.setUpper(Integer.toString(floor));
			System.out.println("地上楼层："+floor);
		}
		 //地下楼层
		if(command.equalsIgnoreCase("04")){
			int floor=Integer.parseInt(content, 16);
			gateway.setLower(Integer.toString(floor));
			System.out.println("地下楼层："+floor);
		}
		 //设定速度
		if(command.equalsIgnoreCase("02")){
			String velocity =convertHexToString(content);
			gateway.setSpeed(velocity);
			System.out.println("设定速度："+velocity+"mm/s");
		}
		 //层间距
		if(command.equalsIgnoreCase("20")){
			int floor=Integer.parseInt(content, 16);
			gateway.setSpacing(floor+"mm/s");
			System.out.println("层间距："+floor+"mm");
		}
		//终端SIM
		if(command.equalsIgnoreCase("21")){
			String velocity =convertHexToString(content);
			gateway.setSim(velocity);
			System.out.println("终端SIM："+velocity);
		}
		 //硬件版本
		if(command.equalsIgnoreCase("22")){
			String velocity =convertHexToString(content);
			gateway.setHardware(velocity);
			System.out.println("硬件版本："+velocity);
		}
		 //软件版本
		if(command.equalsIgnoreCase("23")){
			String velocity =convertHexToString(content);
			gateway.setSoftware(velocity);
			System.out.println("软件版本："+velocity);
		}
		 //语音/视频
		if(command.equalsIgnoreCase("24")){
			int floor=Integer.parseInt(content, 16);
			if(floor==0){
				gateway.setType("语音版");
				System.out.println("语音/视频：语音版");
			}else{
				gateway.setType("视频版");
				System.out.println("语音/视频：视频版");
			}
			
		}
		 //联网方式
		if(command.equalsIgnoreCase("25")){
			int floor=Integer.parseInt(content, 16);
			if(floor==0){
				gateway.setNetworking("GPRS");
				System.out.println("联网方式：GPRS");
			}else{
				gateway.setNetworking("WIFI");
				System.out.println("联网方式：WIFI");
			}
			
		}
		 //运行方向
		if(command.equalsIgnoreCase("30")){
			int floor=Integer.parseInt(content, 16);
			if(floor==0){
				record.setDirection("上行");
				System.out.println("运行方向：上行");
			}else if(floor==1){
				record.setDirection("下行");
				System.out.println("运行方向：下行");
			}else{
				record.setDirection("静止");
				System.out.println("运行方向：静止");
			}
		}
		 //运行速度
		if(command.equalsIgnoreCase("32")){
			String velocity =convertHexToString(content);
			record.setSpeed(velocity+"mm/s");
			System.out.println("运行速度："+velocity+"mm/s");
		}
		 //当前楼层
		if(command.equalsIgnoreCase("33")){
			int floor = (byte) Integer.parseInt(content, 16); 
//			if(floor<=0)
//				floor=floor-1;
			record.setFloor(Integer.toString(floor));
			System.out.println("当前楼层："+floor);
		}
		//终端日期
		if(command.equalsIgnoreCase("37")){
			String date=hexToString(content);
			 date=dateString(date) ;
			record.setGatewayDate("20"+date);
			System.out.println("终端日期："+"20"+date);
		}
		//终端时间
		if(command.equalsIgnoreCase("38")){
			String time=hexToString(content);
			time=timeString(time) ;
			record.setGatewayTime(time);
			System.out.println("终端时间："+time);
		}
		
		//人
		if(command.equalsIgnoreCase("39")){
			int floor=Integer.parseInt(content, 16);
			if(floor==0){
				record.setPeople("没人");
				System.out.println("人：没人");
			}else{
				record.setPeople("有人");
				System.out.println("人：有人");
			}
		}
		//门
		if(command.equalsIgnoreCase("3a")){
			int floor=Integer.parseInt(content, 16);
			if(floor==0){
				record.setDoor("开门");
				System.out.println("门 ：开门");
			}else{
				record.setDoor("关门");
				System.out.println("门：关门");
			}
		}
		//一切正常
		if(command.equalsIgnoreCase("3c")){
			int floor=Integer.parseInt(content, 16);
			record.setHeartbeat(Integer.toString(floor));
			System.out.println("一切正常 ："+floor);
			
		}
		 //现场维保人员
		if(command.equalsIgnoreCase("50")){
			String velocity =convertHexToString(content);
			record.setMaintenanceUserId(velocity);
			System.out.println("现场维保人员："+velocity);
		}
		//检修状态
		if(command.equalsIgnoreCase("51")){
			int floor=Integer.parseInt(content, 16);
			if(floor==0){
				record.setMaintenanceState("检修中");
				System.out.println("检修状态 ：检修中");
			}else{
				record.setMaintenanceState("正常");
				System.out.println("检修状态：正常");
			}
		}
		//终端日期
		if(command.equalsIgnoreCase("60")){
			 String date=hexToString(content);
			 date=dateString(date) ;
			 ask.setDates(date);
			 System.out.println("日期："+date);
		}
		//终端时间
		if(command.equalsIgnoreCase("61")){
			String time=hexToString(content);
			time=timeString(time) ;
			ask.setTime(time);
			System.out.println("时间："+time);
		}
		//上报周期
		if(command.equalsIgnoreCase("62")){
			String velocity =convertHexToString(content);
			gateway.setReport(velocity);
			ask.setPeriod(velocity);
			System.out.println("上报周期 ："+velocity+"秒");
			
		}
		//白名单1
		if(command.equalsIgnoreCase("63")){
			String velocity =convertHexToString(content);
			ask.setWhite1(velocity);
			System.out.println("白名单1："+velocity);
		}
		//白名单2
		if(command.equalsIgnoreCase("64")){
			String velocity =convertHexToString(content);
			ask.setWhite2(velocity);
			System.out.println("白名单2："+velocity);
		}
		//白名单3
		if(command.equalsIgnoreCase("65")){
			String velocity =convertHexToString(content);
			ask.setWhite3(velocity);
			System.out.println("白名单3："+velocity);
		}
		//白名单4
		if(command.equalsIgnoreCase("66")){
			String velocity =convertHexToString(content);
			ask.setWhite4(velocity);
			System.out.println("白名单4："+velocity);
		}
		
	}
	 
	
	 
	/**
	 * 0×21  判断故障类型
	 * @param type
	 * @return
	 */
	 public String warning(String type){
		 String name="";
		 if(type.equals("0")){
			 name="正常";
		 }else if(type.equals("01")){
			 name="超速";
		 }else if(type.equals("02")){
			 name="困人";
		 }else if(type.equals("03")){
			 name="门关不上";
		 }else if(type.equals("04")){
			 name="冲顶困人";
		 }else if(type.equals("05")){
			 name="冲顶";
		 }else if(type.equals("06")){
			 name="蹲底困人";
		 }else if(type.equals("07")){
			 name="蹲底";
		 }else if(type.equals("08")){
			 name="开门走梯";
		 }else if(type.equals("09")){
			 name="运动中开门";
		 }else if(type.equals("0a")){
			 name="非平层困人";
		 }else if(type.equals("0b")){
			 name="非平层停梯";
		 }else if(type.equals("0c")){
			 name="停电";
		 }else if(type.equals("0d")){
			 name="开门不到位";
		 }else if(type.equals("10")){
			 name="非平层开门";
		 }else{
			 return name;
		 }
		 return name;
	 }
	//日期
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
	//时间
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
	
	 
	//判断命令长度
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
		 * java 写人txt文本
		 * @param content		写入内容
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
		 * 向excel中追加数据
		 * @param a
		 */
		public static void CreateWorkbook(String cmd) {
			SimpleDateFormat d=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 String[] a={d.format(new Date()),type,elevatorId,str,cmd};
			String url="D:/网关通信命令.xls";
			File file=new File(url);
	        try {
	            if (!file.exists()) { //判断文件是否已存在，如果没有存在则创建新文件
	            	export(url);
	            } 
	            	// 输出流  
	            	   FileInputStream is = new FileInputStream(file);  
	            	   HSSFWorkbook wb =new HSSFWorkbook(is); 
	            	   HSSFCellStyle style = wb.createCellStyle(); // 样式对象
		   //    			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
		   //    			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平
	            	   HSSFSheet sheet1 = wb.getSheetAt(0);  
	            	   HSSFRow row = sheet1.createRow(sheet1.getLastRowNum() + 1);  
	            	   row.setRowStyle(style);
	            	   row.setHeightInPoints((short) 25);  
	            	   // 给这一行赋值  
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
		 * 创建一个excel
		 * @param filePath 创建路径
		 */
		public static void export(String filePath) {
			try {
				HSSFWorkbook workbook = new HSSFWorkbook();
				// 在Excel工作簿中建一工作表，其名为缺省值
				HSSFSheet sheet = null;
				HSSFRow row = null;
				HSSFCell cell = null;
				sheet = workbook.createSheet("网关通信信息");
				HSSFCellStyle style = workbook.createCellStyle(); // 样式对象
				style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平
				/**
				 * 设置字体
				 */
				HSSFFont f = workbook.createFont();
				f.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 字体加粗
				style.setFont(f);		
				String[] top_arraydis = {"时间","命令类型","电梯注册号","原始命令","回复命令"};
					
					row = sheet.createRow(0);// 创建一行
					for (int c = 0; c < top_arraydis.length; c++) {
						cell = row.createCell((short) c);// 创建格 字段 
						cell.setCellValue("中文");
						cell.setCellStyle(style);
						cell.setCellValue(top_arraydis[c]);
					}
					
				// 新建一输出文件流
					sheet.setColumnWidth((short)0,(short)5000);
					sheet.setColumnWidth((short)1,(short)5000); 
					sheet.setColumnWidth((short)2,(short)7000); 
					sheet.setColumnWidth((short)3,(short)20000); 
					sheet.setColumnWidth((short)4,(short)5000); 
				FileOutputStream fOut = new FileOutputStream(filePath);
				workbook.write(fOut);
				fOut.flush();
				// 操作结束，关闭文件
				fOut.close();
				return;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("已运行 xlCreate() : " + e);
			}
			
		}
}
