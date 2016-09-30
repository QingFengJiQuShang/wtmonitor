package com.jrsoft.fri.common.core.action;

import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;


public class SelectAction  extends DispatchAction{
	
	public ActionForward selectAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String ids = request.getParameter("ids");
		String txtname = request.getParameter("txtname");
		txtname= URLDecoder.decode(txtname,"utf-8");
		
		/*
		 * ������3��ͷ<ѧ������ģ��>
		 * */
		//����ѧ������
		if(ids.equals("31")||ids=="31"){
			request.setAttribute("txtname", txtname);
			return new ActionForward("/police/jsp/xsjl/xshy/huiyisel.jsp");
		}
		//�а�ѧ������
		if(ids.equals("32")||ids=="32"){
			request.setAttribute("txtname", txtname);
			return new ActionForward("/police/jsp/xsjl/xshy/cjhysel.jsp");
		}
		//ѧ������
		if(ids.equals("33")||ids=="33"){
			request.setAttribute("txtname", txtname);
			return new ActionForward("/police/jsp/xsjl/xsbg/xsbgsel.jsp");
		}
		//�����ѧ
		if(ids.equals("34")||ids=="34"){
			request.setAttribute("txtname", txtname);
			return new ActionForward("/police/jsp/xsjl/xsjz/wcjxsel.jsp");
		}
		//���뽲ѧ
		if(ids.equals("311")||ids=="311"){
			request.setAttribute("txtname", txtname);
			return new ActionForward("/police/jsp/xsjl/xsjz/yqjxsel.jsp");
		}
		//��������
		if(ids.equals("35")||ids=="35"){
			request.setAttribute("txtname", txtname);
			return new ActionForward("/police/jsp/xsjl/hzjl/huiyisel.jsp");
		}
		//�������
		if(ids.equals("36")||ids=="36"){
			request.setAttribute("txtname", txtname);
			return new ActionForward("/police/jsp/xsjl/kcpx/wckcsel.jsp");
		}
		//�Ӵ�����
		if(ids.equals("37")||ids=="37"){
			request.setAttribute("txtname", txtname);
			return new ActionForward("/police/jsp/xsjl/kcpx/jdkcsel.jsp");
		}
		//�����ѵ
		if(ids.equals("38")||ids=="38"){
			request.setAttribute("txtname", txtname);
			return new ActionForward("/police/jsp/xsjl/kcpx/wcpxsel.jsp");
		}
		//�����ѵ
		if(ids.equals("39")||ids=="39"){
			request.setAttribute("txtname", txtname);
			return new ActionForward("/police/jsp/xsjl/pxb/huiyisel.jsp");
		}
		//�����ѵ
		if(ids.equals("310")||ids=="310"){
			request.setAttribute("txtname", txtname);
			return new ActionForward("/police/jsp/xsjl/qt/huiyisel.jsp");
		}
		/*
		 * ������4��ͷ<������Դ>ģ��
		 * */
		//���߷���
		if(ids.equals("41")||ids=="41"){
			request.setAttribute("txtname", txtname);
			return new ActionForward("/police/jsp/zhpt/glgd/glgdsel.jsp");
		}
		//��ȱ���
		if(ids.equals("42")||ids=="42"){
			request.setAttribute("txtname", txtname);
			request.setAttribute("flag", "5");
			return new ActionForward("/police/jsp/jczy/kjjh/kjjh_listsel.jsp");
		}
		//֪ͨ����
		if(ids.equals("43")||ids=="43"){
			request.setAttribute("txtname", txtname);
			request.setAttribute("flag", "4");
			return new ActionForward("/police/jsp/jczy/kjjh/kjjh_listsel.jsp");
		}
		//���мƻ�
		if(ids.equals("44")||ids=="44"){
			request.setAttribute("txtname", txtname);
			request.setAttribute("flag", "1");
			return new ActionForward("/police/jsp/jczy/kjjh/kjjh_listsel.jsp");
		}
		//���ж�̬
		if(ids.equals("45")||ids=="45"){
			request.setAttribute("txtname", txtname);
			request.setAttribute("flag", "3");
			return new ActionForward("/police/jsp/jczy/kjjh/kjjh_listsel.jsp");
		}
		//�Ƽ���̬
		if(ids.equals("46")||ids=="46"){
			request.setAttribute("txtname", txtname);
			request.setAttribute("flag", "2");
			return new ActionForward("/police/jsp/jczy/kjjh/kjjh_listsel.jsp");
		}
		//������Ա
		if(ids.equals("47")||ids=="47"){
			request.setAttribute("txtname", txtname);
		
			return new ActionForward("/police/jsp/jczy/userKz/kz_listsel.jsp");
		}
		//��ϰ��Ա
		if(ids.equals("48")||ids=="48"){
			request.setAttribute("txtname", txtname);
		
			return new ActionForward("/police/jsp/jczy/userJx/jx_listsel.jsp");
		}
		//��ҵר��
		if(ids.equals("49")||ids=="49"){
			request.setAttribute("txtname", txtname);
			return new ActionForward("/police/jsp/jczy/zjk/zjklistsel.jsp");
		}
		//��Ŀ����
		if(("11").equals(ids)){
			request.setAttribute("txtname", txtname);
			return new ActionForward("/police/jsp/xmqq/xmyk/xmsb.jsp");
		}else if(("12").equals(ids)){
			request.setAttribute("txtname", txtname);
			return new ActionForward("/police/jsp/xmqq/lxps/lxps.jsp");
		}else if(("13").equals(ids)){
			request.setAttribute("txtname", txtname);
			return new ActionForward("/police/jsp/xmqq/xmxd/xmrws.jsp");
		}else if(("14").equals(ids)){
			request.setAttribute("txtname", txtname);
			return new ActionForward("/police/jsp/xmss/ktps/xmkt.jsp");
		}else if(("15").equals(ids)){
			request.setAttribute("txtname", txtname);
			return new ActionForward("/police/jsp/xmss/xmbg/xmbg.jsp");
		}else if(("16").equals(ids)){
			request.setAttribute("txtname", txtname);
			return new ActionForward("/police/jsp/xmss/xmjh/xmkh.jsp");
		}else if(("17").equals(ids)){
			request.setAttribute("txtname", txtname);
			return new ActionForward("/police/jsp/xmss/xmys/xmys.jsp");
		}else if(("18").equals(ids)){
			request.setAttribute("txtname", txtname);
			return new ActionForward("/police/jsp/xmss/xmjt/xmjt.jsp");
		}else if(("21").equals(ids)){
			request.setAttribute("txtname", txtname);
			return new ActionForward("/police/jsp/fruit/lunwen/lunwen.jsp");
		}else if(("22").equals(ids)){
			request.setAttribute("txtname", txtname);
			return new ActionForward("/police/jsp/fruit/zhuzuo/zhuzuo.jsp");
		}else if(("23").equals(ids)){
			request.setAttribute("txtname", txtname);
			return new ActionForward("/police/jsp/fruit/zhuanli/zhuanli.jsp");
		}else if(("24").equals(ids)){
			request.setAttribute("txtname", txtname);
			return new ActionForward("/police/jsp/fruit/ruanjian/rjzz.jsp");
		}else if(("25").equals(ids)){
			request.setAttribute("txtname", txtname);
			return new ActionForward("/police/jsp/fruit/jishu/jsbz.jsp");
		}
		
		return null;
	}
}
