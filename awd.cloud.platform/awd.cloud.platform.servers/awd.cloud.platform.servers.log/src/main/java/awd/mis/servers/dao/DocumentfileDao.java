/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.dao;

import awd.framework.common.dao.api.CrudDao;
import awd.framework.common.entity.Entity;
import awd.mis.servers.entity.DocumentfileEntity;

import java.util.List;

public interface DocumentfileDao extends CrudDao<DocumentfileEntity, String> {
	
	List<DocumentfileEntity> query(Entity queryEntity);

    int count(Entity queryEntity);

}
