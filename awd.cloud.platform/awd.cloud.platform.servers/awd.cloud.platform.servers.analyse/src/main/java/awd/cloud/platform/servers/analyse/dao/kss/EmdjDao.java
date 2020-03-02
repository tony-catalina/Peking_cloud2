/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.servers.analyse.dao.kss;

import awd.cloud.platform.servers.analyse.vo.AnalysisResultVO;
import awd.framework.common.dao.api.Dao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmdjDao  extends Dao{
    List<Map<String, Object>> rqcxE(@Param("starttime")String starttime, @Param("endtime") String endtime);

    //上位机版本耳目登记
    List<AnalysisResultVO> swj_rqcxE(@Param("starttime")String starttime, @Param("endtime") String endtime);

    /**
     * 查询监区号，监区名称
     */
    List<Map<String , Object>> queryJq(@Param("jsbh")String jsbh);


    /**
     * 已布建耳目监室
     */
    List<Map<String , Object>> emYbjjs(@Param("jsbh")String jsbh);

    /**
     * 未布建耳目监室
     */
    List<Map<String , Object>> emWbjjs(@Param("jsbh")String jsbh);

    /**
     * 耳目总数
     */
    List<Map<String , Object>> emZs(@Param(value = "jqh")String jqh,@Param("jsbh")String jsbh);

    /**
     * 监室人数，耳目统计
     */
    List<Map<String , Object>> emTj(
            @Param(value = "jqh")String jqh, @Param("jsbh")String jsbh);

}
