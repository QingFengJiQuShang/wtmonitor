package com.jrsoft.fri.common.exp;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import java.util.*;
import java.io.*;
import java.util.Calendar;

public class debug {

  public debug() {
  }

  /**
   * @功能描述:   <p>log输出</p>
   * @param:      <p>str 需要输出内容</p>
   * @return
   * @author:     <p>王 军</p>
   * @repairer:
   * @exception   <p>Exception   </p>
   */
  public static void printLog(String str)
  {
    String s = "";
    try{
      String dateTime=debug.getDateAndTime(0);
      String logStr = "";
      String head = "\n"+"...........................";
      String[] strArr = debug.getSqlArray(str);
      if(strArr.length>1)
      {
        for(int i=0;i<strArr.length;i++)
        {
          if(i==0)
            logStr =logStr+(strArr[i]);
          else
            logStr =logStr+head+(strArr[i]);
        }
      }
      else
        logStr=(strArr[0]);
      s = dateTime+"........"+logStr+"\n";
      debug.printDug(s);
      }catch(Exception e)
      {
      }
  }

  /**
   * @功能描述:   <p>log输出到指定地方</p>
   * @param:      <p>str 需要输出内容</p>
   * @return
   * @author:     <p>王 军</p>
   * @repairer:
   * @exception   <p>Exception   </p>
   */
  public static void printDug(String str)
  {
    System.out.println("aaaaaaaaaaaaaaaaaaaaaaaa");
    String dateTime=debug.getDateAndTime(0);
    FileOutputStream myFAFile;
    String url = "/home/lena/bin/jboss-3.2.5/server/default/deploy/cra.war/source/log/log.log";
    try
    {
      myFAFile = new FileOutputStream(url,true);
      try{
        myFAFile.write(str.getBytes());
        myFAFile.flush();
        }catch(IOException e)
        {
        }
        finally
        {

          myFAFile.close();
        }
    }catch(Exception e){
    }

  }

  /**
   * @功能描述:   <p>获得系统时间</p>
   * @para        <p>i         0 要求获得yyyy-mm-dd hh:mm:ss
                                 1 要求获得yyyymmddhhmmss
                                 2 要求获得yyyy-mm-dd
                                 3 要求获得mm-dd-yyyy
                                 4 要求获得hh:mm:ss
                                 5 要求获得yyyymmdd
                                 6 要求获得mmddyyyy
                                 7 要求获得hhmmss

                                 * @return      <p>String返回时间字符串</p>
                                 * @author:     <p>王 军</p>
                                 * @repairer:
                                 * @exception   <p>Exception   </p>
                                 */
                                //public static  String getDateAndTime(int i)
  public static synchronized String getDateAndTime(int i)
  {
    String dateTime = null;
    try
    {
      Calendar cal = Calendar.getInstance();
      String year = String.valueOf(cal.get(cal.YEAR) );
      String month = String.valueOf(cal.get(cal.MONTH)+1 );
      String day = String.valueOf(cal.get(cal.DATE) );
      String hour = String.valueOf(cal.get(cal.HOUR_OF_DAY) );
      String minute = String.valueOf(cal.get(cal.MINUTE) );
      String second = String.valueOf(cal.get(cal.SECOND) );
      year=getEqualStr(year,4);
      month=getEqualStr(month,2);
      day=getEqualStr(day,2);
      hour=getEqualStr(hour,2);
      minute=getEqualStr(minute,2);
      second=getEqualStr(second,2);

      if(i==0) dateTime = year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
      if(i==1) dateTime = year+month+day+hour+minute+second;
      if(i==2) dateTime = year+"-"+month+"-"+day;
      if(i==3) dateTime = month+"-"+day+"-"+year;
      if(i==4) dateTime = hour+":"+minute+":"+second;
      if(i==5) dateTime = year+month+day;
      if(i==6) dateTime = month+day+year;
      if(i==7) dateTime = hour+minute+second;
      if(i==8) dateTime = month+"/"+day+"/"+year;
      if(i==9) dateTime = year+"/"+month+"/"+day;
      }catch(Exception e)
      {
      }
      return dateTime;
  }

  /**
   * @功能描述:   <p>获取等长的字符串,不足前加0</p>
   * @param:      <p>str    要转换的字符串
                    totallen  总长度</p>
                    * @return      <p>结果字符串</p>
                    * @author:     <p>chenfz</p>
                    * @repairer:
                    * @exception   <p>Exception   </p>
                    */
  public static String getEqualStr(String str,int totallen)
  {
    String s="";
    try
    {
      for(int i=1;i<=totallen-str.length();i++)
                { s="0"+s; }
      }catch(Exception e)
      {

      }
      return s+str;
  }


  /**
   * @功能描述:   <p>把一个长的sql字符串转换成每个等长的数组（250）</p>
   * @param:      <p>sql    要截取的sql语句</p>
   * @return      <p>拆分后的sql数组</p>
   * @author:     <p>王 军</p>
   * @repairer:
   * @exception   <p>Exception   </p>
   */
  public   static String[] getSqlArray(String sql)
  {
    String[] sqlarr = null;
    try{
      int conlen=250;
      byte[] str = sql.getBytes();
      Vector vec=new Vector();
      int i = sql.getBytes().length;
      int one = 0;
      String sss="";
      while((i-one)>conlen)
      {
        int a=0;
        sss = new String(str,one,conlen,"gb2312");
        a=one+conlen;
        if(sss.equals(""))
        {
          sss = new String(str,one,conlen-1,"gb2312");
          a=one+conlen-1;
        }
        one=a;
        vec.addElement(sss);
      }

      if((i-one)>0)
      {
        sss = new String(str,one,i-one,"gb2312");
        vec.addElement(sss);
      }
      sqlarr=new String[vec.size()];
      vec.copyInto(sqlarr);
      vec.clear();

      }catch(Exception e)
      {
      }
      return sqlarr;
  }
}
