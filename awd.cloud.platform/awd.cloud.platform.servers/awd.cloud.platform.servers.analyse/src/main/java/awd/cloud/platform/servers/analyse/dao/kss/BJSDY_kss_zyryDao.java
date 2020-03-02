package awd.cloud.platform.servers.analyse.dao.kss;

import awd.framework.common.dao.api.Dao;

import java.util.Map;

/**
 * Author：YaoBen
 * Date：2019-11-23 16:20
 * Description：<描述>
 */
public interface BJSDY_kss_zyryDao extends Dao {

    //分所大屏在所人员
   Map<String, Object> select_zsry(String jsbh);
    //分所大屏在所人员=今日入所人员信息
    //ArrayList<HashMap<String, Object>> select_zsryxx(Object jsbh);

}
