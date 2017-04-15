package com.jrsoft.fri.tjfx.action;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import smart.sys.platform.dao.DBEntity;

import com.jrsoft.fri.bxgl.from.Safe;
import com.jrsoft.fri.bxgl.from.SafeUnit;
import com.jrsoft.fri.dtjk.entity.DtjkElevator;
import com.jrsoft.fri.dtjk.service.DtjkElevatorService;
import com.jrsoft.fri.gzcl.entity.GzclFault;
import com.jrsoft.fri.gzcl.entity.GzclRescue;
import com.jrsoft.fri.gzcl.service.GzclFaultService;
import com.jrsoft.fri.gzcl.service.GzclRescueService;
import com.jrsoft.fri.tjfx.from.BrandCount;
import com.jrsoft.fri.tjfx.from.FaultCount;
import com.jrsoft.fri.tjfx.from.MaintenanceCount;
import com.jrsoft.fri.tjfx.from.MessageCount;
import com.jrsoft.fri.tjfx.from.RegionCount;
import com.jrsoft.fri.tjfx.from.RescueCount;
import com.jrsoft.fri.xtgl.entity.XtglRescueUnit;
import com.jrsoft.fri.xtgl.from.ExcelColumns;
import com.jrsoft.fri.xtgl.from.Page;
import com.jrsoft.fri.xtgl.service.XtglMaintenanceUnitService;
import com.jrsoft.fri.xtgl.service.XtglMaintenanceUsersService;
import com.jrsoft.fri.xtgl.service.XtglRescueUnitService;
import com.jrsoft.fri.xtgl.service.XtglUseUnitService;
import com.jrsoft.fri.xtgl.service.XtglUsersService;

public class CountAction  extends DispatchAction{
	private DtjkElevatorService elevatorService;
	private GzclFaultService faultService;
	private XtglMaintenanceUnitService maintenanceUnitService;
	private XtglMaintenanceUsersService maintenanceUsersService;
	private XtglRescueUnitService rescueUnitService;
	private XtglUsersService usersService;
	private XtglUseUnitService useUnitService;
	private GzclRescueService gzclRescueService;

	
	public DtjkElevatorService getElevatorService() {
		return elevatorService;
	}
	public void setElevatorService(DtjkElevatorService elevatorService) {
		this.elevatorService = elevatorService;
	}
	public GzclFaultService getFaultService() {
		return faultService;
	}
	public void setFaultService(GzclFaultService faultService) {
		this.faultService = faultService;
	}
	public XtglMaintenanceUnitService getMaintenanceUnitService() {
		return maintenanceUnitService;
	}
	public void setMaintenanceUnitService(
			XtglMaintenanceUnitService maintenanceUnitService) {
		this.maintenanceUnitService = maintenanceUnitService;
	}
	public XtglMaintenanceUsersService getMaintenanceUsersService() {
		return maintenanceUsersService;
	}
	public void setMaintenanceUsersService(
			XtglMaintenanceUsersService maintenanceUsersService) {
		this.maintenanceUsersService = maintenanceUsersService;
	}
	public XtglRescueUnitService getRescueUnitService() {
		return rescueUnitService;
	}
	public void setRescueUnitService(XtglRescueUnitService rescueUnitService) {
		this.rescueUnitService = rescueUnitService;
	}
	public XtglUsersService getUsersService() {
		return usersService;
	}
	public void setUsersService(XtglUsersService usersService) {
		this.usersService = usersService;
	}
	public XtglUseUnitService getUseUnitService() {
		return useUnitService;
	}
	public void setUseUnitService(XtglUseUnitService useUnitService) {
		this.useUnitService = useUnitService;
	}
	public GzclRescueService getGzclRescueService() {
		return gzclRescueService;
	}
	public void setGzclRescueService(GzclRescueService gzclRescueService) {
		this.gzclRescueService = gzclRescueService;
	}
	
	/**
	 *  故障区域统计
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public ActionForward  regionCount (ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		String begintime=request.getParameter("begintime");
		String endtime=request.getParameter("endtime");
		String province=request.getParameter("province");
		String city=request.getParameter("city");
		String area=request.getParameter("area");
		DecimalFormat    df   = new DecimalFormat("#0.00");   
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		RegionCount count=new RegionCount();
		String sql="select count(gf.id) from gzcl_fault gf  left join dtjk_elevator de on de.id=gf.elevator_id  where 1=1";
		if(begintime!=null&&!begintime.equals("")){
			sql+=" and gf.happen_Time  >=to_date('" + begintime+ "','yyyy-MM-dd hh24:mi:ss')";
		}
		if(endtime!=null&&!endtime.equals("")){
			sql+=" and gf.happen_Time  <=to_date('" + endtime+ "','yyyy-MM-dd hh24:mi:ss')";
		}
		if(province!=null&&!province.equals("")){
			province=new String(province.getBytes("ISO-8859-1"),"UTF-8");
			sql+=" and de.province  ='" + province+ "'";
			count.setProvince(province);
		}else{
			count.setProvince("所有");
		}
		if(city!=null&&!city.equals("")){
			city=new String(city.getBytes("ISO-8859-1"),"UTF-8");
			sql+=" and de.city ='" + city+ "'";
			count.setCity(city);
		}else{
			count.setCity("所有");
		}
		if(area!=null&&!area.equals("")){
			area=new String(area.getBytes("ISO-8859-1"),"UTF-8");
			sql+=" and de.area  ='" + area+ "'";
			count.setArea(area);
		}else{
			count.setArea("所有");
		}
		//故障数量
		count.setFaultNum(DBEntity.getInstance().queryDataCount(sql));
		//人工接警数量
		count.setAlarmNum(DBEntity.getInstance().queryDataCount(sql+" and gf.type='人工接警' "));
		//自动接警数量
		count.setAutomaticNum(DBEntity.getInstance().queryDataCount(sql+" and  gf.type='自动接警' "));
		//困人故障数量
		count.setPeopleTrappedNum(DBEntity.getInstance().queryDataCount(sql+" and  (gf.fault_Type='困人' or gf.fault_Type='冲顶困人' or gf.fault_Type='蹲底困人' or gf.fault_Type='非平层困人')"));
		//困人率
		count.setPeopleTrappedRate(count.getFaultNum()==0?"0":df.format((double)count.getPeopleTrappedNum()/count.getFaultNum()));
		//其它故障数量
		count.setOtherNum(count.getFaultNum()-count.getPeopleTrappedNum());
		
		request.setAttribute("count",count );
		request.setAttribute("province",province );
		request.setAttribute("city",city );
		request.setAttribute("area",area );
		if(endtime!=null&&!endtime.equals("")){
			request.setAttribute("endtime", sdf.parse( endtime));
		}
		if(begintime!=null&&!begintime.equals("")){
			request.setAttribute("begintime", sdf.parse( begintime));
		}
		return	new ActionForward("/jsp/count/regionCount.jsp");
		}
	
	/**
	 * 导出 区域统计
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public void  exportCount (ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		String begintime=request.getParameter("begintime");
		String endtime=request.getParameter("endtime");
		String province=request.getParameter("province");
		String city=request.getParameter("city");
		String area=request.getParameter("area");
		DecimalFormat    df   = new DecimalFormat("#0.00");   
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		RegionCount count=new RegionCount();
		String sql="select count(gf.id) from gzcl_fault gf  left join dtjk_elevator de on de.id=gf.elevator_id  where 1=1";
		if(begintime!=null&&!begintime.equals("")){
			sql+=" and gf.happen_Time  >=to_date('" + begintime+ "','yyyy-MM-dd hh24:mi:ss')";
		}
		if(endtime!=null&&!endtime.equals("")){
			sql+=" and gf.happen_Time  <=to_date('" + endtime+ "','yyyy-MM-dd hh24:mi:ss')";
		}
		if(province!=null&&!province.equals("")){
			province=new String(province.getBytes("ISO-8859-1"),"UTF-8");
			sql+=" and de.province  ='" + province+ "'";
			count.setProvince(province);
		}else{
			count.setProvince("所有");
		}
		if(city!=null&&!city.equals("")){
			city=new String(city.getBytes("ISO-8859-1"),"UTF-8");
			sql+=" and de.city ='" + city+ "'";
			count.setCity(city);
		}else{
			count.setCity("所有");
		}
		if(area!=null&&!area.equals("")){
			area=new String(area.getBytes("ISO-8859-1"),"UTF-8");
			sql+=" and de.area  ='" + area+ "'";
			count.setArea(area);
		}else{
			count.setArea("所有");
		}
		//故障数量
		count.setFaultNum(DBEntity.getInstance().queryDataCount(sql));
		//人工接警数量
		count.setAlarmNum(DBEntity.getInstance().queryDataCount(sql+" and gf.type='人工接警' "));
		//自动接警数量
		count.setAutomaticNum(DBEntity.getInstance().queryDataCount(sql+" and  gf.type='自动接警' "));
		//困人故障数量
		count.setPeopleTrappedNum(DBEntity.getInstance().queryDataCount(sql+" and  (gf.fault_Type='困人' or gf.fault_Type='冲顶困人' or gf.fault_Type='蹲底困人' or gf.fault_Type='非平层困人')"));
		//困人率
		count.setPeopleTrappedRate(count.getFaultNum()==0?"0":df.format((double)count.getPeopleTrappedNum()/count.getFaultNum()));
		//其它故障数量
		count.setOtherNum(count.getFaultNum()-count.getPeopleTrappedNum());
		List<RegionCount> list=new ArrayList<RegionCount>();
		list.add(count);
		try {
		String dates = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String fileName = "区域统计" + dates + ".xls";
		String filePath = request.getRealPath("/")
		+ "excel\\" + fileName;
				try {
					
			// 生成excel文件
					System.out.println("========>>" + filePath);
					//Excel ex = new Excel();
					HSSFWorkbook workbook = new HSSFWorkbook();
					// 在Excel工作簿中建一工作表，其名为缺省值
					HSSFSheet sheet = null;
					HSSFRow row = null;
					HSSFCell cell = null;
					sheet = workbook.createSheet("区域统计");
					HSSFCellStyle style = workbook.createCellStyle(); // 样式对象
					style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
					style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平
					/**
					 * 设置字体
					 */
					HSSFFont f = workbook.createFont();
					f.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 字体加粗
					style.setFont(f);		
					String[] top_arraydis = null;
					
					if (1 == 1) {
						top_arraydis = ExcelColumns.RegionCount;
		
						row = sheet.createRow(0);// 创建一行
		
						for (int c = 0; c < top_arraydis.length; c++) {
							cell = row.createCell((short) c);// 创建格 字段 
							cell.setCellValue("中文");
							cell.setCellStyle(style);
							cell.setCellValue(top_arraydis[c]);
						}
						int j = 0;
						for (int i = 0; i < list.size(); i++) {
							    row = sheet.createRow(i+1);
							    RegionCount	e = (RegionCount) list.get(i);
								
								cell = row.createCell(j);// 创建格 字段
								cell.setCellValue(e.getProvince());   
								
								cell = row.createCell(++j);// 创建格 字段
								cell.setCellValue(e.getCity());  
		
								cell = row.createCell(++j);// 创建格 字段
								cell.setCellValue(e.getArea());  
								
								cell = row.createCell(++j);// 创建格 字段
								cell.setCellValue(e.getFaultNum());   //电梯型号
								
								cell = row.createCell(++j);// 创建格 字段
								cell.setCellValue(e.getAlarmNum());   
								
								cell = row.createCell(++j);// 创建格 字段
								cell.setCellValue(e.getAutomaticNum());  
								
								cell = row.createCell(++j);// 创建格 字段
								cell.setCellValue(e.getPeopleTrappedNum());  
								
								cell = row.createCell(++j);// 创建格 字段
								cell.setCellValue(e.getPeopleTrappedRate());  
								
								
								cell = row.createCell(++j);// 创建格 字段
								cell.setCellValue(e.getOtherNum());  
								
								j = 0;
							}
					}
		
					// 新建一输出文件流
					
					FileOutputStream fOut = new FileOutputStream(filePath);
		//			System.out.println(fOut + "-----");
					// 把相应的Excel 工作簿存盘
					workbook.write(fOut);
					fOut.flush();
					// 操作结束，关闭文件
					fOut.close();
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("已运行 xlCreate() : " + e);
				}
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


		}
	
	/**
	 * 故障 类型统计
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  faultCount(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String begintime=request.getParameter("begintime");
		String endtime=request.getParameter("endtime");
		String province=request.getParameter("province");
		String city=request.getParameter("city");
		String area=request.getParameter("area");
		String flag=request.getParameter("flag");
		String unitId=request.getParameter("unitId");
		String unitId1=request.getParameter("unitId1");
		if(unitId1!=null&&!unitId1.equals("")){
			unitId1=new String(unitId1.getBytes("ISO-8859-1"),"UTF-8");
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		DecimalFormat    df   = new DecimalFormat("0.00");   
		Connection conn=DBEntity.getInstance().getConnection();
		String sql="select count(gf.id) from gzcl_fault gf " +
				" left join dtjk_elevator de on de.id=gf.elevator_id " +
				" left join xtgl_use_unit xuu on xuu.id=de.use_unit_id "+  //使用单位
				" left join xtgl_maintenance_unit xmu on xmu.id=de.maintenance_unit_id"+  //维保单位
				" left join Xtgl_Property_Unit xpu on xpu.id=de.property_Unit_Id"+  //物业单位
				" left join Xtgl_Make_Unit make on make.id=de.make_Unit_Id"+  //制造单位
				" left join Gzcl_Rescue gr on gr.fault_Id=gf.id"+  //救援单位 和故障 关系表

				" where 1=1";
		String sql1="select count(gf.id) as faultNum,gf.fault_type as faultType from gzcl_fault gf " +
				" left join dtjk_elevator de on de.id=gf.elevator_id " +
				" left join xtgl_use_unit xuu on xuu.id=de.use_unit_id "+  //使用单位
				" left join xtgl_maintenance_unit xmu on xmu.id=de.maintenance_unit_id"+  //维保单位
				" left join Xtgl_Property_Unit xpu on xpu.id=de.property_Unit_Id"+  //物业单位
				" left join Xtgl_Make_Unit make on make.id=de.make_Unit_Id"+  //制造单位
				" left join Gzcl_Rescue gr on gr.fault_Id=gf.id"+  //救援单位 和故障 关系表

				" where 1=1";
		 
		 if(begintime!=null&&!begintime.equals("")){
				sql+=" and gf.happen_Time  >=to_date('" + begintime+ "','yyyy-MM-dd hh24:mi:ss')";
				sql1+=" and gf.happen_Time  >=to_date('" + begintime+ "','yyyy-MM-dd hh24:mi:ss')";
			}
			if(endtime!=null&&!endtime.equals("")){
				sql+=" and gf.happen_Time  <=to_date('" + endtime+ "','yyyy-MM-dd hh24:mi:ss')";
				sql1+=" and gf.happen_Time  <=to_date('" + endtime+ "','yyyy-MM-dd hh24:mi:ss')";
			}
			if(province!=null&&!province.equals("")){
				province=new String(province.getBytes("ISO-8859-1"),"UTF-8");
				sql+=" and de.province  ='" + province+ "'";
				sql1+=" and de.province  ='" + province+ "'";
			}
			if(city!=null&&!city.equals("")){
				city=new String(city.getBytes("ISO-8859-1"),"UTF-8");
				sql+=" and de.city ='" + city+ "'";
				sql1+=" and de.city ='" + city+ "'";
			}
			if(area!=null&&!area.equals("")){
				area=new String(area.getBytes("ISO-8859-1"),"UTF-8");
				sql+=" and de.area  ='" + area+ "'";
				sql1+=" and de.area  ='" + area+ "'";
			}
			if(flag!=null&&!flag.equals("")){
					if(flag.equals("1")&&unitId!=null&&!unitId.equals("")){
						sql+=" and xuu.id='"+unitId+"' ";
						sql1+=" and xuu.id='"+unitId+"' ";
					}else if(flag.equals("2")&&unitId!=null&&!unitId.equals("")){
						sql+=" and xpu.id='"+unitId+"' ";
						sql1+=" and xpu.id='"+unitId+"' ";
					}else if(flag.equals("3")&&unitId!=null&&!unitId.equals("")){
						sql+=" and xmu.id='"+unitId+"' ";
						sql1+=" and xmu.id='"+unitId+"' ";
					}else if(flag.equals("4")&&unitId!=null&&!unitId.equals("")){
						sql+=" and make.id='"+unitId+"' ";
						sql1+=" and make.id='"+unitId+"' ";
					}else if(flag.equals("5")&&unitId!=null&&!unitId.equals("")){
						sql+=" and gr.rescue_Unit_Id='"+unitId+"' ";
						sql1+=" and gr.rescue_Unit_Id='"+unitId+"' ";
						//sql+=" and (gf.unit_id='"+unitId+",%'  or gf.unit_id like '%,"+unitId+",%' ) ";
						//sql1+=" and (gf.unit_id='"+unitId+",%'  or gf.unit_id like '%,"+unitId+",%' ) ";
					}else {
						
					}
			}
		 sql1=sql1+" group by gf.fault_type";
		int zong=DBEntity.getInstance().queryDataCount(sql); 		//电梯总数
		PreparedStatement sta = conn.prepareStatement(sql1);
		ResultSet rs = sta.executeQuery();
		List<FaultCount> counts=new ArrayList<FaultCount>();
		List<String> title=new ArrayList<String>();
		List<String> num=new ArrayList<String>();

		while(rs.next()){
			FaultCount count=new FaultCount();
			count.setName(unitId1==null?"所有单位":unitId1);
			count.setElevatorNum(zong);
			count.setFaultNum(rs.getInt("faultNum"));
			count.setFaultType(rs.getString("faultType"));		//故障类型
			count.setIncidence(count.getElevatorNum()==0?"0":df.format((double)count.getFaultNum()/count.getElevatorNum()));
			counts.add(count);
			num.add(count.getElevatorNum()==0?"0":df.format((double)count.getFaultNum()/count.getElevatorNum()));
			if(rs.getString("faultType")==null){
				title.add("\"\"");
			}else{
				title.add("\""+rs.getString("faultType")+"\"");
			}
		}
		System.out.println(title.toString());
		request.setAttribute("counts",counts);
		request.setAttribute("title",title.toString());
		request.setAttribute("num",num.toString());
		request.setAttribute("flag",flag);
		request.setAttribute("unitId1",unitId1);
		request.setAttribute("unitId",unitId);
		request.setAttribute("province",province );
		request.setAttribute("city",city );
		request.setAttribute("area",area );
		if(endtime!=null&&!endtime.equals("")){
			request.setAttribute("endtime", sdf.parse( endtime));
		}
		if(begintime!=null&&!begintime.equals("")){
			request.setAttribute("begintime", sdf.parse( begintime));
		}
	    return	new ActionForward("/jsp/count/fault.jsp");
	}
	
	/**
	 * 导出 故障类型统计
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public void  exportFaultCount(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		String begintime=request.getParameter("begintime");
		String endtime=request.getParameter("endtime");
		String province=request.getParameter("province");
		String city=request.getParameter("city");
		String area=request.getParameter("area");
		String flag=request.getParameter("flag");
		String unitId=request.getParameter("unitId");
		String unitId1=request.getParameter("unitId1");
		if(unitId1!=null&&!unitId1.equals("")){
			unitId1=new String(unitId1.getBytes("ISO-8859-1"),"UTF-8");
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		DecimalFormat    df   = new DecimalFormat("0.00");   
		Connection conn=DBEntity.getInstance().getConnection();
		String sql="select count(gf.id) from gzcl_fault gf " +
				" left join dtjk_elevator de on de.id=gf.elevator_id " +
				" left join xtgl_use_unit xuu on xuu.id=de.use_unit_id "+  //使用单位
				" left join xtgl_maintenance_unit xmu on xmu.id=de.maintenance_unit_id"+  //维保单位
				" left join Xtgl_Property_Unit xpu on xpu.id=de.property_Unit_Id"+  //物业单位
				" left join Xtgl_Make_Unit make on make.id=de.make_Unit_Id"+  //制造单位
				" left join Gzcl_Rescue gr on gr.fault_Id=gf.id"+  //救援单位 和故障 关系表
				
				" where 1=1";
		String sql1="select count(gf.id) as faultNum,gf.fault_type as faultType from gzcl_fault gf " +
				" left join dtjk_elevator de on de.id=gf.elevator_id " +
				" left join xtgl_use_unit xuu on xuu.id=de.use_unit_id "+  //使用单位
				" left join xtgl_maintenance_unit xmu on xmu.id=de.maintenance_unit_id"+  //维保单位
				" left join Xtgl_Property_Unit xpu on xpu.id=de.property_Unit_Id"+  //物业单位
				" left join Xtgl_Make_Unit make on make.id=de.make_Unit_Id"+  //制造单位
				" left join Gzcl_Rescue gr on gr.fault_Id=gf.id"+  //救援单位 和故障 关系表

				" where 1=1";
		 
		 if(begintime!=null&&!begintime.equals("")){
				sql+=" and gf.happen_Time  >=to_date('" + begintime+ "','yyyy-MM-dd hh24:mi:ss')";
				sql1+=" and gf.happen_Time  >=to_date('" + begintime+ "','yyyy-MM-dd hh24:mi:ss')";
			}
			if(endtime!=null&&!endtime.equals("")){
				sql+=" and gf.happen_Time  <=to_date('" + endtime+ "','yyyy-MM-dd hh24:mi:ss')";
				sql1+=" and gf.happen_Time  <=to_date('" + endtime+ "','yyyy-MM-dd hh24:mi:ss')";
			}
			if(province!=null&&!province.equals("")){
				province=new String(province.getBytes("ISO-8859-1"),"UTF-8");
				sql+=" and de.province  ='" + province+ "'";
				sql1+=" and de.province  ='" + province+ "'";
			}
			if(city!=null&&!city.equals("")){
				city=new String(city.getBytes("ISO-8859-1"),"UTF-8");
				sql+=" and de.city ='" + city+ "'";
				sql1+=" and de.city ='" + city+ "'";
			}
			if(area!=null&&!area.equals("")){
				area=new String(area.getBytes("ISO-8859-1"),"UTF-8");
				sql+=" and de.area  ='" + area+ "'";
				sql1+=" and de.area  ='" + area+ "'";
			}
			if(flag!=null&&!flag.equals("")){
					if(flag.equals("1")&&unitId!=null&&!unitId.equals("")){
						sql+=" and xuu.id='"+unitId+"' ";
						sql1+=" and xuu.id='"+unitId+"' ";
					}else if(flag.equals("2")&&unitId!=null&&!unitId.equals("")){
						sql+=" and xpu.id='"+unitId+"' ";
						sql1+=" and xpu.id='"+unitId+"' ";
					}else if(flag.equals("3")&&unitId!=null&&!unitId.equals("")){
						sql+=" and xmu.id='"+unitId+"' ";
						sql1+=" and xmu.id='"+unitId+"' ";
					}else if(flag.equals("4")&&unitId!=null&&!unitId.equals("")){
						sql+=" and make.id='"+unitId+"' ";
						sql1+=" and make.id='"+unitId+"' ";
					}else if(flag.equals("5")&&unitId!=null&&!unitId.equals("")){
						sql+=" and gr.rescue_Unit_Id='"+unitId+"' ";
						sql1+=" and gr.rescue_Unit_Id='"+unitId+"' ";
						//sql+=" and (gf.unit_id='"+unitId+",%'  or gf.unit_id like '%,"+unitId+",%' ) ";
						//sql1+=" and (gf.unit_id='"+unitId+",%'  or gf.unit_id like '%,"+unitId+",%' ) ";
					}else {
						
					}
			}
		 sql1=sql1+" group by gf.fault_type";
		int zong=DBEntity.getInstance().queryDataCount(sql); 		//电梯总数
		PreparedStatement sta = conn.prepareStatement(sql1);
		ResultSet rs = sta.executeQuery();
		List<FaultCount> list=new ArrayList<FaultCount>();
		while(rs.next()){
			FaultCount count=new FaultCount();
			count.setName(unitId1==null?"所有单位":unitId1);
			count.setElevatorNum(zong);
			count.setFaultNum(rs.getInt("faultNum"));
			count.setFaultType(rs.getString("faultType"));		//故障类型
			count.setIncidence(count.getElevatorNum()==0?"0":df.format((double)count.getFaultNum()/count.getElevatorNum()));
			list.add(count);
		}
		try {
		String dates = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String fileName = "区域统计" + dates + ".xls";
		String filePath = request.getRealPath("/")
		+ "excel\\" + fileName;
				try {
					
			// 生成excel文件
					System.out.println("========>>" + filePath);
					//Excel ex = new Excel();
					HSSFWorkbook workbook = new HSSFWorkbook();
					// 在Excel工作簿中建一工作表，其名为缺省值
					HSSFSheet sheet = null;
					HSSFRow row = null;
					HSSFCell cell = null;
					sheet = workbook.createSheet("区域统计");
					HSSFCellStyle style = workbook.createCellStyle(); // 样式对象
					style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
					style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平
					/**
					 * 设置字体
					 */
					HSSFFont f = workbook.createFont();
					f.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 字体加粗
					style.setFont(f);		
					String[] top_arraydis = null;
					
					if (1 == 1) {
						top_arraydis = ExcelColumns.FaultCount;
		
						row = sheet.createRow(0);// 创建一行
		
						for (int c = 0; c < top_arraydis.length; c++) {
							cell = row.createCell((short) c);// 创建格 字段 
							cell.setCellValue("中文");
							cell.setCellStyle(style);
							cell.setCellValue(top_arraydis[c]);
						}
						int j = 0;
						for (int i = 0; i < list.size(); i++) {
							    row = sheet.createRow(i+1);
							    FaultCount	e = (FaultCount) list.get(i);
								
								cell = row.createCell(j);// 创建格 字段
								cell.setCellValue(e.getName());   
								
								cell = row.createCell(++j);// 创建格 字段
								cell.setCellValue(e.getFaultType());  
								
								cell = row.createCell(++j);// 创建格 字段
								cell.setCellValue(e.getFaultNum());   //电梯型号
								
								cell = row.createCell(++j);// 创建格 字段
								cell.setCellValue(e.getIncidence());   
								
								j = 0;
							}
					}
		
					// 新建一输出文件流
					
					FileOutputStream fOut = new FileOutputStream(filePath);
		//			System.out.println(fOut + "-----");
					// 把相应的Excel 工作簿存盘
					workbook.write(fOut);
					fOut.flush();
					// 操作结束，关闭文件
					fOut.close();
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("已运行 xlCreate() : " + e);
				}
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
}

	/**
	 *  救援区域统计
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public ActionForward  rescueRegionCount (ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		String begintime=request.getParameter("begintime");
		String endtime=request.getParameter("endtime");
		String province=request.getParameter("province");
		String city=request.getParameter("city");
		String area=request.getParameter("area");
		DecimalFormat    df   = new DecimalFormat("#0.00");   
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		RescueCount count=new RescueCount();
		SimpleDateFormat d=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String sql="select gf.happen_Time as happenTime,gf.arrive_Time as arriveTime,gf.success_Time as successTime" +
				" from Gzcl_Rescue gr " +
				" left join Gzcl_Fault gf on gf.id=gr.fault_Id " +
				" left join Xtgl_Rescue_Unit xru on xru.id=gr.rescue_Unit_Id " +
				" where 1=1 ";
		String sql1="select  count( gf.id) " +
		" from Gzcl_Rescue gr " +
		" left join Gzcl_Fault gf on gf.id=gr.fault_Id " +
		" left join Xtgl_Rescue_Unit xru on xru.id=gr.rescue_Unit_Id " +
		" where 1=1 ";
		if(begintime!=null&&!begintime.equals("")){
			sql+=" and gf.happen_Time  >=to_date('" + begintime+ "','yyyy-MM-dd hh24:mi:ss')";
			sql1+=" and gf.happen_Time  >=to_date('" + begintime+ "','yyyy-MM-dd hh24:mi:ss')";
		}
		if(endtime!=null&&!endtime.equals("")){
			sql+=" and gf.happen_Time  <=to_date('" + endtime+ "','yyyy-MM-dd hh24:mi:ss')";
			sql1+=" and gf.happen_Time  <=to_date('" + endtime+ "','yyyy-MM-dd hh24:mi:ss')";
		}
		if(province!=null&&!province.equals("")){
			province=new String(province.getBytes("ISO-8859-1"),"UTF-8");
			sql+=" and xru.province  ='" + province+ "'";
			sql1+=" and xru.province  ='" + province+ "'";
			count.setProvince(province);
		}else{
			count.setProvince("所有");
		}
		if(city!=null&&!city.equals("")){
			city=new String(city.getBytes("ISO-8859-1"),"UTF-8");
			sql+=" and xru.city ='" + city+ "'";
			sql1+=" and xru.city ='" + city+ "'";
			count.setCity(city);
		}else{
			count.setCity("所有");
		}
		if(area!=null&&!area.equals("")){
			area=new String(area.getBytes("ISO-8859-1"),"UTF-8");
			sql+=" and xru.area  ='" + area+ "'";
			sql1+=" and xru.area  ='" + area+ "'";
			count.setArea(area);
		}else{
			count.setArea("所有");
		}
		Connection conn=DBEntity.getInstance().getConnection();
		PreparedStatement sta = conn.prepareStatement(sql);
		ResultSet rs = sta.executeQuery();
		double  arriveTime=0;
		double successTime=0;
		while(rs.next()){
			long arrive=((rs.getString("arriveTime")==null?0:d.parse(rs.getString("arriveTime")).getTime())-(rs.getString("happenTime")==null?0:d.parse(rs.getString("happenTime")).getTime()));
			arriveTime+= arrive /(1000*60);  		 //  救援到达时间  分钟
			long success=((rs.getString("successTime")==null?0:d.parse(rs.getString("successTime")).getTime())-(rs.getString("arriveTime")==null?0:d.parse(rs.getString("arriveTime")).getTime()));
			successTime+= success /(1000*60);  		 //  救援成功时间  分钟
		}
		count.setNum(DBEntity.getInstance().queryDataCount(sql1));
		count.setArriveTime(count.getNum()==0?"0":df.format(arriveTime/count.getNum()));
		count.setSuccessTime(count.getNum()==0?"0":df.format(successTime/count.getNum()));
		request.setAttribute("count",count );
		request.setAttribute("province",province );
		request.setAttribute("city",city );
		request.setAttribute("area",area );
		if(endtime!=null&&!endtime.equals("")){
			request.setAttribute("endtime", sdf.parse( endtime));
		}
		if(begintime!=null&&!begintime.equals("")){
			request.setAttribute("begintime", sdf.parse( begintime));
		}
		return	new ActionForward("/jsp/count/rescueRegionCount.jsp");
		}

	/**
	 * 导出 救援区域统计
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public void  exportRescueRegionCount(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		String begintime=request.getParameter("begintime");
		String endtime=request.getParameter("endtime");
		String province=request.getParameter("province");
		String city=request.getParameter("city");
		String area=request.getParameter("area");
		DecimalFormat    df   = new DecimalFormat("#0.00");   
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		RescueCount count=new RescueCount();
		SimpleDateFormat d=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String sql="select gf.happen_Time as happenTime,gf.arrive_Time as arriveTime,gf.success_Time as successTime" +
				" from Gzcl_Rescue gr " +
				" left join Gzcl_Fault gf on gf.id=gr.fault_Id " +
				" left join Xtgl_Rescue_Unit xru on xru.id=gr.rescue_Unit_Id " +
				" where 1=1 ";
		String sql1="select  count( gf.id) " +
		" from Gzcl_Rescue gr " +
		" left join Gzcl_Fault gf on gf.id=gr.fault_Id " +
		" left join Xtgl_Rescue_Unit xru on xru.id=gr.rescue_Unit_Id " +
		" where 1=1 ";
		if(begintime!=null&&!begintime.equals("")){
			sql+=" and gf.happen_Time  >=to_date('" + begintime+ "','yyyy-MM-dd hh24:mi:ss')";
			sql1+=" and gf.happen_Time  >=to_date('" + begintime+ "','yyyy-MM-dd hh24:mi:ss')";
		}
		if(endtime!=null&&!endtime.equals("")){
			sql+=" and gf.happen_Time  <=to_date('" + endtime+ "','yyyy-MM-dd hh24:mi:ss')";
			sql1+=" and gf.happen_Time  <=to_date('" + endtime+ "','yyyy-MM-dd hh24:mi:ss')";
		}
		if(province!=null&&!province.equals("")){
			province=new String(province.getBytes("ISO-8859-1"),"UTF-8");
			sql+=" and xru.province  ='" + province+ "'";
			sql1+=" and xru.province  ='" + province+ "'";
			count.setProvince(province);
		}else{
			count.setProvince("所有");
		}
		if(city!=null&&!city.equals("")){
			city=new String(city.getBytes("ISO-8859-1"),"UTF-8");
			sql+=" and xru.city ='" + city+ "'";
			sql1+=" and xru.city ='" + city+ "'";
			count.setCity(city);
		}else{
			count.setCity("所有");
		}
		if(area!=null&&!area.equals("")){
			area=new String(area.getBytes("ISO-8859-1"),"UTF-8");
			sql+=" and xru.area  ='" + area+ "'";
			sql1+=" and xru.area  ='" + area+ "'";
			count.setArea(area);
		}else{
			count.setArea("所有");
		}
		Connection conn=DBEntity.getInstance().getConnection();
		PreparedStatement sta = conn.prepareStatement(sql);
		ResultSet rs = sta.executeQuery();
		double  arriveTime=0;
		double successTime=0;
		while(rs.next()){
			long arrive=(d.parse(rs.getString("arriveTime")).getTime()-d.parse(rs.getString("happenTime")).getTime());
			arriveTime+= arrive /(1000*60);  		 //  救援到达时间  分钟
			long success=(d.parse(rs.getString("successTime")).getTime()-d.parse(rs.getString("arriveTime")).getTime());
			successTime+= success /(1000*60);  		 //  救援成功时间  分钟
		}
		count.setNum(DBEntity.getInstance().queryDataCount(sql1));
		count.setArriveTime(count.getNum()==0?"0":df.format(arriveTime/count.getNum()));
		count.setSuccessTime(count.getNum()==0?"0":df.format(successTime/count.getNum()));
		
		List<RescueCount> list=new ArrayList<RescueCount>();
		list.add(count);
		
		try {
		String dates = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String fileName = "救援区域统计" + dates + ".xls";
		String filePath = request.getRealPath("/")
		+ "excel\\" + fileName;
				try {
					
			// 生成excel文件
					System.out.println("========>>" + filePath);
					//Excel ex = new Excel();
					HSSFWorkbook workbook = new HSSFWorkbook();
					// 在Excel工作簿中建一工作表，其名为缺省值
					HSSFSheet sheet = null;
					HSSFRow row = null;
					HSSFCell cell = null;
					sheet = workbook.createSheet("区域统计");
					HSSFCellStyle style = workbook.createCellStyle(); // 样式对象
					style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
					style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平
					/**
					 * 设置字体
					 */
					HSSFFont f = workbook.createFont();
					f.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 字体加粗
					style.setFont(f);		
					String[] top_arraydis = null;
					
					if (1 == 1) {
						top_arraydis = ExcelColumns.RescueCount;
		
						row = sheet.createRow(0);// 创建一行
		
						for (int c = 0; c < top_arraydis.length; c++) {
							cell = row.createCell((short) c);// 创建格 字段 
							cell.setCellValue("中文");
							cell.setCellStyle(style);
							cell.setCellValue(top_arraydis[c]);
						}
						int j = 0;
						for (int i = 0; i < list.size(); i++) {
							    row = sheet.createRow(i+1);
							    RescueCount	e = (RescueCount) list.get(i);
								
							    cell = row.createCell(j);// 创建格 字段
								cell.setCellValue(e.getProvince());   
								
								cell = row.createCell(++j);// 创建格 字段
								cell.setCellValue(e.getCity());  
		
								cell = row.createCell(++j);// 创建格 字段
								cell.setCellValue(e.getArea());  
								
								cell = row.createCell(++j);// 创建格 字段
								cell.setCellValue(e.getNum());  
								
								cell = row.createCell(++j);// 创建格 字段
								cell.setCellValue(e.getArriveTime());   //电梯型号
								
								cell = row.createCell(++j);// 创建格 字段
								cell.setCellValue(e.getSuccessTime());   
								
								j = 0;
							}
					}
		
					// 新建一输出文件流
					
					FileOutputStream fOut = new FileOutputStream(filePath);
		//			System.out.println(fOut + "-----");
					// 把相应的Excel 工作簿存盘
					workbook.write(fOut);
					fOut.flush();
					// 操作结束，关闭文件
					fOut.close();
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("已运行 xlCreate() : " + e);
				}
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
}

	/**
	 *  救援单位 响应统计
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public ActionForward  rescueUnitCount (ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		String begintime=request.getParameter("begintime");
		String endtime=request.getParameter("endtime");
		String flag=request.getParameter("flag");
		String unitId=request.getParameter("unitId");
		String unitId1=request.getParameter("unitId1");
		DecimalFormat    df   = new DecimalFormat("#0.00");   
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		if(unitId1!=null&&!unitId1.equals("")){
			unitId1=new String(unitId1.getBytes("ISO-8859-1"),"UTF-8");
		}
		String sql="select xru.name as name, " +
					" count(gf.id) as num," +
					" sum( ROUND(TO_NUMBER(gf.arrive_Time - gf.happen_Time) * 24 * 60))/count(gf.id) as arriveTime," +
					" sum( ROUND(TO_NUMBER(gf.success_Time - gf.arrive_Time) * 24 * 60))/count(gf.id) as successTime" +
				" from Gzcl_Rescue gr " +
				" left join Gzcl_Fault gf on gf.id=gr.fault_Id " +
				" left join Xtgl_Rescue_Unit xru on xru.id=gr.rescue_Unit_Id " +
				" left join dtjk_elevator de on de.id=gf.elevator_id " +
				" left join xtgl_use_unit xuu on xuu.id=de.use_unit_id "+  //使用单位
				" left join xtgl_maintenance_unit xmu on xmu.id=de.maintenance_unit_id"+  //维保单位
				" left join Xtgl_Property_Unit xpu on xpu.id=de.property_Unit_Id"+  //物业单位
				" left join Xtgl_Make_Unit make on make.id=de.make_Unit_Id"+  //制造单位

				" where 1=1 ";
		
		if(begintime!=null&&!begintime.equals("")){
			sql+=" and gf.happen_Time  >=to_date('" + begintime+ "','yyyy-MM-dd hh24:mi:ss')";
		}
		if(endtime!=null&&!endtime.equals("")){
			sql+=" and gf.happen_Time  <=to_date('" + endtime+ "','yyyy-MM-dd hh24:mi:ss')";
		}
		if(flag!=null&&!flag.equals("")){
			if(flag.equals("1")&&unitId!=null&&!unitId.equals("")){
				sql+=" and xuu.id='"+unitId+"' ";
			}else if(flag.equals("2")&&unitId!=null&&!unitId.equals("")){
				sql+=" and xpu.id='"+unitId+"' ";
			}else if(flag.equals("3")&&unitId!=null&&!unitId.equals("")){
				sql+=" and xmu.id='"+unitId+"' ";
			}else if(flag.equals("4")&&unitId!=null&&!unitId.equals("")){
				sql+=" and make.id='"+unitId+"' ";
			}else if(flag.equals("5")&&unitId!=null&&!unitId.equals("")){
				sql+=" and gr.rescue_Unit_Id='"+unitId+"' ";
			}else {
				
			}
	}
		sql+="  group by xru.name order by successTime desc";
		List<RescueCount> list=new ArrayList<RescueCount>();
		Connection conn=DBEntity.getInstance().getConnection();
		PreparedStatement sta = conn.prepareStatement(sql);
		ResultSet rs = sta.executeQuery();
		List<String> names=new ArrayList<String>();
		List<String> num=new ArrayList<String>();
		List<String> arriveTime=new ArrayList<String>();
		List<String> successTime=new ArrayList<String>();
		while(rs.next()){
			RescueCount count=new RescueCount();
			count.setName(rs.getString("name"));
			count.setNum(rs.getInt("num"));
			count.setArriveTime(df.format(rs.getDouble("arriveTime")));
			count.setSuccessTime(df.format(rs.getDouble("successTime")));
			list.add(count);
			if(rs.getString("name")==null){
				names.add("\"\"");
			}else{
				names.add("\""+rs.getString("name")+"\"");
			}
			
			num.add(rs.getString("num"));
			arriveTime.add(df.format(rs.getDouble("arriveTime")));
			successTime.add(df.format(rs.getDouble("successTime")));
		}
		request.setAttribute("names",names.toString() );
		request.setAttribute("num",num.toString() );
		request.setAttribute("arriveTime",arriveTime.toString() );
		request.setAttribute("successTime",successTime.toString() );
		request.setAttribute("list",list );
		request.setAttribute("flag",flag);
		request.setAttribute("unitId1",unitId1);
		request.setAttribute("unitId",unitId);
		if(endtime!=null&&!endtime.equals("")){
			request.setAttribute("endtime", sdf.parse( endtime));
		}
		if(begintime!=null&&!begintime.equals("")){
			request.setAttribute("begintime", sdf.parse( begintime));
		}
		return	new ActionForward("/jsp/count/rescueUnitCount.jsp");
		}

	/**
	 * 导出 救援区域统计
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public void  exportRescueUnitCount(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		String begintime=request.getParameter("begintime");
		String endtime=request.getParameter("endtime");
		String flag=request.getParameter("flag");
		String unitId=request.getParameter("unitId");
		String unitId1=request.getParameter("unitId1");
		DecimalFormat    df   = new DecimalFormat("#0.00");   
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		if(unitId1!=null&&!unitId1.equals("")){
			unitId1=new String(unitId1.getBytes("ISO-8859-1"),"UTF-8");
		}
		String sql="select xru.name as name, " +
					" count(gf.id) as num," +
					" sum( ROUND(TO_NUMBER(gf.arrive_Time - gf.happen_Time) * 24 * 60))/count(gf.id) as arriveTime," +
					" sum( ROUND(TO_NUMBER(gf.success_Time - gf.arrive_Time) * 24 * 60))/count(gf.id) as successTime" +
				" from Gzcl_Rescue gr " +
				" left join Gzcl_Fault gf on gf.id=gr.fault_Id " +
				" left join Xtgl_Rescue_Unit xru on xru.id=gr.rescue_Unit_Id " +
				" left join dtjk_elevator de on de.id=gf.elevator_id " +
				" left join xtgl_use_unit xuu on xuu.id=de.use_unit_id "+  //使用单位
				" left join xtgl_maintenance_unit xmu on xmu.id=de.maintenance_unit_id"+  //维保单位
				" left join Xtgl_Property_Unit xpu on xpu.id=de.property_Unit_Id"+  //物业单位
				" left join Xtgl_Make_Unit make on make.id=de.make_Unit_Id"+  //制造单位

				" where 1=1 ";
		
		if(begintime!=null&&!begintime.equals("")){
			sql+=" and gf.happen_Time  >=to_date('" + begintime+ "','yyyy-MM-dd hh24:mi:ss')";
		}
		if(endtime!=null&&!endtime.equals("")){
			sql+=" and gf.happen_Time  <=to_date('" + endtime+ "','yyyy-MM-dd hh24:mi:ss')";
		}
		if(flag!=null&&!flag.equals("")){
			if(flag.equals("1")&&unitId!=null&&!unitId.equals("")){
				sql+=" and xuu.id='"+unitId+"' ";
			}else if(flag.equals("2")&&unitId!=null&&!unitId.equals("")){
				sql+=" and xpu.id='"+unitId+"' ";
			}else if(flag.equals("3")&&unitId!=null&&!unitId.equals("")){
				sql+=" and xmu.id='"+unitId+"' ";
			}else if(flag.equals("4")&&unitId!=null&&!unitId.equals("")){
				sql+=" and make.id='"+unitId+"' ";
			}else if(flag.equals("5")&&unitId!=null&&!unitId.equals("")){
				sql+=" and gr.rescue_Unit_Id='"+unitId+"' ";
			}else {
				
			}
	}
		sql+="  group by xru.name order by successTime desc";
		List<RescueCount> list=new ArrayList<RescueCount>();
		Connection conn=DBEntity.getInstance().getConnection();
		PreparedStatement sta = conn.prepareStatement(sql);
		ResultSet rs = sta.executeQuery();
		while(rs.next()){
			RescueCount count=new RescueCount();
			count.setName(rs.getString("name"));
			count.setNum(rs.getInt("num"));
			count.setArriveTime(rs.getString("arriveTime"));
			count.setSuccessTime(rs.getString("successTime"));
			list.add(count);
		}
		
		try {
		String dates = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String fileName = "救援响应统计" + dates + ".xls";
		String filePath = request.getRealPath("/")
		+ "excel\\" + fileName;
				try {
					
			// 生成excel文件
					System.out.println("========>>" + filePath);
					//Excel ex = new Excel();
					HSSFWorkbook workbook = new HSSFWorkbook();
					// 在Excel工作簿中建一工作表，其名为缺省值
					HSSFSheet sheet = null;
					HSSFRow row = null;
					HSSFCell cell = null;
					sheet = workbook.createSheet("响应统计");
					HSSFCellStyle style = workbook.createCellStyle(); // 样式对象
					style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
					style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平
					/**
					 * 设置字体
					 */
					HSSFFont f = workbook.createFont();
					f.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 字体加粗
					style.setFont(f);		
					String[] top_arraydis = null;
					
					if (1 == 1) {
						top_arraydis = ExcelColumns.RescueCount1;
		
						row = sheet.createRow(0);// 创建一行
		
						for (int c = 0; c < top_arraydis.length; c++) {
							cell = row.createCell((short) c);// 创建格 字段 
							cell.setCellValue("中文");
							cell.setCellStyle(style);
							cell.setCellValue(top_arraydis[c]);
						}
						int j = 0;
						for (int i = 0; i < list.size(); i++) {
							    row = sheet.createRow(i+1);
							    RescueCount	e = (RescueCount) list.get(i);
								
							    cell = row.createCell(j);// 创建格 字段
								cell.setCellValue(e.getName());   
								
								cell = row.createCell(++j);// 创建格 字段
								cell.setCellValue(e.getNum());  
								
								cell = row.createCell(++j);// 创建格 字段
								cell.setCellValue(e.getArriveTime());   //电梯型号
								
								cell = row.createCell(++j);// 创建格 字段
								cell.setCellValue(e.getSuccessTime());   
								
								j = 0;
							}
					}
		
					// 新建一输出文件流
					
					FileOutputStream fOut = new FileOutputStream(filePath);
		//			System.out.println(fOut + "-----");
					// 把相应的Excel 工作簿存盘
					workbook.write(fOut);
					fOut.flush();
					// 操作结束，关闭文件
					fOut.close();
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("已运行 xlCreate() : " + e);
				}
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
}

	/**
	 *  维保区域统计
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public ActionForward  maintenanceRegionCount (ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		String begintime=request.getParameter("begintime");
		String endtime=request.getParameter("endtime");
		String province=request.getParameter("province");
		String city=request.getParameter("city");
		String area=request.getParameter("area");
		DecimalFormat    df   = new DecimalFormat("#0.00");   
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		MaintenanceCount count=new MaintenanceCount();
		String sql="select  count(dmr.id) from Dtjk_Maintenance_Records dmr  left join dtjk_elevator de   on de.id = dmr.elevator_id where 1 = 1";
		String sql1="select count(de.id) from Dtjk_Elevator de   where de.delflag!='1' ";
		String sql2="select count(gf.id) from gzcl_fault gf  left join dtjk_elevator de on de.id=gf.elevator_id  where 1=1";

		
		if(begintime!=null&&!begintime.equals("")){
			sql2+=" and gf.happen_Time  >=to_date('" + begintime+ "','yyyy-MM-dd hh24:mi:ss')";
		}
		if(endtime!=null&&!endtime.equals("")){
			sql2+=" and gf.happen_Time  <=to_date('" + endtime+ "','yyyy-MM-dd hh24:mi:ss')";
		}
		if(province!=null&&!province.equals("")){
			province=new String(province.getBytes("ISO-8859-1"),"UTF-8");
			sql+=" and de.province  ='" + province+ "'";
			sql1+=" and de.province  ='" + province+ "'";
			sql2+=" and de.province  ='" + province+ "'";
			count.setProvince(province);
		}else{
			count.setProvince("所有");
		}
		if(city!=null&&!city.equals("")){
			city=new String(city.getBytes("ISO-8859-1"),"UTF-8");
			sql+=" and de.city ='" + city+ "'";
			sql1+=" and de.city ='" + city+ "'";
			sql2+=" and de.city ='" + city+ "'";
			count.setCity(city);
		}else{
			count.setCity("所有");
		}
		if(area!=null&&!area.equals("")){
			area=new String(area.getBytes("ISO-8859-1"),"UTF-8");
			sql+=" and de.area  ='" + area+ "'";
			sql1+=" and de.area  ='" + area+ "'";
			sql2+=" and de.area  ='" + area+ "'";
			count.setArea(area);
		}else{
			count.setArea("所有");
		}
		String sql3=sql2+" and  (gf.fault_Type='困人' or gf.fault_Type='冲顶困人' or gf.fault_Type='蹲底困人' or gf.fault_Type='非平层困人')";
		//电梯总数
		count.setNum(DBEntity.getInstance().queryDataCount(sql1));
		//维保卡总数
		count.setWbkNum(DBEntity.getInstance().queryDataCount(sql));
		//维保卡率
		count.setWbkRate(count.getNum()==0?"0":df.format((double)count.getWbkNum()/count.getNum()));
		//故障数量
		count.setFaultNum(DBEntity.getInstance().queryDataCount(sql2));
				//困人故障数量
		count.setPeopleTrappedNum(DBEntity.getInstance().queryDataCount(sql3));
		//困人率
		count.setPeopleTrappedRate(count.getFaultNum()==0?"0":df.format((double)count.getPeopleTrappedNum()/count.getFaultNum()));
		
		
		request.setAttribute("count",count );
		request.setAttribute("province",province );
		request.setAttribute("city",city );
		request.setAttribute("area",area );
		if(endtime!=null&&!endtime.equals("")){
			request.setAttribute("endtime", sdf.parse( endtime));
		}
		if(begintime!=null&&!begintime.equals("")){
			request.setAttribute("begintime", sdf.parse( begintime));
		}
		return	new ActionForward("/jsp/count/maintenanceRegionCount.jsp");
		}
	
	/**
	 * 导出 维保区域统计
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public void  exportMaintenanceRegionCount(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		String begintime=request.getParameter("begintime");
		String endtime=request.getParameter("endtime");
		String province=request.getParameter("province");
		String city=request.getParameter("city");
		String area=request.getParameter("area");
		DecimalFormat    df   = new DecimalFormat("#0.00");   
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		MaintenanceCount count=new MaintenanceCount();
		String sql="select  count(dmr.id) from Dtjk_Maintenance_Records dmr  left join dtjk_elevator de   on de.id = dmr.elevator_id where 1 = 1";
		String sql1="select count(de.id) from Dtjk_Elevator de   where de.delflag!='1' ";
		String sql2="select count(gf.id) from gzcl_fault gf  left join dtjk_elevator de on de.id=gf.elevator_id  where 1=1";

		
		if(begintime!=null&&!begintime.equals("")){
			sql2+=" and gf.happen_Time  >=to_date('" + begintime+ "','yyyy-MM-dd hh24:mi:ss')";
		}
		if(endtime!=null&&!endtime.equals("")){
			sql2+=" and gf.happen_Time  <=to_date('" + endtime+ "','yyyy-MM-dd hh24:mi:ss')";
		}
		if(province!=null&&!province.equals("")){
			province=new String(province.getBytes("ISO-8859-1"),"UTF-8");
			sql+=" and de.province  ='" + province+ "'";
			sql1+=" and de.province  ='" + province+ "'";
			sql2+=" and de.province  ='" + province+ "'";
			count.setProvince(province);
		}else{
			count.setProvince("所有");
		}
		if(city!=null&&!city.equals("")){
			city=new String(city.getBytes("ISO-8859-1"),"UTF-8");
			sql+=" and de.city ='" + city+ "'";
			sql1+=" and de.city ='" + city+ "'";
			sql2+=" and de.city ='" + city+ "'";
			count.setCity(city);
		}else{
			count.setCity("所有");
		}
		if(area!=null&&!area.equals("")){
			area=new String(area.getBytes("ISO-8859-1"),"UTF-8");
			sql+=" and de.area  ='" + area+ "'";
			sql1+=" and de.area  ='" + area+ "'";
			sql2+=" and de.area  ='" + area+ "'";
			count.setArea(area);
		}else{
			count.setArea("所有");
		}
		String sql3=sql2+" and  (gf.fault_Type='困人' or gf.fault_Type='冲顶困人' or gf.fault_Type='蹲底困人' or gf.fault_Type='非平层困人')";
		//电梯总数
		count.setNum(DBEntity.getInstance().queryDataCount(sql1));
		//维保卡总数
		count.setWbkNum(DBEntity.getInstance().queryDataCount(sql));
		//维保卡率
		count.setWbkRate(count.getNum()==0?"0":df.format((double)count.getWbkNum()/count.getNum()));
		//故障数量
		count.setFaultNum(DBEntity.getInstance().queryDataCount(sql2));
				//困人故障数量
		count.setPeopleTrappedNum(DBEntity.getInstance().queryDataCount(sql3));
		//困人率
		count.setPeopleTrappedRate(count.getFaultNum()==0?"0":df.format((double)count.getPeopleTrappedNum()/count.getFaultNum()));
		
		List<MaintenanceCount> list=new ArrayList<MaintenanceCount>();
		list.add(count);
		try {
		String dates = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String fileName = "维保区域统计" + dates + ".xls";
		String filePath = request.getRealPath("/")
		+ "excel\\" + fileName;
				try {
					
			// 生成excel文件
					System.out.println("========>>" + filePath);
					//Excel ex = new Excel();
					HSSFWorkbook workbook = new HSSFWorkbook();
					// 在Excel工作簿中建一工作表，其名为缺省值
					HSSFSheet sheet = null;
					HSSFRow row = null;
					HSSFCell cell = null;
					sheet = workbook.createSheet("区域统计");
					HSSFCellStyle style = workbook.createCellStyle(); // 样式对象
					style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
					style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平
					/**
					 * 设置字体
					 */
					HSSFFont f = workbook.createFont();
					f.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 字体加粗
					style.setFont(f);		
					String[] top_arraydis = null;
					
					if (1 == 1) {
						top_arraydis = ExcelColumns.MaintenanceCount;
		
						row = sheet.createRow(0);// 创建一行
		
						for (int c = 0; c < top_arraydis.length; c++) {
							cell = row.createCell((short) c);// 创建格 字段 
							cell.setCellValue("中文");
							cell.setCellStyle(style);
							cell.setCellValue(top_arraydis[c]);
						}
						int j = 0;
						for (int i = 0; i < list.size(); i++) {
							    row = sheet.createRow(i+1);
							    MaintenanceCount	e = (MaintenanceCount) list.get(i);
							    
							    cell = row.createCell(j);// 创建格 字段
								cell.setCellValue(e.getProvince());   
								
								cell = row.createCell(++j);// 创建格 字段
								cell.setCellValue(e.getCity());  
		
								cell = row.createCell(++j);// 创建格 字段
								cell.setCellValue(e.getArea());  
								
							    cell = row.createCell(++j);// 创建格 字段
								cell.setCellValue(e.getNum());   
								
								cell = row.createCell(++j);// 创建格 字段
								cell.setCellValue(e.getWbkNum());  
								
								cell = row.createCell(++j);// 创建格 字段
								cell.setCellValue(e.getWbkRate());   //电梯型号
								
								cell = row.createCell(++j);// 创建格 字段
								cell.setCellValue(e.getPeopleTrappedRate());   
								
								j = 0;
							}
					}
		
					// 新建一输出文件流
					
					FileOutputStream fOut = new FileOutputStream(filePath);
		//			System.out.println(fOut + "-----");
					// 把相应的Excel 工作簿存盘
					workbook.write(fOut);
					fOut.flush();
					// 操作结束，关闭文件
					fOut.close();
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("已运行 xlCreate() : " + e);
				}
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
}
	

	/**
	 *  维保出勤统计
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public ActionForward  maintenanceAttendanceCount (ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		String begintime=request.getParameter("begintime");
		String endtime=request.getParameter("endtime");
		String flag=request.getParameter("flag");
		String unitId=request.getParameter("unitId");
		String unitId1=request.getParameter("unitId1");
		DecimalFormat    df   = new DecimalFormat("#0.00");   
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		MaintenanceCount count=new MaintenanceCount();
		if(unitId1!=null&&!unitId1.equals("")){
			unitId1=new String(unitId1.getBytes("ISO-8859-1"),"UTF-8");
			count.setName(unitId1);
		}else{
			count.setName("所有单位");
		}
		
		String sql="select  count(dmr.id) " +
				" from Dtjk_Maintenance_Records dmr  " +
				" left join dtjk_elevator de   on de.id = dmr.elevator_id " +
				" left join xtgl_use_unit xuu on xuu.id=de.use_unit_id "+  //使用单位
				" left join xtgl_maintenance_unit xmu on xmu.id=de.maintenance_unit_id"+  //维保单位
				" left join Xtgl_Property_Unit xpu on xpu.id=de.property_Unit_Id"+  //物业单位
				" left join Xtgl_Make_Unit make on make.id=de.make_Unit_Id"+  //制造单位
				" where 1 = 1";
		String sql1="select count(de.id) " +
				" from Dtjk_Elevator de  " +
				" left join xtgl_use_unit xuu on xuu.id=de.use_unit_id "+  //使用单位
				" left join xtgl_maintenance_unit xmu on xmu.id=de.maintenance_unit_id"+  //维保单位
				" left join Xtgl_Property_Unit xpu on xpu.id=de.property_Unit_Id"+  //物业单位
				" left join Xtgl_Make_Unit make on make.id=de.make_Unit_Id"+  //制造单位
				" where de.delflag!='1' ";
		String sql2="select count(gf.id) " +
				"	from gzcl_fault gf " +
				" left join dtjk_elevator de on de.id=gf.elevator_id " +
				" left join xtgl_use_unit xuu on xuu.id=de.use_unit_id "+  //使用单位
				" left join xtgl_maintenance_unit xmu on xmu.id=de.maintenance_unit_id"+  //维保单位
				" left join Xtgl_Property_Unit xpu on xpu.id=de.property_Unit_Id"+  //物业单位
				" left join Xtgl_Make_Unit make on make.id=de.make_Unit_Id"+  //制造单位
		//		" left join Gzcl_Rescue gr on gr.fault_Id=gf.id"+  //救援单位 和故障 关系表
				" where 1=1";

		
		if(begintime!=null&&!begintime.equals("")){
			sql2+=" and gf.happen_Time  >=to_date('" + begintime+ "','yyyy-MM-dd hh24:mi:ss')";
		}
		if(endtime!=null&&!endtime.equals("")){
			sql2+=" and gf.happen_Time  <=to_date('" + endtime+ "','yyyy-MM-dd hh24:mi:ss')";
		}
		if(flag!=null&&!flag.equals("")){
			if(flag.equals("1")&&unitId!=null&&!unitId.equals("")){
				sql+=" and xuu.id='"+unitId+"' ";
				sql1+=" and xuu.id='"+unitId+"' ";
				sql2+=" and xuu.id='"+unitId+"' ";
			}else if(flag.equals("2")&&unitId!=null&&!unitId.equals("")){
				sql+=" and xpu.id='"+unitId+"' ";
				sql1+=" and xpu.id='"+unitId+"' ";
				sql2+=" and xpu.id='"+unitId+"' ";
			}else if(flag.equals("3")&&unitId!=null&&!unitId.equals("")){
				sql+=" and xmu.id='"+unitId+"' ";
				sql1+=" and xmu.id='"+unitId+"' ";
				sql2+=" and xmu.id='"+unitId+"' ";
			}else if(flag.equals("4")&&unitId!=null&&!unitId.equals("")){
				sql+=" and make.id='"+unitId+"' ";
				sql1+=" and make.id='"+unitId+"' ";
				sql2+=" and make.id='"+unitId+"' ";
			}else if(flag.equals("5")&&unitId!=null&&!unitId.equals("")){
				sql2+=" and gr.rescue_Unit_Id='"+unitId+"' ";
			}else {
				
			}
	}
		String sql3=sql2+" and  (gf.fault_Type='困人' or gf.fault_Type='冲顶困人' or gf.fault_Type='蹲底困人' or gf.fault_Type='非平层困人')";
		//电梯总数
		count.setNum(DBEntity.getInstance().queryDataCount(sql1));
		//维保卡总数
		count.setWbkNum(DBEntity.getInstance().queryDataCount(sql));
		//维保卡率
		count.setWbkRate(count.getNum()==0?"0":df.format((double)count.getWbkNum()/count.getNum()));
		//故障数量
		count.setFaultNum(DBEntity.getInstance().queryDataCount(sql2));
				//困人故障数量
		count.setPeopleTrappedNum(DBEntity.getInstance().queryDataCount(sql3));
		//困人率
		count.setPeopleTrappedRate(count.getFaultNum()==0?"0":df.format((double)count.getPeopleTrappedNum()/count.getFaultNum()));
		
		
		request.setAttribute("count",count );
		request.setAttribute("flag",flag);
		request.setAttribute("unitId1",unitId1);
		request.setAttribute("unitId",unitId);
		if(endtime!=null&&!endtime.equals("")){
			request.setAttribute("endtime", sdf.parse( endtime));
		}
		if(begintime!=null&&!begintime.equals("")){
			request.setAttribute("begintime", sdf.parse( begintime));
		}
		return	new ActionForward("/jsp/count/maintenanceAttendanceCount.jsp");
		}

	/**
	 * 导出 维保区域统计
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public void  exportMaintenanceAttendanceCount(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		String begintime=request.getParameter("begintime");
		String endtime=request.getParameter("endtime");
		String flag=request.getParameter("flag");
		String unitId=request.getParameter("unitId");
		String unitId1=request.getParameter("unitId1");
		DecimalFormat    df   = new DecimalFormat("#0.00");   
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		MaintenanceCount count=new MaintenanceCount();
		if(unitId1!=null&&!unitId1.equals("")){
			unitId1=new String(unitId1.getBytes("ISO-8859-1"),"UTF-8");
			count.setName(unitId1);
		}else{
			count.setName("所有单位");
		}
		
		String sql="select  count(dmr.id) " +
				" from Dtjk_Maintenance_Records dmr  " +
				" left join dtjk_elevator de   on de.id = dmr.elevator_id " +
				" left join xtgl_use_unit xuu on xuu.id=de.use_unit_id "+  //使用单位
				" left join xtgl_maintenance_unit xmu on xmu.id=de.maintenance_unit_id"+  //维保单位
				" left join Xtgl_Property_Unit xpu on xpu.id=de.property_Unit_Id"+  //物业单位
				" left join Xtgl_Make_Unit make on make.id=de.make_Unit_Id"+  //制造单位
				" where 1 = 1";
		String sql1="select count(de.id) " +
				" from Dtjk_Elevator de  " +
				" left join xtgl_use_unit xuu on xuu.id=de.use_unit_id "+  //使用单位
				" left join xtgl_maintenance_unit xmu on xmu.id=de.maintenance_unit_id"+  //维保单位
				" left join Xtgl_Property_Unit xpu on xpu.id=de.property_Unit_Id"+  //物业单位
				" left join Xtgl_Make_Unit make on make.id=de.make_Unit_Id"+  //制造单位
				" where de.delflag!='1' ";
		String sql2="select count(gf.id) " +
				"	from gzcl_fault gf " +
				" left join dtjk_elevator de on de.id=gf.elevator_id " +
				" left join xtgl_use_unit xuu on xuu.id=de.use_unit_id "+  //使用单位
				" left join xtgl_maintenance_unit xmu on xmu.id=de.maintenance_unit_id"+  //维保单位
				" left join Xtgl_Property_Unit xpu on xpu.id=de.property_Unit_Id"+  //物业单位
				" left join Xtgl_Make_Unit make on make.id=de.make_Unit_Id"+  //制造单位
		//		" left join Gzcl_Rescue gr on gr.fault_Id=gf.id"+  //救援单位 和故障 关系表
				" where 1=1";

		
		if(begintime!=null&&!begintime.equals("")){
			sql2+=" and gf.happen_Time  >=to_date('" + begintime+ "','yyyy-MM-dd hh24:mi:ss')";
		}
		if(endtime!=null&&!endtime.equals("")){
			sql2+=" and gf.happen_Time  <=to_date('" + endtime+ "','yyyy-MM-dd hh24:mi:ss')";
		}
		if(flag!=null&&!flag.equals("")){
			if(flag.equals("1")&&unitId!=null&&!unitId.equals("")){
				sql+=" and xuu.id='"+unitId+"' ";
				sql1+=" and xuu.id='"+unitId+"' ";
				sql2+=" and xuu.id='"+unitId+"' ";
			}else if(flag.equals("2")&&unitId!=null&&!unitId.equals("")){
				sql+=" and xpu.id='"+unitId+"' ";
				sql1+=" and xpu.id='"+unitId+"' ";
				sql2+=" and xpu.id='"+unitId+"' ";
			}else if(flag.equals("3")&&unitId!=null&&!unitId.equals("")){
				sql+=" and xmu.id='"+unitId+"' ";
				sql1+=" and xmu.id='"+unitId+"' ";
				sql2+=" and xmu.id='"+unitId+"' ";
			}else if(flag.equals("4")&&unitId!=null&&!unitId.equals("")){
				sql+=" and make.id='"+unitId+"' ";
				sql1+=" and make.id='"+unitId+"' ";
				sql2+=" and make.id='"+unitId+"' ";
			}else if(flag.equals("5")&&unitId!=null&&!unitId.equals("")){
				sql2+=" and gr.rescue_Unit_Id='"+unitId+"' ";
			}else {
				
			}
	}
		String sql3=sql2+" and  (gf.fault_Type='困人' or gf.fault_Type='冲顶困人' or gf.fault_Type='蹲底困人' or gf.fault_Type='非平层困人')";
		//电梯总数
		count.setNum(DBEntity.getInstance().queryDataCount(sql1));
		//维保卡总数
		count.setWbkNum(DBEntity.getInstance().queryDataCount(sql));
		//维保卡率
		count.setWbkRate(count.getNum()==0?"0":df.format((double)count.getWbkNum()/count.getNum()));
		//故障数量
		count.setFaultNum(DBEntity.getInstance().queryDataCount(sql2));
				//困人故障数量
		count.setPeopleTrappedNum(DBEntity.getInstance().queryDataCount(sql3));
		//困人率
		count.setPeopleTrappedRate(count.getFaultNum()==0?"0":df.format((double)count.getPeopleTrappedNum()/count.getFaultNum()));
		
		List<MaintenanceCount> list=new ArrayList<MaintenanceCount>();
		list.add(count);
		try {
		String dates = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String fileName = "维保出勤统计" + dates + ".xls";
		String filePath = request.getRealPath("/")
		+ "excel\\" + fileName;
				try {
					
			// 生成excel文件
					System.out.println("========>>" + filePath);
					//Excel ex = new Excel();
					HSSFWorkbook workbook = new HSSFWorkbook();
					// 在Excel工作簿中建一工作表，其名为缺省值
					HSSFSheet sheet = null;
					HSSFRow row = null;
					HSSFCell cell = null;
					sheet = workbook.createSheet("出勤统计");
					HSSFCellStyle style = workbook.createCellStyle(); // 样式对象
					style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
					style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平
					/**
					 * 设置字体
					 */
					HSSFFont f = workbook.createFont();
					f.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 字体加粗
					style.setFont(f);		
					String[] top_arraydis = null;
					
					if (1 == 1) {
						top_arraydis = ExcelColumns.MaintenanceCount1;
		
						row = sheet.createRow(0);// 创建一行
		
						for (int c = 0; c < top_arraydis.length; c++) {
							cell = row.createCell((short) c);// 创建格 字段 
							cell.setCellValue("中文");
							cell.setCellStyle(style);
							cell.setCellValue(top_arraydis[c]);
						}
						int j = 0;
						for (int i = 0; i < list.size(); i++) {
							    row = sheet.createRow(i+1);
							    MaintenanceCount	e = (MaintenanceCount) list.get(i);
							    
							    cell = row.createCell(j);// 创建格 字段
								cell.setCellValue(e.getName());   
								
								
							    cell = row.createCell(++j);// 创建格 字段
								cell.setCellValue(e.getNum());   
								
								cell = row.createCell(++j);// 创建格 字段
								cell.setCellValue(e.getWbkNum());  
								
								cell = row.createCell(++j);// 创建格 字段
								cell.setCellValue(e.getWbkRate());   //电梯型号

								cell = row.createCell(++j);// 创建格 字段
								cell.setCellValue(e.getFaultNum());  
		
								cell = row.createCell(++j);// 创建格 字段
								cell.setCellValue(e.getPeopleTrappedNum());  
								
								cell = row.createCell(++j);// 创建格 字段
								cell.setCellValue(e.getPeopleTrappedRate());   
								
								j = 0;
							}
					}
		
					// 新建一输出文件流
					
					FileOutputStream fOut = new FileOutputStream(filePath);
		//			System.out.println(fOut + "-----");
					// 把相应的Excel 工作簿存盘
					workbook.write(fOut);
					fOut.flush();
					// 操作结束，关闭文件
					fOut.close();
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("已运行 xlCreate() : " + e);
				}
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
}
	

	/**
	 * 短信统计
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public ActionForward  messageCount (ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		String begintime=request.getParameter("begintime");
		String endtime=request.getParameter("endtime");
		String flag=request.getParameter("flag");
		String unitId=request.getParameter("unitId");
		String unitId1=request.getParameter("unitId1");
		String num=request.getParameter("num");   //当前页

		DecimalFormat    df   = new DecimalFormat("#0.00");   
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		if(unitId1!=null&&!unitId1.equals("")){
			unitId1=new String(unitId1.getBytes("ISO-8859-1"),"UTF-8");
		}
		Page  page=new Page();
		if(num!=null&&!num.equals("")){
			page.setPageNum(Integer.parseInt(num));//当前页数
		}else{
			page.setPageNum(0);//当前页数
		}
		String sql="select xm.phone as phone, count(xm.id) as num, xm.name as name  " +
				" from xtsz_message xm " +
				" left join xtgl_use_unit xuu   on xuu.phone = xm.phone  " +
				" left join xtgl_maintenance_unit xmu   on xmu.phone = xm.phone " +
				" left join Xtgl_Property_Unit xpu   on xpu.phone = xm.phone " +
				" left join Xtgl_Make_Unit make   on make.phone = xm.phone" +
				" left join Xtgl_Safe_Unit xsu   on xsu.phone = xm.phone" +				
				" left join Xtgl_Rescue_Unit xru   on xru.phone = xm.phone" +
				" where 1=1" ;
		String sql1="select count(xm.id)  " +
		" from xtsz_message xm " +
		" left join xtgl_use_unit xuu   on xuu.phone = xm.phone  " +
		" left join xtgl_maintenance_unit xmu   on xmu.phone = xm.phone " +
		" left join Xtgl_Property_Unit xpu   on xpu.phone = xm.phone " +
		" left join Xtgl_Make_Unit make   on make.phone = xm.phone" +
		" left join Xtgl_Safe_Unit xsu   on xsu.phone = xm.phone" +				
		" left join Xtgl_Rescue_Unit xru   on xru.phone = xm.phone" +
		" where 1=1" ;

		
		if(begintime!=null&&!begintime.equals("")){
			sql+=" and xm.Time  >=to_date('" + begintime+ "','yyyy-MM-dd hh24:mi:ss')";
			sql1+=" and xm.Time  >=to_date('" + begintime+ "','yyyy-MM-dd hh24:mi:ss')";
		}
		if(endtime!=null&&!endtime.equals("")){
			sql+=" and xm.Time  <=to_date('" + endtime+ "','yyyy-MM-dd hh24:mi:ss')";
			sql1+=" and xm.Time  <=to_date('" + begintime+ "','yyyy-MM-dd hh24:mi:ss')";
		}
		if(flag!=null&&!flag.equals("")){
			if(flag.equals("1")&&unitId!=null&&!unitId.equals("")){
				sql+=" and xuu.id='"+unitId+"' ";
				sql1+=" and xuu.id='"+unitId+"' ";
			}else if(flag.equals("2")&&unitId!=null&&!unitId.equals("")){
				sql+=" and xpu.id='"+unitId+"' ";
				sql1+=" and xpu.id='"+unitId+"' ";
			}else if(flag.equals("3")&&unitId!=null&&!unitId.equals("")){
				sql+=" and xmu.id='"+unitId+"' ";
				sql1+=" and xmu.id='"+unitId+"' ";
			}else if(flag.equals("4")&&unitId!=null&&!unitId.equals("")){
				sql+=" and make.id='"+unitId+"' ";
				sql1+=" and make.id='"+unitId+"' ";
			}else if(flag.equals("5")&&unitId!=null&&!unitId.equals("")){
				sql+=" and xru.id='"+unitId+"' ";
				sql1+=" and xru.id='"+unitId+"' ";
			}else if(flag.equals("6")&&unitId!=null&&!unitId.equals("")){
				sql+=" and xru.id='"+unitId+"' ";
				sql1+=" and xru.id='"+unitId+"' ";
			}else {
				
			}
	}
		 sql=sql+" group by xm.phone,xm.name order by num";
		 sql1=sql1+" group by xm.phone,xm.name ";
			Connection conn=DBEntity.getInstance().getConnection();
			List<MessageCount> list=new ArrayList<MessageCount>();
			String sql2="select * from ( select a.*,rownum rn from ("+sql+") a where rownum<="+page.getPageSize() * (page.getPageNum() +1)+") where rn>="+(page.getPageSize() * page.getPageNum()+1);
			int siz=	DBEntity.getInstance().queryDataCount(sql1);
			page.setCount(siz);//总记录数
			page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//总页数	
			List<String> title=new ArrayList<String>();
			List<String> num1=new ArrayList<String>();
			PreparedStatement sta = conn.prepareStatement(sql2);
			ResultSet rs = sta.executeQuery();
			while(rs.next()){
				MessageCount count=new MessageCount();
				count.setName(rs.getString("name"));
				count.setNum(rs.getInt("num"));
				count.setPhone(rs.getString("phone"));
				list.add(count);
				if(rs.getString("phone")==null){
					title.add("\"\"");
				}else{
					title.add("\""+rs.getString("phone")+"\"");
				}
				num1.add(rs.getString("num"));
			}
			request.setAttribute("title",title.toString() );
			request.setAttribute("num1",num1.toString() );
		request.setAttribute("list",list );
		request.setAttribute("flag",flag);
		request.setAttribute("unitId1",unitId1);
		request.setAttribute("unitId",unitId);
		request.setAttribute("page", page);
		if(endtime!=null&&!endtime.equals("")){
			request.setAttribute("endtime", sdf.parse( endtime));
		}
		if(begintime!=null&&!begintime.equals("")){
			request.setAttribute("begintime", sdf.parse( begintime));
		}
		return	new ActionForward("/jsp/count/messageCount.jsp");
		}

	/**
	 * 导出 短信统计
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public void  exportMessageCount(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		String begintime=request.getParameter("begintime");
		String endtime=request.getParameter("endtime");
		String flag=request.getParameter("flag");
		String unitId=request.getParameter("unitId");
		String unitId1=request.getParameter("unitId1");
		DecimalFormat    df   = new DecimalFormat("#0.00");   
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		if(unitId1!=null&&!unitId1.equals("")){
			unitId1=new String(unitId1.getBytes("ISO-8859-1"),"UTF-8");
		}
		
		String sql="select xm.phone as phone, count(xm.id) as num, xm.name as name  " +
				" from xtsz_message xm " +
				" left join xtgl_use_unit xuu   on xuu.phone = xm.phone  " +
				" left join xtgl_maintenance_unit xmu   on xmu.phone = xm.phone " +
				" left join Xtgl_Property_Unit xpu   on xpu.phone = xm.phone " +
				" left join Xtgl_Make_Unit make   on make.phone = xm.phone" +
				" left join Xtgl_Safe_Unit xsu   on xsu.phone = xm.phone" +				
				" left join Xtgl_Rescue_Unit xru   on xru.phone = xm.phone" +
				" where 1=1" ;
		

		
		if(begintime!=null&&!begintime.equals("")){
			sql+=" and xm.Time  >=to_date('" + begintime+ "','yyyy-MM-dd hh24:mi:ss')";
		}
		if(endtime!=null&&!endtime.equals("")){
			sql+=" and xm.Time  <=to_date('" + endtime+ "','yyyy-MM-dd hh24:mi:ss')";
		}
		if(flag!=null&&!flag.equals("")){
			if(flag.equals("1")&&unitId!=null&&!unitId.equals("")){
				sql+=" and xuu.id='"+unitId+"' ";
			}else if(flag.equals("2")&&unitId!=null&&!unitId.equals("")){
				sql+=" and xpu.id='"+unitId+"' ";
			}else if(flag.equals("3")&&unitId!=null&&!unitId.equals("")){
				sql+=" and xmu.id='"+unitId+"' ";
			}else if(flag.equals("4")&&unitId!=null&&!unitId.equals("")){
				sql+=" and make.id='"+unitId+"' ";
			}else if(flag.equals("5")&&unitId!=null&&!unitId.equals("")){
				sql+=" and xru.id='"+unitId+"' ";
			}else if(flag.equals("6")&&unitId!=null&&!unitId.equals("")){
				sql+=" and xru.id='"+unitId+"' ";
			}else {
				
			}
	}
		 sql=sql+" group by xm.phone,xm.name order by num";
			Connection conn=DBEntity.getInstance().getConnection();
			List<MessageCount> list=new ArrayList<MessageCount>();
			PreparedStatement sta = conn.prepareStatement(sql);
			ResultSet rs = sta.executeQuery();
			while(rs.next()){
				MessageCount count=new MessageCount();
				count.setName(rs.getString("name"));
				count.setNum(rs.getInt("num"));
				count.setPhone(rs.getString("phone"));
				list.add(count);
			}
		try {
		String dates = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String fileName = "短信统计" + dates + ".xls";
		String filePath = request.getRealPath("/")
		+ "excel\\" + fileName;
				try {
					
			// 生成excel文件
					System.out.println("========>>" + filePath);
					//Excel ex = new Excel();
					HSSFWorkbook workbook = new HSSFWorkbook();
					// 在Excel工作簿中建一工作表，其名为缺省值
					HSSFSheet sheet = null;
					HSSFRow row = null;
					HSSFCell cell = null;
					sheet = workbook.createSheet("短信统计");
					HSSFCellStyle style = workbook.createCellStyle(); // 样式对象
					style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
					style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平
					/**
					 * 设置字体
					 */
					HSSFFont f = workbook.createFont();
					f.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 字体加粗
					style.setFont(f);		
					String[] top_arraydis = null;
					
					if (1 == 1) {
						top_arraydis = ExcelColumns.MessageCount;
		
						row = sheet.createRow(0);// 创建一行
		
						for (int c = 0; c < top_arraydis.length; c++) {
							cell = row.createCell((short) c);// 创建格 字段 
							cell.setCellValue("中文");
							cell.setCellStyle(style);
							cell.setCellValue(top_arraydis[c]);
						}
						int j = 0;
						for (int i = 0; i < list.size(); i++) {
							    row = sheet.createRow(i+1);
							    MessageCount	e = (MessageCount) list.get(i);
							    
							    cell = row.createCell(j);// 创建格 字段
								cell.setCellValue(e.getName());   
								
								 cell = row.createCell(++j);// 创建格 字段
								cell.setCellValue(e.getPhone());   
								
							    cell = row.createCell(++j);// 创建格 字段
								cell.setCellValue(e.getNum());   
								
								
								j = 0;
							}
					}
		
					// 新建一输出文件流
					
					FileOutputStream fOut = new FileOutputStream(filePath);
		//			System.out.println(fOut + "-----");
					// 把相应的Excel 工作簿存盘
					workbook.write(fOut);
					fOut.flush();
					// 操作结束，关闭文件
					fOut.close();
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("已运行 xlCreate() : " + e);
				}
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
}
	/**
	 * 维保单位统计
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  maintenanceUnitCount(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String begintime=request.getParameter("begintime");	
		String endtime=request.getParameter("endtime");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");		
		DecimalFormat    df   = new DecimalFormat("0.00");   
		
		Connection conn=DBEntity.getInstance().getConnection();																	//故障总数
		
		String sql="select distinct de.maintenance_Unit_Id,x.name  from dtjk_elevator  de   left join Xtgl_Maintenance_Unit x on x.id=de.maintenance_Unit_Id  where de.delflag='0'  " ;
		 
		PreparedStatement sta = conn.prepareStatement(sql);
		ResultSet rs = sta.executeQuery();
		List<BrandCount> counts=new ArrayList<BrandCount>();
		JSONArray rows = new JSONArray();				//详细数据
		JSONArray title = new JSONArray();				//饼状图 标题
		while(rs.next()){
			BrandCount count=new BrandCount();
			 count.setBrand(rs.getString("name"));						//电梯品牌
			 sql="select count(de.id) from dtjk_elevator de  where de.delflag='0' ";
			 if(rs.getString("maintenance_Unit_Id")!=null){
				 sql+= "and de.maintenance_Unit_Id='"+rs.getString("maintenance_Unit_Id")+"' " ;
			 }else{
				 sql+= "and de.maintenance_Unit_Id is null " ;
			 }
			 int num=DBEntity.getInstance().queryDataCount(sql); 	
			 count.setNum(num);						//品牌电梯数量
			 sql="select count(g.id) from gzcl_fault g   left join dtjk_elevator de on de.id=g.elevator_Id  where de.delflag='0'  " ;
			 if(rs.getString("maintenance_Unit_Id")!=null){
				 sql+= "and de.maintenance_Unit_Id='"+rs.getString("maintenance_Unit_Id")+"' " ;
			 }else{
				 sql+= "and de.maintenance_Unit_Id is null " ;
			 }
			 if(begintime!=null&&!begintime.equals("")){
					sql+=" and g.happen_Time  >=to_date('" + begintime+ "','yyyy-MM-dd hh24:mi:ss')";
				}
				if(endtime!=null&&!endtime.equals("")){
					sql+=" and g.happen_Time  <=to_date('" + endtime+ "','yyyy-MM-dd hh24:mi:ss')";
				}
			 int faultNum=DBEntity.getInstance().queryDataCount(sql);
			 count.setFaultNum(faultNum);						//故障次数
			 float l= (float)faultNum/num;
			 String incidence=df.format(l*100);														//故障率
			 count.setIncidence(incidence);				//故障发生率
			 String begin="";
			 String end="";
			 	if(begintime!=null&&!begintime.equals("")){
			 		begin="to_date('" +sdf.format(sdf.parse(begintime)) + "','yyyy-MM-dd')";
				}else{
					begin="de.install_time";
				}
				if(endtime!=null&&!endtime.equals("")){
					end=sdf.format(sdf.parse(endtime)) ;
				}else{
					end=sdf.format(new Date()) ;
				}
			 sql="select sum(to_date('" +end+ "','yyyy-MM-dd')-"+begin+") from dtjk_elevator de where de.delflag='0' ";
			 if(rs.getString("maintenance_Unit_Id")!=null){
				 sql+= "and de.maintenance_Unit_Id='"+rs.getString("maintenance_Unit_Id")+"' " ;
			 }else{
				 sql+= "and de.maintenance_Unit_Id is null " ;
			 }
			 
			 int time=DBEntity.getInstance().queryDataCount(sql); 	
			 if(time!=0&&num!=0&&faultNum!=0){
				 count.setTime(df.format((time*num)/faultNum));
				 rows.add((time*num)/faultNum);
			 }else{
				 count.setTime("0");
				 rows.add(0);
			 }
			 
			 counts.add(count);
			 title.add(count.getBrand()==null?"":count.getBrand());
		}
		
		
		if(endtime!=null&&!endtime.equals("")){
			request.setAttribute("endtime", sdf.parse( endtime));
		}
		if(begintime!=null&&!begintime.equals("")){
			request.setAttribute("begintime", sdf.parse( begintime));
		}
		request.setAttribute("rows", rows);
		request.setAttribute("title", title);
		request.setAttribute("counts", counts);
	    return	new ActionForward("/jsp/count/Maintenance.jsp");
	}
	

	/**
	 * 使用单位统计
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  useUnitCount(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String begintime=request.getParameter("begintime");	
		String endtime=request.getParameter("endtime");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");		
		DecimalFormat    df   = new DecimalFormat("0.00");   
		
		Connection conn=DBEntity.getInstance().getConnection();																	//故障总数
		
		String sql="select distinct de.use_Unit_Id,x.name  from dtjk_elevator  de  left join Xtgl_Use_Unit x on x.id=de.use_unit_id   where de.delflag='0'  " ;
		 
		PreparedStatement sta = conn.prepareStatement(sql);
		ResultSet rs = sta.executeQuery();
		List<BrandCount> counts=new ArrayList<BrandCount>();
		JSONArray rows = new JSONArray();				//详细数据
		JSONArray title = new JSONArray();				//饼状图 标题
		while(rs.next()){
			BrandCount count=new BrandCount();
			 count.setBrand(rs.getString("name"));						//电梯品牌
			 sql="select count(de.id) from dtjk_elevator de where de.delflag='0' ";
			 if(rs.getString("use_Unit_Id")!=null){
				 sql+= "and de.use_Unit_Id='"+rs.getString("use_Unit_Id")+"' " ;
			 }else{
				 sql+= "and de.use_Unit_Id is null " ;
			 }
			 int num=DBEntity.getInstance().queryDataCount(sql); 	
			 count.setNum(num);						//品牌电梯数量
			 sql="select count(g.id) from gzcl_fault g   left join dtjk_elevator de on de.id=g.elevator_Id  where de.delflag='0'  " ;
			 if(rs.getString("use_Unit_Id")!=null){
				 sql+= "and de.use_Unit_Id='"+rs.getString("use_Unit_Id")+"' " ;
			 }else{
				 sql+= "and de.use_Unit_Id is null " ;
			 }
			 if(begintime!=null&&!begintime.equals("")){
					sql+=" and g.happen_Time  >=to_date('" + begintime+ "','yyyy-MM-dd hh24:mi:ss')";
				}
				if(endtime!=null&&!endtime.equals("")){
					sql+=" and g.happen_Time  <=to_date('" + endtime+ "','yyyy-MM-dd hh24:mi:ss')";
				}
			 int faultNum=DBEntity.getInstance().queryDataCount(sql);
			 count.setFaultNum(faultNum);						//故障次数
			 float l= (float)faultNum/num;
			 String incidence=df.format(l*100);														//故障率
			 count.setIncidence(incidence);				//故障发生率
			 String begin="";
			 String end="";
			 	if(begintime!=null&&!begintime.equals("")){
			 		begin="to_date('" +sdf.format(sdf.parse(begintime)) + "','yyyy-MM-dd')";
				}else{
					begin="de.install_time";
				}
				if(endtime!=null&&!endtime.equals("")){
					end=sdf.format(sdf.parse(endtime)) ;
				}else{
					end=sdf.format(new Date()) ;
				}
			 sql="select sum(to_date('" +end+ "','yyyy-MM-dd')-"+begin+") from dtjk_elevator de where de.delflag='0' ";
			 if(rs.getString("use_Unit_Id")!=null){
				 sql+= "and de.use_Unit_Id='"+rs.getString("use_Unit_Id")+"' " ;
			 }else{
				 sql+= "and de.use_Unit_Id is null " ;
			 }
			 
			 int time=DBEntity.getInstance().queryDataCount(sql); 	
			 if(time!=0&&num!=0&&faultNum!=0){
				 count.setTime(df.format((time*num)/faultNum));
				 rows.add((time*num)/faultNum);
			 }else{
				 count.setTime("0");
				 rows.add(0);
			 }
			 
			 counts.add(count);
			 title.add(count.getBrand()==null?"":count.getBrand());
		}
		
		
		if(endtime!=null&&!endtime.equals("")){
			request.setAttribute("endtime", sdf.parse( endtime));
		}
		if(begintime!=null&&!begintime.equals("")){
			request.setAttribute("begintime", sdf.parse( begintime));
		}
		request.setAttribute("rows", rows);
		request.setAttribute("title", title);
		request.setAttribute("counts", counts);
	    return	new ActionForward("/jsp/count/useNuit.jsp");
	}

}
