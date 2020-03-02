/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.dao;

import java.util.List;
import awd.framework.common.dao.api.CrudDao;
import awd.framework.common.entity.Entity;
import awd.mis.servers.entity.ApidocEntity;

public interface ApidocDao extends CrudDao<ApidocEntity, String> {
	
	List<ApidocEntity> query(Entity queryEntity);

    int count(Entity queryEntity);
    
	/**
	 * 循环查找表字段是否含有rybh，有的话就自动生成jbxxList 方法，没有就不生成
	 */

}
