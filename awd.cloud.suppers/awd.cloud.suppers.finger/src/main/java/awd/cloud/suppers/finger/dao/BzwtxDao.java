/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.suppers.finger.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import awd.cloud.suppers.finger.entity.BzwtxEntity;
import awd.framework.common.dao.api.CrudDao;
import awd.framework.common.entity.Entity;

public interface BzwtxDao extends CrudDao<BzwtxEntity, String> {
	
    int count(Entity queryEntity);
    
    List<BzwtxEntity> query(Entity queryEntity);
    
    BzwtxEntity selectZwCode(@Param("rybh") String rybh, @Param("state1") String state1,@Param("state2") String state2);

    //int selectCount(@Param("rybh") String rybh, @Param("state") String state);
    int selectCount(String rybh);
    
    int selectYcsCount(String rybh);
}
