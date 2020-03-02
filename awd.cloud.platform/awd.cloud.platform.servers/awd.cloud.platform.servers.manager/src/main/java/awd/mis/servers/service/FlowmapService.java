/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.service;

import java.util.Map;

import awd.framework.common.service.api.CrudService;
import awd.mis.servers.entity.FlowmapEntity;
import awd.mis.servers.entity.RequestParameters;

public interface FlowmapService extends CrudService<FlowmapEntity, String> {
	
	String queryForId(String mapname);
	
	String deleteByMapname(String mapname);

	boolean loopAdd(Map<String,Object> maps);

	void initFlowMapCache();

	void updateFlowMapFlowNodeCache(RequestParameters requestParameters);

}
