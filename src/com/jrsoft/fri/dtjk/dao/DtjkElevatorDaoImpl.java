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
import com.jrsoft.fri.xtgl.from.ExcelColumns;

public class DtjkElevatorDaoImpl extends BaseDaoImpl< DtjkElevator, String> implements  DtjkElevatorDao{

	@Override
	public Class<? extends Object> getEntityClass() {
		// TODO Auto-generated method stub
		return  DtjkElevator.class;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void export(String filePath, DtjkElevator elevator) {
		try {
			System.out.println("========>>" + filePath);
			//Excel ex = new Excel();
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 在Excel工作簿中建一工作表，其名为缺省值
			HSSFSheet sheet = null;
			HSSFRow row = null;
			HSSFCell cell = null;
			List<DtjkElevator> list=new ArrayList<DtjkElevator>();
			sheet = workbook.createSheet("电梯信息");
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
			String sql="select dr.*  from Dtjk_elevator dr where  1=1 " ;
			if(elevator.getRegisterid()!=null&&!elevator.getRegisterid().equals("")){
				sql+=" and registerid like '%"+elevator.getRegisterid()+"%'";
			}		
			if(elevator.getDistinguishid()!=null&&!elevator.getDistinguishid().equals("")){
				sql+=" and distinguishid like '%"+elevator.getDistinguishid()+"%'";
			}
			if(elevator.getLabel()!=null&&!elevator.getLabel().equals("")){
				sql+=" and label like '%"+elevator.getLabel()+"%'";
			}
			if(elevator.getBrand()!=null&&!elevator.getBrand().equals("")){
				sql+=" and brand like '%"+elevator.getBrand()+"%'";
			}
			if(elevator.getType()!=null&&!elevator.getType().equals("")){
				sql+=" and type like '%"+elevator.getType()+"%'";
			}
			if(elevator.getModel()!=null&&!elevator.getModel().equals("")){
				sql+=" and model like '%"+elevator.getModel()+"%'";
			}
			if(elevator.getNumbers()!=null&&!elevator.getNumbers().equals("")){
				sql+=" and numbers like '%"+elevator.getNumbers()+"%'";
			}
			
			sql+=" order by id";	
			
			PreparedStatement sta = conn.prepareStatement(sql);
			ResultSet rs = sta.executeQuery();
			list=new ArrayList<DtjkElevator>();
			while(rs.next()){
				DtjkElevator elevators=new DtjkElevator();
				elevators.setId(rs.getLong("id"));
				elevators.setRegisterid(rs.getString("registerid"));
				elevators.setDistinguishid(rs.getString("distinguishid"));
				elevators.setBrand(rs.getString("brand"));
				elevators.setModel(rs.getString("model"));
				elevators.setState(rs.getString("state"));
				elevators.setType(rs.getString("type"));
				elevators.setNumbers(rs.getString("numbers"));
				elevators.setLabel(rs.getString("label"));
				elevators.setPlace(rs.getString("place"));
				elevators.setManufactureTime(df.parse(rs.getString("manufacture_Time")));
				elevators.setYearlyState(rs.getString("yearly_State"));
				elevators.setGatewayId(rs.getLong("gateway_Id"));
				elevators.setUseUnitId(rs.getLong("use_Unit_Id"));
				elevators.setMaintenanceUnitId(rs.getLong("maintenance_Unit_Id"));
				elevators.setMaintenanceState(rs.getString("maintenance_State"));
				list.add(elevators);
				
			}
			
			if (1 == 1) {
				top_arraydis = ExcelColumns.elevator;

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
					    DtjkElevator	e = (DtjkElevator) list.get(i);
						
						cell = row.createCell(j);// 创建格 字段
						cell.setCellValue(e.getRegisterid());   //电梯注册号
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getDistinguishid());   //识别码

						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getBrand());   //电梯品牌
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getModel());   //电梯型号

						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getState());   //注册状态
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getType());   //电梯类型

						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getNumbers());   //总层数
						

						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getLabel());   //地图标注
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getPlace());   //安装地点

						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(df.format(e.getManufactureTime()));   //生产日期
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getGatewayId());   //电梯网关id

						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getUseUnitId());   //使用单位id
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getMaintenanceUnitId());   //维保单位id

						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getYearlyState());   //年检状态
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getMaintenanceState());   //维保状态

						
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
