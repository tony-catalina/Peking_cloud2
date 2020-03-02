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

public interface JjxDao  extends Dao{
    /**
     * 查询加减刑数量
     */
    List<AnalysisResultVO> queryJjxNum(@Param(value = "starttime")String starttime,
                                       @Param(value = "endtime")String endtime);


}
