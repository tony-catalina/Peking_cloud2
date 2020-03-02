package awd.cloud.platform.servers.analyse.controller.jls;

import awd.cloud.platform.servers.analyse.service.jls.Jls_ThjyService;
import awd.cloud.platform.servers.analyse.vo.AnalysisJlsResultVO;
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
@RequestMapping("/jls")
@RefreshScope
@Api(tags = "jls_thjy",description = "谈话教育")
public class Jls_ThjyController
{
    @Autowired
    private Jls_ThjyService jls_thjyService;


    @GetMapping("/jlsThjyFx")
    @ApiOperation("查询")
    @OpenAPI
    public Map<String, Object> jlsThjyFx(@RequestParam(value="starttime", required = false) String starttime,
        @RequestParam(value="endtime", required = false) String endtime){
        Map<String, Object> result=new HashMap<String, Object>();
        if(starttime != null){
            starttime += " 00:00:00";
        }
        if(endtime != null){
            endtime += " 23:59:59";
        }
        List<AnalysisJlsResultVO> list= jls_thjyService.queryjlsThjyFx(starttime,endtime);
        if(list==null || list.size() == 0) {
            result.put("state", 400);
            result.put("data", null);
            result.put("msg", "分析失败，没有符合条件的结果。");
        }else {
            result.put("state", 200);
            result.put("data", list);
            result.put("msg", "分析成功");
        }
        return result;
    }
}
