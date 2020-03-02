package awd.cloud.platform.servers.analyse.dao.kss;

import awd.framework.common.dao.api.Dao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface Tjdao extends Dao {


    /**
     * 提解业务动态
     */
    List<Map<String,Object>> tjYwdt(@Param(value = "starttime")String starttime,
                                      @Param(value = "endtime")String endtime, @Param("jsbh")String jsbh);
}
