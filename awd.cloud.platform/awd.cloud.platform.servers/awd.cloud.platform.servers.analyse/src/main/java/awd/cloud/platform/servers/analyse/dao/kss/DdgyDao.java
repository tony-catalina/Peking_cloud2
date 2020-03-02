package awd.cloud.platform.servers.analyse.dao.kss;

import awd.framework.common.dao.api.Dao;

import java.util.Map;

/**
 * Author：YaoBen
 * Date：2019-11-11 14:01
 * Description：<描述>
 */
//大屏单独关押情况
public interface DdgyDao  extends Dao {
   //大屏看守所单独关押情况
   Map<String, Object> ddgy(String jsbh);
   //大屏拘留所单独关押情况
   Map<String, Object> jls_ddgy(String jsbh);
}
