package com.jrsoft.fri.quartz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.jrsoft.fri.dtjk.entity.DtjkElevator;
import com.jrsoft.fri.dtjk.service.DtjkElevatorService;
import com.jrsoft.fri.xtgl.action.Authority;
import com.jrsoft.fri.xtgl.service.XtglMaintenanceUsersService;
import com.jrsoft.fri.xtgl.service.XtglUseUnitService;
import com.jrsoft.fri.xtgl.service.XtglUsersService;
import com.jrsoft.fri.xtsz.action.Message;
import com.jrsoft.fri.xtsz.service.XtszDictionaryService;
import com.jrsoft.fri.xtsz.service.XtszMessageService;

import smart.sys.platform.dao.DBEntity;
import smart.sys.platform.springUtils.SpringBeanUtil;

public class MessageJob    implements Job {
	private static XtszMessageService messageService = (XtszMessageService)SpringBeanUtil.getBean("messageService");
	private static DtjkElevatorService elevatorService = (DtjkElevatorService)SpringBeanUtil.getBean("elevatorService");
	private static XtglUseUnitService useUnitService = (XtglUseUnitService)SpringBeanUtil.getBean("useUnitService");
	private static XtglMaintenanceUsersService maintenanceUsersService = (XtglMaintenanceUsersService)SpringBeanUtil.getBean("maintenanceUsersService");
	private static XtglUsersService usersService = (XtglUsersService)SpringBeanUtil.getBean("usersService");
	private static XtszDictionaryService dictionaryService = (XtszDictionaryService)SpringBeanUtil.getBean("dictionaryService");

	
	/**
	 * 定时自动生成短信
	 */

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+ "启动定时任务：根据条件自动生成短信");  
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat d=new SimpleDateFormat("yyyy年MM月dd日");
		Calendar c =  Calendar.getInstance();
		c.setTime(new Date());
		String time=df.format(new Date());
		try {
			Connection conn=DBEntity.getInstance().getConnection();
			//服务费到期短信提醒   提前一个月提醒
			String sql="select de.*,g.sim as sim," +
					" xuu.name as useUnitName,xuu.phone as useUnitPhone, " +
					" xmu.name as  maintenanceUnitName,xmu.phone as maintenanceUnitPhone," +
					" mu.name as  maintenanceUserName,mu.phone as maintenanceUserPhone," +
					" xpu.name as  propertyUnitName,xpu.phone as propertyUnitPhone," +
					" make.name as  makeUnitName,make.phone as makeUnitPhone," +
					" xu.name as  usersName,xu.phone as usersPhone " +
			" from dtjk_elevator de " +
			" left join Dtjk_gateway g on g.elevator_Id=de.registerid "+  //网关
			" left join xtgl_use_unit xuu on xuu.id=de.use_unit_id "+  //使用单位
			" left join xtgl_maintenance_unit xmu on xmu.id=de.maintenance_unit_id"+  //维保单位
			" left join xtgl_maintenance_users mu on mu.id=de.maintenance_users_id"+  //维保人
			" left join Xtgl_Property_Unit xpu on xpu.id=de.property_Unit_Id"+  //物业单位
			" left join Xtgl_Make_Unit make on make.id=de.make_Unit_Id"+  //制造单位
			" left join Xtgl_Users xu on xu.id=de.userid"+  //区域用户
			" where  1=1  and de.delflag!='1' " ;
			//年检到期短信提醒
			c.add(Calendar.DATE, 30);  //当时间加30天
			String sql1=sql+" and de.next_Time>=to_date('" + time+ "','yyyy-MM-dd') and de.next_Time<=to_date('" + df.format(c.getTime())+ "','yyyy-MM-dd')";
			PreparedStatement sta = conn.prepareStatement(sql1);
			ResultSet rs = sta.executeQuery();
			while(rs.next()){
				//判断使用单位是否拥有年检到期短信提醒权限
				if(Authority.haveMessage("sy_nj")){
					Message.addMessage("年检短信", rs.getString("useUnitName")==null?"":rs.getString("useUnitName"), rs.getString("useUnitPhone")==null?"":rs.getString("useUnitPhone"), rs.getString("install_Place")==null?"":rs.getString("install_Place"), rs.getString("distinguishid")==null?"":rs.getString("distinguishid"), rs.getString("sim")==null?"":rs.getString("sim"),rs.getString("next_Time")==null?"":d.format(df.parse(rs.getString("next_Time"))) ,"");
				}
				//判断物业位是否拥有年检到期短信提醒权限
				if(Authority.haveMessage("wy_nj")){
					Message.addMessage("年检短信", rs.getString("propertyUnitName")==null?"":rs.getString("propertyUnitName"), rs.getString("propertyUnitPhone")==null?"":rs.getString("propertyUnitPhone"), rs.getString("install_Place")==null?"":rs.getString("install_Place"), rs.getString("distinguishid")==null?"":rs.getString("distinguishid"), rs.getString("sim")==null?"":rs.getString("sim"),rs.getString("next_Time")==null?"":d.format(df.parse(rs.getString("next_Time"))) ,"");

				}
				//判断维保单位是否拥有年检到期短信提醒权限
				if(Authority.haveMessage("wb_nj")){
					Message.addMessage("年检短信", rs.getString("maintenanceUnitName")==null?"":rs.getString("maintenanceUnitName"), rs.getString("maintenanceUnitPhone")==null?"":rs.getString("maintenanceUnitPhone"), rs.getString("install_Place")==null?"":rs.getString("install_Place"), rs.getString("distinguishid")==null?"":rs.getString("distinguishid"), rs.getString("sim")==null?"":rs.getString("sim"),rs.getString("next_Time")==null?"":d.format(df.parse(rs.getString("next_Time"))) ,"");

				}
				//判断维保人是否拥有年检到期短信提醒权限
				if(Authority.haveMessage("wbr_nj")){
					Message.addMessage("年检短信", rs.getString("maintenanceUserName")==null?"":rs.getString("maintenanceUserName"), rs.getString("maintenanceUserPhone")==null?"":rs.getString("maintenanceUserPhone"), rs.getString("install_Place")==null?"":rs.getString("install_Place"), rs.getString("distinguishid")==null?"":rs.getString("distinguishid"), rs.getString("sim")==null?"":rs.getString("sim"),rs.getString("next_Time")==null?"":d.format(df.parse(rs.getString("next_Time"))) ,"");

				}
				//判断制造单位是否拥有年检到期短信提醒权限
				if(Authority.haveMessage("zz_nj")){
					Message.addMessage("年检短信", rs.getString("makeUnitName")==null?"":rs.getString("makeUnitName"), rs.getString("makeUnitPhone")==null?"":rs.getString("makeUnitPhone"), rs.getString("install_Place")==null?"":rs.getString("install_Place"), rs.getString("distinguishid")==null?"":rs.getString("distinguishid"), rs.getString("sim")==null?"":rs.getString("sim"),rs.getString("next_Time")==null?"":d.format(df.parse(rs.getString("next_Time"))) ,"");

				}
				//判断区域用户是否拥有年检到期短信提醒权限
				if(Authority.haveMessage("xt_nj")){
					Message.addMessage("年检短信", rs.getString("usersName")==null?"":rs.getString("usersName"), rs.getString("usersPhone")==null?"":rs.getString("usersPhone"), rs.getString("install_Place")==null?"":rs.getString("install_Place"), rs.getString("distinguishid")==null?"":rs.getString("distinguishid"), rs.getString("sim")==null?"":rs.getString("sim"),rs.getString("next_Time")==null?"":d.format(df.parse(rs.getString("next_Time"))) ,"");

				}
			}
			
			/**
			 * 流量不足10M时，短信提醒
			 */
			 sql1=sql+" and de.flow_Surplus<='10'  ";
			 sta = conn.prepareStatement(sql1);
			 rs = sta.executeQuery();
				while(rs.next()){
					//判断使用单位是否拥有年检到期短信提醒权限
					if(Authority.haveMessage("sy_ll")){
						Message.addMessage("流量短信", rs.getString("useUnitName")==null?"":rs.getString("useUnitName"), rs.getString("useUnitPhone")==null?"":rs.getString("useUnitPhone"), rs.getString("install_Place")==null?"":rs.getString("install_Place"), rs.getString("distinguishid")==null?"":rs.getString("distinguishid"), rs.getString("sim")==null?"":rs.getString("sim"),rs.getString("next_Time")==null?"":d.format(df.parse(rs.getString("next_Time"))) ,"");
					}
					//判断物业位是否拥有年检到期短信提醒权限
					if(Authority.haveMessage("wy_ll")){
						Message.addMessage("流量短信", rs.getString("propertyUnitName")==null?"":rs.getString("propertyUnitName"), rs.getString("propertyUnitPhone")==null?"":rs.getString("propertyUnitPhone"), rs.getString("install_Place")==null?"":rs.getString("install_Place"), rs.getString("distinguishid")==null?"":rs.getString("distinguishid"), rs.getString("sim")==null?"":rs.getString("sim"),rs.getString("next_Time")==null?"":d.format(df.parse(rs.getString("next_Time"))) ,"");

					}
					//判断维保单位是否拥有年检到期短信提醒权限
					if(Authority.haveMessage("wb_ll")){
						Message.addMessage("流量短信", rs.getString("maintenanceUnitName")==null?"":rs.getString("maintenanceUnitName"), rs.getString("maintenanceUnitPhone")==null?"":rs.getString("maintenanceUnitPhone"), rs.getString("install_Place")==null?"":rs.getString("install_Place"), rs.getString("distinguishid")==null?"":rs.getString("distinguishid"), rs.getString("sim")==null?"":rs.getString("sim"),rs.getString("next_Time")==null?"":d.format(df.parse(rs.getString("next_Time"))) ,"");

					}
					//判断维保人是否拥有年检到期短信提醒权限
					if(Authority.haveMessage("wbr_ll")){
						Message.addMessage("流量短信", rs.getString("maintenanceUserName")==null?"":rs.getString("maintenanceUserName"), rs.getString("maintenanceUserPhone")==null?"":rs.getString("maintenanceUserPhone"), rs.getString("install_Place")==null?"":rs.getString("install_Place"), rs.getString("distinguishid")==null?"":rs.getString("distinguishid"), rs.getString("sim")==null?"":rs.getString("sim"),rs.getString("next_Time")==null?"":d.format(df.parse(rs.getString("next_Time"))) ,"");

					}
					//判断制造单位是否拥有年检到期短信提醒权限
					if(Authority.haveMessage("zz_ll")){
						Message.addMessage("流量短信", rs.getString("makeUnitName")==null?"":rs.getString("makeUnitName"), rs.getString("makeUnitPhone")==null?"":rs.getString("makeUnitPhone"), rs.getString("install_Place")==null?"":rs.getString("install_Place"), rs.getString("distinguishid")==null?"":rs.getString("distinguishid"), rs.getString("sim")==null?"":rs.getString("sim"),rs.getString("next_Time")==null?"":d.format(df.parse(rs.getString("next_Time"))) ,"");

					}
					//判断区域用户是否拥有年检到期短信提醒权限
					if(Authority.haveMessage("xt_ll")){
						Message.addMessage("流量短信", rs.getString("usersName")==null?"":rs.getString("usersName"), rs.getString("usersPhone")==null?"":rs.getString("usersPhone"), rs.getString("install_Place")==null?"":rs.getString("install_Place"), rs.getString("distinguishid")==null?"":rs.getString("distinguishid"), rs.getString("sim")==null?"":rs.getString("sim"),rs.getString("next_Time")==null?"":d.format(df.parse(rs.getString("next_Time"))) ,"");

					}
				}
				
				/**
				 * 服务费到期前30天，进行短信提醒
				 */
				 sql1=sql+" and de.service_State='1'  ";
				 sta = conn.prepareStatement(sql1);
				 rs = sta.executeQuery();
					while(rs.next()){
						
						String sql2="select trunc(t.end_time - sysdate) from DTJK_SERVICE t where t.elevator_id = '"+rs.getLong("id")+"'	 and t.start_time <= sysdate and t.end_time >= sysdate and trunc(t.end_time - sysdate)<'30' order by id	 ";
						Object day=DBEntity.getInstance().queryUniqueData(sql2);
						if(day!=null&&!day.equals("")){
							//判断使用单位是否拥有年检到期短信提醒权限
							if(Authority.haveMessage("sy_fwf")){
								Message.addMessage("服务费到期提醒", rs.getString("useUnitName")==null?"":rs.getString("useUnitName"), rs.getString("useUnitPhone")==null?"":rs.getString("useUnitPhone"), rs.getString("install_Place")==null?"":rs.getString("install_Place"), rs.getString("distinguishid")==null?"":rs.getString("distinguishid"), rs.getString("sim")==null?"":rs.getString("sim"),rs.getString("next_Time")==null?"":d.format(df.parse(rs.getString("next_Time"))) ,day.toString());
							}
							//判断物业位是否拥有年检到期短信提醒权限
							if(Authority.haveMessage("wy_fwf")){
								Message.addMessage("服务费到期提醒", rs.getString("propertyUnitName")==null?"":rs.getString("propertyUnitName"), rs.getString("propertyUnitPhone")==null?"":rs.getString("propertyUnitPhone"), rs.getString("install_Place")==null?"":rs.getString("install_Place"), rs.getString("distinguishid")==null?"":rs.getString("distinguishid"), rs.getString("sim")==null?"":rs.getString("sim"),rs.getString("next_Time")==null?"":d.format(df.parse(rs.getString("next_Time"))) ,day.toString());

							}
							//判断维保单位是否拥有年检到期短信提醒权限
							if(Authority.haveMessage("wb_fwf")){
								Message.addMessage("服务费到期提醒", rs.getString("maintenanceUnitName")==null?"":rs.getString("maintenanceUnitName"), rs.getString("maintenanceUnitPhone")==null?"":rs.getString("maintenanceUnitPhone"), rs.getString("install_Place")==null?"":rs.getString("install_Place"), rs.getString("distinguishid")==null?"":rs.getString("distinguishid"), rs.getString("sim")==null?"":rs.getString("sim"),rs.getString("next_Time")==null?"":d.format(df.parse(rs.getString("next_Time"))) ,day.toString());

							}
							//判断维保人是否拥有年检到期短信提醒权限
							if(Authority.haveMessage("wbr_fwf")){
								Message.addMessage("服务费到期提醒", rs.getString("maintenanceUserName")==null?"":rs.getString("maintenanceUserName"), rs.getString("maintenanceUserPhone")==null?"":rs.getString("maintenanceUserPhone"), rs.getString("install_Place")==null?"":rs.getString("install_Place"), rs.getString("distinguishid")==null?"":rs.getString("distinguishid"), rs.getString("sim")==null?"":rs.getString("sim"),rs.getString("next_Time")==null?"":d.format(df.parse(rs.getString("next_Time"))) ,day.toString());

							}
							//判断制造单位是否拥有年检到期短信提醒权限
							if(Authority.haveMessage("zz_fwf")){
								Message.addMessage("服务费到期提醒", rs.getString("makeUnitName")==null?"":rs.getString("makeUnitName"), rs.getString("makeUnitPhone")==null?"":rs.getString("makeUnitPhone"), rs.getString("install_Place")==null?"":rs.getString("install_Place"), rs.getString("distinguishid")==null?"":rs.getString("distinguishid"), rs.getString("sim")==null?"":rs.getString("sim"),rs.getString("next_Time")==null?"":d.format(df.parse(rs.getString("next_Time"))) ,day.toString());

							}
							//判断区域用户是否拥有年检到期短信提醒权限
							if(Authority.haveMessage("xt_fwf")){
								Message.addMessage("服务费到期提醒", rs.getString("usersName")==null?"":rs.getString("usersName"), rs.getString("usersPhone")==null?"":rs.getString("usersPhone"), rs.getString("install_Place")==null?"":rs.getString("install_Place"), rs.getString("distinguishid")==null?"":rs.getString("distinguishid"), rs.getString("sim")==null?"":rs.getString("sim"),rs.getString("next_Time")==null?"":d.format(df.parse(rs.getString("next_Time"))) ,day.toString());

							}
						}
						
					}
					
				/**
				 * 维保超15天短信
				 */
					 c.setTime(new Date());
					 sql1=sql+" and trunc(sysdate-de.maintenance_Time)>15 ";
					 sta = conn.prepareStatement(sql1);
					 rs = sta.executeQuery();
					while(rs.next()){
						Calendar c1 =  Calendar.getInstance();
						c1.setTime(df.parse(rs.getString("maintenance_Time")));
						int days= c.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
						String day=Integer.toString(days);
						//判断使用单位是否拥有年检到期短信提醒权限
						if(Authority.haveMessage("sy_wb")){
							Message.addMessage("维保超15天", rs.getString("useUnitName")==null?"":rs.getString("useUnitName"), rs.getString("useUnitPhone")==null?"":rs.getString("useUnitPhone"), rs.getString("install_Place")==null?"":rs.getString("install_Place"), rs.getString("distinguishid")==null?"":rs.getString("distinguishid"), rs.getString("sim")==null?"":rs.getString("sim"),rs.getString("next_Time")==null?"":d.format(df.parse(rs.getString("next_Time"))) ,day.toString());
						}
						//判断物业位是否拥有年检到期短信提醒权限
						if(Authority.haveMessage("wy_wb")){
							Message.addMessage("维保超15天", rs.getString("propertyUnitName")==null?"":rs.getString("propertyUnitName"), rs.getString("propertyUnitPhone")==null?"":rs.getString("propertyUnitPhone"), rs.getString("install_Place")==null?"":rs.getString("install_Place"), rs.getString("distinguishid")==null?"":rs.getString("distinguishid"), rs.getString("sim")==null?"":rs.getString("sim"),rs.getString("next_Time")==null?"":d.format(df.parse(rs.getString("next_Time"))) ,day.toString());

						}
						//判断维保单位是否拥有年检到期短信提醒权限
						if(Authority.haveMessage("wb_wb")){
							Message.addMessage("维保超15天", rs.getString("maintenanceUnitName")==null?"":rs.getString("maintenanceUnitName"), rs.getString("maintenanceUnitPhone")==null?"":rs.getString("maintenanceUnitPhone"), rs.getString("install_Place")==null?"":rs.getString("install_Place"), rs.getString("distinguishid")==null?"":rs.getString("distinguishid"), rs.getString("sim")==null?"":rs.getString("sim"),rs.getString("next_Time")==null?"":d.format(df.parse(rs.getString("next_Time"))) ,day.toString());

						}
						//判断维保人是否拥有年检到期短信提醒权限
						if(Authority.haveMessage("wbr_wb")){
							Message.addMessage("维保超15天", rs.getString("maintenanceUserName")==null?"":rs.getString("maintenanceUserName"), rs.getString("maintenanceUserPhone")==null?"":rs.getString("maintenanceUserPhone"), rs.getString("install_Place")==null?"":rs.getString("install_Place"), rs.getString("distinguishid")==null?"":rs.getString("distinguishid"), rs.getString("sim")==null?"":rs.getString("sim"),rs.getString("next_Time")==null?"":d.format(df.parse(rs.getString("next_Time"))) ,day.toString());

						}
						//判断制造单位是否拥有年检到期短信提醒权限
						if(Authority.haveMessage("zz_wb")){
							Message.addMessage("维保超15天", rs.getString("makeUnitName")==null?"":rs.getString("makeUnitName"), rs.getString("makeUnitPhone")==null?"":rs.getString("makeUnitPhone"), rs.getString("install_Place")==null?"":rs.getString("install_Place"), rs.getString("distinguishid")==null?"":rs.getString("distinguishid"), rs.getString("sim")==null?"":rs.getString("sim"),rs.getString("next_Time")==null?"":d.format(df.parse(rs.getString("next_Time"))) ,day.toString());

						}
						//判断区域用户是否拥有年检到期短信提醒权限
						if(Authority.haveMessage("xt_wb")){
							Message.addMessage("维保超15天", rs.getString("usersName")==null?"":rs.getString("usersName"), rs.getString("usersPhone")==null?"":rs.getString("usersPhone"), rs.getString("install_Place")==null?"":rs.getString("install_Place"), rs.getString("distinguishid")==null?"":rs.getString("distinguishid"), rs.getString("sim")==null?"":rs.getString("sim"),rs.getString("next_Time")==null?"":d.format(df.parse(rs.getString("next_Time"))) ,day.toString());

						}
					}
					
					/**
					 * 保险到期前30天，进行短信提醒
					 */
					 sql1=sql+" and de.safe_State='1'  ";
					 sta = conn.prepareStatement(sql1);
					 rs = sta.executeQuery();
						while(rs.next()){
							
							String sql2="select trunc(t.end_time - sysdate) from bxgl_safe t where t.elevator_id = '"+rs.getLong("id")+"'	 and t.start_time <= sysdate and t.end_time >= sysdate and trunc(t.end_time - sysdate)<'30' order by id	 ";
							Object day=DBEntity.getInstance().queryUniqueData(sql2);
							if(day!=null&&!day.equals("")){
								//判断使用单位是否拥有年检到期短信提醒权限
								if(Authority.haveMessage("sy_bx")){
									Message.addMessage("保险到期提醒", rs.getString("useUnitName")==null?"":rs.getString("useUnitName"), rs.getString("useUnitPhone")==null?"":rs.getString("useUnitPhone"), rs.getString("install_Place")==null?"":rs.getString("install_Place"), rs.getString("distinguishid")==null?"":rs.getString("distinguishid"), rs.getString("sim")==null?"":rs.getString("sim"),rs.getString("next_Time")==null?"":d.format(df.parse(rs.getString("next_Time"))) ,day.toString());
								}
								//判断物业位是否拥有年检到期短信提醒权限
								if(Authority.haveMessage("wy_bx")){
									Message.addMessage("保险到期提醒", rs.getString("propertyUnitName")==null?"":rs.getString("propertyUnitName"), rs.getString("propertyUnitPhone")==null?"":rs.getString("propertyUnitPhone"), rs.getString("install_Place")==null?"":rs.getString("install_Place"), rs.getString("distinguishid")==null?"":rs.getString("distinguishid"), rs.getString("sim")==null?"":rs.getString("sim"),rs.getString("next_Time")==null?"":d.format(df.parse(rs.getString("next_Time"))) ,day.toString());

								}
								//判断维保单位是否拥有年检到期短信提醒权限
								if(Authority.haveMessage("wb_bx")){
									Message.addMessage("保险到期提醒", rs.getString("maintenanceUnitName")==null?"":rs.getString("maintenanceUnitName"), rs.getString("maintenanceUnitPhone")==null?"":rs.getString("maintenanceUnitPhone"), rs.getString("install_Place")==null?"":rs.getString("install_Place"), rs.getString("distinguishid")==null?"":rs.getString("distinguishid"), rs.getString("sim")==null?"":rs.getString("sim"),rs.getString("next_Time")==null?"":d.format(df.parse(rs.getString("next_Time"))) ,day.toString());

								}
								//判断维保人是否拥有年检到期短信提醒权限
								if(Authority.haveMessage("wbr_bx")){
									Message.addMessage("保险到期提醒", rs.getString("maintenanceUserName")==null?"":rs.getString("maintenanceUserName"), rs.getString("maintenanceUserPhone")==null?"":rs.getString("maintenanceUserPhone"), rs.getString("install_Place")==null?"":rs.getString("install_Place"), rs.getString("distinguishid")==null?"":rs.getString("distinguishid"), rs.getString("sim")==null?"":rs.getString("sim"),rs.getString("next_Time")==null?"":d.format(df.parse(rs.getString("next_Time"))) ,day.toString());

								}
								//判断制造单位是否拥有年检到期短信提醒权限
								if(Authority.haveMessage("zz_bx")){
									Message.addMessage("保险到期提醒", rs.getString("makeUnitName")==null?"":rs.getString("makeUnitName"), rs.getString("makeUnitPhone")==null?"":rs.getString("makeUnitPhone"), rs.getString("install_Place")==null?"":rs.getString("install_Place"), rs.getString("distinguishid")==null?"":rs.getString("distinguishid"), rs.getString("sim")==null?"":rs.getString("sim"),rs.getString("next_Time")==null?"":d.format(df.parse(rs.getString("next_Time"))) ,day.toString());

								}
								//判断区域用户是否拥有年检到期短信提醒权限
								if(Authority.haveMessage("xt_bx")){
									Message.addMessage("保险到期提醒", rs.getString("usersName")==null?"":rs.getString("usersName"), rs.getString("usersPhone")==null?"":rs.getString("usersPhone"), rs.getString("install_Place")==null?"":rs.getString("install_Place"), rs.getString("distinguishid")==null?"":rs.getString("distinguishid"), rs.getString("sim")==null?"":rs.getString("sim"),rs.getString("next_Time")==null?"":d.format(df.parse(rs.getString("next_Time"))) ,day.toString());

								}
							}
							
						}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public static XtszMessageService getMessageService() {
		return messageService;
	}


	public static void setMessageService(XtszMessageService messageService) {
		MessageJob.messageService = messageService;
	}


	public static DtjkElevatorService getElevatorService() {
		return elevatorService;
	}


	public static void setElevatorService(DtjkElevatorService elevatorService) {
		MessageJob.elevatorService = elevatorService;
	}


	public static XtglUseUnitService getUseUnitService() {
		return useUnitService;
	}


	public static void setUseUnitService(XtglUseUnitService useUnitService) {
		MessageJob.useUnitService = useUnitService;
	}


	public static XtglMaintenanceUsersService getMaintenanceUsersService() {
		return maintenanceUsersService;
	}


	public static void setMaintenanceUsersService(
			XtglMaintenanceUsersService maintenanceUsersService) {
		MessageJob.maintenanceUsersService = maintenanceUsersService;
	}


	public static XtglUsersService getUsersService() {
		return usersService;
	}


	public static void setUsersService(XtglUsersService usersService) {
		MessageJob.usersService = usersService;
	}


	public static XtszDictionaryService getDictionaryService() {
		return dictionaryService;
	}


	public static void setDictionaryService(XtszDictionaryService dictionaryService) {
		MessageJob.dictionaryService = dictionaryService;
	}

}
