package awd.cloud.platform.servers.analyse.dao.jls;

import awd.framework.common.dao.api.Dao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface Jls_JbjlDao extends Dao {

    List<Map<String, Object>> dpzbld(@Param("jsbh")String jsbh);

}
