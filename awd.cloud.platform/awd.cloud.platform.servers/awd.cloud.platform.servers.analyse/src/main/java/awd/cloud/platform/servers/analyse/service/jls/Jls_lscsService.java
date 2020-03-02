package awd.cloud.platform.servers.analyse.service.jls;

import awd.cloud.platform.servers.analyse.dao.jls.Jls_lscsDao;
import awd.cloud.platform.servers.analyse.vo.AnalysisJlsResultVO;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Author：YaoBen
 * Date：2020-02-07 14:07
 * Description：<描述>
 */
@Service
public class Jls_lscsService {

    @Autowired
    private Jls_lscsDao jls_lscsDao;



    //临时出所=上位机版本
    @UseDataSource("jls_ds")
    public List<AnalysisJlsResultVO> swj_lslsfxCount(String starttime, String endtime) {
        return jls_lscsDao.swj_lslsfxCount(starttime,endtime);
    }

    /**
     * 临时出所昨日出所
     */
    @UseDataSource("jls_ds")
    public List<Map<String, Object>> lscsZrcs(String jsbh) {
        return jls_lscsDao.lscsZrcs(jsbh);
    }

    /**
     * 临时出所昨日出所未归
     */
    @UseDataSource("jls_ds")
    public List<Map<String, Object>> lscsZrcswg(String jsbh) {
        return jls_lscsDao.lscsZrcswg(jsbh);
    }

    /**
     * 临时出所今日出所
     */
    @UseDataSource("jls_ds")
    public List<Map<String, Object>> lscsJrcs(String jsbh) {
        return jls_lscsDao.lscsJrcs(jsbh);
    }

    /**
     * 临时出所今日出所未归
     */
    @UseDataSource("jls_ds")
    public List<Map<String, Object>> lscsJrcswg(String jsbh) {
        return jls_lscsDao.lscsJrcswg(jsbh);
    }

    /**
     * 临时出所本周出所
     */
    @UseDataSource("jls_ds")
    public List<Map<String, Object>> lscsBzcs(String jsbh) {
        return jls_lscsDao.lscsBzcs(jsbh);
    }

    /**
     * 临时出所本周出所未归
     */
    @UseDataSource("jls_ds")
    public List<Map<String, Object>> lscsBzcswg(String jsbh) {
        return jls_lscsDao.lscsBzcswg(jsbh);
    }

    /**
     * 临时出所本月出所
     */
    @UseDataSource("jls_ds")
    public List<Map<String, Object>> lscsBycs(String jsbh) {
        return jls_lscsDao.lscsBycs(jsbh);
    }

    /**
     * 临时出所本月出所未归
     */
    @UseDataSource("jls_ds")
    public List<Map<String, Object>> lscsBycswg(String jsbh) {
        return jls_lscsDao.lscsBycswg(jsbh);
    }


}
