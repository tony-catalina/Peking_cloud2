package awd.cloud.platform.servers.analyse.controller.jds;

import awd.cloud.platform.servers.analyse.service.jds.Jds_JbxxService;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jds/jbxx")
@Api(tags = "jds_jbxx",description = "戒毒所基本信息")
public class Jds_JbxxController {

    @Autowired
    private Jds_JbxxService jbxxService;



    @GetMapping("/swj_csrsFX")
    @ApiOperation("出所人数分析查询=上位机")
    @OpenAPI
    public ResponseMessage<?> swj_csrsFX (@RequestParam(required = false, value = "starttime") String starttime, @RequestParam(required = false, value = "endtime") String endtime ){


        try {
            List<Map<String, Object>> swjCsrsFX = jbxxService.swj_csrsFX(starttime, endtime);
            return  ResponseMessage.ok(swjCsrsFX);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("查询失败");
        }

    }



    @GetMapping("/swj_xdsjFX")
    @ApiOperation("人员吸毒时间分析查询=上位机")
    @OpenAPI
    public ResponseMessage<?> swj_xdsjFX (@RequestParam(required = false, value = "starttime") String starttime, @RequestParam(required = false, value = "endtime") String endtime ){


        try {
            List<Map<String, Object>> swjXdsjFX = jbxxService.swj_xdsjFX(starttime, endtime);
            return  ResponseMessage.ok(swjXdsjFX);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("查询失败");
        }
    }

    //上位机戒毒所在戒人数分析查询
    @GetMapping("/swj_zjrsFX")
    @ApiOperation("上位机戒毒所在戒人数分析查询")
    @OpenAPI
    public ResponseMessage<?> swj_zjrsFX (@RequestParam(required = false, value = "starttime") String starttime, @RequestParam(required = false, value = "endtime") String endtime ){

        try {
            List<Map<String, Object>> result = jbxxService.swj_zjrsFX(starttime, endtime);
            return  ResponseMessage.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("查询失败");
        }
    }

    @GetMapping("/swjRynlFx")
    @ApiOperation("上位机--人员年龄分析")
    @OpenAPI
    public ResponseMessage<?> getRynlFx (@RequestParam(value = "starttime") String starttime, @RequestParam(value = "endtime") String endtime ){
        try {
            List<Map<String, Object>> swRynlFX = jbxxService.getRynlFx(starttime, endtime);
            return  ResponseMessage.ok(swRynlFX);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("查询失败");
        }
    }
}
