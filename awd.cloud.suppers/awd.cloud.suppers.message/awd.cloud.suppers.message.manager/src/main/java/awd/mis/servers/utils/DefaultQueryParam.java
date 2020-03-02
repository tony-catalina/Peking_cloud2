package awd.mis.servers.utils;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

import awd.framework.common.core.param.TermType;
import awd.framework.common.entity.param.QueryParamEntity;

public class DefaultQueryParam {
	/**
	 * 把一些默认参数封装进qParam，state、page、rows、sort、order等
	 * @param request
	 * @param qParam
	 */
	public static QueryParamEntity getQueryParamEntity(HttpServletRequest request) {
		QueryParamEntity queryParamEntity = new QueryParamEntity();
		String page = request.getParameter("page");
		String limit = request.getParameter("limit");
		queryParamEntity.setPaging(true);
		int pageIndex = 0;
		int pageSize = 10;
		try {
			pageSize = Integer.valueOf(limit).intValue();
			pageIndex = (Integer.valueOf(page).intValue() - 1) * pageSize;
		} catch (NumberFormatException e) {
			//e.printStackTrace();
			System.err.println("分页参数出错！");
		}finally {
			queryParamEntity.setPageIndex(pageIndex);
			queryParamEntity.setPageSize(pageSize);
		}
		
		List<String> keyList = JSON.parseArray(JSON.toJSONString(request.getParameterNames()), String.class);
		keyList.forEach(name->{
			if ("pageIndex,pageSize,page,limit".indexOf(name) == -1) {
				queryParamEntity.and(name, TermType.like, "%"+request.getParameter(name)+"%");
			}
		});
		
		return queryParamEntity;
	}
	
	public static Map<String, String> getPaginParam(HttpServletRequest request) {
		Map<String, String> map = Maps.newHashMap();
		String page = request.getParameter("page");
		String limit = request.getParameter("limit");
		int pageIndex = 0;
		int pageSize = 10;
		try {
			pageSize = Integer.valueOf(limit).intValue();
			pageIndex = (Integer.valueOf(page).intValue() - 1) * pageSize;
		} catch (NumberFormatException e) {
			//e.printStackTrace();
			System.err.println("分页参数出错！");
		}
		map.put("pageIndex", String.valueOf(pageIndex));
		map.put("pageSize", String.valueOf(pageSize));
		return map;
	}
	
	public static Map<String, String> requestToParam(HttpServletRequest request) {
		List<String> keyList = JSON.parseArray(JSON.toJSONString(request.getParameterNames()), String.class);
		Map<String, String> map = getPaginParam(request);
		keyList.forEach(name->{
			if ("pageIndex,pageSize,page,limit".indexOf(name) == -1) {
				map.put(name, request.getParameter(name));
			}
		});
		return map;
	}
	
}
