package com.jrsoft.fri.gzcl.dao;

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
import com.jrsoft.fri.gzcl.entity.GzlcAlarm;
import com.jrsoft.fri.xtgl.from.ExcelColumns;

public class GzlcAlarmDaoImpl  extends BaseDaoImpl< GzlcAlarm, String> implements  GzlcAlarmDao{

	@Override
	public Class<? extends Object> getEntityClass() {
		// TODO Auto-generated method stub
		return  GzlcAlarm.class;
	}

	@Override
	public void export(String filePath, GzlcAlarm entity) {

		try {
			System.out.println("========>>" + filePath);
			//Excel ex = new Excel();
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 在Excel工作簿中建一工作表，其名为缺省值
			HSSFSheet sheet = null;
			HSSFRow row = null;
			HSSFCell cell = null;
			List<GzlcAlarm> list=new ArrayList<GzlcAlarm>();
			sheet = workbook.createSheet("人工接警");
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
			
			//查询服务订单
			String sql="select de.*,e.registerid as registerid,e.distinguishid as distinguishid,e.install_place as place   " +
					" from gzlc_alarm de " +
					" left join dtjk_elevator e on e.id=de.elevator_id "+  //电梯信息
					"where  1=1 " ;
			if(entity.getRegisterid()!=null&&!entity.getRegisterid().equals("")){
				sql+=" and e.registerid like '%"+entity.getRegisterid()+"%'";
			}
			if(entity.getPlace()!=null&&!entity.getPlace().equals("")){
				sql+=" and e.install_Place like '%"+entity.getPlace()+"%'";
			}
			if(entity.getTime()!=null&&!entity.getTime().equals("")){
				sql+=" and de.time  =to_date('" + df.format(entity.getTime())+ "','yyyy-MM-dd')";
			}
			sql+=" order by de.time desc";	
			
			PreparedStatement sta = conn.prepareStatement(sql);
			ResultSet rs = sta.executeQuery();
			list=new ArrayList<GzlcAlarm>();
			while(rs.next()){
				GzlcAlarm useUnit=new GzlcAlarm();
				useUnit.setId(rs.getLong("id"));
				useUnit.setFault(rs.getString("fault"));
				useUnit.setTime(rs.getString("time")==null?null:df.parse(rs.getString("time")));
				useUnit.setHappenTime(rs.getString("happen_time")==null?null:df.parse(rs.getString("happen_time")));
				useUnit.setAlarmPerson(rs.getString("alarm_Person"));
				useUnit.setPhone(rs.getString("phone"));
				useUnit.setDescribe(rs.getString("describe"));
				useUnit.setRegisterid(rs.getString("registerid"));
				useUnit.setDistinguishid(rs.getString("distinguishid"));
				useUnit.setPlace(rs.getString("place"));
				list.add(useUnit);
				
			}
			
			if (1 == 1) {
				top_arraydis = ExcelColumns.GzlcAlarm;

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
					    GzlcAlarm	e = (GzlcAlarm) list.get(i);
						
						cell = row.createCell(j);// 创建格 字段
						cell.setCellValue(e.getRegisterid());   //电梯注册号
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getDistinguishid());   //识别码
			
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getPlace());   //安装地点

						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getHappenTime()==null?null:df.format(e.getHappenTime()));   //故障发生时间
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getTime()==null?null:df.format(e.getTime()));   //报警时间

						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getAlarmPerson());   //接警人
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getPhone());   //接警人电话
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getFault());   //故障问题
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getDescribe());   //故障描述
						
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
