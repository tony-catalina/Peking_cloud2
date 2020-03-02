package awd.cloud.platform.servers.analyse.dao.jds;

import awd.framework.common.dao.api.Dao;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * Author：YaoBen
 * Date：2019-11-16 9:40
 * Description：<描述>
 */
public interface Jds_DsjPTDao  extends Dao {
    //大屏戒毒所在押人数
    Map<String,Object> zyRS(@Param(value = "starttime")String starttime,
                            @Param(value = "endtime")String endtime);


}
