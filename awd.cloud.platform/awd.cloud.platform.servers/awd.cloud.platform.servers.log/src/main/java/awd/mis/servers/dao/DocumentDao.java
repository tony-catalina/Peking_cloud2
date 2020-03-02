/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.dao;

import awd.framework.common.dao.api.CrudDao;
import awd.framework.common.entity.Entity;
import awd.mis.servers.entity.DocumentEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DocumentDao extends CrudDao<DocumentEntity, String> {

    List<DocumentEntity> query(Entity queryEntity);

    int count(Entity queryEntity);

    List<DocumentEntity> selectByUUID(@Param("uuid") String uuid);
}
