/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.service;

import java.util.Map;

import awd.framework.common.service.api.CrudService;
import awd.mis.servers.entity.JsappEntity;

public interface JsappService extends CrudService<JsappEntity, String> {
	
	int delJsApp(Map map);

	String insertJsappAndMount(JsappEntity jsappEntity);
}
