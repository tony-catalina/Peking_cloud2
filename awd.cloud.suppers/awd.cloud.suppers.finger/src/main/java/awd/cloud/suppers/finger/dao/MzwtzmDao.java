/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.suppers.finger.dao;

import java.util.List;
import java.util.Map;

import awd.framework.common.dao.api.CrudDao;
import awd.framework.common.entity.Entity;
import awd.cloud.suppers.finger.entity.MzwtzmEntity;

public interface MzwtzmDao extends CrudDao<MzwtzmEntity, String> {
	
	List<MzwtzmEntity> query(Entity queryEntity);
	
	List<?> selectParam(String jsbh);
	
    int count(Entity queryEntity);
    
    int selectCount(String zjh);

}
