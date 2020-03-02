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

public interface TsdjDao extends Dao {
    /**
     * 查询提审登记数量
     */
    List<Map<String, Object>> queryTsdjNum(@Param(value = "kssj")String kssj,
                                          @Param(value = "jssj")String jssj);


    //提审登记=上位机版本
    List<AnalysisResultVO> swj_tsdjnum(@Param(value = "kssj")String kssj,
                                       @Param(value = "jssj")String jssj);


    //流水牌>实时状态>提审
    int getTsdjCount(@Param(value = "jsbh")String jsbh);

    int getJsTsdjCount(@Param("jsbh") String jsbh,@Param("jsh") String jsh);
}
