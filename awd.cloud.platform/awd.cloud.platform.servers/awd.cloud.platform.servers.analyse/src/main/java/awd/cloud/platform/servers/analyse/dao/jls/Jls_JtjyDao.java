package awd.cloud.platform.servers.analyse.dao.jls;

import awd.framework.common.dao.api.Dao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface Jls_JtjyDao extends Dao {
    List<Map<String, Object>> jtjyDt(@Param("starttime")String starttime,
                                     @Param("endtime")String endtime,
                                     @Param("jsbh")String jsbh);


    List<Map<String, Object>> jtjyTz(@Param("starttime")String starttime,
                                     @Param("endtime")String endtime,
                                     @Param("jyr")String cprq,
                                     @Param("zw")String ly,
                                     @Param("pzr")String gjyj);
}
