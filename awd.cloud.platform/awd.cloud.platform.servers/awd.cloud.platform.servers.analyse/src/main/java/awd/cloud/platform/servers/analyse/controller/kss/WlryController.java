package awd.cloud.platform.servers.analyse.controller.kss;

import awd.cloud.platform.servers.analyse.service.kss.Kss_WlryService;
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
@RequestMapping("kss/wlry")
public class WlryController {
    @Autowired
    private Kss_WlryService kss_wlryService;

    @GetMapping("/wlryYwdt")
    @ApiOperation("外来人员业务动态")
    @OpenAPI
    public ResponseMessage<?> wlryYwdt(@RequestParam(value="starttime",required = false) String starttime,
                                       @RequestParam(value="endtime",required = false) String endtime,
                                       @RequestParam(value = "jsbh",required =false )String jsbh){

        try {
            List<Map<String,Object>> wlryLssyList=kss_wlryService.wlryLssy(starttime, endtime, jsbh);
            List<Map<String,Object>> wlryLfrsList=kss_wlryService.wlryLfrs(starttime, endtime, jsbh);
            List<Map<String,Object>> wlrySxclsList=kss_wlryService.wlrySxcls(starttime, endtime, jsbh);
            Map<String,Object> map=new HashMap<>();
            map.put("wlryLssy",wlryLssyList);
            map.put("wlryLfrs",wlryLfrsList);
            map.put("wlrySxcls",wlrySxclsList);
            return ResponseMessage.ok(map);
        }   catch (Exception e){
            e.printStackTrace();
            return  ResponseMessage.error("失败");
        }

    }

}
