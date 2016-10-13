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
import com.jrsoft.fri.dagl.entity.DaxxElevator;
import com.jrsoft.fri.dagl.entity.DaxxGateway;
import com.jrsoft.fri.dagl.from.ExcelColumns;

public class DaxxGatewayDaoImpl  extends BaseDaoImpl< DaxxGateway, String> implements  DaxxGatewayDao{

	@Override
	public Class<? extends Object> getEntityClass() {
		// TODO Auto-generated method stub
		return  DaxxGateway.class;
	}

	@Override
	public void export(String filePath, DaxxGateway gateway) {
		try {
			System.out.println("========>>" + filePath);
			//Excel ex = new Excel();
			HSSFWorkbook workbook = new HSSFWorkbook();
			// ��Excel�������н�һ����������Ϊȱʡֵ
			HSSFSheet sheet = null;
			HSSFRow row = null;
			HSSFCell cell = null;
			List<DaxxGateway> list=new ArrayList<DaxxGateway>();
			sheet = workbook.createSheet("����������Ϣ");
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
			String sql="select dr.*  from daxx_maintenance_unit dr where  1=1 " ;
			
			if(gateway.getType()!=null&&!gateway.getType().equals("")){
				sql+=" and type = '%"+gateway.getType()+"%'";
			}
			if(gateway.getNet()!=null&&!gateway.getNet().equals("")){
				sql+=" and net = '%"+gateway.getNet()+"%'";
			}
			if(gateway.getFlow()!=null&&!gateway.getFlow().equals("")){
				sql+=" and flow = '%"+gateway.getFlow()+"%'";
			}
			if(gateway.getCommunication()!=null&&!gateway.getCommunication().equals("")){
				sql+=" and communication like '%"+gateway.getCommunication()+"%'";
			}
			if(gateway.getTerminal()!=null&&!gateway.getTerminal().equals("")){
				sql+=" and terminal like '%"+gateway.getTerminal()+"%'";
			}
			if(gateway.getHardware()!=null&&!gateway.getHardware().equals("")){
				sql+=" and hardware like '%"+gateway.getHardware()+"%'";
			}
			if(gateway.getSoftware()!=null&&!gateway.getSoftware().equals("")){
				sql+=" and software like '%"+gateway.getSoftware()+"%'";
			}
			sql+=" order by id";	
			
			PreparedStatement sta = conn.prepareStatement(sql);
			ResultSet rs = sta.executeQuery();
			list=new ArrayList<DaxxGateway>();
			while(rs.next()){
				DaxxGateway elevators=new DaxxGateway();
				elevators.setId(rs.getLong("id"));
				elevators.setType(rs.getString("type"));
				elevators.setFlow(rs.getString("flow"));
				elevators.setNet(rs.getString("net"));
				elevators.setCommunication(rs.getString("communication"));
				elevators.setTerminal(rs.getString("terminal"));
				elevators.setHardware(rs.getString("hardware"));
				elevators.setSoftware(rs.getString("software"));
				elevators.setSim(rs.getString("sim"));
				elevators.setReport(rs.getString("report"));
				elevators.setSerialNumber(rs.getString("serial_Number"));
				elevators.setIp(rs.getString("ip"));
				elevators.setPort(rs.getString("port"));
				list.add(elevators);
				
			}
			
			if (1 == 1) {
				top_arraydis = ExcelColumns.gateway;

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
					    DaxxGateway	e = (DaxxGateway) list.get(i);
						
						cell = row.createCell(j);// ������ �ֶ�
						cell.setCellValue(e.getType());   //������������
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getFlow());   //ȡ����ʽ

						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getNet());   //������ʽ
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getCommunication());   //ͨ����·

						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getTerminal());   //�ն˻���
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getHardware());   //Ӳ���汾

						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getSoftware());   //����汾
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getSim());   //SIM����

						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getReport());   //�ϱ�����
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getSerialNumber());   //�豸���к�

						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getIp());   //IP��ַ
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getPort());   //port

						
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

