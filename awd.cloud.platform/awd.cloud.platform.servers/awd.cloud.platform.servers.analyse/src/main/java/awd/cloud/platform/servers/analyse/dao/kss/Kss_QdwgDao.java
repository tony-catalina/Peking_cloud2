package awd.cloud.platform.servers.analyse.dao.kss;

import awd.framework.common.dao.api.Dao;

import java.util.List;
import java.util.Map;

/**
 * @ author：DS
 * @ Date：2019/12/27 9:46
 * @ Description:
 */
public interface Kss_QdwgDao extends Dao {
    //轻度违规情况分析
    List<Map<String,Object>> qdwgCount();

}
