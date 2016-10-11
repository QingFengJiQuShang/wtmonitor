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
import smart.sys.platform.dao.DBEntity;
import com.jrsoft.fri.common.core.dao.BaseDaoImpl;
import com.jrsoft.fri.dagl.entity.DaxxRegion;
import com.jrsoft.fri.dagl.from.ExcelColumns;

public class DaxxRegionDaoImpl  extends BaseDaoImpl< DaxxRegion, String> implements  DaxxRegionDao{

	@Override
	public Class<? extends Object> getEntityClass() {
		// TODO Auto-generated method stub
		return  DaxxRegion.class;
	}

	@Override
	public void export(String filePath, DaxxRegion region) {
		try {
			System.out.println("========>>" + filePath);
			//Excel ex = new Excel();
			HSSFWorkbook workbook = new HSSFWorkbook();
			// ��Excel�������н�һ����������Ϊȱʡֵ
			HSSFSheet sheet = null;
			HSSFRow row = null;
			HSSFCell cell = null;
			List<DaxxRegion> list=new ArrayList<DaxxRegion>();
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
			//��ѯ���񶩵�
			String sql="select dr.*  from daxx_region dr where  1=1 " ;
					
			if(region.getRegion()!=null&&!region.getRegion().equals("")){
				sql+=" and region like '%"+region.getRegion()+"%'";
			}
			if(region.getClientId()!=null&&!region.getClientId().equals("")){
				sql+=" and client_id like '%"+region.getClientId()+"%'";
			}
			sql+=" order by id";	
			
			PreparedStatement sta = conn.prepareStatement(sql);
			ResultSet rs = sta.executeQuery();
			list=new ArrayList<DaxxRegion>();
			while(rs.next()){
				DaxxRegion regios=new DaxxRegion();
				regios.setId(rs.getLong("id"));
				regios.setRegion(rs.getString("region"));
				regios.setClientId(rs.getLong("client_id"));
				list.add(regios);
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
					    DaxxRegion	e = (DaxxRegion) list.get(i);
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getRegion());   //��������
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getClientId());   //�ͻ�����
						
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
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("������ xlCreate() : " + e);
		}
		
		
	}


}
