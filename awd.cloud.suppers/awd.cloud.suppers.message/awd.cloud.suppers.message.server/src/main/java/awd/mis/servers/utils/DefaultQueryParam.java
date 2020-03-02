package awd.mis.servers.utils;

import java.util.Map;

import awd.framework.common.core.param.TermType;
import awd.framework.common.entity.param.QueryParamEntity;

public class DefaultQueryParam {

	public static QueryParamEntity getQueryParamEntity(Map<String, String> params) {
		QueryParamEntity queryParamEntity = new QueryParamEntity();
		queryParamEntity.setPaging(false);
		params.forEach((k,v)->{
			if ("pageIndex,pageSize,page,limit".indexOf(k) == -1) {
				queryParamEntity.and(k,TermType.like,"%"+v+"%");
			}
		});
		return queryParamEntity;
	}
}
