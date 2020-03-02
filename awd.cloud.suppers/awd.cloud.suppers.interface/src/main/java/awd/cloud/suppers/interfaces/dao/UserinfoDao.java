/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.suppers.interfaces.dao;

import java.util.List;

import awd.cloud.suppers.interfaces.entity.UserinfoEntity;
import awd.framework.common.dao.api.CrudDao;
import awd.framework.common.entity.Entity;

public interface UserinfoDao extends CrudDao<UserinfoEntity, String> {
	
	List<UserinfoEntity> query(Entity queryEntity);

    int count(Entity queryEntity);
    
    void deleteTable();
    
}
