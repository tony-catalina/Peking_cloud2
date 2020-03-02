package awd.cloud.platform.servers.analyse.controller.kss;

import awd.cloud.platform.servers.analyse.service.kss.Kss_ClcsService;
import awd.cloud.platform.servers.analyse.service.kss.Kss_PjdjService;
import awd.cloud.platform.servers.analyse.vo.AnalysisResultVO;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.utils.OpenAPI;
import com.alibaba.fastjson.JSON;
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
@RequestMapping("/kss/Clcs")
public class ClcsController {
    @Autowired
    private Kss_ClcsService kss_clcsService;

    @GetMapping("/clcsCx")
    @ApiOperation("出所查询")
    @OpenAPI
    public Map<String , Object> clcs_rq(@RequestParam(value = "starttime",required = false) String starttime,
                                        @RequestParam(value = "endtime",required = false) String endtime){

        if(starttime != null){
            starttime += " 00:00:00";
        }
        if(endtime != null ){
            endtime += " 23:59:59";
        }
        Map<String, Object> result = new HashMap<String, Object>();
        List<Map<String, Object>> list= kss_clcsService.Clcs_rqFx(starttime ,endtime );
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


    @GetMapping("/swj_clcsCx")
    @ApiOperation("上位机_出所查询")
    @OpenAPI
    public Map<String , Object> swj_clcs_rq(@RequestParam(value = "starttime",required = false) String starttime,
                                        @RequestParam(value = "endtime",required = false) String endtime){

        if(starttime != null){
            starttime += " 00:00:00";
        }
        if(endtime != null ){
            endtime += " 23:59:59";
        }
        Map<String, Object> result = new HashMap<String, Object>();
        List<AnalysisResultVO> list = kss_clcsService.Swj_clcs_rqFx(starttime ,endtime );
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



    @GetMapping("/clcsYwdt")
    @ApiOperation("处理出所业务动态")
    @OpenAPI
    public ResponseMessage<Map<String,Object>> clcsYwdt(@RequestParam(value="startTime",required = false) String starttime,
                                                        @RequestParam(value="endTime",required = false) String endtime,
                                                        @RequestParam(value = "jsbh",required =false )String jsbh){
        try {
              List<Map<String,Object>> clcsYwdtList=kss_clcsService.clcsYwdt(starttime, endtime, jsbh) ;
              Map<String,Object> map=new HashMap<>();
              map.put("clcsYwdt",clcsYwdtList);
            return ResponseMessage.ok(map);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseMessage.error("执行失败");
        }
    }
}
