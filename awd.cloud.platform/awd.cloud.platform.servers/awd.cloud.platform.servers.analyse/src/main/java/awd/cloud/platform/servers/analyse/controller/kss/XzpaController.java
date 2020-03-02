package awd.cloud.platform.servers.analyse.controller.kss;

import awd.cloud.platform.servers.analyse.service.kss.Kss_XzpaService;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kss/xzpa")
public class XzpaController {
    @Autowired
    private Kss_XzpaService kss_xzpaService;


    @GetMapping("/xzpaYwdt")
    @ApiOperation("协助破案业务动态")
    @OpenAPI
    public ResponseMessage<?> xzpaYwdt(String starttime, String endtime,String jsbh){
        try {
            List<Map<String,Object>>   xzpaYwdt=kss_xzpaService.xzpaYwdt(starttime, endtime, jsbh);
            Map<String,Object> map=new HashMap<>();
            map.put("xzpaYwdt",xzpaYwdt);
            return ResponseMessage.ok(map);
        }   catch (Exception e){
                e.printStackTrace();
                return ResponseMessage.error("失败");
        }
    }
}
