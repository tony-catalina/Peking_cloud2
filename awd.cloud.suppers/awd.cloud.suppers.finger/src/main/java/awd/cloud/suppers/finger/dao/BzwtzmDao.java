/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.suppers.finger.dao;

import java.util.List;
import java.util.Map;

import awd.framework.common.dao.api.CrudDao;
import awd.framework.common.entity.Entity;
import awd.cloud.suppers.finger.entity.BzwtzmEntity;

public interface BzwtzmDao extends CrudDao<BzwtzmEntity, String> {
	
	List<BzwtzmEntity> query(Entity queryEntity);
	
	List<Map<String, Object>> selectParam(String jsbh);

    int count(Entity queryEntity);
    
    int selectCount(String rybh);
}
