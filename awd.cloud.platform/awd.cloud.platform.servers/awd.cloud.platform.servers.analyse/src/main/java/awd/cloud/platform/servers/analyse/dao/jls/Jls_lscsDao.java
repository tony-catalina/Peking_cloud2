package awd.cloud.platform.servers.analyse.dao.jls;

import awd.cloud.platform.servers.analyse.vo.AnalysisJlsResultVO;
import awd.framework.common.dao.api.Dao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Author：YaoBen
 * Date：2020-02-07 14:08
 * Description：<描述>
 */
public interface Jls_lscsDao extends Dao {


    //临时出所=上位机版本
    List<AnalysisJlsResultVO> swj_lslsfxCount(@Param("starttime")String starttime, @Param("endtime")String endtime);

    /**
     * 临时出所昨日出所
     */
    List<Map<String, Object>> lscsZrcs(@Param("jsbh")String jsbh);

    /**
     * 临时出所昨日出所未归
     */
    List<Map<String, Object>> lscsZrcswg(@Param("jsbh")String jsbh);


    /**
     * 临时出所今日出所
     */
    List<Map<String, Object>> lscsJrcs(@Param("jsbh")String jsbh);

    /**
     * 临时出所今日出所未归
     */
    List<Map<String, Object>> lscsJrcswg(@Param("jsbh")String jsbh);

    /**
     * 临时出所本周出所
     */
    List<Map<String, Object>> lscsBzcs(@Param("jsbh")String jsbh);


    /**
     * 临时出所本周出所未归
     */
    List<Map<String, Object>> lscsBzcswg(@Param("jsbh")String jsbh);

    /**
     * 临时出所本月出所
     */
    List<Map<String, Object>> lscsBycs(@Param("jsbh")String jsbh);

    /**
     * 临时出所本月出所未归
     */
    List<Map<String, Object>> lscsBycswg(@Param("jsbh")String jsbh);
}
