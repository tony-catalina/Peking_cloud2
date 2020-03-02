package awd.cloud.platform.servers.analyse.dao.jls;

import awd.cloud.platform.servers.analyse.vo.AnalysisJlsResultVO;
import awd.framework.common.dao.api.Dao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface Jls_EmDao extends Dao {

    List<Map<String, Object>> emCount( @Param("jsbh")String jsbh,@Param("starttime")String starttime,
            @Param("endtime")String endtime);

    List<AnalysisJlsResultVO> queryEmdjNum(@Param(value = "starttime")String starttime,
                                           @Param(value = "endtime")String endtime);

}
