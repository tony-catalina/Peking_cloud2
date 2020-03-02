package awd.cloud.platform.servers.analyse.controller.kss;

import awd.cloud.platform.servers.analyse.service.kss.Kss_JshjService;
import awd.cloud.platform.servers.analyse.vo.AnalysisResultVO;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kss/jshj")
public class JshjController {
    @Autowired
    private Kss_JshjService kss_jshjService;

    @GetMapping("/jshjCx")
    @ApiOperation("家属会见")
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
        List<Map<String, Object>> list= kss_jshjService.Jshj_rqFx(hjsj ,jssj );
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

    @GetMapping("/jshjfx")
    @ApiOperation("上位机家属会见统计")
    @OpenAPI
    public Map<String, Object> jshjfx(@RequestParam(value = "hjsj" ,required = false) String hjsj,
                                       @RequestParam(value = "jssj" ,required = false) String jssj){
        if(hjsj != null){
            hjsj += " 00:00:00";
        }
        if(jssj != null ){
            jssj += " 23:59:59";
        }
        Map<String, Object> result = new HashMap<String, Object>();
        List<AnalysisResultVO> list= kss_jshjService.jshjFx(hjsj ,jssj );
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


