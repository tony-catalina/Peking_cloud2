package awd.cloud.platform.servers.analyse.dao.all;

import awd.framework.common.dao.api.Dao;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Author：YaoBen
 * Date：2019-12-20 15:08
 * Description：<描述>
 */
public interface ZYRY_jbxxDao extends Dao {

    //在押人员==基本信息
    HashMap<String,Object> selectJbxx(String rybh);
    //在押人员==基本信息=照片
    ArrayList<HashMap<String,Object>> selectJbxxImg(String rybh);

}
