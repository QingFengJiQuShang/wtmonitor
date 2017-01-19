package com.jrsoft.fri.quartz;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import smart.sys.platform.dao.DBEntity;

public class FlowJob   implements Job {
	/**
	 * 计算电梯流量
	 */

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+ "启动定时任务：计算昨天的电梯流量");  
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		Calendar c =  Calendar.getInstance();
		c.setTime(new Date());
		String time=df.format(new Date());
		c.add(Calendar.DAY_OF_MONTH, -1);
		String yesterday=df.format(c.getTime());
		try {
			//修改 服务期内的电梯保险状态
			String	sql="update DTJK_ELEVATOR t  set t.flow_surplus = t.flow_surplus -( ( select x.dictionary * (select count(*) from dtjk_record de where elevator_id =t.registerid and de.found_time >=to_date('"+yesterday+"','yyyy-MM-dd') and de.found_time <=to_date('"+time+"','yyyy-MM-dd') )  from Xtsz_Dictionary x where x.flag = '1') / 1024) where t.delflag!='1' ";
			System.out.println(sql);
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
