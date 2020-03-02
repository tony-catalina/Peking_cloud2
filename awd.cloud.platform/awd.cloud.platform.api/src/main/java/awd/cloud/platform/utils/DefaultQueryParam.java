package awd.cloud.platform.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

import awd.cloud.platform.api.ManagerService;
import awd.framework.common.utils.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultQueryParam {
	
	@Autowired
	private ManagerService managerService;
	/**
	 * 把一些默认参数封装进qParam，state、page、rows、sort、order等
	 * @param request
	 * @param qParam
	 */
	public static void addDefaultQueryParam(HttpServletRequest request ,QueryParam qParam,String state) {
		String json = request.getParameter("json");
		JSONObject jsonStr= JSONObject.parseObject(json);
		System.err.println(jsonStr);
		Map<String, Object> map = new HashMap<String, Object>();
		System.err.println(json);
		if(json!=null) {
			map = JSONObject.parseObject(json, HashMap.class);
		}
		if(state != null) {
			qParam.and("state", TermType.in, state);
		}else {
			qParam.and("state", TermType.eq, "R2");
		}
		String page = "";
		String rows = "";
		if(!StringUtils.isNullOrEmpty(map.get("page"))) {
				page = map.get("page").toString();
		}else{
			page = "1";
		}
		if(!StringUtils.isNullOrEmpty(map.get("pageSize"))) {
			if(Integer.parseInt(map.get("pageSize").toString())>50) {
				rows = "50";
			}else {
				rows = map.get("pageSize").toString();
			}
		}
		int pageIndex = 0;
		int pageSize = 50;
		try {
			pageIndex = Integer.valueOf(page).intValue() - 1;
			pageSize = Integer.valueOf(rows).intValue();
		} catch (NumberFormatException e) {
			//e.printStackTrace();
			System.err.println("分页参数出错！");
		}finally {
			qParam.setPageIndex(pageIndex);
			qParam.setPageSize(pageSize);
		}
		String sortName = "";
		if(!StringUtils.isNullOrEmpty(map.get("sort"))) {
			sortName = request.getParameter("sort");
		}
        if (sortName != null && sortName.indexOf("String") > 0) {
        	sortName = sortName.substring(0, sortName.length() - 6);
        }
        String orderBy = "";
		if(!StringUtils.isNullOrEmpty(map.get("order"))) {
			orderBy = request.getParameter("order");
		}
        List<Sort> sorts = new ArrayList<>();
        Sort sort = new Sort();
        if(sortName != null && orderBy != null) {
        	sort.setName(sortName);
        	sort.setOrder(orderBy);
        }else {
        	sort.setName("id");
        	sort.setOrder("desc");
		}
        sorts.add(sort);
        qParam.setSorts(sorts);
	}
	
}
