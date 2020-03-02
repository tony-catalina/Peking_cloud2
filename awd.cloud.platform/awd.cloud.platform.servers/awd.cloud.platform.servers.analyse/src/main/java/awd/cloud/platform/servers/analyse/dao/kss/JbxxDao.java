/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.servers.analyse.dao.kss;

import awd.cloud.platform.servers.analyse.vo.AnalysisResultVO;
import awd.framework.common.dao.api.Dao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface JbxxDao extends Dao {




    /**
     * 查询国籍数量
     */
    List<Map<String, Object>> queryGjNum(@Param(value = "jyrq")String jyrq,
                                         @Param(value = "starttime")String starttime,
                                         @Param(value = "endtime") String endtime);

    /**
     * 查询关押期限数量
     */
    List<Map<String, Object>> queryGyqxNum(@Param(value = "starttime")String starttime,
                                           @Param(value = "endtime")String endtime);


    /**
     * 查询性别统计
     */
    List<Map<String,Object>> queryByXb(@Param(value = "starttime")String starttime,
                                       @Param(value = "endtime")String endtime,@Param("jsbh")String jsbh);

    /**
     * 查询文化程度统计
     */
    List<Map<String,Object>> queryByWhcd(@Param(value = "starttime")String starttime,
                                       @Param(value = "endtime")String endtime,@Param("jsbh")String jsbh);

    /**
     * 查询入所性质统计
     */
    List<Map<String,Object>> queryByRsxz(@Param(value = "starttime")String starttime,
                                         @Param(value = "endtime")String endtime,@Param("jsbh")String jsbh);

    /**
     * 查询户籍地统计
     */
    List<Map<String,Object>> queryByHjd(@Param(value = "starttime")String starttime,
                                         @Param(value = "endtime")String endtime,@Param("jsbh")String jsbh);

    /**
     * 收押入所业务动态，按性别分析
     */
    List<Map<String,Object>> queryForSyrsYwdz(@Param(value = "jsbh")String jsbh,
                                              @Param(value = "startTime")String startTime,
                                                @Param(value = "endTime")String endTime);

    /**
     * 入所总人数统计
     */
    List<Map<String,Object>> queryRszrs(@Param(value = "starttime")String starttime,
                                        @Param(value = "endtime")String endtime,@Param("jsbh")String jsbh);
    /**
     * 关押量人数统计
     */
     Integer zyrsQuery();

    /**
     * 在押总人数统计
     */
    List<Map<String,Object>> queryZyzrs(@Param(value = "starttime")String starttime,
                                        @Param(value = "endtime")String endtime,@Param("jsbh")String jsbh);
    /**
     * 拒收总人数统计
     */
    List<Map<String,Object>> queryJs(@Param(value = "starttime")String starttime,
                                     @Param(value = "endtime")String endtime,@Param("jsbh")String jsbh);

    Map<String,Object> getJbxxR8Count(@Param(value = "jsbh")String jsbh);

    List<Map<String,Object>> getJbxxR8ByField(@Param(value = "jsbh")String jsbh, @Param(value = "field")String field, @Param(value = "value")String value);

    //流水牌>基本信息
    int getJbxxCountByField (@Param(value = "jsbh")String jsbh,@Param(value = "field")String field,
                             @Param(value = "value")String value);

    //点名
    List<Map<String,Object>> getJbxxForDm();

    //械具，严管人员，单独关押人数统计
    Map<String,Object> aqglQuery();

    //统计
    List<Map<String,Object>> jianShiFuW(@Param(value = "jsbh")String jsbh,@Param(value = "jsh")String jsh);

    int getJsBhrsByBhlx(@Param("jsbh") String jsbh, @Param("jsh") String jsh,@Param("field")String field,@Param("value")String value);

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
    
    //涉黑涉恶
    List<Map<String,Object>> shseCount();

    //重点人员
    List<Map<String,Object>> zdryCount();

    
    //国籍分析
    List<Map<String,Object>> gjfxCount();
    
    //超期羁押
    List<Map<String,Object>> cqjyCount();

    //超期羁押=上位机版本
    List<Map<String,Object>> swj_cqjyCount(@Param("starttime") String starttime,@Param("endtime") String endtime,@Param("zszt") String zszt);
    
    //所有看守所在押数量
    List<Map<String,Object>> alljbxxCount();
    
    //	<!-- 关押期限分析 3  3-6月   6-1年 --> 
    List<Map<String,Object>> gyqxfxCount();
    
//		<!-- 案件情况分析 -->
    List<Map<String,Object>> ajqkfxCount();

    //上位机版本=案件情况分析
    List<AnalysisResultVO> swjAjqkfx(@Param("starttime")String starttime,@Param("endtime")String endtime,@Param("ay") String ay);

	//	<!-- 留所服刑分析 -->
	List<Map<String,Object>> lsfxfxCount();

    //	<!-- 留所服刑分析=上位机版本 -->
    List<Map<String,Object>> swj_lsfxfxCount(@Param("starttime")String starttime,@Param("endtime")String endtime);
	
//	<!-- 严重疾病分析 -->
	List<Map<String,Object>> yzjbfxCount();

    //	<!-- 严重疾病分析=上位机版本 -->
    List<Map<String,Object>> swj_yzjbfxCount(@Param("starttime")String starttime,@Param("endtime")String endtime);
	
//	投送未收分析
	List<Map<String,Object>> tswsfxCount();
	
	//	临时离所分析
	List<Map<String,Object>> lslsfxCount();

    //临时离所分析==上位机版本
    List<Map<String,Object>> swj_lslsfxCount(@Param("starttime")String starttime,@Param("endtime")String endtime);

    //送监未收人员==上位机版本
    List<Map<String,Object>> swj_sjwsry(@Param("starttime")String starttime,@Param("endtime")String endtime);
	
	//大屏安全管理
	Map<String, Object> dpaqgl(@Param("jsbh") String jsbh);
    //监室流水牌
    //根据特地字段查询
    int getJbxxCountByFieldWithJs(@Param("jsbh") String jsbh,@Param("jsh") String jsh, @Param("field") String field, @Param("value") String value);
    //监室人数
    int getJbxxR8CountWithJs(@Param("jsbh") String jsbh, @Param("jsh") String jsh);

    int queryZyzrsWithJs(@Param("starttime")String date,@Param("endtime")String date1,@Param("jsbh")String jsbh,@Param("jsh")String jsh);


    //kss分所大屏在所人员
    Map<String, Object> select_zsry(String jsbh);

    //kss分所大屏在所人员=今日入所人员信息
    ArrayList<Map<String, Object>> select_zsryxx(@Param(value = "bahj")String bahj,
                                                 @Param(value = "rybh")String rybh,
                                                 @Param(value = "jsbh") String jsbh,
                                                 @Param(value = "rsrq")String rsrq,
                                                 @Param(value = "crjbj")String crjbj,
                                                 @Param(value = "jsh")String jsh
    );


    //医疗情况==上位机版本
    ArrayList<Map<String,Object>> swj_ylgl(@Param("starttime") String starttime, @Param("endtime") String endtime);

    //kss分所大屏医疗管理
    Map<String,Object> select_ylgl(String jsbh);
    //kss分所大屏提讯会见
    Map<String,Object> select_txhj(String jsbh);
    //KSS分所大屏关押期限
    HashMap<String,Object> select_gyqx(String jsbh);

    //kss分所大屏各监区抱病情况趋势图
    ArrayList<HashMap<String,Object>> select_jqbbDY(@Param("jsbh") String jsbh, @Param("jqh") String jqh);
    //kss分所大屏各监区名称
    ArrayList<HashMap<String,Object>> select_jqbbmc (@Param("jsbh") String jsbh);

    // kss分所大屏在岗民警 协警--
    ArrayList<Map<String, Object>> select_zbmj(String jsbh);
    //kss分所大屏值班领导
    ArrayList<Map<String, Object>> select_zbld(String jsbh);
    //kss分所大屏值班领导手机号
    ArrayList<Map<String, Object>> select_sjh(@Param("jsbh") String jsbh,@Param("xm") String xm);

    //kss分所大屏重点关注人员信息
    ArrayList<HashMap<String,Object>> select_zdgzry(@Param("jsbh") String jsbh,@Param("rybh") String rybh);

    //kss分所大屏重点关注人员
    ArrayList<HashMap<String,Object>> select_zdgz(@Param("jsbh") String jsbh);
    //kss分所大屏重点关注人员照片
    ArrayList<HashMap<String,Object>> select_zdgzImg(@Param("rybh") String rybh);

    //kss大屏监区滑动菜单===下面卡牌各监区数据 占比
    ArrayList<HashMap<String,Object>> select_hdsjDY(@Param("jsbh") String jsbh,@Param("jsh") String jsh);

    //kss大屏百分比下面的图 带其他的这幅图
    @UseDataSource("kss_ds")
    ArrayList<HashMap<String,Object>> select_ssqq(String jsbh);

    //kss大屏诉讼情况分类与犯罪类型分析--其他数量
    @UseDataSource("kss_ds")
    ArrayList<HashMap<String,Object>>select_qt(String jsbh);

    //kss大屏诉讼情况分类与犯罪类型分析--页面百分比
    @UseDataSource("kss_ds")
    ArrayList<HashMap<String,Object>> select_bfb(String jsbh);

    //kss查看违规情况=上位机版本
    ArrayList<HashMap<String,Object>>swj_wgqk(@Param("starttime") String starttime,@Param("endtime") String endtime);

     //大屏kss查看监区号和监区名称
    ArrayList<HashMap<String,Object>>select_jsh(String jsbh);
    //大屏kss监区违规情况监视图==详细人员数据
    ArrayList<HashMap<String,Object>>select_jqwgry(@Param("jsbh") String jsbh, @Param("jqh") String jqh);
    //大屏kss监区违规情况监视图==详细人员数据==主管民警和协管民警
    ArrayList<HashMap<String,Object>>select_jqwgry_zgxg(@Param("jsbh") String jsbh, @Param("jsh") String jsh);


    //大屏kss查看违规程度
    ArrayList<HashMap<String,Object>>select_wgcd(@Param("jsbh") String jsbh);
    //大屏kss查看违规程度=里面联动下面的数据
    ArrayList<HashMap<String,Object>>select_wgcd_ld(@Param("jsbh") String jsbh);
    //大屏kss查看违规程度=里面联动下面的数据=数据详查rybh
    ArrayList<HashMap<String,Object>>select_wgcd_ld_rybh(@Param("jsbh") String jsbh,
                                                         @Param("wgqk") String wgqk);

    //大屏在押人员风险因素
    Map<String, Object> dpzyryFxys(@Param("jsbh") String jsbh, @Param("rybh") String rybh);

    //根据入所日期统计人员数量
    List<Map<String, Object>> rqcxR(@Param("starttime")String starttime, @Param("endtime") String endtime);

    //入所情况查询=上位机版本
    List<AnalysisResultVO> swj_rsqk(@Param("starttime")String starttime, @Param("endtime") String endtime);

    /**
     * 查询国籍数量
     */
    List<Map<String, Object>> queryGjfxNum(@Param(value = "jyrq")String jyrq,
                                         @Param(value = "starttime")String starttime,
                                         @Param(value = "endtime") String endtime);

}

