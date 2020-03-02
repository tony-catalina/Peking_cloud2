package awd.cloud.platform.servers.analyse.dao.jls;

import awd.framework.common.dao.api.Dao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface Jls_QqdhDao extends Dao {
    int qqdhDt(@Param("starttime")String starttime,
                                     @Param("endtime")String endtime,
                                     @Param("jsbh")String jsbh);

    int qqdhday(@Param("starttime")String starttime,
                                     @Param("endtime")String endtime,
                                     @Param("jsbh")String jsbh);

}
