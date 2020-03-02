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

public interface HjbdDao extends Dao{

    /**
     * 查询加减刑数量
     */
    List<AnalysisResultVO> queryHjbdNum(@Param(value = "starttime")String starttime,
                                        @Param(value = "endtime")String endtime);


    /**
     * 环节变动业务动态
     */
    List<Map<String,Object>> hjbdYwdt(@Param(value = "starttime")String starttime,
                                    @Param(value = "endtime")String endtime, @Param("jsbh")String jsbh);
    /**
     * 在押人员诉讼情况
     */
   List<Map<String,Object>> zyryss(@Param("rybh")String rybh);
}
