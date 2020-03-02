package awd.cloud.platform.servers.analyse.dao.jls;

import awd.cloud.platform.servers.analyse.vo.AnalysisJlsResultVO;
import awd.cloud.platform.servers.analyse.vo.AnalysisResultVO;
import awd.framework.common.dao.api.Dao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface Jls_TsdjDao extends Dao {

    /**
     * 查询提审登记数量
     */
    List<AnalysisJlsResultVO> queryTsdjNum(@Param(value = "kssj")String starttime,
                                           @Param(value = "jssj")String endtime);
}
