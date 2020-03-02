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

public interface JstzDao  extends Dao{

    List<AnalysisResultVO> rqcxJ(@Param("starttime")String starttime, @Param("endtime") String endtime);
    
    
    List<Map<String, Object>> Jstzcount(@Param("starttime")String starttime, @Param("endtime") String endtime,@Param("jsbh") String jsbh);
    
    List<Map<String, Object>> JstzcountR(@Param("jsbh") String jsbh,@Param("starttime")String starttime, @Param("endtime") String endtime);

}
