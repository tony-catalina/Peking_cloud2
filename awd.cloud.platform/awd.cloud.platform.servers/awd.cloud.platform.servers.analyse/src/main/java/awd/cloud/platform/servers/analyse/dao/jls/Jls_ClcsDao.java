package awd.cloud.platform.servers.analyse.dao.jls;

import awd.cloud.platform.servers.analyse.vo.AnalysisJlsResultVO;
import awd.framework.common.dao.api.Dao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface Jls_ClcsDao extends Dao {

    List<AnalysisJlsResultVO> ClcsInfo(@Param("starttime")String starttime,
                                       @Param("endtime")String endtime);

    Map<String, Object> qmcsYwdt(@Param("starttime")String starttime,
                                 @Param("endtime")String endtime,
                                 @Param("jsbh")String jsbh);

    Map<String, Object> ncsrsYwdt(@Param("jsbh") String jsbh);

    Map<String, Object> tqjcYwdt(@Param("starttime")String starttime,
                                 @Param("endtime")String endtime,
                                 @Param("jsbh")String jsbh);
}
