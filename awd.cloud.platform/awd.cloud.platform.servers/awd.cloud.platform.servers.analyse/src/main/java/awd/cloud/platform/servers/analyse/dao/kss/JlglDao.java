package awd.cloud.platform.servers.analyse.dao.kss;

import awd.framework.common.dao.api.Dao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface JlglDao extends Dao {
    /**
     * 奖励管理业务动态
     */
    List<Map<String,Object>> jlglYwdt(@Param(value = "starttime")String starttime, @Param(value = "endtime")String endtime, @Param("jsbh")String jsbh,@Param("jsh")String jsh);
}
