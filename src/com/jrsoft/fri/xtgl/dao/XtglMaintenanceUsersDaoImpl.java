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
			// ��Excel�������н�һ����������Ϊȱʡֵ
			HSSFSheet sheet = null;
			HSSFRow row = null;
			HSSFCell cell = null;
			List<XtglMaintenanceUsers> list=new ArrayList<XtglMaintenanceUsers>();
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
					    XtglMaintenanceUsers	e = (XtglMaintenanceUsers) list.get(i);
						
						cell = row.createCell(j);// ������ �ֶ�
						cell.setCellValue(e.getName());   //ά����
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getPhone());   //�����˵绰
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getNumbers());   //ά��֤���

						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getValidity());   //ά��֤��Ч��
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getCardNumber());   //ά������
						
						
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
