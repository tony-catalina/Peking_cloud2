package awd.cloud.platform.servers.analyse.controller.jls;

import awd.cloud.platform.servers.analyse.service.jls.Jls_TsdjService;
import awd.cloud.platform.servers.analyse.vo.AnalysisJlsResultVO;
import awd.cloud.platform.servers.analyse.vo.AnalysisResultVO;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jls/tsdj")
@RefreshScope
@Api(tags = "jls_tsdj",description = "提审登记")
public class Jls_TsdjController {

    @Autowired
    private Jls_TsdjService tsdjService;

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
        List<AnalysisJlsResultVO> list= tsdjService.queryTsdjList(kssj, jssj);
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
