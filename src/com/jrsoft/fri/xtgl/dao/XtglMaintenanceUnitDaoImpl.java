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
			// ��Excel�������н�һ����������Ϊȱʡֵ
			HSSFSheet sheet = null;
			HSSFRow row = null;
			HSSFCell cell = null;
			List<XtglMaintenanceUnit> list=new ArrayList<XtglMaintenanceUnit>();
			sheet = workbook.createSheet("ά����λ��Ϣ");
			HSSFCellStyle style = workbook.createCellStyle(); // ��ʽ����
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// ��ֱ
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// ˮƽ
			/**
			 * ��������
			 */
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//�������ڸ�ʽ	
			HSSFFont f = workbook.createFont();
			f.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// ����Ӵ�
			style.setFont(f);		
			String[] top_arraydis = null;
			Connection conn = DBEntity.getInstance().getConnection();
			//��ѯ���񶩵�
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
				list.add(elevators);
				
			}
			
			if (1 == 1) {
				top_arraydis = ExcelColumns.MaintenanceUnit;

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
					    XtglMaintenanceUnit	e = (XtglMaintenanceUnit) list.get(i);
						
						cell = row.createCell(j);// ������ �ֶ�
						cell.setCellValue(e.getName());   //��λ����
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getLiaisons());   //������

						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getPhone());   //�����˵绰
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getAddress());   //�칫�ص�

						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getCode());   //��˾����
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getCorporation());   //����

						
						
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
