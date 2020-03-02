package awd.cloud.platform.servers.analyse.dao.jls;

import awd.framework.common.dao.api.Dao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface Jls_SqdtfxhyDao extends Dao {

    List<Map<String, Object>> sqdtfxhyCount( @Param("jsbh")String jsbh,@Param("starttime")String starttime,
            @Param("endtime")String endtime);

}
