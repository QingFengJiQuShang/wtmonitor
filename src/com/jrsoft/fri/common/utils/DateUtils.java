package com.jrsoft.fri.common.utils;

import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtils {

    /**
     * Date 加 amount 天
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
		   week="星期日";
		}else if (cal.get(Calendar.DAY_OF_WEEK) == 2) {
			week="星期一";
		}else if (cal.get(Calendar.DAY_OF_WEEK) == 3) {
			week="星期二";
		}else if (cal.get(Calendar.DAY_OF_WEEK) == 4) {
			week="星期三";
		}else if (cal.get(Calendar.DAY_OF_WEEK) == 5) {
			week="星期四";
		}else if (cal.get(Calendar.DAY_OF_WEEK) == 6) {
			week="星期五";
		}else if (cal.get(Calendar.DAY_OF_WEEK) == 7) {
			week="星期六";
		}	
	   return week;
   }
   /**返回当前日期格式：xxxx年xx月xx日
    * */
   public static String getNowDate(){
		SimpleDateFormat df=new SimpleDateFormat("yyyy年MM月dd日");	
	   return df.format(new Date());
   }
   
   /**返回当前日期格式：xxxx年xx月xx日
    * */
   public static String getFormatDate(Date date){
	   if(date==null){
		   return "";
	   }
		SimpleDateFormat df=new SimpleDateFormat("yyyy年MM月dd日");	
	   return df.format(date);
   }

	public static void main(String[] args) {
		System.out.println(getNowDate());
		
	}
}
