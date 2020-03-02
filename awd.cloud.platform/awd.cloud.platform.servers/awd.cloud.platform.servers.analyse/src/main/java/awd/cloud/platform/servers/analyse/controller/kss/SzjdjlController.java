package awd.cloud.platform.servers.analyse.controller.kss;

import awd.cloud.platform.servers.analyse.service.kss.Kss_SzjdjlService;
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
@RequestMapping("kss/szjdjl")
public class SzjdjlController {
    @Autowired
    private Kss_SzjdjlService kss_szjdjlService;

    @GetMapping("/ldjfYwdt")
    @ApiOperation("领导接访业务动态")
    @OpenAPI
    public ResponseMessage<?>ldjfYwdt(String starttime, String endtime,String jsbh){
        try {
            List<Map<String,Object>> ldjfLfcsList=kss_szjdjlService.ldjfLfcs(starttime, endtime, jsbh);
            List<Map<String,Object>> ldjfClqkList=kss_szjdjlService.ldjfClqk(starttime, endtime, jsbh);
            List<Map<String,Object>> ldjfZrsList=kss_szjdjlService.ldjfZrs(starttime, endtime, jsbh);
            Map<String,Object> map =new HashMap<>();
            map.put("ldjfLfcs",ldjfLfcsList);
            map.put("ldjfClqk",ldjfClqkList);
            map.put("ldjfZrs",ldjfZrsList);
            return ResponseMessage.ok(map);
        }   catch (Exception e){
            e.printStackTrace();
            return ResponseMessage.error("失败");
        }
    }
}
