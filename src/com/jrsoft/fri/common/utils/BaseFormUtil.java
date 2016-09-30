package com.jrsoft.fri.common.utils;

import java.util.HashMap;
import java.util.Map;
import net.sf.json.JSONObject;
import com.jrsoft.fri.common.core.bean.JqGridSearchDetailTo;
import com.jrsoft.fri.common.core.bean.JqGridSearchTo;
import com.jrsoft.fri.common.core.form.BaseForm;

public class BaseFormUtil {

	/**
	 * �ѷ�ҳ������Ͳ�ѯ������Ϣ��form����ȡ��page��
	 * @param form
	 * @return
	 */
	public static Page getPage(BaseForm form){
		Page page = new Page(form.getPage(), form.getRows());
		page.setSearch(form.getSearch());
		page.setFilters(form.getFilters());
		page.setSidx(form.getSidx());
		page.setSord(form.getSord());
		page.setSubId(form.getSord());
		String deptId = form.getDeptId();
		if(StringUtils.isNotEmpty(deptId)){
//			page.setDeptIds(SmartOrg.getDeptsByCurDeptId(deptId));
		}
		if(page.getSearch() && StringUtils.isNotEmpty(page.getFilters())){
			JSONObject filter = JSONObject.fromObject(page.getFilters());
//			System.out.println(filter);
			Map m = new HashMap();
			m.put("rules", JqGridSearchDetailTo.class);
			JqGridSearchTo to = (JqGridSearchTo)JSONObject.toBean(filter, JqGridSearchTo.class, m);
			to.setSearch(page.getSearch());
			to.setSidx(page.getSidx());
			to.setSord(page.getSord());
			page.setTo(to);
		}
		return page;
	}
}
