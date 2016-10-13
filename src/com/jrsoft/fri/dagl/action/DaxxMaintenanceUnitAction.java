package com.jrsoft.fri.dagl.action;

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

import com.jrsoft.fri.dagl.entity.DaxxElevator;
import com.jrsoft.fri.dagl.entity.DaxxMaintenanceUnit;
import com.jrsoft.fri.dagl.from.DaglFrom;
import com.jrsoft.fri.dagl.from.Page;
import com.jrsoft.fri.dagl.service.DaxxMaintenanceUnitService;

public class DaxxMaintenanceUnitAction  extends DispatchAction {
	private DaxxMaintenanceUnitService maintenanceUnitService;

	public DaxxMaintenanceUnitService getMaintenanceUnitService() {
		return maintenanceUnitService;
	}

	public void setMaintenanceUnitService(
			DaxxMaintenanceUnitService maintenanceUnitService) {
		this.maintenanceUnitService = maintenanceUnitService;
	}

	/**
	 * 新增 维保单位信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  addEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		DaglFrom daglFrom=(DaglFrom)form;
		DaxxMaintenanceUnit elevator =daglFrom.getMaintenanceUnit();
		maintenanceUnitService.save(elevator);
	    return	new ActionForward("/elevatorAction.do?method=query");
	}
	/**
	 * 查询 区域列表
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public ActionForward  query(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		
		String num=request.getParameter("num");   //当前页
		

		Page  page=new Page();
		String hql=" where  1=1 " ;
		
		hql+="order by id ";
		List<DaxxMaintenanceUnit> DaxxMaintenanceUnits=maintenanceUnitService.queryAll(hql);
		
		page.setPageSize(3);	//每页显示数
		if(num!=null&&!num.equals("")){
			page.setPageNum(Integer.parseInt(num));//当前页数
		}else{
			page.setPageNum(0);//当前页数
		}
		page.setCount(DaxxMaintenanceUnits.size());//总记录数
		page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//总页数	
		
		List<DaxxMaintenanceUnit> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//查询服务订单
				String sql="select de.*  from daxx_maintenance_unit de where  1=1 " ;
				
				sql+=" order by id";	
				String sql1="select * from ( select a.*,rownum rn from ("+sql+") a where rownum<="+page.getPageSize() * (page.getPageNum() +1)+") where rn>="+(page.getPageSize() * page.getPageNum()+1);
				
				PreparedStatement sta = conn.prepareStatement(sql1);
				ResultSet rs = sta.executeQuery();
				list=new ArrayList<DaxxMaintenanceUnit>();
				while(rs.next()){
					DaxxMaintenanceUnit elevator=new DaxxMaintenanceUnit();
					elevator.setId(rs.getLong("id"));
					elevator.setName(rs.getString("name"));
					elevator.setLiaisons(rs.getString("liaisons"));
					elevator.setPhone(rs.getString("phone"));
					elevator.setAddress(rs.getString("address"));
					elevator.setCode(rs.getString("code"));
					elevator.setCorporation(rs.getString("corporation"));
					
					list.add(elevator);
					
				}
				request.setAttribute("page", page);
				request.setAttribute("list", list);
		
		
		 return	new ActionForward("/jsp/dagl/elevator/elevatorList.jsp");
		}
	
	/**
	 * 编辑 查看 区域 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  findById(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String id=request.getParameter("id");
		DaxxMaintenanceUnit list=maintenanceUnitService.get(Long.parseLong(id));
		request.setAttribute("list", list);
		return	new ActionForward("/jsp/dagl/elevator/updateElevator.jsp");
	}
	
	/**
	 * 修改区域
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  updateEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		DaglFrom daglFrom=(DaglFrom)form;
		DaxxMaintenanceUnit unit =daglFrom.getMaintenanceUnit();
		DaxxMaintenanceUnit elevator=maintenanceUnitService.get(unit.getId());
		
		if(elevator!=null){
			elevator.setName(unit.getName());
			elevator.setLiaisons(unit.getLiaisons());
			elevator.setPhone(unit.getPhone());
			elevator.setAddress(unit.getAddress());
			elevator.setCode(unit.getCode());
			elevator.setCorporation(unit.getCorporation());
			maintenanceUnitService.update(elevator);
		}
		return	new ActionForward("/elevatorAction.do?method=query");
	}
	
	
	
	
	/**
	 * 删除区域
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  delEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		Long id=Long.parseLong(request.getParameter("id"));
		maintenanceUnitService.delete(id);
		 return	new ActionForward("/elevatorAction.do?method=query");
	}
	/**
	 * 批量 删除 区域
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
				maintenanceUnitService.delete(Long.parseLong(arr[i]));
			}
		}
		 return	new ActionForward("/elevatorAction.do?method=query");
		
	}
	
	/**
	 * 导出 维保单位信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  export(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {

		
		DaxxMaintenanceUnit elevator=new DaxxMaintenanceUnit();
		
		
		try {
			String dates = new SimpleDateFormat("yyyyMMddHHmmss")
					.format(new Date());
			String fileName = "维保单位信息" + dates + ".xls";
			String filePath = request.getRealPath("/")
					+ "excel\\" + fileName;
			// 生成excel文件
			maintenanceUnitService.export(filePath, elevator);

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
