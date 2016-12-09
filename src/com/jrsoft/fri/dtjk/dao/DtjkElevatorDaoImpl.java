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
			String sql="select de.*,xuu.name as useUnitName, xmu.name as  maintenanceUnitName,mu.name as usersName" +
			" from dtjk_elevator de " +
			" left join xtgl_use_unit xuu on xuu.id=de.use_unit_id "+  //维保单位
			" left join xtgl_maintenance_unit xmu on xmu.id=de.maintenance_unit_id"+  //维保单位
			" left join xtgl_maintenance_users mu on mu.id=de.maintenance_users_id"+  //维保单位
			" where  1=1   and de.delflag!='1' " ;
				
			if(elevator.getRegisterid()!=null&&!elevator.getRegisterid().equals("")){
				sql+=" and de.registerid like '%"+elevator.getRegisterid()+"%'";
			}		
			if(elevator.getDistinguishid()!=null&&!elevator.getDistinguishid().equals("")){
				sql+=" and de.distinguishid like '%"+elevator.getDistinguishid()+"%'";
			}
			if(elevator.getUseUnitName()!=null&&!elevator.getUseUnitName().equals("")){
				sql+=" and xuu.name like '%"+elevator.getUseUnitName()+"%'";
			}
			if(elevator.getBrand()!=null&&!elevator.getBrand().equals("")){
				sql+=" and de.brand like '%"+elevator.getBrand()+"%'";
			}
			if(elevator.getNumbers()!=null&&!elevator.getNumbers().equals("")){
				sql+=" and de.numbers = '"+elevator.getNumbers()+"'";
			}			
			sql+=" order by id";	
			
			PreparedStatement sta = conn.prepareStatement(sql);
			ResultSet rs = sta.executeQuery();
			list=new ArrayList<DtjkElevator>();
			while(rs.next()){
				DtjkElevator elevators=new DtjkElevator();
				elevators.setId(rs.getLong("id"));
				elevators.setRegisterid(rs.getString("registerid"));   		//注册号
				elevators.setDistinguishid(rs.getString("distinguishid"));   		//识别码
				elevators.setBrand(rs.getString("brand"));   		//电梯品牌
				elevators.setModel(rs.getString("model"));   		//电梯型号
				elevators.setState(rs.getString("state"));   		//运行状态
				elevators.setType(rs.getString("type"));   		//电梯类型
				elevators.setNumbers(rs.getString("numbers"));   		//总层数
				elevators.setLabel(rs.getString("label"));   		//
				elevators.setPlace(rs.getString("place"));   		//安装地点
				elevators.setManufactureTime(df.parse(rs.getString("manufacture_Time")));   		//生产日期
				elevators.setYearlyState(rs.getString("yearly_State"));   		//年检状态
				elevators.setMaintenanceState(rs.getString("maintenance_State"));   		//维保状态				
				elevators.setRegisterState(rs.getString("registerstate"));   		//注册状态
				elevators.setSpeed(rs.getString("speed"));   		//电梯速度
				elevators.setInstallPlace(rs.getString("install_Place"));   		//安装地点
				elevators.setInstallUnit(rs.getString("install_Unit"));   		//安装单位
				elevators.setInstallUser(rs.getString("install_User"));   		//安装人
				elevators.setInstallTime(df.parse(rs.getString("install_Time")));   		//安装时间
				elevators.setManufacturer(rs.getString("manufacturer"));   		//制造商
				elevators.setManufacturerPhone(rs.getString("manufacturer_Phone"));   		//制作商电话
				elevators.setManufacturerAddress(rs.getString("manufacturer_Address"));   		//制造商地址
				elevators.setManufacturerUrl(rs.getString("manufacturer_Url"));   		//制造商网址
				elevators.setFilialeAddress(rs.getString("filiale_Address"));   		//制作商本地分公司地址
				elevators.setFilialePhone(rs.getString("filiale_Phone"));   		//制造商本地分公司电话			
				elevators.setFilialeContact(rs.getString("filiale_Contact"));   		//制造商本地分公司联系人
				elevators.setServiceIfe(rs.getString("service_ife"));   		//电梯使用年限
				elevators.setRemarks(rs.getString("remarks"));   		//备注
				elevators.setUseUnitName(rs.getString("useUnitName"));   		//维保状态
				elevators.setMaintenanceUnitName(rs.getString("maintenanceUnitName"));   		//维保状态
				elevators.setMaintenanceUsersName(rs.getString("usersName"));   		//维保状态
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
						cell.setCellValue(e.getRegisterid());   //注册号
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getDistinguishid());   //识别码

						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getBrand());   //电梯品牌
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getModel());   //电梯型号

						
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getType());   //电梯类型
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getNumbers());   //电梯速度
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getNumbers());   //总层数
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getRegisterState());   //注册状态
						
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getUseUnitName());   //使用单位

						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getMaintenanceUnitName());   //维保单位
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getMaintenanceUsersName());   //维保人

						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getInstallUnit());   //安装单位
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getInstallUser());   //安装人员

						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(df.format(e.getInstallTime()));   //安装时间
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getMaintenanceState());   //安装地点

						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getInstallPlace());   //生产日期
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getServiceIfe());   //使用年限
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getManufacturer());   //制造商
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getManufacturerAddress());   //制造商地址
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getManufacturerPhone());   //制作商电话
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getManufacturerUrl());   //制造商网站
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getFilialeAddress());   //本地分公司地址
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getFilialeContact());   //本地分公司联系人
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getFilialePhone());   //本地分公司电话
						
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
