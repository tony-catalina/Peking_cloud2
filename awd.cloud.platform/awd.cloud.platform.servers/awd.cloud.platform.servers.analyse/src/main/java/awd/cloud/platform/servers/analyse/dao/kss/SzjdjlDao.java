/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.servers.analyse.dao.kss;

import awd.framework.common.dao.api.Dao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SzjdjlDao  extends Dao{

    /**
     * 领导接访，来访次数统计
     */
    List<Map<String,Object>> ldjfLfcs(@Param(value = "starttime")String starttime,
                                      @Param(value = "endtime")String endtime, @Param("jsbh")String jsbh);


    /**
     * 领导接访，处理情况统计
     */
    List<Map<String,Object>> ldjfClqk(@Param(value = "starttime")String starttime,
                                      @Param(value = "endtime")String endtime, @Param("jsbh")String jsbh);


    /**
     * 领导接访，总人数统计
     */
    List<Map<String,Object>> ldjfZrs(@Param(value = "starttime")String starttime,
                                      @Param(value = "endtime")String endtime, @Param("jsbh")String jsbh);
}
