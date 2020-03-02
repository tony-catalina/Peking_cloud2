package awd.cloud.platform.servers.analyse.controller.jls;

import awd.cloud.platform.servers.analyse.service.jls.Jls_ZdryService;
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
@RequestMapping("/jls_zdry")
@RefreshScope
@Api(tags = "jls_zdry",description = "重点人员")
public class Jls_ZdryController {
    @Autowired
    private Jls_ZdryService jls_zdryService;

    @GetMapping("/zdryYwdt")
    @ApiOperation("重点人员业务动态")
    @OpenAPI
    public ResponseMessage<?> zdryYwdt(String starttime, String endtime, String jsbh){
        try {
            List<Map<String,Object>>     zdryYwdtList=jls_zdryService.zdry_dtFx(starttime, endtime, jsbh);
            Map<String,Object> map=new HashMap<>();
            map.put("zdryYwdt",zdryYwdtList);
            return  ResponseMessage.ok(map);
        }   catch (Exception e){
            e.printStackTrace();
            return ResponseMessage.error("失败");
        }
    }


}
