package awd.cloud.platform.servers.analyse.dao.jds;

import awd.framework.common.dao.api.Dao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface Jds_JbxxDao extends Dao {

    //出所人数分析查询=上位机
    List<Map<String, Object>> swj_csrsFX(@Param("starttime") String starttime,
                                                @Param("endtime") String endtime);

    //上位机统计分析戒毒所--人员年龄分析
    List<Map<String,Object>> swjJdsRynlFx(@Param("starttime") String starttime , @Param("endtime") String endtime);

 //上位机戒毒所在戒人数分析查询
    List<Map<String, Object>> swj_zjrsFX(@Param("starttime") String starttime,
                                         @Param("endtime") String endtime);

//人员吸毒时间分析查询=上位机
    public List<Map<String, Object>> swj_xdsjFX(@Param("starttime") String starttime,
                                                @Param("endtime") String endtime);}

   