package awd.cloud.suppers.interfaces.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DefaultQueryParam {
	/**
	 * 把一些默认参数封装进qParam，state、page、rows、sort、order等
	 * @param request
	 * @param qParam
	 */
	public static void addDefaultQueryParam(Map<String, Object> map, QueryParam queryParam, String state) {
		//String state = request.getParameter("state");
		if(state != null) {
			queryParam.and("state", TermType.eq, state);
		}else {
			queryParam.and("state", TermType.eq, "R2");
		}

		int pageIndex = 0;
		int pageSize = 40;
		queryParam.setPageIndex(pageIndex);
		queryParam.setPageSize(pageSize);
		
		List<Sort> sorts = new ArrayList<>();
		Sort sort = new Sort();
		
		if (StringUtils.isNotBlank(map)) {
			String sortName = (String) map.get("sort");
	        String orderBy = (String) map.get("order");
			if (StringUtils.isNotBlank(sortName)) {
				sort.setName(sortName);
			}else {
				sort.setName("id");
			}
			
			if (StringUtils.isNotBlank(orderBy)) {
				sort.setOrder(orderBy);
			}else {
				sort.setOrder("desc");
			}
		}else {
			sort.setName("id");
        	sort.setOrder("desc");
		}
		
        sorts.add(sort);
        queryParam.setSorts(sorts);
	}
	
}
