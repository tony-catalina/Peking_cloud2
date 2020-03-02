package awd.cloud.platform.servers.analyse.controller.jls;

import awd.cloud.platform.servers.analyse.service.jls.Jls_SwdjService;
import awd.cloud.platform.servers.analyse.service.jls.Jls_ZdryService;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jls_swdj")
@RefreshScope
@Api(tags = "jls_swdj",description = "死亡登记")
public class Jls_SwdjController {
    @Autowired
    private Jls_SwdjService jls_swdjService;

    @GetMapping("/swdjYwdt")
    @ApiOperation("重点人员业务动态")
    @OpenAPI
    public ResponseMessage<Map<String,Object>> swdjYwdt(String startTime, String endTime, String jsbh){
        try {
            System.out.println("调用业务动态----startTime----"+startTime);
            System.out.println("调用业务动态----endTime----"+endTime);
            Map<String,Object>  swdjYwdt=jls_swdjService.swdjYwdt(startTime, endTime, jsbh);
            return  ResponseMessage.ok(swdjYwdt);
        }   catch (Exception e){
            e.printStackTrace();
            return ResponseMessage.error("失败");
        }
    }


}
