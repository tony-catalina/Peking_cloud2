/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.servers.analyse.dao.kss;

import awd.framework.common.dao.api.Dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface JyDao extends Dao{

    /**
     * 流水牌>重点人员>所外就医
     * @param jsbh
     * @param field
     * @param value
     * @return
     */
    int getJyCountByField(@Param(value = "jsbh")String jsbh, @Param(value = "field")String field,
                          @Param(value = "value")String value);
    
    List<Map<String, Object>> swjyCount();
    
    List<Map<String, Object>> snjyCount();

    int getJyCountByCsjylx(@Param("jsbh") String jsbh, @Param("jsh") String jsh, @Param("field") String field,@Param("value") String value);
}
