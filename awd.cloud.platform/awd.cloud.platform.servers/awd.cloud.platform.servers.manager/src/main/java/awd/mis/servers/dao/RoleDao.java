/*
s * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.dao;

import java.util.List;

import awd.framework.common.dao.api.CrudDao;
import awd.framework.common.entity.Entity;
import awd.mis.servers.entity.RoleEntity;

public interface RoleDao extends CrudDao<RoleEntity, String> {
	
	List<RoleEntity> query(Entity queryEntity);

    int count(Entity queryEntity);

}
