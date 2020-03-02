package awd.cloud.platform.servers.analyse.controller.kss;

import awd.cloud.platform.servers.analyse.service.kss.kssDpFxService;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/kss/zddpfx")
@Api(tags = "kss_zddpfx",description = "总队大屏分析")
public class kssDpFxController {

	@Autowired
	private kssDpFxService kssDpFxService;


   @GetMapping("/select_kss_hjrs")
   @ApiOperation("kss会见人数分析 : 家属会见、律师会见、提讯、总数")
   @OpenAPI
   public ResponseMessage<Map<String, Object>> select_kss_hjrs() {
	   int[] jsbh = new int[]{1100001, 110101111, 110102111, 110105111, 110106111, 110107111, 110108111, 110109111, 11011111, 110112111, 110113111, 110221111, 110224111, 110226111, 110227111, 110228111, 110229111};
	   Map<String, Object> map = new HashMap<String, Object>();
	   List<Map<String, Object>> kss_hjrs = kssDpFxService.find_kss_hjrs();
	   int[] jshj = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};//家属会见
	   int[] lshj = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};//律师会见
	   int[] tx = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};//提讯
	   int[] all = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};//总数

	   try {
		   if(kss_hjrs.size()>0){
			   for(Map<String, Object> key:kss_hjrs){
			   	String JSHJ = "0";
				String LSHJ = "0";
				String TX = "0";
				String ALL = "0";
				   if(key!=null) {
                       Object jshj1 = key.get("jshj");
                       Object lshj1 = key.get("lshj");
                       Object tx1 = key.get("tx");
                       Object all1 = key.get("all");
                       Object JSBH = key.get("jsbh");
                       if (jshj1 != null && !"".equals(jshj1.toString())) {
                           JSHJ = jshj1.toString();
                       }
                       if (lshj1 != null && !"".equals(lshj1.toString())) {
                           LSHJ = lshj1.toString();
                       }
                       if (tx1 != null && !"".equals(tx1.toString())) {
                           TX = tx1.toString();
                       }
                       if (all1 != null && !"".equals(all1.toString())) {
                           ALL = all1.toString();
                       }
                       if (JSBH != null && !"".equals(JSBH.toString())){
                           for (int i = 0; i < jsbh.length; i++) {
                               if (key.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
                                   jshj[i] = jshj[i] + Integer.parseInt(JSHJ);
                                   lshj[i] = lshj[i] + Integer.parseInt(LSHJ);
                                   tx[i] = tx[i] + Integer.parseInt(TX);
                                   all[i] = all[i] + Integer.parseInt(ALL);
                               }
                                   continue;

                           }
                   }
				}
			}
			   map.put("jshj", jshj);
			   map.put("lshj", lshj);
			   map.put("tx", tx);
			   map.put("all", all);
			   return ResponseMessage.ok(map);

		   }else{
			   map.put("jshj", 0);
			   map.put("lshj", 0);
			   map.put("tx", 0);
			   map.put("all", 0);
			   return ResponseMessage.ok(map);
		   }

	   } catch (NumberFormatException e) {
		   e.printStackTrace();
		   return ResponseMessage.error("查询失败");
	   }


   }


	@GetMapping("/select_kss_zszt")
	@ApiOperation("kss在所状态 :  已决、未决")
	@OpenAPI
	public ResponseMessage<Map<String, Object>> select_kss_zszt() {
		int[] jsbh = new int[]{1100001, 110101111, 110102111, 110105111, 110106111, 110107111, 110108111, 110109111, 11011111, 110112111, 110113111, 110221111, 110224111, 110226111, 110227111, 110228111, 110229111};
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> kss_zszt = kssDpFxService.find_kss_zszt();
		int[] yj = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};//已决
		int[] wj = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};//未决

		try {
			if(kss_zszt.size()>0){
					String YJ="0";
					String WJ="0";
				for(Map<String, Object> key:kss_zszt){
					if(key!=null){
						Object yj1 = key.get("yj");
						Object wj1 = key.get("wj");
                        Object JSBH = key.get("jsbh");
					 if(yj1!=null && !"".equals(yj1)){
						 YJ=yj1.toString();
					 }
					 if(wj1!=null && !"".equals(wj1)){
						 WJ=wj1.toString();
					 }
                     if(JSBH.toString()!=null && !"".equals(JSBH.toString())) {
                         for (int i = 0; i < jsbh.length; i++) {
                             if (JSBH.toString().contains(String.valueOf(jsbh[i]))) {
                                 yj[i] = yj[i] + Integer.parseInt(YJ);
                                 wj[i] = wj[i] + Integer.parseInt(WJ);
                             }
                                continue;
                         }
                     }
					}
				}
				map.put("yj", yj);
				map.put("wj", wj);
				return ResponseMessage.ok(map);

			}else{
				map.put("yj", 0);
				map.put("wj", 0);
				return ResponseMessage.ok(map);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败");
		}

	}


	@GetMapping("/select_kss_rygl")
    @ApiOperation("kss人员管理情况分析 ：械具、禁闭、单独关押、严管人员、耳目")
    @OpenAPI
    public ResponseMessage<Map<String, Object>> select_kss_rygl() {
        int[] jsbh = new int[]{1100001, 110101111, 110102111, 110105111, 110106111, 110107111, 110108111, 110109111, 11011111, 110112111, 110113111, 110221111, 110224111, 110226111, 110227111, 110228111, 110229111};

        int[] jj = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};//械具
        int[] jb = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};//禁闭
        //单独关押
        int[] ddgy = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        //严管人员
        int[] ygry = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        //狱侦耳目
        int[] yzem = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
       /* //安全耳目
        int[] aqem = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};*/
        Map<String, Object> map = new HashMap<String, Object>();
        List<Map<String, Object>> kss_rygl = kssDpFxService.find_kss_rygl();

        try {
            if(kss_rygl.size()>0){
                for(Map<String, Object> key:kss_rygl){
                    String JSBH="";
                    String JJ="0";
                    String JB="0";
                    String DDGY="0"; String YGRY="0"; String YZEM="0"; //String AQEM="0";

                    Object jsbh1 = key.get("jsbh");
                    Object jj1 = key.get("jj");
                    Object jb1 = key.get("jb");
                    Object ddgy1 = key.get("ddgy");
                    Object ygry1 = key.get("ygry");
                    Object yzem1 = key.get("yzem");
                    //Object aqem1 = key.get("aqem");

                    if(jj1!=null &&!"".equals(jj1.toString())){
                        JJ=jj1.toString();
                    }
                    if(jb1!=null &&!"".equals(jb1.toString())){
                        JB=jb1.toString();
                    }
                    if(ddgy1!=null &&!"".equals(ddgy1.toString())){
                        DDGY=ddgy1.toString();
                    }
                    if(ygry1!=null &&!"".equals(ygry1.toString())){
                        YGRY=ygry1.toString();
                    }
                    if(yzem1!=null &&!"".equals(yzem1.toString())){
                        YZEM=yzem1.toString();
                    }
                    /*if(aqem1!=null &&!"".equals(aqem1.toString())){
                        AQEM=aqem1.toString();
                    }*/
                    if(jsbh1!=null &&!"".equals(jsbh1.toString())){
                        JSBH=jsbh1.toString();
                        for(int i =0;i<jsbh.length;i++){
                            if(JSBH.contains(String.valueOf(jsbh[i]))) {
                                jj[i] = jj[i] + Integer.parseInt(JJ);
                                jb[i] = jb[i] + Integer.parseInt(JB);
                                ddgy[i] = ddgy[i] + Integer.parseInt(DDGY);
                                ygry[i] = ygry[i] + Integer.parseInt(YGRY);
                                yzem[i] = yzem[i] + Integer.parseInt(YZEM);
                                //aqem[i] = aqem[i] + Integer.parseInt(AQEM);
                            }
                                continue;
                        }
                    }

                }
                map.put("jj",jj);map.put("jb",jb);map.put("ddgy",ddgy);
                map.put("ygry",ygry);map.put("yzem",yzem);//map.put("aqem",aqem);
                return ResponseMessage.ok(map);
            }else {
                map.put("jj",0);map.put("jb",0);map.put("ddgy",0);
                map.put("ygry",0);map.put("yzem",0);//map.put("aqem",0);
                return ResponseMessage.ok(map);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return ResponseMessage.error("查询失败");
        }

    }



    @GetMapping("/select_kss_qsbajd")
    @ApiOperation("kss全省办案阶段分析")
    @OpenAPI
    public ResponseMessage<List<Object>> select_kss_qsbajd(@RequestParam(value = "jsbh") String jsbh) {


        try {

            List<Map<String, Object>> kss_rygl = kssDpFxService.find_kss_qsbajd(jsbh);
            ArrayList<Object> list = new ArrayList<>();
            if(kss_rygl.size()>0){
                for(Map<String, Object> key:kss_rygl){

                    String kss_xsjl="0";String kss_db="0";String kss_gabhzc1="0";
                    String kss_gabhzc2="0";String kss_scqs1="0";String kss_scqs2="0";
                    String kss_scqs3="0";String kss_jcbczc="0";String kss_ys="0";
                    String kss_es="0";String kss_fhcs="0";String kss_sxfh="0";
                    String kss_dzx="0";String kss_yj="0";String kss_qt="0";
                    String kss_ysssq="0";String kss_zs="0";

                    if(key!=null){

                        Object xsjl = key.get("xsjl");//刑事拘留
                        Object db = key.get("db");//逮捕
                        Object gabhzc1 = key.get("gabhzc1");//公安补充侦查(1)
                        Object gabhzc2 = key.get("gabhzc2");//公安补充侦查(2)
                        Object scqs1 = key.get("scqs1");//审查起诉(1)
                        Object scqs2 = key.get("scqs2");//审查起诉(2)
                        Object scqs3 = key.get("scqs3");//审查起诉(3)
                        Object jcbczc = key.get("jcbczc");//检察补充侦查
                        Object ys = key.get("ys");//一审
                        Object es = key.get("es");//二审
                        Object fhcs = key.get("fhcs");//发回重审
                        Object sxfh = key.get("sxfh");//死刑复核
                        Object dzx = key.get("dzx");//待执行
                        Object yj = key.get("yj");//已决
                        Object qt = key.get("qt");//其它
                        Object ysssq = key.get("ysssq");//一审上诉期
                        Object zs = key.get("zs");//再审
                        if(xsjl!=null&&!"".equals(xsjl.toString())){
                            kss_xsjl=xsjl.toString();
                            HashMap<String, Object> map = new HashMap<>();
                            map.put("name","刑事拘留");
                            map.put("value",kss_xsjl);
                            list.add(map);
                        }
                        if(db!=null&&!"".equals(db.toString())){
                            kss_db=db.toString();
                            HashMap<String, Object> map1 = new HashMap<>();
                            map1.put("name","逮捕");
                            map1.put("value",kss_db);
                            list.add(map1);
                        }
                        if(gabhzc1!=null&&!"".equals(gabhzc1.toString())){
                            kss_gabhzc1=gabhzc1.toString();
                            HashMap<String, Object> map2 = new HashMap<>();
                            map2.put("name","公安补充侦查(1)");
                            map2.put("value",kss_gabhzc1);
                            list.add(map2);
                        }
                        if(gabhzc2!=null&&!"".equals(gabhzc2.toString())){
                            kss_gabhzc2=gabhzc2.toString();
                            HashMap<String, Object> map3 = new HashMap<>();
                            map3.put("name","公安补充侦查(2)");
                            map3.put("value",kss_gabhzc2);
                            list.add(map3);
                        }
                        if(scqs1!=null&&!"".equals(scqs1.toString())){
                            kss_scqs1=scqs1.toString();
                            HashMap<String, Object> map4 = new HashMap<>();
                            map4.put("name","审查起诉(1)");
                            map4.put("value",kss_scqs1);
                            list.add(map4);
                        }
                        if(scqs2!=null&&!"".equals(scqs2.toString())){
                            kss_scqs2=scqs2.toString();
                            HashMap<String, Object> map5 = new HashMap<>();
                            map5.put("name","审查起诉(2)");
                            map5.put("value",kss_scqs2);
                            list.add(map5);
                        }
                        if(scqs3!=null&&!"".equals(scqs3.toString())){
                            kss_scqs3=scqs3.toString();
                            HashMap<String, Object> map6 = new HashMap<>();
                            map6.put("name","审查起诉(3)");
                            map6.put("value",kss_scqs3);
                            list.add(map6);
                        }
                        if(jcbczc!=null&&!"".equals(jcbczc.toString())){
                            kss_jcbczc=jcbczc.toString();
                            HashMap<String, Object> map7 = new HashMap<>();
                            map7.put("name","检察补充侦查");
                            map7.put("value",kss_jcbczc);
                            list.add(map7);
                        }
                        if(ys!=null&&!"".equals(ys.toString())){
                            kss_ys=ys.toString();
                            HashMap<String, Object> map8 = new HashMap<>();
                            map8.put("name","一审");
                            map8.put("value",kss_ys);
                            list.add(map8);
                        }
                        if(es!=null&&!"".equals(es.toString())){
                            kss_es=es.toString();
                            HashMap<String, Object> map9 = new HashMap<>();
                            map9.put("name","二审");
                            map9.put("value",kss_es);
                            list.add(map9);
                        }
                        if(fhcs!=null&&!"".equals(fhcs.toString())){
                            kss_fhcs=fhcs.toString();
                            HashMap<String, Object> map10 = new HashMap<>();
                            map10.put("name","发回重审");
                            map10.put("value",kss_fhcs);
                            list.add(map10);
                        }
                        if(sxfh!=null&&!"".equals(sxfh.toString())){
                            kss_sxfh=sxfh.toString();
                            HashMap<String, Object> map11 = new HashMap<>();
                            map11.put("name","死刑复核");
                            map11.put("value",kss_sxfh);
                            list.add(map11);
                        }
                        if(dzx!=null&&!"".equals(dzx.toString())){
                            kss_dzx=dzx.toString();
                            HashMap<String, Object> map12 = new HashMap<>();
                            map12.put("name","待执行");
                            map12.put("value",kss_dzx);
                            list.add(map12);
                        }
                        if(yj!=null&&!"".equals(yj.toString())){
                            kss_yj=yj.toString();
                            HashMap<String, Object> map13 = new HashMap<>();
                            map13.put("name","已决");
                            map13.put("value",kss_yj);
                            list.add(map13);
                        }
                        if(qt!=null&&!"".equals(qt.toString())){
                            kss_qt=qt.toString();
                            HashMap<String, Object> map14 = new HashMap<>();
                            map14.put("name","其它");
                            map14.put("value",kss_qt);
                            list.add(map14);
                        }
                        if(ysssq!=null&&!"".equals(ysssq.toString())){
                            kss_ysssq=ysssq.toString();
                            HashMap<String, Object> map15 = new HashMap<>();
                            map15.put("name","一审上诉期");
                            map15.put("value",kss_ysssq);
                            list.add(map15);
                        }
                        if(zs!=null&&!"".equals(zs.toString())){
                            kss_zs=zs.toString();
                            HashMap<String, Object> map16 = new HashMap<>();
                            map16.put("name","再审");
                            map16.put("value",kss_zs);
                            list.add(map16);
                        }


                    }
                }


            }else {
                HashMap<String, Object> map = new HashMap<>();
                map.put("name","暂无全省办案阶段分析数据");
                map.put("value",0);
                list.add(map);
            }
            return  ResponseMessage.ok(list);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return ResponseMessage.error("查询失败");
        }

    }


    @GetMapping("/select_kss_ndrs")
    @ApiOperation("kss年度入所量比对")
    @OpenAPI
    public ResponseMessage<Map<String, Object>> select_kss_ndrs() {
        int[] jsbh = new int[]{1100001, 110101111, 110102111, 110105111, 110106111, 110107111, 110108111, 110109111, 11011111, 110112111, 110113111, 110221111, 110224111, 110226111, 110227111, 110228111, 110229111};

        int[] jnArray = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};//今年
        int[] qnArray = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};//去年

        try {
            Map<String, Object> map = new HashMap<String, Object>();
            List<Map<String, Object>> kss_ndrs = kssDpFxService.find_kss_ndrs();
            if(kss_ndrs.size()>0){
                for(Map<String, Object> key:kss_ndrs){
                    String kss_jn="0";String kss_qn="0";String kss_jsbh="";
                    if(key!=null){
                        Object jn = key.get("jn");
                        Object qn = key.get("qn");
                        Object jsbh1 = key.get("jsbh");
                        if(jn!=null && !"".equals(jn.toString())){
                            kss_jn=jn.toString();
                        }
                        if(qn!=null && !"".equals(qn.toString())){
                            kss_qn=qn.toString();
                        }
                        if(jsbh1!=null && !"".equals(jsbh1.toString())){
                            kss_jsbh=jsbh1.toString();
                            for(int i =0;i<jsbh.length;i++) {
                                if (kss_jsbh.contains(String.valueOf(jsbh[i]))){
                                    jnArray[i]=jnArray[i]+Integer.parseInt(kss_jn);
                                    qnArray[i]=qnArray[i]+Integer.parseInt(kss_qn);
                                }
                                    continue;

                            }
                        }

                    }
                }
                map.put("jnArray", jnArray);map.put("qnArray", qnArray);
                return ResponseMessage.ok(map);

            }else{
                map.put("jnArray", 0);map.put("qnArray", 0);
                return ResponseMessage.ok(map);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return ResponseMessage.error("查询失败");
        }
    }



    @GetMapping("/select_kss_fxdj")
    @ApiOperation("kss风险情况分析=人员风险等级分析")
    @OpenAPI
    public ResponseMessage<Map<String, Object>> select_kss_fxdj() {
        int[] jsbh = new int[]{1100001, 110101111, 110102111, 110105111, 110106111, 110107111, 110108111, 110109111, 11011111, 110112111, 110113111, 110221111, 110224111, 110226111, 110227111, 110228111, 110229111};

        int[] yjArray = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};//一级
        int[] ejArray = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};//二级
        int[] sjArray = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};//三级
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            List<Map<String, Object>> kss_fxdj = kssDpFxService.find_kss_fxdj();
            if(kss_fxdj.size()>0){
                for(Map<String, Object> key:kss_fxdj){
                    String YJ ="0";String EJ ="0";String SJ ="0";String JSBH="";

                    if(key!=null){
                        Object yj = key.get("yj");
                        Object ej = key.get("ej");
                        Object sj = key.get("sj");
                        Object jsbh1 = key.get("jsbh");
                        if(yj!=null &&!"".equals(yj.toString())){
                            YJ=yj.toString();
                        }
                        if(ej!=null &&!"".equals(ej.toString())){
                            EJ=ej.toString();
                        }
                        if(sj!=null &&!"".equals(sj.toString())){
                            SJ=sj.toString();
                        }
                        if(jsbh1!=null &&!"".equals(jsbh1.toString())){
                            JSBH=jsbh1.toString();
                            for(int i =0;i<jsbh.length;i++){
                                if(JSBH.contains(String.valueOf(jsbh[i]))){
                                    yjArray[i]=yjArray[i]+Integer.parseInt(YJ);
                                    ejArray[i]=ejArray[i]+Integer.parseInt(EJ);
                                    sjArray[i]=sjArray[i]+Integer.parseInt(SJ);
                                }continue;
                            }

                        }
                    }
                }
                map.put("yjArray", yjArray);
                map.put("ejArray", ejArray);
                map.put("sjArray", sjArray);
                return ResponseMessage.ok(map);

            }else {
                map.put("yjArray", 0);
                map.put("ejArray", 0);
                map.put("sjArray", 0);
                return ResponseMessage.ok(map);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return ResponseMessage.error("查询失败");
        }


    }



    @GetMapping("/select_kss_xdry")
    @ApiOperation("吸毒人员分析 吸毒人数、百分比")
    @OpenAPI
    public ResponseMessage<Map<String, Object>> select_kss_xdry() {
        int[] jsbh = new int[]{1100001, 110101111, 110102111, 110105111, 110106111, 110107111, 110108111, 110109111, 11011111, 110112111, 110113111, 110221111, 110224111, 110226111, 110227111, 110228111, 110229111};

        int[] xdry = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};//吸毒人员
        String[] bfb = new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"};//百分比
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();


        try {
            List<Map<String, Object>> kss_xdry = kssDpFxService.find_kss_xdry();
            if(!StringUtils.isNullOrEmpty(kss_xdry)){
                for(Map<String, Object> key :kss_xdry){
                    for(int i =0;i<jsbh.length;i++){
                        if(key.get("jsbh").toString().contains(String.valueOf(jsbh[i]))){
                            xdry[i]=Integer.parseInt(key.get("ay").toString());
                        }
                    }
                }
            }

            Map<String, Object> kss_xdry_all = kssDpFxService.find_kss_xdry_all();//百分比=总人数
            if(!StringUtils.isNullOrEmpty(kss_xdry_all)){
                for(Object key:kss_xdry_all.keySet()){

                    if(!"0".equals(kss_xdry_all.get("ay").toString())){
                        for(int i =0;i<jsbh.length;i++) {
                            int ay = Integer.parseInt(kss_xdry_all.get("ay").toString());

                            String format = String.format("%.2f", (float) Integer.parseInt(xdry[i] + "") / (float) Integer.parseInt(ay + "") * 100);
                            bfb[i]=format;
                        }
                    }
                }
            }
            map.put("xdry",xdry);
            map.put("bfb",bfb);
            return  ResponseMessage.ok(map);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return  ResponseMessage.error("服务器繁，请重新刷新页面");
        }
    }







	@GetMapping("/zyrygyl")
	@ApiOperation("在押人员关押量分析")
	@OpenAPI
	public ResponseMessage<Map<String, Object>> zyrygyl() {
		int[] jsbh = new int[]{1100001, 110101111, 110102111, 110105111, 110106111, 110107111, 110108111, 110109111, 11011111, 110112111, 110113111, 110221111, 110224111, 110226111, 110227111, 110228111, 110229111};
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> lslsfxCountlist = kssDpFxService.zyrygyl();
			int[] man = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			int[] woman = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			int[] unknow = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			int[] zs = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			if (lslsfxCountlist.size() > 0 && !lslsfxCountlist.isEmpty()) {
				for (Map<String, Object> maps : lslsfxCountlist) {
					for (int i = 0; i < jsbh.length; i++) {
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							man[i] = man[i] + Integer.parseInt(maps.get("man").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							woman[i] = woman[i] + Integer.parseInt(maps.get("woman").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							unknow[i] = unknow[i] + Integer.parseInt(maps.get("unknow").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							zs[i] = zs[i] + Integer.parseInt(maps.get("zs").toString());
						}
					}
				}
				map.put("man", man);
				map.put("woman", woman);
				map.put("unknow", unknow);
				map.put("zs", zs);
				return ResponseMessage.ok(map);
			} else {
				map.put("man", 0);
				map.put("woman", 0);
				map.put("unknow", 0);
				map.put("zs", 0);
				return ResponseMessage.ok(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败");
		}
	}


	@GetMapping("/hjdfx")
	@ApiOperation("户籍地分析")
	@OpenAPI
	public ResponseMessage<Map<String, Object>> hjdfx() {
		int[] jsbh = new int[]{1100001, 110101111, 110102111, 110105111, 110106111, 110107111, 110108111, 110109111, 11011111, 110112111, 110113111, 110221111, 110224111, 110226111, 110227111, 110228111, 110229111};
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> lslsfxCountlist = kssDpFxService.hjdfx();
			int[] bs = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			int[] ws = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			int[] wg = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			if (lslsfxCountlist.size() > 0 && !lslsfxCountlist.isEmpty()) {
				for (Map<String, Object> maps : lslsfxCountlist) {
					for (int i = 0; i < jsbh.length; i++) {
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							bs[i] = bs[i] + Integer.parseInt(maps.get("bs").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							ws[i] = ws[i] + Integer.parseInt(maps.get("ws").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							wg[i] = wg[i] + Integer.parseInt(maps.get("wg").toString());
						}
					}
				}
				map.put("bs", bs);
				map.put("ws", ws);
				map.put("wg", wg);
				return ResponseMessage.ok(map);
			} else {
				map.put("bs", 0);
				map.put("ws", 0);
				map.put("wg", 0);
				return ResponseMessage.ok(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败");
		}
	}


	@GetMapping("/nlfx")
	@ApiOperation("年龄分析")
	@OpenAPI
	public ResponseMessage<List<Map<String, Object>>> nlfx() {
		//定义地区  与前台顺序对应
		int[] jsbh = new int[]{1100001, 110101111, 110102111, 110105111, 110106111, 110107111, 110108111, 110109111, 11011111, 110112111, 110113111, 110221111, 110224111, 110226111, 110227111, 110228111, 110229111};
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			//<!--按照年龄分 -->
			List<Map<String, Object>> gyqxfxCountlist = kssDpFxService.nlfx();
			for (int i = 0; i < jsbh.length; i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				int[] s = new int[]{0, 0, 0, 0};
				for (Map<String, Object> maps : gyqxfxCountlist) {
					if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
						s[0] = s[0] + Integer.parseInt(maps.get("sn").toString());
						s[1] = s[1] + Integer.parseInt(maps.get("qn").toString());
						s[2] = s[2] + Integer.parseInt(maps.get("zn").toString());
						s[3] = s[3] + Integer.parseInt(maps.get("zln").toString());
					}
				}
				map.put("data", s);
				list.add(map);
			}
			return ResponseMessage.ok(list);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败");
		}
	}

	@GetMapping("/whcd")
	@ApiOperation("文化程度分析")
	@OpenAPI
	public ResponseMessage<Map<String, Object>> whcd() {
		int[] jsbh = new int[]{1100001, 110101111, 110102111, 110105111, 110106111, 110107111, 110108111, 110109111, 11011111, 110112111, 110113111, 110221111, 110224111, 110226111, 110227111, 110228111, 110229111};
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> lslsfxCountlist = kssDpFxService.whcd();
			int[] wm = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			int[] xx = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			int[] zx = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			int[] dzys = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			if (lslsfxCountlist.size() > 0 && !lslsfxCountlist.isEmpty()) {
				for (Map<String, Object> maps : lslsfxCountlist) {
					for (int i = 0; i < jsbh.length; i++) {
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							wm[i] = wm[i] + Integer.parseInt(maps.get("wm").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							xx[i] = xx[i] + Integer.parseInt(maps.get("xx").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							zx[i] = zx[i] + Integer.parseInt(maps.get("zx").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							dzys[i] = dzys[i] + Integer.parseInt(maps.get("dzys").toString());
						}
					}
				}
				map.put("wm", wm);
				map.put("xx", xx);
				map.put("zx", zx);
				map.put("dzys", dzys);
				return ResponseMessage.ok(map);
			} else {
				map.put("wm", 0);
				map.put("xx", 0);
				map.put("zx", 0);
				map.put("dzys", 0);
				return ResponseMessage.ok(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败");
		}
	}

	@GetMapping("/sffx")
	@ApiOperation("身份分析")
	@OpenAPI
	public ResponseMessage<Map<String, Object>> sffx() {
		int[] jsbh = new int[]{1100001, 110101111, 110102111, 110105111, 110106111, 110107111, 110108111, 110109111, 11011111, 110112111, 110113111, 110221111, 110224111, 110226111, 110227111, 110228111, 110229111};
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> lslsfxCountlist = kssDpFxService.sffx();
			int[] gjgwy = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			int[] qyglry = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			int[] gr = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			int[] nm = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			int[] zxxs = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			int[] gtgsry = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			int[] ltxry = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			int[] wyry = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			int[] jr = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			int[] qt = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			if (lslsfxCountlist.size() > 0 && !lslsfxCountlist.isEmpty()) {
				for (Map<String, Object> maps : lslsfxCountlist) {
					for (int i = 0; i < jsbh.length; i++) {
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							gjgwy[i] = gjgwy[i] + Integer.parseInt(maps.get("gjgwy").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							qyglry[i] = qyglry[i] + Integer.parseInt(maps.get("qyglry").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							gr[i] = gr[i] + Integer.parseInt(maps.get("gr").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							nm[i] = nm[i] + Integer.parseInt(maps.get("nm").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							zxxs[i] = zxxs[i] + Integer.parseInt(maps.get("zxxs").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							gtgsry[i] = gtgsry[i] + Integer.parseInt(maps.get("gtgsry").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							ltxry[i] = ltxry[i] + Integer.parseInt(maps.get("ltxry").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							wyry[i] = wyry[i] + Integer.parseInt(maps.get("wyry").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							jr[i] = jr[i] + Integer.parseInt(maps.get("jr").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							qt[i] = qt[i] + Integer.parseInt(maps.get("qt").toString());
						}
					}
				}
				map.put("gjgwy", gjgwy);
				map.put("qyglry", qyglry);
				map.put("gr", gr);
				map.put("nm", nm);
				map.put("zxxs", zxxs);
				map.put("gtgsry", gtgsry);
				map.put("ltxry", ltxry);
				map.put("wyry", wyry);
				map.put("jr", jr);
				map.put("qt", qt);
				return ResponseMessage.ok(map);
			} else {
				map.put("gjgwy", 0);
				map.put("qyglry", 0);
				map.put("gr", 0);
				map.put("nm", 0);
				map.put("zxxs", 0);
				map.put("gtgsry", 0);
				map.put("ltxry", 0);
				map.put("wyry", 0);
				map.put("jr", 0);
				map.put("qt", 0);
				return ResponseMessage.ok(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败");
		}
	}

	@GetMapping("/jkqkfx")
	@ApiOperation("健康情况分析")
	@OpenAPI
	public ResponseMessage<Map<String, Object>> jkqkfx() {
		int[] jsbh = new int[]{1100001, 110101111, 110102111, 110105111, 110106111, 110107111, 110108111, 110109111, 11011111, 110112111, 110113111, 110221111, 110224111, 110226111, 110227111, 110228111, 110229111};
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> lslsfxCountlist = kssDpFxService.jkqkfx();
			int[] jkhlh = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			int[] ybhjr = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			int[] yb = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			int[] slqx = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			int[] cf = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			if (lslsfxCountlist.size() > 0 && !lslsfxCountlist.isEmpty()) {
				for (Map<String, Object> maps : lslsfxCountlist) {
					for (int i = 0; i < jsbh.length; i++) {
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							jkhlh[i] = jkhlh[i] + Integer.parseInt(maps.get("jkhlh").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							ybhjr[i] = ybhjr[i] + Integer.parseInt(maps.get("ybhjr").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							yb[i] = yb[i] + Integer.parseInt(maps.get("yb").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							slqx[i] = slqx[i] + Integer.parseInt(maps.get("slqx").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							cf[i] = cf[i] + Integer.parseInt(maps.get("cf").toString());
						}
					}
				}
				map.put("jkhlh", jkhlh);
				map.put("ybhjr", ybhjr);
				map.put("yb", yb);
				map.put("slqx", slqx);
				map.put("cf", cf);
				return ResponseMessage.ok(map);
			} else {
				map.put("jkhlh", 0);
				map.put("ybhjr", 0);
				map.put("yb", 0);
				map.put("slqx", 0);
				map.put("cf", 0);
				return ResponseMessage.ok(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败");
		}
	}

	@GetMapping("/jsryfx")
	@ApiOperation("拒收人员分析")
	@OpenAPI
	public ResponseMessage<Map<String, Object>> jsryfx() {
		int[] jsbh = new int[]{1100001, 110101111, 110102111, 110105111, 110106111, 110107111, 110108111, 110109111, 11011111, 110112111, 110113111, 110221111, 110224111, 110226111, 110227111, 110228111, 110229111};
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> lslsfxCountlist = kssDpFxService.jsryfx();
			int[] js = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			Double[] bfb = new Double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
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
        Map<String, Object> map14 = new HashMap<String, Object>();
        Map<String, Object> map15 = new HashMap<String, Object>();
        Map<String, Object> map16 = new HashMap<String, Object>();
        List list1=new ArrayList();
		try {
			List<Map<String, Object>> list = kssDpFxService.rsxz(jsbh2);
			for (int i=0;i<list.size();i++){
                String xsjl = list.get(i).get("xsjl").toString();
                String db = list.get(i).get("db").toString();
                String lsjy = list.get(i).get("lsjy").toString();
                String wsszr = list.get(i).get("wsszr").toString();
                String bsszr = list.get(i).get("bsszr").toString();
                String ydjy = list.get(i).get("ydjy").toString();
                String qbhszdb = list.get(i).get("qbhszdb").toString();
                String jsjbzdb = list.get(i).get("jsjbzdb").toString();
                String qxjwzx = list.get(i).get("qxjwzx").toString();
                String cxbwjy = list.get(i).get("cxbwjy").toString();
                String qxjs = list.get(i).get("qxjs").toString();
                String cxhx = list.get(i).get("cxhx").toString();
                String tpsh = list.get(i).get("tpsh").toString();
                String qtsh = list.get(i).get("qtsh").toString();
                String tsws = list.get(i).get("tsws").toString();
                String qt = list.get(i).get("qt").toString();
                map1.put("name","刑事拘留");
                map1.put("value",xsjl);
                map2.put("name","逮捕");
                map2.put("value",db);
                map3.put("name","临时寄押");
                map3.put("value",lsjy);
                map4.put("name","外省市转入");
                map4.put("value",wsszr);
                map5.put("name","本省市转入");
                map5.put("value",bsszr);
                map6.put("name","异地羁押");
                map6.put("value",ydjy);
                map7.put("name","取保候审转逮捕");
                map7.put("value",qbhszdb);
                map8.put("name","监视居住转逮捕");
                map8.put("value",jsjbzdb);
                map9.put("name","取消监外执行");
                map9.put("value",qxjwzx);
                map10.put("name","撤销保外就医");
                map10.put("value",cxbwjy);
                map11.put("name","取消假释");
                map11.put("value",qxjs);
                map12.put("name","撤销缓刑");
                map12.put("value",cxhx);
                map13.put("name","逃跑收回");
                map13.put("value",tpsh);
                map14.put("name","其他收回");
                map14.put("value",qtsh);
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
            list1.add(map14);
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
        List list1=new ArrayList();
        try {
            List<Map<String, Object>> list = kssDpFxService.ajlb(jsbh2);
            for (int i=0;i<list.size();i++){
                String wfgjaq = list.get(i).get("wfgjaq").toString();
                String jrwfzr = list.get(i).get("jrwfzr").toString();
                String whggaq = list.get(i).get("whggaq").toString();
                String whgfly = list.get(i).get("whgfly").toString();
                String twhl = list.get(i).get("twhl").toString();
                String qfgmql = list.get(i).get("qfgmql").toString();
                String mzql = list.get(i).get("mzql").toString();
                String qfcc = list.get(i).get("qfcc").toString();
                String fhshgl = list.get(i).get("fhshgl").toString();
                String phshzyjj = list.get(i).get("phshzyjj").toString();
                String dz = list.get(i).get("dz").toString();

                map1.put("name","危害国家安全案");
                map1.put("value",wfgjaq);
                map2.put("name","军人违反职责案");
                map2.put("value",jrwfzr);
                map3.put("name","危害公共安全案");
                map3.put("value",whggaq);
                map4.put("name","危害国防利益案");
                map4.put("value",whgfly);
                map5.put("name","贪污贿赂案");
                map5.put("value",twhl);
                map6.put("name","侵犯公民人身权利");
                map6.put("value",qfgmql);
                map7.put("name","民主权利案");
                map7.put("value",mzql);
                map8.put("name","侵犯财产案");
                map8.put("value",qfcc);
                map9.put("name","妨碍社会管理秩序案");
                map9.put("value",fhshgl);
                map10.put("name","破坏社会主义市场经济秩序案");
                map10.put("value",phshzyjj);
                map11.put("name","渎职罪");
                map11.put("value",dz);
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
		int[] jsbh = new int[]{1100001, 110101111, 110102111, 110105111, 110106111, 110107111, 110108111, 110109111, 11011111, 110112111, 110113111, 110221111, 110224111, 110226111, 110227111, 110228111, 110229111};
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> lslsfxCountlist = kssDpFxService.zccs();
			int[] jn = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			int[] qn = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
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
		int[] jsbh = new int[]{1100001, 110101111, 110102111, 110105111, 110106111, 110107111, 110108111, 110109111, 11011111, 110112111, 110113111, 110221111, 110224111, 110226111, 110227111, 110228111, 110229111};
		Map<String, Object> map = new HashMap<String, Object>();

		List<Map<String, Object>> lslsfxCountlist = kssDpFxService.lscsfx();
		int[] ktsl = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		int[] gjfba = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		int[] tq = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		int[] kb = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		int[] zy = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		int[] ld = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		int[] qt = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		int[] swjy = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		int[] qjcs = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

		System.out.println("============" + lslsfxCountlist.size());
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
            List<Map<String, Object>> list = kssDpFxService.csyyfx(jsbh2);
            for (int i=0;i<list.size();i++){
                String tfjy = list.get(i).get("tfjy").toString();
                String sf = list.get(i).get("sf").toString();
                String qbhs = list.get(i).get("qbhs").toString();
                String jsjz = list.get(i).get("jsjz").toString();
                String zyjwzx = list.get(i).get("zyjwzx").toString();
                String bwjy = list.get(i).get("bwjy").toString();
                String js = list.get(i).get("js").toString();
                String tp = list.get(i).get("tp").toString();
                String sw = list.get(i).get("sw").toString();
                String zqts = list.get(i).get("zqts").toString();
                String zxsx = list.get(i).get("zxsx").toString();
                String qt = list.get(i).get("qt").toString();
                map1.put("name","投送监狱");
                map1.put("value",tfjy);
                map2.put("name","释放");
                map2.put("value",sf);
                map3.put("name","取保候审");
                map3.put("value",qbhs);
                map4.put("name","监视居住");
                map4.put("value",jsjz);
                map5.put("name","暂予监外执行");
                map5.put("value",zyjwzx);
                map6.put("name","保外就医");
                map6.put("value",bwjy);
                map7.put("name","假释");
                map7.put("value",js);
                map8.put("name","逃跑");
                map8.put("value",tp);
                map9.put("name","死亡");
                map9.put("value",sw);
                map10.put("name","转其他所");
                map10.put("value",zqts);
                map11.put("name","执行死刑");
                map11.put("value",zxsx);
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
		int[] jsbh = new int[]{1100001, 110101111, 110102111, 110105111, 110106111, 110107111, 110108111, 110109111, 11011111, 110112111, 110113111, 110221111, 110224111, 110226111, 110227111, 110228111, 110229111};
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> lslsfxCountlist = kssDpFxService.swfz();
			int[] tbjd = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			int[] jj = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			int[] zs = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
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
		int[] jsbh = new int[]{1100001, 110101111, 110102111, 110105111, 110106111, 110107111, 110108111, 110109111, 11011111, 110112111, 110113111, 110221111, 110224111, 110226111, 110227111, 110228111, 110229111};
		Map<String, Object> map = new HashMap<String, Object>();

		List<Map<String, Object>> lslsfxCountlist = kssDpFxService.yzblfz();
		int[] gysh = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		int[] fmdp = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		int[] qj = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		int[] gysr = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		int[] qiangjie = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		int[] fh = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		int[] bz = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		int[] td = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

		System.out.println("============" + lslsfxCountlist.size());
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
							qj[i] = qj[i] + Integer.parseInt(maps.get("qj").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							gysr[i] = gysr[i] + Integer.parseInt(maps.get("gysr").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							qiangjie[i] = qiangjie[i] + Integer.parseInt(maps.get("qiangjie").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							fh[i] = fh[i] + Integer.parseInt(maps.get("fh").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							bz[i] = bz[i] + Integer.parseInt(maps.get("bz").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							td[i] = td[i] + Integer.parseInt(maps.get("td").toString());
						}
					}
				}
				map.put("gysh", gysh);
				map.put("fmdp", fmdp);
				map.put("qj", qj);
				map.put("gysr", gysr);
				map.put("qiangjie", qiangjie);
				map.put("fh", fh);
				map.put("bz", bz);
				map.put("td", td);
				return ResponseMessage.ok(map);
			}else {
				map.put("gysh", 0);
				map.put("fmdp", 0);
				map.put("qj", 0);
				map.put("gysr", 0);
				map.put("qiangjie", 0);
				map.put("fh", 0);
				map.put("bz", 0);
				map.put("td", 0);
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
            List<Map<String, Object>> list = kssDpFxService.shsefx(jsbh2);
            for (int i=0;i<list.size();i++){
                String hsh = list.get(i).get("hsh").toString();
                String xxzs = list.get(i).get("xxzs").toString();
                String ksdc = list.get(i).get("ksdc").toString();
                String jzdo = list.get(i).get("jzdo").toString();
                String qzls = list.get(i).get("qzls").toString();
                String ffjj = list.get(i).get("ffjj").toString();
                String zzmy = list.get(i).get("zzmy").toString();
                String qpjy = list.get(i).get("qpjy").toString();
                String qpmy = list.get(i).get("qpmy").toString();
                String gyshcw = list.get(i).get("gyshcw").toString();
                map1.put("name","组织、领导、参加黑社会性质组织");
                map1.put("value",hsh);
                map2.put("name","寻衅滋事");
                map2.put("value",xxzs);
                map3.put("name","开设赌场");
                map3.put("value",ksdc);
                map4.put("name","聚众斗殴");
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
	@GetMapping("/mjsl")
	@ApiOperation("民警数量")
	@OpenAPI
	public ResponseMessage<Map<String, Object>> mjsl() {
		int[] jsbh = new int[]{1100001, 110101111, 110102111, 110105111, 110106111, 110107111, 110108111, 110109111, 11011111, 110112111, 110113111, 110221111, 110224111, 110226111, 110227111, 110228111, 110229111};
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> lslsfxCountlist = kssDpFxService.mjsl();
			int[] sl = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			if (lslsfxCountlist.size() > 0 && !lslsfxCountlist.isEmpty()) {
				for (Map<String, Object> maps : lslsfxCountlist) {
					for (int i = 0; i < jsbh.length; i++) {
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							sl[i] = sl[i] + Integer.parseInt(maps.get("sl").toString());
						}
					}
				}
				map.put("sl", sl);
				return ResponseMessage.ok(map);
			} else {
				map.put("sl", 0);
				return ResponseMessage.ok(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败");
		}
	}
	@GetMapping("/mjxbfx")
	@ApiOperation("民警性别分析")
	@OpenAPI
	public ResponseMessage<Map<String, Object>> mjxbfx() {
		int[] jsbh = new int[]{1100001, 110101111, 110102111, 110105111, 110106111, 110107111, 110108111, 110109111, 11011111, 110112111, 110113111, 110221111, 110224111, 110226111, 110227111, 110228111, 110229111};
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> lslsfxCountlist = kssDpFxService.mjxbfx();
			int[] man = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			int[] woman = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			int[] zs = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			if (lslsfxCountlist.size() > 0 && !lslsfxCountlist.isEmpty()) {
				for (Map<String, Object> maps : lslsfxCountlist) {
					for (int i = 0; i < jsbh.length; i++) {
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							man[i] = man[i] + Integer.parseInt(maps.get("man").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							woman[i] = woman[i] + Integer.parseInt(maps.get("woman").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							zs[i] = zs[i] + Integer.parseInt(maps.get("zs").toString());
						}
					}
				}
				map.put("man", man);
				map.put("woman", woman);
				map.put("zs", zs);
				return ResponseMessage.ok(map);
			} else {
				map.put("man", 0);
				map.put("woman", 0);
				map.put("zs", 0);
				return ResponseMessage.ok(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败");
		}
	}
	@GetMapping("/mjjxfx")
	@ApiOperation("民警警衔分析")
	@OpenAPI
    public List mjjxfx(@RequestParam(value = "jsbh",required =false )String jsbh) {
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
        Map<String, Object> map14 = new HashMap<String, Object>();
        List list1=new ArrayList();
        try {
            List<Map<String, Object>> list = kssDpFxService.mjjxfx(jsbh);
            for (int i=0;i<list.size();i++){
                String zjj = list.get(i).get("zjj").toString();
                String fzjj = list.get(i).get("fzjj").toString();
                String yjjj = list.get(i).get("yjjj").toString();
                String ejjj = list.get(i).get("ejjj").toString();
                String sjjj = list.get(i).get("sjjj").toString();
                String yjjd = list.get(i).get("yjjd").toString();
                String ejjd = list.get(i).get("ejjd").toString();
                String sjjd = list.get(i).get("sjjd").toString();
                String yjjs = list.get(i).get("yjjs").toString();
                String ejjs = list.get(i).get("ejjs").toString();
                String sjjs = list.get(i).get("sjjs").toString();
                String yjjy = list.get(i).get("yjjy").toString();
                String ejjy = list.get(i).get("ejjy").toString();
                String jxjy = list.get(i).get("jxjy").toString();

                map1.put("name","总警监");
                map1.put("value",zjj);
                map2.put("name","副总警监");
                map2.put("value",fzjj);
                map3.put("name","一级警监");
                map3.put("value",yjjj);
                map4.put("name","二级警监");
                map4.put("value",ejjj);
                map5.put("name","三级警监");
                map5.put("value",sjjj);
                map6.put("name","一级警督");
                map6.put("value",yjjd);
                map7.put("name","二级警督");
                map7.put("value",ejjd);
                map8.put("name","三级警督");
                map8.put("value",sjjd);
                map9.put("name","一级警司");
                map9.put("value",yjjs);
                map10.put("name","二级警司");
                map10.put("value",ejjs);
                map11.put("name","三级警司");
                map11.put("value",sjjs);
                map12.put("name","一级警员");
                map12.put("value",yjjy);
                map13.put("name","二级警员");
                map13.put("value",ejjy);
                map14.put("name","见习警员");
                map14.put("value",jxjy);
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
            list1.add(map14);
            return list1;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	@GetMapping("/zzscfx")
	@ApiOperation("在职时长分析")
	@OpenAPI
	public ResponseMessage<List<Map<String, Object>>> zzscfx() {

        //定义地区  与前台顺序对应
        int[] jsbh = new int[]{1100001, 110101111, 110102111, 110105111, 110106111, 110107111, 110108111, 110109111, 11011111, 110112111, 110113111, 110221111, 110224111, 110226111, 110227111, 110228111, 110229111};
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            //<!--按照年龄分 -->
            List<Map<String, Object>> gyqxfxCountlist = kssDpFxService.zzscfx();
            for (int i = 0; i < jsbh.length; i++) {
                Map<String, Object> map = new HashMap<String, Object>();
                int[] s = new int[]{0, 0, 0, 0,0};
                for (Map<String, Object> maps : gyqxfxCountlist) {
                    if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
                        s[0] = s[0] + Integer.parseInt(maps.get("sgyyx").toString());
                        s[1] = s[1] + Integer.parseInt(maps.get("sdlgy").toString());
                        s[2] = s[2] + Integer.parseInt(maps.get("ldyn").toString());
                        s[3] = s[3] + Integer.parseInt(maps.get("ydsn").toString());
                        s[4] = s[4] + Integer.parseInt(maps.get("snys").toString());
                    }
                }
                map.put("data", s);
                list.add(map);
            }
            return ResponseMessage.ok(list);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("查询失败");
        }
	}
	@GetMapping("/yzjbfx")
	@ApiOperation("严重疾病分析")
	@OpenAPI
	public ResponseMessage<Map<String, Object>> yzjbfx() {
		int[] jsbh = new int[]{1100001, 110101111, 110102111, 110105111, 110106111, 110107111, 110108111, 110109111, 11011111, 110112111, 110113111, 110221111, 110224111, 110226111, 110227111, 110228111, 110229111};
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> lslsfxCountlist = kssDpFxService.yzjbfx();
			int[] zdbh = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			int[] azb = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			int[] jsyc = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			if (lslsfxCountlist.size() > 0 && !lslsfxCountlist.isEmpty()) {
				for (Map<String, Object> maps : lslsfxCountlist) {
					for (int i = 0; i < jsbh.length; i++) {
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							zdbh[i] = zdbh[i] + Integer.parseInt(maps.get("zdbh").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							azb[i] = azb[i] + Integer.parseInt(maps.get("azb").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							jsyc[i] = jsyc[i] + Integer.parseInt(maps.get("jsyc").toString());
						}
					}
				}
				map.put("zdbh", zdbh);
				map.put("azb", azb);
				map.put("jsyc", jsyc);
				return ResponseMessage.ok(map);
			} else {
				map.put("zdbh", 0);
				map.put("azb", 0);
				map.put("jsyc", 0);
				return ResponseMessage.ok(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败");
		}
	}
    @GetMapping("/mjwhcd")
    @ApiOperation("民警文化程度分析")
    @OpenAPI
    public ResponseMessage<Map<String, Object>> mjwhcd() {
        int[] jsbh = new int[]{1100001, 110101111, 110102111, 110105111, 110106111, 110107111, 110108111, 110109111, 11011111, 110112111, 110113111, 110221111, 110224111, 110226111, 110227111, 110228111, 110229111};
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<Map<String, Object>> lslsfxCountlist = kssDpFxService.mjwhcd();
            int[] wm = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            int[] xx = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            int[] zx = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            int[] dzys = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            if (lslsfxCountlist.size() > 0 && !lslsfxCountlist.isEmpty()) {
                for (Map<String, Object> maps : lslsfxCountlist) {
                    for (int i = 0; i < jsbh.length; i++) {
                        if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
                            wm[i] = wm[i] + Integer.parseInt(maps.get("wm").toString());
                        }
                        if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
                            xx[i] = xx[i] + Integer.parseInt(maps.get("xx").toString());
                        }
                        if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
                            zx[i] = zx[i] + Integer.parseInt(maps.get("zx").toString());
                        }
                        if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
                            dzys[i] = dzys[i] + Integer.parseInt(maps.get("dzys").toString());
                        }
                    }
                }
                map.put("wm", wm);
                map.put("xx", xx);
                map.put("zx", zx);
                map.put("dzys", dzys);
                return ResponseMessage.ok(map);
            } else {
                map.put("wm", 0);
                map.put("xx", 0);
                map.put("zx", 0);
                map.put("dzys", 0);
                return ResponseMessage.ok(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("查询失败");
        }
    }
}



