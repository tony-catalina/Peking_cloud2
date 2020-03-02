package awd.cloud.platform.servers.analyse.controller.kss;

import awd.cloud.platform.servers.analyse.service.kss.Kss_XjService;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kss/xj")
public class Xjcontroller {
    @Autowired
    private Kss_XjService kss_xjService;

    @GetMapping("/xjYwdt")
    @ApiOperation("械具业务动态")
    @OpenAPI
    public ResponseMessage<?> xjYwdt (String starttime, String endtime, String jsbh){
        try {
        	List<Map<String,Object>> xjZrs=kss_xjService.xjZrs(starttime, endtime, jsbh);
            List<Map<String,Object>> xjSycp=kss_xjService.xjSycp(starttime, endtime, jsbh);
            List<Map<String,Object>> xjSydj=kss_xjService.xjSydj(starttime, endtime, jsbh);
            List<Map<String,Object>> xjYcdj=kss_xjService.xjYcdj(starttime, endtime, jsbh);
            Map<String,Object> map=new HashMap<>();
            map.put("xjZrs",xjZrs);
            map.put("xjSycp",xjSycp);
            map.put("xjSydj",xjSydj);
            map.put("xjYcdj",xjYcdj);
            return ResponseMessage.ok(map);
        }   catch (Exception e){
            e.printStackTrace();
            return ResponseMessage.error("失败");
        }
    }
}
