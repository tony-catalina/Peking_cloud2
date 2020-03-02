/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.dao;

import java.util.List;

import awd.framework.common.dao.api.CrudDao;
import awd.framework.common.entity.Entity;
import awd.mis.servers.entity.GroupsEntity;

public interface GroupsDao extends CrudDao<GroupsEntity, String> {
	
	List<GroupsEntity> query(Entity queryEntity);

    int count(Entity queryEntity);

    List<GroupsEntity> getGroups(String jsbh);
}
