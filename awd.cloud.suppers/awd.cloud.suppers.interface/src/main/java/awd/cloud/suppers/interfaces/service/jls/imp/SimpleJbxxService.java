package awd.cloud.suppers.interfaces.service.jls.imp;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import awd.cloud.suppers.interfaces.api.JlsServersApi;
import awd.cloud.suppers.interfaces.service.jls.JbxxService;
import awd.cloud.suppers.interfaces.utils.DefaultQueryParam;
import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.QueryParam;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;
import awd.cloud.suppers.interfaces.utils.Sort;
import awd.framework.common.entity.param.QueryParamEntity;

@Service("jbxxService")
public class SimpleJbxxService implements JbxxService {

	@Autowired
	private JlsServersApi jlsServersApi;
	
	@Override
	public ResponseMessage<PagerResult<Map<String, Object>>> getJbxx() {
		
		QueryParam queryParam = new QueryParam();
		queryParam.and("state", "R8");
		queryParam.setPaging(true);
		queryParam.setPageIndex(0);
		queryParam.setPageSize(2);
		
		Set<String> includes = new LinkedHashSet<>();
		includes.add("jsbh");
		includes.add("jsh");
		includes.add("rybh");
		
		//queryParam.setIncludes(includes);
		
		DefaultQueryParam.addDefaultQueryParam(null, queryParam, "R8");
		
        System.err.println("queryParam---"+JSON.toJSONString(queryParam));
        
		ResponseMessage<PagerResult<Map<String, Object>>> list = jlsServersApi.queryJbxx(queryParam);
		
		
		System.err.println("list.getIncludes()---"+JSON.toJSONString(list.getIncludes()));
		System.err.println("list---"+JSON.toJSONString(list));
		
		return list;
	}

	@Override
	public ResponseMessage<String> saveJbxx(Map<String,Object> map) {
		return jlsServersApi.saveJbxx(map);
	}
	
	@Override
	public ResponseMessage<String> updateJbxxById(String id,Map<String,Object> map) {
		return jlsServersApi.updateJbxxById(id,map);
	}

}
