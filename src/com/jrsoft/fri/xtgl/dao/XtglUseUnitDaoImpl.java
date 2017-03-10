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
import com.jrsoft.fri.xtgl.entity.XtglUseUnit;
import com.jrsoft.fri.xtgl.from.ExcelColumns;

public class XtglUseUnitDaoImpl  extends BaseDaoImpl< XtglUseUnit, String> implements  XtglUseUnitDao{

	@Override
	public Class<? extends Object> getEntityClass() {
		// TODO Auto-generated method stub
		return  XtglUseUnit.class;
	}

	@Override
	public void export(String filePath, XtglUseUnit unit) {
		try {
			System.out.println("========>>" + filePath);
			//Excel ex = new Excel();
			HSSFWorkbook workbook = new HSSFWorkbook();
			// ��Excel�������н�һ����������Ϊȱʡֵ
			HSSFSheet sheet = null;
			HSSFRow row = null;
			HSSFCell cell = null;
			List<XtglUseUnit> list=new ArrayList<XtglUseUnit>();
			sheet = workbook.createSheet("ʹ�õ�λ��Ϣ");
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
			String sql="select dr.*  from Xtgl_Use_Unit dr where  1=1 " ;
			
			if(unit.getProvince()!=null&&!unit.getProvince().equals("")){
				sql+=" and province  ='" +unit.getProvince()+ "'";
			}
			if(unit.getCity()!=null&&!unit.getCity().equals("")){
				sql+=" and city ='" + unit.getCity()+ "'";
			}
			if(unit.getArea()!=null&&!unit.getArea().equals("")){
				sql+=" and area  ='" + unit.getArea()+ "'";
			}
			if(unit.getName()!=null&&!unit.getName().equals("")){
				sql+=" and name = '"+unit.getName()+"'";
			}
			if(unit.getLiaisons()!=null&&!unit.getLiaisons().equals("")){
				sql+=" and liaisons  '"+unit.getLiaisons()+"'";
			}
			if(unit.getPhone()!=null&&!unit.getPhone().equals("")){
				sql+=" and phone = '"+unit.getPhone()+"'";
			}
			if(unit.getAddress()!=null&&!unit.getAddress().equals("")){
				sql+=" and address like '%"+unit.getAddress()+"%'";
			}
			sql+=" order by id";	
			
			PreparedStatement sta = conn.prepareStatement(sql);
			ResultSet rs = sta.executeQuery();
			list=new ArrayList<XtglUseUnit>();
			while(rs.next()){
				XtglUseUnit elevators=new XtglUseUnit();
				elevators.setId(rs.getLong("id"));
				elevators.setName(rs.getString("name"));
				elevators.setType(rs.getString("type"));
				elevators.setLiaisons(rs.getString("liaisons"));
				elevators.setPhone(rs.getString("phone"));
				elevators.setAddress(rs.getString("address"));
				elevators.setProvince(rs.getString("province"));
				elevators.setCity(rs.getString("city"));
				elevators.setArea(rs.getString("area"));
				list.add(elevators);
				
			}
			
			if (1 == 1) {
				top_arraydis = ExcelColumns.XtglUseUnit;

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
					    XtglUseUnit	e = (XtglUseUnit) list.get(i);
						
						cell = row.createCell(j);// ������ �ֶ�
						cell.setCellValue(e.getName());   //��λ����
						
					//	cell = row.createCell(++j);// ������ �ֶ�
					//	cell.setCellValue(e.getType());   //��λ����
			
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getLiaisons());   //������

						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getPhone());   //�����˵绰
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getAddress());   //��ַ

						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getProvince());   //ʡ
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getCity());   //��
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getArea());   //��
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

	@Override
	public void exportModel(String filePath) {
		// TODO Auto-generated method stub
		
	}


}
