package com.jrsoft.fri.gzcl.action;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.net.aso.e;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import smart.sys.platform.dao.DBEntity;

import com.jrsoft.fri.dtjk.entity.DtjkElevator;
import com.jrsoft.fri.dtjk.service.DtjkElevatorService;
import com.jrsoft.fri.gzcl.entity.GzclFault;
import com.jrsoft.fri.gzcl.entity.GzlcAlarm;
import com.jrsoft.fri.gzcl.from.GzclForm;
import com.jrsoft.fri.gzcl.service.GzclFaultService;
import com.jrsoft.fri.gzcl.service.GzlcAlarmService;
import com.jrsoft.fri.xtgl.entity.XtglUseUnit;
import com.jrsoft.fri.xtgl.entity.XtglUsers;
import com.jrsoft.fri.xtgl.from.Page;
import com.jrsoft.fri.xtgl.from.XtglForm;
import com.jrsoft.fri.xtsz.action.Log;

public class GzlcAlarmAction  extends DispatchAction {
	
	private GzlcAlarmService alarmService ;
	private GzclFaultService faultService;
	private DtjkElevatorService elevatorService;
	public GzlcAlarmService getAlarmService() {
		return alarmService;
	}

	public void setAlarmService(GzlcAlarmService alarmService) {
		this.alarmService = alarmService;
	}

	public GzclFaultService getFaultService() {
		return faultService;
	}

	public void setFaultService(GzclFaultService faultService) {
		this.faultService = faultService;
	}

	public DtjkElevatorService getElevatorService() {
		return elevatorService;
	}

	public void setElevatorService(DtjkElevatorService elevatorService) {
		this.elevatorService = elevatorService;
	}

	/**
	 * 新增 人工接警信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  addEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		GzclForm GzclForm=(GzclForm)form;
		GzlcAlarm elevator =GzclForm.getAlarm();
		String time=request.getParameter("time");
		String happenTime=request.getParameter("happenTime");
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		elevator.setTime(df.parse(time));
		elevator.setHappenTime(df.parse(happenTime));
		alarmService.save(elevator);
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		//生成当前故障
		GzclFault fault=new GzclFault();
		fault.setElevatorId(elevator.getElevatorId());
		fault.setFault(elevator.getDescribe());
		fault.setAlarmTime(elevator.getTime());
		fault.setHappenTime(elevator.getHappenTime());
		fault.setFaultType(elevator.getFault());
		fault.setNumbers("0");
		fault.setType("人工接警");
		fault.setState("处理中");
		fault.setDutyId(user);
		faultService.save(fault);
		//修改电梯当前运行状态
		DtjkElevator list=elevatorService.get(elevator.getElevatorId().getId());
		list.setState("故障");
		elevatorService.update(list);
		//生成 操作日志
		Log log=new Log();
		log.addLog(user.getName(), "添加人工接警", "1");
        log.addLog(user.getName(), "添加当前故障", "1");
		
		
	    return	new ActionForward("/alarmAction.do?method=query");
	}
	/**
	 * 查询 人工接警列表
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public ActionForward  query(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		String registerid=request.getParameter("registerid");
		String place=request.getParameter("place");
		String time=request.getParameter("time");
		
		String num=request.getParameter("num");   //当前页
		
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Page  page=new Page();
		page.setPageSize(3);	//每页显示数
		if(num!=null&&!num.equals("")){
			page.setPageNum(Integer.parseInt(num));//当前页数
		}else{
			page.setPageNum(0);//当前页数
		}
		page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//总页数	
		
		List<GzlcAlarm> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//查询服务订单
				String sql="select de.*,e.registerid as registerid,e.distinguishid as distinguishid,e.install_place as place   " +
						" from gzlc_alarm de " +
						" left join dtjk_elevator e on e.id=de.elevator_id "+  //电梯信息
						"where  1=1 " ;
				if(registerid!=null&&!registerid.equals("")){
					sql+=" and e.registerid like '%"+registerid+"%'";
				}
				if(place!=null&&!place.equals("")){
					sql+=" and e.install_Place like '%"+place+"%'";
				}
				if(time!=null&&!time.equals("")){
					sql+=" and de.time  =to_date('" + time+ "','yyyy-MM-dd')";
				}
				sql+=" order by de.time desc";	
				int siz=	DBEntity.getInstance().queryCount(sql);
				page.setCount(siz);//总记录数
				String sql1="select * from ( select a.*,rownum rn from ("+sql+") a where rownum<="+page.getPageSize() * (page.getPageNum() +1)+") where rn>="+(page.getPageSize() * page.getPageNum()+1);
				
				PreparedStatement sta = conn.prepareStatement(sql1);
				ResultSet rs = sta.executeQuery();
				list=new ArrayList<GzlcAlarm>();
				while(rs.next()){
					GzlcAlarm useUnit=new GzlcAlarm();
					useUnit.setId(rs.getLong("id"));
					useUnit.setFault(rs.getString("fault"));
					useUnit.setTime(rs.getString("time")==null?null:df.parse(rs.getString("time")));
					useUnit.setHappenTime(rs.getString("happen_time")==null?null:df.parse(rs.getString("happen_time")));
					useUnit.setAlarmPerson(rs.getString("alarm_Person"));
					useUnit.setPhone(rs.getString("phone"));
					useUnit.setDescribe(rs.getString("describe"));
					useUnit.setRegisterid(rs.getString("registerid"));
					useUnit.setDistinguishid(rs.getString("distinguishid"));
					useUnit.setPlace(rs.getString("place"));
					list.add(useUnit);
					
				}
				request.setAttribute("registerid", registerid);
				request.setAttribute("place", place);
				if(time!=null){
					request.setAttribute("time", df.parse( time));
				}
				request.setAttribute("page", page);
				request.setAttribute("list", list);
		
		
		 return	new ActionForward("/jsp/gzgl/alarm/alarmList.jsp");
		}
	
	/**
	 * 编辑 查看 人工接警
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  findById(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String id=request.getParameter("id");
		String flag=request.getParameter("flag");
		GzlcAlarm list=alarmService.get(Long.parseLong(id));
		request.setAttribute("list", list);
		if(flag.equals("1")){
			return	new ActionForward("/jsp/gzgl/alarm/updateAlarm.jsp");
		}else{
			return	new ActionForward("/jsp/gzgl/alarm/detailAlarm.jsp");
		}
	}
	
	/**
	 * 修改人工接警
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  updateEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		GzclForm GzclForm=(GzclForm)form;
		GzlcAlarm unit =GzclForm.getAlarm();
		String time=request.getParameter("time");
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		unit.setTime(df.parse(time));
		alarmService.update(unit);
		//生成 操作日志
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
		log.addLog(user.getName(), "修改人工接警", "1");
		return	new ActionForward("/alarmAction.do?method=query");
	}
	
	
	
	
	/**
	 * 删除人工接警
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  delEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		Long id=Long.parseLong(request.getParameter("id"));
		alarmService.delete(id);
		//生成 操作日志
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
		log.addLog(user.getName(), "删除人工接警", "1");
		 return	new ActionForward("/alarmAction.do?method=query");
	}
	/**
	 * 批量 删除 人工接警
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  deleteEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String ids=request.getParameter("ids");
		if(ids!=null&&!ids.equals("")){
			String  arr []=ids.split(",");
			for(int i=0;i<arr.length;i++){
				alarmService.delete(Long.parseLong(arr[i]));
				//生成 操作日志
				XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
				Log log=new Log();
				log.addLog(user.getName(), "删除人工接警", "1");
			}
		}
		 return	new ActionForward("/alarmAction.do?method=query");
		
	}
	
	/**
	 * 导出 人工接警信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  export(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {

		String name=request.getParameter("name");
		String liaisons=request.getParameter("liaisons");
		String phone=request.getParameter("phone");
		
		if(name!=null){
			name=new String(name.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(liaisons!=null){
			 liaisons=new String(liaisons.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(phone!=null){
			 phone=new String(phone.getBytes("iso-8859-1"),"utf-8");
		 }
		GzlcAlarm elevator=new GzlcAlarm();
		elevator.setPhone(phone);
		
		try {
			String dates = new SimpleDateFormat("yyyyMMddHHmmss")
					.format(new Date());
			String fileName = "人工接警信息" + dates + ".xls";
			String filePath = request.getRealPath("/")
					+ "excel\\" + fileName;
			// 生成excel文件
			alarmService.export(filePath, elevator);

			// 下载excel
			BufferedOutputStream bos = null;
			StringBuffer sb = new StringBuffer(50);
			sb.append("attachment;   filename=");
			sb.append(fileName);
			// System.out.println("----------sb="+sb);

			if (null != filePath && fileName != null) {
				response.setContentType("application/x-msdownload;charset=GBK");
				response.setHeader("Content-Disposition", new String(sb
						.toString().getBytes(), "ISO8859-1"));
				FileInputStream fis = new FileInputStream(filePath);
				bos = new BufferedOutputStream(response.getOutputStream());
				byte[] buffer = new byte[2048];
				while (fis.read(buffer) != -1) {
					bos.write(buffer);
				}
				bos.write(buffer, 0, buffer.length);
				fis.close();
				bos.close();
			}
			File fs = new File(filePath);
			if (fs.isFile() && fs.exists()) {
				fs.delete();
				System.out.println("删除单个文件" + fileName + "成功！");
			} else {
				System.out.println("删除单个文件" + fileName + "失败！");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
