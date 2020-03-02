package awd.cloud.platform.servers.analyse.dao.jls;

import awd.framework.common.dao.api.Dao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface Jls_SwjyDao extends Dao {

    List<Map<String, Object>> swjyCount( @Param("jsbh")String jsbh,@Param("starttime")String starttime,
            @Param("endtime")String endtime);
    
    Map<String, Object> dpylgl( @Param("jsbh")String jsbh);
    
    List<Map<String, Object>> swjyBym( @Param("jsbh")String jsbh);


    //jls 总队全市出所就医
    Map<String ,Object> select_jls_QSCSJY();

}
