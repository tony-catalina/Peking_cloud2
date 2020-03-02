package awd.cloud.platform.servers.analyse.controller.jls;

import awd.cloud.platform.servers.analyse.model.jls.JbxxModel;
import awd.cloud.platform.servers.analyse.service.jls.Jls_JbxxService;
import awd.cloud.platform.servers.analyse.service.jls.Jls_JcjlService;
import awd.cloud.platform.servers.analyse.service.jls.Jls_SnjyService;
import awd.cloud.platform.servers.analyse.service.jls.Jls_SzjdjlService;
import awd.cloud.platform.servers.analyse.service.jls.Jls_WpjsService;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sound.midi.SysexMessage;

@RestController
@RequestMapping("/jls_wpjs")
@RefreshScope
@Api(tags = "jls_wpjs",description = "物品接收")
public class Jls_WpjsController {
    @Autowired
    private Jls_WpjsService jls_WpjsService;


    @GetMapping("/wpjsCount")
    @ApiOperation("业务动态")
    @OpenAPI
    public ResponseMessage<Map<String, Object>> wpjsCount( @RequestParam(value = "jsbh", required = false) String jsbh,
    		@RequestParam(value = "starttime", required = false) String starttime,
            @RequestParam(value = "endtime", required = false) String endtime) {
    	try {
	        Map<String, Object> result = new HashMap<String, Object>();
	        Map<String, Object> list = jls_WpjsService.wpjsCount(jsbh,starttime,endtime);
	        return ResponseMessage.ok(list);
    	}catch (Exception e){
			e.printStackTrace();
			return ResponseMessage.error("执行失败");
    	}
    }
}