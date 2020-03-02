package awd.cloud.platform.servers.analyse.controller.kss;

import awd.cloud.platform.servers.analyse.service.kss.Kss_JshjService;
import awd.cloud.platform.servers.analyse.service.kss.Kss_LshjService;
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

@RestController
@RequestMapping("/kss/lshj")
public class LshjController {
    @Autowired
    private Kss_LshjService kss_lshjService;

    @GetMapping("/lshjCx")
    @ApiOperation("律师会见")
    @OpenAPI
    public Map<String, Object> jshj_rq(@RequestParam(value = "hjsj" ,required = false) String hjsj,
                                       @RequestParam(value = "jssj" ,required = false) String jssj){
        if(hjsj != null){
            hjsj += " 00:00:00";
        }
        if(jssj != null ){
            jssj += " 23:59:59";
        }
        Map<String, Object> result = new HashMap<String, Object>();
        List<Map<String, Object>> list= kss_lshjService.Lshj_rqFx(hjsj ,jssj );
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

    @GetMapping("/swj_lshjCx")
    @ApiOperation("上位机_律师会见")
    @OpenAPI
    public Map<String, Object> swj_jshj_rq(@RequestParam(value = "hjsj" ,required = false) String hjsj,
                                       @RequestParam(value = "jssj" ,required = false) String jssj){
        if(hjsj != null){
            hjsj += " 00:00:00";
        }
        if(jssj != null ){
            jssj += " 23:59:59";
        }
        Map<String, Object> result = new HashMap<String, Object>();
        List<AnalysisResultVO> list= kss_lshjService.Swj_Lshj_rqFx(hjsj ,jssj );
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
