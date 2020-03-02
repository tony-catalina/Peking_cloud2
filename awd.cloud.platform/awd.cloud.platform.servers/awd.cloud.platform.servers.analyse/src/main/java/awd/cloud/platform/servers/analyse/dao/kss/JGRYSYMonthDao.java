package awd.cloud.platform.servers.analyse.dao.kss;

import awd.framework.common.dao.api.Dao;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Author：YaoBen
 * Date：2019-11-13 14:35
 * Description：<描述>
 */
public interface JGRYSYMonthDao  extends Dao {

    //全市被监管人员收押情况   月
    ArrayList<HashMap<String,Object>> select_JGRYSYMonth();


}
