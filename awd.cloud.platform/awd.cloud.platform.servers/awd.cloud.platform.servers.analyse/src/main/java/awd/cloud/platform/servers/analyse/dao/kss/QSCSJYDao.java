package awd.cloud.platform.servers.analyse.dao.kss;

import awd.framework.common.dao.api.Dao;

import java.util.Map;

/**
 * Author：YaoBen
 * Date：2019-11-13 9:05
 * Description：<描述>
 */

public interface QSCSJYDao extends Dao {

    //1kss全市出所就医
    Map<String ,Object> select_QSCSJY();


}
