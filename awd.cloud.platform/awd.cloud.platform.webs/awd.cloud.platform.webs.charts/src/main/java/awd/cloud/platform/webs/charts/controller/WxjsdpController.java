package awd.cloud.platform.webs.charts.controller;

import awd.bj.kss.model.JyModel;
import awd.bj.kss.model.WgsjclModel;
import awd.cloud.platform.webs.charts.api.AnalyseApis;
import awd.cloud.platform.webs.charts.api.KssServerApis;
import awd.cloud.platform.webs.charts.utils.*;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Author：YaoBen
 * Date：2019-12-25 13:58
 * Description：<描述>
 */
@RestController
@RefreshScope
@RequestMapping("/Wxjsdp")
public class WxjsdpController {
    @Autowired
    private AnalyseApis analyseApis;


    @Autowired
    private KssServerApis kssServerApis;

    @GetMapping("/jbxxlist")
    @ResponseBody
    @OpenAPI
    @ApiOperation("看守所大屏人员信息查询")
    public Map<String, Object> jbxxlist(HttpServletRequest request) {
        String bahj = request.getParameter("bahj");//人员编号
        String rybh = request.getParameter("rybh");//人员编号
        String jsbh = request.getParameter("jsbh");//jsbh
        String rsrq = request.getParameter("rsrq");//入所日期
        String crjbj = request.getParameter("crjbj");//出入监标记
        String jsh = request.getParameter("jsh");//4位的监室号 后台截



        try {
            Map<String, Object> map = analyseApis.select_zsryxx(bahj,rybh,jsbh,rsrq,crjbj,jsh);
            System.err.println("map--"+JSON.toJSONString(map));
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    @GetMapping("/select_ylgl")
    @ResponseBody
    @OpenAPI
    @ApiOperation("分所大屏医疗管理")
    public Map<String, Object> select_ylgl(HttpServletRequest request){
         String strify = request.getParameter("strify");
         //String strify = "110000114";
        Map<String, Object> map = analyseApis.select_ylgl(strify);
        System.err.println("map--"+JSON.toJSONString(map));
        return map;
    }

    @GetMapping("/select_zsry")
    @ResponseBody
    @OpenAPI
    @ApiOperation("看守所在所人员")
    public Map<String, Object> select_zsry(HttpServletRequest request){
        String strify = request.getParameter("strify");

        Map<String, Object> map = analyseApis.select_zsry(strify);
        System.err.println("map--"+JSON.toJSONString(map));
        return map;
    }


    @GetMapping("/select_hdsj")
    @ResponseBody
    @OpenAPI
    @ApiOperation("分所大屏各监区滑动卡牌")
    public Map<String, Object> select_hdsj(HttpServletRequest request){
    String strify = request.getParameter("jsbh");



        Map<String, Object> map = analyseApis.select_hdsj(strify);
        System.err.println("map--"+JSON.toJSONString(map));
        return map;
    }



    @GetMapping("/find_wgcd")
    @ResponseBody
    @OpenAPI
    @ApiOperation("分所大屏各监区违规程度")
    public Map<String, Object> find_wgcd(HttpServletRequest request){
        String strify = request.getParameter("jsbh");

        /*String strify ="110000114";
                String jsh = "0104";
                String jqh = "01%";*/
        Map<String, Object> map = analyseApis.find_wgcd(strify);
        System.err.println("map--"+JSON.toJSONString(map));
       return map;
    }

    @GetMapping("/select_wgcd_rybh")
    @ResponseBody
    @OpenAPI
    @ApiOperation("大屏监区违规程度点击==根据违规类型=一般违规")
    public LinkedHashMap<String, Object> select_wgcd_rybh(HttpServletRequest request){
        String strify = request.getParameter("jsbh");
        String wgqk = request.getParameter("wgqk");

        LinkedHashMap<String, Object> map = analyseApis.select_wgcd_rybh(strify,wgqk);

        System.err.println("map--"+JSON.toJSONString(map));
        return map;
    }



    @GetMapping("/select_zdgz")
    @ResponseBody
    @OpenAPI
    @ApiOperation("分所大屏各监区重点人员")
    public Map<String, Object> select_zdgz(HttpServletRequest request){
        String strify = request.getParameter("jsbh");


        Map<String, Object> map = analyseApis.select_zdgz(strify);
        System.err.println("map--"+JSON.toJSONString(map));
        return map;
    }


    @GetMapping("/select_zdgzry")
    @ResponseBody
    @OpenAPI
    @ApiOperation("分所大屏各监区重点人员信息")
    public Map<String, Object> select_zdgzry(HttpServletRequest request){

        String strify = request.getParameter("jsbh");
        String rybh = request.getParameter("rybh");

        Map<String, Object> map = analyseApis.select_zdgzry(strify,rybh);
        System.err.println("map--"+JSON.toJSONString(map));
        return map;
    }


     @GetMapping("/select_ssqq")
    @ResponseBody
    @OpenAPI
    @ApiOperation("诉讼情况分类与犯罪类型分析")
    public Map<String, Object> select_ssqq(HttpServletRequest request){
         String strify = request.getParameter("jsbh");

        /* String strify="110000114";*/
        Map<String, Object> map = analyseApis.select_ssqq(strify);
        System.err.println("map--"+JSON.toJSONString(map));
        return map;
    }


     @GetMapping("/selectJbxx")
    @ResponseBody
    @OpenAPI
    @ApiOperation("在押人员基本信息")
    public Map<String, Object> selectJbxx(HttpServletRequest request){

         String rybh = request.getParameter("rybh");
         System.out.println("在押人员基本信息="+rybh);


        Map<String, Object> map = analyseApis.selectJbxx(rybh);
        System.err.println("map--"+JSON.toJSONString(map));
        return map;
    }

    @GetMapping("/selectRyqk")
    @ResponseBody
    @OpenAPI
    @ApiOperation("在押人员人员情况")
    public Map<String, Object> selectRyqk(HttpServletRequest request){
        String rybh = request.getParameter("rybh");


       /* String strify="110000114";
        String xm="李娜";
        String jsh = "0101";*/

        Map<String, Object> map = analyseApis.selectRyqk(rybh);
        System.err.println("map--"+JSON.toJSONString(map));
        return map;
    }




    @GetMapping("/selectRygx")
    @ResponseBody
    @OpenAPI
    @ApiOperation("在押人员人员关系")
    public Map<String, Object> selectRygx(HttpServletRequest request){

        String strify = request.getParameter("strify");

        String rybh = request.getParameter("rybh");
        String jsh = request.getParameter("jsh");

        Map<String, Object> map = analyseApis.selectRygx(strify,rybh,jsh);
        System.err.println("map88--"+JSON.toJSONString(map));
        return map;
    }


    @GetMapping("/selectFxys")
    @ResponseBody
    @OpenAPI
    @ApiOperation("在押人员风险因素查询")
    public Map<String, Object> selectFxys(HttpServletRequest request){
        String jsbh = request.getParameter("jsbh");

        String rybh = request.getParameter("rybh");
        Map<String, Object> map = analyseApis.kssdpzyryFxys(jsbh,rybh);
        System.err.println("map--"+JSON.toJSONString(map));
        return map;
    }


    @GetMapping("/select_Jqwg")
    @ResponseBody
    @OpenAPI
    @ApiOperation("监区违规情况趋势图")
    public Map<String, Object> select_Jqwg(HttpServletRequest request){
       String jsbh = request.getParameter("jsbh");


       /* String jsbh="110000114";
        String jsh = "01";*/
        Map<String, Object> map = analyseApis.select_Jqwg(jsbh);
        System.err.println("map--"+JSON.toJSONString(map));
        return map;
    }
    @GetMapping("/select_jqwgryxx")
    @ResponseBody
    @OpenAPI
    @ApiOperation("监区违规情况趋势图人员信息")
    public Map<String, Object> select_jqwgryxx(HttpServletRequest request){
        String jsbh = request.getParameter("jsbh");
        String jqh = request.getParameter("jqh");


       /* String jsbh="110000114";
        String jsh = "01";*/
        Map<String, Object> map = analyseApis.select_jqwgryxx(jsbh,jqh);
        System.err.println("map--"+JSON.toJSONString(map));
        return map;
    }



    @GetMapping("/aqglQuery")
    @ResponseBody
    @OpenAPI
    @ApiOperation("安全管理查询")
    public Map<String, Object> aqglQuery(){

        Map<String, Object> map = analyseApis.aqglQuery();
        System.err.println("map--"+JSON.toJSONString(map));
        return map;
    }


    @GetMapping("/select_txhj")
    @ResponseBody
    @OpenAPI
    @ApiOperation("分所大屏提讯会见")
    public Map<String, Object> select_txhj(HttpServletRequest request){
        String strify = request.getParameter("strify");

        Map<String, Object> map = analyseApis.select_txhj(strify);
        System.err.println("map--"+JSON.toJSONString(map));
        return map;
    }

    @GetMapping("/select_gyqx")
    @ResponseBody
    @OpenAPI
    @ApiOperation("分所大屏关押期限")
    public Map<String, Object> select_gyqx(HttpServletRequest request){
        String strify = request.getParameter("strify");

        Map<String, Object> map = analyseApis.select_gyqx(strify);
        System.err.println("map--"+JSON.toJSONString(map));
        return map;
    }

    @GetMapping("/select_jqbb")
    @ResponseBody
    @OpenAPI
    @ApiOperation("分所大屏各监区抱病情况趋势图")
    public Map<String, Object> select_jqbb(HttpServletRequest request){
        String strify = request.getParameter("strify");


        Map<String, Object> map = analyseApis.select_jqbb(strify);
        System.err.println("map44--"+JSON.toJSONString(map));
        return map;
    }


    @GetMapping("/select_zbld")
    @ResponseBody
    @OpenAPI
    @ApiOperation("分所大屏值班领导 在岗民警 协警")
    public Map<String, Object> select_zbld(HttpServletRequest request){

        String strify = request.getParameter("strify");


        Map<String, Object> map = analyseApis.select_zbld(strify);
        System.err.println("map55--"+JSON.toJSONString(map));
        return map;
    }
    
    @GetMapping("/kssdpaqgl")
    @ResponseBody
    @OpenAPI
    @ApiOperation("安全管理")
    public Map<String, Object> kssdpaqgl(HttpServletRequest request){
        String strify = request.getParameter("strify");

        Map<String, Object> map = analyseApis.kssdpaqgl(strify);
        System.err.println("map--"+JSON.toJSONString(map));
        return map;
    }

    //在押人员=风险等级
    @GetMapping("/selectFxdj")
    @ResponseBody
    @OpenAPI
    @ApiOperation("在押人员=风险等级")
    public Map<String, Object> selectFxdj(HttpServletRequest request){
        System.out.println("fgjg==+++");
        String rybh = request.getParameter("rybh");
        System.out.println("dhfvhgjgdgh="+rybh);

        Map<String, Object> map = analyseApis.selectFxdj(rybh);
        System.out.println("map都发给fg=="+map.toString());
        System.err.println("map--"+JSON.toJSONString(map));
        return map;
    }

    //在押人员页面健康情况点击事件
    @GetMapping("/dpByjkqk")
    @ResponseBody
    @OpenAPI
    @ApiOperation("在押人员=健康情况")
    public Map<String, Object> dpByjkqk(HttpServletRequest request){
    	Map<String, Object> allmap = new HashMap<String, Object>();
    	allmap.put("code", 200);
    	allmap.put("msg", "查询成功");
    	String jsbh = request.getParameter("jsbh");
    	String rybh = request.getParameter("rybh");
    	List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		Map<String, Object> msp = new HashMap<String, Object>();
		msp.put("series", "xl");
		msp.put("name", "序列");
		Map<String, Object> msp1 = new HashMap<String, Object>();
		msp1.put("series", "item1");
		msp1.put("name", "病情");
		Map<String, Object> msp2 = new HashMap<String, Object>();
		msp2.put("series", "item2");
		msp2.put("name", "诊断情况");
		Map<String, Object> msp3 = new HashMap<String, Object>();
		msp3.put("series", "item3");
		msp3.put("name", "处理结果");
		Map<String, Object> msp4 = new HashMap<String, Object>();
		msp4.put("series", "item4");
		msp4.put("name", "陪同民警");
		maps.add(msp);
		maps.add(msp1);
		maps.add(msp2);
		maps.add(msp3);
		maps.add(msp4);
		allmap.put("title", maps);
    	QueryParam param = new QueryParam();
    	param.and("jsbh", jsbh);
    	param.and("rybh", rybh);
    	List<Sort> sorts = new ArrayList<>();
		Sort sort = new Sort();
		sort.setName("createtime");
		sort.setOrder("desc");
		sorts.add(sort);
		param.setSorts(sorts);
    	ResponseMessage<PagerResult<JyModel>> jylist = kssServerApis.jyQueryForPage(param);
    	int i = 0;
    	List<Map<String, Object>> jylists = new ArrayList<Map<String,Object>>();
    	if(jylist.getResult().getTotal()>0) {
    		for (JyModel jyModel : jylist.getResult().getData()) {
    			i++;
				Map<String, Object> jymap = new HashMap<String, Object>();
				jymap.put("xl", i);
				jymap.put("item1", jyModel.getBrbq());
				jymap.put("item2", jyModel.getZdqk());
				jymap.put("item3", jyModel.getCljg());
				jymap.put("item4", jyModel.getPtmj());
				jylists.add(jymap);
			}
    	}
    	allmap.put("data", jylists);
		return allmap;
    }
    
  //在押人员页面违规点击事件
    @GetMapping("/dpBywgsjcl")
    @ResponseBody
    @OpenAPI
    @ApiOperation("在押人员=违规")
    public Map<String, Object> dpBywgsjcl(HttpServletRequest request){
    	Map<String, Object> allmap = new HashMap<String, Object>();
    	allmap.put("code", 200);
    	allmap.put("msg", "查询成功");
    	String jsbh = request.getParameter("jsbh");
    	String rybh = request.getParameter("rybh");
    	List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		Map<String, Object> msp = new HashMap<String, Object>();
		msp.put("series", "xl");
		msp.put("name", "序列");
		Map<String, Object> msp1 = new HashMap<String, Object>();
		msp1.put("series", "item1");
		msp1.put("name", "违规事件");
		Map<String, Object> msp2 = new HashMap<String, Object>();
		msp2.put("series", "item2");
		msp2.put("name", "违规类型");
		Map<String, Object> msp3 = new HashMap<String, Object>();
		msp3.put("series", "item3");
		msp3.put("name", "处理结果");
		Map<String, Object> msp4 = new HashMap<String, Object>();
		msp4.put("series", "item4");
		msp4.put("name", "处理人");
		maps.add(msp);
		maps.add(msp1);
		maps.add(msp2);
		maps.add(msp3);
		maps.add(msp4);
		allmap.put("title", maps);
    	QueryParam param = new QueryParam();
    	param.and("jsbh", jsbh);
    	param.and("dxbh", rybh);
    	List<Sort> sorts = new ArrayList<>();
		Sort sort = new Sort();
		sort.setName("createtime");
		sort.setOrder("desc");
		sorts.add(sort);
		param.setSorts(sorts);
    	ResponseMessage<PagerResult<WgsjclModel>> jylist = kssServerApis.wgsjclQueryForPage(param);
    	int i = 0;
    	List<Map<String, Object>> jylists = new ArrayList<Map<String,Object>>();
    	if(jylist.getResult().getTotal()>0) {
    		for (WgsjclModel wgsjclModel : jylist.getResult().getData()) {
    			i++;
				Map<String, Object> jymap = new LinkedHashMap<String, Object>();
				jymap.put("xl", i);
				jymap.put("item1", wgsjclModel.getWgqk());
				jymap.put("item2", wgsjclModel.getWglxString());
				if(wgsjclModel.getClqk()!=null) {
					if(!StringUtils.isNullOrEmpty(CacheUtils.get().findDictionarys("WGCLQK", wgsjclModel.getClqk()))) {
						jymap.put("item3", CacheUtils.get().findDictionarys("WGCLQK", wgsjclModel.getClqk()));
					}else {
						jymap.put("item3", wgsjclModel.getClqk());
					}
				}else {
					jymap.put("item3", "");
				}
				jymap.put("item4", wgsjclModel.getClr());
				jylists.add(jymap);
			}
    	}
    	allmap.put("data", jylists);
		return allmap;
    }
    
}
