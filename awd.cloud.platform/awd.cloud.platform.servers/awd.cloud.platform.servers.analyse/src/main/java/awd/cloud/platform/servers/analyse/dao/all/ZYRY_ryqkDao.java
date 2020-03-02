package awd.cloud.platform.servers.analyse.dao.all;

import awd.framework.common.dao.api.Dao;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Author：YaoBen
 * Date：2019-12-20 16:00
 * Description：<描述>
 */
public interface ZYRY_ryqkDao extends Dao {

    //案件情况
    ArrayList<HashMap<String,Object>> selectAjqk(String rybh);
    //身体状况
    ArrayList<HashMap<String,Object>> selectStzk(String rybh);
    //精神状态
    ArrayList<HashMap<String,Object>> selectJszt(String rybh);
    //2风险等级
    ArrayList<HashMap<String,Object>> selectFxdj(String rybh);
}
