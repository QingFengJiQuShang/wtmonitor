package com.jrsoft.fri.xtgl.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.struts.upload.FormFile;

public class Upload {
	
	/**
	 * 上传文件
	 * @throws Exception 
	 */
	public String uploadFile(FormFile file ,HttpServletRequest request,String filePath) throws Exception{
		
		Date date = new Date();
		int i = 0;
		String type = file.getFileName();
		String path = "";
		while(i!=-1){
			i = type.indexOf(".");
			type = type.substring(i+1);//文件类型
		}
		String times = String.valueOf(date.getTime());
		String  fname = times + "." + type;
		try{
			InputStream streamIn = file.getInputStream();
			int ok=file.getFileSize();
			String strFee = null;
			if(ok>=1024*1024)
			{
				float ok1=(((float)ok)/1024f/1024f); 
				DecimalFormat myformat1 = new DecimalFormat("0.00");         
				strFee = myformat1.format(ok1)+"M";
				System.out.println(strFee+"M");
			}
			else if(ok>1024 && ok<=1024*1024)
			{
				double  ok2=((double)ok)/1024;
				DecimalFormat myformat2=new DecimalFormat("0.00");
				strFee = myformat2.format(ok2)+"kb";
				System.out.println(strFee+"kb");
			}
			else if(ok<1024)
			{
				strFee=String.valueOf(ok)+"byte";
				System.out.println(strFee);
			}
			System.out.println( streamIn.available()+"文件大小byte"+"   "+filePath);
			File uploadFile = new File(filePath);
			if (!uploadFile.exists() || uploadFile == null) {  
				uploadFile.mkdirs();
			}
			path = uploadFile.getPath() + "\\" + fname;
			OutputStream streamOut = new FileOutputStream(path);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = streamIn.read(buffer, 0, 8192)) != -1) {
				streamOut.write(buffer, 0, bytesRead);
			}
			streamOut.close();
			streamIn.close();
			file.destroy();  
			System.out.println("path==="+path);
		}catch(Exception e){
			e.printStackTrace();
		}
		getExcelData(path);
		return path;
	}
	public void getExcelData(String fileName) throws Exception 
	{
		InputStream in = new FileInputStream(fileName);
	    Workbook workbook = WorkbookFactory.create(in);  
	    Sheet sheet = workbook.getSheetAt(0);  //示意访问sheet  
		Row row = null;
		Cell cell = null;
		
		int totalRows = sheet.getPhysicalNumberOfRows();
		int totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
		
		for(int r=1; r<totalRows; r++) 
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
