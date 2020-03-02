package awd.cloud.platform.servers.analyse.dao.jls;

import awd.cloud.platform.servers.analyse.vo.AnalysisJlsResultVO;
import awd.framework.common.dao.api.Dao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Jls_JypzDao extends Dao {

    List<AnalysisJlsResultVO> queryJypzNum(@Param(value = "starttime")String starttime,
                                           @Param(value = "endtime")String endtime);
}
