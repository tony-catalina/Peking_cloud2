package awd.cloud.platform.servers.analyse.controller.kss;

import awd.cloud.platform.servers.analyse.service.kss.Kss_JlglService;
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
@RequestMapping("/kss/jlgl")
public class JlglController {
    @Autowired
    private Kss_JlglService kss_jlglService;

    @GetMapping("/jlglYwdt")
    @ApiOperation("奖励管理业务动态")
    @OpenAPI
    public ResponseMessage<?>  jlglYwdt(String starttime, String endtime, String jsbh,String jsh){
        /*System.out.println("-----=||||||||||||++"+starttime+"--"+endtime+"--"+jsbh+"--"+jsh);*/
        try {
            List<Map<String,Object>>   jlglYwdt=kss_jlglService.jlglYwdt(starttime, endtime, jsbh,jsh);
            Map<String,Object> map=new HashMap<>();
            map.put("jlglYwdt",jlglYwdt);
            return     ResponseMessage.ok(map);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseMessage.error("失败");
        }
    }
}
