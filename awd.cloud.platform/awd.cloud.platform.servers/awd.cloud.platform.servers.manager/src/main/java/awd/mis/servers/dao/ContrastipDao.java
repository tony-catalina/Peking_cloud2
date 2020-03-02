/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.dao;

import awd.framework.common.dao.api.CrudDao;
import awd.framework.common.entity.Entity;
import awd.mis.servers.entity.ContrastipEntity;

import java.util.List;

public interface ContrastipDao extends CrudDao<ContrastipEntity, String> {
	
	List<ContrastipEntity> query(Entity queryEntity);

    int count(Entity queryEntity);
    
	/**
	 * 循环查找表字段是否含有rybh，有的话就自动生成jbxxList 方法，没有就不生成
	 */

}
