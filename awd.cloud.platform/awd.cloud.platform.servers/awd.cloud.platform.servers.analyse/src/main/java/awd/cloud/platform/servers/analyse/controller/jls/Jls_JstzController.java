package awd.cloud.platform.servers.analyse.controller.jls;

import awd.cloud.platform.servers.analyse.model.jls.JbxxModel;
import awd.cloud.platform.servers.analyse.service.jls.Jls_JbxxService;
import awd.cloud.platform.servers.analyse.service.jls.Jls_JcjlService;
import awd.cloud.platform.servers.analyse.service.jls.Jls_JstzService;
import awd.cloud.platform.servers.analyse.service.jls.Jls_SnjyService;
import awd.cloud.platform.servers.analyse.service.jls.Jls_SzjdjlService;
import awd.cloud.platform.servers.analyse.vo.AnalysisJlsResultVO;
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
@RequestMapping("/jls_jstz")
@RefreshScope
@Api(tags = "jls_jstz",description = "拘留所监事调整")
public class Jls_JstzController {
    @Autowired
    private Jls_JstzService jls_JstzService;


    @GetMapping("/jstzCount")
    @ApiOperation("业务动态")
    @OpenAPI
    public ResponseMessage<Map<String, Object>> jstzCount( @RequestParam(value = "jsbh", required = false) String jsbh,
    		@RequestParam(value = "starttime", required = false) String starttime,
            @RequestParam(value = "endtime", required = false) String endtime) {
    	try {
	        Map<String, Object> result = new HashMap<String, Object>();
	        List<Map<String, Object>> list = jls_JstzService.jstzCount(jsbh,starttime,endtime);
	        result.put("jstz", list);
	        return ResponseMessage.ok(result);
    	}catch (Exception e){
			e.printStackTrace();
			return ResponseMessage.error("执行失败");
    	}
    }

	@GetMapping("/jstzNum")
	@ApiOperation("监事调整")
	@OpenAPI
	public Map<String, Object> getTsdjnum(@RequestParam(value="starttime", required = false) String starttime,
										  @RequestParam(value="endtime", required = false) String endtime){
		Map<String, Object> result=new HashMap<String, Object>();
		if(starttime != null){
			starttime += " 00:00:00";
		}
		if(endtime != null){
			endtime += " 23:59:59";
		}
		List<AnalysisJlsResultVO> list= jls_JstzService.queryJstzList(starttime, endtime);
		if(list==null || list.size() == 0) {
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
}