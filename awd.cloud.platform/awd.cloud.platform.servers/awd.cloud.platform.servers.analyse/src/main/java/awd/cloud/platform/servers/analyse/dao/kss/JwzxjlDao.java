/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.servers.analyse.dao.kss;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import awd.framework.common.dao.api.Dao;

public interface JwzxjlDao extends Dao{
	
	 List<Map<String, Object>> jwzxjlInfo(@Param("starttime")String starttime,
             @Param("endtime")String endtime);

	 List<Map<String,Object>> jwzxjlYwdt(@Param(value = "starttime")String starttime,
            @Param(value = "endtime")String endtime,@Param("jsbh")String jsbh);


}
