package awd.cloud.platform.servers.analyse.controller.kss;

import awd.cloud.platform.servers.analyse.api.AwdapiService;
import awd.cloud.platform.servers.analyse.model.kss.ShgxModel;
import awd.cloud.platform.servers.analyse.service.kss.Kss_ShgxService;
import awd.cloud.platform.servers.analyse.vo.AnalysisResultVO;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.core.param.TermType;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kss")
public class ShgxController {
    @Autowired
    private Kss_ShgxService kss_shgxService;


    @GetMapping("/shgxnum")
    @ApiOperation("社会关系")
    @OpenAPI
    public Map<String, Object> getShgxnum(@RequestParam(value="starttime", required = false) String starttime,
                                          @RequestParam(value="endtime", required = false) String endtime){
        Map<String, Object> result=new HashMap<String, Object>();
        if(starttime != null){
            starttime += " 00:00:00";
        }
        if(endtime != null){
            endtime += " 23:59:59";
        }
        List<AnalysisResultVO> list= kss_shgxService.queryShgxList(starttime,endtime);
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
