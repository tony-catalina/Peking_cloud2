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

public interface ClcsDao extends Dao {

    List<Map<String, Object>> ClcsInfo(@Param("starttime")String starttime,
                                       @Param("endtime")String endtime);


    //处理处所查询上位机版本
    List<AnalysisResultVO> swj_clcsInfo(@Param("starttime")String starttime,
                                        @Param("endtime")String endtime);

    /**
     * 处理出所业务动态
     */
    List<Map<String,Object>> clcsYwdt(@Param(value = "starttime")String starttime,
                                      @Param(value = "endtime")String endtime,@Param("jsbh")String jsbh);


}
