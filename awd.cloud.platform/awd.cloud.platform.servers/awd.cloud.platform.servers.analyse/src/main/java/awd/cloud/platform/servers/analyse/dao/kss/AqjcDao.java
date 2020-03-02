/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.servers.analyse.dao.kss;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import awd.framework.common.dao.api.Dao;

public interface AqjcDao extends Dao {

	List<Map<String, Object>> rqfx(@Param("jsbh")String jsbh);
	

}
