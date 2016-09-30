package smart.sys.log.csv;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import smart.sys.log.log.po.SysLogLoginfo;
public class CSVUtils {
	/**
	 * 导出csv文件
	 * @param exportData
	 * @param rowMapper
	 * @param outPutPath
	 * @param filename
	 * @return
	 */
    public static File createCSVFile(List exportData, LinkedHashMap rowMapper,
            String outPutPath, String filename) {

        File csvFile = null;
        BufferedWriter csvFileOutputStream = null;
        try {
            csvFile = new File(outPutPath + filename + ".csv");
            // csvFile.getParentFile().mkdir();
            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            csvFile.createNewFile();

            // GB2312使正确读取分隔符","
            csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(csvFile), "GB2312"), 1024);
            // 写入文件头部
            for (Iterator propertyIterator = rowMapper.entrySet().iterator(); propertyIterator
                    .hasNext();) {
                java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator
                        .next();
                csvFileOutputStream.write("\""
                        + propertyEntry.getValue().toString() + "\"");
                if (propertyIterator.hasNext()) {
                    csvFileOutputStream.write(",");
                }
            }
            csvFileOutputStream.newLine();
            // 写入文件内容
            for (Iterator iterator = exportData.iterator(); iterator.hasNext();) {
            	SysLogLoginfo row = (SysLogLoginfo) iterator.next();
                
                   
                    csvFileOutputStream.write("\""
                            + row.getAutoId()+ "\"");
                    csvFileOutputStream.write(",");
                    csvFileOutputStream.write("\""
                            + row.getType()+ "\"");
                    csvFileOutputStream.write(",");
                    csvFileOutputStream.write("\""
                            + row.getUsername()+ "\"");
                    csvFileOutputStream.write(",");
                    csvFileOutputStream.write("\""
                            + row.getSystemname()+ "\"");
                    csvFileOutputStream.write(",");
                    csvFileOutputStream.write("\""
                            + row.getOperateTime()+ "\"");
                    csvFileOutputStream.write(",");
                    csvFileOutputStream.write("\""
                            + row.getOperateDetail()+ "\"");
                    csvFileOutputStream.write(",");
                    csvFileOutputStream.write("\""
                            + row.getIp()+ "\"");
                    csvFileOutputStream.write(",");
                    csvFileOutputStream.write("\""
                            + row.getArchiveFlag()+ "\"");
                    csvFileOutputStream.write(",");
                    csvFileOutputStream.write("\""
                            + row.getArchiveId()+ "\"");
                    csvFileOutputStream.write(",");
                    csvFileOutputStream.write("\""
                            + row.getDescrip()+ "\"");
                    csvFileOutputStream.write(",");
                    
                    csvFileOutputStream.newLine();
                
            }
            csvFileOutputStream.flush();
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            try {
                csvFileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return csvFile;
    }
}
