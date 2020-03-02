package awd.cloud.platform.servers.analyse.dao.kss;

import awd.framework.common.dao.api.Dao;

import java.util.Map;

/**
 * Author：YaoBen
 * Date：2019-11-11 16:10
 * Description：<描述>
 */
//全市监所羁押分布
public interface JSJYDao extends Dao {
     //kss
    Map<String,Object> jSJY(String jsbh);
    //jls
    Map<String,Object> jls_jSJY(String jsbh);
}
