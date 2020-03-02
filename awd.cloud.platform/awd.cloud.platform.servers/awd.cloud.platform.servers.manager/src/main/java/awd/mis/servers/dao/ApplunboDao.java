/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.dao;

import awd.framework.common.dao.api.CrudDao;
import awd.framework.common.entity.Entity;
import awd.mis.servers.entity.ApplunboEntity;

import java.util.List;

public interface ApplunboDao extends CrudDao<ApplunboEntity, String> {

    List<ApplunboEntity> query(Entity queryEntity);

    int count(Entity queryEntity);

    /**
     * 更新所有applunbo数据为R3
     */
    void setAppLunboR3();
}
