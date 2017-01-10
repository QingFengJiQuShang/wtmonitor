package com.jrsoft.fri.xtgl.action;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Upload {
	public void getExcelData(String fileName) throws Exception 
	{
		InputStream in = new FileInputStream(fileName);
	    Workbook workbook = WorkbookFactory.create(in);  
	    Sheet sheet = workbook.getSheetAt(0);  //示意访问sheet  
		Row row = null;
		Cell cell = null;
		
		int totalRows = sheet.getPhysicalNumberOfRows();
		int totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
		
		for(int r=0; r<totalRows; r++) 
		{
			row = sheet.getRow(r);
			System.out.print("第" + r + "行");
			for(int c = 0; c < totalCells; c++)
			{
				cell = row.getCell(c);
				String cellValue = "";
				if(null != cell){
					// 以下是判断数据的类型
					switch (cell.getCellType()) 
					{
					case HSSFCell.CELL_TYPE_NUMERIC: // 数字
						cellValue = cell.getNumericCellValue() + "";
						//时间格式
						if(HSSFDateUtil.isCellDateFormatted(cell)){
							Date dd = cell.getDateCellValue();
							DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							cellValue = df.format(dd);
						}
						break;

					case HSSFCell.CELL_TYPE_STRING: // 字符串
						cellValue = cell.getStringCellValue();
						break;

					case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
						cellValue = cell.getBooleanCellValue() + "";
						break;

					case HSSFCell.CELL_TYPE_FORMULA: // 公式
						cellValue = cell.getCellFormula() + "";
						break;

					case HSSFCell.CELL_TYPE_BLANK: // 空值
						cellValue = "";
						break;

					case HSSFCell.CELL_TYPE_ERROR: // 故障
						cellValue = "非法字符";
						break;

					default:
						cellValue = "未知类型";
						break;
					}
					
					System.out.print("   "+cellValue+"\t");
				}
			}
			System.out.println();
		}
		
	}
	
	public static void main(String[] args) throws Exception
	{
		String fileName = "D:/网关通信命令.xls";
		Upload upload = new Upload();
		upload.getExcelData(fileName);
	}

}
