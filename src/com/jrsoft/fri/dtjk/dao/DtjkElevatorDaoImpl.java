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
import com.jrsoft.fri.xtgl.from.ExcelColumns;

public class DtjkElevatorDaoImpl extends BaseDaoImpl< DtjkElevator, String> implements  DtjkElevatorDao{

	@Override
	public Class<? extends Object> getEntityClass() {
		// TODO Auto-generated method stub
		return  DtjkElevator.class;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void export(String filePath, DtjkElevator elevator) {
		try {
			System.out.println("========>>" + filePath);
			//Excel ex = new Excel();
			HSSFWorkbook workbook = new HSSFWorkbook();
			// ��Excel�������н�һ����������Ϊȱʡֵ
			HSSFSheet sheet = null;
			HSSFRow row = null;
			HSSFCell cell = null;
			List<DtjkElevator> list=new ArrayList<DtjkElevator>();
			sheet = workbook.createSheet("������Ϣ");
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
			String sql="select de.*,xuu.name as useUnitName, " +
					" xmu.name as  maintenanceUnitName,mu.name as maintenanceUsersName," +
					" xpu.name as propertyUnitName,make.name as makeUnitName" +
					" from dtjk_elevator de " +
					" left join xtgl_use_unit xuu on xuu.id=de.use_unit_id "+  //ʹ�õ�λ
					" left join xtgl_maintenance_unit xmu on xmu.id=de.maintenance_unit_id"+  //ά����λ
					" left join xtgl_maintenance_users mu on mu.id=de.maintenance_users_id"+  //ά����
					" left join Xtgl_Property_Unit xpu on xpu.id=de.property_Unit_Id"+  //��ҵ��λ
					" left join Xtgl_Make_Unit make on make.id=de.make_Unit_Id"+  //���쵥λ
					" where  1=1  and de.delflag!='1' " ;
				
			if(elevator.getRegisterid()!=null&&!elevator.getRegisterid().equals("")){
				sql+=" and de.registerid like '%"+elevator.getRegisterid()+"%'";
			}		
			if(elevator.getDistinguishid()!=null&&!elevator.getDistinguishid().equals("")){
				sql+=" and de.distinguishid like '%"+elevator.getDistinguishid()+"%'";
			}
			if(elevator.getUseUnitName()!=null&&!elevator.getUseUnitName().equals("")){
				sql+=" and xuu.name like '%"+elevator.getUseUnitName()+"%'";
			}
			if(elevator.getBrand()!=null&&!elevator.getBrand().equals("")){
				sql+=" and de.brand like '%"+elevator.getBrand()+"%'";
			}
			if(elevator.getNumbers()!=null&&!elevator.getNumbers().equals("")){
				sql+=" and de.numbers = '"+elevator.getNumbers()+"'";
			}			
			sql+=" order by de.id";	
			
			PreparedStatement sta = conn.prepareStatement(sql);
			ResultSet rs = sta.executeQuery();
			list=new ArrayList<DtjkElevator>();
			while(rs.next()){
				DtjkElevator elevators=new DtjkElevator();
				elevators.setId(rs.getLong("id"));
				elevators.setRegisterid(rs.getString("registerid"));   		//ע���
				elevators.setDistinguishid(rs.getString("distinguishid"));   		//ʶ����
				elevators.setBrand(rs.getString("brand"));   		//����Ʒ��
				elevators.setModel(rs.getString("model"));   		//�����ͺ�
				elevators.setType(rs.getString("type"));   		//��������
				elevators.setSpeed(rs.getString("speed"));   		//�����ٶ�
				elevators.setNumbers(rs.getString("numbers"));   		//�ܲ���
				elevators.setRegisterState(rs.getString("registerstate"));   		//ע��״̬
				elevators.setLabel(rs.getString("label"));   		//��ͼ��ע
				elevators.setIp(rs.getString("ip"));					//��Ƶip
				elevators.setFlowStart(rs.getString("flow_Start")==null?null:df.parse(rs.getString("flow_Start")));					//��Ƶip
				elevators.setFlowEnd(rs.getString("flow_End")==null?null:df.parse(rs.getString("flow_End")));					//��Ƶip
				elevators.setProvince(rs.getString("province"));   		//ʡ
				elevators.setCity(rs.getString("city"));   		//��
				elevators.setArea(rs.getString("area"));   		//��
				elevators.setUseUnitName(rs.getString("useUnitName"));   		//ʹ�õ�λ����
				elevators.setUseUnitLiaisons(rs.getString("use_Unit_Liaisons"));   		//ʹ�õ�λ������
				elevators.setUseUnitPhone(rs.getString("use_Unit_Phone"));   		//ʹ�õ�λ�����˵绰
				elevators.setMakeUnitName(rs.getString("makeUnitName"));   		//���쵥λ

				elevators.setPropertyUnitName(rs.getString("propertyUnitName"));   		//��ҵ��λ����
				elevators.setPropertyUnitLiaisons(rs.getString("property_Unit_Liaisons"));   		//��ҵ��λ������
				elevators.setUseUnitPhone(rs.getString("property_Unit_Phone"));   				//��ҵ��λ�����˵绰
				elevators.setMaintenanceUnitName(rs.getString("maintenanceUnitName"));		//ά����λ
				elevators.setMaintenanceUsersName(rs.getString("maintenanceUsersName"));		//ά����Ա

				elevators.setInstallPlace(rs.getString("install_Place"));   		//��װ�ص�
				elevators.setInstallUnit(rs.getString("install_Unit"));   		//��װ��λ
				elevators.setInstallUser(rs.getString("install_User"));   		//��װ��
				elevators.setInstallTime(rs.getString("install_Time")==null?null:df.parse(rs.getString("install_Time")));   		//��װʱ��
				elevators.setManufactureTime(rs.getString("manufacture_Time")==null?null:df.parse(rs.getString("manufacture_Time")));   		//��������

				elevators.setServiceIfe(rs.getString("service_ife"));   		//����ʹ������
				elevators.setYearlyTime1(rs.getString("yearly_Time1")==null?null:df.parse(rs.getString("yearly_Time1")));   		//�״μ��ʱ��

				list.add(elevators);
				
			}
			
			if (1 == 1) {
				top_arraydis = ExcelColumns.elevator;

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
					    DtjkElevator	e = (DtjkElevator) list.get(i);
						
						cell = row.createCell(j);// ������ �ֶ�
						cell.setCellValue(e.getRegisterid());   //ע���
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getDistinguishid());   //ʶ����

						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getBrand());   //����Ʒ��
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getModel());   //�����ͺ�

						
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getType());   //��������
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getSpeed());   //�����ٶ�
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getNumbers());   //�ܲ���
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getRegisterState());   //ע��״̬
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getLabel());   //��ͼ��ע(��γ��)
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getIp());   //��Ƶip
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getFlowStart()==null?null:df.format(e.getFlowStart()));   //ά����ͬ��
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getFlowEnd()==null?null:df.format(e.getFlowEnd()));   //ά����ͬ��
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getProvince());   //ʡ/ֱϽ��
					
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getCity());   //��
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getArea());   //��
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getUseUnitName());   //ʹ�õ�λ

						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getUseUnitLiaisons());   //ʹ�õ�λ������
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getUseUnitPhone());   //ʹ�õ�λ�����˵绰
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getMakeUnitName());   //���쵥λ	
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getPropertyUnitName());   //��ҵ��λ
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getPropertyUnitLiaisons());   //��ҵ��λ������
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getPropertyUnitPhone());   //��ҵ��λ�����˵绰
						

						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getMaintenanceUnitName());   //ά����λ
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getMaintenanceUsersName());   //ά����

						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getInstallUnit());   //��װ��λ
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getMaintenanceState());   //��װ�ص�
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getInstallUser());   //��װ��Ա

						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getInstallTime()==null?null:df.format(e.getInstallTime()));   //��װʱ��

						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getManufactureTime()==null?null:df.format(e.getManufactureTime()));   //��������
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getServiceIfe());   //ʹ������

						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getYearlyTime1()==null?null:df.format(e.getYearlyTime1()));   //��������
						
						
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
