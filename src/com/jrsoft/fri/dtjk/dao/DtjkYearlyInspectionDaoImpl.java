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
import com.jrsoft.fri.dtjk.entity.DtjkYearlyInspection;
import com.jrsoft.fri.xtgl.entity.XtglUseUnit;
import com.jrsoft.fri.xtgl.from.ExcelColumns;

public class DtjkYearlyInspectionDaoImpl  extends BaseDaoImpl< DtjkYearlyInspection, String> implements  DtjkYearlyInspectionDao{

	@Override
	public Class<? extends Object> getEntityClass() {
		// TODO Auto-generated method stub
		return DtjkYearlyInspection.class;
	}

	@Override
	public void export(String filePath, DtjkYearlyInspection inspection ) {
		try {
			System.out.println("========>>" + filePath);
			//Excel ex = new Excel();
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 在Excel工作簿中建一工作表，其名为缺省值
			HSSFSheet sheet = null;
			HSSFRow row = null;
			HSSFCell cell = null;
			List<DtjkYearlyInspection> list=new ArrayList<DtjkYearlyInspection>();
			sheet = workbook.createSheet("年检记录信息");
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
			String sql="select de.*,e.install_place as place, e.registerid as registerid,e.distinguishid as distinguishid, xuu.name as userunitname  " +
					" from dtjk_yearly_inspection de " +
					" left join xtgl_use_unit xuu on xuu.id=de.use_unit_id "+  //使用单位
					" left join dtjk_elevator e on e.id=de.elevator_id "+  //电梯信息
					" where  1=1 " ;
			
			if(inspection.getElevatorId().getId()!=null&&!inspection.getElevatorId().getId().equals("")){
				sql+=" and de.elevator_Id = '"+inspection.getElevatorId().getId()+"'";
			}
			if(inspection.getTime()!=null&&!inspection.getTime().equals("")){
				sql+=" and de.time = to_date('" + df.format(inspection.getTime())+ "','yyyy-MM-dd')";
			}
			if(inspection.getUseUnitId().getId()!=null&&!inspection.getUseUnitId().getId().equals("")){
				sql+=" and de.use_unit_id  ='"+inspection.getUseUnitId().getId()+"'";
			}
			if(inspection.getInspectionUnit()!=null&&!inspection.getInspectionUnit().equals("")){
				sql+=" and de.inspection_unit  ='"+inspection.getInspectionUnit()+"'";
			}
			sql+=" order by id";	
			
			PreparedStatement sta = conn.prepareStatement(sql);
			ResultSet rs = sta.executeQuery();
			while(rs.next()){
				DtjkYearlyInspection useUnit=new DtjkYearlyInspection();
				useUnit.setId(rs.getLong("id"));
				useUnit.setRegisterid(rs.getString("registerid"));
				useUnit.setDistinguishid(rs.getString("distinguishid"));
				useUnit.setPlace(rs.getString("place"));
				useUnit.setUseUnitName(rs.getString("userunitname"));
				useUnit.setInspectionUnit(rs.getString("inspection_unit"));
				useUnit.setTime(df.parse(rs.getString("time")));
				useUnit.setResult(rs.getString("result"));
				useUnit.setRemarks(rs.getString("remarks"));
				list.add(useUnit);
				
			}
			
			if (1 == 1) {
				top_arraydis = ExcelColumns.DtjkYearlyInspection;

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
					    DtjkYearlyInspection	e = (DtjkYearlyInspection) list.get(i);
						
					    cell = row.createCell(j);// 创建格 字段
						cell.setCellValue(e.getRegisterid());   //电梯注册号
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getDistinguishid());   //识别码
			
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getUseUnitName());   //使用单位

						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getPlace());   //安装地点
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(df.format(e.getTime()));   //检验日期
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getInspectionUnit());   //检验单位
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getResult());   //检验结果
						
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
