package awd.cloud.platform.webs.charts.controller;

import awd.cloud.platform.webs.charts.api.AnalyseApis;
import awd.framework.common.utils.OpenAPI;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Author：YaoBen
 * Date：2019-12-25 13:48
 * Description：<描述>
 */
@RestController
@RefreshScope
@RequestMapping("/kssdp")
public class KssdpController {

    @Autowired
    private AnalyseApis analyseApis;


    @GetMapping("/select_fzx")
    @ResponseBody
    @OpenAPI
    @ApiOperation("法制型查询")
    public Map<String, Object> select_fzx(){
        Map<String, Object> map = analyseApis.select_fzx();
        System.err.println("map--"+JSON.toJSONString(map));
        return map;
    }

    @GetMapping("/select_fwx")
    @ResponseBody
    @OpenAPI
    @ApiOperation("服务型查询")
    public Map<String, Object> select_fwx(){
        Map<String, Object> map = analyseApis.select_fwx();
        System.err.println("map--"+JSON.toJSONString(map));
        return map;
    }



    @GetMapping("/select_aqx")
    @ResponseBody
    @OpenAPI
    @ApiOperation("安全型查询")
    public Map<String, Object> select_aqx(){
        Map<String, Object> map = analyseApis.select_aqx();
        System.err.println("map--"+JSON.toJSONString(map));
        return map;
    }


    @GetMapping("/select_jspf")
    @ResponseBody
    @OpenAPI
    @ApiOperation("监所评分查询")
    public Map<String, Object> select_jspf(){
        Map<String, Object> map = analyseApis.select_jspf();
        System.err.println("map--"+JSON.toJSONString(map));
        return map;
    }

    @GetMapping("/select_zhx")
    @ResponseBody
    @OpenAPI
    @ApiOperation("智慧型查询")
    public Map<String, Object> select_zhx(){
        Map<String, Object> map = analyseApis.select_zhx();
        System.err.println("map--"+JSON.toJSONString(map));
        return map;
    }


    @GetMapping("/select_ljx")
    @ResponseBody
    @OpenAPI
    @ApiOperation("廉洁型查询")
    public Map<String, Object> select_ljx(){
        Map<String, Object> map = analyseApis.select_ljx();
        System.err.println("map--"+JSON.toJSONString(map));
        return map;
    }
    @GetMapping("/select_ssqk")
    @ResponseBody
    @OpenAPI
    @ApiOperation("诉讼情况")
    public Map<String, Object> select_ssqk(HttpServletRequest request){
        String rybh=request.getParameter("rybh");
        Map<String, Object> map = analyseApis.select_ssqk(rybh);
        System.err.println("map--"+JSON.toJSONString(map));
        return map;
    }
    @GetMapping("/zyrygyl")
    @ResponseBody
    @OpenAPI
    @ApiOperation("在押人员关押量分析")
    public Map<String, Object> zyrygyl(){
        Map<String, Object> map = analyseApis.zyrygyl();
        System.err.println("map--"+JSON.toJSONString(map));
        return map;
    }
    @GetMapping("/hjdfx")
    @ResponseBody
    @OpenAPI
    @ApiOperation("户籍地分析")
    public Map<String, Object> hjdfx(){
        Map<String, Object> map = analyseApis.hjdfx();
        System.err.println("map--"+JSON.toJSONString(map));
        return map;
    }
    @GetMapping("/nlfx")
    @ResponseBody
    @OpenAPI
    @ApiOperation("年龄分析")
    public Map<String, Object> nlfx(){
        Map<String, Object> map = analyseApis.nlfx();
        System.err.println("map--"+JSON.toJSONString(map));
        return map;
    }
    @GetMapping("/whcd")
    @ResponseBody
    @OpenAPI
    @ApiOperation("文化程度分析")
    public Map<String, Object> whcd(){
        Map<String, Object> map = analyseApis.whcd();
        System.err.println("map--"+JSON.toJSONString(map));
        return map;
    }
    @GetMapping("/sffx")
    @ResponseBody
    @OpenAPI
    @ApiOperation("身份分析")
    public Map<String, Object> sffx(){
        Map<String, Object> map = analyseApis.sffx();
        System.err.println("map--"+JSON.toJSONString(map));
        return map;
    }
    @GetMapping("/jkqkfx")
    @ResponseBody
    @OpenAPI
    @ApiOperation("健康情况分析")
    public Map<String, Object> jkqkfx(){
        Map<String, Object> map = analyseApis.jkqkfx();
        System.err.println("map--"+JSON.toJSONString(map));
        return map;
    }
    @GetMapping("/jsryfx")
    @ResponseBody
    @OpenAPI
    @ApiOperation("拒收人员分析")
    public Map<String, Object> jsryfx(){
        Map<String, Object> map = analyseApis.jsryfx();
        System.err.println("map--"+JSON.toJSONString(map));
        return map;
    }

    @GetMapping("/rsxz")
    @ResponseBody
    @OpenAPI
    @ApiOperation("入所性质")
    public List rsxz(HttpServletRequest request){
        String jsbh = request.getParameter("jsbh");
       /* String jsbh="110105111";*/
        List map = analyseApis.rsxz(jsbh);
        System.err.println("map--"+JSON.toJSONString(map));
        return map;
    }

    @GetMapping("/ajlb")
    @ResponseBody
    @OpenAPI
    @ApiOperation("案件类别分析")
    public List ajlb(HttpServletRequest request){
        String jsbh = request.getParameter("jsbh");
        /* String jsbh="110105111";*/
        List map = analyseApis.ajlb(jsbh);
        System.err.println("map--"+JSON.toJSONString(map));
        return map;
    }
    @GetMapping("/zccs")
    @ResponseBody
    @OpenAPI
    @ApiOperation("正常出所对比分析")
    public Map<String, Object> zccs(){
        Map<String, Object> map = analyseApis.zccs();
        System.err.println("map--"+JSON.toJSONString(map));
        return map;
    }
    @GetMapping("/lscsfx")
    @ResponseBody
    @OpenAPI
    @ApiOperation("临时出所对比分析")
    public Map<String, Object> lscsfx(){
        Map<String, Object> map = analyseApis.lscsfx();
        System.err.println("map--"+JSON.toJSONString(map));
        return map;
    }
    @GetMapping("/csyyfx")
    @ResponseBody
    @OpenAPI
    @ApiOperation("出所原因分析")
    public List csyyfx(HttpServletRequest request){
        String jsbh = request.getParameter("jsbh");
        /* String jsbh="110105111";*/
        List map = analyseApis.csyyfx(jsbh);
        System.err.println("map--"+JSON.toJSONString(map));
        return map;
    }
    @GetMapping("/yzblfz")
    @ResponseBody
    @OpenAPI
    @ApiOperation("严重暴力犯罪")
    public Map<String, Object> yzblfz(){
        Map<String, Object> map = analyseApis.yzblfz();
        System.err.println("map--"+JSON.toJSONString(map));
        return map;
    }
    @GetMapping("/shsefx")
    @ResponseBody
    @OpenAPI
    @ApiOperation("涉黑涉恶案件分析")
    public List shsefx(HttpServletRequest request){
        String jsbh = request.getParameter("jsbh");
        /* String jsbh="110105111";*/
        List map = analyseApis.shsefx(jsbh);
        System.err.println("map--"+JSON.toJSONString(map));
        return map;
    }
    @GetMapping("/mjsl")
    @ResponseBody
    @OpenAPI
    @ApiOperation("民警数量")
    public Map<String, Object> mjsl(){
        Map<String, Object> map = analyseApis.mjsl();
        System.err.println("map--"+JSON.toJSONString(map));
        return map;
    }
    @GetMapping("/yzjbfx")
    @ResponseBody
    @OpenAPI
    @ApiOperation("严重疾病分析")
    public Map<String, Object> yzjbfx(){
        Map<String, Object> map = analyseApis.yzjbfx();
        System.err.println("map--"+JSON.toJSONString(map));
        return map;
    }
    @GetMapping("/mjxbfx")
    @ResponseBody
    @OpenAPI
    @ApiOperation("民警性别分析")
    public Map<String, Object> mjxbfx(){
        Map<String, Object> map = analyseApis.mjxbfx();
        System.err.println("map--"+JSON.toJSONString(map));
        return map;
    }
    @GetMapping("/swfz")
    @ResponseBody
    @OpenAPI
    @ApiOperation("深挖犯罪")
    public Map<String, Object> swfz(){
        Map<String, Object> map = analyseApis.swfz();
        System.err.println("map--"+JSON.toJSONString(map));
        return map;
    }
    @GetMapping("/mjjxfx")
    @ResponseBody
    @OpenAPI
    @ApiOperation("民警警衔分析")
    public List mjjxfx(HttpServletRequest request){
        String jsbh = request.getParameter("jsbh");
        /* String jsbh="110105111";*/
        List map = analyseApis.mjjxfx(jsbh);
        System.err.println("map--"+JSON.toJSONString(map));
        return map;
    }
    @GetMapping("/zzscfx")
    @ResponseBody
    @OpenAPI
    @ApiOperation("民警在职时长分析")
    public Map<String, Object> zzscfx(){
        Map<String, Object> map = analyseApis.zzscfx();
        System.err.println("map--"+JSON.toJSONString(map));
        return map;
    }


    @GetMapping("/select_kss_hjrs")
    @ResponseBody
    @OpenAPI
    @ApiOperation("kss会见人数分析 : 家属会见、律师会见、提讯、总数")
    public Map<String, Object> select_kss_hjrs(){
        Map<String, Object> map = analyseApis.select_kss_hjrs();
        System.err.println("map--"+JSON.toJSONString(map));
        return map;
    }

    @GetMapping("/select_kss_zszt")
    @ResponseBody
    @OpenAPI
    @ApiOperation("kss在所状态 :  已决、未决")
    public Map<String, Object> select_kss_zszt(){
        Map<String, Object> map = analyseApis.select_kss_zszt();
        System.err.println("map--"+JSON.toJSONString(map));
        return map;
    }


    @GetMapping("/select_kss_rygl")
    @ResponseBody
    @OpenAPI
    @ApiOperation("kss人员管理情况分析 ：械具、禁闭、单独关押、严管人员、耳目")
    public Map<String, Object> select_kss_rygl(){
        Map<String, Object> map = analyseApis.select_kss_rygl();
        System.err.println("map--"+JSON.toJSONString(map));
        return map;
    }

    @GetMapping("/select_kss_xdry")
    @ResponseBody
    @OpenAPI
    @ApiOperation("kss吸毒人员分析 吸毒人数、百分比")
    public Map<String, Object> select_kss_xdry(){
        Map<String, Object> map = analyseApis.select_kss_xdry();
        System.err.println("map--"+JSON.toJSONString(map));
        return map;
    }


    @GetMapping("/select_kss_qsbajd")
    @ResponseBody
    @OpenAPI
    @ApiOperation("kss全省办案阶段分析")
    public List<Object>  select_kss_qsbajd(HttpServletRequest request){
        try {
            String jsbh = request.getParameter("jsbh");
            System.out.println("jsbh==="+jsbh);
            List<Object> map = analyseApis.select_kss_qsbajd(jsbh);
            System.err.println("map姚奔--"+JSON.toJSONString(map));
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }
    }


    @GetMapping("/select_kss_ndrs")
    @ResponseBody
    @OpenAPI
    @ApiOperation("kss年度入所量比对")
    public Map<String, Object> select_kss_ndrs(){
        Map<String, Object> map = analyseApis.select_kss_ndrs();
        System.err.println("map--"+JSON.toJSONString(map));
        return map;
    }


    @GetMapping("/select_kss_fxdj")
    @ResponseBody
    @OpenAPI
    @ApiOperation("kss风险情况分析=人员风险等级分析")
    public Map<String, Object> select_kss_fxdj(){
        Map<String, Object> map = analyseApis.select_kss_fxdj();
        System.err.println("map--"+JSON.toJSONString(map));
        return map;
    }
    @GetMapping("/mjwhcd")
    @ResponseBody
    @OpenAPI
    @ApiOperation("民警文化程度分析")
    public Map<String, Object> mjwhcd(){
        Map<String, Object> map = analyseApis.mjwhcd();
        System.err.println("map--"+JSON.toJSONString(map));
        return map;
    }

}
