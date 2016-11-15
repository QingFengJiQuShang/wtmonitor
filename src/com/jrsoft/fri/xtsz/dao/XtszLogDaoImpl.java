package com.jrsoft.fri.xtsz.dao;

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
import com.jrsoft.fri.gzcl.entity.GzclFault;
import com.jrsoft.fri.xtgl.from.ExcelColumns;
import com.jrsoft.fri.xtsz.entity.XtszLog;

public class XtszLogDaoImpl  extends BaseDaoImpl< XtszLog, String> implements  XtszLogDao{

	@Override
	public Class<? extends Object> getEntityClass() {
		return XtszLog.class;
	}

	@Override
	public void export(String filePath, XtszLog entity) {

		try {
			System.out.println("========>>" + filePath);
			//Excel ex = new Excel();
			HSSFWorkbook workbook = new HSSFWorkbook();
			// ��Excel�������н�һ����������Ϊȱʡֵ
			HSSFSheet sheet = null;
			HSSFRow row = null;
			HSSFCell cell = null;
			List<XtszLog> list=new ArrayList<XtszLog>();
			sheet = workbook.createSheet("��ǰ����");
			HSSFCellStyle style = workbook.createCellStyle(); // ��ʽ����
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// ��ֱ
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// ˮƽ
			/**
			 * ��������
			 */
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			HSSFFont f = workbook.createFont();
			f.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// ����Ӵ�
			style.setFont(f);		
			String[] top_arraydis = null;
			Connection conn = DBEntity.getInstance().getConnection();
			
			//��ѯ
					String sql="select de.* from xtsz_log de  where  1=1 " ;
					if(entity.getUserName()!=null&&!entity.getUserName().equals("")){
						sql+=" and de.user_Name like '%"+entity.getUserName()+"%'";
					}
					if(entity.getBegintime()!=null&&!entity.getBegintime().equals("")){
						sql+=" and de.found_Time  >=to_date('" + entity.getBegintime()+ "','yyyy-MM-dd hh24:mi:ss')";
					}
					if(entity.getEndtime()!=null&&!entity.getEndtime().equals("")){
						sql+=" and de.found_Time  <=to_date('" + entity.getEndtime()+ "','yyyy-MM-dd hh24:mi:ss')";
					}
					sql+=" order by de.found_Time desc";		
					
					PreparedStatement sta = conn.prepareStatement(sql);
					ResultSet rs = sta.executeQuery();
					list=new ArrayList<XtszLog>();
					while(rs.next()){
						XtszLog elevator=new XtszLog();
						elevator.setId(rs.getLong("id"));
						elevator.setFoundTime(rs.getString("found_Time")==null?null:df.parse(rs.getString("found_Time")));
						elevator.setUserName(rs.getString("user_Name"));
						elevator.setContent(rs.getString("content"));
						list.add(elevator);
						
					}
			
			if (1 == 1) {
				top_arraydis = ExcelColumns.XtszLog;

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
					    XtszLog	e = (XtszLog) list.get(i);
						
						cell = row.createCell(j);// ������ �ֶ�
						cell.setCellValue(e.getFoundTime()==null?null:df.format(e.getFoundTime()));   //����ʱ��
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getUserName());   //������
						
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getContent());   //������־
						
						
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
