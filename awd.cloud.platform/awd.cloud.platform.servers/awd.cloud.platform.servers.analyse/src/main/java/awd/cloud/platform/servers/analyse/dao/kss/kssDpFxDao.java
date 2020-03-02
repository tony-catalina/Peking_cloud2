/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.servers.analyse.dao.kss;

import awd.framework.common.dao.api.Dao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface kssDpFxDao extends Dao {
	//kss会见人数分析 : 家属会见、律师会见、提讯、总数
	List<Map<String, Object>>kss_hjrs();

	//kss在所状态 :   已决、未决
	List<Map<String, Object>>kss_zszt();

	//kss人员管理情况分析 ： 械具、禁闭、单独关押、严管人员、耳目
	List<Map<String, Object>>kss_rygl();

	//kss全省办案阶段分析
	List<Map<String, Object>>kss_qsbajd(@Param("jsbh")String jsbh);

	//kss年度入所量比对
	List<Map<String, Object>>kss_ndrs();

	//kss风险情况分析
	List<Map<String, Object>>kss_fxdj();

    //吸毒人员分析 吸毒人数、百分比
	List<Map<String, Object>>kss_xdry();
	//吸毒人员分析 吸毒人数、百分比
	Map<String, Object>kss_xdry_all();



    /*在押人员关押量分析*/
	List<Map<String, Object>> zyrygyl();

	/*户籍地分析*/
	List<Map<String, Object>> hjdfx();

	/*年龄分析*/
	List<Map<String, Object>> nlfx();

	/*文化程度分析*/
	List<Map<String, Object>> whcd();

	/*身份分析*/
	List<Map<String, Object>> sffx();

	/*健康情况分析*/
	List<Map<String, Object>> jkqkfx();

	/*拒收人员分析*/
	List<Map<String, Object>> jsryfx();

	/*入所性质分析*/
	List<Map<String, Object>> rsxz(String jsbh);

	/*案件类别分析*/
	List<Map<String, Object>> ajlb(String jsbh);

	/*正常出所对比分析*/
	List<Map<String, Object>> zccs();

	/*临时出所分析*/
	List<Map<String, Object>> lscsfx();

	/*出所原因分析*/
	List<Map<String, Object>> csyyfx(String jsbh);

	/*深挖犯罪*/
	List<Map<String, Object>> swfz();

	/*严重暴力犯罪*/
	List<Map<String, Object>> yzblfz();

	/*涉黑涉恶案件分析*/
	List<Map<String, Object>> shsefx(String jsbh);

	/*民警数量*/
	List<Map<String, Object>> mjsl();


	/*民警性别分析*/
	List<Map<String, Object>> mjxbfx();

	/*民警警衔分析*/
	List<Map<String, Object>> mjjxfx(String jsbh);

	/*在职时长分析*/
	List<Map<String, Object>> zzscfx();

	/*严重疾病分析*/
	List<Map<String, Object>> yzjbfx();

	/*民警学历分析*/
	List<Map<String, Object>> mjwhcd();
}
