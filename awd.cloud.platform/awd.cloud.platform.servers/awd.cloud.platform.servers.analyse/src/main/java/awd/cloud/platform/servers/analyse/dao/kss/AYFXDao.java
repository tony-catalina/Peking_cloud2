package awd.cloud.platform.servers.analyse.dao.kss;

import awd.framework.common.dao.api.Dao;

import java.util.ArrayList;
import java.util.Map;


/**
 * Author：YaoBen
 * Date：2019-11-13 15:54
 * Description：<描述>
 */
//案由分析 看守所
public interface AYFXDao extends Dao {

    ArrayList<Map<String,Object>> select_AYFX();
}
