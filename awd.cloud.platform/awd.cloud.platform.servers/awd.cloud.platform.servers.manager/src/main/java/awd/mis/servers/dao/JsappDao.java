/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.dao;

import java.util.List;
import java.util.Map;

import awd.framework.common.dao.api.CrudDao;
import awd.framework.common.entity.Entity;
import awd.mis.servers.entity.JsappEntity;

public interface JsappDao extends CrudDao<JsappEntity, String> {
	
	List<JsappEntity> query(Entity queryEntity);

    int count(Entity queryEntity);
    
    int delJsApp(Map map);

}
