package awd.cloud.platform.servers.analyse.dao.kss;

import awd.framework.common.dao.api.Dao;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Author：YaoBen
 * Date：2019-11-28 15:56
 * Description：<描述>
 */
public interface BJSDY_kss_zdgzDao extends Dao {
    //重点关注人员
    ArrayList<HashMap<String,Object>> select_zdgz(String jsbh);
}
