package awd.cloud.platform.servers.analyse.controller.jls;

import awd.cloud.platform.servers.analyse.service.jls.Jls_jshjService;
import awd.cloud.platform.servers.analyse.vo.AnalysisJlsResultVO;
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
 * Author：YaoBen
 * Date：2020-02-07 15:39
 * Description：<描述>
 */

@RestController
@RequestMapping("/jls/jshj")
public class Jls_JshjController {

    @Autowired
    private Jls_jshjService jls_jshjService;

    @GetMapping("/jshjCx")
    @ApiOperation("拘留所家属会见")
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
        List<Map<String, Object>> list= jls_jshjService.Jshj_rqFx(hjsj ,jssj );
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



    @GetMapping("/swj_jshjCx")
    @ApiOperation("拘留所家属会见=上位机版本")
    @OpenAPI
    public Map<String, Object> swj_jshjCx(@RequestParam(value = "hjsj" ,required = false) String hjsj,
                                       @RequestParam(value = "jssj" ,required = false) String jssj){
        if(hjsj != null){
            hjsj += " 00:00:00";
        }
        if(jssj != null ){
            jssj += " 23:59:59";
        }
        Map<String, Object> result = new HashMap<String, Object>();
        List<AnalysisJlsResultVO> list= jls_jshjService.swj_jshj(hjsj ,jssj );
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
