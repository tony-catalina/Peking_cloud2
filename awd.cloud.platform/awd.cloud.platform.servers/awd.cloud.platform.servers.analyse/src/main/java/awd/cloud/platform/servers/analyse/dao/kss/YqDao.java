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

public interface YqDao  extends Dao {
    List<AnalysisResultVO> rqcxY(@Param("starttime")String starttime, @Param("endtime") String endtime);

    /**
     * 延期业务动态
     */
    List<Map<String,Object>> yqYwdt(@Param(value = "starttime")String starttime,
                                      @Param(value = "endtime")String endtime, @Param("jsbh")String jsbh);
}
