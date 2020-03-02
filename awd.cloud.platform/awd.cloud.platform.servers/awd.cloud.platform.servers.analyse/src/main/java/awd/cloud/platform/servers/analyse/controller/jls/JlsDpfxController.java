package awd.cloud.platform.servers.analyse.controller.jls;


import awd.cloud.platform.servers.analyse.service.jls.JlsDpFxService;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jls_dpfx")
@Api(tags ="jls_dpfx",description = "拘留所大屏分析")
public class JlsDpfxController {
    @Autowired
    private JlsDpFxService jlsDpFxService;



    @GetMapping("/select_jls_sjwsry")
	@ApiOperation("大屏jls送监未收人员")
	@OpenAPI
	public ResponseMessage<Map<String, Object>> select_jls_sjwsry(){
		int[] jsbh = new int[]{110000121, 110101121, 110105121, 110108121, 110111121};
		Map<String, Object> map = new HashMap<String, Object>();
		int[] ldjys = new int[]{0, 0, 0, 0, 0};//投送劳动教养所未收
		int[] srjys = new int[]{0, 0, 0, 0, 0};//投送收容教育所未收
		int[] sngjs = new int[]{0, 0, 0, 0, 0};//投送少年管教所未收
		int[] qtts = new int[]{0, 0, 0, 0, 0};//其他投送未收


		try {
			List<Map<String, Object>> jls_sjwsry = jlsDpFxService.find_jls_sjwsry();
			if(!StringUtils.isNullOrEmpty(jls_sjwsry)){
				for(Map<String, Object> key:jls_sjwsry ){
					if(!StringUtils.isNullOrEmpty(key)){
						for(int i =0;i<jsbh.length;i++){
						  if(key.get("jsbh").toString().contains(String.valueOf(jsbh[i]))){
							  ldjys[i]= ldjys[i]+Integer.parseInt(key.get("ldjys").toString());
							  srjys[i]=srjys[i]+Integer.parseInt(key.get("srjys").toString());
							  sngjs[i]=sngjs[i]+Integer.parseInt(key.get("sngjs").toString());
							  qtts[i]=qtts[i]+Integer.parseInt(key.get("qttsws").toString());
						  }
						}
					}
				}
				map.put("ldjys",ldjys);
				map.put("srjys",srjys);
				map.put("sngjs",sngjs);
				map.put("qtts",qtts);
			}else{

			map.put("ldjys",ldjys);
			map.put("srjys",srjys);
			map.put("sngjs",sngjs);
			map.put("qtts",qtts);
			}
			return  ResponseMessage.ok(map);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return  ResponseMessage.error("服务器繁，请重新刷新页面");
		}
	}


	@GetMapping("/select_jls_ndrs")
	@ApiOperation("大屏jls年度入所量比对")
	@OpenAPI
	public ResponseMessage<Map<String, Object>> select_jls_ndrs() {
		int[] jsbh = new int[]{110000121, 110101121, 110105121, 110108121, 110111121};
		Map<String, Object> map = new HashMap<String, Object>();
		int[] jnArray = new int[]{0, 0, 0, 0, 0};//今年
		int[] qnArray = new int[]{0, 0, 0, 0, 0};//去年

		try {
			List<Map<String, Object>> jls_ndrs = jlsDpFxService.find_jls_ndrs();
			if(!StringUtils.isNullOrEmpty(jls_ndrs)){
				for(Map<String, Object> key :jls_ndrs){
					for(int i =0;i<jsbh.length;i++){
						if(key.get("jsbh").toString().contains(String.valueOf(jsbh[i]))){
							jnArray[i]=Integer.parseInt(key.get("jn").toString());
							 qnArray[i]=Integer.parseInt(key.get("qn").toString());
						}
					}
				}
				map.put("jnArray",jnArray);
				map.put("qnArray",qnArray);
			}else{
			map.put("jnArray",jnArray);
			map.put("qnArray",qnArray);
			}
			return ResponseMessage.ok(map);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return  ResponseMessage.error("服务器繁，请重新刷新页面");
		}
	}





	@GetMapping("/select_jls_cqjy")
	@ApiOperation("大屏jls超期羁押分析")
	@OpenAPI
	public ResponseMessage<Map<String, Object>> select_jls_cqjy() {
		int[] jsbh = new int[]{110000121, 110101121, 110105121, 110108121, 110111121};
		Map<String, Object> map = new HashMap<String, Object>();
		int[] cqrs = new int[]{0, 0, 0, 0, 0};//超期人数
		String[] bfb = new String[]{"0", "0", "0", "0", "0"};//百分比

		try {
			List<Map<String, Object>> jls_cqjy = jlsDpFxService.find_jls_cqjy();
			if(!StringUtils.isNullOrEmpty(jls_cqjy)){
				for(Map<String, Object> key:jls_cqjy){
					for(int i =0;i<jsbh.length;i++){
						if(key.get("jsbh").toString().contains(String.valueOf(jsbh[i]))){
							if(!"0".equals(key.get("all"))) {
								String all = key.get("all").toString();
								cqrs[i] = Integer.parseInt(key.get("cq").toString());

								String cq_bfb = String.format("%.2f", (float) Integer.parseInt(cqrs[i]+"") / (float) Integer.parseInt(all) * 100);

								bfb[i]=cq_bfb;

							}
						}
					}
				}
				map.put("cqrs",cqrs);map.put("bfb",bfb);
			}else{
				map.put("cqrs",cqrs);map.put("bfb",bfb);
			}
			return  ResponseMessage.ok(map);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return  ResponseMessage.error("服务器繁，请重新刷新页面");
		}
	}


	@GetMapping("/select_jls_zdry")
	@ApiOperation("大屏jls重点人员分析")
	@OpenAPI
	public ResponseMessage<Map<String, Object>> select_jls_zdry() {
		int[] jsbh = new int[]{110000121, 110101121, 110105121, 110108121, 110111121};
		Map<String, Object> map = new HashMap<String, Object>();
		int[] man = new int[]{0, 0, 0, 0, 0};//男
		int[] woman = new int[]{0, 0, 0, 0, 0};//女
		int[] unknow = new int[]{0, 0, 0, 0, 0};//未知性别

		try {
			List<Map<String, Object>> jls_zdry = jlsDpFxService.find_jls_zdry();
			if(!StringUtils.isNullOrEmpty(jls_zdry)){
				for(Map<String, Object> key:jls_zdry){
					for(int i =0;i<jsbh.length;i++){
						if(key.get("jsbh").toString().contains(String.valueOf(jsbh[i]))){
							man[i]=Integer.parseInt(key.get("man").toString());
							woman[i]=Integer.parseInt(key.get("woman").toString());
							unknow[i]=Integer.parseInt(key.get("unknow").toString());
						}
					}
				}
			}
			map.put("man",man);
			map.put("woman",woman);
			map.put("unknow",unknow);
			return  ResponseMessage.ok(map);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return  ResponseMessage.error("服务器繁，请重新刷新页面");
		}
	}


	@GetMapping("/select_jls_jy")
	@ApiOperation("大屏jls所内、所外就医")
	@OpenAPI
	public ResponseMessage<Map<String, Object>> select_jls_jy() {
		int[] jsbh = new int[]{110000121, 110101121, 110105121, 110108121, 110111121};
		Map<String, Object> map = new HashMap<String, Object>();
		int[] swjy = new int[]{0, 0, 0, 0, 0};//所外就医
		int[] snjy = new int[]{0, 0, 0, 0, 0};//所内就医
		int[] all = new int[]{0, 0, 0, 0, 0};//就医总数

		try {
			List<Map<String, Object>> jls_swjy = jlsDpFxService.find_jls_swjy();//所外就医
			if(!StringUtils.isNullOrEmpty(jls_swjy)){
				for(Map<String, Object> key:jls_swjy){
					for(int i =0;i<jsbh.length;i++) {
						if (key.get("jsbh").toString().contains(String.valueOf(jsbh[i]))){
							swjy[i]=Integer.parseInt(key.get("swjy").toString());
						}
					}
				}
			}

			List<Map<String, Object>> jls_snjy = jlsDpFxService.find_jls_snjy();//所内就医
			if(!StringUtils.isNullOrEmpty(jls_snjy)){
				for(Map<String, Object> key:jls_snjy){
					for(int i =0;i<jsbh.length;i++) {
						if (key.get("jsbh").toString().contains(String.valueOf(jsbh[i]))){
							snjy[i]=Integer.parseInt(key.get("snjy").toString());
						}
					}
				}
			}

			for(int i =0;i<jsbh.length;i++) {  //就医总数
				all[i] = snjy[i]+swjy[i];
			}

			map.put("swjy",swjy);
			map.put("snjy",snjy);
			map.put("all",all);
			return  ResponseMessage.ok(map);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return  ResponseMessage.error("服务器繁，请重新刷新页面");
		}
	}


	@GetMapping("/select_jls_rygl")
	@ApiOperation("大屏jls人员管理情况分析")
	@OpenAPI
	public ResponseMessage<Map<String, Object>> select_jls_rygl() {
		int[] jsbh = new int[]{110000121, 110101121, 110105121, 110108121, 110111121};
		Map<String, Object> map = new HashMap<String, Object>();
		int[] jj = new int[]{0, 0, 0, 0, 0};//械具
		int[] jb = new int[]{0, 0, 0, 0, 0};//禁闭
		int[] ygry = new int[]{0, 0, 0, 0, 0};//严管人员
		int[] em = new int[]{0, 0, 0, 0, 0};//耳目

		try {
			List<Map<String, Object>> jls_rygl = jlsDpFxService.find_jls_rygl();//人员管理情况分析=械具、禁闭、严管人员
			if(!StringUtils.isNullOrEmpty(jls_rygl)){
				for(Map<String, Object> key:jls_rygl){
					for(int i =0;i<jsbh.length;i++) {
						if (key.get("jsbh").toString().contains(String.valueOf(jsbh[i]))){
							jj[i]=Integer.parseInt(key.get("jj").toString());
							jb[i]=Integer.parseInt(key.get("jb").toString());
							ygry[i]=Integer.parseInt(key.get("ygry").toString());
						}
					}
				}
			}

			List<Map<String, Object>> jls_rygl_em = jlsDpFxService.find_jls_rygl_em();  //人员管理情况分析=耳目
			if(!StringUtils.isNullOrEmpty(jls_rygl_em)){
				for(Map<String, Object> key:jls_rygl_em){
					for(int i =0;i<jsbh.length;i++) {
						if (key.get("jsbh").toString().contains(String.valueOf(jsbh[i]))){
							em[i]=Integer.parseInt(key.get("em").toString());

						}
					}
				}
			}

			map.put("jj",jj);
			map.put("jb",jb);
			map.put("ygry",ygry);
			map.put("em",em);
			return  ResponseMessage.ok(map);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return  ResponseMessage.error("服务器繁，请重新刷新页面");
		}

	}



	@GetMapping("/select_jls_hjrs")
	@ApiOperation("大屏jls会见人数分析")
	@OpenAPI
	public ResponseMessage<Map<String, Object>> select_jls_hjrs() {
		int[] jsbh = new int[]{110000121, 110101121, 110105121, 110108121, 110111121};
		Map<String, Object> map = new HashMap<String, Object>();
		int[] jshj = new int[]{0, 0, 0, 0, 0};//家属会见
		int[] lshj = new int[]{0, 0, 0, 0, 0};//律师会见
		int[] tx = new int[]{0, 0, 0, 0, 0};//提讯
		int[] all = new int[]{0, 0, 0, 0, 0};//总数

		try {
			List<Map<String, Object>> jls_hjrs = jlsDpFxService.find_jls_hjrs();
			if(!StringUtils.isNullOrEmpty(jls_hjrs)){
				for(Map<String, Object> key:jls_hjrs){
					for(int i =0;i<jsbh.length;i++){
						if(key.get("jsbh").toString().contains(String.valueOf(jsbh[i]))){
							jshj[i]=Integer.parseInt(key.get("jshj").toString());
							lshj[i]=Integer.parseInt(key.get("lshj").toString());
							tx[i]=Integer.parseInt(key.get("tx").toString());
							all[i]=Integer.parseInt(key.get("all").toString());
						}
					}
				}
			}
			map.put("jshj",jshj);
			map.put("lshj",lshj);
			map.put("tx",tx);
			map.put("all",all);
			return  ResponseMessage.ok(map);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return  ResponseMessage.error("服务器繁，请重新刷新页面");
		}

	}


	@GetMapping("/select_jls_xdry")
	@ApiOperation("大屏jls吸毒人员分析")
	@OpenAPI
	public ResponseMessage<Map<String, Object>> select_jls_xdry() {
		int[] jsbh = new int[]{110000121, 110101121, 110105121, 110108121, 110111121};
		Map<String, Object> map = new HashMap<String, Object>();
		int[] xdrs = new int[]{0, 0, 0, 0, 0};//吸毒人数
		String[] bfb = new String[]{"0", "0", "0", "0", "0"};//百分比

		try {
			List<Map<String, Object>> jls_xdry = jlsDpFxService.find_jls_xdry();//吸毒人数
			if(!StringUtils.isNullOrEmpty(jls_xdry)){
				for(Map<String, Object> key:jls_xdry){
					for(int i =0;i<jsbh.length;i++){
						if(key.get("jsbh").toString().contains(String.valueOf(jsbh[i]))){
							xdrs[i]=Integer.parseInt(key.get("ay").toString());
						}
					}
				}
			}

			Map<String, Object> jls_xdry_all = jlsDpFxService.find_jls_xdry_all();//百分比=总人数
			if(!StringUtils.isNullOrEmpty(jls_xdry_all)){
				for(Object key:jls_xdry_all.keySet()){

					if(!"0".equals(jls_xdry_all.get("ay").toString())){
						for(int i =0;i<jsbh.length;i++) {
							int ay = Integer.parseInt(jls_xdry_all.get("ay").toString());

							String format = String.format("%.2f", (float) Integer.parseInt(xdrs[i] + "") / (float) Integer.parseInt(ay + "") * 100);
							bfb[i]=format;
						}
					}
				}
			}
			map.put("xdrs",xdrs);
			map.put("bfb",bfb);
			return  ResponseMessage.ok(map);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return  ResponseMessage.error("服务器繁，请重新刷新页面");

		}
	}



	@GetMapping("/select_jls_fxdj")
	@ApiOperation("大屏jls风险情况分析")
	@OpenAPI
	public ResponseMessage<Map<String, Object>> select_jls_fxdj() {
		int[] jsbh = new int[]{110000121, 110101121, 110105121, 110108121, 110111121};
		Map<String, Object> map = new HashMap<String, Object>();
		int[] zdyj = new int[]{0, 0, 0, 0, 0};//重大一级
		int[] zdej = new int[]{0, 0, 0, 0, 0};//重大二级
		int[] zdsj = new int[]{0, 0, 0, 0, 0};//重大三级

		try {
			List<Map<String, Object>> jls_fxdj = jlsDpFxService.find_jls_fxdj();
			if(!StringUtils.isNullOrEmpty(jls_fxdj)){
				for(Map<String, Object> key:jls_fxdj){
					for(int i =0;i<jsbh.length;i++){
						if(key.get("jsbh").toString().contains(String.valueOf(jsbh[i]))){
							zdyj[i]=Integer.parseInt(key.get("yj").toString());
							zdej[i]=Integer.parseInt(key.get("ej").toString());
							zdsj[i]=Integer.parseInt(key.get("sj").toString());

						}
					}
				}
			}
			map.put("zdyj",zdyj);
			map.put("zdej",zdej);
			map.put("zdsj",zdsj);
			return ResponseMessage.ok(map);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return  ResponseMessage.error("服务器繁，请重新刷新页面");
		}

	}


	@GetMapping("/select_jls_gyqx")
	@ApiOperation("关押期限分析(3月以上   3-6   6-1  1-3  3)")
	@OpenAPI
	public ResponseMessage<Map<String, Object>> select_jls_gyqx() {
		int[] jsbh = new int[]{110000121, 110101121, 110105121, 110108121, 110111121};
		Map<String, Object> map = new HashMap<String, Object>();
		int[] three = new int[]{0, 0, 0, 0, 0};//三个月
		int[] six = new int[]{0, 0, 0, 0, 0};//6个月
		int[] oneyear = new int[]{0, 0, 0, 0, 0};//6月-1年
		int[] threeyear = new int[]{0, 0, 0, 0, 0};//1年-3年
		int[] threeyearover = new int[]{0, 0, 0, 0, 0};//3年以上

		try {
			List<Map<String, Object>> jls_gyqx = jlsDpFxService.find_jls_gyqx();
			if(!StringUtils.isNullOrEmpty(jls_gyqx)){
				for(Map<String, Object> key: jls_gyqx){
					for(int i =0;i<jsbh.length;i++){
						if(key.get("jsbh").toString().contains(String.valueOf(jsbh[i]))){
							three[i]=Integer.parseInt(key.get("three").toString());
							six[i]=Integer.parseInt(key.get("six").toString());
							oneyear[i]=Integer.parseInt(key.get("oneyear").toString());
							threeyear[i]=Integer.parseInt(key.get("threeyear").toString());
							threeyearover[i]=Integer.parseInt(key.get("threeyearover").toString());
						}
					}
				}
			}
			map.put("three",three);
			map.put("six",six);
			map.put("oneyear",oneyear);
			map.put("threeyear",threeyear);
			map.put("threeyearover",threeyearover);
			return  ResponseMessage.ok(map);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return  ResponseMessage.error("服务器繁，请重新刷新页面");
		}

	}



	@GetMapping("/jsryfx")
	@ApiOperation("拒收人员分析")
	@OpenAPI
	public ResponseMessage<Map<String, Object>> jsryfx() {
		int[] jsbh = new int[]{110000121, 110101121, 110105121, 110108121, 110111121};
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> lslsfxCountlist = jlsDpFxService.jsryfx();
			int[] js = new int[]{0, 0, 0, 0, 0};
			Double[] bfb = new Double[]{0.0, 0.0, 0.0, 0.0, 0.0};
			if (lslsfxCountlist.size() > 0 && !lslsfxCountlist.isEmpty()) {
				for (Map<String, Object> maps : lslsfxCountlist) {
					for (int i = 0; i < jsbh.length; i++) {
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							js[i] = js[i] + Integer.parseInt(maps.get("js").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							bfb[i] = bfb[i] + Double.parseDouble(maps.get("bfb").toString());
						}
					}
				}
				map.put("js", js);
				map.put("bfb", bfb);
				return ResponseMessage.ok(map);
			} else {
				map.put("js", 0);
				map.put("bfb", 0);
				return ResponseMessage.ok(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败");
		}
	}
	@GetMapping("/rsxz")
	@ApiOperation("入所性质")
	@OpenAPI
	public List rsxz(@RequestParam(value = "jsbh",required =false )String jsbh) {
		String jsbh2=jsbh+"%";
		Map<String, Object> map1 = new HashMap<String, Object>();
		Map<String, Object> map2 = new HashMap<String, Object>();
		Map<String, Object> map3 = new HashMap<String, Object>();
		Map<String, Object> map4 = new HashMap<String, Object>();
		Map<String, Object> map5 = new HashMap<String, Object>();
		Map<String, Object> map6 = new HashMap<String, Object>();
		Map<String, Object> map7 = new HashMap<String, Object>();
		Map<String, Object> map8 = new HashMap<String, Object>();
		Map<String, Object> map9 = new HashMap<String, Object>();
		Map<String, Object> map10 = new HashMap<String, Object>();
		Map<String, Object> map11 = new HashMap<String, Object>();
		Map<String, Object> map12 = new HashMap<String, Object>();
		Map<String, Object> map13 = new HashMap<String, Object>();
		Map<String, Object> map15 = new HashMap<String, Object>();
		Map<String, Object> map16 = new HashMap<String, Object>();
		List list1=new ArrayList();
		try {
			List<Map<String, Object>> list = jlsDpFxService.rsxz(jsbh2);
			for (int i=0;i<list.size();i++){
				String xsjl = list.get(i).get("xsjl").toString();
				String sfjl = list.get(i).get("sfjl").toString();
				String jlsc = list.get(i).get("jlsc").toString();
				String xcjl = list.get(i).get("xcjl").toString();
				String lsjj = list.get(i).get("lsjj").toString();
				String wsszr = list.get(i).get("wsszr").toString();
				String bsszr = list.get(i).get("bsszr").toString();
				String ydjl = list.get(i).get("ydjl").toString();
				String wgjrdq = list.get(i).get("wgjrdq").toString();
				String gatjrdq = list.get(i).get("gatjrdq").toString();
				String ccszl = list.get(i).get("ccszl").toString();
				String tpsh = list.get(i).get("tpsh").toString();
				String qtsh = list.get(i).get("qtsh").toString();
				String tsws = list.get(i).get("tsws").toString();
				String qt = list.get(i).get("qt").toString();
				map1.put("name","行政拘留");
				map1.put("value",xsjl);
				map2.put("name","司法拘留");
				map2.put("value",sfjl);
				map3.put("name","拘留审查");
				map3.put("value",jlsc);
				map4.put("name","现场拘留");
				map4.put("value",xcjl);
				map5.put("name","临时寄拘");
				map5.put("value",lsjj);
				map6.put("name","外省市转入");
				map6.put("value",wsszr);
				map7.put("name","本省市转入");
				map7.put("value",bsszr);
				map8.put("name","异地拘留");
				map8.put("value",ydjl);
				map9.put("name","外国籍人待遣");
				map9.put("value",wgjrdq);
				map10.put("name","港澳台居民待遣");
				map10.put("value",gatjrdq);
				map11.put("name","撤销出所治疗");
				map11.put("value",ccszl);
				map12.put("name","逃跑收回");
				map12.put("value",tpsh);
				map13.put("name","其他收回");
				map13.put("value",qtsh);
				map15.put("name","投送未收");
				map15.put("value",tsws);
				map16.put("name","其它");
				map16.put("value",qt);
			}
			list1.add(map1);
			list1.add(map2);
			list1.add(map3);
			list1.add(map4);
			list1.add(map5);
			list1.add(map6);
			list1.add(map7);
			list1.add(map8);
			list1.add(map9);
			list1.add(map10);
			list1.add(map11);
			list1.add(map12);
			list1.add(map13);
			list1.add(map15);
			list1.add(map16);
			return list1;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@GetMapping("/ajlb")
	@ApiOperation("案件类别分析")
	@OpenAPI
	public List ajlb(@RequestParam(value = "jsbh",required =false )String jsbh) {
		String jsbh2=jsbh+"%";
		Map<String, Object> map1 = new HashMap<String, Object>();
		Map<String, Object> map2 = new HashMap<String, Object>();
		Map<String, Object> map3 = new HashMap<String, Object>();
		Map<String, Object> map4 = new HashMap<String, Object>();
		Map<String, Object> map5 = new HashMap<String, Object>();
		Map<String, Object> map6 = new HashMap<String, Object>();
		Map<String, Object> map7 = new HashMap<String, Object>();
		Map<String, Object> map8 = new HashMap<String, Object>();
		Map<String, Object> map9 = new HashMap<String, Object>();
		Map<String, Object> map10 = new HashMap<String, Object>();
		Map<String, Object> map11 = new HashMap<String, Object>();
		Map<String, Object> map12 = new HashMap<String, Object>();
		Map<String, Object> map13 = new HashMap<String, Object>();
		List list1=new ArrayList();
		try {
			List<Map<String, Object>> list = jlsDpFxService.ajlb(jsbh2);
			for (int i=0;i<list.size();i++){
				String gjmm = list.get(i).get("gjmm").toString();
				String ggaq = list.get(i).get("ggaq").toString();
				String gzdj = list.get(i).get("gzdj").toString();
				String wxwz = list.get(i).get("wxwz").toString();
				String dw = list.get(i).get("dw").toString();
				String ccql = list.get(i).get("ccql").toString();
				String odtr = list.get(i).get("odtr").toString();
				String gysh = list.get(i).get("gysh").toString();
				String dq = list.get(i).get("dq").toString();
				String zp = list.get(i).get("zp").toString();
				String qzls = list.get(i).get("qzls").toString();
				String fhshgl = list.get(i).get("fhshgl").toString();
				String zyzp = list.get(i).get("zyzp").toString();
				map1.put("name","故意或者过失泄露有关国家安全工作的国家秘密");
				map1.put("value",gjmm);
				map2.put("name","妨害公共安全的案件");
				map2.put("value",ggaq);
				map3.put("name","非法携带枪支、弹药、管制器具");
				map3.put("value",gzdj);
				map4.put("name","非法制造、买卖、储存、运输、邮寄、携带、使用、提供危险物质");
				map4.put("value",wxwz);
				map5.put("name","擅自安装、使用电网");
				map5.put("value",dw);
				map6.put("name","侵犯他人人身权利、财产权利的案件");
				map6.put("value",ccql);
				map7.put("name","殴打他人");
				map7.put("value",odtr);
				map8.put("name","故意伤害");
				map8.put("value",gysh);
				map9.put("name","盗窃");
				map9.put("value",dq);
				map10.put("name","诈骗");
				map10.put("value",zp);
				map11.put("name","敲诈勒索");
				map11.put("value",qzls);
				map12.put("name","妨害社会管理的案件");
				map12.put("value",fhshgl);
				map13.put("name","招摇撞骗");
				map13.put("value",zyzp);

			}
			list1.add(map1);
			list1.add(map2);
			list1.add(map3);
			list1.add(map4);
			list1.add(map5);
			list1.add(map6);
			list1.add(map7);
			list1.add(map8);
			list1.add(map9);
			list1.add(map10);
			list1.add(map11);
			list1.add(map12);
			list1.add(map13);
			return list1;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@GetMapping("/zccs")
	@ApiOperation("正常出所对比分析")
	@OpenAPI
	public ResponseMessage<Map<String, Object>> zccs() {
		int[] jsbh = new int[]{110000121, 110101121, 110105121, 110108121, 110111121};
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> lslsfxCountlist = jlsDpFxService.zccs();
			int[] jn = new int[]{0, 0, 0, 0, 0};
			int[] qn = new int[]{0, 0, 0, 0, 0};
			if (lslsfxCountlist.size() > 0 && !lslsfxCountlist.isEmpty()) {
				for (Map<String, Object> maps : lslsfxCountlist) {
					for (int i = 0; i < jsbh.length; i++) {
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							jn[i] = jn[i] + Integer.parseInt(maps.get("jn").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							qn[i] = qn[i] + Integer.parseInt(maps.get("qn").toString());
						}
					}
				}
				map.put("jn", jn);
				map.put("qn", qn);
				return ResponseMessage.ok(map);
			} else {
				map.put("jn", 0);
				map.put("qn", 0);
				return ResponseMessage.ok(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败");
		}
	}
	@GetMapping("/lscsfx")
	@ApiOperation("临时出所分析")
	@OpenAPI
	public ResponseMessage<Map<String, Object>> lscsfx() {
		int[] jsbh = new int[]{110000121, 110101121, 110105121, 110108121, 110111121};
		Map<String, Object> map = new HashMap<String, Object>();

		List<Map<String, Object>> lslsfxCountlist = jlsDpFxService.lscsfx();
		int[] ktsl = new int[]{0, 0, 0, 0, 0};
		int[] gjfba = new int[]{0, 0, 0, 0, 0};
		int[] tq = new int[]{0, 0, 0, 0, 0, 0};
		int[] kb = new int[]{0, 0, 0, 0, 0};
		int[] zy = new int[]{0, 0, 0, 0, 0};
		int[] ld = new int[]{0, 0, 0, 0, 0};
		int[] qt = new int[]{0, 0, 0, 0, 0};
		int[] swjy = new int[]{0, 0, 0, 0, 0};
		int[] qjcs = new int[]{0, 0, 0, 0, 0};

		try{
			if (lslsfxCountlist.size()>0 && !lslsfxCountlist.isEmpty()) {
				for (Map<String, Object> maps : lslsfxCountlist) {
					for (int i = 0; i < jsbh.length; i++) {
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							ktsl[i] = ktsl[i] + Integer.parseInt(maps.get("ktsl").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							gjfba[i] = gjfba[i] + Integer.parseInt(maps.get("gjfba").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							tq[i] = tq[i] + Integer.parseInt(maps.get("tq").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							kb[i] = kb[i] + Integer.parseInt(maps.get("kb").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							zy[i] = zy[i] + Integer.parseInt(maps.get("zy").toString());
						}

						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							ld[i] = ld[i] + Integer.parseInt(maps.get("ld").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							qt[i] = qt[i] + Integer.parseInt(maps.get("qt").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							swjy[i] = swjy[i] + Integer.parseInt(maps.get("swjy").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							qjcs[i] = qjcs[i] + Integer.parseInt(maps.get("qjcs").toString());
						}
					}
				}
				map.put("ktsl", ktsl);
				map.put("gjfba", gjfba);
				map.put("tq", tq);
				map.put("kb", kb);
				map.put("zy", zy);
				map.put("ld", ld);
				map.put("qt", qt);
				map.put("swjy", swjy);
				map.put("qjcs", qjcs);
				return ResponseMessage.ok(map);
			}else {
				map.put("ktsl", 0);
				map.put("gjfba", 0);
				map.put("tq", 0);
				map.put("kb", 0);
				map.put("zy", 0);
				map.put("ld", 0);
				map.put("qt", 0);
				map.put("swjy", 0);
				map.put("qjcs", 0);
				return ResponseMessage.ok(map);
			}
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败");
		}
	}
	@GetMapping("/csyyfx")
	@ApiOperation("出所原因分析")
	@OpenAPI
	public List csyyfx(@RequestParam(value = "jsbh",required =false )String jsbh) {
		String jsbh2=jsbh+"%";
		Map<String, Object> map1 = new HashMap<String, Object>();
		Map<String, Object> map2 = new HashMap<String, Object>();
		Map<String, Object> map3 = new HashMap<String, Object>();
		Map<String, Object> map4 = new HashMap<String, Object>();
		Map<String, Object> map5 = new HashMap<String, Object>();
		Map<String, Object> map6 = new HashMap<String, Object>();
		Map<String, Object> map7 = new HashMap<String, Object>();
		Map<String, Object> map8 = new HashMap<String, Object>();
		Map<String, Object> map9 = new HashMap<String, Object>();
		Map<String, Object> map10 = new HashMap<String, Object>();
		Map<String, Object> map11 = new HashMap<String, Object>();
		Map<String, Object> map12 = new HashMap<String, Object>();
		List list1=new ArrayList();
		try {
			List<Map<String, Object>> list = jlsDpFxService.csyyfx(jsbh2);
			for (int i=0;i<list.size();i++){
				String yzjb = list.get(i).get("yzjb").toString();
				String jlqm = list.get(i).get("jlqm").toString();
				String tqjcjl = list.get(i).get("tqjcjl").toString();
				String zqtjs = list.get(i).get("zqtjs").toString();
				String jd = list.get(i).get("jd").toString();
				String xsjl = list.get(i).get("xsjl").toString();
				String db = list.get(i).get("db").toString();
				String lsjj = list.get(i).get("lsjj").toString();
				String tp = list.get(i).get("tp").toString();
				String sw = list.get(i).get("sw").toString();
				String zqts = list.get(i).get("zqts").toString();
				String qt = list.get(i).get("qt").toString();
				map1.put("name","被拘留审查人患有严重疾病");
				map1.put("value",yzjb);
				map2.put("name","拘留期满");
				map2.put("value",jlqm);
				map3.put("name","提前解除拘留");
				map3.put("value",tqjcjl);
				map4.put("name","转其他教所");
				map4.put("value",zqtjs);
				map5.put("name","转强制隔离戒毒或责令社区戒毒");
				map5.put("value",jd);
				map6.put("name","转刑事拘留");
				map6.put("value",xsjl);
				map7.put("name","转逮捕");
				map7.put("value",db);
				map8.put("name","临时寄拘带走");
				map8.put("value",lsjj);
				map9.put("name","逃跑");
				map9.put("value",tp);
				map10.put("name","死亡");
				map10.put("value",sw);
				map11.put("name","转其他所");
				map11.put("value",zqts);
				map12.put("name","其它");
				map12.put("value",qt);
			}
			list1.add(map1);
			list1.add(map2);
			list1.add(map3);
			list1.add(map4);
			list1.add(map5);
			list1.add(map6);
			list1.add(map7);
			list1.add(map8);
			list1.add(map9);
			list1.add(map10);
			list1.add(map11);
			list1.add(map12);
			return list1;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@GetMapping("/swfz")
	@ApiOperation("深挖犯罪")
	@OpenAPI
	public ResponseMessage<Map<String, Object>> swfz() {
		int[] jsbh = new int[]{110000121, 110101121, 110105121, 110108121, 110111121};
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> lslsfxCountlist = jlsDpFxService.swfz();
			int[] tbjd = new int[]{0, 0, 0, 0, 0};
			int[] jj = new int[]{0, 0, 0, 0, 0};
			int[] zs = new int[]{0, 0, 0, 0, 0};
			if (lslsfxCountlist.size() > 0 && !lslsfxCountlist.isEmpty()) {
				for (Map<String, Object> maps : lslsfxCountlist) {
					for (int i = 0; i < jsbh.length; i++) {
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							tbjd[i] = tbjd[i] + Integer.parseInt(maps.get("tbjd").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							jj[i] = jj[i] + Integer.parseInt(maps.get("jj").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							zs[i] = zs[i] + Integer.parseInt(maps.get("zs").toString());
						}
					}
				}
				map.put("tbjd", tbjd);
				map.put("jj", jj);
				map.put("zs", zs);
				return ResponseMessage.ok(map);
			} else {
				map.put("man", 0);
				map.put("jj", 0);
				map.put("zs", 0);
				return ResponseMessage.ok(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败");
		}
	}
	@GetMapping("/yzblfz")
	@ApiOperation("严重暴力犯罪")
	@OpenAPI
	public ResponseMessage<Map<String, Object>> yzblfz() {
		int[] jsbh = new int[]{110000121, 110101121, 110105121, 110108121, 110111121};
		Map<String, Object> map = new HashMap<String, Object>();

		List<Map<String, Object>> lslsfxCountlist = jlsDpFxService.yzblfz();
		int[] gysh = new int[]{0, 0, 0, 0, 0};
		int[] fmdp = new int[]{0, 0, 0, 0, 0};
		int[] dq = new int[]{0, 0, 0, 0, 0};
		int[] zp = new int[]{0, 0, 0, 0, 0};
		int[] hq = new int[]{0, 0, 0, 0, 0};
		int[] qd = new int[]{0, 0, 0, 0, 0};
		int[] qzls = new int[]{0, 0, 0, 0, 0};
		int[] nd = new int[]{0, 0, 0, 0, 0};

		try{
			if (lslsfxCountlist.size()>0 && !lslsfxCountlist.isEmpty()) {
				for (Map<String, Object> maps : lslsfxCountlist) {
					for (int i = 0; i < jsbh.length; i++) {
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							gysh[i] = gysh[i] + Integer.parseInt(maps.get("gysh").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							fmdp[i] = fmdp[i] + Integer.parseInt(maps.get("fmdp").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							dq[i] = dq[i] + Integer.parseInt(maps.get("dq").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							zp[i] = zp[i] + Integer.parseInt(maps.get("zp").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							hq[i] = hq[i] + Integer.parseInt(maps.get("hq").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							qd[i] = qd[i] + Integer.parseInt(maps.get("qd").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							qzls[i] = qzls[i] + Integer.parseInt(maps.get("qzls").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							nd[i] = nd[i] + Integer.parseInt(maps.get("nd").toString());
						}
					}
				}
				map.put("gysh", gysh);
				map.put("fmdp", fmdp);
				map.put("dq", dq);
				map.put("zp", zp);
				map.put("hq", hq);
				map.put("qd", qd);
				map.put("qzls", qzls);
				map.put("nd", nd);
				return ResponseMessage.ok(map);
			}else {
				map.put("gysh", gysh);
				map.put("fmdp", fmdp);
				map.put("dq", dq);
				map.put("zp", zp);
				map.put("hq", hq);
				map.put("qd", qd);
				map.put("qzls", qzls);
				map.put("nd", nd);
				return ResponseMessage.ok(map);
			}
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败");
		}
	}

	@GetMapping("/shsefx")
	@ApiOperation("涉黑涉恶案件分析")
	@OpenAPI
	public List shsefx(@RequestParam(value = "jsbh",required =false )String jsbh) {
		String jsbh2=jsbh+"%";
		Map<String, Object> map1 = new HashMap<String, Object>();
		Map<String, Object> map2 = new HashMap<String, Object>();
		Map<String, Object> map3 = new HashMap<String, Object>();
		Map<String, Object> map4 = new HashMap<String, Object>();
		Map<String, Object> map5 = new HashMap<String, Object>();
		Map<String, Object> map6 = new HashMap<String, Object>();
		Map<String, Object> map7 = new HashMap<String, Object>();
		Map<String, Object> map8 = new HashMap<String, Object>();
		Map<String, Object> map10 = new HashMap<String, Object>();
		Map<String, Object> map11 = new HashMap<String, Object>();
		List list1=new ArrayList();
		try {
			List<Map<String, Object>> list = jlsDpFxService.shsefx(jsbh2);
			for (int i=0;i<list.size();i++){
				String whsh = list.get(i).get("whsh").toString();
				String xxzs = list.get(i).get("xxzs").toString();
				String db = list.get(i).get("db").toString();
				String jzdo = list.get(i).get("jzdo").toString();
				String qzls = list.get(i).get("qzls").toString();
				String ffjj = list.get(i).get("ffjj").toString();
				String zzmy = list.get(i).get("zzmy").toString();
				String qpjy = list.get(i).get("qpjy").toString();
				String qpmy = list.get(i).get("qpmy").toString();
				String gyshcw = list.get(i).get("gyshcw").toString();
				map1.put("name","冒用宗教、会道门等迷信活动危害社会");
				map1.put("value",whsh);
				map2.put("name","寻衅滋事");
				map2.put("value",xxzs);
				map3.put("name","赌博");
				map3.put("value",db);
				map4.put("name","殴打他人");
				map4.put("value",jzdo);
				map5.put("name","敲诈勒索");
				map5.put("value",qzls);
				map6.put("name","非法拘禁");
				map6.put("value",ffjj);
				map7.put("name","组织卖淫");
				map7.put("value",zzmy);
				map8.put("name","强迫交易");
				map8.put("value",qpjy);
				map10.put("name","强迫卖淫");
				map10.put("value",qpmy);
				map11.put("name","故意损坏财物");
				map11.put("value",gyshcw);
			}
			list1.add(map1);
			list1.add(map2);
			list1.add(map3);
			list1.add(map4);
			list1.add(map5);
			list1.add(map6);
			list1.add(map7);
			list1.add(map8);
			list1.add(map10);
			list1.add(map11);
			return list1;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}