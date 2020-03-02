package awd.cloud.platform.servers.analyse.controller.jls;

import awd.cloud.platform.servers.analyse.service.jls.Jls_ClcsService;
import awd.cloud.platform.servers.analyse.service.jls.Jls_QjcsService;
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

import java.util.Map;

@RestController
@RequestMapping("/jls_qjcs")
@RefreshScope
@Api(tags = "jls_qjcs",description = "请假出所")
public class Jls_QjcsController {
    @Autowired
    private Jls_QjcsService jls_QjcsService;

    @GetMapping("/qjcsYwdt")
    @ApiOperation("请假出所业务动态")
    @OpenAPI
    public ResponseMessage<Map<String,Object>> qmcsYwdt(@RequestParam(value="startTime",required = false) String starttime,
                                                        @RequestParam(value="endTime",required = false) String endtime,
                                                        @RequestParam(value = "jsbh",required =true )String jsbh){
        try {
              Map<String,Object> qmcsYwdt=jls_QjcsService.qjcsYwdt(starttime, endtime, jsbh) ;
            return ResponseMessage.ok(qmcsYwdt);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseMessage.error("执行失败");
        }
    }
}
