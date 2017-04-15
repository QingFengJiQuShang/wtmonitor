package com.jrsoft.fri.xtsz.action;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import smart.sys.platform.dao.DBEntity;

import com.jrsoft.fri.common.utils.JsonUtil;
import com.jrsoft.fri.xtgl.entity.XtglUsers;
import com.jrsoft.fri.xtgl.from.Page;
import com.jrsoft.fri.xtsz.entity.Area;
import com.jrsoft.fri.xtsz.entity.City;
import com.jrsoft.fri.xtsz.entity.Province;
import com.jrsoft.fri.xtsz.from.XtszFrom;
import com.jrsoft.fri.xtsz.service.AreaService;
import com.jrsoft.fri.xtsz.service.CityService;
import com.jrsoft.fri.xtsz.service.ProvinceService;

public class AreaAction extends DispatchAction {
	private AreaService areaService;
	private CityService cityService;
	private ProvinceService provinceService;
	public AreaService getAreaService() {
		return areaService;
	}

	public void setAreaService(AreaService areaService) {
		this.areaService = areaService;
	}

	public CityService getCityService() {
		return cityService;
	}

	public void setCityService(CityService cityService) {
		this.cityService = cityService;
	}

	public ProvinceService getProvinceService() {
		return provinceService;
	}

	public void setProvinceService(ProvinceService provinceService) {
		this.provinceService = provinceService;
	}
	/**
	 * 根据市 查询区
	 * @param request
	 * @param response
	 */
	public void query(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response ){
		String city=request.getParameter("cityid");
		String hql=" where  1=1  and father='"+city+"' " ;
		List<Area> list=areaService.queryAll(hql);
		JSONArray array=new JSONArray();
		
		for(int i=0;i<list.size();i++){
			JSONObject cell = new JSONObject();
			Area c=list.get(i);
			cell.put("areaid", c.getAreaid());		//
			cell.put("area",c.getArea());		//
			array.add(cell);
		}
		JsonUtil.ajaxOutPutJson(response, array);
	}
	
	/**
	 * 根据查询树形菜单
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	public ActionForward queryTree(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response ) throws IOException{
		
		String hql="  where 1=1 ";
		List<Province> list=provinceService.queryAll(hql);
		
		//查询所有省
		JSONArray array=new JSONArray();
		for(int i=0;i<list.size();i++){
			JSONObject cell = new JSONObject();
			cell.put("text",list.get(i).getProvince());
			cell.put("flag","1");
			cell.put("id",list.get(i).getProvinceid());
			//查询所有市
			String hql1="  where 1=1 and father='"+list.get(i).getProvinceid()+"'";
			List<City> list1=cityService.queryAll(hql1);
			JSONArray array1=new JSONArray();
			for (int j = 0; j < list1.size(); j++) {
				JSONObject cell1 = new JSONObject();
				cell1.put("text",list1.get(j).getCity());
				cell1.put("flag","2");
				cell1.put("id",list1.get(j).getCityid());
				//查询区县
				String hql2=" where  1=1  and father='"+list1.get(j).getCityid()+"' " ;
				List<Area> list2=areaService.queryAll(hql2);
				JSONArray array2=new JSONArray();
				for (int k = 0; k < list2.size(); k++) {
					JSONObject cell2 = new JSONObject();
					cell2.put("text",list2.get(k).getArea());
					cell2.put("flag","3");
					cell2.put("id",list2.get(k).getAreaid());
					array2.add(cell2);
				}
				cell1.put("nodes",array2);
				array1.add(cell1);
			}
			cell.put("nodes",array1);
			
			array.add(cell);
		}
		System.out.println(array.toString());
		request.setAttribute("array", array);
		return	new ActionForward("/jsp/xtsz/position/ctiy.jsp");
	}

	/**
	 * 县区 列表
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	public ActionForward queryList(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response ) throws Exception{
		
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String title=request.getParameter("title");
		String num=request.getParameter("num");   //当前页
		String cityid=request.getParameter("id");

		Page  page=new Page();
		
		if(num!=null&&!num.equals("")){
			page.setPageNum(Integer.parseInt(num));//当前页数
		}else{
			page.setPageNum(0);//当前页数
		}
		
		List<Area> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//查询日志
				String sql="select de.* from Area de  where  1=1 and father='"+cityid+"'" ;
				if(title!=null&&!title.equals("")){
					sql+=" and de.Area like '%"+title+"%'";
				}
				
				sql+=" order by de.id ";	
				String sql1="select * from ( select a.*,rownum rn from ("+sql+") a where rownum<="+page.getPageSize() * (page.getPageNum() +1)+") where rn>="+(page.getPageSize() * page.getPageNum()+1);
				int siz=	DBEntity.getInstance().queryCount(sql);
				page.setCount(siz);//总记录数
				page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//总页数	

				PreparedStatement sta = conn.prepareStatement(sql1);
				ResultSet rs = sta.executeQuery();
				list=new ArrayList<Area>();
				while(rs.next()){
					Area Area=new Area();
					Area.setId(rs.getLong("id"));
					Area.setAreaid(rs.getString("Areaid"));
					Area.setArea(rs.getString("Area"));
					list.add(Area);
					
				}
				request.setAttribute("cityid", cityid);
				request.setAttribute("page", page);
				request.setAttribute("list", list);
				return	new ActionForward("/jsp/xtsz/position/areaList.jsp");

	}

	/**
	 * 新增 县区信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  addEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		XtszFrom from=(XtszFrom)form;
		Area elevator =from.getArea();
		
		areaService.save(elevator);
		//生成 操作日志
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "添加了"+elevator.getArea(), "1");
	    return	new ActionForward("/areaAction.do?method=queryList");
	}
	

	/**
	 * 编辑 查看 县区
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  findById(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String id=request.getParameter("id");
		String flag=request.getParameter("flag");
		Area list=areaService.get(Long.parseLong(id));
		request.setAttribute("list", list);
		if(flag.equals("1")){
			return	new ActionForward("/jsp/xtsz/position/updateArea.jsp");
		}else{
			return	new ActionForward("/jsp/xtsz/position/detailArea.jsp");
		}
	}
	
	/**
	 * 修改县区
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  updateEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		
		XtszFrom from=(XtszFrom)form;
		Area elevator =from.getArea();
		Area entity =areaService.get(elevator.getId());
		
		if(entity.getId()!=null){
			entity.setArea(elevator.getArea());
			entity.setAreaid(elevator.getAreaid());
			areaService.update(entity);
		}
		//生成 操作日志
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "修改了"+elevator.getArea(), "1");
		return	new ActionForward("/areaAction.do?method=queryList");
	}
	
	/**
	 * 删除县区
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  delEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		Long id=Long.parseLong(request.getParameter("id"));
		Area entity =areaService.get(id);
		areaService.delete(id);
		//生成 操作日志
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "删除了："+entity.getArea(), "1");
		 return	new ActionForward("/areaAction.do?method=queryList");
	}
	/**
	 * 批量 删除 县区
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
				Area entity =areaService.get(Long.parseLong(arr[i]));
				areaService.delete(Long.parseLong(arr[i]));
				//生成 操作日志
				XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
				Log log=new Log();
		        log.addLog(user.getName(), "删除了"+entity.getArea(), "1");
			}
		}
			return	new ActionForward("/areaAction.do?method=queryList");

	}
	
}
