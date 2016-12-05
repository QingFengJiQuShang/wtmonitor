package com.jrsoft.fri.xtsz.action;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import smart.sys.platform.dao.DBEntity;

import com.jrsoft.fri.dtjk.entity.DtjkElevator;
import com.jrsoft.fri.dtjk.entity.DtjkPhone;
import com.jrsoft.fri.gzcl.entity.GzclFault;
import com.jrsoft.fri.xtgl.entity.XtglMaintenanceUnit;
import com.jrsoft.fri.xtgl.entity.XtglUseUnit;
import com.jrsoft.fri.xtgl.entity.XtglUsers;
import com.jrsoft.fri.xtgl.from.Page;
import com.jrsoft.fri.xtsz.entity.XtszLog;
import com.jrsoft.fri.xtsz.service.XtszLogService;

public class XtszLogAction extends DispatchAction {
	private XtszLogService logService;

	public XtszLogService getLogService() {
		return logService;
	}

	public void setLogService(XtszLogService logService) {
		this.logService = logService;
	}

	/**
	 * 查询 操作日志
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public ActionForward  query(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String flag=request.getParameter("flag");
		String username=request.getParameter("username");
		String begintime=request.getParameter("begintime");
		String endtime=request.getParameter("endtime");
		
		if(username!=null){
			username=new String(username.getBytes("iso-8859-1"),"utf-8");
		 }

		String num=request.getParameter("num");   //当前页
		Page  page=new Page();
		
		if(num!=null&&!num.equals("")){
			page.setPageNum(Integer.parseInt(num));//当前页数
		}else{
			page.setPageNum(0);//当前页数
		}
		page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//总页数	
		
		List<XtszLog> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//查询日志
				String sql="select de.* from xtsz_log de  where  1=1 " ;
				if(username!=null&&!username.equals("")){
					sql+=" and de.user_Name like '%"+username+"%'";
				}
				if(begintime!=null&&!begintime.equals("")){
					sql+=" and de.found_Time  >=to_date('" + begintime+ "','yyyy-MM-dd hh24:mi:ss')";
				}
				if(endtime!=null&&!endtime.equals("")){
					sql+=" and de.found_Time  <=to_date('" + endtime+ "','yyyy-MM-dd hh24:mi:ss')";
				}
				if(flag.equals("0")){
					sql+=" and de.flag  ='" + flag+ "' or de.flag is null ";
				}else{
					sql+=" and de.flag  ='" + flag+ "' ";
				}
				
				sql+=" order by de.found_Time desc";	
				String sql1="select * from ( select a.*,rownum rn from ("+sql+") a where rownum<="+page.getPageSize() * (page.getPageNum() +1)+") where rn>="+(page.getPageSize() * page.getPageNum()+1);
				int siz=	DBEntity.getInstance().queryCount(sql);
				page.setCount(siz);//总记录数
				PreparedStatement sta = conn.prepareStatement(sql1);
				ResultSet rs = sta.executeQuery();
				list=new ArrayList<XtszLog>();
				while(rs.next()){
					XtszLog elevator=new XtszLog();
					elevator.setId(rs.getLong("id"));
					elevator.setFoundTime(rs.getString("found_Time")==null?null:df.parse(rs.getString("found_Time")));
					elevator.setUserName(rs.getString("user_Name"));
					elevator.setContent(rs.getString("content"));
					list.add(elevator);
					
				}
				request.setAttribute("username", username);
				if(endtime!=null){
					request.setAttribute("endtime", df.parse( endtime));
				}
				if(begintime!=null){
					request.setAttribute("begintime", df.parse( begintime));
				}
				request.setAttribute("flag", flag);
				request.setAttribute("page", page);
				request.setAttribute("list", list);
		
		
		 return	new ActionForward("/jsp/xtsz/log/logList.jsp");
		}
	

	/**
	 * 编辑 查看 网关 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  findById(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String id=request.getParameter("id");
		XtszLog list=logService.get(Long.parseLong(id));
		request.setAttribute("list", list);
		return	new ActionForward("/jsp/xtsz/log/detailLog.jsp");
		
	}

	/**
	 * 导出 操作日志信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  export(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {

		String username=request.getParameter("username");
		String begintime=request.getParameter("begintime");
		String endtime=request.getParameter("endtime");
		String flag=request.getParameter("flag");
		XtszLog fault=new XtszLog();
		 if(username!=null)
			 fault.setUserName(username);
		 if(begintime!=null)
			 fault.setBegintime(begintime);
		 if(endtime!=null)	 
			 fault.setEndtime(endtime);
		 if(flag!=null)	 
			 fault.setFlag(flag);
		try {
			String dates = new SimpleDateFormat("yyyyMMddHHmmss")
					.format(new Date());
			String fileName = "操作日志信息" + dates + ".xls";
			String filePath = request.getRealPath("/")
					+ "excel\\" + fileName;
			// 生成excel文件
			logService.export(filePath, fault);

			// 下载excel
			BufferedOutputStream bos = null;
			StringBuffer sb = new StringBuffer(50);
			sb.append("attachment;   filename=");
			sb.append(fileName);
			// System.out.println("----------sb="+sb);

			if (null != filePath && fileName != null) {
				response.setContentType("application/x-msdownload;charset=GBK");
				response.setHeader("Content-Disposition", new String(sb
						.toString().getBytes(), "ISO8859-1"));
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
				System.out.println("删除单个文件" + fileName + "成功！");
			} else {
				System.out.println("删除单个文件" + fileName + "失败！");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
