/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.service;

import java.util.List;
import java.util.Map;

import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.service.api.CrudService;
import awd.mis.servers.entity.InterfaceEntity;
import awd.mis.servers.model.InterfaceModel;

public interface InterfaceService extends CrudService<InterfaceEntity, String> {
	
	
	PagerResult<InterfaceEntity> selectByappcode(String appcode,String bdzt,String start,String end);
	
	List<InterfaceEntity>  getApiParentNode();

	void cached();

	ResponseMessage<PagerResult<InterfaceModel>> interfaceBdztQuery( Map<String,Object> param);

	List<Integer> apiDshslQuery(String[] appcodes);

	List<Map<String,Object>> getTree();
}
