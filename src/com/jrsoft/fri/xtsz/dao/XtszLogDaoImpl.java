package com.jrsoft.fri.xtsz.dao;

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
import com.jrsoft.fri.gzcl.entity.GzclFault;
import com.jrsoft.fri.xtgl.from.ExcelColumns;
import com.jrsoft.fri.xtsz.entity.XtszLog;

public class XtszLogDaoImpl  extends BaseDaoImpl< XtszLog, String> implements  XtszLogDao{

	@Override
	public Class<? extends Object> getEntityClass() {
		return XtszLog.class;
	}

	@Override
	public void export(String filePath, XtszLog entity) {

		try {
			System.out.println("========>>" + filePath);
			//Excel ex = new Excel();
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 在Excel工作簿中建一工作表，其名为缺省值
			HSSFSheet sheet = null;
			HSSFRow row = null;
			HSSFCell cell = null;
			List<XtszLog> list=new ArrayList<XtszLog>();
			sheet = workbook.createSheet("当前故障");
			HSSFCellStyle style = workbook.createCellStyle(); // 样式对象
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平
			/**
			 * 设置字体
			 */
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			HSSFFont f = workbook.createFont();
			f.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 字体加粗
			style.setFont(f);		
			String[] top_arraydis = null;
			Connection conn = DBEntity.getInstance().getConnection();
			
			//查询
					String sql="select de.* from xtsz_log de  where  1=1 " ;
					if(entity.getUserName()!=null&&!entity.getUserName().equals("")){
						sql+=" and de.user_Name like '%"+entity.getUserName()+"%'";
					}
					if(entity.getBegintime()!=null&&!entity.getBegintime().equals("")){
						sql+=" and de.found_Time  >=to_date('" + entity.getBegintime()+ "','yyyy-MM-dd hh24:mi:ss')";
					}
					if(entity.getEndtime()!=null&&!entity.getEndtime().equals("")){
						sql+=" and de.found_Time  <=to_date('" + entity.getEndtime()+ "','yyyy-MM-dd hh24:mi:ss')";
					}
					sql+=" order by de.found_Time desc";		
					
					PreparedStatement sta = conn.prepareStatement(sql);
					ResultSet rs = sta.executeQuery();
					list=new ArrayList<XtszLog>();
					while(rs.next()){
						XtszLog elevator=new XtszLog();
						elevator.setId(rs.getLong("id"));
						elevator.setFoundTime(rs.getString("found_Time")==null?null:df.parse(rs.getString("found_Time")));
						elevator.setUserName(rs.getString("user_Name"));
						elevator.setContent(rs.getString("content"));
						list.add(elevator);
						
					}
			
			if (1 == 1) {
				top_arraydis = ExcelColumns.XtszLog;

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
					    XtszLog	e = (XtszLog) list.get(i);
						
						cell = row.createCell(j);// 创建格 字段
						cell.setCellValue(e.getFoundTime()==null?null:df.format(e.getFoundTime()));   //操作时间
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getUserName());   //操作人
						
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getContent());   //操作日志
						
						
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
