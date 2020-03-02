package awd.cloud.platform.servers.analyse.controller.kss;

import awd.cloud.platform.servers.analyse.service.kss.*;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.netflix.discovery.converters.Auto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.omg.CORBA.ObjectHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("kss/lsp")
@Api(tags = "kss_lsp",description = "流水牌")
public class LspController {

    @Autowired
    private Kss_JbxxService jbxxService;

    @Autowired
    private Kss_ZdryService zdryService;

    @Autowired
    private Kss_TsdjService tsdjService;

    @Autowired
    private Kss_JshjService jshjService;

    @Autowired
    private Kss_LshjService lshjService;

    @Autowired
    private Kss_XjService xjService;

    @Autowired
    private Kss_JbqkService jbqkService;

    @Autowired
    private Kss_JyService jyService;

    @Autowired
    private Kss_BjjlService bjjlService;

    @Autowired
    private Kss_LscsService lscsService;
    @Autowired
    private Kss_ThjyService thjyService;
    @Autowired
    private Kss_WgsjclService wgsjclService;
    @GetMapping("/queryLsp")
    @ApiOperation("加载流水牌数据")
    @OpenAPI
    public Map<String,Object> queryLsp (@RequestParam(value = "jsbh",required =false )String jsbh){

        Map<String , Object> result = Maps.newHashMap();
//        Map<String , Object> rstj = Maps.newHashMap();//人数统计
//        Map<String , Object> sshj = Maps.newHashMap();//诉讼环节
//        Map<String , Object> sszt = Maps.newHashMap();//实时状态
//        Map<String , Object> zdry = Maps.newHashMap();//重点人员
        //人数统计
        result.put("wjs",(String.valueOf(jbxxService.getJbxxCountByField(jsbh,"zszt","11"))!=null?jbxxService.getJbxxCountByField(jsbh,"zszt","11"):"0"));//未决
        result.put("yjs",jbxxService.getJbxxCountByField(jsbh,"zszt","12"));//已决待送
        result.put("lsfx",jbxxService.getJbxxCountByField(jsbh,"zszt","13"));//留所服刑
        result.put("zs",jbxxService.getJbxxR8Count(jsbh));//总数
        result.put("lsjy",jbxxService.getJbxxCountByField(jsbh,"rsxz","13"));//临时寄押

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());//获取今天日期
        result.put("jds",jbxxService.queryZyzrs(date,date,jsbh).get(0).get("zyzrs"));//新收入所

        //诉讼环节
        result.put("xsjl",jbxxService.getJbxxCountByField(jsbh,"bahj","11"));//刑事拘留
        result.put("db",jbxxService.getJbxxCountByField(jsbh,"bahj","12"));//逮捕
        result.put("gabczc1",jbxxService.getJbxxCountByField(jsbh,"bahj","13"));//公安补充侦查1
        result.put("gabczc2",jbxxService.getJbxxCountByField(jsbh,"bahj","14"));//公安补充侦查2
        result.put("scqs1",jbxxService.getJbxxCountByField(jsbh,"bahj","21"));//审查起诉1
        result.put("scqs2",jbxxService.getJbxxCountByField(jsbh,"bahj","22"));//审查起诉2
        result.put("scqs3",jbxxService.getJbxxCountByField(jsbh,"bahj","23"));//审查起诉3
        result.put("ys",jbxxService.getJbxxCountByField(jsbh,"bahj","31"));//一审
        result.put("es",jbxxService.getJbxxCountByField(jsbh,"bahj","32"));//二审

        //实时状态
        result.put("ts",tsdjService.getTsdjCount(jsbh));//提审
        result.put("lshj",lshjService.getLshjCount(jsbh));//律师会见
        result.put("jshj",jshjService.getJshjCount(jsbh));//家属会见
        result.put("lsts",lscsService.getLscsCountByField(jsbh,"csyy","3"));//离所探视
        result.put("jj",xjService.getXjCount(jsbh));//械具
        result.put("jb",jbqkService.getJbCount(jsbh));//禁闭

        //重点人员
        result.put("zdry",zdryService.getZdryCount(jsbh));//重点人员
        result.put("ptbh",jbxxService.getJbxxCountByField(jsbh,"bhlx","3"));//普通病号
        result.put("zdbjdx",bjjlService.getBjjlCount(jsbh));//重点帮教对象
        result.put("swjyzl",jyService.getJyCountByField(jsbh,"csjylx","1"));//所外就医-治疗
        result.put("swjyzy",jyService.getJyCountByField(jsbh,"csjylx","2"));//所外就医-住院

//        result.put("rstj",rstj);
//        result.put("sshj",sshj);
//        result.put("sszt",sszt);
//        result.put("zdry",zdry);

        return result;
    }
    @GetMapping("/queryJsLsp")
    @ApiOperation("加载本监室流水牌数据")
    @OpenAPI
    public Map<String,Object> queryLsp (String jsbh,String jsh){
        Map<String , Object> result = Maps.newHashMap();
        result.put("zs",jbxxService.getJbxxR8CountWithJs(jsbh,jsh));//总数
        result.put("lsjy",jbxxService.getJbxxCountByFieldWithJs(jsbh,jsh,"rsxz","13"));//临时寄押
        //获取今天日期
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        result.put("xsrs",jbxxService.queryZyzrsWithJs(date,date,jsbh,jsh));//新收入所
        result.put("xsjl",jbxxService.getJbxxCountByFieldWithJs(jsbh,jsh,"bahj","11"));//刑事拘留
        result.put("db",jbxxService.getJbxxCountByFieldWithJs(jsbh,jsh,"bahj","12"));//逮捕
        result.put("ys",jbxxService.getJbxxCountByFieldWithJs(jsbh,jsh,"bahj","31"));//一审
        result.put("es",jbxxService.getJbxxCountByFieldWithJs(jsbh,jsh,"bahj","32"));//二审
        result.put("lsts",lscsService.getLscsCountByCsyyWithJs(jsbh,jsh,"3"));//离所探视
        result.put("scqs1",jbxxService.getJbxxCountByFieldWithJs(jsbh,jsh,"bahj","21"));//审查起诉1
        result.put("scqs2",jbxxService.getJbxxCountByFieldWithJs(jsbh,jsh,"bahj","22"));//审查起诉2
        result.put("scqs3",jbxxService.getJbxxCountByFieldWithJs(jsbh,jsh,"bahj","23"));//审查起诉3
        result.put("jb",jbxxService.getJbxxCountByFieldWithJs(jsbh,jsh,"jb","02"));//禁闭
        result.put("swjyzl",jyService.getJyCountByCsjylx(jsbh,jsh,"CSJYLX","1"));//所外就医-治疗
        result.put("swjyzy",jyService.getJyCountByCsjylx(jsbh,jsh,"CSJYLX","2"));//所外就医-住院
        result.put("zdry",jbxxService.getJbxxCountByFieldWithJs(jsbh,jsh,"zdry","1"));//重点人员
        result.put("txrs",tsdjService.getJsTsdjCount(jsbh,jsh));//提讯人数
        result.put("thrs",thjyService.getBzJsThrs(jsbh,jsh));//本日监室谈话人数
        result.put("ptbh",jbxxService.getJsBhrsByBhlx(jsbh,jsh,"bhlx","3"));//普通病号
        //result.put("bzwgrs",wgsjclService.getBzwgrs(jsbh,jsh));//本周违规人数,已放在wgclControll中
        System.err.println(JSON.toJSONString(result));
        return result;
    }
}
