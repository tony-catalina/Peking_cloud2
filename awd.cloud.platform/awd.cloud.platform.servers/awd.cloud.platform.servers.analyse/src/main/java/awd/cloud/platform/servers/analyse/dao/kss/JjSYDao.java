package awd.cloud.platform.servers.analyse.dao.kss;

import awd.framework.common.dao.api.Dao;

import java.util.Map;

/**
 * Author：YaoBen
 * Date：2019-11-11 15:38
 * Description：<描述>
 */

public interface JjSYDao  extends Dao {
    //kss械具
    Map<String,Object> jjSY(String jsbh);
    //jls械具数量
    Map<String,Object> jls_jjSY(String jsbh);

}
