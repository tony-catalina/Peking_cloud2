package awd.cloud.platform.servers.analyse.dao.jls;

import awd.cloud.platform.servers.analyse.vo.AnalysisJlsResultVO;
import awd.framework.common.dao.api.Dao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Author：YaoBen
 * Date：2020-02-07 13:38
 * Description：<描述>
 */
public interface Jls_lshjDao extends Dao {

    List<AnalysisJlsResultVO> lshjInfo(@Param(value="hjsj")String hjsj,
                                       @Param(value="jssj")String jssj);


}
