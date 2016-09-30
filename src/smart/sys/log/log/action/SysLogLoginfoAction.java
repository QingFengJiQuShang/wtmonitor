package smart.sys.log.log.action;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import smart.sys.log.csv.CSVUtils;
import smart.sys.log.log.po.SysLogArchive;
import smart.sys.log.log.po.SysLogLoginfo;
import smart.sys.log.log.service.ISysLogLoginfoService;
import smart.sys.platform.export.FileExportUtil;



public class SysLogLoginfoAction extends DispatchAction{
	private ISysLogLoginfoService sysLogLoginfoService;
	public ISysLogLoginfoService getSysLogLoginfoService() {
		return sysLogLoginfoService;
	}
	public void setSysLogLoginfoService(ISysLogLoginfoService sysLogLoginfoService) {
		this.sysLogLoginfoService = sysLogLoginfoService;
	}
	public ActionForward test(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			sysLogLoginfoService.saveLog(3, "张三", "后台管理","新增用户[于静]成功.", request.getRemoteAddr(), "登录成功");
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String type=request.getParameter("type");
		String start=request.getParameter("start"); 
		String limit=request.getParameter("limit");
		int starti = 0;
		int limiti = 15;
		if(start!=null){
			starti = Integer.parseInt(start);
		}
		if(limit!=null){
			limiti = Integer.parseInt(limit);
		}
		List<SysLogLoginfo> logList = sysLogLoginfoService.findLogList(Integer.parseInt(type),starti,limiti);
		int count = sysLogLoginfoService.getCount(Integer.parseInt(type));
		SimpleDateFormat myFmt=new SimpleDateFormat("yyyy\u5e74MM\u6708dd\u65e5 HH:mm:ss");//年月日
		JSONArray array = new JSONArray();
		int i=1;
		for(SysLogLoginfo element:logList){
			JSONObject json = new JSONObject();
			json.put("autoId",element.getAutoId());
			String typeName="";
			int typeI = element.getType();
			
		    if(typeI==1){
		    		typeName="\u767b\u5f55/\u6ce8\u9500\u65e5\u5fd7";	//登录/注销日志
		    	}
		    if(typeI==2){
		    		typeName="\u5e94\u7528\u64cd\u4f5c\u65e5\u5fd7";	//应用操作日志
		    }
		    if(typeI==3){
		    		typeName="\u540e\u53f0\u64cd\u4f5c\u65e5\u5fd7";	//后台操作日志
		    }
		    if(typeI==4){
		    	typeName="\u5b89\u5168\u5907\u4efd\u65e5\u5fd7";		//安全备份日志
		    }
		    String time = "";
		    if(element.getOperateTime()!=null){
		    	time = myFmt.format(element.getOperateTime());
		    }
		    json.put("num", i++);
			json.put("type",typeName);
			json.put("subsys",element.getSystemname());
			json.put("content",element.getOperateDetail());
			json.put("user",element.getUsername());
			json.put("time",time);
			json.put("ip",element.getIp());
			json.put("count",count );
			//json.fromObject(element);
			array.add(json);
		}
		Map map=new HashMap(); 

		map.put("data", array); 

		map.put("count",count); 

		 JSONObject jalist=JSONObject.fromObject(map);
		//System.out.println(array);
		
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		out.print(jalist);
		//out.print(array);
		/*StringBuffer jsonString = new StringBuffer();
		jsonString.append("[");
		for(int i=0;i<logList.size();i++){
			SysLogLoginfo log = logList.get(i);
			jsonString.append("['"+log.getAutoId());
			jsonString.append("','"+log.getType());
			jsonString.append("','"+log.getSystemname());
			jsonString.append("','"+log.getOperateDetail());
			jsonString.append("','"+log.getUsername());
			jsonString.append("','"+log.getOperateTime());
			jsonString.append("','"+log.getIp());
			jsonString.append("'],");
		}
		jsonString.deleteCharAt(jsonString.length()-1);
		jsonString.append("]");
		
		System.out.println(jsonString.toString());
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonString.toString()); */
		
		return null;
		//return mapping.findForward("list");
	}
	/**
	 * 安全备份日志
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward safelist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String type=request.getParameter("type");
		String start=request.getParameter("start"); 
		String limit=request.getParameter("limit");
		int starti = 0;
		int limiti = 15;
		if(start!=null){
			starti = Integer.parseInt(start);
		}
		if(limit!=null){
			limiti = Integer.parseInt(limit);
		}
		List<SysLogLoginfo> logList = sysLogLoginfoService.findLogList(Integer.parseInt(type),starti,limiti);
		int count = sysLogLoginfoService.getCount(Integer.parseInt(type));
		SimpleDateFormat myFmt=new SimpleDateFormat("yyyy\u5e74MM\u6708dd\u65e5 HH:mm:ss");//年月日
		JSONArray array = new JSONArray();
		int i=1;
		for(SysLogLoginfo element:logList){
			JSONObject json = new JSONObject();
			json.put("autoId",element.getAutoId());
			String typeName="";
			int typeI = element.getType();
			
			if(typeI==1){
	    		typeName="\u767b\u5f55/\u6ce8\u9500\u65e5\u5fd7";	//登录/注销日志
		    }
		    if(typeI==2){
		    	typeName="\u5e94\u7528\u64cd\u4f5c\u65e5\u5fd7";	//应用操作日志
		    }
		    if(typeI==3){
		    	typeName="\u540e\u53f0\u64cd\u4f5c\u65e5\u5fd7";	//后台操作日志
		    }
		    if(typeI==4){
		    	typeName="\u5b89\u5168\u5907\u4efd\u65e5\u5fd7";	//安全备份日志
		    }
		    String time = "";
		    if(element.getOperateTime()!=null){
		    	time = myFmt.format(element.getOperateTime());
		    }
		    json.put("num",i++);
			json.put("type",typeName);
			json.put("scheme",element.getSystemname());
			json.put("result",element.getOperateDetail());
			json.put("time",time);
			json.put("size", element.getDescrip());
			
			//json.fromObject(element);
			array.add(json);
		}
		
		//System.out.println(array);
		Map map=new HashMap(); 

		map.put("data", array); 

		map.put("count",count); 

		JSONObject jalist=JSONObject.fromObject(map);
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		out.print(jalist);
		//out.print(array);
		return null;
	}
	/**
	 * 归档
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	
	public ActionForward fileElements(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		//String autoId_str = request.getParameter("autoIds");
		String type=request.getParameter("type");
		String[] autoIds =null;
		SysLogArchive arch = new SysLogArchive();
		Date maxtime = sysLogLoginfoService.findMaxTime(type);
		Date mintime = sysLogLoginfoService.findMinTime(type);
		List<SysLogLoginfo> logList = sysLogLoginfoService.findLogInfo(Integer.parseInt(type));
		arch.setType(Integer.parseInt(type));
		arch.setArchiveEnd(maxtime);
		arch.setArchiveStart(mintime);
		arch.setArchiveTime(new Date());
		arch.setDataCount((long)logList.size());
		//sysLogLoginfoService.save(arch);
		//sysLogLoginfoService.updateFile(type, arch.getAutoId());
		sysLogLoginfoService.saveArchive(arch, type);
		PrintWriter out=response.getWriter();
		//out.print(array);
		return null;
	}
	/**
	 * 归档日志列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward archivelist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String start=request.getParameter("start"); 
		String limit=request.getParameter("limit");
		int starti = 0;
		int limiti = 15;
		if(start!=null){
			starti = Integer.parseInt(start);
		}
		if(limit!=null){
			limiti = Integer.parseInt(limit);
		}
		List<SysLogArchive> logList = sysLogLoginfoService.findLogArchive(starti,limiti);
		int countArch = sysLogLoginfoService.getArchCount();
		JSONArray array = new JSONArray();
		SimpleDateFormat fo = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat myFmt2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int i=1;
		for(SysLogArchive element:logList){
			JSONObject json = new JSONObject();
			json.put("autoId",element.getAutoId());
			String typeName="";
			int typeI = element.getType();
			
			if(typeI==1){
	    		typeName="\u767b\u5f55/\u6ce8\u9500\u65e5\u5fd7";	//登录/注销日志
		    }
		    if(typeI==2){
		    	typeName="\u5e94\u7528\u64cd\u4f5c\u65e5\u5fd7";	//应用操作日志
		    }
		    if(typeI==3){
		    	typeName="\u540e\u53f0\u64cd\u4f5c\u65e5\u5fd7";	//后台操作日志
		    }
		    if(typeI==4){
		    	typeName="\u5b89\u5168\u5907\u4efd\u65e5\u5fd7";	//安全备份日志
		    }
		    String startDate="";
		    if(element.getArchiveStart()!=null){
		    	startDate = fo.format(element.getArchiveStart());
		    }
		    String endDate="";
		    if(element.getArchiveEnd()!=null){
		    	endDate = fo.format(element.getArchiveEnd());
		    }
		    String time="";
		    if(element.getArchiveTime()!=null){
		    	time = myFmt2.format(element.getArchiveTime());
		    }
		    json.put("num",i++);
			json.put("type",typeName);
			json.put("time",time);
			json.put("scale", startDate+"\u81f3"+endDate);//XX至XX
			json.put("count",element.getDataCount());
			
			//json.fromObject(element);
			array.add(json);
		}
		
		//System.out.println(array);
		
		Map map=new HashMap(); 

		map.put("data", array); 

		map.put("countArch",countArch); 

		JSONObject jalist=JSONObject.fromObject(map);
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		out.print(jalist);
		return null;
	}
	/**
	 * 归档日志列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward exportLog(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String autoId_str = request.getParameter("autoIds");
		String[] autoIds =null;
		if(autoId_str!=null&&!"".equals(autoId_str)){
			autoIds = autoId_str.split(",");
		}
		List<SysLogLoginfo> list = sysLogLoginfoService.findLogArchId(autoIds);
		LinkedHashMap map = new LinkedHashMap();
        map.put("1", "\u7528\u6237\u540d\u79f0");//用户名称
        map.put("2", "\u7cfb\u7edf\u540d\u79f0");//系统名称
        map.put("3", "\u64cd\u4f5c\u65f6\u95f4");//操作时间
        map.put("4", "\u64cd\u4f5c\u8be6\u7ec6");//操作详细
        map.put("5", "IP");//Ip
        map.put("6", "\u63cf\u8ff0");//描述
        
        BufferedOutputStream bos = null;
        StringBuffer sb = new StringBuffer(50);
		sb.append("attachment;   filename=");
		sb.append("log.csv");
		response.setContentType("application/x-msdownload;charset=UTF-8");
		response.setHeader("Content-Disposition", new String(sb.toString().getBytes(), "ISO8859-1"));
		bos = new BufferedOutputStream(response.getOutputStream());
		BufferedOutputStream eos = FileExportUtil.createCSVFile(list, map, bos);
		eos.close();
      //创建文件夹
		/*String path =request.getRealPath("/") + "logExport";
		java.io.File uploadFileDir = new java.io.File(path);
		判断上传文件夹是否存在
		if (!uploadFileDir.exists()) {
			uploadFileDir.mkdirs();
		}
		Date dates=new java.sql.Date(new java.util.Date().getTime());
        
        String filePath = path+"\\"+dates+"-Log.csv";
        String fileName = dates+"-Log.csv";
        request.setAttribute("filePath", filePath);
        request.setAttribute("fileName", fileName);
        
        CSVUtils.createCSVFile(list, map, path+"\\", dates+"-Log");
        
        JSONObject json = new JSONObject();
        json.put("path", filePath);
        
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		out.print(json.toString());*/
        //this.getResponse().getWriter().write("{'path' : '"+outPath+"'}");
       // request.getRequestDispatcher("/sysLogLoginfoAction.do?method=download").forward(request,response);
		return null;
	}
	/**
	 * 下载文件
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward download(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BufferedOutputStream bos = null;
		String filePath = (String) request.getAttribute("filePath");
		String filename = (String) request.getAttribute("fileName");
		System.out.println("----------filePath="+filePath+"； filename="+filename);
		StringBuffer sb = new StringBuffer(50);
		sb.append("attachment;   filename=");
		sb.append(filename);
//		System.out.println("----------sb="+sb);
		try {
			if (null != filePath && filename != null) {
				response.setContentType("application/x-msdownload;charset=GBK");
				response.setHeader("Content-Disposition", new String(sb.toString().getBytes(), "ISO8859-1"));
				FileInputStream fis = new FileInputStream(filePath);
				bos = new BufferedOutputStream(response.getOutputStream());
				byte[] buffer = new byte[2048];
				while (fis.read(buffer) != -1) {
					bos.write(buffer);
				}
				bos.write(buffer, 0, buffer.length);
				fis.close();
				bos.close();
			}
			File fs = new File(filePath);
			if (fs.isFile() && fs.exists()) {
				fs.delete();
				System.out.println("删除单个文件" + filename + "成功！");
			} else {
				System.out.println("删除单个文件" + filename + "失败！");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
