/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.dao;

import java.util.List;

import awd.framework.common.dao.api.CrudDao;
import awd.framework.common.entity.Entity;
import awd.mis.servers.entity.MenusEntity;

public interface MenusDao extends CrudDao<MenusEntity, String> {
	
	List<MenusEntity> query(Entity queryEntity);

    int count(Entity queryEntity);

}
