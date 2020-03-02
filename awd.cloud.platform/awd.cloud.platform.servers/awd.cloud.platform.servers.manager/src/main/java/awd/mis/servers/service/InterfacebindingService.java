/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.service;

import org.apache.ibatis.annotations.Param;

import awd.framework.common.service.api.CrudService;
import awd.mis.servers.entity.InterfacebindingEntity;

public interface InterfacebindingService extends CrudService<InterfacebindingEntity, String> {
	
	void updateInterfaceBdByCode(String bdzt,String appcode,String interface_id);

	void cached();
}
