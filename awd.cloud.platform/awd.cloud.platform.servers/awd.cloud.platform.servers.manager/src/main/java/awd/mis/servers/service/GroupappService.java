/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.service;

import awd.framework.common.service.api.CrudService;
import awd.mis.servers.entity.Groupapp;
import awd.mis.servers.entity.GroupappEntity;

public interface GroupappService extends CrudService<GroupappEntity, String> {
	String addGroupApp(Groupapp groupapp);
}
