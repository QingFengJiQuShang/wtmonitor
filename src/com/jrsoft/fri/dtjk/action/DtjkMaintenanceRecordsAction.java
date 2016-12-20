package com.jrsoft.fri.dtjk.action;

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
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import smart.sys.platform.dao.DBEntity;

import com.jrsoft.fri.dtjk.entity.DtjkElevator;
import com.jrsoft.fri.dtjk.entity.DtjkMaintenanceRecords;
import com.jrsoft.fri.dtjk.from.DtjkFrom;
import com.jrsoft.fri.dtjk.service.DtjkElevatorService;
import com.jrsoft.fri.dtjk.service.DtjkMaintenanceRecordsService;
import com.jrsoft.fri.xtgl.entity.XtglMaintenanceUnit;
import com.jrsoft.fri.xtgl.entity.XtglMaintenanceUsers;
import com.jrsoft.fri.xtgl.entity.XtglUseUnit;
import com.jrsoft.fri.xtgl.entity.XtglUsers;
import com.jrsoft.fri.xtgl.from.Page;
import com.jrsoft.fri.xtsz.action.Log;

public class DtjkMaintenanceRecordsAction extends DispatchAction {
	
	private DtjkMaintenanceRecordsService recordsService;
	private DtjkElevatorService elevatorService;
	public DtjkMaintenanceRecordsService getRecordsService() {
		return recordsService;
	}

	public void setRecordsService(DtjkMaintenanceRecordsService recordsService) {
		this.recordsService = recordsService;
	}
	
	public DtjkElevatorService getElevatorService() {
		return elevatorService;
	}

	public void setElevatorService(DtjkElevatorService elevatorService) {
		this.elevatorService = elevatorService;
	}

	/**
	 * 编辑 查看 维保记录
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  findByAdd(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String elevatorId=request.getParameter("elevatorId");
		DtjkElevator list=elevatorService.get(Long.parseLong(elevatorId));
		request.setAttribute("list", list);
		return	new ActionForward("/jsp/dtjk/maintenanceRecords/addMaintenanceRecords.jsp");
	}
	
	/**
	 * 新增 维保记录信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  addEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		DtjkFrom DtjkFrom=(DtjkFrom)form;
		DtjkMaintenanceRecords elevator =DtjkFrom.getMaintenanceRecords();
		String time=request.getParameter("time");
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		elevator.setTime(df.parse(time));
		recordsService.save(elevator);
		
		//修改电梯的维保状态和维保时间
		DtjkElevator entity =elevatorService.get(elevator.getElevatorId().getId());
		entity.setMaintenanceTime(new Date());
		entity.setMaintenanceState("正常");
		elevatorService.update(entity);
		//生成 操作日志
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "添加电梯维保记录，电梯注册号："+elevator.getRegisterid(), "1");
		
	    return	new ActionForward("/recordsAction.do?method=query&elevatorId="+elevator.getElevatorId().getId());
	}
	/**
	 * 查询 维保记录列表
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public ActionForward  query(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		String elevatorId=request.getParameter("elevatorId");
		String time=request.getParameter("time");
		String useUnitId=request.getParameter("useUnitId");
		String useUnitId1=request.getParameter("useUnitId1");
		String maintenanceUnitId=request.getParameter("maintenanceUnitId");
		String maintenanceUnitId1=request.getParameter("maintenanceUnitId1");
		
		String num=request.getParameter("num");   //当前页
		
		if(useUnitId1!=null){
			useUnitId1=new String(useUnitId1.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(maintenanceUnitId1!=null){
			 maintenanceUnitId1=new String(maintenanceUnitId1.getBytes("iso-8859-1"),"utf-8");
		 }
		Page  page=new Page();
		
		if(num!=null&&!num.equals("")){
			page.setPageNum(Integer.parseInt(num));//当前页数
		}else{
			page.setPageNum(0);//当前页数
		}
		
		List<DtjkMaintenanceRecords> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//查询服务订单
				String sql="select de.*,xuu.name as userunitname,xmu.name unitname,mu.name as username,e.registerid as registerid,e.distinguishid as distinguishid,e.install_place as place  " +
						" from dtjk_maintenance_records de " +
						" left join xtgl_use_unit xuu on xuu.id=de.use_unit_id "+  //使用单位
						" left join xtgl_maintenance_unit xmu on xmu.id=de.unit_id"+  //维保单位
						" left join xtgl_maintenance_users mu on mu.id=de.user_id"+  //维保人员
						" left join dtjk_elevator e on e.id=de.elevator_id "+  //电梯信息
						"where  1=1 " ;
				if(elevatorId!=null&&!elevatorId.equals("")){
					sql+=" and de.elevator_Id = '"+elevatorId+"'";
				}
				if(time!=null&&!time.equals("")){
					sql+=" and de.time = to_date('" + time+ "','yyyy-MM-dd')";
				}
				if(useUnitId!=null&&!useUnitId.equals("")){
					sql+=" and de.use_unit_id  ='"+useUnitId+"'";
				}
				if(maintenanceUnitId!=null&&!maintenanceUnitId.equals("")){
					sql+=" and de.unit_id  ='"+maintenanceUnitId+"'";
				}
				sql+=" order by time desc";	
				String sql1="select * from ( select a.*,rownum rn from ("+sql+") a where rownum<="+page.getPageSize() * (page.getPageNum() +1)+") where rn>="+(page.getPageSize() * page.getPageNum()+1);
				int siz=	DBEntity.getInstance().queryCount(sql);
				page.setCount(siz);//总记录数
				page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//总页数	

				PreparedStatement sta = conn.prepareStatement(sql1);
				ResultSet rs = sta.executeQuery();
				list=new ArrayList<DtjkMaintenanceRecords>();
				while(rs.next()){
					DtjkMaintenanceRecords useUnit=new DtjkMaintenanceRecords();
					useUnit.setId(rs.getLong("id"));
					useUnit.setUseUnitName(rs.getString("userunitname"));
					useUnit.setUserName(rs.getString("username"));
					useUnit.setUnitName(rs.getString("unitname"));
					useUnit.setRegisterid(rs.getString("registerid"));
					useUnit.setDistinguishid(rs.getString("distinguishid"));
					useUnit.setPlace(rs.getString("place"));
					useUnit.setTime(df.parse(rs.getString("time")));
					useUnit.setContent(rs.getString("content"));
					list.add(useUnit);
					
				}
				request.setAttribute("elevatorId", elevatorId);
				if(time!=null){
					request.setAttribute("time", df.parse(time));
				}
				
				request.setAttribute("useUnitId", useUnitId);
				request.setAttribute("useUnitId1", useUnitId1);
				request.setAttribute("maintenanceUnitId", maintenanceUnitId);
				request.setAttribute("maintenanceUnitId1", maintenanceUnitId1);
				request.setAttribute("page", page);
				request.setAttribute("list", list);
		
		
		 return	new ActionForward("/jsp/dtjk/maintenanceRecords/maintenanceRecordsList.jsp");
		}
	
	/**
	 * 编辑 查看 维保记录
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  findById(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String id=request.getParameter("id");
		String flag=request.getParameter("flag");
		DtjkMaintenanceRecords list=recordsService.get(Long.parseLong(id));
		request.setAttribute("list", list);
		if(flag.equals("1")){
			return	new ActionForward("/jsp/dtjk/maintenanceRecords/updateMaintenanceRecords.jsp");
		}else{
			return	new ActionForward("/jsp/dtjk/maintenanceRecords/detailMaintenanceRecords.jsp");
		}
	}
	
	/**
	 * 修改维保记录
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  updateEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		DtjkFrom DtjkFrom=(DtjkFrom)form;
		DtjkMaintenanceRecords elevator =DtjkFrom.getMaintenanceRecords();
		String time=request.getParameter("time");
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		elevator.setTime(df.parse(time));
		
		recordsService.update(elevator);
		//生成 操作日志
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "修改电梯维保记录，电梯注册号："+elevator.getRegisterid(), "1");
		return	new ActionForward("/recordsAction.do?method=query&elevatorId="+elevator.getElevatorId().getId());
	}
	
	
	
	
	/**
	 * 删除维保记录
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  delEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		Long id=Long.parseLong(request.getParameter("id"));
		String elevatorId=request.getParameter("elevatorId");
		DtjkElevator entity =elevatorService.get(id);
		recordsService.delete(id);
		//生成 操作日志
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "删除电梯维保记录，电梯注册号："+entity.getRegisterid(), "1");
		 return	new ActionForward("/recordsAction.do?method=query&elevatorId="+elevatorId);
	}
	/**
	 * 批量 删除 维保记录
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
				DtjkElevator entity =elevatorService.get(Long.parseLong(arr[i]));
				recordsService.delete(Long.parseLong(arr[i]));
				
				//生成 操作日志
				XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
				Log log=new Log();
		        log.addLog(user.getName(), "删除电梯维保记录，电梯注册号："+entity.getRegisterid(), "1");
			}
		}
		 return	new ActionForward("/recordsAction.do?method=query");
		
	}
	
	/**
	 * 导出 维保记录信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  export(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {

		String elevatorId=request.getParameter("elevatorId");
		String time=request.getParameter("time");
		String useUnitId=request.getParameter("useUnitId");
		String maintenanceUnitId=request.getParameter("maintenanceUnitId");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式	

		DtjkMaintenanceRecords records=new DtjkMaintenanceRecords();
		
		 XtglMaintenanceUnit unit=new XtglMaintenanceUnit();			//维保单位id
		 XtglUseUnit useUnit=new XtglUseUnit();			//使用单位id
		 DtjkElevator elevator=new DtjkElevator();//维保电梯Id
		if(useUnitId!=null)
			useUnit.setId(Long.parseLong(useUnitId));
		 if(maintenanceUnitId!=null)
			 unit.setId(Long.parseLong(maintenanceUnitId));
		 if(elevatorId!=null)
			 elevator.setId(Long.parseLong(elevatorId));
		 if(time!=null)
			 records.setTime(df.parse(time));
		 records.setUnitId(unit);
		 records.setUseUnitId(useUnit);
		 records.setElevatorId(elevator);
		try {
			String dates = new SimpleDateFormat("yyyyMMddHHmmss")
					.format(new Date());
			String fileName = "维保记录信息" + dates + ".xls";
			String filePath = request.getRealPath("/")
					+ "excel\\" + fileName;
			// 生成excel文件
			recordsService.export(filePath, records);

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
