/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.service;

import java.util.List;

import awd.framework.common.entity.Entity;
import awd.framework.common.service.api.CrudService;
import awd.mis.servers.entity.YyEntity;

public interface YyService extends CrudService<YyEntity, String> {

	List<YyEntity> selectByOracle(Entity entity);
}
