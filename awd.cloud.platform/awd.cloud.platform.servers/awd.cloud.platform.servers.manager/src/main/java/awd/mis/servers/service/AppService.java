/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.service;

import java.util.List;
import java.util.Map;

import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.api.CrudService;
import awd.mis.servers.entity.AppEntity;

public interface AppService extends CrudService<AppEntity, String> {

	void cached();
	
	List<AppEntity> getApplistWithJsappByJsbh(QueryParamEntity  params);
	
	ResponseMessage<String> test();
	
}
