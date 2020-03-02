/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.servers.analyse.dao.kss;

import awd.framework.common.dao.api.Dao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TafDao  extends Dao {
    List<Map<String, Object>> TafInfo(@Param(value="starttime")String starttime,
                                       @Param(value="endtime")String endtime);

    //同案犯查询=上位机版本
    List<Map<String, Object>> swj_tafcx(@Param(value="starttime")String starttime,
                                      @Param(value="endtime")String endtime);
}
