package awd.cloud.platform.servers.analyse.controller.kss;

import awd.cloud.platform.servers.analyse.service.kss.Kss_JbqkService;
import awd.cloud.platform.servers.analyse.vo.AnalysisResultVO;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by wuyu on 2019/7/4 14:11
 **/
@RestController
@RequestMapping("/kss/jbqk")
public class JbqkController {
    @Autowired
    private Kss_JbqkService kss_jbqkService;
    @GetMapping("/rqcxJ")
    @ApiOperation("禁闭情况")
    @OpenAPI
    public Map<String, Object> jbqk_rq(@RequestParam(value="kssj",required = false) String kssj,
                                       @RequestParam(value="jssj",required = false) String jssj){
        Map<String, Object> result=new HashMap<String, Object>();
        if(kssj != null){
            kssj += " 00:00:00";
        }
        if(jssj != null){
            jssj += " 23:59:59";
        }
        List<Map<String, Object>> list= kss_jbqkService.jb_rqcxJ(kssj,jssj);
        if(list==null) {
            result.put("state", 400);
            result.put("data", null);
            result.put("msg", "分析失败");
        }else {
            result.put("state", 200);
            result.put("data", list);
            result.put("msg", "分析成功");
        }

        return result;
    }

    @GetMapping("/swj_rqcxJ")
    @ApiOperation("上位机_禁闭情况")
    @OpenAPI
    public Map<String, Object> swj_jbqk_rq(@RequestParam(value="kssj",required = false) String kssj,
                                       @RequestParam(value="jssj",required = false) String jssj){
        Map<String, Object> result=new HashMap<String, Object>();
        if(kssj != null){
            kssj += " 00:00:00";
        }
        if(jssj != null){
            jssj += " 23:59:59";
        }
        List<AnalysisResultVO> list= kss_jbqkService.swj_jb_rqcxJ(kssj,jssj);
        if(list==null) {
            result.put("state", 400);
            result.put("data", null);
            result.put("msg", "分析失败");
        }else {
            result.put("state", 200);
            result.put("data", list);
            result.put("msg", "分析成功");
        }

        return result;
    }


}
