/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.service;

import java.util.List;
import java.util.Map;

import awd.framework.common.service.api.CrudService;
import awd.mis.servers.entity.GroupsEntity;

public interface GroupsService extends CrudService<GroupsEntity, String> {

	List<GroupsEntity> getGroups(String jsbh);
	
	List<Map<String, Object>> getJsTypeByGroups(String type);
	
	List<Map<String, Object>> getJsTreeTypeByGroups(String type);

}
