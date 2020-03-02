package awd.cloud.platform.servers.analyse.controller.jls;

import awd.cloud.platform.servers.analyse.service.jls.Jls_FxpgService;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/jls_fxpg")
@RefreshScope
@Api(tags = "jls_fxpg",description = "死亡登记")
public class Jls_FxpgController {
    @Autowired
    private Jls_FxpgService jls_fxpgService;

    @GetMapping("/fxpgYwdt")
    @ApiOperation("重点人员业务动态")
    @OpenAPI
    public ResponseMessage<Map<String,Object>> fxpgYwdt(String starttime, String endtime, String jsbh){
        try {
            Map<String,Object>  fxpgYwdt=jls_fxpgService.fxpgYwdt(starttime, endtime, jsbh);
            return  ResponseMessage.ok(fxpgYwdt);
        }   catch (Exception e){
            e.printStackTrace();
            return ResponseMessage.error("失败");
        }
    }


}
