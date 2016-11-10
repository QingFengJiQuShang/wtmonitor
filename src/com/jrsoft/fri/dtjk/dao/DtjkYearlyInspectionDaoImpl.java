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
			// ��Excel�������н�һ����������Ϊȱʡֵ
			HSSFSheet sheet = null;
			HSSFRow row = null;
			HSSFCell cell = null;
			List<DtjkYearlyInspection> list=new ArrayList<DtjkYearlyInspection>();
			sheet = workbook.createSheet("����¼��Ϣ");
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
			String sql="select de.*,e.install_place as place, e.registerid as registerid,e.distinguishid as distinguishid, xuu.name as userunitname  " +
					" from dtjk_yearly_inspection de " +
					" left join xtgl_use_unit xuu on xuu.id=de.use_unit_id "+  //ʹ�õ�λ
					" left join dtjk_elevator e on e.id=de.elevator_id "+  //������Ϣ
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
					    DtjkYearlyInspection	e = (DtjkYearlyInspection) list.get(i);
						
					    cell = row.createCell(j);// ������ �ֶ�
						cell.setCellValue(e.getRegisterid());   //����ע���
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getDistinguishid());   //ʶ����
			
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getUseUnitName());   //ʹ�õ�λ

						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getPlace());   //��װ�ص�
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(df.format(e.getTime()));   //��������
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getInspectionUnit());   //���鵥λ
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getResult());   //������
						
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
