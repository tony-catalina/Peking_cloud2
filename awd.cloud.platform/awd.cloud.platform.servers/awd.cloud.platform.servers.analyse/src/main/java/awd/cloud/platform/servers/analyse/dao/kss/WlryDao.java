package awd.cloud.platform.servers.analyse.dao.kss;

import awd.framework.common.dao.api.Dao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface WlryDao extends Dao {

    /**
     * 外来人员,来所事由统计
     */
    List<Map<String,Object>> wlryLssy(@Param(value = "starttime")String starttime,
                                      @Param(value = "endtime")String endtime, @Param("jsbh")String jsbh);

    /**
     * 外来人员,来所人数统计
     */
    List<Map<String,Object>> wlryLfrs(@Param(value = "starttime")String starttime,
                                      @Param(value = "endtime")String endtime, @Param("jsbh")String jsbh);

    /**
     * 外来人员,来所车辆统计
     */
    List<Map<String,Object>> wlrySxcls(@Param(value = "starttime")String starttime,
                                       @Param(value = "endtime")String endtime, @Param("jsbh")String jsbh);
}
