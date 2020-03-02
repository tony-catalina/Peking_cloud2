package awd.cloud.platform.servers.analyse.dao.jls;

import awd.framework.common.dao.api.Dao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface Jls_JkqkDao extends Dao {
    //拘留所身体状况
    Map<String, Object> jkqkCount(@Param("jsbh")String jsbh, @Param("rybh")String rybh);
}
