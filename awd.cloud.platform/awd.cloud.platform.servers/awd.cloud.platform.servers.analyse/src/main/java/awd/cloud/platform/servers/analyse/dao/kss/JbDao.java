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

public interface JbDao  extends Dao{
    List<Map<String, Object>> rqcxJ(@Param("kssj")String kssj, @Param("jssj") String jssj );

    //流水牌>实时状态>禁闭
    int getJbCount(@Param(value = "jsbh")String jsbh);

    //上位机_禁闭情况
    List<AnalysisResultVO> swj_rqcxJ(@Param("kssj")String kssj, @Param("jssj") String jssj);
}
