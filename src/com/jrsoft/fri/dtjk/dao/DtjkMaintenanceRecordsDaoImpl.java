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
import com.jrsoft.fri.dtjk.entity.DtjkMaintenanceRecords;
import com.jrsoft.fri.xtgl.entity.XtglUseUnit;
import com.jrsoft.fri.xtgl.from.ExcelColumns;

public class DtjkMaintenanceRecordsDaoImpl extends BaseDaoImpl< DtjkMaintenanceRecords, String> implements  DtjkMaintenanceRecordsDao{

	@Override
	public Class<? extends Object> getEntityClass() {
		// TODO Auto-generated method stub
		return DtjkMaintenanceRecords.class;
	}

	@Override
	public void export(String filePath, DtjkMaintenanceRecords records) {
		try {
			System.out.println("========>>" + filePath);
			//Excel ex = new Excel();
			HSSFWorkbook workbook = new HSSFWorkbook();
			// ��Excel�������н�һ����������Ϊȱʡֵ
			HSSFSheet sheet = null;
			HSSFRow row = null;
			HSSFCell cell = null;
			List<DtjkMaintenanceRecords> list=new ArrayList<DtjkMaintenanceRecords>();
			sheet = workbook.createSheet("ά����¼��Ϣ");
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
			//��ѯ���񶩵�
			String sql="select de.*,xuu.name as userunitname,xmu.name unitname,mu.name as username,e.registerid as registerid,e.distinguishid as distinguishid,e.install_place as place  " +
					" from dtjk_maintenance_records de " +
					" left join xtgl_use_unit xuu on xuu.id=de.use_unit_id "+  //ʹ�õ�λ
					" left join xtgl_maintenance_unit xmu on xmu.id=de.unit_id"+  //ά����λ
					" left join xtgl_maintenance_users mu on mu.id=de.user_id"+  //ά����Ա
					" left join dtjk_elevator e on e.id=de.elevator_id "+  //������Ϣ
					"where  1=1 " ;
			if(records.getElevatorId().getId()!=null&&!records.getElevatorId().getId().equals("")){
				sql+=" and de.elevator_Id = '"+records.getElevatorId().getId()+"'";
			}
			if(records.getTime()!=null&&!records.getTime().equals("")){
				sql+=" and de.time = to_date('" + df.format(records.getTime()) + "','yyyy-MM-dd')";
			}
			if(records.getUseUnitId().getId()!=null&&!records.getUseUnitId().getId().equals("")){
				sql+=" and de.use_unit_id  ='"+records.getUseUnitId().getId()+"'";
			}
			if(records.getUnitId().getId()!=null&&!records.getUnitId().getId().equals("")){
				sql+=" and de.unit_id  ='"+records.getUnitId().getId()+"'";
			}
			sql+=" order by time desc";	
			
			PreparedStatement sta = conn.prepareStatement(sql);
			ResultSet rs = sta.executeQuery();
			while(rs.next()){
				DtjkMaintenanceRecords useUnit=new DtjkMaintenanceRecords();
				useUnit.setId(rs.getLong("id"));
				useUnit.setUseUnitName(rs.getString("userunitname"));
				useUnit.setUserName(rs.getString("username"));
				useUnit.setUnitName(rs.getString("unitname"));
				useUnit.setRegisterid(rs.getString("registerid"));
				useUnit.setDistinguishid(rs.getString("distinguishid"));
				useUnit.setPlace(rs.getString("place"));
				useUnit.setTime(df.parse(rs.getString("time")));
				useUnit.setContent(rs.getString("content"));
				list.add(useUnit);
				
			}
			
			if (1 == 1) {
				top_arraydis = ExcelColumns.DtjkMaintenanceRecords;

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
					    DtjkMaintenanceRecords	e = (DtjkMaintenanceRecords) list.get(i);
						
						cell = row.createCell(j);// ������ �ֶ�
						cell.setCellValue(e.getRegisterid());   //����ע���
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getDistinguishid());   //ʶ����
			
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getUseUnitName());   //ʹ�õ�λ

						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getPlace());   //��װ�ص�
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(df.format(e.getTime()));   //ά������
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getPlace());   //��װ�ص�
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getUnitName());   //ά����λ
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getUserName());   //ά����
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getContent());   //ά������

						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getRemarks());   //��ע
						
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
