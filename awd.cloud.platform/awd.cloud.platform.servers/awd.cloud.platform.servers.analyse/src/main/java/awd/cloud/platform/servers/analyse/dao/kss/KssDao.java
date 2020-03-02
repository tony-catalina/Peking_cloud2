/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.servers.analyse.dao.kss;

import awd.framework.common.dao.api.Dao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface KssDao  extends Dao{


     //上位机=查询超量关押数量
    List<Map<String, Object>> swj_Clgyfx(@Param(value="starttime")String starttime,@Param(value = "endtime")String endtime);

    /**
     * 查询超量关押数量
     */
    List<Map<String, Object>> queryClgyfxNum(@Param(value="starttime")String starttime,@Param(value = "endtime")String endtime);




}
