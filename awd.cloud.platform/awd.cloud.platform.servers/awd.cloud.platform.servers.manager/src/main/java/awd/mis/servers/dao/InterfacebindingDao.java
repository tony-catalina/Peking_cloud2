/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import awd.framework.common.dao.api.CrudDao;
import awd.framework.common.entity.Entity;
import awd.mis.servers.entity.InterfacebindingEntity;

public interface InterfacebindingDao extends CrudDao<InterfacebindingEntity, String> {
	
	List<InterfacebindingEntity> query(Entity queryEntity);

    int count(Entity queryEntity);
    
    void updateInterfaceBdByCode(@Param("bdzt")String bdzt,@Param("appcode")String appcode,@Param("interface_id")String interface_id);

}
