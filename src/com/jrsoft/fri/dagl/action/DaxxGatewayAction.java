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
import com.jrsoft.fri.dagl.entity.DaxxGateway;
import com.jrsoft.fri.dagl.from.DaglFrom;
import com.jrsoft.fri.dagl.from.Page;
import com.jrsoft.fri.dagl.service.DaxxGatewayService;

public class DaxxGatewayAction  extends DispatchAction {
	private DaxxGatewayService gatewayService;

	public DaxxGatewayService getGatewayService() {
		return gatewayService;
	}

	public void setGatewayService(DaxxGatewayService gatewayService) {
		this.gatewayService = gatewayService;
	}

	/**
	 * 新增 电梯网关信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  addEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		DaglFrom daglFrom=(DaglFrom)form;
		DaxxGateway elevator =daglFrom.getGateway();
		
		gatewayService.save(elevator);
	    return	new ActionForward("/gatewayAction.do?method=query");
	}
	/**
	 * 查询 电梯网关列表
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public ActionForward  query(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		String type=request.getParameter("type");
		String net=request.getParameter("net");
		String flow=request.getParameter("flow");
		String communication=request.getParameter("communication");
		String terminal=request.getParameter("terminal");
		String hardware=request.getParameter("hardware");
		String software=request.getParameter("software");
		
		 if(type!=null){
			 type=new String(type.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(net!=null){
			 net=new String(net.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(flow!=null){
			 flow=new String(flow.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(communication!=null){
			 communication=new String(communication.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(type!=null){
			 type=new String(type.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(terminal!=null){
			 terminal=new String(terminal.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(hardware!=null){
			 hardware=new String(hardware.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(software!=null){
			 software=new String(software.getBytes("iso-8859-1"),"utf-8");
		 }
		String num=request.getParameter("num");   //当前页
		

		Page  page=new Page();
		String hql=" where  1=1 " ;
		if(type!=null&&!type.equals("")){
			hql+=" and type = '%"+type+"%'";
		}
		if(net!=null&&!net.equals("")){
			hql+=" and net = '%"+net+"%'";
		}
		if(flow!=null&&!flow.equals("")){
			hql+=" and flow = '%"+flow+"%'";
		}
		if(communication!=null&&!communication.equals("")){
			hql+=" and communication like '%"+communication+"%'";
		}
		if(terminal!=null&&!terminal.equals("")){
			hql+=" and terminal like '%"+terminal+"%'";
		}
		if(hardware!=null&&!hardware.equals("")){
			hql+=" and hardware like '%"+hardware+"%'";
		}
		if(software!=null&&!software.equals("")){
			hql+=" and software like '%"+software+"%'";
		}
		hql+="order by id ";
		List<DaxxGateway> DaxxGateways=gatewayService.queryAll(hql);
		
		page.setPageSize(3);	//每页显示数
		if(num!=null&&!num.equals("")){
			page.setPageNum(Integer.parseInt(num));//当前页数
		}else{
			page.setPageNum(0);//当前页数
		}
		page.setCount(DaxxGateways.size());//总记录数
		page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//总页数	
		
		List<DaxxGateway> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//查询服务订单
				String sql="select de.*  from daxx_gateway de where  1=1 " ;
				if(type!=null&&!type.equals("")){
					sql+=" and type = '%"+type+"%'";
				}
				if(net!=null&&!net.equals("")){
					sql+=" and net = '%"+net+"%'";
				}
				if(flow!=null&&!flow.equals("")){
					sql+=" and flow = '%"+flow+"%'";
				}
				if(communication!=null&&!communication.equals("")){
					sql+=" and communication like '%"+communication+"%'";
				}
				if(terminal!=null&&!terminal.equals("")){
					sql+=" and terminal like '%"+terminal+"%'";
				}
				if(hardware!=null&&!hardware.equals("")){
					sql+=" and hardware like '%"+hardware+"%'";
				}
				if(software!=null&&!software.equals("")){
					sql+=" and software like '%"+software+"%'";
				}
				sql+=" order by id";	
				String sql1="select * from ( select a.*,rownum rn from ("+sql+") a where rownum<="+page.getPageSize() * (page.getPageNum() +1)+") where rn>="+(page.getPageSize() * page.getPageNum()+1);
				
				PreparedStatement sta = conn.prepareStatement(sql1);
				ResultSet rs = sta.executeQuery();
				list=new ArrayList<DaxxGateway>();
				while(rs.next()){
					DaxxGateway elevators=new DaxxGateway();
					elevators.setId(rs.getLong("id"));
					elevators.setType(rs.getString("type"));
					elevators.setFlow(rs.getString("flow"));
					elevators.setNet(rs.getString("net"));
					elevators.setCommunication(rs.getString("communication"));
					elevators.setTerminal(rs.getString("terminal"));
					elevators.setHardware(rs.getString("hardware"));
					elevators.setSoftware(rs.getString("software"));
					elevators.setSim(rs.getString("sim"));
					elevators.setReport(rs.getString("report"));
					elevators.setSerialNumber(rs.getString("serial_Number"));
					elevators.setIp(rs.getString("ip"));
					elevators.setPort(rs.getString("port"));
					list.add(elevators);
					
				}

				request.setAttribute("type", type);
				request.setAttribute("net", net);
				request.setAttribute("flow", flow);
				request.setAttribute("communication", communication);
				request.setAttribute("terminal", terminal);
				request.setAttribute("hardware", hardware);
				request.setAttribute("software", software);
				request.setAttribute("page", page);
				request.setAttribute("list", list);
		
		
		 return	new ActionForward("/jsp/dagl/gateway/gatewayList.jsp");
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
		DaxxGateway list=gatewayService.get(Long.parseLong(id));
		request.setAttribute("list", list);
		return	new ActionForward("/jsp/dagl/gateway/updateGateway.jsp");
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
		DaxxGateway daxxgateway =daglFrom.getGateway();
		DaxxGateway gateway=gatewayService.get(daxxgateway.getId());
		
		if(gateway!=null){
			gateway.setType(daxxgateway.getType());
			gateway.setFlow(daxxgateway.getFlow());
			gateway.setNet(daxxgateway.getNet());
			gateway.setCommunication(daxxgateway.getCommunication());
			gateway.setTerminal(daxxgateway.getTerminal());
			gateway.setHardware(daxxgateway.getHardware());
			gateway.setSoftware(daxxgateway.getSoftware());
			gateway.setSim(daxxgateway.getSim());
			gateway.setReport(daxxgateway.getReport());
			gateway.setSerialNumber(daxxgateway.getSerialNumber());
			gateway.setIp(daxxgateway.getIp());
			gateway.setPort(daxxgateway.getPort());
			gatewayService.update(gateway);
		}
		return	new ActionForward("/gatewayAction.do?method=query");
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
		gatewayService.delete(id);
		 return	new ActionForward("/gatewayAction.do?method=query");
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
				gatewayService.delete(Long.parseLong(arr[i]));
			}
		}
		 return	new ActionForward("/gatewayAction.do?method=query");
		
	}
	
	/**
	 * 导出 电梯网关信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  export(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {

		String type=request.getParameter("type");
		String net=request.getParameter("net");
		String flow=request.getParameter("flow");
		String communication=request.getParameter("communication");
		String terminal=request.getParameter("terminal");
		String hardware=request.getParameter("hardware");
		String software=request.getParameter("software");
		
		 if(type!=null){
			 type=new String(type.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(net!=null){
			 net=new String(net.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(flow!=null){
			 flow=new String(flow.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(communication!=null){
			 communication=new String(communication.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(type!=null){
			 type=new String(type.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(terminal!=null){
			 terminal=new String(terminal.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(hardware!=null){
			 hardware=new String(hardware.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(software!=null){
			 software=new String(software.getBytes("iso-8859-1"),"utf-8");
		 }
		DaxxGateway elevator=new DaxxGateway();
		elevator.setType(type);
		elevator.setFlow(flow);
		elevator.setNet(net);
		elevator.setCommunication(communication);
		elevator.setTerminal(terminal);
		elevator.setHardware(hardware);
		elevator.setSoftware(software);
		
		try {
			String dates = new SimpleDateFormat("yyyyMMddHHmmss")
					.format(new Date());
			String fileName = "电梯网关信息" + dates + ".xls";
			String filePath = request.getRealPath("/")
					+ "excel\\" + fileName;
			// 生成excel文件
			gatewayService.export(filePath, elevator);

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
