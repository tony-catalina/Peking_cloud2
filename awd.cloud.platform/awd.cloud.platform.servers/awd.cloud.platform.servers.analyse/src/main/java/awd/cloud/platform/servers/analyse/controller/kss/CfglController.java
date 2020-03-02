package awd.cloud.platform.servers.analyse.controller.kss;

import awd.cloud.platform.servers.analyse.service.kss.Kss_CfglService;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kss/cfgl")
@Api(tags = "kss_aqjc",description = "安全检查")
public class CfglController {
    @Autowired
    private Kss_CfglService kss_cfglService;

    @GetMapping("/cfglYwdt")
    @ApiOperation("惩罚管理业务动态")
    @OpenAPI
    public ResponseMessage<?> cfglYwdt (String starttime, String endtime, String jsbh,String jsh){
        try {
            List<Map<String,Object>>  cfglYwdt=kss_cfglService.cfglYwdt(starttime, endtime, jsbh,jsh);
            Map<String,Object> map=new HashMap<>();
            map.put("cfglYwdt",cfglYwdt);
            return ResponseMessage.ok(map);
        }catch (Exception e){
             e.printStackTrace();
             return ResponseMessage.error("失败");
        }

    }
}
