package awd.cloud.platform.servers.analyse.dao.jls;

import awd.framework.common.dao.api.Dao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface Jls_GbjyDao extends Dao {
    List<Map<String, Object>> gbjyDt (@Param("starttime")String starttime,
                                      @Param("endtime")String endtime,
                                      @Param("badw")String badw);

    List<Map<String, Object>> gbjyZt ( @Param("state")String state,
                                       @Param("xm")String xm,
                                       @Param("starttime")String starttime,
                                       @Param("endtime")String endtime,
                                       @Param("starttime1")String starttime1,
                                       @Param("endtime1")String endtime1);

}



