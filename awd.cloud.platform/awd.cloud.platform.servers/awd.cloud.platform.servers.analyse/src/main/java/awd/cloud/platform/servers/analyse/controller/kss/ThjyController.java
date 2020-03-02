package awd.cloud.platform.servers.analyse.controller.kss;

import awd.cloud.platform.servers.analyse.service.kss.Kss_ThjyService;
import awd.cloud.platform.servers.analyse.vo.AnalysisResultVO;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.ApiOperation;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kss/thjy")
public class ThjyController {
	
	
	@Autowired
    private Kss_ThjyService kss_ThjyService;
	
    @GetMapping("/rqcx")
    @ApiOperation("谈话教育")
    @OpenAPI
    public Map<String, Object> thjy_rq(@RequestParam(value="kssj",required = false) String kssj,
                                       @RequestParam(value="jssj",required = false) String jssj){
        Map<String, Object> result=new HashMap<String, Object>();
        if(kssj != null){
            kssj += " 00:00:00";
        }
        if(jssj != null){
            jssj += " 23:59:59";
        }

        List<AnalysisResultVO> list= kss_ThjyService.thjy_rqcx(kssj,jssj);

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
