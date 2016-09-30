package com.jrsoft.fri.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ImpExcel {
	
	
	public static void main(String[] args) {
	}
	
	public static List<String> changeStrArr (String[] strArr){
		List<String> list = new ArrayList<String>();
		for(String str : strArr){
			if(str!=null && !str.trim().equals("")){
				list.add(str);
			}
		}
		return list;
	}
	private static List<List<Object>> list = null;
	
	public static List<List<Object>> getData(File file, int date) throws Exception{
		return getData(file, new int[]{date});
	}
	
	public static List<List<Object>> getData(File file,int[] date) throws Exception{
		list = new ArrayList<List<Object>>();
		InputStream is = new FileInputStream(file);
		Workbook book = null;
		try {
			
			book = create(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int numSheet = 0; numSheet < book.getNumberOfSheets(); numSheet++) {
			Sheet sheet = book.getSheetAt(numSheet);
			if (sheet == null) {
				continue;
			}
//			System.out.println(sheet.getLastRowNum());
			for(int j = 1;j<=sheet.getLastRowNum();j++){
				Row row = sheet.getRow(j);  
				List<Object> strList = new ArrayList<Object>();
				if(row == null ){
					continue;
				}
				for(int z = 0;z<row.getLastCellNum();z++){
					Object obj = "";
					try {
						if (isDate(date, z)) {
							if(row.getCell(z)!=null){
								if(row.getCell(z).getDateCellValue()!=null){
									obj = new SimpleDateFormat("yyyy-MM-dd").format(row
											.getCell(z).getDateCellValue());
								}
							}
						}
						else{
							obj = row.getCell(z)+"";
						}
					} catch (Exception e) {
						obj = row.getCell(z)+"";
					}
					
					strList.add(obj);
				}
				list.add(strList);
			}
		}
		file.delete();
		return list;
	}

	public static List<List<Object>> getData(File file) throws Exception {
		return getData(file,null);
	}
	
	private static boolean isDate(int[] date,int i){
		if (date == null || date.length < 1)return false;
		else{
			for(int k = 0;k<date.length;k++){
				if(i == date[k]){
					return true;
				}
			}
		}
		return false;
	}
	public static Workbook create(InputStream in) throws Exception {
	    if (!in.markSupported()) {
	        in = new PushbackInputStream(in, 8);
	    }
	    if (POIFSFileSystem.hasPOIFSHeader(in)) {
	        return new HSSFWorkbook(in);
	    }
	    if (POIXMLDocument.hasOOXMLHeader(in)) {
	        return new XSSFWorkbook(OPCPackage.open(in));
	    }
	    throw new IllegalArgumentException("���excel�汾Ŀǰpoi��������");
	}

}
