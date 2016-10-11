package com.jrsoft.fri.dagl.action;

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
import com.jrsoft.fri.dagl.entity.DaxxRegion;
import com.jrsoft.fri.dagl.from.DaglFrom;
import com.jrsoft.fri.dagl.from.Page;
import com.jrsoft.fri.dagl.service.DaxxRegionService;

public class DaxxRegionAction extends DispatchAction {
	private DaxxRegionService regionService;

	public DaxxRegionService getRegionService() {
		return regionService;
	}

	public void setRegionService(DaxxRegionService regionService) {
		this.regionService = regionService;
	}
	
	
	/**
	 * 新增区域
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  addEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		DaglFrom daglFrom=(DaglFrom)form;
		DaxxRegion region =daglFrom.getRegion();
		
		regionService.save(region);
		System.out.println("添加成功");
	    return	new ActionForward("/regionAction.do?method=query");
	}
	/**
	 * 查询 区域列表
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public ActionForward  query(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		String reg=request.getParameter("reg");
		String client=request.getParameter("client");
		
		 if(reg!=null){
			 reg=new String(reg.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(client!=null){
			 client=new String(client.getBytes("iso-8859-1"),"utf-8");
		 }
		String num=request.getParameter("num");   //当前页
		

		Page  page=new Page();
		String hql=" where  1=1 " ;
		if(reg!=null&&!reg.equals("")){
			hql+=" and region like '%"+reg+"%'";
		}
		if(client!=null&&!client.equals("")){
			hql+=" and clientId like '%"+client+"%'";
		}
		hql+="order by id ";
		List<DaxxRegion> daxxRegions=regionService.queryAll(hql);
		
		page.setPageSize(3);	//每页显示数
		if(num!=null&&!num.equals("")){
			page.setPageNum(Integer.parseInt(num));//当前页数
		}else{
			page.setPageNum(0);//当前页数
		}
		page.setCount(daxxRegions.size());//总记录数
		page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//总页数	
		
		List<DaxxRegion> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//查询服务订单
				String sql="select dr.*  from daxx_region dr where  1=1 " ;
						
				if(reg!=null&&!reg.equals("")){
					sql+=" and region like '%"+reg+"%'";
				}
				if(client!=null&&!client.equals("")){
					sql+=" and client_id like '%"+client+"%'";
				}
				sql+=" order by id";	
				String sql1="select * from ( select a.*,rownum rn from ("+sql+") a where rownum<="+page.getPageSize() * (page.getPageNum() +1)+") where rn>="+(page.getPageSize() * page.getPageNum()+1);
				
				PreparedStatement sta = conn.prepareStatement(sql1);
				ResultSet rs = sta.executeQuery();
				list=new ArrayList<DaxxRegion>();
				while(rs.next()){
					DaxxRegion region=new DaxxRegion();
					region.setId(rs.getLong("id"));
					region.setRegion(rs.getString("region"));
					region.setClientId(rs.getLong("client_id"));
					list.add(region);
					
				}
				
//				sta.close();
//				rs.close();
//				conn.close();
				request.setAttribute("reg", reg);
				request.setAttribute("client", client);
				request.setAttribute("page", page);
				request.setAttribute("list", list);
		
		
		 return	new ActionForward("/jsp/dagl/region/regionList.jsp");
		}
	
	/**
	 * 编辑 查看 区域 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  findById(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String id=request.getParameter("id");
		DaxxRegion list=regionService.get(Long.parseLong(id));
		request.setAttribute("list", list);
		return	new ActionForward("/jsp/dagl/region/updateRegion.jsp");
	}
	
	/**
	 * 修改区域
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  updateEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		DaglFrom daglFrom=(DaglFrom)form;
		DaxxRegion region =daglFrom.getRegion();
		DaxxRegion daxxRegion=regionService.get(region.getId());
		
		if(daxxRegion!=null){
			daxxRegion.setRegion(region.getRegion());
			daxxRegion.setClientId(region.getClientId());
			regionService.update(daxxRegion);
		}
		return	new ActionForward("/regionAction.do?method=query");
	}
	
	
	
	
	/**
	 * 删除区域
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  delEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		Long id=Long.parseLong(request.getParameter("id"));
		regionService.delete(id);
		 return	new ActionForward("/regionAction.do?method=query");
	}
	/**
	 * 批量 删除 区域
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  deleteEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String ids=request.getParameter("ids");
		if(ids!=null&&!ids.equals("")){
			String  arr []=ids.split(",");
			for(int i=0;i<arr.length;i++){
				regionService.delete(Long.parseLong(arr[i]));
			}
		}
		 return	new ActionForward("/regionAction.do?method=query");
		
	}
	
	/**
	 * 导出 区域信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  export(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {

		String reg=request.getParameter("reg");
		String client=request.getParameter("client");
		DaxxRegion region=new DaxxRegion();
		 if(reg!=null){
			 reg=new String(reg.getBytes("iso-8859-1"),"utf-8");
			 region.setRegion(reg);
		 }
		 if(client!=null){
			 client=new String(client.getBytes("iso-8859-1"),"utf-8");
			 region.setClientId(Long.parseLong(client));
		 }
		 
		 
		 
		try {
			String dates = new SimpleDateFormat("yyyyMMddHHmmss")
					.format(new Date());
			String fileName = "区域信息" + dates + ".xls";
			String filePath = request.getRealPath("/")
					+ "execl\\" + fileName;
			// 生成excel文件
			regionService.export(filePath, region);

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

	
		 return	new ActionForward("/regionAction.do?method=query");
		
	}

}
