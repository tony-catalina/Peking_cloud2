/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.suppers.interfaces.dao;

import java.util.List;

import awd.cloud.suppers.interfaces.entity.UserappEntity;
import awd.framework.common.dao.api.CrudDao;
import awd.framework.common.entity.Entity;

public interface UserappDao extends CrudDao<UserappEntity, String> {
	
	List<UserappEntity> query(Entity queryEntity);

    int count(Entity queryEntity);

}
