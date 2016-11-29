package com.jrsoft.fri.dtjk.action;

import java.text.SimpleDateFormat;
import java.util.Date;



public class test {
	public static void main(String[] args) { 
		String str="38";
		 System.out.println(Integer.parseInt(str,16)*2);
	}
	//十六进制ascii 解析
	 public static  String stringToHex(String hex){

		  StringBuilder temp = new StringBuilder();
		  for( int i=0; i<hex.length()-1; i+=2 ){
		      String output = hex.substring(i, (i + 2));
		      int decimal = Integer.parseInt(output);
		      if(decimal>15){
		    	  temp.append(Integer.toHexString(decimal)); 
		      }else{
		    	  temp.append("0"+Integer.toHexString(decimal));
		      }
		  }

		  return temp.toString();
		  }
	
}
