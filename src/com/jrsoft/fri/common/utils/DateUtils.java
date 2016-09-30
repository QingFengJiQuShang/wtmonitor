package com.jrsoft.fri.common.utils;

import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtils {

    /**
     * Date �� amount ��
     * @return
     */
    public static final Date addDay(Date date, int amount){
    	Calendar cd = Calendar.getInstance();
    	cd.setTime(date);
    	cd.add(Calendar.DATE, amount);
    	return cd.getTime();
    }
   public static String getNowWeek(){
	   
	   String week="";
	   Calendar cal = Calendar.getInstance();
	   if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
		   week="������";
		}else if (cal.get(Calendar.DAY_OF_WEEK) == 2) {
			week="����һ";
		}else if (cal.get(Calendar.DAY_OF_WEEK) == 3) {
			week="���ڶ�";
		}else if (cal.get(Calendar.DAY_OF_WEEK) == 4) {
			week="������";
		}else if (cal.get(Calendar.DAY_OF_WEEK) == 5) {
			week="������";
		}else if (cal.get(Calendar.DAY_OF_WEEK) == 6) {
			week="������";
		}else if (cal.get(Calendar.DAY_OF_WEEK) == 7) {
			week="������";
		}	
	   return week;
   }
   /**���ص�ǰ���ڸ�ʽ��xxxx��xx��xx��
    * */
   public static String getNowDate(){
		SimpleDateFormat df=new SimpleDateFormat("yyyy��MM��dd��");	
	   return df.format(new Date());
   }
   
   /**���ص�ǰ���ڸ�ʽ��xxxx��xx��xx��
    * */
   public static String getFormatDate(Date date){
	   if(date==null){
		   return "";
	   }
		SimpleDateFormat df=new SimpleDateFormat("yyyy��MM��dd��");	
	   return df.format(date);
   }

	public static void main(String[] args) {
		System.out.println(getNowDate());
		
	}
}
