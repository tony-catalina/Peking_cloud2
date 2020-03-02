package awd.cloud.platform.servers.analyse.controller.kss;

import awd.cloud.platform.servers.analyse.service.kss.Kss_LscsService;
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
@RequestMapping("/kss/lscs")
public class LscsController {

    @Autowired
    private Kss_LscsService kss_lscsService;

    @GetMapping("/lscsYwdt")
    @ApiOperation("处理出所业务动态")
    @OpenAPI
    public ResponseMessage<?> lscsYwdt(@RequestParam(value = "jsbh",required =false )String jsbh){

        try {

            List<Map<String,Object>> zrcsList= kss_lscsService.lscsZrcs(jsbh);
            List<Map<String,Object>>  zrcswgList=kss_lscsService.lscsZrcswg(jsbh);
            List<Map<String,Object>> jrcsList=kss_lscsService.lscsJrcs(jsbh);
            List<Map<String,Object>> jrcswgList=kss_lscsService.lscsJrcswg(jsbh);
            List<Map<String,Object>> bzcsList=kss_lscsService.lscsBzcs(jsbh);
            List<Map<String,Object>> bzcswgList=kss_lscsService.lscsBzcswg(jsbh);
            List<Map<String,Object>> bycsList=kss_lscsService.lscsBycs(jsbh);
            List<Map<String,Object>> bycswgList=kss_lscsService.lscsBycswg(jsbh);

            Map<String,Object> map = new HashMap();
            map.put("zrcs",zrcsList);
            map.put("zrcswg",zrcswgList);
            map.put("jrcs",jrcsList);
            map.put("jrcswg",jrcswgList);
            map.put("bzcs",bzcsList);
            map.put("bzcswg",bzcswgList);
            map.put("bycs",bycsList);
            map.put("bycswg",bycswgList);
            return ResponseMessage.ok(map);
        }   catch (Exception e){
            e.printStackTrace();
            return ResponseMessage.error("失败");
        }
    }
}
