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
import com.jrsoft.fri.dtjk.dao.DtjkGatewayDao;
import com.jrsoft.fri.dtjk.entity.DtjkGateway;
import com.jrsoft.fri.gzcl.entity.GzclFault;
import com.jrsoft.fri.xtgl.entity.XtglUseUnit;
import com.jrsoft.fri.xtgl.from.ExcelColumns;

public class GzclFaultDaoImpl  extends BaseDaoImpl< GzclFault, String> implements  GzclFaultDao{

	@Override
	public Class<? extends Object> getEntityClass() {
		// TODO Auto-generated method stub
		return  GzclFault.class;
	}

	@Override
	public void export(String filePath, GzclFault entity) {

		try {
			System.out.println("========>>" + filePath);
			//Excel ex = new Excel();
			HSSFWorkbook workbook = new HSSFWorkbook();
			// ��Excel�������н�һ����������Ϊȱʡֵ
			HSSFSheet sheet = null;
			HSSFRow row = null;
			HSSFCell cell = null;
			List<GzclFault> list=new ArrayList<GzclFault>();
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
			String sql="select de.*,e.registerid as registerid,e.distinguishid as distinguishid,e.install_place as place ,xu.name as username  " +
					" from gzcl_fault de " +
					" left join dtjk_elevator e on e.id=de.elevator_id "+  //������Ϣ
					" left join xtgl_users xu on xu.id=de.duty_id "+  //������Ϣ
					"where  1=1 " ;
					if(entity.getRegisterid()!=null&&!entity.getRegisterid().equals("")){
						sql+=" and e.registerid like '%"+entity.getRegisterid()+"%'";
					}
					if(entity.getPlace()!=null&&!entity.getPlace().equals("")){
						sql+=" and e.install_Place like '%"+entity.getPlace()+"%'";
					}
					if(entity.getBegintime()!=null&&!entity.getBegintime().equals("")){
						sql+=" and de.happen_Time  >=to_date('" + entity.getBegintime()+ "','yyyy-MM-dd hh24:mi:ss')";
					}
					if(entity.getEndtime()!=null&&!entity.getEndtime().equals("")){
						sql+=" and de.happen_Time  <=to_date('" +entity.getEndtime()+ "','yyyy-MM-dd hh24:mi:ss')";
					}
					sql+="  and de.state"+entity.getState()+" order by de.happen_Time desc";	
					
					PreparedStatement sta = conn.prepareStatement(sql);
					ResultSet rs = sta.executeQuery();
					list=new ArrayList<GzclFault>();
					while(rs.next()){
						GzclFault useUnit=new GzclFault();
						useUnit.setId(rs.getLong("id"));
						useUnit.setFault(rs.getString("fault"));
						useUnit.setHandle(rs.getString("handle"));
						useUnit.setHappenTime(rs.getString("happen_Time")==null?null:df.parse(rs.getString("happen_Time")));
						useUnit.setAlarmTime(rs.getString("alarm_Time")==null?null:df.parse(rs.getString("alarm_Time")));
						useUnit.setArriveTime(rs.getString("arrive_Time")==null?null:df.parse(rs.getString("arrive_Time")));
						useUnit.setSuccessTime(rs.getString("success_Time")==null?null:df.parse(rs.getString("success_Time")));
						useUnit.setRegisterid(rs.getString("registerid"));
						useUnit.setDistinguishid(rs.getString("distinguishid"));
						useUnit.setPlace(rs.getString("place"));
						useUnit.setDutyName(rs.getString("username"));
						useUnit.setNumbers(rs.getString("numbers"));
						useUnit.setState(rs.getString("state"));
						list.add(useUnit);
						
					}
			
			if (1 == 1) {
				top_arraydis = ExcelColumns.GzclFault;

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
					    GzclFault	e = (GzclFault) list.get(i);
						
						cell = row.createCell(j);// ������ �ֶ�
						cell.setCellValue(e.getRegisterid());   //����ע���
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getDistinguishid());   //ʶ����
			
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getPlace());   //��װ�ص�

						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getHappenTime()==null?null:df.format(e.getHappenTime()));   //���Ϸ���ʱ��
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getAlarmTime()==null?null:df.format(e.getAlarmTime()));   //����ʱ��

						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getType());   //�Ӿ�����
						
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getFault());   //��������
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getArriveTime()==null?null:df.format(e.getArriveTime()));   //��Ԯ����ʱ��
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getSuccessTime()==null?null:df.format(e.getSuccessTime()));   //��Ԯ�ɹ�ʱ��
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getNumbers());   //��������
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getUnitId());   //ʩ�ȵ�λ
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getHandle());   //������Ϣ
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getDutyName());   //ֵ����
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getState());   //״̬

						
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
