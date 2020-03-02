package awd.cloud.platform.servers.analyse.dao.kss;

import awd.framework.common.dao.api.Dao;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.Map;

/**
 * Author：YaoBen
 * Date：2019-11-30 11:46
 * Description：<描述>
 */
public interface BJSDY_kss_wgcdDao  extends Dao {

    //	<!-- 轻度违规分析 -->
    ArrayList<Map<String, Object>> qdwgCount(@Param("jsbh")String jsbh);
    //	<!-- 一般违规分析 -->
    ArrayList<Map<String, Object>> ybwgCount(@Param("jsbh")String jsbh);
    //	<!-- 重度违规分析 -->
    ArrayList<Map<String, Object>> zdwgCount(@Param("jsbh")String jsbh);
}
