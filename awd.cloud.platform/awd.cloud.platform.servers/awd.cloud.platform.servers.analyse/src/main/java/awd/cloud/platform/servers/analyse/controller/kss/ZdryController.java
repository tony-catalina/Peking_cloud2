package awd.cloud.platform.servers.analyse.controller.kss;


import awd.cloud.platform.servers.analyse.service.kss.Kss_ZdryService;
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
@RequestMapping("/kss/zdry")
public class ZdryController {
    @Autowired
    private Kss_ZdryService kss_zdryService;

    @GetMapping("/swj_zdry")
    @ApiOperation("上位机版本=重点人员分析")
    @OpenAPI
    public Map<String , Object> swj_zdry(@RequestParam(value = "starttime",required = false) String starttime,
                                        @RequestParam(value = "endtime",required = false) String endtime){
        if(starttime != null){
            starttime += " 00:00:00";
        }
        if(endtime != null ){
            endtime += " 23:59:59";
        }
        Map<String, Object> result = new HashMap<String, Object>();
        List<Map<String, Object>> list= kss_zdryService.swj_zdry(starttime ,endtime);
        if(list==null) {
            result.put("state", 400);
            result.put("data", null);
            result.put("msg", "分析失败");
        }else {
            result.put("state", 200);
            result.put("data", list);
            result.put("msg", "分析成功");
        }
        return result;
    }

    @GetMapping("/zdryCx")
    @ApiOperation("重点人员分析")
    @OpenAPI
    public Map<String , Object> zdry_rq(@RequestParam(value = "starttime",required = false) String starttime,
                                       @RequestParam(value = "endtime",required = false) String endtime){
        if(starttime != null){
            starttime += " 00:00:00";
        }
        if(endtime != null ){
            endtime += " 23:59:59";
        }
        Map<String, Object> result = new HashMap<String, Object>();
        List<Map<String, Object>> list= kss_zdryService.Zdry_rqFx(starttime ,endtime);
        if(list==null) {
            result.put("state", 400);
            result.put("data", null);
            result.put("msg", "分析失败");
        }else {
            result.put("state", 200);
            result.put("data", list);
            result.put("msg", "分析成功");
        }
        return result;
    }

    @GetMapping("/zdryYwdt")
    @ApiOperation("重点人员业务动态")
    @OpenAPI
    public ResponseMessage<?>    zdryYwdt(String starttime, String endtime,String jsbh,String jsh){
        System.out.println(jsbh);
        System.out.println(jsh);
        try {
            List<Map<String,Object>>     zdryYwdtList=kss_zdryService.zdryYwdt(starttime, endtime, jsbh,jsh);
            Map<String,Object> map=new HashMap<>();
            map.put("zdryYwdt",zdryYwdtList);
            return  ResponseMessage.ok(map);
        }   catch (Exception e){
            e.printStackTrace();
            return ResponseMessage.error("失败");
        }
    }
}
