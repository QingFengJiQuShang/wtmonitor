package com.jrsoft.fri.xtsz.action;

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
import com.jrsoft.fri.xtsz.entity.City;
import com.jrsoft.fri.xtsz.entity.Province;
import com.jrsoft.fri.xtsz.from.XtszFrom;
import com.jrsoft.fri.xtsz.service.CityService;
import com.jrsoft.fri.xtsz.service.ProvinceService;

public class CityAction  extends DispatchAction {
	private CityService cityService;
	private ProvinceService provinceService;

	public ProvinceService getProvinceService() {
		return provinceService;
	}

	public void setProvinceService(ProvinceService provinceService) {
		this.provinceService = provinceService;
	}
	public CityService getCityService() {
		return cityService;
	}

	public void setCityService(CityService cityService) {
		this.cityService = cityService;
	}

	/**
	 * 根据省 查询市
	 * @param request
	 * @param response
	 */
	public void query(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response ){
		String province=request.getParameter("provinceid");
		String hql=" where  1=1   and father='"+province+"' " ;
		List<City> list=cityService.queryAll(hql);
		JSONArray array=new JSONArray();
		
		for(int i=0;i<list.size();i++){
			JSONObject cell = new JSONObject();
			City c=list.get(i);
			cell.put("cityid", c.getCityid());		//
			cell.put("city",c.getCity());		//
			array.add(cell);
		}
		JsonUtil.ajaxOutPutJson(response, array);
	}

	/**
	 * 省 列表
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	public ActionForward queryList(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response ) throws Exception{
		
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String title=request.getParameter("title");
		String provinceid=request.getParameter("id");
		String num=request.getParameter("num");   //当前页
		Page  page=new Page();
		
		if(num!=null&&!num.equals("")){
			page.setPageNum(Integer.parseInt(num));//当前页数
		}else{
			page.setPageNum(0);//当前页数
		}
		
		List<City> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//查询日志
				String sql="select de.* from City de  where  1=1 and father='"+provinceid+"'" ;
				if(title!=null&&!title.equals("")){
					sql+=" and de.City like '%"+title+"%'";
				}
				
				sql+=" order by de.id ";	
				String sql1="select * from ( select a.*,rownum rn from ("+sql+") a where rownum<="+page.getPageSize() * (page.getPageNum() +1)+") where rn>="+(page.getPageSize() * page.getPageNum()+1);
				int siz=	DBEntity.getInstance().queryCount(sql);
				page.setCount(siz);//总记录数
				page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//总页数	

				PreparedStatement sta = conn.prepareStatement(sql1);
				ResultSet rs = sta.executeQuery();
				list=new ArrayList<City>();
				while(rs.next()){
					City City=new City();
					City.setId(rs.getLong("id"));
					City.setCityid(rs.getString("Cityid"));
					City.setCity(rs.getString("City"));
					list.add(City);
					
				}
				request.setAttribute("page", page);
				request.setAttribute("provinceid", provinceid);
				request.setAttribute("list", list);
				return	new ActionForward("/jsp/xtsz/position/cityList.jsp");

	}

	/**
	 * 新增 市信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  addEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		XtszFrom from=(XtszFrom)form;
		City elevator =from.getCity();
		
		cityService.save(elevator);
		//生成 操作日志
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "添加了"+elevator.getCity(), "1");
	    return	new ActionForward("/cityAction.do?method=queryList");
	}
	

	/**
	 * 编辑 查看 市
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  findById(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String id=request.getParameter("id");
		String flag=request.getParameter("flag");
		City list=cityService.get(Long.parseLong(id));
		request.setAttribute("list", list);
		if(flag.equals("1")){
			return	new ActionForward("/jsp/xtsz/help/updateHelp.jsp");
		}else{
			return	new ActionForward("/jsp/xtsz/help/detailHelp.jsp");
		}
	}
	
	/**
	 * 修改市
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  updateEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		
		XtszFrom from=(XtszFrom)form;
		City elevator =from.getCity();
		City entity =cityService.get(elevator.getId());
		
		if(entity.getId()!=null){
			entity.setCity(elevator.getCity());
			entity.setCityid(elevator.getCityid());
			cityService.update(entity);
		}
		//生成 操作日志
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "修改了"+elevator.getCity(), "1");
		return	new ActionForward("/cityAction.do?method=queryList");
	}
	
	/**
	 * 删除电梯
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  delEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		Long id=Long.parseLong(request.getParameter("id"));
		City entity =cityService.get(id);
		cityService.delete(id);
		//生成 操作日志
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "删除了："+entity.getCity(), "1");
		 return	new ActionForward("/cityAction.do?method=queryList");
	}
	/**
	 * 批量 删除 市
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
				City entity =cityService.get(Long.parseLong(arr[i]));
				cityService.delete(Long.parseLong(arr[i]));
				//生成 操作日志
				XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
				Log log=new Log();
		        log.addLog(user.getName(), "删除了"+entity.getCity(), "1");
			}
		}
			return	new ActionForward("/cityAction.do?method=queryList");

	}
	

}
