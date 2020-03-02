/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.servers.analyse.dao.kss;

import awd.cloud.platform.servers.analyse.vo.AnalysisResultVO;
import awd.framework.common.dao.api.Dao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 谈话教育
 */
public interface ThjyDao extends Dao {
    List<AnalysisResultVO> rqcx(@Param("kssj")String kssj,@Param("jssj") String jssj );

    int getBzJsThrs(@Param("jsbh") String jsbh, @Param("jsh") String jsh);
}
