package awd.cloud.platform.webs.charts.controller;

import awd.bj.kss.model.JyModel;
import awd.cloud.platform.webs.charts.api.AnalyseApis;
import awd.cloud.platform.webs.charts.api.JlsServersApi;
import awd.cloud.platform.webs.charts.api.KssServerApis;
import awd.cloud.platform.webs.charts.modal.User;
import awd.cloud.platform.webs.charts.modal.jls.JbxxModel;
import awd.cloud.platform.webs.charts.modal.jls.SwjyModel;
import awd.cloud.platform.webs.charts.utils.*;
import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RefreshScope
@RequestMapping("/zddp")
public class ZddpController {
	@Autowired
	private AnalyseApis analyseApis;
	@Autowired
	private JlsServersApi jlsServersApi;
	@Autowired
	private KssServerApis kssServerApis;
	@RequestMapping("/ryfx.html")
    public String clcsModel(org.springframework.ui.Model model) {
        User users = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", JSONUtil.toJson(users));
        return "ryfx/ryjcxx";
    }
	
	
	
	@GetMapping("/gylQuery")
	@ResponseBody
	@OpenAPI
	@ApiOperation("关押量")
	public Map<String, Object> gylQuery(){
		Map<String, Object> map = analyseApis.gylQuery();
		System.err.println("map111--"+JSON.toJSONString(map));
		return map;
	}
	
	@GetMapping("/swjyByjbxx")
	@ResponseBody
	@OpenAPI
	@ApiOperation("所外就医")
	public Map<String, Object> swjyByjbxx(HttpServletRequest request){
		Map<String, Object> msp = new LinkedHashMap<String, Object>();
		Map<String, Object> allmap = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> mapname = new HashMap<String, Object>();
		mapname.put("name", "监所");
		list.add(mapname);
		Map<String, Object> mapname1 = new HashMap<String, Object>();
		mapname1.put("name", "姓名");
		list.add(mapname1);
		Map<String, Object> mapname2 = new HashMap<String, Object>();
		mapname2.put("name", "症状");
		list.add(mapname2);
		Map<String, Object> mapname3 = new HashMap<String, Object>();
		mapname3.put("name", "就医医院");
		list.add(mapname3);
		Map<String, Object> mapname4 = new HashMap<String, Object>();
		mapname4.put("name", "出所时间");
		list.add(mapname4);
		Map<String, Object> mapname5 = new HashMap<String, Object>();
		mapname5.put("name", "回所时间");
		list.add(mapname5);
		Map<String, Object> mapname6 = new HashMap<String, Object>();
		mapname6.put("name", "押解民警");
		list.add(mapname6);
		Map<String, Object> mapname7 = new HashMap<String, Object>();
		mapname7.put("name", "是否住院");
		list.add(mapname7);
		msp.put("head", list);
		allmap.put("msg", "查询成功");
		allmap.put("result", true);
		try {
		QueryParam param = new QueryParam();
		String jsbh = request.getParameter("jsbh");
		String type = request.getParameter("type");
		if(!StringUtils.isNullOrEmpty(jsbh)) {
			param.and("jsbh", jsbh);
		}
		param.and("state", "R8");
		param.and("crjbj", "09");
		Map<String, Object> jlsmap = new LinkedHashMap<String, Object>();
		ResponseMessage<PagerResult<JbxxModel>> jlslist = jlsServersApi.jbxxQueryForPage(param);
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		List<Map<String, Object>> jylist = new ArrayList<Map<String,Object>>();
		if(jlslist.getResult().getTotal()>0) {
			for (JbxxModel jbxxModel : jlslist.getResult().getData()) {
				QueryParam params = new QueryParam();
				params.and("blrq", TermType.eq,sd.format(new Date()));
				params.and("rybh", jbxxModel.getRybh());
				params.and("zdzyj", "1");
				params.and("szyj", "1");
				if(!StringUtils.isNullOrEmpty(type)) {
					if("one".equals(type)) {
						
					}else if("two".equals(type)) {
						//params.and("hssj", TermType.isnull,"1");
					}else if("three".equals(type)) {
						//params.and("jzyy", TermType.like, "%公安%");
					}
				}
				List<Sort> sorts = new ArrayList<>();
				Sort sort = new Sort();
				sort.setName("createtime");
				sort.setOrder("desc");
				sorts.add(sort);
				params.setSorts(sorts);
				ResponseMessage<PagerResult<SwjyModel>> swjylist = jlsServersApi.swjyQueryForPage(params);
				if(swjylist.getResult().getTotal()>0) {
					Map<String, Object> maps1 = new LinkedHashMap();
					maps1.put("name", jbxxModel.getJsbhString());
					maps1.put("msg", jbxxModel.getXm());
					maps1.put("type", swjylist.getResult().getData().get(0).getJbmc());
					maps1.put("location", swjylist.getResult().getData().get(0).getJzyy());
					if(!StringUtils.isNullOrEmpty(swjylist.getResult().getData().get(0).getCssj())) {
						maps1.put("timeIn", sd.format(swjylist.getResult().getData().get(0).getCssj()));
					}else {
						maps1.put("timeIn","");
					}
					if(!StringUtils.isNullOrEmpty(swjylist.getResult().getData().get(0).getHssj())) {
						maps1.put("timeOut", sd.format(swjylist.getResult().getData().get(0).getHssj()));
					}else {
						maps1.put("timeOut", "");
					}
					maps1.put("police", "");
					maps1.put("judge", "是");
					maps1.put("judgeType", 0);
					jylist.add(maps1);
				}
			}
		}
		ResponseMessage<PagerResult<awd.bj.kss.model.JbxxModel>> kssjbxx = kssServerApis.queryForPage(param);
		if(kssjbxx.getResult().getTotal()>0) {
			for (awd.bj.kss.model.JbxxModel jbxxModel : kssjbxx.getResult().getData()) {
				QueryParam params1 = new QueryParam();
				params1.and("zdrq", TermType.gte,sd.format(new Date())+" 00:00:00");
				params1.and("zdrq", TermType.lte,sd.format(new Date())+" 23:59:59");
				params1.and("rybh", jbxxModel.getRybh());
				params1.and("psbz", "1");
				params1.and("swjy", "1");
				if("one".equals(type)) {
					
				}else if("two".equals(type)) {
					//params1.and("hssj", TermType.isnull,"1");
				}else if("three".equals(type)) {
					//params1.and("jzyy", TermType.like, "%公安%");
				}
				List<Sort> sorts = new ArrayList<>();
				Sort sort = new Sort();
				sort.setName("createtime");
				sort.setOrder("desc");
				sorts.add(sort);
				params1.setSorts(sorts);
				ResponseMessage<PagerResult<JyModel>> swjylist = kssServerApis.jyQueryForPage(params1);
				if(swjylist.getResult().getTotal()>0) {
					Map<String, Object> maps1 = new LinkedHashMap();
					maps1.put("name", jbxxModel.getJsbhString());
					maps1.put("msg", jbxxModel.getXm());
					maps1.put("type", swjylist.getResult().getData().get(0).getShjb());
					maps1.put("location", swjylist.getResult().getData().get(0).getJzyy());
					if(!StringUtils.isNullOrEmpty(swjylist.getResult().getData().get(0).getCssj())) {
						maps1.put("timeIn", sd.format(swjylist.getResult().getData().get(0).getCssj()));
					}else {
						maps1.put("timeIn","");
					}
					if(!StringUtils.isNullOrEmpty(swjylist.getResult().getData().get(0).getHssj())) {
						maps1.put("timeOut", sd.format(swjylist.getResult().getData().get(0).getHssj()));
					}else {
						maps1.put("timeOut", "");
					}
					maps1.put("police", swjylist.getResult().getData().get(0).getPtmj());
					maps1.put("judge", "是");
					maps1.put("judgeType", 0);
					jylist.add(maps1);
				}
			}
		}
		msp.put("data", jylist);
		allmap.put("msg", msp);
			return allmap;
		} catch (Exception e) {
			return allmap;
		}
	}
	

	@GetMapping("/findKss_dsjPT")
	@ResponseBody
	@OpenAPI
	@ApiOperation("大屏看守所在押人数")
	public Map<String, Object> findKss_dsjPT(){
		Map<String, Object> map = analyseApis.findKss_dsjPT();
		System.err.println("map--"+JSON.toJSONString(map));
		return map;
	}

	@GetMapping("/select_Jsjy")
	@ResponseBody
	@OpenAPI
	@ApiOperation("监所羁押")
	public Map<String, Object> select_Jsjy(HttpServletRequest request){

		String strify = request.getParameter("strify");


		Map<String, Object> map = analyseApis.select_Jsjy(strify);
		System.err.println("mapdf--"+JSON.toJSONString(map));
		return map;
	}




	@GetMapping("/select_GQSXX")
    @ResponseBody
    @OpenAPI
    @ApiOperation("地图红点")
    public Map<String, Object> select_GQSXX(HttpServletRequest request){
		String strify = request.getParameter("strify");
		//String strify = "110000114";

        Map<String, Object> map = analyseApis.select_GQSXX(strify);
        System.err.println("map--"+JSON.toJSONString(map));
        return map;
    }

	@GetMapping("/select_JSJL")
    @ResponseBody
    @OpenAPI
    @ApiOperation("今日监所警力部署")
    public Map<String, Object> select_JSJL(HttpServletRequest request){
		String strify = request.getParameter("strify");
		/*String strify = "110000114";*/

        Map<String, Object> map = analyseApis.select_JSJL(strify);
        System.err.println("map--"+JSON.toJSONString(map));
        return map;
    }




	@GetMapping("/select_Dagy")
	@ResponseBody
	@OpenAPI
	@ApiOperation("大屏单独关押 械具使用情况")
	public Map<String, Object> select_Dagy(HttpServletRequest request){

		String strify = request.getParameter("strify");

		Map<String, Object> map = analyseApis.select_Dagy(strify);

		System.err.println("map--"+JSON.toJSONString(map));
		return map;
	}
	@GetMapping("/select_AYFX")
	@ResponseBody
	@OpenAPI
	@ApiOperation("全市被监管人员收押情况查询")
	public Map<String, Object> select_AYFX(){
		Map<String, Object> map = analyseApis.select_AYFX();
		System.err.println("map--"+JSON.toJSONString(map));
		return map;
	}


	@GetMapping("/select_JGRYSY")
	@ResponseBody
	@OpenAPI
	@ApiOperation("全市被监管人员收押情况查询")
	public Map<String, Object> select_JGRYSY(){
		Map<String, Object> map = analyseApis.select_JGRYSY();
		System.err.println("map--"+JSON.toJSONString(map));
		return map;
	}

	@GetMapping("/findZfzlk")
	@ResponseBody
	@OpenAPI
	@ApiOperation("执法质量靠评查询")
	public Map<String, Object> findZfzlk(){
		Map<String, Object> map = analyseApis.findZfzlk();
		System.err.println("map--"+JSON.toJSONString(map));
		return map;
	}

	@GetMapping("/select_QSCSJY")
	@ResponseBody
	@OpenAPI
	@ApiOperation("全市出所就医 swjy 所外就医  1是  0否,csjylx出所就医类型 1门诊  2住院,  jzyy就诊医院")
	public Map<String, Object> select_QSCSJY(HttpServletRequest request){
		String strify = request.getParameter("strify");
		/*String jsbh ="110000114";
		System.out.println(jsbh);*/
		Map<String, Object> map = analyseApis.select_QSCSJY(strify);
		System.err.println("map--"+JSON.toJSONString(map));
		return map;
	}
}
