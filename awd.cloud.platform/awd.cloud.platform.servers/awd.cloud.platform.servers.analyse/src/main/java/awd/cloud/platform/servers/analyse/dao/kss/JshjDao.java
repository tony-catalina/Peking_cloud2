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

public interface JshjDao  extends Dao{

    List<Map<String, Object>> jshjInfo(@Param(value="hjsj")String hjsj,
                                       @Param(value="jssj")String jssj);

    List<AnalysisResultVO> jshjFx(@Param(value="hjsj")String hjsj,
                                  @Param(value="jssj")String jssj);

    //流水牌>实时状态>家属会见
    int getJshjCount (@Param(value = "jsbh")String jsbh);
}
