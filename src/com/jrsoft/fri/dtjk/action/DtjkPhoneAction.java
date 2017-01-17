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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import smart.sys.platform.dao.DBEntity;
import smart.sys.platform.springUtils.SpringBeanUtil;
import com.jrsoft.fri.dtjk.entity.DtjkElevator;
import com.jrsoft.fri.dtjk.entity.DtjkPhone;
import com.jrsoft.fri.dtjk.from.DtjkFrom;
import com.jrsoft.fri.dtjk.service.DtjkElevatorService;
import com.jrsoft.fri.dtjk.service.DtjkPhoneService;
import com.jrsoft.fri.xtgl.entity.XtglMaintenanceUnit;
import com.jrsoft.fri.xtgl.entity.XtglMaintenanceUsers;
import com.jrsoft.fri.xtgl.entity.XtglPropertyUnit;
import com.jrsoft.fri.xtgl.entity.XtglUseUnit;
import com.jrsoft.fri.xtgl.entity.XtglUsers;
import com.jrsoft.fri.xtgl.from.Page;
import com.jrsoft.fri.xtgl.service.XtglMaintenanceUnitService;
import com.jrsoft.fri.xtgl.service.XtglMaintenanceUsersService;
import com.jrsoft.fri.xtgl.service.XtglPropertyUnitService;
import com.jrsoft.fri.xtgl.service.XtglUseUnitService;
import com.jrsoft.fri.xtgl.service.XtglUsersService;
import com.jrsoft.fri.xtsz.action.Log;

public class DtjkPhoneAction extends DispatchAction{
	private DtjkPhoneService phoneService;
	private DtjkElevatorService elevatorService;
	
	private XtglMaintenanceUnitService maintenanceUnitService;
	private XtglMaintenanceUsersService maintenanceUsersService;
	private XtglUseUnitService useUnitService;
	private XtglUsersService usersService;
	private XtglPropertyUnitService propertyUnitService;
	public DtjkPhoneService getPhoneService() {
		return phoneService;
	}

	public void setPhoneService(DtjkPhoneService phoneService) {
		this.phoneService = phoneService;
	}
	
	public DtjkElevatorService getElevatorService() {
		return elevatorService;
	}

	public void setElevatorService(DtjkElevatorService elevatorService) {
		this.elevatorService = elevatorService;
	}
	
	public XtglMaintenanceUnitService getMaintenanceUnitService() {
		return maintenanceUnitService;
	}

	public void setMaintenanceUnitService(
			XtglMaintenanceUnitService maintenanceUnitService) {
		this.maintenanceUnitService = maintenanceUnitService;
	}

	public XtglUseUnitService getUseUnitService() {
		return useUnitService;
	}

	public void setUseUnitService(XtglUseUnitService useUnitService) {
		this.useUnitService = useUnitService;
	}

	public XtglUsersService getUsersService() {
		return usersService;
	}

	public void setUsersService(XtglUsersService usersService) {
		this.usersService = usersService;
	}

	public XtglMaintenanceUsersService getMaintenanceUsersService() {
		return maintenanceUsersService;
	}

	public void setMaintenanceUsersService(
			XtglMaintenanceUsersService maintenanceUsersService) {
		this.maintenanceUsersService = maintenanceUsersService;
	}

	public XtglPropertyUnitService getPropertyUnitService() {
		return propertyUnitService;
	}

	public void setPropertyUnitService(XtglPropertyUnitService propertyUnitService) {
		this.propertyUnitService = propertyUnitService;
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
		DtjkElevator elevator=elevatorService.get(Long.parseLong(elevatorId));
		XtglMaintenanceUnit maintenanceUnit=maintenanceUnitService.get(elevator.getMaintenanceUnitId().getId());
		XtglUseUnit useUnit=useUnitService.get(elevator.getUseUnitId().getId());		
		XtglPropertyUnit propertyUnit=propertyUnitService.get(elevator.getPropertyUnitId().getId());
		XtglMaintenanceUsers maintenanceUsers=maintenanceUsersService.get(elevator.getMaintenanceUsersId().getId());
		
		
		JSONArray array=new JSONArray();
		if(useUnit!=null){
			JSONObject object=new JSONObject();
			object.put("name","使用单位负责人");
			object.put("phone", useUnit.getPhone());
			array.add(object);
		}
		if(propertyUnit!=null){
			JSONObject object=new JSONObject();
			object.put("name","物业单位负责人");
			object.put("phone", propertyUnit.getPhone());
			array.add(object);
		}
		if(maintenanceUnit!=null){
			JSONObject object=new JSONObject();
			object.put("name","维保单位负责人");
			object.put("phone", maintenanceUnit.getPhone());
			array.add(object);
		}
		if(maintenanceUsers!=null){
			JSONObject object=new JSONObject();
			object.put("name","维保人员");
			object.put("phone", maintenanceUsers.getPhone());
			array.add(object);
		}
			
			
		
		request.setAttribute("array", array);
		request.setAttribute("elevatorId", elevatorId);
		
		return	new ActionForward("/jsp/dtjk/phone/addPhone.jsp");
	}
	/**
	 * 新增 电梯信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  addEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		DtjkFrom DtjkFrom=(DtjkFrom)form;
		DtjkPhone elevator =DtjkFrom.getPh();
		phoneService.save(elevator);
		//生成 操作日志
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "添加电梯白名单，电话号码："+elevator.getPhone(), "1");
		
	    return	new ActionForward("/phoneAction.do?method=query&elevatorId="+elevator.getElevatorId().getId());
	}
	/**
	 * 查询 电梯白名单列表
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public ActionForward  query(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		String elevatorId=request.getParameter("elevatorId");
		
		String num=request.getParameter("num");   //当前页
		

		Page  page=new Page();
		
		if(num!=null&&!num.equals("")){
			page.setPageNum(Integer.parseInt(num));//当前页数
		}else{
			page.setPageNum(0);//当前页数
		}
		
		List<DtjkPhone> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//查询服务订单
				String sql="select de.* ,e.registerid as registerid,e.distinguishid as distinguishid,e.install_place as place " +
						" from dtjk_phone de " +
						" left join dtjk_elevator e on e.id=de.elevator_id "+  //电梯信息
						" where  1=1 " ;
				if(elevatorId!=null&&!elevatorId.equals("")){
					sql+=" and de.elevator_Id = '"+elevatorId+"'";
				}
				
				sql+=" order by de.id";	
				String sql1="select * from ( select a.*,rownum rn from ("+sql+") a where rownum<="+page.getPageSize() * (page.getPageNum() +1)+") where rn>="+(page.getPageSize() * page.getPageNum()+1);
				int siz=	DBEntity.getInstance().queryCount(sql);
				page.setCount(siz);//总记录数
				page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//总页数	

				PreparedStatement sta = conn.prepareStatement(sql1);
				ResultSet rs = sta.executeQuery();
				list=new ArrayList<DtjkPhone>();
				while(rs.next()){
					DtjkPhone elevators=new DtjkPhone();
					elevators.setId(rs.getLong("id"));
					elevators.setPhone(rs.getString("phone"));
					elevators.setBelong(rs.getString("belong"));
					list.add(elevators);
					
				}

				request.setAttribute("elevatorId", elevatorId);
				request.setAttribute("page", page);
				request.setAttribute("list", list);
		
		
		 return	new ActionForward("/jsp/dtjk/phone/phoneList.jsp");
		}
	
	/**
	 * 编辑 查看 网关 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  findById(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String id=request.getParameter("id");
		String flag=request.getParameter("flag");
		DtjkPhone list=phoneService.get(Long.parseLong(id));
		request.setAttribute("list", list);
		if(flag.equals("1")){
			DtjkElevator elevator=elevatorService.get(list.getElevatorId().getId());
			XtglMaintenanceUnit maintenanceUnit=maintenanceUnitService.get(elevator.getMaintenanceUnitId().getId());
			XtglUseUnit useUnit=useUnitService.get(elevator.getUseUnitId().getId());		
			XtglPropertyUnit propertyUnit=propertyUnitService.get(elevator.getPropertyUnitId().getId());
			XtglMaintenanceUsers maintenanceUsers=maintenanceUsersService.get(elevator.getMaintenanceUsersId().getId());
			JSONArray array=new JSONArray();
			if(useUnit!=null){
				JSONObject object=new JSONObject();
				object.put("name","使用单位负责人");
				object.put("phone", useUnit.getPhone());
				array.add(object);
			}
			if(propertyUnit!=null){
				JSONObject object=new JSONObject();
				object.put("name","物业单位负责人");
				object.put("phone", propertyUnit.getPhone());
				array.add(object);
			}
			if(maintenanceUnit!=null){
				JSONObject object=new JSONObject();
				object.put("name","维保单位负责人");
				object.put("phone", maintenanceUnit.getPhone());
				array.add(object);
			}
			if(maintenanceUsers!=null){
				JSONObject object=new JSONObject();
				object.put("name","维保人员");
				object.put("phone", maintenanceUsers.getPhone());
				array.add(object);
			}
			request.setAttribute("array", array);
			return	new ActionForward("/jsp/dtjk/phone/updatePhone.jsp");
		}else{
			return	new ActionForward("/jsp/dtjk/phone/detailPhone.jsp");
		}
	}
	
	
	/**
	 * 新增 电梯信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  updateEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		DtjkFrom DtjkFrom=(DtjkFrom)form;
		DtjkPhone elevator =DtjkFrom.getPh();
		DtjkPhone entity=phoneService.get(elevator.getId());
		if(entity.getId()!=null){
			entity.setPhone(elevator.getPhone());
			phoneService.save(entity);
		}
		//生成 操作日志
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "修改电梯白名单，电话号码："+elevator.getPhone(), "1");
	    return	new ActionForward("/phoneAction.do?method=query&elevatorId="+elevator.getElevatorId().getId());
	}
	
	
	/**
	 * 删除网关
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  delEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		Long id=Long.parseLong(request.getParameter("id"));
		String elevatorId=request.getParameter("elevatorId");
		DtjkPhone entity=phoneService.get(id);
		phoneService.delete(id);
		//生成 操作日志
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "删除电梯白名单，电话号码："+entity.getPhone(), "1");
		 return	new ActionForward("/phoneAction.do?method=query&elevatorId"+elevatorId);
	}
	/**
	 * 批量 删除 网关
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  deleteEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String ids=request.getParameter("ids");
		String elevatorId=request.getParameter("elevatorId");
		if(ids!=null&&!ids.equals("")){
			String  arr []=ids.split(",");
			for(int i=0;i<arr.length;i++){
				DtjkPhone entity=phoneService.get(Long.parseLong(arr[i]));
				phoneService.delete(Long.parseLong(arr[i]));
				//生成 操作日志
				XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
				Log log=new Log();
		        log.addLog(user.getName(), "删除电梯白名单，电话号码："+entity.getPhone(), "1");
			}
		}
		 return	new ActionForward("/phoneAction.do?method=query&elevatorId"+elevatorId);
		
	}
	
	/**
	 * 导出 电梯白名单信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public ActionForward  export(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {

		String type=request.getParameter("type");
		String net=request.getParameter("net");
		
		 if(type!=null){
			 type=new String(type.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(net!=null){
			 net=new String(net.getBytes("iso-8859-1"),"utf-8");
		 }
		DtjkPhone elevator=new DtjkPhone();
		
		try {
			String dates = new SimpleDateFormat("yyyyMMddHHmmss")
					.format(new Date());
			String fileName = "电梯白名单信息" + dates + ".xls";
			String filePath = request.getRealPath("/")
					+ "excel\\" + fileName;
			// 生成excel文件
			//phoneService.export(filePath, elevator);

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

	
		 return	null;
		
	}


}
