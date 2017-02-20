package com.jrsoft.fri.bxgl.dao;

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

import com.jrsoft.fri.bxgl.entity.BxglSafe;
import com.jrsoft.fri.bxgl.from.Safe;
import com.jrsoft.fri.bxgl.from.SafeUnit;
import com.jrsoft.fri.common.core.dao.BaseDaoImpl;
import com.jrsoft.fri.dtjk.entity.DtjkElevator;
import com.jrsoft.fri.xtgl.from.ExcelColumns;

public class BxglSafeDaoImpl   extends BaseDaoImpl< BxglSafe, String> implements  BxglSafeDao{

	@Override
	public Class<? extends Object> getEntityClass() {
		// TODO Auto-generated method stub
		return BxglSafe.class;
	}

	@Override
	public void exportSafe(String filePath, List<Safe> list) {
		try {
			System.out.println("========>>" + filePath);
			//Excel ex = new Excel();
			HSSFWorkbook workbook = new HSSFWorkbook();
			// ��Excel�������н�һ����������Ϊȱʡֵ
			HSSFSheet sheet = null;
			HSSFRow row = null;
			HSSFCell cell = null;
			sheet = workbook.createSheet("����ͳ��");
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
			
			if (1 == 1) {
				top_arraydis = ExcelColumns.Safe;

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
					    Safe	e = (Safe) list.get(i);
						
						cell = row.createCell(j);// ������ �ֶ�
						cell.setCellValue(e.getZong());   //ע���
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getWei());   //ʶ����

						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getWeiRate());   //����Ʒ��
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getZai());   //�����ͺ�

						
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getZaiRate());   //��������
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getTuo());   //�����ٶ�
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getTuoRate());   //�ܲ���
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getClaimNum());   //ע��״̬
						
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getClaimRate());   //ʹ�õ�λ

						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getMostNum());   //ά����λ
						
						
						
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
	public void exportSafeUnit(String filePath, List<SafeUnit> list) {
		try {
			System.out.println("========>>" + filePath);
			//Excel ex = new Excel();
			HSSFWorkbook workbook = new HSSFWorkbook();
			// ��Excel�������н�һ����������Ϊȱʡֵ
			HSSFSheet sheet = null;
			HSSFRow row = null;
			HSSFCell cell = null;
			sheet = workbook.createSheet("����ͳ��");
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
			
			if (1 == 1) {
				top_arraydis = ExcelColumns.SafeUnit;

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
					    SafeUnit	e = (SafeUnit) list.get(i);
						
						cell = row.createCell(j);// ������ �ֶ�
						cell.setCellValue(e.getName());   //ע���
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getNum());   //ʶ����

						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getClaimNum());   //����Ʒ��
						
						cell = row.createCell(++j);// ������ �ֶ�
						cell.setCellValue(e.getClaimRate());   //�����ͺ�
						
						
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
