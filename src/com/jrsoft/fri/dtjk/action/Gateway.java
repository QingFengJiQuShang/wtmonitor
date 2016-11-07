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
		
		DtjkGateway gateway=new DtjkGateway();   //终端静态记录
		DtjkRecord record=new DtjkRecord();     //终端上报记录
		
		String type=str.substring(6,8); 
		System.out.println("数据类型："+judgeType(type));
		
		String elevatorId=str.substring(8,48); 
		 elevatorId=convertHexToString(elevatorId);
		System.out.println("电梯id："+elevatorId);
		
		String serialNumber=str.substring(48,70); 
		serialNumber=convertHexToString(serialNumber);
		System.out.println("网关id："+serialNumber);
		
	
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
				juageContent(command, content,gateway,record);
				
				i=i+4+decimal;   //更改i
				String end =str.substring(i,i+2); //判断是否结束
				if(end.equalsIgnoreCase("f0")){
					break;
				}
			}
			//保存 上报的运行数据
			recordService.save(record);
			
		}  else if(type.equalsIgnoreCase("23")){    //上报静态数据
			gateway.setElevatorId(elevatorId);
			gateway.setSerialNumber(serialNumber);
			//循环  解析命令
			for( int i=70; i<str.length()-1; i=i ){
				 String command =str.substring(i,i+2); //命令类型 
				 String output = str.substring(i+2, (i + 4));//命令长度
			     int decimal = Integer.parseInt(output, 16)*2;
				String content =str.substring(i+4,i+4+decimal);
				juageContent(command, content,gateway,record);
				
				i=i+4+decimal;   //更改i
				String end =str.substring(i,i+2); //判断是否结束
				if(end.equalsIgnoreCase("f0")){
					break;
				}
			}
			
			//根据终端号查询该终端是否已记录
			String hql=" where  1=1 and serialNumber='"+gateway.getSerialNumber()+"' " ;
			List<DtjkGateway> list=gatewayService.queryAll(hql);
			
			//num等于0 说明该终端未记录，重新保存
			if(list.size()==0){
				gatewayService.save(gateway);
				String hql1="where registerid='"+elevatorId+"'";
				List<DtjkElevator> elevators=elevatorService.query(hql1);
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
			
		}  else if(type.equalsIgnoreCase("21")){    //报警
			//循环  解析命令
				 String command =str.substring(70,72); //故障代码
				 System.out.println("故障类型："+ warning(command));
				 
				 String state =str.substring(72,74); //状态
				 if(state.equalsIgnoreCase("00")){
					 System.out.println("状态：上行");
				 }else if(state.equalsIgnoreCase("01")){
					 System.out.println("状态：下行");
				 }else{
					 System.out.println("状态：静止");
				 }
				 
				 String people  =str.substring(74,76); //有人
				 if(people.equalsIgnoreCase("00")){
					 System.out.println("人：无人");
				 }else{
					 System.out.println("人：有人");
				 }
				 
				 String floor  =str.substring(76,78); //楼层
				 System.out.println("楼层："+ Integer.parseInt(floor,16) );				 
				 
				 String door  =str.substring(78,80); // 门
				 if(door.equalsIgnoreCase("00")){
					 System.out.println("门：开门");
				 }else{
					 System.out.println("门：关门");
				 }
				 
				 try {
					 os.write("e00101e0".getBytes());
				} catch (IOException e) {
					e.printStackTrace();
				}
				
		}else{   //请求数据
			try {
				 os.write("e0XX113331313034333133303032303037313030303032313031363038303130303140630b3132333435363738393132630b3132333435363738313233e0".getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
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
	  */
	 public void juageContent(String command,String content,DtjkGateway gateway,DtjkRecord record) {
		
		 //总楼层
		if(command.equalsIgnoreCase("01")){
			int floor=Integer.parseInt(content, 16);
			gateway.setFloor(Integer.toString(floor));
			System.out.println("总楼层："+floor);
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
				record.setDirection("上");
				System.out.println("运行方向：上");
			}else{
				record.setDirection("下");
				System.out.println("运行方向：下");
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
			int floor=Integer.parseInt(content, 16);
			record.setFloor(Integer.toString(floor));
			System.out.println("当前楼层："+floor);
		}
		//终端日期
		if(command.equalsIgnoreCase("37")){
			int floor=Integer.parseInt(content, 16);
			record.setGatewayDate(Integer.toString(floor));
			System.out.println("终端日期："+floor);
		}
		//终端时间
		if(command.equalsIgnoreCase("38")){
			int floor=Integer.parseInt(content, 16);
			record.setGatewayTime(Integer.toString(floor));
			System.out.println("终端时间："+floor);
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
			int floor=Integer.parseInt(content, 16);
			System.out.println("日期："+floor);
		}
		//终端时间
		if(command.equalsIgnoreCase("61")){
			int floor=Integer.parseInt(content, 16);
			System.out.println("时间："+floor);
		}
		//上报周期
		if(command.equalsIgnoreCase("62")){
			int floor=Integer.parseInt(content, 16);
			gateway.setReport(Integer.toString(floor));
			System.out.println("上报周期 ："+floor+"秒");
			
		}
		//白名单1
		if(command.equalsIgnoreCase("63")){
			String velocity =convertHexToString(content);
			System.out.println("白名单1："+velocity);
		}
		//白名单2
		if(command.equalsIgnoreCase("64")){
			String velocity =convertHexToString(content);
			System.out.println("白名单2："+velocity);
		}
		//白名单3
		if(command.equalsIgnoreCase("65")){
			String velocity =convertHexToString(content);
			System.out.println("白名单3："+velocity);
		}
		//白名单4
		if(command.equalsIgnoreCase("66")){
			String velocity =convertHexToString(content);
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
		 
		 if(type.equals("01")){
			 name="超速";
		 }else if(type.equals("02")){
			 name="运行中开门";
		 }else if(type.equals("03")){
			 name="开门走梯";
		 }else if(type.equals("04")){
			 name="门关不上";
		 }else if(type.equals("05")){
			 name="困人";
		 }else if(type.equals("06")){
			 name="待机太长（死机）";
		 }else if(type.equals("07")){
			 name="非平层停梯";
		 }else if(type.equals("08")){
			 name="非平层困人";
		 }else if(type.equals("09")){
			 name="冲顶";
		 }else if(type.equals("0a")){
			 name="冲顶困人";
		 }else if(type.equals("0b")){
			 name="遵底";
		 }else if(type.equals("0c")){
			 name="遵底困人";
		 }else if(type.equals("20")){
			 name="运行";
		 }else if(type.equals("21")){
			 name="静止";
		 }else if(type.equals("22")){
			 name="开门";
		 }else if(type.equals("23")){
			 name="关门";
		 }else if(type.equals("24")){
			 name="有人";
		 }else if(type.equals("25")){
			 name="无人";
		 }else{
			 return name;
		 }
		 return name;
	 }

}
