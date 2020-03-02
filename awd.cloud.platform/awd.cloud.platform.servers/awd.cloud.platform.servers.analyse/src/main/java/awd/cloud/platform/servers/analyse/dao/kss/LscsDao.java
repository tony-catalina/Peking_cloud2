/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.servers.analyse.dao.kss;

import awd.framework.common.dao.api.Dao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface LscsDao  extends Dao{

    /**
     * 临时出所昨日出所
     */
    List<Map<String, Object>> lscsZrcs(@Param("jsbh")String jsbh);

    /**
     * 临时出所昨日出所未归
     */
    List<Map<String, Object>> lscsZrcswg(@Param("jsbh")String jsbh);


    /**
     * 临时出所今日出所
     */
    List<Map<String, Object>> lscsJrcs(@Param("jsbh")String jsbh);

    /**
     * 临时出所今日出所未归
     */
    List<Map<String, Object>> lscsJrcswg(@Param("jsbh")String jsbh);

    /**
     * 临时出所本周出所
     */
    List<Map<String, Object>> lscsBzcs(@Param("jsbh")String jsbh);


    /**
     * 临时出所本周出所未归
     */
    List<Map<String, Object>> lscsBzcswg(@Param("jsbh")String jsbh);
    
    /**
     * 临时出所本月出所
     */
    List<Map<String, Object>> lscsBycs(@Param("jsbh")String jsbh);

    /**
     * 临时出所本月出所未归
     */
    List<Map<String, Object>> lscsBycswg(@Param("jsbh")String jsbh);


    //流水牌>实时状态>离所探视
    int getLscsCountByField (@Param(value = "jsbh")String jsbh,@Param(value = "field")String field,
                             @Param(value = "value")String value);

    int getLscsCountByCsyyWithJs(@Param(value = "jsbh")String jsbh,@Param("jsh")String jsh,
                                  @Param(value = "value")String value);
}
