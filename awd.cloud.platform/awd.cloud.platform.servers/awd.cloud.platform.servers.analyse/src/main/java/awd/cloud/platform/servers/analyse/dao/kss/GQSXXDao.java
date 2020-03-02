package awd.cloud.platform.servers.analyse.dao.kss;

import awd.framework.common.dao.api.Dao;

import java.util.List;
import java.util.Map;

/**
 * Author：YaoBen
 * Date：2019-11-12 10:08
 * Description：<描述>
 */

public interface GQSXXDao extends Dao {

    //kss各区所信息 页面中间偏下 律师会见。。。
    List<Map<String  ,String>> select_GQSXX(String jsbh);

    //kss鼠标悬浮 查询 设计押量  在编警力
    List<Map<String  ,String>> select_xf(String jsbh);


    //2jls各区所信息 页面中间偏下 律师会见。。。
    List<Map<String  ,String>> select_jls_GQSXX(String jsbh);

    //2jls鼠标悬浮 查询 设计押量  在编警力
    List<Map<String  ,String>> select_jls_xf(String jsbh);
}
