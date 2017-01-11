package com.jrsoft.fri.xtgl.dao;

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
import com.jrsoft.fri.xtgl.entity.XtglMaintenanceUnit;
import com.jrsoft.fri.xtgl.from.ExcelColumns;

public class XtglMaintenanceUnitDaoImpl  extends BaseDaoImpl< XtglMaintenanceUnit, String> implements  XtglMaintenanceUnitDao{

	@Override
	public Class<? extends Object> getEntityClass() {
		// TODO Auto-generated method stub
		return  XtglMaintenanceUnit.class;
	}

	@Override
	public void export(String filePath, XtglMaintenanceUnit gateway) {
		try {
			System.out.println("========>>" + filePath);
			//Excel ex = new Excel();
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 在Excel工作簿中建一工作表，其名为缺省值
			HSSFSheet sheet = null;
			HSSFRow row = null;
			HSSFCell cell = null;
			List<XtglMaintenanceUnit> list=new ArrayList<XtglMaintenanceUnit>();
			sheet = workbook.createSheet("维保单位信息");
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
			String sql="select dr.*  from Xtgl_maintenance_unit dr where  1=1 " ;
			if(gateway.getName()!=null&&!gateway.getName().equals("")){
				sql+=" and name like '%"+gateway.getName()+"%'";
			}
			if(gateway.getLiaisons()!=null&&!gateway.getLiaisons().equals("")){
				sql+=" and liaisons like '%"+gateway.getLiaisons()+"%'";
			}
			if(gateway.getPhone()!=null&&!gateway.getPhone().equals("")){
				sql+=" and phone like '%"+gateway.getPhone()+"%'";
			}
			
			sql+=" order by id";	
			
			PreparedStatement sta = conn.prepareStatement(sql);
			ResultSet rs = sta.executeQuery();
			list=new ArrayList<XtglMaintenanceUnit>();
			while(rs.next()){
				XtglMaintenanceUnit elevators=new XtglMaintenanceUnit();
				elevators.setId(rs.getLong("id"));
				elevators.setName(rs.getString("name"));
				elevators.setLiaisons(rs.getString("liaisons"));
				elevators.setPhone(rs.getString("phone"));
				elevators.setAddress(rs.getString("address"));
				elevators.setCode(rs.getString("code"));
				elevators.setCorporation(rs.getString("corporation"));
				elevators.setProvince(rs.getString("province"));
				elevators.setCity(rs.getString("city"));
				elevators.setArea(rs.getString("area"));
				list.add(elevators);
				
			}
			
			if (1 == 1) {
				top_arraydis = ExcelColumns.MaintenanceUnit;

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
					    XtglMaintenanceUnit	e = (XtglMaintenanceUnit) list.get(i);
						
						cell = row.createCell(j);// 创建格 字段
						cell.setCellValue(e.getName());   //单位名称
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getLiaisons());   //负责人

						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getPhone());   //负责人电话
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getAddress());   //办公地点

						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getCode());   //公司代码
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getCorporation());   //法人


						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getProvince());   //省
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getCity());   //市
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getArea());   //区
						
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
