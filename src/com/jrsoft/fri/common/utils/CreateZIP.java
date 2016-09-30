package com.jrsoft.fri.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CreateZIP {
	
	
	public static void main(String[] args) {
		
		String path="E:/temp";
		zipDIR(path,"E:/temp/temp123.zip");
	}
	
	
	/**
	 * 压缩文件夹
	 * @param sourceDIR 文件夹名称（包含路径）
	 * @param targetZipFile 生成zip文件名（包含路径、文件名及扩展名）
	 * @author wangcuicui
	 */
	public static void zipDIR(String sourceDIR[], String targetZipFile) {
		try {
			FileOutputStream target = new FileOutputStream(targetZipFile);
			ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(target));
			int BUFFER_SIZE = 1024;
			byte buff[] = new byte[BUFFER_SIZE];
	 
			for (int i = 0; i < sourceDIR.length; i++) {
				File dir = new File(sourceDIR[i]);
				if  (!dir.exists())  {   
		            System.out.println(sourceDIR[i]+"该文件不存在！"); 
		            continue;
		        }  
				FileInputStream fi = new FileInputStream(dir);
				BufferedInputStream origin = new BufferedInputStream(fi);
				ZipEntry entry = new ZipEntry(dir.getName());
				out.putNextEntry(entry);
				int count;
				while ((count = origin.read(buff)) != -1) {
					out.write(buff, 0, count);
				}
				origin.close();
			}
			out.close();
	 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 压缩文件夹
	 * @param sourceDIR 文件夹名称（包含路径）
	 * @param targetZipFile 生成zip文件名
	 * @author liuxiangwei
	 */
	public static void zipDIR(String sourceDIR, String targetZipFile) {
		try {
			FileOutputStream target = new FileOutputStream(targetZipFile);
			ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(target));
			int BUFFER_SIZE = 1024;
			byte buff[] = new byte[BUFFER_SIZE];
			File dir = new File(sourceDIR);
			if (!dir.isDirectory()) {
				throw new IllegalArgumentException(sourceDIR+" is not a directory!");
			}
			File files[] = dir.listFiles();
	 
			for (int i = 0; i < files.length; i++) {
				FileInputStream fi = new FileInputStream(files[i]);
				BufferedInputStream origin = new BufferedInputStream(fi);
				ZipEntry entry = new ZipEntry(files[i].getName());
				out.setComment(sourceDIR);
				//out.setEncoding("GBK");
				out.putNextEntry(entry);
				int count;
				while ((count = origin.read(buff)) != -1) {
					out.write(buff, 0, count);
				}
				origin.close();
			}
			out.close();
	 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	   public static void zip(String inputFileName,String zipfileName) throws Exception {
	        zip(zipfileName, new File(inputFileName));
	    }

	    public static void zip(String zipFileName, File inputFile) throws Exception {
	        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
	        zip(out, inputFile, "");
	        out.close();
	    }

	    public static void zip(ZipOutputStream out, File f, String base) throws Exception {
	        if (f.isDirectory()) {
	           File[] fl = f.listFiles();
	           out.putNextEntry(new org.apache.tools.zip.ZipEntry(base + "/"));
	           base = base.length() == 0 ? "" : base + "/";
	           for (int i = 0; i < fl.length; i++) {
	           zip(out, fl[i], base + fl[i].getName());
	         }
	        }else {
	           out.putNextEntry(new org.apache.tools.zip.ZipEntry(base));
	           FileInputStream in = new FileInputStream(f);
	           int b;
	           System.out.println(base);
	           while ( (b = in.read()) != -1) {
	            out.write(b);
	         }
	         in.close();
	       }
	    }

	  

}
