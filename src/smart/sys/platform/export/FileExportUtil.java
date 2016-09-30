package smart.sys.platform.export;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import smart.sys.log.log.po.SysLogLoginfo;

public class FileExportUtil {
	public static BufferedOutputStream createCSVFile(List exportData,
			LinkedHashMap rowMapper, BufferedOutputStream bos)
			throws IOException {

		BufferedWriter csvFileOutputStream = null;
		csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(bos),
				1024);
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

			csvFileOutputStream.write("\"" + row.getUsername() + "\"");
			csvFileOutputStream.write(",");
			csvFileOutputStream.write("\"" + row.getSystemname() + "\"");
			csvFileOutputStream.write(",");
			csvFileOutputStream.write("\"" + row.getOperateTime() + "\"");
			csvFileOutputStream.write(",");
			csvFileOutputStream.write("\"" + row.getOperateDetail() + "\"");
			csvFileOutputStream.write(",");
			csvFileOutputStream.write("\"" + row.getIp() + "\"");
			csvFileOutputStream.write(",");
			csvFileOutputStream.write("\"" + row.getDescrip() + "\"");
			csvFileOutputStream.write(",");

			csvFileOutputStream.newLine();

		}
		csvFileOutputStream.flush();
		return bos;
	}
}
