package awd.cloud.platform.webs.charts.api;

import awd.cloud.platform.webs.charts.api.hystrix.AnalyseFallBackFactory;
import awd.cloud.platform.webs.charts.utils.ResponseMessage;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;



@FeignClient(name = "${bj.server.analyse:AWD-MIS-ANALYSE-SERVER}",url = "192.168.4.76:12104",fallbackFactory = AnalyseFallBackFactory.class)

@Component
public interface AnalyseApis {
	 //总关押量
	@GetMapping("/all/sy/gylQuery")
	public Map<String, Object> gylQuery();
	 //大屏看守所在押人数
	@GetMapping("/kss/dsjpt/findKss_dsjPT")
	public Map<String, Object> findKss_dsjPT();
	
	//监所羁押
	@GetMapping("/kss/jsjy/select_JSJY")
	public Map<String, Object> select_Jsjy(@RequestParam(value ="jsbh") String jsbh);
	
	//大屏单独关押 械具使用情况
	@GetMapping("/qsjs/ddgy/select_Dagy")
	public Map<String, Object> select_Dagy(@RequestParam(value ="jsbh") String jsbh);
	
	/*全市出所就医 swjy 所外就医  1是  0否,csjylx出所就医类型 1门诊  2住院,  jzyy就诊医院*/
	@GetMapping("/kss/qscsjy/select_QSCSJY")
	public Map<String, Object> select_QSCSJY(@RequestParam("jsbh") String jsbh);
	
	//案由分析
	@GetMapping("/kss/AYFXController/select_AYFX")
	public Map<String, Object> select_AYFX();
	
	 //今日监所警力部署
	@GetMapping("/kss/jsjl/select_JSJL")
	public Map<String, Object> select_JSJL(@RequestParam("jsbh") String jsbh);
	
	 //全市被监管人员收押情况
	@GetMapping("/kss/JGRYSYController/select_JGRYSY")
	public Map<String, Object> select_JGRYSY();
	
	//执法质量靠评
	@GetMapping("/Kss/Zfzlkp/findZfzlk")
	public Map<String, Object> findZfzlk();
	
	//地图红点
	@GetMapping("/kss/gqsxx/select_GQSXX")
	public Map<String, Object> select_GQSXX(@RequestParam(required = false,value = "jsbh") String jsbh);
	
	 //看守所在所人员
	@GetMapping("/kss/jbxx/select_zsry")
    public Map<String, Object> select_zsry(@RequestParam("jsbh") String jsbh);

	//看守所在所人员信息
	@GetMapping("/kss/jbxx/select_zsryxx")
	public Map<String,Object> select_zsryxx(@RequestParam(required = false, value = "bahj") String bahj,
											@RequestParam(required = false, value = "rybh") String rybh,
											@RequestParam(required = false, value = "jsbh") String jsbh,
											@RequestParam(required = false, value = "rsrq") String rsrq,
											@RequestParam(required = false, value = "crjbj") String crjbj,
											@RequestParam(required = false, value = "jsh") String jsh);


	//安全管理
	@GetMapping("/kss/aqgl/aqglQuery")
	public Map<String, Object> aqglQuery();
	
	//分所大屏医疗管理
	@GetMapping("/kss/jbxx/select_ylgl")
	public Map<String, Object> select_ylgl(@RequestParam("jsbh") String jsbh);
	
	//监区违规情况趋势图
	@GetMapping("/kss/jbxx/select_Jqwg")
	public Map<String, Object> select_Jqwg(@RequestParam("jsbh") String jsbh);
    //监区违规情况趋势图人员信息
    @GetMapping("/kss/jbxx/select_jqwgryxx")
    public Map<String, Object> select_jqwgryxx(@RequestParam(required = false, value = "jsbh") String jsbh, @RequestParam(required = false, value = "jqh") String jqh);
	
	//分所大屏提讯会见
	@GetMapping("/kss/jbxx/select_txhj")
	public Map<String, Object> select_txhj(@RequestParam("jsbh") String jsbh);
	
	//分所大屏关押期限
	@GetMapping("/kss/jbxx/select_gyqx")
	public Map<String, Object> select_gyqx(@RequestParam("jsbh") String jsbh);
	
	//分所大屏各监区抱病情况趋势图
	@GetMapping("/kss/jbxx/select_jqbb")
	public Map<String, Object> select_jqbb(@RequestParam("jsbh") String jsbh);
	
	//分所大屏值班领导 在岗民警 协警
	@GetMapping("/kss/jbxx/select_zbld")
	public Map<String, Object> select_zbld(@RequestParam("jsbh") String jsbh);
	
	//分所大屏各监区滑动卡牌
	@GetMapping("/kss/jbxx/select_hdsj")
    public HashMap<String,Object> select_hdsj(@RequestParam(required = false,value = "jsbh") String jsbh);
	
	//分所大屏各监区违规程度
	@GetMapping("/kss/jbxx/find_wgcd")
	public Map<String, Object> find_wgcd(@RequestParam("jsbh") String jsbh);
	//大屏监区违规程度点击==根据违规类型=一般违规
	@GetMapping("/kss/jbxx/select_wgcd_rybh")
	public LinkedHashMap<String, Object> select_wgcd_rybh(@RequestParam(required = false, value = "jsbh") String jsbh, @RequestParam(required = false, value = "wgqk") String wgqk
	);

	//分所大屏各监区重点人员
	@GetMapping("/kss/jbxx/select_zdgz")
	public HashMap<String, Object> select_zdgz(@RequestParam(required = false, value = "jsbh") String jsbh);
	//分所大屏各监区重点人员信息弹窗
	@GetMapping("/kss/jbxx/select_zdgzry")
	public Map<String, Object> select_zdgzry(@RequestParam(required = false, value = "jsbh") String jsbh,@RequestParam(required = false, value = "rybh") String rybh);
	
	// 诉讼情况分类与犯罪类型分析
	@GetMapping("/kss/jbxx/select_ssqq")
	public Map<String, Object> select_ssqq(@RequestParam("jsbh") String jsbh);
	
	//在押人员基本信息
	@GetMapping("/ZYRY_jbxx/selectJbxx")
	public Map<String, Object> selectJbxx(@RequestParam("rybh") String rybh);
	
	//在押人员人员情况
	@GetMapping("/ZYRY_ryqk/selectRyqk")
	public Map<String, Object> selectRyqk(@RequestParam("rybh") String rybh);
	
	//在押人员人员关系
	@GetMapping("/ZYRY_rygx/selectRygx")
	public Map<String, Object> selectRygx(@RequestParam("jsbh") String jsbh, @RequestParam("rybh") String rybh,
            @RequestParam("jsh") String jsh);
	//拘留所风险等级
	@GetMapping("/jls_zyry_rygx/jlsFxdj")
	public Map<String,Object> jlsFxdj(@RequestParam("rybh") String rybh);
	//法制型
	@GetMapping("/wxjs/fzx")
	public Map<String, Object> select_fzx();
	
	//服务型
	@GetMapping("/wxjs/select_fwx")
	public Map<String, Object> select_fwx();
	
	//安全型
	@GetMapping("/wxjs/select_aqx")
	public Map<String, Object> select_aqx();
	
	//监所评分
	@GetMapping("/wxjs/select_jspf")
	public Map<String, Object> select_jspf();
		
	//智慧型
	@GetMapping("/wxjs/select_zhx")
	public Map<String, Object> select_zhx();
	
	//廉洁型
	@GetMapping("/wxjs/select_ljx")
	public Map<String, Object> select_ljx();
	
	//医疗情况分析
	@GetMapping("/kss/jy/ylwsCount")
	public ResponseMessage<Map<String, Object>> ylwsCount();
	
	//人员分类分析
	@GetMapping("/kss/jbxx/ryflfxCount")
	public ResponseMessage<Map<String, Object>> ryflfxCount();
	
	//涉黑涉恶人员统计
	@GetMapping("/kss/jbxx/shseCount")
	public ResponseMessage<Map<String, Object>> shseCount();


	//重点人员分析
	@GetMapping("/kss/jbxx/zdryCount")
	public ResponseMessage<Map<String, Object>> zdryCount();

	
	//国籍分析
	@GetMapping("/kss/jbxx/gjfxCount")
	public ResponseMessage<Map<String, Object>> gjfxCount();
	
	//超期羁押
	@GetMapping("/kss/jbxx/cqjyCount")
	public ResponseMessage<Map<String, Object>> cqjyCount();
	
	//关押期限分析
	@GetMapping("/kss/jbxx/gyqxCount")
	public ResponseMessage<List<Map<String, Object>>> gyqxCount();
	
	//案件情况分析
	@GetMapping("/kss/jbxx/ajqkfxCount")
	public ResponseMessage<Map<String, Object>> ajqkfxCount();
	
	//留所服刑分析
	@GetMapping("/kss/jbxx/lsfxfxCount")
	public ResponseMessage<Map<String, Object>> lsfxfxCount();
	
	//严重疾病分析
	@GetMapping("/kss/jbxx/yzjbfxCount")
	public ResponseMessage<Map<String, Object>> yzjbfxCount();
	
	//违规事件分析
	@GetMapping("/kss/wgsjcl/wgqkfxCount")
	public ResponseMessage<Map<String, Object>> wgqkfxCount();
	
	//投送未收
	@GetMapping("/kss/jbxx/tswsfxCount")
	public ResponseMessage<Map<String, Object>> tswsfxCount();
	

	//违规事件分析
	@GetMapping("/kss/jbxx/lslsfxCount")
	public ResponseMessage<Map<String, Object>> lslsfxCount();

	//违规程度
	@GetMapping("/kss/wgcd/wgCount")
	public Map<String,Object> wgCount(@RequestParam("jsbh") String jsbh,@RequestParam("jsh") String jsh);
	
	
	//拘留所在所人员
	@GetMapping("/jls_jbxx/dpzsry")
	public Map<String, Object> dpzsry(@RequestParam("jsbh") String jsbh);
    //看守所安全管理
    @GetMapping("/kss/jbxx/kssdpaqgl")
    public Map<String, Object> kssdpaqgl(@RequestParam("jsbh") String jsbh);

	 //拘留所安全管理
	@GetMapping("/jls_jbxx/jlsdpaqgl")
	public Map<String, Object> jlsdpaqgl(@RequestParam("jsbh") String jsbh);
	
	
	 //拘留所医疗管理
	@GetMapping("/jls_swjy/jlsdpylgl")
	public Map<String, Object> jlsdpylgl(@RequestParam("jsbh") String jsbh);
	
	//拘留所提讯会见
	@GetMapping("/jls_jbxx/jlsdptxhj")
	public Map<String, Object> jlsdptxhj(@RequestParam("jsbh") String jsbh);
	
	//拘留所关押期限
	@GetMapping("/jls_jbxx/jlsdpcqjy")
	public Map<String, Object> jlsdpcqjy(@RequestParam("jsbh") String jsbh);
	
	//拘留所监区违规趋势图
	@GetMapping("/jls_xsjl/jlsdpjqwgqst")
	public Map<String, Object> jlsdpjqwgqst(@RequestParam("jsbh") String jsbh);
	
	//拘留所入所性质
	@GetMapping("/jls_jbxx/jlsdprsxz")
	public Map<String, Object> jlsdprsxz(@RequestParam("jsbh") String jsbh);
	
	//拘留所案由
	@GetMapping("/jls_jbxx/jlsdpajlb")
	public Map<String, Object> jlsdpajlb(@RequestParam("jsbh") String jsbh);
	
	//拘留所违规程度
	@GetMapping("/jls_xsjl/jlsdpjqwgry")
	public Map<String, Object> jlsdpjqwgry(@RequestParam("jsbh") String jsbh);
	
	 //大屏各监区数据
	@GetMapping("/jls_jbxx/jlsdpjqhdkp")
	public Map<String, Object> jlsdpjqhdkp(@RequestParam("jsbh") String jsbh);
	
	 //大屏各监区报病
	@GetMapping("/jls_swjy/jlsdpjqbb")
	public Map<String, Object> jlsdpjqbb(@RequestParam("jsbh") String jsbh);

	//看守所诉讼情况
	@GetMapping("/kss/hjbd/select_ssqk")
	Map<String, Object> select_ssqk(@RequestParam("rybh") String rybh);
	
	//拘留所值班领导
	@GetMapping("/jls_jbjl/jlsdpzbld")
	Map<String, Object> jlsdpzbld(@RequestParam("jsbh") String jsbh);

	//身体状况
	@GetMapping("/jls_jkqk/jkqkCount")
	Map<String,Object> jkqkCount(@RequestParam("jsbh") String jsbh,@RequestParam("rybh") String rybh);

    @GetMapping("/jls_zyry_rygx/selectRygx")
    Map<String,Object> findRygx(@RequestParam("jsbh") String jsbh,@RequestParam("rybh") String rybh,@RequestParam("jsh") String jsh);
    
    @GetMapping("/kss/jbxx/kssdpzyryFxys")
    Map<String,Object> kssdpzyryFxys(@RequestParam("jsbh") String jsbh,@RequestParam("rybh") String rybh);

	@GetMapping("/jls_jbxx/jlsdpzyryFxys")
	Map<String,Object> dpzyryFxys(@RequestParam("jsbh") String jsbh,@RequestParam("rybh") String rybh);

	//看守所诉讼情况
	@GetMapping("/ZYRY_ryqk/selectFxdj")
	Map<String, Object> selectFxdj(@RequestParam("rybh") String rybh);
//案件分析
	@GetMapping("/jls_jbxx/select_ajfx")
	Map<String,Object> select_ajfx(@RequestParam("jsbh") String jsbh,@RequestParam("rybh") String rybh);
//就医
	@GetMapping("/jls_jbxx/select_jy")
	Map<String,Object> select_jy(@RequestParam("jsbh") String jsbh,@RequestParam("rybh") String rybh);

	//在押人员关押量分析
	@GetMapping("/kss/zddpfx/zyrygyl")
	Map<String, Object> zyrygyl();

	//户籍地分析
	@GetMapping("/kss/zddpfx/hjdfx")
	Map<String, Object> hjdfx();

	//年龄分析
	@GetMapping("/kss/zddpfx/nlfx")
	Map<String, Object> nlfx();

	//文化程度分析
	@GetMapping("/kss/zddpfx/whcd")
	Map<String, Object> whcd();

	//身份分析
	@GetMapping("/kss/zddpfx/sffx")
	Map<String, Object> sffx();

	//健康情况分析
	@GetMapping("/kss/zddpfx/jkqkfx")
	Map<String, Object> jkqkfx();

	//拒收人员分析
	@GetMapping("/kss/zddpfx/jsryfx")
	Map<String, Object> jsryfx();

	//入所性质
	@GetMapping("/kss/zddpfx/rsxz")
	List rsxz(@RequestParam("jsbh") String jsbh);

	//案件类别分析
	@GetMapping("/kss/zddpfx/ajlb")
	List ajlb(@RequestParam("jsbh") String jsbh);

	//正常出所对比分析
	@GetMapping("/kss/zddpfx/zccs")
	Map<String, Object> zccs();

	//临时出所对比分析
	@GetMapping("/kss/zddpfx/lscsfx")
	Map<String, Object> lscsfx();

	//出所原因分析
	@GetMapping("/kss/zddpfx/csyyfx")
	List csyyfx(@RequestParam("jsbh") String jsbh);

	//严重暴力犯罪
	@GetMapping("/kss/zddpfx/yzblfz")
	Map<String, Object> yzblfz();

	//涉黑涉恶案件分析
	@GetMapping("/kss/zddpfx/shsefx")
	List shsefx(@RequestParam("jsbh") String jsbh);

    //民警数量
    @GetMapping("/kss/zddpfx/mjsl")
    Map<String, Object> mjsl();

	//严重疾病分析
	@GetMapping("/kss/zddpfx/yzjbfx")
	Map<String, Object> yzjbfx();
    //民警性别分析
    @GetMapping("/kss/zddpfx/mjxbfx")
    Map<String, Object> mjxbfx();
	//深挖犯罪
	@GetMapping("/kss/zddpfx/swfz")
	Map<String, Object> swfz();
	//民警警衔分析
	@GetMapping("/kss/zddpfx/mjjxfx")
	List mjjxfx(@RequestParam("jsbh") String jsbh);
	//民警警衔分析
	@GetMapping("/kss/zddpfx/mjwhcd")
	Map<String, Object> mjwhcd();
	//民警在职时长分析
	@GetMapping("/kss/zddpfx/zzscfx")
	Map<String, Object> zzscfx();

	//kss会见人数分析 : 家属会见、律师会见、提讯、总数
	@GetMapping("/kss/zddpfx/select_kss_hjrs")
	public Map<String, Object> select_kss_hjrs();

	//kss在所状态 :  已决、未决
	@GetMapping("/kss/zddpfx/select_kss_zszt")
	public Map<String, Object> select_kss_zszt();

//kss人员管理情况分析 ：械具、禁闭、单独关押、严管人员、耳目
	@GetMapping("/kss/zddpfx/select_kss_rygl")
	public Map<String, Object> select_kss_rygl();

	//kss全省办案阶段分析
	@GetMapping("/kss/zddpfx/select_kss_qsbajd")
	public List<Object> select_kss_qsbajd(@RequestParam(value = "jsbh") String jsbh);

    //kss年度入所量比对
	@GetMapping("/kss/zddpfx/select_kss_ndrs")
	public Map<String, Object> select_kss_ndrs();

    //kss风险情况分析=人员风险等级分析
	@GetMapping("/kss/zddpfx/select_kss_fxdj")
	public Map<String, Object> select_kss_fxdj();

	//kss大屏吸毒人员分析 吸毒人数、百分比
	@GetMapping("/kss/zddpfx/select_kss_xdry")
	public Map<String, Object> select_kss_xdry();

	//大屏jls送监未收人员
	@GetMapping("/jls_dpfx/select_jls_sjwsry")
	public ResponseMessage<Map<String, Object>> select_jls_sjwsry();

	//大屏jls年度入所量比对
	@GetMapping("/jls_dpfx/select_jls_ndrs")
	public ResponseMessage<Map<String, Object>> select_jls_ndrs();

	//大屏jls超期羁押分析
	@GetMapping("/jls_dpfx/select_jls_cqjy")
	public ResponseMessage<Map<String, Object>> select_jls_cqjy();

	//大屏jls重点人员分析
	@GetMapping("/jls_dpfx/select_jls_zdry")
	public ResponseMessage<Map<String, Object>> select_jls_zdry();

	//大屏jls所内、所外就医
	@GetMapping("/jls_dpfx/select_jls_jy")
	public ResponseMessage<Map<String, Object>> select_jls_jy();

	//大屏jls人员管理情况分析
	@GetMapping("/jls_dpfx/select_jls_rygl")
	public ResponseMessage<Map<String, Object>> select_jls_rygl();

	//大屏jls会见人数分析
	@GetMapping("/jls_dpfx/select_jls_hjrs")
	public ResponseMessage<Map<String, Object>> select_jls_hjrs();

	//大屏jls吸毒人员分析
	@GetMapping("/jls_dpfx/select_jls_xdry")
	public ResponseMessage<Map<String, Object>> select_jls_xdry();

	//大屏jls风险情况分析
	@GetMapping("/jls_dpfx/select_jls_fxdj")
	public ResponseMessage<Map<String, Object>> select_jls_fxdj();

	//大屏jls关押期限分析(3月以上   3-6   6-1  1-3  3)
	@GetMapping("/jls_dpfx/select_jls_gyqx")
	public ResponseMessage<Map<String, Object>> select_jls_gyqx();

}



