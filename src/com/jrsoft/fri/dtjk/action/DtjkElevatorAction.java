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
import smart.sys.platform.dao.DBEntity;
import com.jrsoft.fri.dtjk.entity.DtjkElevator;
import com.jrsoft.fri.dtjk.from.DtjkFrom;
import com.jrsoft.fri.dtjk.service.DtjkElevatorService;
import com.jrsoft.fri.xtgl.from.Page;

public class DtjkElevatorAction {
	private DtjkElevatorService elevatorService;

	public DtjkElevatorService getElevatorService() {
		return elevatorService;
	}

	public void setElevatorService(DtjkElevatorService elevatorService) {
		this.elevatorService = elevatorService;
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
		String time=request.getParameter("time");
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		DtjkFrom DtjkFrom=(DtjkFrom)form;
		DtjkElevator elevator =DtjkFrom.getElevator();
		
		elevator.setManufactureTime(df.parse(time));
		elevatorService.save(elevator);
	    return	new ActionForward("/elevatorAction.do?method=query");
	}
	/**
	 * 查询 电梯列表
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public ActionForward  query(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		String register=request.getParameter("reg");
		String distinguish=request.getParameter("distinguish");
		String label=request.getParameter("label");
		String brand=request.getParameter("brand");
		String type=request.getParameter("type");
		String model=request.getParameter("model");
		String numbers=request.getParameter("numbers");
		String lengths=request.getParameter("lengths");
		
		 if(register!=null){
			 register=new String(register.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(distinguish!=null){
			 distinguish=new String(distinguish.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(label!=null){
			 label=new String(label.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(brand!=null){
			 brand=new String(brand.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(type!=null){
			 type=new String(type.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(model!=null){
			 model=new String(model.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(numbers!=null){
			 numbers=new String(numbers.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(lengths!=null){
			 lengths=new String(lengths.getBytes("iso-8859-1"),"utf-8");
		 }
		String num=request.getParameter("num");   //当前页
		

		Page  page=new Page();
		String hql=" where  1=1 " ;
		if(register!=null&&!register.equals("")){
			hql+=" and registerid like '%"+register+"%'";
		}
		if(distinguish!=null&&!distinguish.equals("")){
			hql+=" and distinguishid like '%"+distinguish+"%'";
		}
		if(label!=null&&!label.equals("")){
			hql+=" and label like '%"+label+"%'";
		}
		if(brand!=null&&!brand.equals("")){
			hql+=" and brand like '%"+brand+"%'";
		}
		if(type!=null&&!type.equals("")){
			hql+=" and type like '%"+type+"%'";
		}
		if(model!=null&&!model.equals("")){
			hql+=" and model like '%"+model+"%'";
		}
		if(numbers!=null&&!numbers.equals("")){
			hql+=" and numbers like '%"+numbers+"%'";
		}
		if(lengths!=null&&!lengths.equals("")){
			hql+=" and lengths like '%"+lengths+"%'";
		}
		hql+="order by id ";
		List<DtjkElevator> DtjkElevators=elevatorService.queryAll(hql);
		
		page.setPageSize(3);	//每页显示数
		if(num!=null&&!num.equals("")){
			page.setPageNum(Integer.parseInt(num));//当前页数
		}else{
			page.setPageNum(0);//当前页数
		}
		page.setCount(DtjkElevators.size());//总记录数
		page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//总页数	
		
		List<DtjkElevator> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//查询服务订单
				String sql="select de.*  from daxx_elevator de where  1=1 " ;
				if(register!=null&&!register.equals("")){
					sql+=" and registerid like '%"+register+"%'";
				}		
				if(distinguish!=null&&!distinguish.equals("")){
					sql+=" and distinguishid like '%"+distinguish+"%'";
				}
				if(label!=null&&!label.equals("")){
					sql+=" and label like '%"+label+"%'";
				}
				if(brand!=null&&!brand.equals("")){
					sql+=" and brand like '%"+brand+"%'";
				}
				if(type!=null&&!type.equals("")){
					sql+=" and type like '%"+type+"%'";
				}
				if(model!=null&&!model.equals("")){
					sql+=" and model like '%"+model+"%'";
				}
				if(numbers!=null&&!numbers.equals("")){
					sql+=" and numbers like '%"+numbers+"%'";
				}
				if(lengths!=null&&!lengths.equals("")){
					sql+=" and lengths like '%"+lengths+"%'";
				}
				sql+=" order by id";	
				String sql1="select * from ( select a.*,rownum rn from ("+sql+") a where rownum<="+page.getPageSize() * (page.getPageNum() +1)+") where rn>="+(page.getPageSize() * page.getPageNum()+1);
				
				PreparedStatement sta = conn.prepareStatement(sql1);
				ResultSet rs = sta.executeQuery();
				list=new ArrayList<DtjkElevator>();
				while(rs.next()){
					DtjkElevator elevator=new DtjkElevator();
					elevator.setId(rs.getLong("id"));
					elevator.setRegisterid(rs.getString("registerid"));
					elevator.setDistinguishid(rs.getString("distinguishid"));
					elevator.setBrand(rs.getString("brand"));
					elevator.setModel(rs.getString("model"));
					elevator.setState(rs.getString("state"));
					elevator.setType(rs.getString("type"));
					elevator.setNumbers(rs.getString("numbers"));
					elevator.setLabel(rs.getString("label"));
					elevator.setPlace(rs.getString("place"));
					elevator.setManufactureTime(rs.getDate("manufacture_Time"));
					elevator.setYearlyState(rs.getString("yearly_State"));
					elevator.setGatewayId(rs.getLong("gateway_Id"));
					elevator.setUseUnitId(rs.getLong("use_Unit_Id"));
					elevator.setMaintenanceUnitId(rs.getLong("maintenance_Unit_Id"));
					elevator.setMaintenanceState(rs.getString("maintenance_State"));
					list.add(elevator);
					
				}
				
//				sta.close();
//				rs.close();
//				conn.close();
				
				request.setAttribute("register", register);
				request.setAttribute("distinguish", distinguish);
				request.setAttribute("label", label);
				request.setAttribute("brand", brand);
				request.setAttribute("type", type);
				request.setAttribute("model", model);
				request.setAttribute("numbers", numbers);
				request.setAttribute("lengths", lengths);
				request.setAttribute("page", page);
				request.setAttribute("list", list);
		
		
		 return	new ActionForward("/jsp/dagl/elevator/elevatorList.jsp");
		}
	
	/**
	 * 编辑 查看 电梯 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  findById(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String id=request.getParameter("id");
		DtjkElevator list=elevatorService.get(Long.parseLong(id));
		request.setAttribute("list", list);
		return	new ActionForward("/jsp/dagl/elevator/updateElevator.jsp");
	}
	
	/**
	 * 修改电梯
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  updateEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String time=request.getParameter("time");
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		DtjkFrom DtjkFrom=(DtjkFrom)form;
		DtjkElevator DtjkElevator =DtjkFrom.getElevator();
		DtjkElevator.setManufactureTime(df.parse(time));
		DtjkElevator elevator=elevatorService.get(DtjkElevator.getId());
		
		if(elevator!=null){
			elevator.setRegisterid(DtjkElevator.getRegisterid());
			elevator.setDistinguishid(DtjkElevator.getDistinguishid());
			elevator.setBrand(DtjkElevator.getBrand());
			elevator.setModel(DtjkElevator.getModel());
			elevator.setState(DtjkElevator.getState());
			elevator.setType(DtjkElevator.getType());
			elevator.setNumbers(DtjkElevator.getNumbers());
			elevator.setLabel(DtjkElevator.getLabel());
			elevator.setPlace(DtjkElevator.getPlace());
			elevator.setManufactureTime(DtjkElevator.getManufactureTime());
			elevator.setYearlyState(DtjkElevator.getYearlyState());
			elevator.setGatewayId(DtjkElevator.getGatewayId());
			elevator.setUseUnitId(DtjkElevator.getUseUnitId());
			elevator.setMaintenanceUnitId(DtjkElevator.getMaintenanceUnitId());
			elevator.setMaintenanceState(DtjkElevator.getMaintenanceState());
			elevatorService.update(elevator);
		}
		return	new ActionForward("/elevatorAction.do?method=query");
	}
	
	
	
	
	/**
	 * 删除电梯
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  delEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		Long id=Long.parseLong(request.getParameter("id"));
		elevatorService.delete(id);
		 return	new ActionForward("/elevatorAction.do?method=query");
	}
	/**
	 * 批量 删除 电梯
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
				elevatorService.delete(Long.parseLong(arr[i]));
			}
		}
		 return	new ActionForward("/elevatorAction.do?method=query");
		
	}
	
	/**
	 * 导出 电梯信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public ActionForward  export(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {

		String register=request.getParameter("reg");
		String distinguish=request.getParameter("distinguish");
		String label=request.getParameter("label");
		String brand=request.getParameter("brand");
		String type=request.getParameter("type");
		String model=request.getParameter("model");
		String numbers=request.getParameter("numbers");
		DtjkElevator elevator=new DtjkElevator();
		 if(register!=null){
			 register=new String(register.getBytes("iso-8859-1"),"utf-8");
			 elevator.setRegisterid(register);
		 }
		 if(distinguish!=null){
			 distinguish=new String(distinguish.getBytes("iso-8859-1"),"utf-8");
			 elevator.setDistinguishid(distinguish);
		 }
		 if(label!=null){
			 label=new String(label.getBytes("iso-8859-1"),"utf-8");
			 elevator.setLabel(label);
		 }
		 if(brand!=null){
			 brand=new String(brand.getBytes("iso-8859-1"),"utf-8");
			 elevator.setBrand(brand);
		 }
		 if(type!=null){
			 type=new String(type.getBytes("iso-8859-1"),"utf-8");
			 elevator.setType(type);
		 }
		 if(model!=null){
			 model=new String(model.getBytes("iso-8859-1"),"utf-8");
			 elevator.setModel(model);
		 }
		 if(numbers!=null){
			 numbers=new String(numbers.getBytes("iso-8859-1"),"utf-8");
			 elevator.setNumbers(numbers);
		 }
		
		try {
			String dates = new SimpleDateFormat("yyyyMMddHHmmss")
					.format(new Date());
			String fileName = "电梯信息" + dates + ".xls";
			String filePath = request.getRealPath("/")
					+ "excel\\" + fileName;
			// 生成excel文件
			elevatorService.export(filePath, elevator);

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
