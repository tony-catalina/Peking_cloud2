package awd.cloud.platform.servers.analyse.controller.kss;

import awd.cloud.platform.servers.analyse.service.kss.Kss_FssgService;
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
 * Create by wuyu on 2019/7/4 15:09
 **/
@RestController
@RequestMapping("/kss/fssg")
public class FssgController {
    @Autowired
    private Kss_FssgService kss_fssgService;
    @GetMapping("/rqcxF")
    @ApiOperation("事故情况")
    @OpenAPI
    public Map<String, Object> fssg_rq(@RequestParam(value="starttime",required = false) String starttime,
                                       @RequestParam(value="endtime",required = false) String endtime){
        Map<String, Object> result=new HashMap<String, Object>();
        if(starttime != null){
            starttime += " 00:00:00";
        }
        if(endtime != null){
            endtime += " 23:59:59";
        }
        List<AnalysisResultVO> list= kss_fssgService.fssg_rqcxF(starttime,endtime);
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


