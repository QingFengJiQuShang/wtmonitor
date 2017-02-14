package com.jrsoft.fri.bxgl.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import smart.sys.platform.dao.DBEntity;
import com.jrsoft.fri.bxgl.dao.BxglClaimDao;
import com.jrsoft.fri.bxgl.entity.BxglClaim;
import com.jrsoft.fri.bxgl.entity.BxglSafe;
import com.jrsoft.fri.bxgl.from.BxglFrom;
import com.jrsoft.fri.bxgl.service.BxglClaimService;
import com.jrsoft.fri.bxgl.service.BxglSafeService;
import com.jrsoft.fri.dtjk.entity.DtjkElevator;
import com.jrsoft.fri.dtjk.service.DtjkElevatorService;
import com.jrsoft.fri.xtgl.entity.XtglUsers;
import com.jrsoft.fri.xtgl.from.Page;
import com.jrsoft.fri.xtsz.action.Log;

public class BxglClaimAction   extends DispatchAction{
	private BxglSafeService safeService;
	private BxglClaimService claimService ;
	public BxglSafeService getSafeService() {
		return safeService;
	}
	public void setSafeService(BxglSafeService safeService) {
		this.safeService = safeService;
	}
	
	
	public BxglClaimService getClaimService() {
		return claimService;
	}
	public void setClaimService(BxglClaimService claimService) {
		this.claimService = claimService;
	}
	
	/**
	 * 新增电梯理赔记录信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  addEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String happenTime=request.getParameter("happenTime");
		String claimTime=request.getParameter("claimTime");
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		BxglFrom from=(BxglFrom)form;
		BxglClaim entity =from.getClaim();
		entity.setHappenTime(df.parse(happenTime));
		entity.setClaimTime(df.parse(claimTime));
		claimService.save(entity);
		
		//修改保单理赔状态
		BxglSafe elevator=safeService.get(entity.getSafeId().getId());
		if(!elevator.equals("是")){
			elevator.setState("是");
			safeService.update(elevator);
		}
		
		//生成 操作日志
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "添加电梯理赔记录", "1");
		
	    return	new ActionForward("/claimAction.do?method=query&safeId="+elevator.getId());
	}
	/**
	 * 查询 电梯理赔记录列表
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public ActionForward  query(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		String safeId=request.getParameter("safeId");
		String happenTime=request.getParameter("happenTime");
		String claimTime=request.getParameter("claimTime");
		String cause=request.getParameter("cause");
		
		String num=request.getParameter("num");   //当前页
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");

		Page  page=new Page();
		
		if(num!=null&&!num.equals("")){
			page.setPageNum(Integer.parseInt(num));//当前页数
		}else{
			page.setPageNum(0);//当前页数
		}
		
		List<BxglClaim> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//查询服务订单
				String sql="select de.*  " +
						" from Bxgl_Claim de " +			
						" left join Bxgl_Safe e on e.id=de.Safe_id "+  //电梯信息
						" where  1=1 " ;
				if(safeId!=null&&!safeId.equals("")){
					sql+=" and safe_id='"+safeId+"'";
				}
				if(cause!=null&&!cause.equals("")){
					cause=new String(cause.getBytes("ISO-8859-1"),"UTF-8");
					sql+=" and de.cause like '%"+cause+"%'";
				}
				if(happenTime!=null&&!happenTime.equals("")){
					sql+=" and de.happen_Time  =to_date('" + happenTime+ "','yyyy-MM-dd')";
				}
				if(claimTime!=null&&!claimTime.equals("")){
					sql+=" and de.claim_Time  =to_date('" + claimTime+ "','yyyy-MM-dd')";
				}
				sql+=" order by de.id desc";	
				String sql1="select * from ( select a.*,rownum rn from ("+sql+") a where rownum<="+page.getPageSize() * (page.getPageNum() +1)+") where rn>="+(page.getPageSize() * page.getPageNum()+1);
				int siz=	DBEntity.getInstance().queryCount(sql);
				page.setCount(siz);//总记录数
				page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//总页数	

				PreparedStatement sta = conn.prepareStatement(sql1);
				ResultSet rs = sta.executeQuery();
				list=new ArrayList<BxglClaim>();
				while(rs.next()){
					BxglClaim useUnit=new BxglClaim();
					useUnit.setId(rs.getLong("id"));
					useUnit.setCause(rs.getString("cause"));
					useUnit.setHappenTime(df.parse(rs.getString("happen_Time")));
					useUnit.setClaimTime(df.parse(rs.getString("claim_Time")));
					useUnit.setMoney(rs.getString("money"));
					list.add(useUnit);
					
				}
				
				
				request.setAttribute("safeId", safeId);
				request.setAttribute("cause", cause);
				if(happenTime!=null){
					request.setAttribute("happenTime", df.parse(happenTime));
				}
				if(claimTime!=null){
					request.setAttribute("claimTime", df.parse(claimTime));
				}
				request.setAttribute("page", page);
				request.setAttribute("list", list);
		
		
		 return	new ActionForward("/jsp/Insurance/claim/claimList.jsp");
		}
	

	/**
	 * 编辑 查看 电梯理赔记录
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  findById(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String id=request.getParameter("id");
		String flag=request.getParameter("flag");
		BxglClaim list=claimService.get(Long.parseLong(id));
		request.setAttribute("list", list);
		
		//查询图片路径
		List<String> str=new ArrayList<String>();
		if(list.getPicturePath()!=null&&!list.getPicturePath().equals("")){
			String  arr []=list.getPicturePath().split(",");
			for(int i=0;i<arr.length;i++){
				arr[i]=arr[i].replace("//", "/");
				str.add(arr[i]);
			}
			request.setAttribute("str", str);
		}
		
		
		
		
		if(flag.equals("1")){
			return	new ActionForward("/jsp/Insurance/claim/updateClaim.jsp");
		}else{
			return	new ActionForward("/jsp/Insurance/claim/detailClaim.jsp");

		}
	}
	
	/**
	 * 修改电梯理赔记录
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  updateEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String happenTime=request.getParameter("happenTime");
		String claimTime=request.getParameter("claimTime");
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		BxglFrom from=(BxglFrom)form;
		BxglClaim unit =from.getClaim();
		BxglClaim entity=claimService.get(unit.getId());
		if(entity!=null){
			entity.setMoney(unit.getMoney());
			entity.setCause(unit.getCause());
			entity.setHappenTime(df.parse(happenTime));
			entity.setClaimTime(df.parse(claimTime));
			entity.setPicturePath(unit.getPicturePath());
			claimService.update(entity);

			//生成 操作日志
			XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
			Log log=new Log();
	        log.addLog(user.getName(), "修改电梯电梯理赔记录，电梯注册号：", "1");

		}	
		return	new ActionForward("/claimAction.do?method=query&safeId="+entity.getSafeId().getId());

	}
	
	
	
	/**
	 * 删除年检记录
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  delEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		Long id=Long.parseLong(request.getParameter("id"));
		Long safeid=claimService.get(id).getSafeId().getId();
		claimService.delete(id);
		

		//生成 操作日志
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "删除电梯电梯理赔记录", "1");
		 return	new ActionForward("/claimAction.do?method=query&safeId="+safeid);
	}
	/**
	 * 批量 删除 年检记录
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  deleteEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String ids=request.getParameter("ids");
		Long safeid = null;
		if(ids!=null&&!ids.equals("")){
			String  arr []=ids.split(",");
			for(int i=0;i<arr.length;i++){
				 safeid=claimService.get(Long.parseLong(arr[i])).getSafeId().getId();
				 claimService.delete(Long.parseLong(arr[i]));
				//生成 操作日志
				XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
				Log log=new Log();
		        log.addLog(user.getName(), "删除电梯电梯理赔记录", "1");
			}
		}
		 return	new ActionForward("/claimAction.do?method=query&safeid="+safeid);
		
	}

}
