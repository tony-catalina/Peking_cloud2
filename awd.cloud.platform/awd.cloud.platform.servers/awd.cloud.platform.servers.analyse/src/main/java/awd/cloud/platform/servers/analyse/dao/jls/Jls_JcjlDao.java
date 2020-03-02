package awd.cloud.platform.servers.analyse.dao.jls;

import awd.framework.common.dao.api.Dao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface Jls_JcjlDao extends Dao {

    List<Map<String, Object>> jcjlFx(@Param("starttime")String starttime,
                                     @Param("endtime")String endtime,
                                     @Param("badw")String badw);


    List<Map<String, Object>> jcjlyw(@Param("cpgj")String cpgj,
                                     @Param("cprq")String cprq,
                                     @Param("ly")String ly,
                                     @Param("gjyj")String gjyj);
}
