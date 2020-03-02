/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.dao;

import awd.framework.common.dao.api.CrudDao;
import awd.framework.common.entity.Entity;
import awd.mis.servers.entity.DocumentdetailEntity;
import awd.mis.servers.model.DocumentdetailModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DocumentdetailDao extends CrudDao<DocumentdetailEntity, String> {

    List<DocumentdetailEntity> query(Entity queryEntity);

    int count(Entity queryEntity);

    List<DocumentdetailModel> getDocumentDetail(Entity queryEntity);
}
