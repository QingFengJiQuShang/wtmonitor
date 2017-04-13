package com.jrsoft.fri.quartz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import smart.sys.platform.dao.DBEntity;

public class ServiceState implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+ "启动定时任务：判断并修改电梯的服务状态");  
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		String time=df.format(new Date());
		try {
			//修改 服务期内的电梯服务状态
			String	sql="update DTJK_ELEVATOR t set t.service_State='1' where (select count(ds.id) from Dtjk_Service ds where ds.elevator_id = t.id and ds.start_Time <= to_date('"+time+"', 'yyyy-MM-dd') and ds.end_Time >= to_date('"+time+"', 'yyyy-MM-dd')) > 0 and t.delflag!='1' ";
			DBEntity.getInstance().executeSql(sql);
			//修改 不在服务期内的电梯服务状态
			sql="update DTJK_ELEVATOR t set t.service_State='0' where (select count(ds.id) from Dtjk_Service ds where ds.elevator_id = t.id and ds.start_Time <= to_date('"+time+"', 'yyyy-MM-dd') and ds.end_Time >= to_date('"+time+"', 'yyyy-MM-dd')) = 0 and t.delflag!='1' ";
			DBEntity.getInstance().executeSql(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
