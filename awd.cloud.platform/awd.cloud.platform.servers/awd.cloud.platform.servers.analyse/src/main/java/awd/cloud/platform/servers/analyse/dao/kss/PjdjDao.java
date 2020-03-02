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

public interface PjdjDao extends Dao {

    List<Map<String, Object>> pjdjInfo(@Param(value="starttime")String starttime,
                                       @Param(value="endtime")String endtime);

    List<AnalysisResultVO> pjdjFx(@Param(value="starttime")String starttime,
                                  @Param(value="endtime")String endtime);

    List<Map<String,Object>> pjdjYwdt(@Param(value="starttime")String starttime,
    		@Param(value ="endtime")String endtime, @Param("jsbh") String jsbh);
}
