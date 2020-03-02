/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.servers.analyse.dao.kss;

import awd.framework.common.dao.api.Dao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ZdryDao extends Dao {

    //上位机版本=重点人员统计查询
    List<Map<String, Object>> swj_zdry(@Param(value="starttime")String starttime,
                                       @Param(value="endtime")String endtime);

    List<Map<String, Object>> ZdryInfo(@Param(value="starttime")String starttime,
                                      @Param(value="endtime")String endtime);


    /**
     *    管教，重点人员，业务动态
     */
    List<Map<String,Object>> zdryYwdt(@Param(value = "starttime")String starttime,
                                      @Param(value = "endtime")String endtime, @Param("jsbh")String jsbh,@Param("jsh")String jsh);


    //流水牌>重点人员>重点人员
    int getZdryCount(@Param(value = "jsbh")String starttime);
}
