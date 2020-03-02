/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.tasks.dao;

import java.util.List;

import awd.cloud.platform.tasks.entity.TasklogsEntity;
import awd.framework.common.dao.api.CrudDao;
import awd.framework.common.entity.Entity;

public interface TasklogsDao extends CrudDao<TasklogsEntity, String> {
	
	List<TasklogsEntity> query(Entity queryEntity);

    int count(Entity queryEntity);

}
