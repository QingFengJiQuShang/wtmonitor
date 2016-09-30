package com.jrsoft.fri.common.exp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.LabelCell;
import jxl.NumberCell;
import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.WritableFont;



public class Excel {

  private jxl.write.WritableWorkbook WorkBuk=null ;
  private jxl.write.WritableSheet ws=null;
  private FileOutputStream fos = null;
  private InputStream is = null;
  private jxl.Workbook rwb = null;
  private jxl.write.WritableFont wfc = new jxl.write.WritableFont(WritableFont.ARIAL,10, WritableFont.NO_BOLD, false,UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
  private jxl.write.WritableCellFormat wcfFC= null;
  private jxl.Sheet rs=null;


  /**
   * ��ָ��·�����½�һ��Excel�ļ���
   * @parma String FileName �ļ����?+�ļ�·�����ļ����·��? �磺D:\\sheet1.xls��
   * @return boolean
  */
 public boolean createExcel(String FileName) throws Exception {
   boolean Rtn = false;
   try {
     fos = new FileOutputStream(FileName);
     WorkBuk = Workbook.createWorkbook(fos);
     Rtn = true;
   }
   catch (Exception e) {
     debug.printDug("Excel.createExcel is error,this is errormessage="+e.getMessage());
     Rtn = false;
   }
   finally {
     try {

     }
     catch (Exception e) {

     }

   }
   return Rtn;
 }


  /**
   * ���õ�ǰsheet����������Ƽ�sheet��
   * @parma int SheetIdx(sheet��) String SheetName(�����������?)
   * @return boolean
  */
 public boolean setCurSheet(int SheetIdx, String SheetName) {
   boolean Rtn = false;
   try {
     ws = WorkBuk.createSheet(SheetName, SheetIdx);
     Rtn = true;
   }
   catch (Exception e) {
     debug.printDug("Excel.setCurSheet is error,this is errormessage="+e.getMessage());
     Rtn = false;
   }
   finally {
     try {

     }
     catch (Exception e) {
     }
   }
   return Rtn;
 }

public boolean setFontColour(String colorstr){
  boolean result = false;
  try{
    if(colorstr.toUpperCase()=="BLACK"){
      wfc.setColour(jxl.format.Colour.BLACK);
      result = true;
    }
  }catch(Exception e){
    result = false;
  }
  return result;
}

public boolean setFamily(String fontName){
  boolean result = false;
  try{
      wfc.createFont(fontName);
      result = true;
  }catch(Exception e){
    result = false;
  }
  return result;
}


   /**
   * ��cell��Ԫ����д��String����ݲ��������õ�Ԫ�񱳾�?
   * @parma int col(�к�) int row(�к�) Txt ����
   * @return boolean
   */
  public boolean setCellText(int row, int col, String Txt, String bgcolor) {
    boolean Rtn = false;
    try {
      wfc = new jxl.write.WritableFont(WritableFont.ARIAL, 10,
                                       WritableFont.NO_BOLD, false,
                                       UnderlineStyle.NO_UNDERLINE,
                                       jxl.format.Colour.BLACK);
      setCellBgcolor(bgcolor);
      jxl.write.Label labelC = new jxl.write.Label(col, row, Txt, wcfFC);
      ws.addCell(labelC);
      Rtn = true;
    }
    catch (Exception e) {
      debug.printDug("Excel.setCellText is error,this is errormessage="+e.getMessage());
      Rtn = false;
    }
    finally {
      try {

      }
      catch (Exception e) {

      }
    }
    return Rtn;
  }



  /**
   * ��cell��Ԫ����д��Date����ݲ��������õ�Ԫ�񱳾�?
   * @parma int col(�к�) int row(�к�) Txt ����
   * @return boolean
  */
 public boolean setCellDate(int row, int col, Date Txt, String bgcolor) {
   boolean Rtn = false;
   try {
     wfc = new jxl.write.WritableFont(WritableFont.ARIAL, 10,
                                      WritableFont.NO_BOLD, false,
                                      UnderlineStyle.NO_UNDERLINE,
                                      jxl.format.Colour.BLACK);
     setCellBgcolor(bgcolor);
     jxl.write.DateTime labelC = new jxl.write.DateTime(col, row, Txt, wcfFC);
     ws.addCell(labelC);
     Rtn = true;
   }
   catch (Exception e) {
     debug.printDug("Excel.setCellDate is error,this is errormessage="+e.getMessage());
     Rtn = false;
   }
   finally {
     try {

     }
     catch (Exception e) {

     }

   }
   return Rtn;

 }


  /**
   * ��cell��Ԫ����д�����֣�double�����ֲ��������õ�Ԫ�񱳾�
   * @parma int col(�к�) int row(�к�) Txt ����
   * @return boolean
  */
  public boolean setCellNumber(int row,int col,double Txt,String bgcolor)
  {
    boolean Rtn = false;
    try {
	  wfc = new jxl.write.WritableFont(WritableFont.ARIAL, 10,
                                      WritableFont.NO_BOLD, false,
                                      UnderlineStyle.NO_UNDERLINE,
                                      jxl.format.Colour.BLACK);
      setCellBgcolor(bgcolor);
      jxl.write.Number labelC = new jxl.write.Number(col, row, Txt, wcfFC);
      ws.addCell(labelC);
      Rtn = true;
    }
    catch (Exception e) {
      debug.printDug("Excel.setCellNumber is error,this is errormessage="+e.getMessage());
      Rtn = false;
    }
    finally {
      try {

      }
      catch (Exception e) {

      }

    }
    return Rtn;

  }


  /**
   * ��cell��Ԫ����д��String����ݲ����������������?
   * @parma int col(�к�) int row(�к�) Txt ���� int format ������ʽ���? int fontSize ������?
   * @return boolean
  */
  public boolean setCellText(int row,int col,String Txt,int format,int fontSize)
  {
      boolean Rtn=false;
      try
      {
        setCellFont(format,fontSize);
        wcfFC=new jxl.write.WritableCellFormat(wfc);
        jxl.write.Label labelC = new jxl.write.Label(col, row, Txt,wcfFC);
        ws.addCell(labelC);
        Rtn=true;
      }
      catch(Exception e)
      {
        debug.printDug("Excel.setCellText is error,this is errormessage="+e.getMessage());
        Rtn=false;
      }
      finally
      {
        try
        {

        }
        catch(Exception e)
        {

        }
      }
      return Rtn;
    }



  /**
   * ��cell��Ԫ����д��Date����ݲ����������������?
   * @parma int col(�к�) int row(�к�) Txt ���� int format ������ʽ���? int fontSize ������?
   * @return boolean
  */
  public boolean setCellDate(int row,int col,Date Txt,int format,int fontSize)
  {
      boolean Rtn=false;
      try
      {
          setCellFont(format,fontSize);
          wcfFC=new jxl.write.WritableCellFormat(wfc);
          jxl.write.DateTime  labelC = new jxl.write.DateTime (col, row, Txt,wcfFC);
          ws.addCell(labelC);
          Rtn=true;
      }
      catch(Exception e)
      {
          debug.printDug("Excel.setCellDate is error,this is errormessage="+e.getMessage());
          Rtn=false;
      }
      finally
      {
          try
          {

          }
          catch(Exception e)
          {

          }

      }
      return Rtn;
  }


  /**
   * ��cell��Ԫ����д�����֣�double�����ֲ���������������ʽ
   * @parma int col(�к�) int row(�к�) Txt ���� int format ������ʽ���? int fontSize ������?
   * @return boolean
  */
  public boolean setCellNumber(int row,int col,double Txt,int format,int fontSize)
  {
      boolean Rtn=false;
      try
      {
          setCellFont(format,fontSize);
          wcfFC=new jxl.write.WritableCellFormat(wfc);
          jxl.write.Number  labelC = new jxl.write.Number (col, row, Txt,wcfFC);
          ws.addCell(labelC);
          Rtn=true;
      }
      catch(Exception e)
      {
         debug.printDug("Excel.setCellNumber is error,this is errormessage="+e.getMessage());
         Rtn=false;
      }
      finally
      {
          try
          {

          }
          catch(Exception e)
          {

          }

      }
      return Rtn;
 }


  /**
   * ��cell��Ԫ����д��String�����?
   * @parma int col(�к�) int row(�к�) Txt ����
   * @return boolean
  */
  public boolean setCellText(int row,int col,String Txt)
  {
      boolean Rtn=false;
      try
      {
          jxl.write.Label labelC = new jxl.write.Label(col, row, Txt);
          ws.addCell(labelC);
          Rtn=true;
      }
      catch(Exception e)
      {
          debug.printDug("Excel.setCellText is error,this is errormessage="+e.getMessage());
          Rtn=false;
      }
      finally
      {
          try
          {

          }
          catch(Exception e)
          {

          }
      }
      return Rtn;
    }


  /**
   * ��cell��Ԫ����д��Date�����?
   * @parma int col(�к�) int row(�к�) Txt ����
   * @return boolean
  */
  public boolean setCellDate(int row,int col,Date Txt)
  {
      boolean Rtn=false;
      try
      {
              jxl.write.DateTime  labelC = new jxl.write.DateTime (col, row, Txt);
              ws.addCell(labelC);
              Rtn=true;
      }
      catch(Exception e)
      {
        debug.printDug("Excel.setCellDate is error,this is errormessage="+e.getMessage());
        Rtn=false;
      }
      finally
      {
              try
              {

              }
              catch(Exception e)
              {

              }

      }
      return Rtn;

  }


  /**
   * ��cell��Ԫ����д�����֣�double�����֡�
   * @parma int col(�к�) int row(�к�) Txt ����
   * @return boolean
  */
  public boolean setCellNumber(int row,int col,double Txt)
  {
      boolean Rtn=false;
      try
      {
              jxl.write.Number  labelC = new jxl.write.Number (col, row, Txt);
              ws.addCell(labelC);
              Rtn=true;
      }
      catch(Exception e)
      {
        debug.printDug("Excel.setCellNumber is error,this is errormessage="+e.getMessage());
        Rtn=false;
      }
      finally
      {
              try
              {

              }
              catch(Exception e)
              {

              }

      }
      return Rtn;

  }

  /**
   * ��cell��Ԫ����д�����֣�float�����֡�
   * @parma int col(�к�) int row(�к�) Txt ����
   * @return boolean
  */
  public boolean setCellNumber(int row,int col,float Txt)
  {
      boolean Rtn=false;
      try
      {
              jxl.write.Number  labelC = new jxl.write.Number (col, row, Txt);
              ws.addCell(labelC);
              Rtn=true;
      }
      catch(Exception e)
      {
        debug.printDug("Excel.setCellNumber is error,this is errormessage="+e.getMessage());
        Rtn=false;
      }
      finally
      {
              try
              {

              }
              catch(Exception e)
              {

              }

      }
      return Rtn;

  }

          /**
   * ��д���cell�����ݱ��浽Excel�ļ���.
   * @parma
   * @return boolean
  */
  public boolean saveExcel()
  {
    boolean Rtn=false;
    try
    {
        //д��Exel������
        WorkBuk.write();
        //�ر�Excel����������
        WorkBuk.close();
        Rtn=true;
    }
    catch(Exception e)
    {
       debug.printDug("Excel.saveExcel is error,this is errormessage="+e.getMessage());
       Rtn=false;
    }
    finally
    {
        try
        {

        }
        catch(Exception e)
        {

        }

    }
    return Rtn;

  }

          /**
   * ��Excel.
   * @parma fileName Excel�ļ���+�ļ�·�������·����?
   * @return boolean
  */
  public boolean openExcel(String fileName)
  {
      boolean Rtn=false;
      try
      {
          is = new FileInputStream(fileName);
          rwb = Workbook.getWorkbook(is);
          Rtn=true;
      }
      catch(Exception e)
      {
    	  e.printStackTrace();
         debug.printDug("Excel.openExcel is error,this is errormessage="+e.getMessage());
         Rtn=false;
      }
      finally
      {
         

      }
      return Rtn;
  }


          /**
   * �رմ򿪵�Excel.
   * @
   * @return boolean
  */
  public boolean closeExcel()
  {
    boolean Rtn=false;
    try
    {
      rwb.close();
      Rtn=true;
    }
    catch(Exception e)
    {
      debug.printDug("Excel.closeExcel is error,this is errormessage="+e.getMessage());
      Rtn=false;
    }
    finally
    {
      try
      {
              rwb.close();
      }
      catch(Exception e)
      {
              Rtn=false;
      }

    }
    return Rtn;

  }


 /**
   * �õ������������?
   * @parma sheetIndex ��������
   * @return String
  */

  public String getSheetName(int sheetIndex) {

    String sheetNam = "";
    try {
      rs = rwb.getSheet(sheetIndex);
      sheetNam = rs.getName();
    }
    catch (Exception e) {
      debug.printDug("Excel.getSheetName is error,this is errormessage="+e.getMessage());
      sheetNam = "";
    }
    finally {
      try {

      }
      catch (Exception e) {
        sheetNam = "";
      }
    }
    return sheetNam;

  }

  /**
   * �õ���ǰ��������������
   * @parma sheetIndex ��������
   * @return int
   */

  public int getColCount(int sheetIndex) {

    int colCnt = 0;
    try {
      rs = rwb.getSheet(sheetIndex);
      colCnt = rs.getColumns();
    }
    catch (Exception e) {
      debug.printDug("Excel.getColCount is error,this is errormessage="+e.getMessage());
      colCnt = 0;
    }
    finally {
      try {

      }
      catch (Exception e) {
        colCnt = 0;
      }

    }
    return colCnt ;

  }


  /**
   * �õ���ǰ��������������
   * @parma sheetIndex ��������
   * @return int
   */

  public int getRowCount(int sheetIndex) {

    int colCnt = 0;
    try {
      rs = rwb.getSheet(sheetIndex);
      colCnt = rs.getRows();
    }
    catch (Exception e) {
      debug.printDug("Excel.getRowCount is error,this is errormessage="+e.getMessage());
      colCnt = 0;
    }
    finally {
      try {

      }
      catch (Exception e) {
        colCnt = 0;
      }

    }
    return colCnt ;

  }


  /**
   * ��ȡĳһ�е����е�Ԫ��
   * @parma col ����
   * @return String[]
   */

  public String[] getColArray(int col) {

      //Sheet rs = rwb.getSheet(sheetIndex);
      Cell[] getArray = rs.getColumn(col);
      String Str[] = new String[getArray.length];
      try {
        for(int i=0;i<getArray.length;i++)
        {
          Cell c00 = rs.getCell(col, i);
          Str[i] = c00.getContents();
        }
    }
    catch (Exception e) {
      debug.printDug("Excel.getColArray is error,this is errormessage="+e.getMessage());
      Str = null;
    }
    finally {
      try {

      }
      catch (Exception e) {
        Str = null;
      }

    }
    return Str ;

  }


  /**
   * ��ȡĳһ�е����е�Ԫ��
   * @parma col ����
   * @return String[]
   */

  public String[] getRowArray(int row) {

      //Sheet rs = rwb.getSheet(sheetIndex);
      Cell[] getArray = rs.getRow(row);
      String Str[] = new String[getArray.length];

    try
    {
      for(int i=0;i<getArray.length;i++)
      {
        Cell c00 = rs.getCell(i,row);
        Str[i] = c00.getContents();
      }
    }
    catch (Exception e) {
      debug.printDug("Excel.getRowArray is error,this is errormessage="+e.getMessage());
      Str = null;
    }
    finally {
      try {

      }
      catch (Exception e) {
        Str = null;
      }

    }
    return Str ;

  }

  /**
   * �������壨��11����ʽ�� ����������ʽ����ɫ����С���Ӵ֡�б�塢�»��ߵȵ����á�
   * @parma int format ��ʽ���? int fontSize ������?
   * @return boolean
   */
  public void  setCellFont(int format,int fontSize)
  {
    if(format==0)
    {
      //���� ARIAL ��ɫ��
      wfc = new jxl.write.WritableFont(WritableFont.ARIAL, fontSize,
                                       WritableFont.NO_BOLD,false,
                                       UnderlineStyle.NO_UNDERLINE,
                                       jxl.format.Colour.RED);
    }
    else if(format==1)
    {
      //���� ARIAL ��ɫ��,�Ӵ�,���»���,б��
      wfc = new jxl.write.WritableFont(WritableFont.ARIAL, fontSize,
                                       WritableFont.BOLD,true,
                                       UnderlineStyle.SINGLE,
                                       jxl.format.Colour.RED);
    }
    else if(format==2)
    {
      //���� TAHOMA ��ɫ��
      wfc = new jxl.write.WritableFont(WritableFont.TAHOMA, fontSize,
                                       WritableFont.NO_BOLD,false,
                                       UnderlineStyle.NO_UNDERLINE,
                                       jxl.format.Colour.GREEN);
    }
    else if(format==3)
    {
      //���� TAHOMA ��ɫ��,�Ӵ�,���»���,б��
      wfc = new jxl.write.WritableFont(WritableFont.TAHOMA, fontSize,
                                       WritableFont.BOLD,true,
                                       UnderlineStyle.SINGLE,
                                       jxl.format.Colour.GREEN);
    }
    else if(format==4)
    {
      //���� TIMES ��ɫ��
      wfc = new jxl.write.WritableFont(WritableFont.TIMES, fontSize,
                                       WritableFont.NO_BOLD,false,
                                       UnderlineStyle.NO_UNDERLINE,
                                       jxl.format.Colour.BLUE);
    }
    else if(format==5)
    {
      //���� TAHOMA ��ɫ��,�Ӵ�,���»���,б��
      wfc = new jxl.write.WritableFont(WritableFont.TIMES, fontSize,
                                       WritableFont.BOLD,true,
                                       UnderlineStyle.SINGLE,
                                       jxl.format.Colour.BLUE);
    }
    else if(format==6)
    {
      //���� COURIER ��ɫ����
      wfc = new jxl.write.WritableFont(WritableFont.COURIER, fontSize,
                                       WritableFont.NO_BOLD,false,
                                       UnderlineStyle.NO_UNDERLINE,
                                       jxl.format.Colour.DARK_BLUE);
    }
    else if(format==7)
    {
      //���� COURIER ��ɫ����,�Ӵ�,���»���,б��
      wfc = new jxl.write.WritableFont(WritableFont.COURIER, fontSize,
                                       WritableFont.BOLD,true,
                                       UnderlineStyle.SINGLE,
                                       jxl.format.Colour.DARK_BLUE);
    }
    else if(format==8)
    {
       //���� ARIAL ��ɫ���?,�Ӵ�,���»���,б��
      wfc = new jxl.write.WritableFont(WritableFont.ARIAL, fontSize,
                                       WritableFont.BOLD,true,
                                       UnderlineStyle.SINGLE,
                                       jxl.format.Colour.DARK_RED);
    }
    else if(format==9)
    {
       //���� TAHOMA ��ɫ��,�Ӵ�,���»���,б��
      wfc = new jxl.write.WritableFont(WritableFont.TAHOMA, fontSize,
                                       WritableFont.BOLD,true,
                                       UnderlineStyle.SINGLE,
                                       jxl.format.Colour.BLACK);
    }
    else
    {
       //���� TAHOMA ��ɫ��
      wfc = new jxl.write.WritableFont(WritableFont.TAHOMA, fontSize,
                                       WritableFont.NO_BOLD,false,
                                       UnderlineStyle.NO_UNDERLINE,
                                       jxl.format.Colour.BLACK);
    }
  }




  /**
   * ���õ���cell�ı���ɫ
   * @parma int col �к� int row �к� String colorStr ����ɫ
   * @return boolean
   */
  public void setCellBgcolor(String colorStr) {
    boolean Rtn = false;
    try {

      wcfFC = new jxl.write.WritableCellFormat(wfc);
      if (colorStr == "red") {
        wcfFC.setBackground(jxl.format.Colour.RED);

      }
      else if (colorStr == "green") {
        wcfFC.setBackground(jxl.format.Colour.GREEN);

      }
      else if (colorStr == "blue") {
        wcfFC.setBackground(jxl.format.Colour.BLUE);

      }
      else if (colorStr == "yellow") {
        wcfFC.setBackground(jxl.format.Colour.YELLOW);

      }
      else if (colorStr == "brown") {
        wcfFC.setBackground(jxl.format.Colour.BROWN);

      }
      else if (colorStr == "dark_blue") {
        wcfFC.setBackground(jxl.format.Colour.DARK_BLUE);
      }
      else {
        wcfFC.setBackground(jxl.format.Colour.BLACK);
      }

    }
    catch (Exception e) {
      Rtn = false;
      debug.printDug("Excel.setCellBgcolor is error,this is errormessage="+e.getMessage());
    }
    finally {
      try {

      }
      catch (Exception e) {}
    }

  }
  /**
   * ָ����ȡ�ڼ��Ź����������ݡ�
   * @parma int sheetIndex ��������
   * @return boolean
   */

  public boolean readExcelSheetIndex(int sheetIndex)
  {
    boolean Rtn=false;
    try{
      rs = rwb.getSheet(sheetIndex);
      Rtn=true;
    }
    catch(Exception e)
    {
    	e.printStackTrace();
        Rtn=false;
    }
    return Rtn;
  }

  /**
   * ȡ��ĳ����Ԫ������ݡ����۵�Ԫ���Ǻ���������Ͷ��������ַ��͡�
   * @parma int col �к� int row �к�
   * @return String
   */
  public String getCellContent(int row, int col) {
    String cellContent = "";
    try {
      //Ĭ�ϴ򿪵�һ�Ź�������
      //Sheet rs = rwb.getSheet(0);
      //ȡ��ĳһ��Ԫ�������?
      Cell c00 = rs.getCell(col, row);
      cellContent = c00.getContents();
    }
    catch (Exception e) {
      debug.printDug("Excel.getCellContent is error,this is errormessage="+e.getMessage());
      cellContent = "";
    }
    finally {
      try {

      }
      catch (Exception e) {
        cellContent = "";
      }
    }
    return cellContent;
  }


  /**
   * ȡ��ĳ����Ԫ��������͡�û�������sheetIndex,Ĭ��ȡ��һ��������
   * @parma int col �к� int row �к�
   * @return String
   */
  public String getCellType(int row, int col) {
    String typeStr = "";
    try {
      //��ȡ��һ�Ź�����
      //Sheet rs = rwb.getSheet(0);
      //��õ�Ԫ�����
      Cell c00 = rs.getCell(col, row);
      //�жϵ�Ԫ�������?, ������Ӧ��ת��
      if (c00.getType() == CellType.LABEL) {
        typeStr = "String";
      }
      if (c00.getType() == CellType.DATE) {
        typeStr = "Date";
      }
      if (c00.getType() == CellType.NUMBER) {
        typeStr = "Number";
      }
    }
    catch (Exception e) {
      debug.printDug("Excel.getCellType is error,this is errormessage="+e.getMessage());
      typeStr = "";
    }
    finally {
      try {

      }
      catch (Exception e) {
        typeStr = "";
      }
    }
    return typeStr;
  }

  /**
   * ȡ��ĳ����Ԫ���ַ����ݡ�û�������sheetIndex,Ĭ��ȡ��һ��������
   * @parma int col �к� int row �к�
   * @return String
   */
  public String getCellTex(int row, int col) {
    String cellContent = "";
    try {
      //Ĭ�ϴ򿪵�һ�Ź�������
      //Sheet rs = rwb.getSheet(0);
      Cell c00 = rs.getCell(col, row);
      LabelCell labelc00 = (LabelCell) c00;
      cellContent = labelc00.getString();
    }
    catch (Exception e) {
      debug.printDug("Excel.getCellTex is error,this is errormessage="+e.getMessage());
      cellContent = "";
    }
    finally {
      try {

      }
      catch (Exception e) {
        cellContent = "";
      }
    }
    return cellContent;
  }



  /**
   * ȡ��ĳ����Ԫ���������ݡ�û�������sheetIndex,Ĭ��ȡ��һ��������
   * @parma int col �к� int row �к�
   * @return String
   */
  public double getCellNum(int row, int col) {
    double cellContent = 0;
    try {
      //Ĭ�ϴ򿪵�һ�Ź�������
      //Sheet rs = rwb.getSheet(0);
      Cell c10 = rs.getCell(col, row);
      NumberCell numc10 = (NumberCell) c10;
      cellContent = numc10.getValue();
    }
    catch (Exception e) {
      debug.printDug("Excel.getCellNum is error,this is errormessage="+e.getMessage());
      cellContent = 0;
    }
    finally {
      try {

      }
      catch (Exception e) {
        cellContent = 0;
      }
    }
    return cellContent;
  }


  /**
   * ȡ��ĳ����Ԫ���������ݡ�û�������sheetIndex,Ĭ��ȡ��һ��������
   * @parma int col �к� int row �к�
   * @return String
   */
  public Date getCellDate(int row, int col) {
    Date cellContent = null;
    try {
      //Ĭ�ϴ򿪵�һ�Ź�������
      //Sheet rs = rwb.getSheet(0);
      Cell c00 = rs.getCell(col, row);
      DateCell labeldate00 = (DateCell) c00;
      cellContent = labeldate00.getDate();
    }
    catch (Exception e) {
      cellContent = null;
    }
    finally {
      try {

      }
      catch (Exception e) {
        cellContent = null;
      }
    }
    return cellContent;
  }




  /**
   * ��ȡָ��·���µ�Excel�ļ�����ݡ�û�������sheetIndex,Ĭ��ȡ��һ�Ź�����
   * @parma
   * @return String[][] ���ض�ά����
   */
  public String[][] readExcel() {
    String[][] strTemp = null;
    try {
      //Sheet rs = rwb.getSheet(0);
      int rows = rs.getRows();
      int cols = rs.getColumns();
      strTemp = new String[rows][cols];
      for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
          Cell ctemp = rs.getCell(j, i);
          strTemp[i][j] = ctemp.getContents();
        }
      }

    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
    finally {
      try {

      }
      catch (Exception e) {}
    }
    return strTemp;
  }



  public static String toCN(String strvalue) {
    try {
      if (strvalue == null)
        return null;
      else {
        strvalue = new String(strvalue.getBytes("gb2312"), "GBK");
        return strvalue;
      }
    }
    catch (Exception e) {
      return null;
    }
  }

  public static String DBtoCN(String strvalue) {
    try {
      if (strvalue == null)
        return null;
      else {
        strvalue = new String(strvalue.getBytes("ISO-8859-1"), "gb2312");
        return strvalue;
      }
    }
    catch (Exception e) {
      return null;
    }
  }

}
