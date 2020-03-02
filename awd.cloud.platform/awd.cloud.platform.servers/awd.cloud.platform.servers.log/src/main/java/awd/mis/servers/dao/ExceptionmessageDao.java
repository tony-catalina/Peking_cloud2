/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.dao;

import awd.framework.common.dao.api.CrudDao;
import awd.framework.common.entity.Entity;
import awd.mis.servers.entity.ExceptionmessageEntity;

import java.util.List;

public interface ExceptionmessageDao extends CrudDao<ExceptionmessageEntity, String> {

    List<ExceptionmessageEntity> query(Entity queryEntity);

    int count(Entity queryEntity);

}
