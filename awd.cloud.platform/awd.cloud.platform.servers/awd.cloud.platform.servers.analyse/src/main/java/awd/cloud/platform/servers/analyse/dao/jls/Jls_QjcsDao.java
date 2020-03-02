package awd.cloud.platform.servers.analyse.dao.jls;

import awd.framework.common.dao.api.Dao;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface Jls_QjcsDao extends Dao {

    Map<String, Object> qjcsYwdt(@Param("starttime") String starttime,
                                 @Param("endtime") String endtime,
                                 @Param("jsbh") String jsbh);
}
