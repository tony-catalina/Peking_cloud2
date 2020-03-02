/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.service;

import java.util.List;

import awd.framework.common.service.api.CrudService;
import awd.mis.servers.entity.FlownodeEntity;

public interface FlownodeService extends CrudService<FlownodeEntity, String> {

	boolean saveNodeForMap(String processid,String flowmapid,List<FlownodeEntity> flownodeEntity);
	
	int deletByFlowmapid(String flowmapid);

	int removeByPk(String id);

	void cached();
	
}
