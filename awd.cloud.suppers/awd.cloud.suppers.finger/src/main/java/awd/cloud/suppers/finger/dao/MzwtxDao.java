/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.suppers.finger.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import awd.cloud.suppers.finger.entity.MzwtxEntity;
import awd.framework.common.dao.api.CrudDao;
import awd.framework.common.entity.Entity;

public interface MzwtxDao extends CrudDao<MzwtxEntity, String> {
	
	List<MzwtxEntity> query(Entity queryEntity);

	MzwtxEntity selectMjZwCode(@Param("zjh") String zjh, @Param("state1") String state1,@Param("state2") String state2);
	
    int count(Entity queryEntity);

    int selectCount(String zjh);
}
