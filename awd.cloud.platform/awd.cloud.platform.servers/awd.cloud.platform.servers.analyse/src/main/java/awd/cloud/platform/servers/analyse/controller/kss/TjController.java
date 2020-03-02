package awd.cloud.platform.servers.analyse.controller.kss;

import awd.cloud.platform.servers.analyse.service.kss.Kss_TjService;
import awd.framework.common.controller.message.ResponseMessage;
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

@RestController
@RequestMapping("/kss/tj")
public class TjController {
    @Autowired
    private Kss_TjService kss_tjService;

    @GetMapping("/tjYwdt")
    @ApiOperation("提解业务动态")
    @OpenAPI
    public ResponseMessage<Map<String,Object>> tjYwdt(@RequestParam(value="startTime",required = false) String starttime,
                                                        @RequestParam(value="endTime",required = false) String endtime,
                                                        @RequestParam(value = "jsbh",required =false )String jsbh){

        try {
            List<Map<String,Object>>   tjYwdtList=kss_tjService.tjYwdt(starttime, endtime, jsbh);
            Map<String,Object> map =new HashMap<>();
            map.put("tjYwdt",tjYwdtList);
            return ResponseMessage.ok(map);
        } catch (Exception e){
                e.printStackTrace();
                return ResponseMessage.error("失败");
        }


    }
}
