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
import com.jrsoft.fri.xtgl.entity.XtglMaintenanceUsers;
import com.jrsoft.fri.xtgl.from.ExcelColumns;

public class XtglMaintenanceUsersDaoImpl  extends BaseDaoImpl< XtglMaintenanceUsers, String> implements  XtglMaintenanceUsersDao{

	@Override
	public Class<? extends Object> getEntityClass() {
		// TODO Auto-generated method stub
		return  XtglMaintenanceUsers.class;
	}

	@Override
	public void export(String filePath, XtglMaintenanceUsers users) {
		
		try {
			System.out.println("========>>" + filePath);
			//Excel ex = new Excel();
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 在Excel工作簿中建一工作表，其名为缺省值
			HSSFSheet sheet = null;
			HSSFRow row = null;
			HSSFCell cell = null;
			List<XtglMaintenanceUsers> list=new ArrayList<XtglMaintenanceUsers>();
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
			String sql="select dr.*  from Xtgl_maintenance_users dr where  1=1 " ;
			if(users.getName()!=null&&!users.getName().equals("")){
				sql+=" and name = '"+users.getName()+"'";
			}
			if(users.getNumbers()!=null&&!users.getNumbers().equals("")){
				sql+=" and numbers = '"+users.getNumbers()+"'";
			}
			if(users.getCardNumber()!=null&&!users.getCardNumber().equals("")){
				sql+=" and card_Number = '"+users.getCardNumber()+"'";
			}
			if(users.getPhone()!=null&&!users.getPhone().equals("")){
				sql+=" and phone = '"+users.getPhone()+"'";
			}
			if(users.getUnitId()!=null&&!users.getUnitId().equals("")){
				sql+=" and unit_Id = '"+users.getUnitId().getId()+"'";
			}
			sql+=" order by id";	
			
			PreparedStatement sta = conn.prepareStatement(sql);
			ResultSet rs = sta.executeQuery();
			list=new ArrayList<XtglMaintenanceUsers>();
			while(rs.next()){
				XtglMaintenanceUsers elevators=new XtglMaintenanceUsers();
				elevators.setId(rs.getLong("id"));
				elevators.setName(rs.getString("name"));
				elevators.setNumbers(rs.getString("numbers"));
				elevators.setPhone(rs.getString("phone"));
				elevators.setValidity(rs.getString("validity"));
				list.add(elevators);
				
			}
			
			if (1 == 1) {
				top_arraydis =ExcelColumns.XtglMaintenanceUsers;

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
					    XtglMaintenanceUsers	e = (XtglMaintenanceUsers) list.get(i);
						
						cell = row.createCell(j);// 创建格 字段
						cell.setCellValue(e.getName());   //维保人
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getPhone());   //负责人电话
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getNumbers());   //维保证编号

						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getValidity());   //维保证有效期
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getCardNumber());   //维保卡号
						
						
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
