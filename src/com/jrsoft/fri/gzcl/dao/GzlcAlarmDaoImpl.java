package com.jrsoft.fri.gzcl.dao;

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
import com.jrsoft.fri.gzcl.entity.GzlcAlarm;
import com.jrsoft.fri.xtgl.from.ExcelColumns;

public class GzlcAlarmDaoImpl  extends BaseDaoImpl< GzlcAlarm, String> implements  GzlcAlarmDao{

	@Override
	public Class<? extends Object> getEntityClass() {
		// TODO Auto-generated method stub
		return  GzlcAlarm.class;
	}

	@Override
	public void export(String filePath, GzlcAlarm entity) {

		try {
			System.out.println("========>>" + filePath);
			//Excel ex = new Excel();
			HSSFWorkbook workbook = new HSSFWorkbook();
			// ��Excel�������н�һ����������Ϊȱʡֵ
			HSSFSheet sheet = null;
			HSSFRow row = null;
			HSSFCell cell = null;
			List<GzlcAlarm> list=new ArrayList<GzlcAlarm>();
			sheet = workbook.createSheet("�˹��Ӿ�");
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
			
			//��ѯ���񶩵�
			String sql="select de.*,e.registerid as registerid,e.distinguishid as distinguishid,e.install_place as place   " +
					" from gzlc_alarm de " +
					" left join dtjk_elevator e on e.id=de.elevator_id "+  //������Ϣ
					"where  1=1 " ;
			if(entity.getRegisterid()!=null&&!entity.getRegisterid().equals("")){
				sql+=" and e.registerid like '%"+entity.getRegisterid()+"%'";
			}
			if(entity.getPlace()!=null&&!entity.getPlace().equals("")){
				sql+=" and e.install_Place like '%"+entity.getPlace()+"%'";
			}
			if(entity.getTime()!=null&&!entity.getTime().equals("")){
				sql+=" and de.time  =to_date('" + df.format(entity.getTime())+ "','yyyy-MM-dd')";
			}
			sql+=" order by de.time desc";	
			
			PreparedStatement sta = conn.prepareStatement(sql);
			ResultSet rs = sta.executeQuery();
			list=new ArrayList<GzlcAlarm>();
			while(rs.next()){
				GzlcAlarm useUnit=new GzlcAlarm();
				useUnit.setId(rs.getLong("id"));
				useUnit.setFault(rs.getString("fault"));
				useUnit.setTime(rs.getString("time")==null?null:df.parse(rs.getString("time")));
				useUnit.setHappenTime(rs.getString("happen_time")==null?null:df.parse(rs.getString("happen_time")));
				useUnit.setAlarmPerson(rs.getString("alarm_Person"));
				useUnit.setPhone(rs.getString("phone"));
				useUnit.setDescribe(rs.getString("describe"));
				useUnit.setRegisterid(rs.getString("registerid"));
				useUnit.setDistinguishid(rs.getString("distinguishid"));
				useUnit.setPlace(rs.getString("place"));
				list.add(useUnit);
				
			}
			
			if (1 == 1) {
				top_arraydis = ExcelColumns.GzlcAlarm;

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
					    GzlcAlarm	e = (GzlcAlarm) list.get(i);
						
						cell = row.createCell(j);// ������ �ֶ�
						cell.setCellValue(e.getRegisterid());   //����ע���
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getDistinguishid());   //ʶ����
			
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getPlace());   //��װ�ص�

						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getHappenTime()==null?null:df.format(e.getHappenTime()));   //���Ϸ���ʱ��
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getTime()==null?null:df.format(e.getTime()));   //����ʱ��

						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getAlarmPerson());   //�Ӿ���
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getPhone());   //�Ӿ��˵绰
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getFault());   //��������
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getDescribe());   //��������
						
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
