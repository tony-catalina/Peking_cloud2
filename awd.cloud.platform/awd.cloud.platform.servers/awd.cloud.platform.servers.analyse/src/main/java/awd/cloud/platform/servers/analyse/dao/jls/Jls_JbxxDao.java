package awd.cloud.platform.servers.analyse.dao.jls;

import awd.cloud.platform.servers.analyse.vo.AnalysisJlsResultVO;
import awd.framework.common.dao.api.Dao;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Jls_JbxxDao extends Dao {


    //超期羁押=上位机版本
    List<AnalysisJlsResultVO> swj_cqjyCount(@Param("starttime") String starttime,@Param("endtime") String endtime);

    //离婚=上位机版本
    List<Map<String,Object>> swj_hyzklh(@Param("starttime")String starttime,
                                        @Param("endtime")String endtime);
    //丧偶=上位机版本
    List<Map<String,Object>> swj_hyzkso(@Param("starttime")String starttime,
                                        @Param("endtime")String endtime);
    //有生理缺陷=上位机版本
    List<Map<String,Object>> swj_jkzkslqx(@Param("starttime")String starttime,
                                          @Param("endtime")String endtime);
    //残疾=上位机版本
    List<Map<String,Object>> swj_jkzkcj(@Param("starttime")String starttime,
                                        @Param("endtime")String endtime);

    //离婚
    List<Map<String,Object>> hyzklh();
    //丧偶
    List<Map<String,Object>> hyzkso();
    //有生理缺陷
    List<Map<String,Object>> jkzkslqx();
    //残疾
    List<Map<String,Object>> jkzkcj();

    List<Map<String, Object>> queryClgyfxNum(@Param(value="starttime")String starttime,@Param(value = "endtime")String endtime);

    List<Map<String, Object>> ZdryInfo(@Param(value="starttime")String starttime,
                                       @Param(value="endtime")String endtime);

    //查看违规程度
    ArrayList<HashMap<String,Object>> select_wgcd(@Param("jsbh") String jsbh);

    //查看违规类型
    ArrayList<HashMap<String,Object>> select_wglx(@Param(value="starttime")String starttime,
                                                  @Param(value="endtime")String endtime);

    //上位机违规情况
    List<HashMap<String,Object>> swj_wglx(@Param(value="starttime")String starttime,
                                                  @Param(value="endtime")String endtime);

    //超期羁押
    List<Map<String,Object>> cqjyCount();

    //所有拘留所在押数量
    List<Map<String,Object>> alljbxxCount();

    /**
     * 查询关押期限数量
     */
    List<Map<String, Object>> queryGyqxNum(@Param(value = "starttime")String starttime,
                                           @Param(value = "endtime")String endtime);

    List<AnalysisJlsResultVO> jls_rscx(@Param("starttime")String starttime, @Param("endtime") String endtime);



    List<Map<String, Object>> zcrsCount( @Param("jsbh")String jsbh,@Param("starttime")String starttime,
            @Param("endtime")String endtime);

    List<Map<String, Object>> jsxkh(@Param("starttime")String starttime,
            @Param("jsbh")String jsbh);

    Integer zyrsQuery();
    
    //大屏在所人员
    Map<String, Object> dpzsry(@Param("jsbh") String jsbh);
    
    //大屏安全管理
    Map<String, Object> dpaqgl(@Param("jsbh") String jsbh);
    
    //大屏提讯会见
    Map<String, Object> dptxhj(@Param("jsbh") String jsbh);
    
    //大屏关押期限
    Map<String, Object> dpcqjy(@Param("jsbh") String jsbh);
    
    //大屏诉讼情况分类
    Map<String, Object> dprsxz(@Param("jsbh") String jsbh);
    
    //大屏案件情况分类
    Map<String, Object> dpajlb(@Param("jsbh") String jsbh);
    
  //分所大屏各监区滑动卡牌
    List<Map<String, Object>> dpjqhdkp(@Param("jsbh") String jsbh);

    //大屏在押人员风险因素
    Map<String, Object> dpzyryFxys(@Param("rybh") String rybh);

    //大屏在押人员案件查看
    List<Map<String,Object>> select_ajfx(@Param("rybh") String rybh);


    //大屏在押人员所内就医查看
    List<Map<String,Object>> select_snjy(@Param("rybh") String rybh);

    //大屏在押人员所外就医查看
    List<Map<String,Object>> select_swjy(@Param("rybh") String rybh);

    /*在押人员关押量分析*/
    List<Map<String, Object>> zyrygyl();


    /*在押人员户籍地分析*/
    List<Map<String, Object>> hjdfx();

    /*在押人员国籍分析*/
    List<Map<String, Object>> gjfx();

    /*在押人员年龄分析*/
    List<Map<String, Object>> nlfx();

    /*在押人员文化程度分析*/
    List<Map<String, Object>> whcd();

    /*在押人员身份分析*/
    List<Map<String, Object>> sffx();

    /*在押人员健康情况分析*/
    List<Map<String, Object>> jkqkfx();

    /*在押人员病号分析*/
    List<Map<String, Object>> bhfx();
}
