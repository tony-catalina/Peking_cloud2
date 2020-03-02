package awd.cloud.platform.servers.analyse.dao.kss;

import awd.framework.common.dao.api.Dao;

import java.util.Map;

/**
 * Author：YaoBen
 * Date：2019-11-13 10:47
 * Description：<描述>
 */
public interface CSJYPMDao extends Dao {

    //1 kss出所就医排名
   Map<String , Object> select_CSJYPM(String jsbh);

    //2 jls出所就医排名
    Map<String , Object> select_jls_CSJYPM(String jsbh);
}
