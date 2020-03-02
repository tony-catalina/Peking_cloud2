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

public interface XjDao extends Dao {
    /**
     * 查询加械具情况数量
     */
    List<AnalysisResultVO> queryXjqkNum(@Param(value = "starttime")String starttime,
                                        @Param(value = "endtime")String endtime);


    /**
     * 械具总人数
     */
    
    List<Map<String , Object>> xjZrs(@Param(value = "starttime")String starttime,
            @Param(value = "endtime")String endtime, @Param("jsbh")String jsbh);
    
    

    /**
     * 械具使用呈批统计
     */
    List<Map<String,Object>> xjSycp(@Param(value = "starttime")String starttime,
                                      @Param(value = "endtime")String endtime, @Param("jsbh")String jsbh);

    /**
     * 械具使用登记统计
     */
    List<Map<String,Object>> xjSydj(@Param(value = "starttime")String starttime,
                                    @Param(value = "endtime")String endtime, @Param("jsbh")String jsbh);

    /**
     * 械具延长登记统计
     */
    List<Map<String,Object>> xjYcdj(@Param(value = "starttime")String starttime,
                                    @Param(value = "endtime")String endtime, @Param("jsbh")String jsbh);
    //脚镣人数统计
      Map<String,Object> aqglQuery();

    //流水牌>实时状态>械具
    int getXjCount(@Param(value = "jsbh")String jsbh);
}
