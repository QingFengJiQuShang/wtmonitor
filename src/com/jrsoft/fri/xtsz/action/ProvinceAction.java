package com.jrsoft.fri.xtsz.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import com.jrsoft.fri.dtjk.entity.DtjkElevator;
import com.jrsoft.fri.xtgl.entity.XtglUsers;
import com.jrsoft.fri.xtgl.from.Page;
import com.jrsoft.fri.xtsz.entity.Province;
import com.jrsoft.fri.xtsz.entity.XtszHelp;
import com.jrsoft.fri.xtsz.from.XtszFrom;
import com.jrsoft.fri.xtsz.service.ProvinceService;

public class ProvinceAction  extends DispatchAction {
	private ProvinceService provinceService;

	public ProvinceService getProvinceService() {
		return provinceService;
	}

	public void setProvinceService(ProvinceService provinceService) {
		this.provinceService = provinceService;
	}
	
	/**
	 * ʡ �б�
	 * @param request
	 * @param response
	 */
	public void query(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response ){
		String hql=" where  1=1   " ;
		List<Province> list=provinceService.queryAll(hql);
		JSONArray array=new JSONArray();
		
		for(int i=0;i<list.size();i++){
			JSONObject cell = new JSONObject();
			Province province=list.get(i);
			cell.put("provinceid", province.getProvinceid());		//
			cell.put("province",province.getProvince());		//����ע���
			array.add(cell);
		}
		JsonUtil.ajaxOutPutJson(response, array);
	}
	/**
	 * ʡ �б�
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	public ActionForward queryList(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response ) throws Exception{
		
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String title=request.getParameter("title");
		String num=request.getParameter("num");   //��ǰҳ
		Page  page=new Page();
		
		if(num!=null&&!num.equals("")){
			page.setPageNum(Integer.parseInt(num));//��ǰҳ��
		}else{
			page.setPageNum(0);//��ǰҳ��
		}
		
		List<Province> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//��ѯ��־
				String sql="select de.* from province de  where  1=1 " ;
				if(title!=null&&!title.equals("")){
					sql+=" and de.province like '%"+title+"%'";
				}
				
				sql+=" order by de.id ";	
				String sql1="select * from ( select a.*,rownum rn from ("+sql+") a where rownum<="+page.getPageSize() * (page.getPageNum() +1)+") where rn>="+(page.getPageSize() * page.getPageNum()+1);
				int siz=	DBEntity.getInstance().queryCount(sql);
				page.setCount(siz);//�ܼ�¼��
				page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//��ҳ��	

				PreparedStatement sta = conn.prepareStatement(sql1);
				ResultSet rs = sta.executeQuery();
				list=new ArrayList<Province>();
				while(rs.next()){
					Province province=new Province();
					province.setId(rs.getLong("id"));
					province.setProvinceid(rs.getString("provinceid"));
					province.setProvince(rs.getString("province"));
					list.add(province);
					
				}
				request.setAttribute("page", page);
				request.setAttribute("list", list);
				return	new ActionForward("/jsp/xtsz/position/provinceList.jsp");

	}

	/**
	 * ���� ʡ��Ϣ
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  addEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		XtszFrom from=(XtszFrom)form;
		Province elevator =from.getProvince();
		
		provinceService.save(elevator);
		//���� ������־
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "�����"+elevator.getProvince(), "1");
	    return	new ActionForward("/provinceAction.do?method=queryList");
	}
	

	/**
	 * �༭ �鿴 ʡ
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  findById(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String id=request.getParameter("id");
		String flag=request.getParameter("flag");
		Province list=provinceService.get(Long.parseLong(id));
		request.setAttribute("list", list);
		if(flag.equals("1")){
			return	new ActionForward("/jsp/xtsz/position/updateProvince.jsp");
		}else{
			return	new ActionForward("/jsp/xtsz/position/detailProvince.jsp");
		}
	}
	
	/**
	 * �޸�ʡ
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  updateEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		
		XtszFrom from=(XtszFrom)form;
		Province elevator =from.getProvince();
		Province entity =provinceService.get(elevator.getId());
		
		if(entity.getId()!=null){
			entity.setProvince(elevator.getProvince());
			entity.setProvinceid(elevator.getProvinceid());
			provinceService.update(entity);
		}
		//���� ������־
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "�޸���"+elevator.getProvince(), "1");
		return	new ActionForward("/provinceAction.do?method=queryList");
	}
	
	/**
	 * ɾ��ʡ
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  delEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		Long id=Long.parseLong(request.getParameter("id"));
		Province entity =provinceService.get(id);
		provinceService.delete(id);
		//���� ������־
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "ɾ���ˣ�"+entity.getProvince(), "1");
		 return	new ActionForward("/provinceAction.do?method=queryList");
	}
	/**
	 * ���� ɾ�� ʡ
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
				Province entity =provinceService.get(Long.parseLong(arr[i]));
				provinceService.delete(Long.parseLong(arr[i]));
				//���� ������־
				XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
				Log log=new Log();
		        log.addLog(user.getName(), "ɾ����"+entity.getProvince(), "1");
			}
		}
			return	new ActionForward("/provinceAction.do?method=queryList");

	}
	
}
