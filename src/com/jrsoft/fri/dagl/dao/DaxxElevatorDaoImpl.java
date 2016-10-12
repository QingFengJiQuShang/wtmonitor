package com.jrsoft.fri.dagl.dao;

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
import org.hibernate.Query;

import smart.sys.platform.dao.DBEntity;

import com.jrsoft.fri.common.core.dao.BaseDaoImpl;
import com.jrsoft.fri.dagl.entity.DaxxElevator;
import com.jrsoft.fri.dagl.entity.DaxxRegion;
import com.jrsoft.fri.dagl.from.ExcelColumns;

public class DaxxElevatorDaoImpl extends BaseDaoImpl< DaxxElevator, String> implements  DaxxElevatorDao{

	@Override
	public Class<? extends Object> getEntityClass() {
		// TODO Auto-generated method stub
		return  DaxxElevator.class;
	}

	@Override
	public void export(String filePath, DaxxElevator elevator) {
		try {
			System.out.println("========>>" + filePath);
			//Excel ex = new Excel();
			HSSFWorkbook workbook = new HSSFWorkbook();
			// ��Excel�������н�һ����������Ϊȱʡֵ
			HSSFSheet sheet = null;
			HSSFRow row = null;
			HSSFCell cell = null;
			List<DaxxElevator> list=new ArrayList<DaxxElevator>();
			sheet = workbook.createSheet("������Ϣ");
			HSSFCellStyle style = workbook.createCellStyle(); // ��ʽ����
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// ��ֱ
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// ˮƽ
			/**
			 * ��������
			 */
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ	
			HSSFFont f = workbook.createFont();
			f.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// ����Ӵ�
			style.setFont(f);		
			String[] top_arraydis = null;
			Connection conn = DBEntity.getInstance().getConnection();
			//��ѯ���񶩵�
			String sql="select dr.*  from daxx_elevators dr where  1=1 " ;
					
			
			sql+=" order by id";	
			
			PreparedStatement sta = conn.prepareStatement(sql);
			ResultSet rs = sta.executeQuery();
			list=new ArrayList<DaxxElevator>();
			while(rs.next()){
				DaxxElevator elevators=new DaxxElevator();
				elevators.setId(rs.getLong("id"));
				elevators.setRegisterid(rs.getString("registerid"));
				elevators.setDistinguishid(rs.getString("distinguishid"));
				elevators.setBrand(rs.getString("brand"));
				elevators.setModel(rs.getString("model"));
				elevators.setState(rs.getString("state"));
				elevators.setType(rs.getString("type"));
				elevators.setNumbers(rs.getString("numbers"));
				elevators.setLengths(rs.getString("lengths"));
				elevators.setLabel(rs.getString("label"));
				elevators.setPlace(rs.getString("place"));
				elevators.setManufactureTime(rs.getDate("manufactureTime"));
				elevators.setYearlyState(rs.getString("yearlyState"));
				elevators.setGatewayId(rs.getLong("gateway_Id"));
				elevators.setUseUnitId(rs.getLong("use_Unit_Id"));
				elevators.setMaintenanceUnitId(rs.getLong("maintenance_Unit_Id"));
				elevators.setMaintenanceState(rs.getString("maintenanceState"));
				list.add(elevators);
				
			}
			
			if (1 == 1) {
				top_arraydis = ExcelColumns.region;

				row = sheet.createRow(0);// ����һ��

				for (int c = 0; c < top_arraydis.length; c++) {
					cell = row.createCell((short) c);// ������ �ֶ� 
					cell.setCellValue("����");
					cell.setCellStyle(style);
					cell.setCellValue(top_arraydis[c]);
				}
				int j = 0;
				for (int i = 0; i < list.size(); i++) {
					    row = sheet.createRow(i+1);
					    DaxxElevator	e = (DaxxElevator) list.get(i);
						
						cell = row.createCell(j);// ������ �ֶ�
						cell.setCellValue(e.getRegisterid());   //����ע���
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getDistinguishid());   //ʶ����

						cell = row.createCell(j);// ������ �ֶ�
						cell.setCellValue(e.getBrand());   //����Ʒ��
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getModel());   //�����ͺ�

						cell = row.createCell(j);// ������ �ֶ�
						cell.setCellValue(e.getState());   //ע��״̬
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getType());   //��������

						cell = row.createCell(j);// ������ �ֶ�
						cell.setCellValue(e.getNumbers());   //�ܲ���
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getLengths());   //����

						cell = row.createCell(j);// ������ �ֶ�
						cell.setCellValue(e.getLabel());   //��ͼ��ע
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getPlace());   //��װ�ص�

						cell = row.createCell(j);// ������ �ֶ�
						cell.setCellValue(e.getManufactureTime());   //��������
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getYearlyState());   //��������id

						cell = row.createCell(j);// ������ �ֶ�
						cell.setCellValue(e.getGatewayId());   //ʹ�õ�λid
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getUseUnitId());   //ά����λid

						cell = row.createCell(j);// ������ �ֶ�
						cell.setCellValue(e.getMaintenanceUnitId());   //���״̬
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getMaintenanceState());   //ά��״̬

						
						j = 0;
					}
			}

			// �½�һ����ļ���
			
			FileOutputStream fOut = new FileOutputStream(filePath);
//			System.out.println(fOut + "-----");
			// ����Ӧ��Excel ����������
			workbook.write(fOut);
			fOut.flush();
			// �����������ر��ļ�
			fOut.close();
			return;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("������ xlCreate() : " + e);
		}
		
		
	}


}
