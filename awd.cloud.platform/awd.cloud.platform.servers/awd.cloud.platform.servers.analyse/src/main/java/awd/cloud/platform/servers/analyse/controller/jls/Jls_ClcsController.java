package awd.cloud.platform.servers.analyse.controller.jls;

import awd.cloud.platform.servers.analyse.service.jls.Jls_ClcsService;
import awd.cloud.platform.servers.analyse.vo.AnalysisJlsResultVO;
import awd.framework.common.controller.message.ResponseMessage;
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
@RequestMapping("/jls_clcs")
@RefreshScope
@Api(tags = "jls_clcs",description = "处理出所")
public class Jls_ClcsController {
    @Autowired
    private Jls_ClcsService jls_ClcsService;

    @GetMapping("/csCx")
    @ApiOperation("拘留所出所查询")
    @OpenAPI
    public Map<String , Object> Clcs_rqFx(@RequestParam(value = "starttime",required = false) String starttime,
                                        @RequestParam(value = "endtime",required = false) String endtime){

        if(starttime != null){
            starttime += " 00:00:00";
        }
        if(endtime != null ){
            endtime += " 23:59:59";
        }
        Map<String, Object> result = new HashMap<String, Object>();
        List<AnalysisJlsResultVO> list= jls_ClcsService.Clcs_rqFx(starttime ,endtime );
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



    @GetMapping("/qmcsYwdt")
    @ApiOperation("期满出所业务动态")
    @OpenAPI
    public ResponseMessage<Map<String,Object>> qmcsYwdt(@RequestParam(value="startTime",required = false) String starttime,
                                                        @RequestParam(value="endTime",required = false) String endtime,
                                                        @RequestParam(value = "jsbh",required =true )String jsbh){
        try {
              Map<String,Object> qmcsYwdt=jls_ClcsService.qmcsYwdt(starttime, endtime, jsbh) ;
            return ResponseMessage.ok(qmcsYwdt);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseMessage.error("执行失败");
        }
    }

    @GetMapping("/tqjcYwdt")
    @ApiOperation("提前解除业务动态")
    @OpenAPI
    public ResponseMessage<Map<String,Object>> tqjcYwdt(@RequestParam(value="startTime",required = false) String starttime,
                                                        @RequestParam(value="endTime",required = false) String endtime,
                                                        @RequestParam(value = "jsbh",required =true )String jsbh){
        try {
            Map<String,Object> tqjcYwdt=jls_ClcsService.tqjcYwdt(starttime, endtime, jsbh) ;
            return ResponseMessage.ok(tqjcYwdt);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseMessage.error("执行失败");
        }
    }
}
