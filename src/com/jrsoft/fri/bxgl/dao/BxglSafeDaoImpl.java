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
			// 在Excel工作簿中建一工作表，其名为缺省值
			HSSFSheet sheet = null;
			HSSFRow row = null;
			HSSFCell cell = null;
			sheet = workbook.createSheet("保险统计");
			HSSFCellStyle style = workbook.createCellStyle(); // 样式对象
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平
			/**
			 * 设置字体
			 */
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式	
			HSSFFont f = workbook.createFont();
			f.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 字体加粗
			style.setFont(f);		
			String[] top_arraydis = null;
			
			if (1 == 1) {
				top_arraydis = ExcelColumns.Safe;

				row = sheet.createRow(0);// 创建一行

				for (int c = 0; c < top_arraydis.length; c++) {
					cell = row.createCell((short) c);// 创建格 字段 
					cell.setCellValue("中文");
					cell.setCellStyle(style);
					cell.setCellValue(top_arraydis[c]);
				}
				int j = 0;
				for (int i = 0; i < list.size(); i++) {
					    row = sheet.createRow(i+1);
					    Safe	e = (Safe) list.get(i);
						
						cell = row.createCell(j);// 创建格 字段
						cell.setCellValue(e.getZong());   //注册号
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getWei());   //识别码

						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getWeiRate());   //电梯品牌
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getZai());   //电梯型号

						
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getZaiRate());   //电梯类型
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getTuo());   //电梯速度
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getTuoRate());   //总层数
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getClaimNum());   //注册状态
						
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getClaimRate());   //使用单位

						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getMostNum());   //维保单位
						
						
						
						j = 0;
					}
			}

			// 新建一输出文件流
			
			FileOutputStream fOut = new FileOutputStream(filePath);
//			System.out.println(fOut + "-----");
			// 把相应的Excel 工作簿存盘
			workbook.write(fOut);
			fOut.flush();
			// 操作结束，关闭文件
			fOut.close();
			return;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("已运行 xlCreate() : " + e);
		}
		
		
	}

	@Override
	public void exportSafeUnit(String filePath, List<SafeUnit> list) {
		try {
			System.out.println("========>>" + filePath);
			//Excel ex = new Excel();
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 在Excel工作簿中建一工作表，其名为缺省值
			HSSFSheet sheet = null;
			HSSFRow row = null;
			HSSFCell cell = null;
			sheet = workbook.createSheet("保险统计");
			HSSFCellStyle style = workbook.createCellStyle(); // 样式对象
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平
			/**
			 * 设置字体
			 */
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式	
			HSSFFont f = workbook.createFont();
			f.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 字体加粗
			style.setFont(f);		
			String[] top_arraydis = null;
			
			if (1 == 1) {
				top_arraydis = ExcelColumns.SafeUnit;

				row = sheet.createRow(0);// 创建一行

				for (int c = 0; c < top_arraydis.length; c++) {
					cell = row.createCell((short) c);// 创建格 字段 
					cell.setCellValue("中文");
					cell.setCellStyle(style);
					cell.setCellValue(top_arraydis[c]);
				}
				int j = 0;
				for (int i = 0; i < list.size(); i++) {
					    row = sheet.createRow(i+1);
					    SafeUnit	e = (SafeUnit) list.get(i);
						
						cell = row.createCell(j);// 创建格 字段
						cell.setCellValue(e.getName());   //注册号
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getNum());   //识别码

						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getClaimNum());   //电梯品牌
						
						cell = row.createCell(++j);// 创建格 字段
						cell.setCellValue(e.getClaimRate());   //电梯型号
						
						
						j = 0;
					}
			}

			// 新建一输出文件流
			
			FileOutputStream fOut = new FileOutputStream(filePath);
//			System.out.println(fOut + "-----");
			// 把相应的Excel 工作簿存盘
			workbook.write(fOut);
			fOut.flush();
			// 操作结束，关闭文件
			fOut.close();
			return;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("已运行 xlCreate() : " + e);
		}
		
		
	}
}
