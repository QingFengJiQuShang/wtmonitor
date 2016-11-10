package com.jrsoft.fri.dtjk.dao;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import smart.sys.platform.dao.DBEntity;

import com.jrsoft.fri.common.core.dao.BaseDaoImpl;
import com.jrsoft.fri.dtjk.entity.DtjkElevator;
import com.jrsoft.fri.dtjk.entity.DtjkMaintenanceRecords;
import com.jrsoft.fri.xtgl.entity.XtglUseUnit;
import com.jrsoft.fri.xtgl.from.ExcelColumns;

public class DtjkMaintenanceRecordsDaoImpl extends BaseDaoImpl< DtjkMaintenanceRecords, String> implements  DtjkMaintenanceRecordsDao{

	@Override
	public Class<? extends Object> getEntityClass() {
		// TODO Auto-generated method stub
		return DtjkMaintenanceRecords.class;
	}

	@Override
	public void export(String filePath, DtjkMaintenanceRecords records) {
		try {
			System.out.println("========>>" + filePath);
			//Excel ex = new Excel();
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 在Excel工作簿中建一工作表，其名为缺省值
			HSSFSheet sheet = null;
			HSSFRow row = null;
			HSSFCell cell = null;
			List<DtjkMaintenanceRecords> list=new ArrayList<DtjkMaintenanceRecords>();
			sheet = workbook.createSheet("维保记录信息");
			HSSFCellStyle style = workbook.createCellStyle(); // 样式对象
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平
			/**
			 * 设置字体
			 */
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式	
			HSSFFont f = workbook.createFont();
			f.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 字体加粗
			style.setFont(f);		
			String[] top_arraydis = null;
			Connection conn = DBEntity.getInstance().getConnection();
			//查询服务订单
			//查询服务订单
			String sql="select de.*,xuu.name as userunitname,xmu.name unitname,mu.name as username,e.registerid as registerid,e.distinguishid as distinguishid,e.install_place as place  " +
					" from dtjk_maintenance_records de " +
					" left join xtgl_use_unit xuu on xuu.id=de.use_unit_id "+  //使用单位
					" left join xtgl_maintenance_unit xmu on xmu.id=de.unit_id"+  //维保单位
					" left join xtgl_maintenance_users mu on mu.id=de.user_id"+  //维保人员
					" left join dtjk_elevator e on e.id=de.elevator_id "+  //电梯信息
					"where  1=1 " ;
			if(records.getElevatorId().getId()!=null&&!records.getElevatorId().getId().equals("")){
				sql+=" and de.elevator_Id = '"+records.getElevatorId().getId()+"'";
			}
			if(records.getTime()!=null&&!records.getTime().equals("")){
				sql+=" and de.time = to_date('" + df.format(records.getTime()) + "','yyyy-MM-dd')";
			}
			if(records.getUseUnitId().getId()!=null&&!records.getUseUnitId().getId().equals("")){
				sql+=" and de.use_unit_id  ='"+records.getUseUnitId().getId()+"'";
			}
			if(records.getUnitId().getId()!=null&&!records.getUnitId().getId().equals("")){
				sql+=" and de.unit_id  ='"+records.getUnitId().getId()+"'";
			}
			sql+=" order by time desc";	
			
			PreparedStatement sta = conn.prepareStatement(sql);
			ResultSet rs = sta.executeQuery();
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
			
			if (1 == 1) {
				top_arraydis = ExcelColumns.DtjkMaintenanceRecords;

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
					    DtjkMaintenanceRecords	e = (DtjkMaintenanceRecords) list.get(i);
						
						cell = row.createCell(j);// 创建格 字段
						cell.setCellValue(e.getRegisterid());   //电梯注册号
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getDistinguishid());   //识别码
			
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getUseUnitName());   //使用单位

						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getPlace());   //安装地点
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(df.format(e.getTime()));   //维保日期
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getPlace());   //安装地点
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getUnitName());   //维保单位
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getUserName());   //维保人
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getContent());   //维保内容

						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getRemarks());   //备注
						
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
			return;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("已运行 xlCreate() : " + e);
		}
		
		
	}

}
