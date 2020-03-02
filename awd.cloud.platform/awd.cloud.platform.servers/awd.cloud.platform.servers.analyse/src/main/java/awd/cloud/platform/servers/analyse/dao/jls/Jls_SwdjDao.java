package awd.cloud.platform.servers.analyse.dao.jls;

import awd.framework.common.dao.api.Dao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface Jls_SwdjDao extends Dao {

    List<Map<String, Object>> swdjDt(@Param("starttime") String starttime,
                                     @Param("endtime") String endtime,
                                     @Param("jsbh") String jsbh);

    Map<String, Object> swdj(@Param("starttime") String starttime,
                                     @Param("endtime") String endtime,
                                     @Param("jsbh") String jsbh);
    

}
