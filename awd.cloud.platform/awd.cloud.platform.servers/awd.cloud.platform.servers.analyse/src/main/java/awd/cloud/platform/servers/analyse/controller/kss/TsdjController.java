package awd.cloud.platform.servers.analyse.controller.kss;

import awd.cloud.platform.servers.analyse.service.kss.Kss_TsdjService;
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
@RequestMapping("/kss/tsdj")
public class TsdjController {
	
	
	@Autowired
    private Kss_TsdjService kss_tsdjService;

    @GetMapping("/tsdjnum")
    @ApiOperation("提审登记")
    @OpenAPI
    public Map<String, Object> getTsdjnum(@RequestParam(value="kssj", required = false) String kssj,
                                          @RequestParam(value="jssj", required = false) String jssj){
        Map<String, Object> result=new HashMap<String, Object>();
        if(kssj != null){
            kssj += " 00:00:00";
        }
        if(jssj != null){
            jssj += " 23:59:59";
        }
        List<Map<String, Object>> list= kss_tsdjService.queryTsdjList(kssj, jssj);
        if(list==null || list.size() == 0) {
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


    @GetMapping("/swj_tsdjnum")
    @ApiOperation("提审登记=上位机版本")
    @OpenAPI
    public Map<String, Object> swj_tsdjnum(@RequestParam(value="kssj", required = false) String kssj,
                                          @RequestParam(value="jssj", required = false) String jssj){
        Map<String, Object> result=new HashMap<String, Object>();
        if(kssj != null){
            kssj += " 00:00:00";
        }
        if(jssj != null){
            jssj += " 23:59:59";
        }
        List<AnalysisResultVO> list= kss_tsdjService.swj_tsdjnum(kssj, jssj);
        if(list==null || list.size() == 0) {
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
