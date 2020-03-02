package awd.cloud.platform.servers.analyse.controller.jls;

import awd.cloud.platform.servers.analyse.service.jls.Jls_lshjService;
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
 * Date：2020-02-07 13:35
 * Description：<描述>
 */
@RestController
@RequestMapping("/jls/lshj")
public class Jls_lshjController {

    @Autowired
    private Jls_lshjService jls_lshjService;

    @GetMapping("/lshjCx")
    @ApiOperation("拘留所律师会见")
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
        List<AnalysisJlsResultVO> list= jls_lshjService.Lshj_rqFx(hjsj ,jssj );
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
